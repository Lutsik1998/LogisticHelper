import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import * as $ from 'jquery';
import { AppRoutingModule } from './app-routing.module';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { AppComponent } from './app.component';
import { TruckServiceService } from './services/truck-service.service';
import { TruckListComponent } from './modules/truck-list/truck-list.component';
import { DataTablesModule } from 'angular-datatables';
import { TripListComponent } from './modules/trip-list/trip-list.component';
import {Ng2SmartTableModule} from 'ng2-smart-table';
import { OwlDateTimeModule, OwlNativeDateTimeModule } from 'ng-pick-datetime';
import { SmartTableDatepickerComponent, SmartTableDatepickerRenderComponent } from './components/smart-table-datepicker/smart-table-datepicker.component';
import { BrowserAnimationsModule, NoopAnimationsModule } from '@angular/platform-browser/animations';
import { ResetButtonWheelsComponent} from './components/reset-button-wheels/reset-button-wheels.component';
import { ResetButtonOilComponent } from './components/reset-button-oil/reset-button-oil.component';

import { MatTableExporterModule } from 'mat-table-exporter';
import {MatButtonModule} from '@angular/material/button';

import {ExporterService} from './services/exporter.service';
import { NbThemeModule, NbLayoutModule, NbCardModule, NbActionsModule, NbIconModule, NbButtonModule, NbTooltipModule } from '@nebular/theme';
import { NbEvaIconsModule } from '@nebular/eva-icons';





@NgModule({
  declarations: [
    AppComponent,
    TruckListComponent,
    TripListComponent,
    SmartTableDatepickerComponent,
    SmartTableDatepickerRenderComponent,
    ResetButtonWheelsComponent,
    ResetButtonOilComponent,
    
   
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    DataTablesModule,
    Ng2SmartTableModule,
    BrowserAnimationsModule,
    OwlDateTimeModule,
    OwlNativeDateTimeModule,
    NoopAnimationsModule,
    MatTableExporterModule,
    MatButtonModule,
    NbThemeModule.forRoot(),
    NbLayoutModule,
    NbCardModule,
    NbActionsModule,
    NbIconModule,
    NbEvaIconsModule,
    NbButtonModule,
    NbTooltipModule
 
    
  ],
  entryComponents: [
    SmartTableDatepickerComponent,
    SmartTableDatepickerRenderComponent
  ],
  providers: [
    TruckServiceService,
    ExporterService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
