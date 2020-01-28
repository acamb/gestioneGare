
import { ModuleWithProviders } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {GestioneGareComponent} from './components/gestione-gare/gestione-gare.component';
import {ClassificaGaraComponent} from './components/classifica-gara/classifica-gara.component';
import {GaraResolver} from './resolvers/GaraResolver';
import {TorneoIndoorComponent} from './components/torneo-indoor/torneo-indoor.component';
import {ScontriComponent} from './components/scontri/scontri.component';
import {FiocchiComponent} from './components/fiocchi/fiocchi.component';
import {ModificaGaraContainerComponent} from './components/modifica-gara-container/modifica-gara-container.component';
import {CreazioneGaraContainerComponent} from './components/creazione-gara-container/creazione-gara-container.component';
import {TipoGaraIndoorResolver} from './resolvers/TipoGaraIndoorResolver';
import {ClassificaPerGruppiComponent} from './components/classifica-per-gruppi/classifica-per-gruppi.component';
import {ClassificaPerDivisioniComponent} from './components/classifica-per-divisioni/classifica-per-divisioni.component';
import {TipoGaraFiocchiResolver} from './resolvers/TipoGaraFiocchiResolver';
import {ArcieriListResolver} from './resolvers/ArcieriListResolver';
import {GestioneDivisioniComponent} from './components/gestione-divisioni/gestione-divisioni.component';
import {GironiComponent} from './components/gironi/gironi.component';
import {AuthGuard} from './resolvers/AuthGuard';
import {HomeComponent} from './components/home/home.component';



const menuRoutes: Routes = [
  {
    path: 'authenticate',
    component: HomeComponent
  },
  {
    path: '',
    canActivateChild: [AuthGuard],
    runGuardsAndResolvers: 'always',
    children: [
      {
        path: "gironi/:id",
        component: GironiComponent,
        resolve: {
          gara: GaraResolver
        }
      }, {
        path: "classifica/:id",
        component: ClassificaGaraComponent,
        resolve: {
          gara: GaraResolver
        }
      },
      {
        path: "modifica/:id",
        component: ModificaGaraContainerComponent,
        resolve: {
          garaDaEditare: GaraResolver,
          arcieri: ArcieriListResolver
        },
        data: { roles: ['ROLE_ADMIN','ROLE_EDIT' ] }
      },
      {
        path: "creazione",
        component: CreazioneGaraContainerComponent,
        resolve: {
          arcieri: ArcieriListResolver
        },
        data: { roles: ['ROLE_ADMIN','ROLE_EDIT' ] }
      },
      {
        path: 'gestioneGare',
        component: GestioneGareComponent,
      },
      {
        path: 'torneoIndoor',
        component: TorneoIndoorComponent,
      },
      {
        path: 'classificaIndoorDivisioni/:anno',
        component: ClassificaPerDivisioniComponent,
        resolve: {
          tipo: TipoGaraIndoorResolver
        }
      },
      {
        path: 'classificaIndoorGruppi/:anno',
        component: ClassificaPerGruppiComponent,
        resolve: {
          tipo: TipoGaraIndoorResolver
        }
      },
      {
        path: 'classificaFiocchiDivisioni/:anno',
        component: ClassificaPerDivisioniComponent,
        resolve: {
          tipo: TipoGaraFiocchiResolver
        }
      },
      {
        path: 'classificaFiocchiGruppi/:anno',
        component: ClassificaPerGruppiComponent,
        resolve: {
          tipo: TipoGaraFiocchiResolver
        }
      },
      {
        path: 'scontri',
        component: ScontriComponent,
      },
      {
        path: 'fiocchi',
        component: FiocchiComponent,
      },
      {
        path: 'divisioni',
        component: GestioneDivisioniComponent,
        data: { roles: ["ROLE_ADMIN" ] }
      },
      {
        path: '',
        redirectTo: "gestioneGare",
        pathMatch: 'full'
      }
      ]
  }

];

const appRoutes: Routes = [
	...menuRoutes
];

export const routing: ModuleWithProviders = RouterModule.forRoot(appRoutes, { useHash: true });
