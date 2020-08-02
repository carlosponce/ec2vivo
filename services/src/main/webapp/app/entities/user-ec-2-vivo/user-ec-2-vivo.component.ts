import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IUserEc2Vivo } from 'app/shared/model/user-ec-2-vivo.model';
import { UserEc2VivoService } from './user-ec-2-vivo.service';
import { UserEc2VivoDeleteDialogComponent } from './user-ec-2-vivo-delete-dialog.component';

@Component({
  selector: 'jhi-user-ec-2-vivo',
  templateUrl: './user-ec-2-vivo.component.html',
})
export class UserEc2VivoComponent implements OnInit, OnDestroy {
  userEc2Vivos?: IUserEc2Vivo[];
  eventSubscriber?: Subscription;

  constructor(
    protected userEc2VivoService: UserEc2VivoService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.userEc2VivoService.query().subscribe((res: HttpResponse<IUserEc2Vivo[]>) => (this.userEc2Vivos = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInUserEc2Vivos();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IUserEc2Vivo): string {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInUserEc2Vivos(): void {
    this.eventSubscriber = this.eventManager.subscribe('userEc2VivoListModification', () => this.loadAll());
  }

  delete(userEc2Vivo: IUserEc2Vivo): void {
    const modalRef = this.modalService.open(UserEc2VivoDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.userEc2Vivo = userEc2Vivo;
  }
}
