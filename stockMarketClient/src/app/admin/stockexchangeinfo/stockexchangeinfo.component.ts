import { Component, OnInit } from '@angular/core';
import { RequestService } from '../../../services/request.service';
import { StockExchange } from '../../../entity/stockexchange';
import { ActivatedRoute} from '@angular/router';
import * as $ from 'jquery';

@Component({
  selector: 'app-stockexchangeinfo',
  templateUrl: './stockexchangeinfo.component.html',
  styleUrls: ['./stockexchangeinfo.component.scss']
})
export class StockexchangeinfoComponent implements OnInit {

  public stockEx:StockExchange;

  public pageStatus:string;

  public validMsg:any={
    id:"",
    abbr:"",
    fullname:"",
    addr:""
  }

  // public stockEx:any;

  constructor(private routerinfo:ActivatedRoute, private reqServices:RequestService) { }

  ngOnInit(): void {

    this.pageStatus =this.routerinfo.snapshot.params['status'];
    if(this.pageStatus=="2"){
      $("input[type='text']").attr("disabled","true");
      let objMap = this.routerinfo.snapshot.queryParamMap;
      this.stockEx = new StockExchange(
        objMap.get('id'), 
        objMap.get('abbrname'), 
        objMap.get('fullname'), 
        objMap.get('brief'),
        objMap.get('contactAddress'),
        objMap.get('remark'), 
        false);
      console.log(this.stockEx);
    } else {
      this.stockEx = new StockExchange("","","","","","",false);
    }

  }

  saveStockExInfo(){
    if(this.valid()){
      console.log(this.stockEx);
      this.reqServices.addNewStockExchange(this.stockEx).subscribe((response:any)=>{
        alert(`code:${response.code} msg:${response.msg}`);
      });
    }
  }

  valid(){
    let checkResult=true;

    this.validMsg.id = "";
    this.validMsg.abbr = "";
    this.validMsg.fullname = "";
    this.validMsg.addr = "";

    if(this.isBlank(this.stockEx.id)){
      this.validMsg.id="id must to be input";
      checkResult = false;
    }
    if(this.isBlank(this.stockEx.abbrname)){
      this.validMsg.abbr="abbrname must to be input";
      checkResult = false;
    }
    if(this.isBlank(this.stockEx.fullname)){
      this.validMsg.fullname="fullname must to be input";
      checkResult = false;
    }
    if(this.isBlank(this.stockEx.contactAddress)){
      this.validMsg.addr="Contact Address must to be input";
      checkResult = false;
    }
    return checkResult;
  }

  isBlank(data:any):boolean{
    let checkResult = false;
    if(data==undefined||data==""){
      checkResult = true;
    }
    return checkResult;
  }

}
