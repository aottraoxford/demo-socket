import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { MenuItem, MessageService } from 'primeng/api';
import {DialogService, DynamicDialogRef} from 'primeng/dynamicdialog';
import { ContactList } from './contact-list';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  display = false;
  displayAddContact = false;
  items: MenuItem[];
  ref: DynamicDialogRef | undefined;
  form: FormGroup;

  constructor(public dialogService: DialogService, public messageService: MessageService, private fb: FormBuilder) {
    this.items = [
      {label: 'Contact', icon: 'pi pi-users', command: () => {
        this.onContact();
      }}
    ];
    this.form = fb.group({
      firstName: new FormControl,
      lastName: new FormControl,
      phoneNumber: new FormControl('+855')
    })
   }

  ngOnInit(): void {
    
  }

  openSlider() {
    this.display = true;
  }

  onContact() {
    this.display = false;
    this.ref = this.dialogService.open(ContactList, {
      header: 'Contact',
      width: '30%',
      contentStyle: {"overflow": "auto"},
      baseZIndex: 10
    });
    this.ref?.onClose.subscribe(res => {
      if (res == "addContact") {
        this.displayAddContact = true;
      }
    })
  }

  onAddContact() {
    this.displayAddContact = true;
  }

  onCancel() {
    this.displayAddContact = false;
  }

  onCreate() {
    
  }

}
