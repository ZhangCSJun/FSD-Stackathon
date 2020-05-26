import { Component, OnInit } from '@angular/core';
import { RequestService } from '../../../services/request.service';
import { Router, ActivatedRoute } from '@angular/router';
import * as $ from 'jquery';

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.scss']
})
export class ChangePasswordComponent implements OnInit {
  public userId:string;

  // valid message
  public alertMsg:any={
    oldpwd:"",
    newpwd:"",
    confirmpwd:"",
  }

  // bundle form item
  public updatePwd:any={
    oldpwd:"",
    newpwd:"",
    confirmpwd:"",
  }

  // new password rule tip info
  public pwdtip:string="Make sure it's at least 15 character OR at least 8 character" +
  " including a number and a lowercase letter";

  constructor(private reqService:RequestService, private router:Router, private routerinfo:ActivatedRoute ) { }

  ngOnInit(): void {
    this.routerinfo.queryParams.subscribe(queryParam=>{
      this.userId = queryParam.userId;
      console.log(this.userId);
    });
  }

  onSubmit(value:any){

    this.alertMsg.oldpwd = "";
    this.alertMsg.successInfo = "";

    let updatePwdInfo = {
      id: this.userId,
      oldPwd: value.oldpwd,
      newPwd: value.newpwd
    }
    // dummy
    console.log(JSON.stringify(updatePwdInfo));
    this.reqService.doUpdatePwd(updatePwdInfo).subscribe((response:any)=>{
      console.log(response);
      if(200 === response.body.status && "001" === response.body.code){
        $("#alertOldpwd").removeClass("alert-danger").addClass("alert-success");
        this.alertMsg.oldpwd = "update password success";
        $('#successInfo').show();
        setTimeout(()=>{
          this.router.navigate(['user/profile'],{queryParams: {userId: this.userId}});
        },2000);
        
      } else {
        this.alertMsg.oldpwd = "old password error";
        $('#alertOldpwd').show();
      }
    }, (error:any)=>{
      console.log(error);
    })
  }

  validUpdatePwd(e:Event){
    let srEle:any = e.currentTarget;

    if(srEle.id == "newpwd" && this.updatePwd.newpwd.length>0){
      console.log("in");
      let pwdReg:any =/[0-9a-zA-Z]{15,}/;
      let pwdReg2:any =/[0-9a-zA-Z]{8,14}/;
      let pwdReg3:any = /[0-9]/;
      let pwdReg4:any = /[a-z]/;
      let regResult = false;

      if(pwdReg.test(this.updatePwd.newpwd)){
        regResult = true;
      } else if(pwdReg2.test(this.updatePwd.newpwd) &&
                pwdReg3.test(this.updatePwd.newpwd) &&
                pwdReg4.test(this.updatePwd.newpwd)){
         regResult = true;
      }

      if(!regResult){
        this.alertMsg.newpwd = "new password is incorrect";
        $('#alertNewpwd').show();
      } else {
        this.alertMsg.newpwd = "";
        $('#alertNewpwd').hide();
      }
    }else if(srEle.id == "confirmpwd"){
      if(this.updatePwd.confirmpwd.length>0 && this.updatePwd.newpwd !== this.updatePwd.confirmpwd){
        this.alertMsg.confirmpwd = "password input inconsistent";
        $('#alertConfirmpwd').show();
      } else {
        this.alertMsg.confirmpwd = "";
        $('#alertConfirmpwd').hide();
      }
    }

    // when valid passed, enable submit button 
    if(this.updatePwd.oldpwd !== "" &&
      (this.alertMsg.newpwd === this.alertMsg.confirmpwd) && 
      (this.updatePwd.newpwd === this.updatePwd.confirmpwd)){
      $('#submit').attr('disabled', false);
    } else {
      $('#submit').attr('disabled', true);
    }
  }
  

}
