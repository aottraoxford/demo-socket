import { NgModule } from "@angular/core";
import {AccordionModule} from 'primeng/accordion';
import {CardModule} from 'primeng/card';
import {InputTextModule} from 'primeng/inputtext';
import {ButtonModule} from 'primeng/button';
import {DialogModule} from 'primeng/dialog';

const modules = [AccordionModule, CardModule, InputTextModule, ButtonModule, DialogModule];
@NgModule({
    imports: modules,
    exports: modules
})
export class PrimeNGModule {}