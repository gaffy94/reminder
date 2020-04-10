import { Injectable } from '@angular/core';
import { NgxSpinnerService } from 'ngx-spinner';
import { Spinner } from 'ngx-spinner/lib/ngx-spinner.enum';

@Injectable({
  providedIn: 'root'
})
export class SpinnerService {

  constructor() { 
   
    let value =  Math.floor(Math.random() * this.animations.length) + 0;
    let anim = this.animations[value];
     console.log("Using Animation : "+ anim);
     this.defaultConfig.type = anim;
  }
  animations = ['ball-clip-rotate-multiple','square-jelly-box','timer','square-loader','pacman','line-scale-pulse-out-rapid','line-scale-pulse-out','line-scale-party','cube-transition','ball-zig-zag-deflect',
  'ball-zig-zag','ball-scale-ripple-multiple','ball-running-dots','ball-rotate','ball-pulse-sync','ball-pulse','ball-newton-cradle','ball-fussion','ball-elastic-dots','ball-clip-rotate-pulse','ball-clip-rotate-multiple','ball-climbing-dot',
  'ball-circus','ball-atom']
 defaultConfig :Spinner = {};

  startSpinner(spinnername :string, spinner: NgxSpinnerService){
    spinner.show(spinnername,this.defaultConfig);
  }


  stopSpinner(spinnername :string, spinner: NgxSpinnerService){
    spinner.hide(spinnername);
  }
}
