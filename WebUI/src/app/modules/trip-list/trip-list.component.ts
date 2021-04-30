import { Component, OnInit } from '@angular/core';
import { Trip } from 'src/app/models/trip';
import { Observable } from 'rxjs';
import { TruckServiceService } from 'src/app/services/truck-service.service';
import { Router } from '@angular/router';
import { TripServiceService } from 'src/app/services/trip-service.service';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { SmartTableDatepickerComponent, SmartTableDatepickerRenderComponent } from 'src/app/components/smart-table-datepicker/smart-table-datepicker.component';
import { LocalDataSource } from 'ng2-smart-table';
import { Truck } from 'src/app/models/truck';
import {ExporterService} from 'src/app/services/exporter.service';
import { DataSource } from 'ng2-smart-table/lib/lib/data-source/data-source';


@Component({
  selector: 'app-trip-list',
  templateUrl: './trip-list.component.html',
  styleUrls: ['./trip-list.component.css'],
  template: `
    <ng2-smart-table
      [settings]="settings"
      [source]="source"
      (deleteConfirm)="onDeleteConfirm($event)"
      (editConfirm)="onSaveConfirm($event)"
      (createConfirm)="onCreateConfirm($event)"></ng2-smart-table>
  `,
})
export class TripListComponent implements OnInit {

  title: string;
  trip: Observable<Trip>;
  trips: Trip[];
  newTrip: Trip = new Trip();
  trucks: Truck[];
  source: LocalDataSource;
  truckNumbers: any[];

  
  totalNetProfit = 0;
  
    

  
  
  //dtOptions: DataTables.Settings = {};
  //@ViewChild('dtOptions', {static: true}) table;
  constructor(
    private tripService: TripServiceService, 
    private truckService: TruckServiceService, 
    private exceltService: ExporterService,
    private router: Router
    ) { 
      this.tripService.findAll().subscribe(data => {
        this.trips = data;
        this.source = new LocalDataSource(this.trips);
      });
      this.truckService.findAll().subscribe(data => {
        this.trucks = data;
        
      });
      
      this.title = "Trips";
      
    }
    ngOnInit() {
     
    }
  settings = {
    pager: {
      display: true,
      perPage: 100
    },
    //selectMode: "multi",
    delete: {
      confirmDelete: true,
       deleteButtonContent: '<i class="nb-trash"></i>',
      
       
      
    },
    add: {
      confirmCreate: true,
       addButtonContent: '<i class="nb-plus"></i>',
       createButtonContent: '<i class="nb-checkmark"></i>',
       cancelButtonContent: '<i class="nb-close"></i>',
    },
    edit: {
      confirmSave: true,
       editButtonContent: '<i class="nb-edit"></i>',
       saveButtonContent: '<i class="nb-checkmark"></i>',
       cancelButtonContent: '<i class="nb-close"></i>',
    },
    columns: {
      id: {
        title: 'ID',
        width: '75px',
        editable: false,
        addable: false,
      },
      customer: {
        title: 'Customer'
      },
      dateOfLoading: {
        title: 'Date of loading',
        type: 'date',
        renderComponent: SmartTableDatepickerRenderComponent,
        component: SmartTableDatepickerComponent,
        editor: {
          type: 'custom',
          component: SmartTableDatepickerComponent,
        }
      },
      dateOfUnLoading: {
        title: 'Date of unloading',
        type: 'date',
        renderComponent: SmartTableDatepickerRenderComponent,
        component: SmartTableDatepickerComponent,
        editor: {
          type: 'custom',
          component: SmartTableDatepickerComponent,
        }
        
      },
      startPoint: {
        title: 'Start point'
      },
      destinationPoint: {
        title: 'Destination point'
      },
      distance: {
        title: 'Distance (km)'
      },
      price: {
        title: 'Price (grn)'
      },
      priceWithoutVAT: {
        title: 'Price Without VAT (grn)',
        editable: false,
        addable: false,
      },
      vat: {
        title: 'VAT',
        width: '100px',
        editable: false,
        addable: false
      },
      dfConsumed: {
        title: 'Diesel fuel consumed'
      },
      driversSalary: {
        title: 'Drivers salary',
        editable: false,
        addable: false,
      },
      truckNumber: {
        title: 'Truck number',
      },
      pricePerKm: {
        title: 'Price Per Km (grn)',
        editable: false,
        addable: false
      },
      expenses: {
        title: 'Expenses (grn)',
        editable: false,
        addable: false
      },
      netProfit: {
        title: 'netProfit (grn)',
        editable: false,
        addable: false
      },
      dieselPrice: {
        title: 'Diesel Price (grn)',
      }
    
      
    }
  };
  
  
  
    

   

