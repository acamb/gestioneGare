package acambieri.sanbernardo.gestionegare

import acambieri.sanbernardo.gestionegare.model.*

import java.util.ArrayList
import java.util.HashMap
import java.util.function.BinaryOperator
import kotlin.streams.toList

object CalcoloPunteggiBL {

    //TODO[AC] provare che dopo la modifica la classifica sia coerente e che  a parita' di posizioni(ascending) guardi la sommaPuntiNormalizzata (descending)

    val sommaPuntiNormalizzatiExpr = { punteggi :HashMap<ArciereVO, StatisticheTorneo>, gara : Gara, partecipazione: Partecipazione, index: Int -> (partecipazione.punteggio.toDouble() * 600.0 / gara.punteggioMassimo.toDouble()).toDouble()}
    val sommaPosizioniExpr = { punteggi :HashMap<ArciereVO, StatisticheTorneo>, gara : Gara, partecipazione: Partecipazione, index: Int->
        punteggi[partecipazione.arciere]!!.punteggi.add(index.toDouble())
    }

    fun calcolaClassificaTorneoSuPunti(gareTorneo: List<Gara>, gruppi: Boolean): List<ClassificaPerDivisione> {
        return calcolaClassifica(gareTorneo,
                gruppi,
                {punti -> Math.round(punti!!.sortedDescending().stream()!!.limit(3).reduce(BinaryOperator<Double> { a, b -> java.lang.Double.sum(a, b) }).get()).toInt()},
                // punti : puntiMassimiGara = x : 600
                { punteggi,g,c,index: Int ->  punteggi[c.arciere]!!.punteggi.add(sommaPuntiNormalizzatiExpr(punteggi,g,c,index)) }
            )
    }

    fun calcolaClassificaSuPosizioni(gareTorneo: List<Gara>, gruppi: Boolean): List<ClassificaPerDivisione> {
        return calcolaClassifica(gareTorneo,
                gruppi,
                {punti: MutableList<Double> ->
                    while(punti.size < 4){
                        punti.add(100.0);
                    }
                    Math.round(punti!!.sorted().stream()!!.limit(3).reduce(BinaryOperator<Double> { a, b -> java.lang.Double.sum(a, b) }).get()).toInt()
                },
                //la posizione e' direttamente l'index
                 fun(punteggi,g,c,index) {

                    sommaPosizioniExpr(punteggi,g,c,index);
                    punteggi[c.arciere]!!.sommaNormalizzata+= sommaPuntiNormalizzatiExpr(punteggi,g,c,index)
                    }

                ,
                compareBy ( {  it.punteggio },{-it.sommaPuntiNormalizzata} )
        )
    }

