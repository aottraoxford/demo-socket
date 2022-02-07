import { NgModule } from "@angular/core";
import { AccordionModule } from 'primeng/accordion';
import { CardModule } from 'primeng/card';
import { InputTextModule } from 'primeng/inputtext';
import { ButtonModule } from 'primeng/button';
import { DialogModule } from 'primeng/dialog';
import { SplitterModule } from 'primeng/splitter';
import { SidebarModule } from 'primeng/sidebar';
import { MenubarModule } from 'primeng/menubar';
import { DividerModule } from 'primeng/divider';
import { MenuModule } from 'primeng/menu';
import { DynamicDialogModule } from 'primeng/dynamicdialog';
import { ToastModule } from 'primeng/toast';
import { TableModule } from 'primeng/table';
import { VirtualScrollerModule } from 'primeng/virtualscroller';
import { ImageModule } from 'primeng/image';

const modules = [ImageModule, VirtualScrollerModule, ToastModule, TableModule ,DynamicDialogModule, MenuModule,AccordionModule, CardModule, InputTextModule, ButtonModule, DialogModule, SplitterModule, SidebarModule, MenubarModule, DividerModule];
@NgModule({
    imports: modules,
    exports: modules
})
export class PrimeNGModule {}