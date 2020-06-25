import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import './vendor';
import { Ec2VivoSharedModule } from 'app/shared/shared.module';
import { Ec2VivoCoreModule } from 'app/core/core.module';
import { Ec2VivoAppRoutingModule } from './app-routing.module';
import { Ec2VivoHomeModule } from './home/home.module';
import { Ec2VivoEntityModule } from './entities/entity.module';
// jhipster-needle-angular-add-module-import JHipster will add new module here
import { MainComponent } from './layouts/main/main.component';
import { NavbarComponent } from './layouts/navbar/navbar.component';
import { FooterComponent } from './layouts/footer/footer.component';
import { PageRibbonComponent } from './layouts/profiles/page-ribbon.component';
import { ActiveMenuDirective } from './layouts/navbar/active-menu.directive';
import { ErrorComponent } from './layouts/error/error.component';

@NgModule({
  imports: [
    BrowserModule,
    Ec2VivoSharedModule,
    Ec2VivoCoreModule,
    Ec2VivoHomeModule,
    // jhipster-needle-angular-add-module JHipster will add new module here
    Ec2VivoEntityModule,
    Ec2VivoAppRoutingModule,
  ],
  declarations: [MainComponent, NavbarComponent, ErrorComponent, PageRibbonComponent, ActiveMenuDirective, FooterComponent],
  bootstrap: [MainComponent],
})
export class Ec2VivoAppModule {}
