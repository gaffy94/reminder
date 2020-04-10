import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PagesRoutingModule } from './pages-routing.module';
import { DashComponent } from './dash/dash.component';
import { NbMenuModule, NbLayoutModule, NbSidebarModule, NbIconModule, NbSelectModule, NbActionsModule, NbUserModule, NbInputModule, NbContextMenuModule, NbDialogModule, NbCardModule, NbButtonModule, NbListModule, NbStepperModule, NbToggleModule } from '@nebular/theme';
import { NgxSpinnerModule } from 'ngx-spinner';
import { TranslateModule } from '@ngx-translate/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NbEvaIconsModule } from '@nebular/eva-icons';


@NgModule({
  declarations: [DashComponent],
  imports: [
    CommonModule,
    PagesRoutingModule,
    NbMenuModule,
    NbLayoutModule,
    NbSidebarModule,
    NbIconModule,
    NbSelectModule,
    NbActionsModule,
    NbUserModule,
    NbInputModule,
    NbContextMenuModule,
    NgxSpinnerModule,
    NbDialogModule.forChild(),
    NbCardModule,
    NbButtonModule,
    TranslateModule,
    NbListModule,
    NbUserModule,
    FormsModule,
    ReactiveFormsModule,
    NbStepperModule,
    NbEvaIconsModule,
    NbToggleModule,
  ]
})
export class PagesModule { }
