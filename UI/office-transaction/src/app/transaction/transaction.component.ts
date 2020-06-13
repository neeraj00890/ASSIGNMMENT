import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, NgForm } from '@angular/forms';
import { RestCallService } from '../rest-call';

@Component({
  selector: 'app-transaction',
  templateUrl: './transaction.component.html',
  styleUrls: ['./transaction.component.css']
})
export class TransactionComponent implements OnInit {

  constructor(private restCallService:RestCallService) { }
 public transForm:FormGroup;
 private selected;
 private payload;

  ngOnInit(): void {
    this.transForm= new FormGroup({
      type:new FormControl(),
      amount:new FormControl(),
      description:new FormControl(),
    })

  }
  onFormClick(transFormm:NgForm){
    this.payload={
      amount:transFormm.form.value.amount,
      desc:transFormm.form.value.description,
      type:this.selected
    }
    this.restCallService.saveTran(this.payload).subscribe(
      (data)=>{
        console.log(data)
        window.top.location.reload();
      }
    );
  }

  onChange(event){
    this.selected=event.target.value;
  }

}
