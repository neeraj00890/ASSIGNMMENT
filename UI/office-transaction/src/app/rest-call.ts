
import { HttpClient } from '@angular/common/http';

import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable()
export class RestCallService{
   
    baseurl='demo/transaction/'
 
   
    constructor(private http:HttpClient){    

    }
    
    getTrans(){
        return this.http.get(this.baseurl+'get');
    }
    saveTran(payLoad){
        return this.http.post(this.baseurl+'save',payLoad);
    }

   
}