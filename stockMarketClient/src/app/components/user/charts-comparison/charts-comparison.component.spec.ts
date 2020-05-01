import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ChartsComparisonComponent } from './charts-comparison.component';

describe('ChartsComparisonComponent', () => {
  let component: ChartsComparisonComponent;
  let fixture: ComponentFixture<ChartsComparisonComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ChartsComparisonComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ChartsComparisonComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  // it('should create', () => {
  //   expect(component).toBeTruthy();
  // });
});
