import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IUserEc2Vivo } from 'app/shared/model/user-ec-2-vivo.model';
import { UserEc2VivoService } from './user-ec-2-vivo.service';

@Component({
  templateUrl: './user-ec-2-vivo-delete-dialog.component.html',
})
export class UserEc2VivoDeleteDialogComponent {
  userEc2Vivo?: IUserEc2Vivo;

  constructor(
    protected userEc2VivoService: UserEc2VivoService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.userEc2VivoService.delete(id).subscribe(() => {
      this.eventManager.broadcast('userEc2VivoListModification');
      this.activeModal.close();
    });
  }
}
