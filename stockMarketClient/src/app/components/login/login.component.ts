import { Component, OnInit } from '@angular/core';
import { RequestService } from '../../services/request.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  public username:string;

  public password:string;

  public validMsg:any={
    loginFail:"",
    username:"",
    password:"",
  }
  constructor(private reqService:RequestService, private router:Router) { }

  ngOnInit(): void {
    
  }

  login(formValue:any){
    console.log(JSON.stringify(formValue));
    // if form value check passed
    if(this.validInput(formValue)){
      this.reqService.doSignIn(formValue).subscribe((response:any)=>{
        // if response status is 200(ok)
        if(response.body.status == 200){
          // if login successful
          if(response.body.code == "001"){
            // save token
            console.log(response.headers.get('tkn'));
            localStorage.setItem("token",response.headers.get('tkn'));
            // go to home page by role
            if(response.body.business.role=="1"){
              this.router.navigate(['admin/uploadxls']);
            } else { 
              this.router.navigate(['user/profile'],{queryParams: {userId: response.body.business.id}});
            }
          } else {
            this.validMsg.loginFail = "Incorrect username or password.";
          }
        } 
      }, (errorResponse:any)=>{
        // if response status is not 200(ok)
        console.log(errorResponse);
        if(errorResponse.status == 400){
          this.validMsg.loginFail = errorResponse.error.message;
        }
      })
    }
  }

  public validInput(formValue:any):boolean{
    this.validMsg.loginFail = "";
    this.validMsg.username = "";
    this.validMsg.password = "";

    let checkResult:boolean=true;
    if(!formValue.username){
      this.validMsg.username = "username can't be empty!"
      checkResult = false;
    };
    if(!formValue.password){
      this.validMsg.password = "password can't be empty!"
      checkResult = false;
    };
    return checkResult;
  }

}
