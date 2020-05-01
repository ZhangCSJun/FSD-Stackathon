import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EditocomponayinfoComponent } from './editocomponayinfo.component';

describe('EditocomponayinfoComponent', () => {
  let component: EditocomponayinfoComponent;
  let fixture: ComponentFixture<EditocomponayinfoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EditocomponayinfoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EditocomponayinfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  // it('should create', () => {
  //   expect(component).toBeTruthy();
  // });
});
