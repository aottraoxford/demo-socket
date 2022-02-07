import { Component } from "@angular/core";
import { DynamicDialogRef } from "primeng/dynamicdialog";

@Component({
    template: `
        <p-virtualScroller [value]="contacts" scrollHeight="450px">
            <ng-template pTemplate="header">
                <div class="p-input-icon-left" style="width: 100%" >
                    <i class="pi pi-search"></i>
                    <input class="full-w" type="text" pInputText placeholder="Search">         
                </div>
            </ng-template>
            <ng-template pTemplate="item" let-contact>
                <div style="backgroup-color: green; width: 100%;">
                    <div style="width:20%;float:left;height:80px;">
                        <img [src]="contact.image" style="max-height: 100%;max-width:100%;border-radius: 50%;"/>
                    </div>
                    <div style="width:80%;float:left;height: 80px;padding: 20px;padding-left: 8px">
                        <div>{{contact.username}}</div>
                        <div style="color: green">online</div>
                    </div>
                </div>
            </ng-template>
            <ng-template pTemplate="footer">
                <button (click)="onAddContact()" pButton type="button" label="ADD CONTACT" class="p-button-text"></button>
                <button (click)="onClose()" style="float: right" pButton type="button" label="CLOSE" class="p-button-text"></button>
            </ng-template>
        </p-virtualScroller>
    `
})
export class ContactList {
    contacts: any = [];
    constructor( public ref: DynamicDialogRef) { }

    ngOnInit() {
        for (let i=0;i<100;i++) {
            this.contacts.push({
              image: 'https://picsum.photos/200',
              username: 'Darong'
            })
          }
    }

    onClose() {
        this.ref.close("close");
    }
    onAddContact() {
        this.ref.close("addContact");
    }

}