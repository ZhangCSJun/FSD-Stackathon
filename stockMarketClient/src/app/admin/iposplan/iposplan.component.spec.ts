import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { IposplanComponent } from './iposplan.component';

describe('IposplanComponent', () => {
  let component: IposplanComponent;
  let fixture: ComponentFixture<IposplanComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ IposplanComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(IposplanComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  // it('should create', () => {
  //   expect(component).toBeTruthy();
  // });

  // it('test'),()=>{
  //   this.iposDetail.totalNumberOfShares = "";
  //   expect(this.valid).toBeFalse();
  // }
});
