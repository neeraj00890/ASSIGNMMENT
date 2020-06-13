import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {ReactiveFormsModule, FormsModule} from '@angular/forms';
import { AppComponent } from './app.component';
import { TransactionComponent } from './transaction/transaction.component';
import { GridComponent } from './grid/grid.component';
import { HttpClientModule }    from '@angular/common/http';
import { RestCallService } from './rest-call';
import { AppRoutingModule } from './app-routing.module';

@NgModule({
  declarations: [
    AppComponent,
    TransactionComponent,
    GridComponent
  ],
  imports: [
    BrowserModule,
    ReactiveFormsModule,
    FormsModule,
    HttpClientModule,
    AppRoutingModule
  ],
  providers: [RestCallService],
  bootstrap: [AppComponent]
})
export class AppModule { }
