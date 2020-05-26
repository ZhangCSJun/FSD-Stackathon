import { Component, OnInit } from '@angular/core';
import { Company } from '../../../entity/company';
import { Router } from "@angular/router";
import{ActivatedRoute,Params} from  '@angular/router';

@Component({
  selector: 'app-editocomponayinfo',
  templateUrl: './editocomponayinfo.component.html',
  styleUrls: ['./editocomponayinfo.component.scss']
})
export class EditocomponayinfoComponent implements OnInit {

  public company:any;
  public pageStatus:string;

  constructor(private router:Router, private activatedRoute:ActivatedRoute) { }

  ngOnInit(): void {
    this.activatedRoute.params
    this.pageStatus =this.activatedRoute.snapshot.params['status'];
    if(this.pageStatus=="1"){
      let objMap = this.activatedRoute.snapshot.queryParamMap;
      this.company = new Company(
        objMap.get('name'), 
        objMap.get('code'), 
        objMap.get('turnover'), 
        objMap.get('ceo'),
        objMap.get('boardofdirectors'),
        objMap.get('sectorid'), 
        objMap.get('briefwriteup'), 
        objMap.get('status'), );
      console.log(this.company);
    } else {
      this.company = new Company("","","","","","","","",);
    }
  }

  gotoDetails(){
    this.router.navigate(['/admin/stockDeatil']);
  }

}
