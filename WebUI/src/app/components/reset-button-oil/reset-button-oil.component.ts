import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import { TruckServiceService } from 'src/app/services/truck-service.service';

@Component({
  selector: 'app-reset-button-oil',
  templateUrl: './reset-button-oil.component.html',
  styleUrls: ['./reset-button-oil.component.css']
})
export class ResetButtonOilComponent implements OnInit {

  renderValue: string;
  

  @Input() value: string | number;
  @Input() rowData: any;

  @Output() save: EventEmitter<any> = new EventEmitter();

  ngOnInit() {
    this.renderValue = this.value.toString().toUpperCase();
    
  }

  onClick(event) {
    
    if (window.confirm('Are you sure you want to reset oil counter?')){
      const presentTruck = this.rowData;
      this.truckService.resetOilCouter(presentTruck.id,presentTruck).subscribe(
        trucks => {
          console.log(trucks);
          window.location.reload();
        }
      );
    }else {
      event.confirm.reject();
    }
  }
  constructor(private truckService: TruckServiceService,
    private router: Router) { }

}
