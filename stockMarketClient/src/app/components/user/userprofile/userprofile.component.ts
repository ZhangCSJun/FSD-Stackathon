import { Component, OnInit } from '@angular/core';
import { RequestService } from '../../../services/request.service';
import { User } from '../../../entity/user';

@Component({
  selector: 'app-userprofile',
  templateUrl: './userprofile.component.html',
  styleUrls: ['./userprofile.component.scss']
})
export class UserprofileComponent implements OnInit {

  public userProfile:User = new User();

  constructor(public reqService:RequestService) {

  }

  ngOnInit(): void {
    // Get user's profile info
    this.reqService.getUserProfile().subscribe((response:any)=>{
      this.userProfile.userName = response.userName;
      this.userProfile.email = response.Email;
      this.userProfile.mobileNumber = response.mobileNumber;
    })
  }

}
