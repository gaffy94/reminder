import { Injectable } from '@angular/core';
import { NbToastrService, NbGlobalPosition, NbGlobalPhysicalPosition, NbComponentStatus } from '@nebular/theme';
import { ToasterConfig } from 'angular2-toaster';
import 'style-loader!angular2-toaster/toaster.css';
@Injectable({
  providedIn: 'root'
})
export class ToastService {

  constructor(private toastrService: NbToastrService) { }
  config: ToasterConfig;
  destroyByClick = true;
  duration = 10000;
  hasIcon = true;
  position: NbGlobalPosition = NbGlobalPhysicalPosition.TOP_RIGHT;
  preventDuplicates = false;


 showToast(type: NbComponentStatus, title: string, body: string) {
    const config = {
      status: type,
      destroyByClick: this.destroyByClick,
      duration: this.duration,
      hasIcon: this.hasIcon,
      position: this.position,
      preventDuplicates: this.preventDuplicates,
    };
    this.toastrService.show(
      body,
      title,
      config);
  }
}
