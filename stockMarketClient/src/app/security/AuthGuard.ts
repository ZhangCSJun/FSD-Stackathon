import { CanActivate} from "@angular/router";

export class AuthGuard implements CanActivate{
    canActivate(){
        if(localStorage.getItem("token")!='null' && localStorage.getItem("token")!=''){
            return true;
        }else{
            return false;
        }
    }
}
