import { Component, OnInit } from '@angular/core';
import { SpinnerService } from 'src/app/services/spinner/spinner.service';
import { RestclientService } from 'src/app/services/restclient/restclient.service';
import { NgxSpinnerService } from 'ngx-spinner';
import { ToastService } from 'src/app/services/toast/toast.service';
import { Router } from '@angular/router';
import { UserService } from 'src/app/services/user/user.service';
import { fetchTenants, doToggle, resetAll } from 'src/app/constants/endpoints';

@Component({
  selector: 'app-dash',
  templateUrl: './dash.component.html',
  styleUrls: ['./dash.component.scss']
})
export class DashComponent implements OnInit {

  constructor(private restClient: RestclientService, private spinner: NgxSpinnerService
    , private toastrService: ToastService, private router: Router, private userService: UserService, private spinnerX: SpinnerService) { }
tenants:any[] = [];
  async ngOnInit() {
await this.fetchTenants();
  }

  async fetchTenants(){
    const link = fetchTenants.apiUrl;
    let resp = await this.restClient.funcGet(link, this.spinner, 'sp2');

    if (resp.error != null || !resp) {
      this.toastrService.showToast('danger', "Application Message", "Error Fetching User Info");
      this.spinnerX.stopSpinner('sp2', this.spinner);
      return;
    }
      this.tenants = resp.response;
  }

  async doToggle(data){
    const link = doToggle.apiUrl.replace("{id}",data.id);
    let resp = await this.restClient.funcGet(link, this.spinner, 'sp2');

    if (resp.error != null || !resp) {
      this.toastrService.showToast('danger', "Application Message", "Error Fetching User Info");
      this.spinnerX.stopSpinner('sp2', this.spinner);
      return;
    }
  }

  async resetAll(){
    const link = resetAll.apiUrl;
    let resp = await this.restClient.funcGet(link, this.spinner, 'sp2');

    if (resp.error != null || !resp) {
      this.toastrService.showToast('danger', "Application Message", "Error Fetching User Info");
      this.spinnerX.stopSpinner('sp2', this.spinner);
      return;
    }
    this.fetchTenants();
  }
}
