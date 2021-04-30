import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { TruckListComponent } from './modules/truck-list/truck-list.component';
import { TripListComponent } from './modules/trip-list/trip-list.component';


 
const routes: Routes = [
  { path: 'trucks', component: TruckListComponent },
  { path: 'trips', component: TripListComponent },
];
 
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }