import { Component, OnInit } from '@angular/core';
import { RequestService } from '../../../services/request.service';
import { FormsModule } from '@angular/forms';
import { StockPrice } from '../../../entity/stockprice';
import * as XLSX from 'xlsx';
import * as $ from 'jquery';

@Component({
  selector: 'app-importdata',
  templateUrl: './importdata.component.html',
  styleUrls: ['./importdata.component.scss']
})
export class ImportdataComponent implements OnInit {

  // file upload target
  private uploadTarget:any;
  // excel data
  excelData:Array<any>;
  // transfer data
  public transferData:Array<StockPrice>=[];
  // excel data checkResult msg
  public xlsCheckResultMsg:Array<any>=[];

  public uploadResult:any={
    company:"",
    stockExchange:"",
    num:"",
    datefrom:"",
    dateto:""
  }

  constructor(private reqService:RequestService) { }

  ngOnInit(): void {
  }

  doSelFile(evt:any){
    $('#location').val(evt.target.value);
    this.uploadTarget = evt.target;
  }

  importExcel(){
    const target:DataTransfer = <DataTransfer>(this.uploadTarget);
    const reader:FileReader = new FileReader();

    reader.onload=(e:any)=>{
      const content:string = e.target.result;
      const wb:XLSX.WorkBook = XLSX.read(content, {type:'binary'})

      const wsname: string = wb.SheetNames[0];
      const ws:XLSX.WorkSheet = wb.Sheets[wsname];

      this.excelData = (XLSX.utils.sheet_to_json(ws, {header:1}));

      // excel Data no problem
      if(this.checkExcelData(this.excelData)){

        this.excelData.forEach(element=>{
          let data:StockPrice = new StockPrice(
            element[0],// companycode
            element[1],// stockcode
            element[2],// stockexchange
            element[3],// current price
            element[4],// currency
            element[5],// data
            element[6])// time

          this.transferData.push(data);
        })
        
        alert(JSON.stringify(this.transferData));
        this.reqService.uploadCompayStockPriceData(this.transferData).subscribe((response:any)=>{
          if(response.code == "200"){
            alert(response.msg);
          }
        })
      } else {
        this.xlsCheckResultMsg.forEach(el=>{
          alert(el);
        })
      }

      this.uploadTarget.value="";
    }

    reader.readAsBinaryString(target.files[0]);
  }


  checkExcelData(xlsData:Array<any>){
    let dataCheckResult = true;
    let companyCode="";
    let stockExchange = "";
    let dateFrom="";
    let dateTo="";
    const columnHeader = "Company Code,Stock Code,Stock Exchange,Price Per Share,Currency,Date,Time";
    // remove head line
    let rowHeader:Array<any> = xlsData.shift();
    if(rowHeader.length != 7){
      dataCheckResult = false;
      this.xlsCheckResultMsg.push("excel format exception: columns' num is less than 7");
    }
    for(let i=0;i<rowHeader.length;i++){
      console.log(rowHeader[i]);
      if(columnHeader.indexOf(rowHeader[i])<0){
        dataCheckResult = false;
        this.xlsCheckResultMsg.push(`excel format exception: column ${rowHeader[i]} is undefined`);
      }
    }

    for(let i=0; i<xlsData.length; i++){
      // get Row Data
      let rowData:Array<any>=xlsData[i];
      // check Col Data
      if(companyCode =="" ){
        companyCode = rowData[0]
      }else if(companyCode != rowData[0]){
        dataCheckResult = false;
        this.xlsCheckResultMsg.push("excel format exception: more than one company code");
        break;
      }
      if(stockExchange =="" ){
        stockExchange = rowData[2]
      }else if(stockExchange != rowData[2]){
        dataCheckResult = false;
        this.xlsCheckResultMsg.push("excel format exception: more than one stock exchange");
        break;
      }

      // record dateFrom
      if(dateFrom == ""){
        dateFrom = rowData[5];
      }

      // record dateTo
      if(dateTo == ""){
        dateTo = rowData[5];
      }else if(dateTo< rowData[5]){
        dateTo = rowData[5];
      }
    }

    // when checkResult is true record check result info
    if(dataCheckResult){
      this.reqService.getCompanyNameByCode(companyCode).subscribe((response:any)=>{
        console.log(response);
        this.uploadResult.company = response.company;
      });
      this.uploadResult.stockExchange = stockExchange;
      this.uploadResult.num = xlsData.length;
      this.uploadResult.datefrom = dateFrom;
      this.uploadResult.dateto = dateTo;
    }
    return dataCheckResult;
  }
}
