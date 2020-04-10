import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule, HttpClient } from "@angular/common/http";
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NbThemeModule, NbLayoutModule, NbContextMenuModule, NbToastrModule, NbSidebarModule, NbDialogModule, NbMenuModule } from '@nebular/theme';
import { NbEvaIconsModule } from '@nebular/eva-icons';
import { UserService } from './services/user/user.service';
import { SpinnerService } from './services/spinner/spinner.service';
import { RestclientService } from './services/restclient/restclient.service';
import { ToastService } from './services/toast/toast.service';
import { DataService } from './services/data/data.service';
import { NgxSpinnerModule } from 'ngx-spinner';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    NbContextMenuModule,
    BrowserAnimationsModule,
    NbToastrModule.forRoot(),
    NbSidebarModule.forRoot(),
    NbDialogModule.forRoot(),
    NbMenuModule.forRoot(),
    NbThemeModule.forRoot({ name: "default" }),
    NbLayoutModule,
    NbEvaIconsModule,
    NgxSpinnerModule,
    // TranslateModule.forRoot({
    //   loader: {
    //     provide: TranslateLoader,
    //     useFactory: HttpLoaderFactory,
    //     deps: [HttpClient]
    //   }
    // }),
  ],
  providers: [
    UserService,
    SpinnerService,
    RestclientService,
    ToastService,
    DataService,
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
