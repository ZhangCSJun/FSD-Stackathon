import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateIposdetailComponent } from './update-iposdetail.component';

describe('UpdateIposdetailComponent', () => {
  let component: UpdateIposdetailComponent;
  let fixture: ComponentFixture<UpdateIposdetailComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UpdateIposdetailComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UpdateIposdetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  // it('should create', () => {
  //   expect(component).toBeTruthy();
  // });
});
