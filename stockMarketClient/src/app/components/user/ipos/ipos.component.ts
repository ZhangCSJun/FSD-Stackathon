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
      this.reqService.getCompanyNameByKeyword(keyword).subscribe((response:any)=>{
        console.log(response);
        if(response.body.status == 200 && response.body.code === "001"){
          this.companyNameList = response.body.business.data;
          console.log(this.companyNameList);
        }
      }, (error:any)=>{
        console.log(error);
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
      let keyword:string =  $('#company').val();
      console.log(keyword);
      this.reqService.getIposDataByKeyword(keyword).subscribe((response:any)=>{
        console.log(response);
        this.iposData = response.body.business.data;
      }, (error:any)=>{
        console.log(error);
      })
  }

}
