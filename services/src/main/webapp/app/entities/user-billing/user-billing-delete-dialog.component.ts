import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IUserBilling } from 'app/shared/model/user-billing.model';
import { UserBillingService } from './user-billing.service';

@Component({
  templateUrl: './user-billing-delete-dialog.component.html',
})
export class UserBillingDeleteDialogComponent {
  userBilling?: IUserBilling;

  constructor(
    protected userBillingService: UserBillingService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.userBillingService.delete(id).subscribe(() => {
      this.eventManager.broadcast('userBillingListModification');
      this.activeModal.close();
    });
  }
}