    fun calcolaClassifica(gareTorneo: List<Gara>, gruppi: Boolean,
                          sommaPuntiExpr: (punteggiList: MutableList<Double>) -> Int,
                          calcoloSingoloPunteggioExpr:  (punteggi: HashMap<ArciereVO,StatisticheTorneo>, gara:Gara, partecipazione: Partecipazione, posizione: Int) -> Any,
                          sortingClassificaComparator: Comparator<in ArciereVO> = compareByDescending {  it.punteggio }): List<ClassificaPerDivisione>{
        val punteggi = HashMap<ArciereVO, StatisticheTorneo>()
        val classifiche = ArrayList<ClassificaPerDivisione>()
        for (g in gareTorneo) {
            //Il dao fornisce una lista ordinata per divisione,punti mentre a noi servono i punti
            val confGare = g.partecipazioni.stream().sorted { o1, o2 -> o2.punteggio - o1.punteggio  }.toList()
            //per ogni gara prendo il punteggio dell'arciere
            confGare.forEachIndexed({ index: Int,c : Partecipazione ->
                if (!punteggi.containsKey(c.arciere)) {
                    if (gruppi) {
                        val arciereVO = ArciereVO(c)
                        arciereVO.divisione = Divisione()
                        /*arciereVO.divisione.id = (if ("A" == c.gruppo) -1 else -2).toLong()
                        arciereVO.divisione.descrizione = if ("A" == c.gruppo) "Gruppo A" else "Gruppo B"*/
                        when{
                            "A" == c.gruppo || "A1" == c.gruppo -> { arciereVO.divisione.id = -1 ; arciereVO.divisione.descrizione = "Gruppo A"}
                            !c.isEscludiClassifica && (c.gruppo== "B" || c.gruppo== "B1") -> { arciereVO.divisione.id = -2 ; arciereVO.divisione.descrizione = "Gruppo B"}
                            c.isEscludiClassifica -> { arciereVO.divisione.id = -3 ; arciereVO.divisione.descrizione = "Gruppo G"}

                        }
                        punteggi.put(arciereVO, StatisticheTorneo())
                    } else {
                        punteggi.put(ArciereVO(c), StatisticheTorneo())
                    }
                }
                //a seconda del metodo per calcolare la classifica calcolo il singolo punteggio
                calcoloSingoloPunteggioExpr(punteggi,g,c,index+1)
            })
        }
        //prendo le migliori 3 gare per ciascuno
        for (arciere in punteggi.keys) {
            var classifica = ClassificaPerDivisione()
            classifica.divisione = arciere.divisione
            //nel caso non abbia ancora la divisione la creo, altrimenti utilizzo quella esistente
            if (!classifiche.contains(classifica)) {
                classifiche.add(classifica)
            } else {
                classifica = classifiche[classifiche.indexOf(classifica)]
            }
            val punteggio3gare = ArciereVO(arciere)
            val punti = punteggi[punteggio3gare]!!.getPunteggi()
            //calcolo il punteggio totale delle 3 gare
            punteggio3gare.punteggio = sommaPuntiExpr(punti)
            punteggio3gare.sommaPuntiNormalizzata = punteggi[punteggio3gare]!!.sommaNormalizzata;
            classifica.arcieri.add(punteggio3gare)
        }

        classifiche.sortWith(compareBy({it.divisione.descrizione}))
        classifiche.stream().forEach { classifica -> classifica.arcieri.sortWith(sortingClassificaComparator) }
        return classifiche
    }

    fun calcolaClassificaGaraScontriPerGruppi(gara: Gara): List<ClassificaPerDivisione> {
        val classifiche = ArrayList<ClassificaPerDivisione>()
        val confGare = gara.partecipazioni.stream().sorted { o1, o2 -> o2.punteggio - o1.punteggio  }.toList()
        for(conf in confGare){
            val arciere = ArciereVO(conf)
            arciere.divisione = Divisione()
            when {
                conf.gruppo == "A1" && conf.gara.tipiGara.any { tipo -> tipo.id != 3L && tipo.id !=4L } -> {
                    arciere.divisione.id = -1
                    arciere.divisione.descrizione = "A"
                }
                conf.gruppo == "A1" -> {
                    arciere.divisione.id = -1
                    arciere.divisione.descrizione = "A1"
                }
                conf.gruppo == "B1" && conf.gara.tipiGara.any { tipo -> tipo.id != 3L && tipo.id !=4L } -> {
                    arciere.divisione.id = -3
                    arciere.divisione.descrizione = "B"
                }
                conf.gruppo == "B1" -> {
                    arciere.divisione.id = -3
                    arciere.divisione.descrizione = "B1"
                }
                conf.gruppo == "A2" -> {
                    arciere.divisione.id = -2
                    arciere.divisione.descrizione = "A2"
                }
                conf.gruppo == "B2" -> {
                    arciere.divisione.id = -4
                    arciere.divisione.descrizione = "B2"
                }
            }
            var classifica = ClassificaPerDivisione()
            classifica.divisione = arciere.divisione
            //nel caso non abbia ancora la divisione la creo, altrimenti utilizzo quella esistente
            if (!classifiche.contains(classifica)) {
                classifiche.add(classifica)
            } else {
                classifica = classifiche[classifiche.indexOf(classifica)]
            }
            classifica.arcieri.add(arciere)
        }
        classifiche.sortBy { it.divisione.descrizione }
        return classifiche
    }
}
