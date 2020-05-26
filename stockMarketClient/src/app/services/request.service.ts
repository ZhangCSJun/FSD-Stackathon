import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from '../../environments/environment';

const httpOptions:any={
  headers: new HttpHeaders({'Content-Type':'application/json', 'tkn':`${localStorage.getItem('token')}`}),
  observe: 'response' // able to get full response include header
};

@Injectable({
  providedIn: 'root'
})
export class RequestService {

  // API Server URL
  private baseUrl:string = environment.baseUrl;

  public data:Array<any>;

  constructor(private http:HttpClient) { }

  // User login
  doSignIn(form:any){
    return this.http.post(`${this.baseUrl}/login`, JSON.stringify(form), httpOptions);
  }

  // User Register
  doSignUp(form:any){
    console.log(JSON.stringify(form));
    return this.http.post(`${environment.baseUrl}/user`, JSON.stringify(form), httpOptions);
  }


  doUpdatePwd(pwdInfo){
    return this.http.put(`${environment.baseUrl}/user`, JSON.stringify(pwdInfo), httpOptions);
  }

  getUserProfile(id:string){
    return this.http.get(`${this.baseUrl}/user/${id}`, httpOptions);
  }

  getCompanyNameByKeyword(str:string){
    return this.http.get(`${this.baseUrl}/company/ajax/${str}`, httpOptions);
  }

  getIposDataByCode(code:string){
    return this.http.get(`${this.baseUrl}/getIposDataByCode`, {params:{companycode:`${code}`}});
  }

  getIposDataByKeyword(keyword:string){
    return this.http.get(`${this.baseUrl}/ipodetail/${keyword}`, httpOptions);
  }

  getCompaniesInfo(){
    return this.http.get(`${this.baseUrl}/company`,  httpOptions);
  }

  getCompanyInfoByKeyword(keyword:string){
    return this.http.get(`${this.baseUrl}/company/ajax/${keyword}`,  httpOptions);
  }

  getCompanyInfoByCode(code:string){
    return this.http.get(`${this.baseUrl}/getCompanyInfoByCode`, {params:{companycode:`${code}`}});
  }


  // getIPOsPlannedByKeyword(keyword:string){
  //   return this.http.get(`${this.baseUrl}/getIPOsPlannedByKeyword`, {params:{keyword:`${keyword}`}});
  // }

  // getIPOsPlannedByCode(code:string){
  //   return this.http.get(`${this.baseUrl}/getIPOsPlannedByCode`, {params:{companycode:`${code}`}});
  // }


  getStockPriceData(){
    return this.http.get(`${this.baseUrl}/stockprice`);
  }

  /*
  * url path('/getCompanyNameByCode')
  * get company name from server
  * condition is company code
  */
 getCompanyNameByCode(companyCode){
  return this.http.get(`${this.baseUrl}/getCompanyNameByCode`, {params:{code:`${companyCode}`}});
}

  /*
  * url path('/uploadstockprice')
  * upload Stock Price Info to server
  */
  uploadCompayStockPriceData(data:any){
    return this.http.post(`${this.baseUrl}/uploadstockprice`, JSON.stringify(data), httpOptions);
  }


  /*
  * url path('/stockexchange')
  * get Stock Exchange Info from server
  */
  getStockExchangeInfo(){
    return this.http.get(`${this.baseUrl}/stockexchange`, httpOptions);
  }

  /*
  * url path('/addstockexchange')
  * post Stock Exchange List which to be added to server
  */
  addNewStockExchange(data:any){
    return this.http.post(`${this.baseUrl}/addstockexchange`, JSON.stringify(data), httpOptions);
  }

  /*
  * url path('/delstockexchange')
  * post Stock Exchange List which to be deleted to server
  */
  delStockExchange(data:any){
    return this.http.post(`${this.baseUrl}/delstockexchange`, JSON.stringify(data), httpOptions);
  }

    /*
  * url path('/addiposplan')
  * post iposplan data which to be added to server
  */
  addIPOsPlan(data:any){
    return this.http.post(`${this.baseUrl}/addiposplan`, JSON.stringify(data), httpOptions);
  }

  getDataSet(data:any){
    return this.http.post(`${this.baseUrl}/getDataSet`, JSON.stringify(data), httpOptions);
  }

  downloadTemplate(){
    return this.http.get(`${this.baseUrl}/template/download`, httpOptions);
  }
}
