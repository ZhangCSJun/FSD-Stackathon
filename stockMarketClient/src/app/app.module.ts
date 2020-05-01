/* Module */
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { NgxEchartsModule } from 'ngx-echarts';
/* Compponet */
import { AppComponent } from './app.component';
import { LoginComponent } from './components/login/login.component';

import { AdminComponent } from './components/admin/admin.component';
import { ImportdataComponent } from './components/admin/importdata/importdata.component';
import { ManagecompanyComponent } from './components/admin/managecompany/managecompany.component';
import { EditocomponayinfoComponent } from './components/admin/editocomponayinfo/editocomponayinfo.component';
import { StockinfodetailComponent } from './components/admin/stockinfodetail/stockinfodetail.component';
import { ManagestockinfoComponent } from './components/admin/managestockinfo/managestockinfo.component';
import { StockexchangeinfoComponent } from './components/admin/stockexchangeinfo/stockexchangeinfo.component';
import { UpdateIposdetailComponent } from './components/admin/update-iposdetail/update-iposdetail.component';
import { IposplanComponent } from './components/admin/iposplan/iposplan.component';

import { UserComponent } from './components/user/user.component';
import { UserprofileComponent } from './components/user/userprofile/userprofile.component';
import { ChangePasswordComponent } from './components/user/change-password/change-password.component';
import { IposComponent } from './components/user/ipos/ipos.component';
import { ChartsComparisonComponent } from './components/user/charts-comparison/charts-comparison.component';
import { SignupComponent } from './components/user/signup/signup.component';
/* Service */
import { RequestService } from './services/request.service';

@NgModule({
  declarations: [
    AppComponent,
    UserComponent,
    AdminComponent,
    UserprofileComponent,
    ChangePasswordComponent,
    IposComponent,
    ImportdataComponent,
    ChartsComparisonComponent,
    SignupComponent,
    LoginComponent,
    ManagecompanyComponent,
    EditocomponayinfoComponent,
    StockinfodetailComponent,
    ManagestockinfoComponent,
    StockexchangeinfoComponent,
    UpdateIposdetailComponent,
    IposplanComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    NgxEchartsModule
  ],
  providers: [RequestService],
  bootstrap: [AppComponent]
})
export class AppModule { }
