import { Component, OnInit } from '@angular/core';
import { RequestService } from '../../../services/request.service';
import { Router } from "@angular/router";
import { StockExchange } from '../../../entity/stockexchange';

@Component({
  selector: 'app-managestockinfo',
  templateUrl: './managestockinfo.component.html',
  styleUrls: ['./managestockinfo.component.scss']
})
export class ManagestockinfoComponent implements OnInit {

  public stockExchangeInfo:Array<StockExchange>;
  constructor(private reqServices:RequestService, private router:Router) { }

  ngOnInit(): void {
    this.reqServices.getStockExchangeInfo().subscribe((response:any)=>{
      this.stockExchangeInfo = response;
      this.stockExchangeInfo.forEach(element => {
        element.checked = false;
      });
    })
  }

  delStockEx(){
    console.log(this.stockExchangeInfo);
    let delStockExList:Array<any>=[];
    this.stockExchangeInfo.forEach(element => {
      if(element.checked == true){
        let stockEx:any = {};
        stockEx.id = element.id;
        delStockExList.push(stockEx);
      }
    });
    console.log(delStockExList);
    alert(JSON.stringify(delStockExList));
    this.reqServices.delStockExchange(delStockExList).subscribe((response:any)=>{
      alert(`code:${response.code} msg:${response.msg}`);
    });
  }


}
