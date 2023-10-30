import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest, HTTP_INTERCEPTORS } from "@angular/common/http";

import { Injectable } from "@angular/core";

import {Observable} from 'rxjs';

import { LoginService } from "./login.service";

 

const TOKEN_HEADER_KEY='Authorization';

 

@Injectable()

export class AuthInterceptor implements HttpInterceptor{

constructor(private loginService:LoginService){}

 

    intercept(req: HttpRequest<any>, next:HttpHandler):Observable<HttpEvent<any>>{

 

        let newReq=req;

        let token=this.loginService.getToken();

 

        console.log("Interceptor",token);

 

        if(token!=null){

            // newReq.clone({setHeaders:{Authorization:`Bearer ${token}`}})

            newReq=req.clone({headers:req.headers.set(TOKEN_HEADER_KEY,'Bearer '+token)})

        }

       

        return next.handle(newReq);

    }

 

}

 

export const httpInterceptorProviders = [

    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true },

];