import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IUserBilling } from 'app/shared/model/user-billing.model';
import { UserBillingService } from './user-billing.service';
import { UserBillingDeleteDialogComponent } from './user-billing-delete-dialog.component';

@Component({
  selector: 'jhi-user-billing',
  templateUrl: './user-billing.component.html',
})
export class UserBillingComponent implements OnInit, OnDestroy {
  userBillings?: IUserBilling[];
  eventSubscriber?: Subscription;

  constructor(
    protected userBillingService: UserBillingService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.userBillingService.query().subscribe((res: HttpResponse<IUserBilling[]>) => (this.userBillings = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInUserBillings();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IUserBilling): string {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInUserBillings(): void {
    this.eventSubscriber = this.eventManager.subscribe('userBillingListModification', () => this.loadAll());
  }

  delete(userBilling: IUserBilling): void {
    const modalRef = this.modalService.open(UserBillingDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.userBilling = userBilling;
  }
}
