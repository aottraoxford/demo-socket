import { Component, OnInit } from '@angular/core';
import { MenuItem } from 'primeng/api';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  display = false;
  items: MenuItem[]

  constructor() {
    this.items = [
      {label: 'Contact', icon: 'pi pi-users'}
    ];
   }

  ngOnInit(): void {
  }

  openSlider() {
    this.display = true;
  }

}
