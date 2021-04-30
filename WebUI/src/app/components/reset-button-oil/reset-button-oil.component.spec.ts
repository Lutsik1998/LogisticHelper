import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ResetButtonOilComponent } from './reset-button-oil.component';

describe('ResetButtonOilComponent', () => {
  let component: ResetButtonOilComponent;
  let fixture: ComponentFixture<ResetButtonOilComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ResetButtonOilComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ResetButtonOilComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
