import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ManagestockinfoComponent } from './managestockinfo.component';

describe('ManagestockinfoComponent', () => {
  let component: ManagestockinfoComponent;
  let fixture: ComponentFixture<ManagestockinfoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ManagestockinfoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ManagestockinfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  // it('should create', () => {
  //   expect(component).toBeTruthy();
  // });
});
