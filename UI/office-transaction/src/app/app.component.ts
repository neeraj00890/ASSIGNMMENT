import { Component } from '@angular/core';
import { RestCallService } from './rest-call';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers:[RestCallService]
})
export class AppComponent {
  title = 'office-transaction';
  gridData;
  constructor(private restCall:RestCallService){
    this.restCall.getTrans().subscribe(data=>{
      
      this.gridData=data;
    })
  }
}
