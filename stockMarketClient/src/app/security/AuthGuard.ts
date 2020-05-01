import { CanActivate} from "@angular/router";

export class AuthGuard implements CanActivate{
    canActivate(){
        if(sessionStorage.getItem("token")=="OK"){
            console.log("guard in");
           return true;
        }else{
            return false;
        }
    }
}
