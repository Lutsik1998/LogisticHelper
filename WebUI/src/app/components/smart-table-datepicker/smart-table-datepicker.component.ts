import { Component, OnInit, Input } from '@angular/core';
import { DefaultEditor, ViewCell } from 'ng2-smart-table';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-smart-table-datepicker',
  templateUrl: './smart-table-datepicker.component.html',
  styleUrls: ['./smart-table-datepicker.component.css']
})
export class SmartTableDatepickerComponent extends DefaultEditor implements OnInit {

  @Input() placeholder: string = 'Date';

  @Input() min = new Date(2010, 1, 12); 

  @Input() max = new Date(2050, 1, 12); // Defaults to 1 month after the min

  @Input() currentDate = new Date(); // Defaults to now(rounded down to the nearest 15 minute mark)

  stringValue;
  inputModel: Date;

  constructor() {
    super();
  }

  ngOnInit() {
    

    if(!this.min) {
      this.min = new Date();
      this.min.setMinutes(Math.floor(this.min.getMinutes() / 15) * 15 );
    }

    if(!this.max) {
      this.max = new Date(this.min);
      this.max.setFullYear(this.min.getFullYear() + 1);
    }

    if(this.cell.newValue) {
      let cellValue = new Date(this.cell.newValue);
      if(cellValue.getTime() >= this.min.getTime() && cellValue.getTime() <= this.max.getTime()) {
        this.inputModel = cellValue;
        this.cell.newValue = this.inputModel.toLocaleDateString();
      }
    }

    if(!this.inputModel) {
      
      this.inputModel = this.currentDate;
      this.cell.newValue = this.inputModel.toLocaleDateString();
    }
  }

  onChange() {
    if(this.inputModel) {
      this.cell.newValue = this.inputModel.toLocaleDateString();
  
  
    }
  }
}

@Component({
  template: `{{value | date}} `,
})
export class SmartTableDatepickerRenderComponent implements ViewCell, OnInit {
  @Input() value: string;
  @Input() rowData: any;

  constructor() { }

  ngOnInit() { }

}
