import { Component, OnInit } from '@angular/core';
import { NbThemeService, NbToastrService } from '@nebular/theme';
import { RestclientService } from 'src/app/services/restclient/restclient.service';
import { NgxSpinnerService } from 'ngx-spinner';
import { ToastService } from 'src/app/services/toast/toast.service';
import { Router } from '@angular/router';
import { UserService } from 'src/app/services/user/user.service';

import { SpinnerService } from 'src/app/services/spinner/spinner.service';
import { link } from 'src/app/constants/endpoints';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  theme: any;
  language: any;
  username: any;
  password: any;
  constructor( 
     private restClient : RestclientService, private spinner: NgxSpinnerService
     ,private toastrService: ToastService, private router: Router, private userService: UserService, private spinnerX: SpinnerService) {
   
  }

  async login(){
    if(this.username == undefined || this.username.trim().length < 5){
      this.toastrService.showToast('danger',"Login Message","Invalid Email");
      return;
    }
    this.spinnerX.startSpinner('sp2',this.spinner);
    let data = new FormData();
    data.append('username', this.username);
    data.append('password', this.password);
    let resp = await this.restClient.doUserAuth(data,link.login,this.spinner,'sp2');

    if (resp.error != null) {
      this.toastrService.showToast('danger',"Login Message","Login Failed");
      this.spinnerX.stopSpinner('sp2',this.spinner);
      return;
    }
    // await this.userService.setUserInfo(ret.response);
    this.spinnerX.stopSpinner('sp2',this.spinner);
    this.toastrService.showToast('success',"Login Message","Login Success");
    this.router.navigate(['app/dash']);
  }
  ngOnInit() {
  }



}
