import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { event } from 'jquery';
import { ViewCell } from 'ng2-smart-table';
import { Subject } from 'rxjs';
import { Truck } from 'src/app/models/truck';
import { TruckServiceService } from 'src/app/services/truck-service.service';

@Component({
  selector: 'app-reset-button-wheels',
  templateUrl: './reset-button-wheels.component.html',
  styleUrls: ['./reset-button-wheels.component.css']
})
export class ResetButtonWheelsComponent implements ViewCell, OnInit {
  renderValue: string;
  

  @Input() value: string | number;
  @Input() rowData: any;

  @Output() save: EventEmitter<any> = new EventEmitter();

  ngOnInit() {
    this.renderValue = this.value.toString().toUpperCase();
    
  }

  onClick(event) {
    //alert(`${this.rowData} clicked!`)
    //this.save.emit(this.rowData);
    // const presentTruck = this.rowData;
    // this.truckService.resetWheelsCouter(this.rowData.id, this.rowData);
    if (window.confirm('Are you sure you want to reset wheels counter?')){
      const presentTruck = this.rowData;
      this.truckService.resetWheelsCouter(presentTruck.id,presentTruck).subscribe(
        trucks => {
         // event.confirm.reject();
          console.log(trucks);
          window.location.reload();
        }
      );
    }else {
      //event.confirm.reject();
    }
  }
  constructor(private truckService: TruckServiceService,
    private router: Router) { }
  

  
}

