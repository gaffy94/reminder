import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";

const routes: Routes = [
  {
    path: "auth",
    loadChildren: () =>
      import("../app/auth/auth.module").then(m => m.AuthModule)
  },
  {
    path: "app",
    loadChildren: () =>
      import("../app/pages/pages.module").then(m => m.PagesModule)
  },
  { path: "", redirectTo: "auth", pathMatch: "full" },
  { path: "**", redirectTo: "auth" }
];

@NgModule({
  imports: [RouterModule.forRoot(routes, { 
    enableTracing: false,
    useHash: true,
    scrollPositionRestoration: "enabled",
    onSameUrlNavigation: "reload",
    anchorScrolling: "enabled",
})],
  exports: [RouterModule]
})
export class AppRoutingModule {}
