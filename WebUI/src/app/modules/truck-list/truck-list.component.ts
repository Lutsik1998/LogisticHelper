import { Component, Input, OnInit, ViewChild } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { Truck } from '../../models/truck';
import { TruckServiceService } from '../../services/truck-service.service';
import { LocalDataSource, ViewCell } from 'ng2-smart-table';
import { HttpErrorResponse } from '@angular/common/http';
import { ResetButtonWheelsComponent } from 'src/app/components/reset-button-wheels/reset-button-wheels.component';
import { ResetButtonOilComponent } from 'src/app/components/reset-button-oil/reset-button-oil.component';


@Component({
  selector: 'app-truck-list',
  templateUrl: './truck-list.component.html',
  styleUrls: ['./truck-list.component.css'],
  template: `
    <ng2-smart-table
      [settings]="settings"
      [source]="trucks"
      (deleteConfirm)="onDeleteConfirm($event)"
      (saveConfirm)="onSaveConfirm($event)"
      (createConfirm)="onCreateConfirm($event)"
      (custom)="onCustom($event)"
     
      ></ng2-smart-table>
  `,
})
export class TruckListComponent implements OnInit {

  truck: Observable<Truck>;
  trucks: Truck[];
  newTruck: Truck = new Truck();
  //dtOptions: DataTables.Settings = {};
  //@ViewChild('dtOptions', {static: true}) table;
  title: string;
 
  settings = {
     rowClassFunction: (row) =>{
        if(row.data.counterOil > 35000 || row.data.counterWheels > 30000){
          return 'warning';
        }else {
          return 'good'
        }},
    pager: {
      display: true,
      perPage: 40
    },
    
    delete: {
      confirmDelete: true,
      deleteButtonContent: '<i class="nb-trash"></i>'
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
        editable: false
      },
      model: {
        title: 'Model'
      },
      truckNumber: {
        title: 'Truck number',
        
      },
      carMileage: {
        title: 'Truck mileage',
        type : 'number'
      },
      counterOil: {
        title: 'Oil check',
        type : 'number',
        width: '150px',
        
      },
     
      resetCounterOil:{
        title: "",
        filter: false,
        type: 'custom',
        renderComponent: ResetButtonOilComponent, 
        editable: false,
        addable: false
      },
      counterWheels: {
        title: 'Wheel check',
        type : 'number',
        width: '150px',
      },
      resetCounterWh:{
        title: "",
        filter: false,
        type: 'custom',
        width: '130px',
        renderComponent: ResetButtonWheelsComponent, 
        editable: false,
        addable: false
      },
    }
  };

  constructor(private route: ActivatedRoute, private truckService: TruckServiceService,
    private router: Router) {
      this.title="Trucks"
  }

  ngOnInit() {
    this.truckService.findAll().subscribe(data => {
      this.trucks = data;
    });
  }

  onDeleteConfirm(event) {
    if (window.confirm('Are you sure you want to delete?')){
      const deletedTruck = event.data;
      this.truckService.deleteTruck(deletedTruck.id).subscribe(
        trucks => {
          console.log(trucks);
          event.confirm.resolve();
          //window.location.reload();
        }
      );
    }else {
      event.confirm.reject();
    }
  }
  

  onSaveConfirm(event) {
    const updatedTruck = event.data;
    var data = {
      "id": event.newData.id,
      "model": event.newData.model,
      "truckNumber": event.newData.truckNumber,
      "carMileage": parseFloat(event.newData.carMileage),
      "counterOil": parseFloat(event.newData.counterOil),
      "counterWheels": parseFloat(event.newData.counterWheels)
    };
    if (window.confirm('Are you sure you want to save update?')){
      this.truckService.updateTruck(updatedTruck.id, data).subscribe(
        res => {
          console.log(res);
          event.confirm.resolve();
          window.location.reload();
        },
        (err: HttpErrorResponse) => {
          if (err.error instanceof Error) {
            console.log("Client-side error occured.");
          } else { 
            console.log(`Backend returned code ${err.status}, body was: ${err.error.message}`);
            alert(`${event.newData.truckNumber} Wrong number! This car is already exist`)
          
          
          }
        });
    }else{
      event.confirm.reject();
    }
    
  }

  onCreateConfirm(event) {
     this.newTruck = {
      "id": event.newData.id,
      "model": event.newData.model,
      "truckNumber": event.newData.truckNumber,
      "carMileage": event.newData.carMileage,
      "counterOil": event.newData.counterOil,
      "counterWheels": event.newData.counterWheels
    };
    if (window.confirm('Are you sure you want to create?')){
      this.truckService.save(this.newTruck).subscribe(
        res => {
          console.log(res);
          event.confirm.resolve(event.newData);
          window.location.reload();
        },
        (err: HttpErrorResponse) => {
          if (err.error instanceof Error) {
            console.log("Client-side error occured.");
          } else {
            console.log(`Backend returned code ${err.status}, body was: ${err.error.message}`);
            alert(` Wrong number! Truck with number "${event.newData.truckNumber}" is already exist`)

          }
        });
    }else{
      event.confirm.reject();
    }
    
  }

}
