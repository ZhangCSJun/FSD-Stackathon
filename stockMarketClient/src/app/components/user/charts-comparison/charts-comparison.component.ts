import { Component, OnInit } from '@angular/core';
import { RequestService } from '../../../services/request.service';
import * as echarts  from 'echarts';
import * as $ from 'jquery';

@Component({
  selector: 'app-charts-comparison',
  templateUrl: './charts-comparison.component.html',
  styleUrls: ['./charts-comparison.component.scss']
})
export class ChartsComparisonComponent implements OnInit {

  public dispFlg:boolean=false;

  public alertMsg:string="";

  public chartsTypes:Array<any>=[
    {
      value:'0',
      type:'sing company over different periods of time',
    }, {
      value:'1',
      type:'different companies over a specific period',
    }];

  public selectedType:string;

  public years:Array<any>=[
    {
      value:'2019',
      year:'2019'
    },{
      value:'2018',
      year:'2018'
    },{
      value:'2017',
      year:'2017'
    },{
      value:'2016',
      year:'2016'
    },{
      value:'2015',
      year:'2015'
    }];
    public period:Array<any>=[
      {
        value:'0',
        period:'1Q'
      },{
        value:'1',
        period:'2Q'
      },{
        value:'2',
        period:'3Q'
      },{
        value:'3',
        period:'4Q'
      }];

      public company:Array<any>=[
        {
          code:'201',
          name:'Tian Mao Mall'
        },{
          code:'202',
          name:'Jing Dong Mall'
        },{
          code:'203',
          name:'Tao Bao'
        },{
          code:'204',
          name:'VIP SALON'
        }];

  // 创建表格对象
  public chartOption3: any = {};
  constructor(private reqService:RequestService) { }

  ngOnInit(): void {

    // this.initChart3();
  }


  initChart3(reqParams:any) {

    this.reqService.getDataSet(reqParams).subscribe((response:any)=>{
      let dimension:Array<any>=response.dimension;
      let productions:Array<any>=response.production;
      let series:Array<any>=response.series;

      // fill chart dataset
      this.chartOption3 = {
        legend: {},
        tooltip: {},
        dataset: {
            dimensions: dimension,
            source: productions,
        },
        xAxis: {type: 'category'},
        yAxis: {},
        series: series,
      }
      let chart = echarts.init(document.getElementById('echarts'));
      chart.setOption(this.chartOption3);
    })

  }


  selType(evt:any){
    this.selectedType = evt.target.value;
    this.dispFlg=true;
  }

  showChart(){

    let reqParams;
    if(this.selectedType=='0'){
      console.log($('#year').val());
      reqParams={
        type:"0",
        year:$('#year').val(),
        comapnyCode:$('#company').val()
      }
      this.initChart3(reqParams);
    }else if(this.selectedType=='1'){
      this.alertMsg = "";

      let companyCodeList:Array<string>=$('#mutilcompany').val();
      if(companyCodeList==undefined || companyCodeList.length==0||companyCodeList.length==1){
        this.alertMsg="at least select tow companies";
      }else if(companyCodeList.length>3){
        this.alertMsg="no more than three companies to be selected";
      } else {
        let reqParams={
          type:"1",
          year:$('#period').val(),
          comapnyCode:$('#mutilcompany').val()
        }
        this.initChart3(reqParams);
      }
    }
  }
}