    async exportAsXLSX(): Promise<void>{
      this.exceltService.exportToExcel(await this.source.getAll(), 'my_export');
    }

    async exportAsXLSXFiltered(): Promise<void>{  
      this.exceltService.exportToExcel(await this.source.getElements(), 'my_export');
    }

    onDeleteConfirm(event) {
      if (window.confirm('Are you sure you want to delete?')){
        const deletedTrip = event.data;
        this.tripService.deleteTrip(deletedTrip.id).subscribe(
          trips => {
            console.log(trips);
            event.confirm.resolve();
            window.location.reload();
            
          }
        );
      }else {
        event.confirm.reject();
      }
      
    }
  
    onSearch(query: string = '') {
      this.source.setFilter([
        
        {
          field: 'truckNumber',
          search: query
        },
        
      ], false);
    }
  
    onSaveConfirm(event) {
      const updatedTrip = event.data;
      var data = {
        "id": event.newData.id,
        "customer": event.newData.customer,
        "dateOfLoading": event.newData.dateOfLoading,
        "dateOfUnLoading": event.newData.dateOfUnLoading,
        "startPoint": event.newData.startPoint,
        "destinationPoint": event.newData.destinationPoint,
        "distance": event.newData.distance,
        "price": event.newData.price,
        "priceWithoutVAT": event.newData.priceWithoutVAT,
        "vat": event.newData.vat,
        "dfConsumed": event.newData.dfConsumed,
        "driversSalary": event.newData.driversSalary,
        "truckNumber": event.newData.truckNumber,
        "pricePerKm": event.newData.pricePerKm,
        "expenses": event.newData.expenses,
        "netProfit": event.newData.netProfit,
        "dieselPrice": event.newData.dieselPrice
      };
      if (window.confirm('Are you sure you want to save update?')){
        this.tripService.updateTrip(updatedTrip.id, data).subscribe(
          res => {
            console.log(res);
            event.confirm.resolve();
            window.location.reload();
          },
          );  
      }else{
        event.confirm.reject();
      }
      
    }
  
    onCreateConfirm(event) {
       this.newTrip = {
        "id": event.newData.id,
        "customer": event.newData.customer,
        "dateOfLoading": event.newData.dateOfLoading,
        "dateOfUnLoading": event.newData.dateOfUnLoading,
        "startPoint": event.newData.startPoint,
        "destinationPoint": event.newData.destinationPoint,
        "distance": event.newData.distance,
        "price": event.newData.price,
        "priceWithoutVAT": event.newData.priceWithoutVAT,
        "vat": event.newData.vat,
        "dfConsumed": event.newData.dfConsumed,
        "truckNumber": event.newData.truckNumber,
        "dieselPrice": event.newData.dieselPrice,
        "driversSalary": event.newData.driversSalary,
        "expenses": event.newData.expenses,
        "netProfit": event.newData.netProfit,
        "pricePerKm": event.newData.pricePerKm
      };
      
      if (window.confirm('Are you sure you want to create?')){
        this.tripService.save(this.newTrip).subscribe(
          res => {
            console.log(res);
            event.confirm.resolve(event.newData);
            window.location.reload();
          },
          (err: HttpErrorResponse) => {
            if (err.error instanceof Error) {
              // A client-side or network error occurred. Handle it accordingly.
              console.log('An error occurred:', err.error.message);
            } else {
              // The backend returned an unsuccessful response code.
              // The response body may contain clues as to what went wrong,
              console.log(`Backend returned code ${err.status}, body was: ${err.error.message}`);
              alert(`${event.newData.truckNumber} Wrong number! This car dont exist`)
            }
          }
        
          );
      }else{
        event.confirm.reject();
      }
      
    }


  // deleteTrip(id: number) {
  //   this.tripService.deleteTrip(id)
  //     .subscribe(
  //       data => {
  //         console.log(data);
  //         this.trip = this.tripService.getTrips();
  //       },
  //       error => console.log(error));
  // }

  // updateTrip(id: number){
  //   this.router.navigate(['/updateTrip/', id]);
  // }

}
