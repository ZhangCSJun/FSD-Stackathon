export class User {
    private _userId;
    private _userName;
    private _password;
    private _userType;
    private _email;
    private _mobileNumber;
    private _confirmed;

    get userId():string{
        return this._userId;
    }
    set userId(userId:string){
        this._userId = userId;
    }
    get userName():string{
        return this._userName;
    }
    set userName(username:string){
        this._userName = username;
    }

    get password():string{
        return this._password;
    }
    set password(password:string){
        this._password = password;
    }

    get userType():string{
        return this._userType;
    }
    set userType(userType:string){
        this._userType = userType;
    }

    get email():string{
        return this._email;
    }
    set email(email:string){
        this._email = email;
    }

    get mobileNumber():string{
        return this._mobileNumber;
    }
    set mobileNumber(mobileNumber:string){
        this._mobileNumber = mobileNumber;
    }

    get confirmed():string{
        return this._confirmed;
    }
    set confirmed(confirmed:string){
        this._confirmed = confirmed;
    }
}