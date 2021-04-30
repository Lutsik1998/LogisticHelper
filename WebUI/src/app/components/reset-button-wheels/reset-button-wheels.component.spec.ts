import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ResetButtonWheelsComponent } from './reset-button-wheels.component';

describe('ResetButtonWheelsComponent', () => {
  let component: ResetButtonWheelsComponent;
  let fixture: ComponentFixture<ResetButtonWheelsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ResetButtonWheelsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ResetButtonWheelsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
