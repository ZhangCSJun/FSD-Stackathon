import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { StockexchangeinfoComponent } from './stockexchangeinfo.component';

describe('StockexchangeinfoComponent', () => {
  let component: StockexchangeinfoComponent;
  let fixture: ComponentFixture<StockexchangeinfoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ StockexchangeinfoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StockexchangeinfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  // it('should create', () => {
  //   expect(component).toBeTruthy();
  // });
});
