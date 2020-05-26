import { Component, OnInit, Compiler, NgModuleFactory } from '@angular/core';
import { RequestService } from '../../../services/request.service';
import { Editstatus } from '../../../util/editstatus.enum';
import { Router } from "@angular/router";
import * as $ from 'jquery';


@Component({
  selector: 'app-managecompany',
  templateUrl: './managecompany.component.html',
  styleUrls: ['./managecompany.component.scss']
})
export class ManagecompanyComponent implements OnInit {
  isDisplay:boolean=false;
  public companyInfo:any;

  public companyNameList:any;

  public searchCode:string = "";

  constructor(private reqService:RequestService,  private router:Router ) { }

  ngOnInit(): void {
  }

  getCompanyNameList(){
    /* 
      when 2 or more characters for a company name
      or company code, display matching company names
    */ 
    if($('#company').val().length>=2){
      // let keyword:string = $('#company').val();
      // // get company names by using ajax
      // this.reqService.getCompanyNameByKeyword(keyword).subscribe((response)=>{
      //   this.companyNameList = response;
      // })
      // this.isDisplay = true;


      let keyword:string = $('#company').val();
      // get company names by using ajax
      this.reqService.getCompanyNameByKeyword(keyword).subscribe((response:any)=>{
        console.log(response);
        if(response.body.status == 200 && response.body.code === "001"){
          this.companyNameList = response.body.business.data;
          console.log(this.companyNameList);
          this.isDisplay = true;
        } else {
          this.isDisplay = false;
        }
      }, (error:any)=>{
        console.log(error);
      })
      
    }
  }


  selectCompany(evt:any){
    let attr = evt.target.attributes;
    // clear old code
    this.searchCode = "";
    // save new one code
    this.searchCode = attr.companyCode.value;
    // display company name on input text
    $('#company').val(attr.companyName.value);
    this.isDisplay = false;
  }

  seletItem(evt:any){alert("hi")
    $('#company').val(evt.target.value);
  }

  getCompanyInfo(){
    // when user click search button after select item from list,
    // get companylist by specified company code
    if(this.searchCode!==""){
      $('#company').val('');
      this.reqService.getCompanyInfoByKeyword(this.searchCode).subscribe((response:any)=>{
        console.log(response);
        this.companyInfo = response.body.business.data;
      })
      this.searchCode =""
     // when user input keyword into text, get companylist by key word
    } else {
      let keyword:string =  $('#company').val();
      if(keyword == ""){
        this.reqService.getCompaniesInfo().subscribe((response:any)=>{
          console.log(response);
          this.companyInfo = response.body.business.data;
        })
      } else {
        this.reqService.getCompanyInfoByKeyword(keyword).subscribe((response:any)=>{
          console.log(response);
          this.companyInfo = response.body.business.data;
        })
      }

    }

  }

  // gotoEdit(){
  //   this.router.navigate(['/admin/edit'],{queryParams: {status: Editstatus.edit}});
  // }
}
