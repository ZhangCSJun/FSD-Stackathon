import { Component, OnInit } from '@angular/core';
import { RequestService } from '../../../services/request.service';
import { Editstatus } from '../../../util/editstatus.enum';
import { Router } from "@angular/router";
import * as $ from 'jquery';

@Component({
  selector: 'app-update-iposdetail',
  templateUrl: './update-iposdetail.component.html',
  styleUrls: ['./update-iposdetail.component.scss']
})
export class UpdateIposdetailComponent implements OnInit {
  isDisplay:boolean=false;
  public IPOsPlanned:any;

  public companyNameList:any;

  public dropDownItem:string = "";

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
      let keyword:string = $('#company').val();
      // get company names by using ajax
      this.reqService.getCompanyNameByKeyword(keyword).subscribe((response)=>{
        this.companyNameList = response;
      })
      this.isDisplay = true;
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

  getIPOsPlanned(){
    // when user click search button after select item from list,
    // get companylist by specified company code
    if(this.searchCode!==""){
      $('#company').val('');
      this.reqService.getIposDataByCode(this.searchCode).subscribe((response:any)=>{
        console.log(response);
        this.IPOsPlanned = response
      })
      this.searchCode =""
     // when user input keyword into text, get companylist by key word
    } else {
      let keyword:string =  $('#company').val();
      this.reqService.getIposDataByKeyword(keyword).subscribe((response:any)=>{
        console.log(response);
        this.IPOsPlanned = response
      })
    }

  }

  gotoEdit(){
    this.router.navigate(['/admin/iposplan'],{queryParams: {status: Editstatus.edit}});
  }
}
