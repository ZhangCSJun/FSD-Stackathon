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
    if(this.validInput(formValue)){
      this.reqService.doSignIn(formValue).subscribe((response:any)=>{
        if(response.code == 200){
          sessionStorage.setItem("token","OK");
          if(response.role=="1"){
            this.router.navigate(['admin/uploadxls']);
          } else{
            this.router.navigate(['user/profile']);
          }
        } else {
          console.log(response.code);
          this.validMsg.loginFail = "Incorrect username or password.";
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
    if(!formValue.pwd){
      this.validMsg.password = "password can't be empty!"
      checkResult = false;
    };
    return checkResult;
  }

}
