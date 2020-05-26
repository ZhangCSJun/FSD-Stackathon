import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { StockinfodetailComponent } from './stockinfodetail.component';

describe('StockinfodetailComponent', () => {
  let component: StockinfodetailComponent;
  let fixture: ComponentFixture<StockinfodetailComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ StockinfodetailComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StockinfodetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  // it('should create', () => {
  //   expect(component).toBeTruthy();
  // });
});
