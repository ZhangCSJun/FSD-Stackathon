import { Component, OnInit } from '@angular/core';
import { RequestService } from '../../../services/request.service';
import * as $ from 'jquery';

@Component({
  selector: 'app-ipos',
  templateUrl: './ipos.component.html',
  styleUrls: ['./ipos.component.scss']
})
export class IposComponent implements OnInit {
  isDisplay:boolean=false;

  public companyNameList:any;

  public dropDownItem:string = "";

  public searchCode = "";

  public iposData:any;

  constructor(private reqService:RequestService) { }

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

  seletItem(evt:any){
    $('#company').val(evt.target.value);
  }

  getIposData(){
    // when user click search button after select item from list,
    // get companylist by specified company code
    if(this.searchCode!==""){
      $('#company').val('');
      this.reqService.getIposDataByCode(this.searchCode).subscribe((response:any)=>{
        console.log(response);
        this.iposData = response
      })
      this.searchCode =""
     // when user input keyword into text, get companylist by key word
    } else {
      let keyword:string =  $('#company').val();
      this.reqService.getIposDataByKeyword(keyword).subscribe((response:any)=>{
        console.log(response);
        this.iposData = response
      })
    }

  }

}
