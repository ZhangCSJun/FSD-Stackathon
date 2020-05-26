import { NgModule } from '@angular/core';
import { Routes, RouterModule, CanActivate } from '@angular/router';

import { LoginComponent } from './components/login/login.component';

import { UserComponent } from './components/user/user.component';
import { AdminComponent } from './components/admin/admin.component';
import { ImportdataComponent } from './components/admin/importdata/importdata.component';
import { ManagecompanyComponent } from './components/admin/managecompany/managecompany.component';
import { EditocomponayinfoComponent } from './components/admin/editocomponayinfo/editocomponayinfo.component';
import { StockinfodetailComponent } from './components/admin/stockinfodetail/stockinfodetail.component';
import { ManagestockinfoComponent } from './components/admin/managestockinfo/managestockinfo.component';
import { StockexchangeinfoComponent } from './components/admin/stockexchangeinfo/stockexchangeinfo.component';
import { UpdateIposdetailComponent } from './components/admin/update-iposdetail/update-iposdetail.component';
import { IposplanComponent } from './components/admin/iposplan/iposplan.component';

import { SignupComponent } from './components/user/signup/signup.component';
import { UserprofileComponent } from './components/user/userprofile/userprofile.component';
import { ChangePasswordComponent } from './components/user/change-password/change-password.component';
import { IposComponent } from './components/user/ipos/ipos.component';
import { ChartsComparisonComponent } from './components/user/charts-comparison/charts-comparison.component';
import { AuthGuard } from './security/AuthGuard';

const routes: Routes = [
 {path:"login",component:LoginComponent,},
 {path:'signup',component:SignupComponent},
 {path:"admin", component:AdminComponent,
  children:[
    {path:'uploadxls',component:ImportdataComponent,},
    {path:'companyinfo',component:ManagecompanyComponent},
    {path:'edit/:status',component:EditocomponayinfoComponent},
    {path:'stockDeatil',component:StockinfodetailComponent},
    {path:'stockExchange',component:ManagestockinfoComponent},
    {path:'stockExchangeInfo/:status',component:StockexchangeinfoComponent},
    {path:'updateipos',component:UpdateIposdetailComponent},
    {path:'iposplan/:status',component:IposplanComponent},
  ], canActivate:[AuthGuard],
},
{path:"user",component:UserComponent,
  children:[
    {path:'profile',component:UserprofileComponent},
    {path:'changePwd',component:ChangePasswordComponent},
    {path:'ipos',component:IposComponent},
    {path:'charts',component:ChartsComparisonComponent}
  ], canActivate:[AuthGuard],
},
{
  path: '**', redirectTo: '/login', pathMatch: 'full'
}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
  providers:[AuthGuard]
})
export class AppRoutingModule { }
