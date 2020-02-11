import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { TranslateLoader, TranslateModule, TranslateService } from '@ngx-translate/core';
import {TranslateHttpLoader} from "@ngx-translate/http-loader";
import { AppComponent } from './app.component';
import {
  AlertModule, BsDropdownModule, ButtonsModule, PaginationModule,
  TooltipModule
} from "ngx-bootstrap";
import { SortableModule } from 'ngx-bootstrap/sortable';
import {environment} from "../environments/environment";
import { GruppiComponent } from './components/gruppi/gruppi.component';
import {FormsModule, NgControl} from "@angular/forms";
import {routing} from "./app.routing";
import {DataTableComponent} from "./components/data-table/app.data-table";
import {HttpClient, HttpClientModule} from "@angular/common/http";
import { CreazioneGaraComponent } from './components/creazione-gara/creazione-gara.component';
import {GareService} from "./gare.service";
import { AnnoValidatorDirective } from './validators/anno-validator.directive';
import { GestioneGareComponent } from './components/gestione-gare/gestione-gare.component';
import { ClassificaGaraComponent } from './components/classifica-gara/classifica-gara.component';
import { TorneoIndoorComponent } from './components/torneo-indoor/torneo-indoor.component';
import { ScontriComponent } from './components/scontri/scontri.component';
import { FiocchiComponent } from './components/fiocchi/fiocchi.component';
import { TableClassificaComponent } from './components/table-classifica/table-classifica.component';
import {GaraResolver} from "./resolvers/GaraResolver";
import { ModificaGaraContainerComponent } from './components/modifica-gara-container/modifica-gara-container.component';
import { CreazioneGaraContainerComponent } from './components/creazione-gara-container/creazione-gara-container.component';
import { StickyOnScrollDirective } from './directives/sticky-on-scroll.directive';
import { ClassifichePerDivisioneContainerComponent } from './components/classifiche-per-divisione-container/classifiche-per-divisione-container.component';
import { ClassificaPerGruppiComponent } from './components/classifica-per-gruppi/classifica-per-gruppi.component';
import {ClassificaPerDivisioniComponent} from "./components/classifica-per-divisioni/classifica-per-divisioni.component";
import {TipoGaraFiocchiResolver} from "./resolvers/TipoGaraFiocchiResolver";
import {TipoGaraIndoorResolver} from "./resolvers/TipoGaraIndoorResolver";
import {ArcieriListResolver} from "./resolvers/ArcieriListResolver";
import { GestioneDivisioniComponent } from './components/gestione-divisioni/gestione-divisioni.component';
import { GaraPickerComponent } from './components/gara-picker/gara-picker.component';
import {ModalComponent} from "./components/modal/modal.component";
import { ArcieriPickerComponent } from './components/arcieri-picker/arcieri-picker.component';
import { CreazioneGruppiComponent } from './components/creazione-gruppi/creazione-gruppi.component';
import { ExtensibleDataTableComponent } from './components/extensible-data-table/extensible-data-table.component';
import { GironiComponent } from './components/gironi/gironi.component';
import { BackButtonComponent } from './components/back-button/back-button.component';
import { HomeComponent } from './components/home/home.component';
import {AuthenticationService} from "./authentication.service";
import {AuthGuard} from "./resolvers/AuthGuard";
import {JwtInterceptorService} from "./jwt-interceptor.service";
import {HTTP_INTERCEPTORS} from "@angular/common/http";
import { RestrictedRoleDirective } from './directives/restricted-role.directive';
import { ManageUsersComponent } from './components/manage-users/manage-users.component';
import { ChangePasswordComponent } from './components/change-password/change-password.component';
import { AdminPanelComponent } from './components/admin-panel/admin-panel.component';
import {UsersService} from "./users.service";

// AoT requires an exported function for factories
export function HttpLoaderFactory(http: HttpClient) {
  return new TranslateHttpLoader(http,"/client/assets/i18n/");
};

@NgModule({
  declarations: [
    AppComponent,
    GruppiComponent,
    DataTableComponent,
    CreazioneGaraComponent,
    AnnoValidatorDirective,
    GestioneGareComponent,
    ClassificaGaraComponent,
    TorneoIndoorComponent,
    ScontriComponent,
    FiocchiComponent,
    TableClassificaComponent,
    ModificaGaraContainerComponent,
    CreazioneGaraContainerComponent,
    StickyOnScrollDirective,
    ClassifichePerDivisioneContainerComponent,
    ClassificaPerGruppiComponent,
    ClassificaPerDivisioniComponent,
    GestioneDivisioniComponent,
    GaraPickerComponent,
    ModalComponent,
    ArcieriPickerComponent,
    CreazioneGruppiComponent,
    ExtensibleDataTableComponent,
    GironiComponent,
    BackButtonComponent,
    HomeComponent,
    RestrictedRoleDirective,
    ManageUsersComponent,
    ChangePasswordComponent,
    AdminPanelComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    routing,
    AlertModule.forRoot(),
    ButtonsModule.forRoot(),
    BsDropdownModule.forRoot(),
    PaginationModule.forRoot(),
    TooltipModule.forRoot(),
    SortableModule.forRoot(),
    TranslateModule.forRoot({
      loader: {
        provide: TranslateLoader,
        useFactory: HttpLoaderFactory,
        deps: [HttpClient]
      }
    })

  ],
  providers: [HttpClient,GareService,GaraResolver,TipoGaraFiocchiResolver,TipoGaraIndoorResolver,ArcieriListResolver,
  AuthenticationService,AuthGuard,UsersService,{
      provide: HTTP_INTERCEPTORS,
      useClass: JwtInterceptorService,
      multi: true
    }],
  bootstrap: [AppComponent]
})
export class AppModule { }

export function extractData(res){
  return res.json() || {};
}

export function getServer(): string{
  let baseUrl = environment.server + environment.context
  return baseUrl;
}
