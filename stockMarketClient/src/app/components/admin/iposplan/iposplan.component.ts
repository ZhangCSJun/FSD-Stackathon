import { Component, OnInit } from '@angular/core';
import { IPODetails } from '../../../entity/iposdetails';
import { RequestService } from '../../../services/request.service';
import { ActivatedRoute} from '@angular/router';
import * as $ from 'jquery';

@Component({
  selector: 'app-iposplan',
  templateUrl: './iposplan.component.html',
  styleUrls: ['./iposplan.component.scss']
})
export class IposplanComponent implements OnInit {

  public iposDetail:IPODetails = new IPODetails();

  public pageStatus:string;

  public validMsg:any={
    company:"",
    stkExchange:"",
    price:"",
    totalNum:"",
    openDateTime:"",
  }

  constructor(private routerinfo:ActivatedRoute, private reqServices:RequestService) { }

  ngOnInit(): void {
    this.pageStatus =this.routerinfo.snapshot.params['status'];
    if(this.pageStatus=="1"){
      let objMap = this.routerinfo.snapshot.queryParamMap;
      this.iposDetail.companyName = objMap.get('companyName');
      this.iposDetail.stockExchange = objMap.get('stockExchange');
      this.iposDetail.pricePerShare = objMap.get('pricePerShare');
      this.iposDetail.totalNumberOfShares = objMap.get('totalNumberOfShares');
      this.iposDetail.openDateTime = objMap.get('openDateTime');
      this.iposDetail.remark = objMap.get('remark');
      console.log(this.iposDetail);
    }
  }

  saveIPOSplan(){
    if(this.valid()){
      console.log(this.iposDetail);
      this.reqServices.addIPOsPlan(this.iposDetail).subscribe((response:any)=>{
        alert(`code:${response.code} msg:${response.msg}`);
      });
    }
  }

  valid(){
    let checkResult=true;

    this.validMsg.company = "";
    this.validMsg.stkExchange = "";
    this.validMsg.price = "";
    this.validMsg.totalNum = "";
    this.validMsg.openDateTime = "";

    if(this.isBlank(this.iposDetail.companyName)){
      this.validMsg.company="companyName must to be input";
      checkResult = false;
    }
    if(this.isBlank(this.iposDetail.stockExchange)){
      this.validMsg.stkExchange="stockExchange must to be input";
      checkResult = false;
    }
    if(this.isBlank(this.iposDetail.pricePerShare)){
      this.validMsg.price="pricePerShare must to be input";
      checkResult = false;
    }
    if(this.isBlank(this.iposDetail.totalNumberOfShares)){
      this.validMsg.totalNum="totalNumberOfShares must to be input";
      checkResult = false;
    }
    if(this.isBlank(this.iposDetail.openDateTime)){
      this.validMsg.openDateTime="openDatetime must to be input";
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
