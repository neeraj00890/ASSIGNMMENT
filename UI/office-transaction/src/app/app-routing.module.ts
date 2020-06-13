import { Routes, RouterModule } from '@angular/router';
import { NgModule } from '@angular/core';
import { TransactionComponent } from './transaction/transaction.component';
const routes: Routes = [
    {path:'addTrans', component:TransactionComponent}
]

@NgModule({
    imports: [RouterModule.forRoot(routes, {enableTracing:true,onSameUrlNavigation: 'reload'})],
    exports: [RouterModule]
  })
  export class AppRoutingModule { }