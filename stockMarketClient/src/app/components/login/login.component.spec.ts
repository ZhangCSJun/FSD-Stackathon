import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpClientTestingModule  } from '@angular/common/http/testing';
import { RouterTestingModule  } from '@angular/router/testing';
import { FormsModule } from '@angular/forms';

import { LoginComponent } from './login.component';

describe('LoginComponent', () => {
  let component: LoginComponent;
  let fixture: ComponentFixture<LoginComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LoginComponent ],
      imports: [HttpClientTestingModule,RouterTestingModule, FormsModule ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LoginComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('if username or password uninput, show alert message', () => {
    let value:any={
      username:null,
      pwd:null
    }

    component.validInput(value);
    expect(component.validMsg.username).toBe("username can't be empty!");
    expect(component.validMsg.password).toBe("password can't be empty!");
  });

  it('if password uninput, show alert message', () => {
    let value:any={
      username:'abc',
      pwd:null
    }

    component.validInput(value);
    expect(component.validMsg.username).toBe("");
    expect(component.validMsg.password).toBe("password can't be empty!");
  });

  it('if username uninput, show alert message', () => {
    let value:any={
      username:null,
      pwd:'12345'
    }

    component.validInput(value);
    expect(component.validMsg.username).toBe("username can't be empty!");
    expect(component.validMsg.password).toBe("");
  });

  it('if username and password were inputted, no alert message', () => {
    let value:any={
      username:'abc',
      pwd:'12345'
    }

    component.validInput(value);
    expect(component.validMsg.username).toBe("");
    expect(component.validMsg.password).toBe("");
  });
});
