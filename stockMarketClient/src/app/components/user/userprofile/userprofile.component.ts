import { Component, OnInit } from '@angular/core';
import { RequestService } from '../../../services/request.service';
import { User } from '../../../entity/user';
import { ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-userprofile',
  templateUrl: './userprofile.component.html',
  styleUrls: ['./userprofile.component.scss']
})
export class UserprofileComponent implements OnInit {

  public userProfile:User = new User();
  public userId:string;

  constructor(private routerinfo:ActivatedRoute, public reqService:RequestService) {

  }

  ngOnInit(): void {
    // get user id from pre page
    this.routerinfo.queryParams.subscribe(queryParam=>{
      this.userId = queryParam.userId;
    });
    // Get user's profile info
    this.reqService.getUserProfile(this.userId).subscribe((response:any)=>{
      console.log(response);
      // if response status is 0k(200), show user profile info
      if(response.status == 200 && response.body.code == "001"){
        this.userProfile.userId = response.body.business.data.id;
        this.userProfile.userName = response.body.business.data.userName;
        this.userProfile.email = response.body.business.data.email;
        this.userProfile.mobileNumber = response.body.business.data.mobileNumber;
        this.userProfile.userType = response.body.business.data.userType
      } 
    }, (error:any)=>{
      console.log(error);
    });
  }

}
