import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { AuthComponent } from './auth/auth.component';
import { PrimeNGModule } from './primeng.module';
import { RoutingModule } from './routing.module';
import { DashboardComponent } from './dashboard/dashboard.component';
import { ContactList } from './dashboard/contact-list';
import { DialogService } from 'primeng/dynamicdialog';
import { MessageService } from 'primeng/api';

@NgModule({
  declarations: [
    AppComponent,
    AuthComponent,
    DashboardComponent,
    ContactList
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    HttpClientModule,
    ReactiveFormsModule,
    RoutingModule,
    PrimeNGModule
  ],
  providers: [DialogService, MessageService],
  bootstrap: [AppComponent]
})
export class AppModule { }
