import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { Ec2VivoSharedModule } from 'app/shared/shared.module';
import { UserEc2VivoComponent } from './user-ec-2-vivo.component';
import { UserEc2VivoDetailComponent } from './user-ec-2-vivo-detail.component';
import { UserEc2VivoUpdateComponent } from './user-ec-2-vivo-update.component';
import { UserEc2VivoDeleteDialogComponent } from './user-ec-2-vivo-delete-dialog.component';
import { userEc2VivoRoute } from './user-ec-2-vivo.route';

@NgModule({
  imports: [Ec2VivoSharedModule, RouterModule.forChild(userEc2VivoRoute)],
  declarations: [UserEc2VivoComponent, UserEc2VivoDetailComponent, UserEc2VivoUpdateComponent, UserEc2VivoDeleteDialogComponent],
  entryComponents: [UserEc2VivoDeleteDialogComponent],
})
export class Ec2VivoUserEc2VivoModule {}
