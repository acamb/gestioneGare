setlocal enabledelayedexpansion
set max=0
for %%x in (gestioneGare-*.jar) do (
    set "FN=%%~nx"
    set "FN=!FN:gestioneGare-=!"
    if !FN! GTR !max! set max=!FN!
)
java -jar gestioneGare-%max%.jar