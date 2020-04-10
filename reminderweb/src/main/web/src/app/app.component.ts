import { Component, OnInit } from '@angular/core';
import { NbThemeService } from '@nebular/theme';
import { TranslateService } from '@ngx-translate/core';
import { UserService } from './services/user/user.service';
import { Router, RouterEvent, RouteConfigLoadStart, RouteConfigLoadEnd } from '@angular/router';
import { NgxSpinnerService } from 'ngx-spinner';
import { SpinnerService } from './services/spinner/spinner.service';
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
}) 
export class AppComponent  implements OnInit {

  public isShowingRouteLoadIndicator: boolean;
  ngOnInit() {
    this.spinnerX.startSpinner('cr',this.spinner);
    if (this.userService.getUserInfo() == undefined || this.userService.getUserInfo() == null) {
      console.log("im catching your redirect");
      this.router.navigate(['auth']);
    }
  }
  title = 'reminderweb';
  constructor(private spinnerX: SpinnerService,private spinner: NgxSpinnerService,private userService : UserService, private router: Router) {
    this.isShowingRouteLoadIndicator = false;
    var asyncLoadCount = 0;
    router.events.subscribe(
			( event: RouterEvent ) : void => {
        console.log(event);

				if ( event instanceof RouteConfigLoadStart ) {

					asyncLoadCount++;

				} else if ( event instanceof RouteConfigLoadEnd ) {

					asyncLoadCount--;

				}

				// If there is at least one pending asynchronous config load request,
				// then let's show the loading indicator.
				// --
				// CAUTION: I'm using CSS to include a small delay such that this loading
				// indicator won't be seen by people with sufficiently fast connections.
        this.isShowingRouteLoadIndicator = !! asyncLoadCount;
       
        if((event instanceof RouteConfigLoadStart || event instanceof RouteConfigLoadEnd) && this.isShowingRouteLoadIndicator){
         this.spinnerX.startSpinner('cr',this.spinner);
        } else if (event instanceof RouteConfigLoadStart || event instanceof RouteConfigLoadEnd) {
          this.spinnerX.stopSpinner('cr',this.spinner);
        }

			}
    );
    

  }



}