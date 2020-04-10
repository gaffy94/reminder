import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule as ngFormsModule } from '@angular/forms';
import { AuthRoutingModule } from './auth-routing.module';
import { LoginComponent } from './login/login.component';
import { NbAlertModule, NbInputModule, NbCheckboxModule, NbIconModule, NbLayoutModule, NbCardModule, NbButtonModule, NbActionsModule, NbUserModule, NbRadioModule, NbDatepickerModule, NbSelectModule } from '@nebular/theme';
import { TranslateModule } from '@ngx-translate/core';
import { NgxSpinnerModule } from 'ngx-spinner';


@NgModule({
  declarations: [LoginComponent],
  imports: [
    CommonModule,
    AuthRoutingModule,
    NbAlertModule,
    NbInputModule,
    NbCheckboxModule,
    NbIconModule,
    NbLayoutModule,
    NbCheckboxModule,
    NbCardModule,
    NbButtonModule,
    NbActionsModule,
    NbUserModule,
    NbRadioModule,
    NbDatepickerModule,
    NbSelectModule,
    ngFormsModule,
    TranslateModule,
    NgxSpinnerModule
  ]
})
export class AuthModule { }
