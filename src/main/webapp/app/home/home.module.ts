import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { Ec2VivoSharedModule } from 'app/shared/shared.module';
import { HOME_ROUTE } from './home.route';
import { HomeComponent } from './home.component';

@NgModule({
  imports: [Ec2VivoSharedModule, RouterModule.forChild([HOME_ROUTE])],
  declarations: [HomeComponent],
})
export class Ec2VivoHomeModule {}
