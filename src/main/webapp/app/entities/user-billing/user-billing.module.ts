import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { Ec2VivoSharedModule } from 'app/shared/shared.module';
import { UserBillingComponent } from './user-billing.component';
import { UserBillingDetailComponent } from './user-billing-detail.component';
import { UserBillingUpdateComponent } from './user-billing-update.component';
import { UserBillingDeleteDialogComponent } from './user-billing-delete-dialog.component';
import { userBillingRoute } from './user-billing.route';

@NgModule({
  imports: [Ec2VivoSharedModule, RouterModule.forChild(userBillingRoute)],
  declarations: [UserBillingComponent, UserBillingDetailComponent, UserBillingUpdateComponent, UserBillingDeleteDialogComponent],
  entryComponents: [UserBillingDeleteDialogComponent],
})
export class Ec2VivoUserBillingModule {}
