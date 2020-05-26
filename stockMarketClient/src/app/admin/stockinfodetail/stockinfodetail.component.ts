import { Component, OnInit } from '@angular/core';
import { RequestService } from '../../../services/request.service';
import { Router } from "@angular/router";
import { Editstatus } from '../../../util/editstatus.enum';

@Component({
  selector: 'app-stockinfodetail',
  templateUrl: './stockinfodetail.component.html',
  styleUrls: ['./stockinfodetail.component.scss']
})
export class StockinfodetailComponent implements OnInit {

  public stockData:any;
  constructor(private reqService:RequestService,  private router:Router) { }

  public bir = new Date();
  ngOnInit(): void {
    this.stockDeatil();
  }

  stockDeatil(){
    this.reqService.getStockPriceData().subscribe((response)=>{
      this.stockData = response;
    })
  }
  backtoEdit(){
    this.router.navigate(['/admin/edit'],{queryParams: {status: Editstatus.edit}});
  }
  
}
