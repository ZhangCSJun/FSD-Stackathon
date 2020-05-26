import { Component, OnInit } from '@angular/core';
import { RequestService } from '../../../services/request.service';
import { Router } from '@angular/router';
import * as $ from 'jquery';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.scss']
})
export class SignupComponent implements OnInit {

    // valid message
    public alertMsg:any={
      username:"",
      mobilenumber:"",
      email:"",
      password:""
    }

    // new password rule tip info
    public pwdtip:string="Make sure it's at least 15 character OR at least 8 character" +
    " including a number and a lowercase letter";

    public signup:any={
      userName:"",
      mobileNumber:"",
      email:"",
      passWord:""
    }
  constructor(private reqService:RequestService, private router:Router) { }

  ngOnInit(): void {

  }

  onSubmit(value:any){
      // dummy
      console.log(JSON.stringify(value));
      this.reqService.doSignUp(value).subscribe((response:any)=>{
        if(200 === response.body.status && "001" === response.body.code){
          $("#fail").removeClass("alert-danger").addClass("alert-success");
          this.alertMsg.username = "signup success";
          $('#fail').show();
          setTimeout(()=>{
            this.router.navigate(['/login']);
          },2000);
          
        } else {
          this.alertMsg.username = "input error please check";
          $('#fail').show();
        }
      })
  }
  validForm(evt:any){
    let srEle:any = evt.currentTarget;

    if(srEle.id == "pwd"){
      console.log("in");
      let pwdReg:any =/[0-9a-zA-Z]{15,}/;
      let pwdReg2:any =/[0-9a-zA-Z]{8,14}/;
      let pwdReg3:any = /[0-9]/;
      let pwdReg4:any = /[a-z]/;
      let regResult = false;

      if(pwdReg.test(this.signup.passWord)){
        regResult = true;
      } else if(pwdReg2.test(this.signup.passWord) &&
                pwdReg3.test(this.signup.passWord) &&
                pwdReg4.test(this.signup.passWord)){
         regResult = true;
      }

      if(!regResult){
        this.alertMsg.pwd = "new password is incorrect";
        $('#alertpwd').show();
      } else {
        this.alertMsg.pwd = "";
        $('#alertpwd').hide();
      }
    }
    if(srEle.id == "username"){
      if(this.signup.userName ==""){
        this.alertMsg.username = "username must be input";
        $('#fail').show();
      } else {
        this.alertMsg.username = "";
        $('#fail').hide();
      }
    }

    if(srEle.id == "mobilenumber"){
      if(this.signup.mobileNumber==""){
        this.alertMsg.mobilenumber = "mobilenumber must be input";
        $('#alertMobile').show();
      } else {
        this.alertMsg.mobilenumber = "";
        $('#alertMobile').hide();
      }
    }

    if(srEle.id == "email"){
      if(this.signup.email==""){
        this.alertMsg.email = "email must be input";
        $('#alertEmail').show();
      } else {
        this.alertMsg.email = "";
        $('#alertEmail').hide();
      }
    }


    // when valid passed, enable submit button 
    if(this.alertMsg.username == "" &&
        this.alertMsg.mobilenumber == "" &&
        this.alertMsg.email == "" &&
        this.alertMsg.password == ""){
      $('#submit').attr('disabled', false);
    } else {
      $('#submit').attr('disabled', true);
    }
  }
}
