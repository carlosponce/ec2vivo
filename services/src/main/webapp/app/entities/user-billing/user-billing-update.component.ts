import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { IUserBilling, UserBilling } from 'app/shared/model/user-billing.model';
import { UserBillingService } from './user-billing.service';
import { IUser } from 'app/core/user/user.model';
import { UserService } from 'app/core/user/user.service';
import { IUserEc2Vivo } from 'app/shared/model/user-ec-2-vivo.model';
import { UserEc2VivoService } from 'app/entities/user-ec-2-vivo/user-ec-2-vivo.service';

type SelectableEntity = IUser | IUserEc2Vivo;

@Component({
  selector: 'jhi-user-billing-update',
  templateUrl: './user-billing-update.component.html',
})
export class UserBillingUpdateComponent implements OnInit {
  isSaving = false;
  users: IUser[] = [];
  user2vivos: IUserEc2Vivo[] = [];
  billingDateDp: any;

  editForm = this.fb.group({
    id: [],
    eventId: [],
    ticketId: [],
    billingDate: [],
    email: [],
    discount: [],
    price: [],
    user: [],
    user2vivo: [],
  });

  constructor(
    protected userBillingService: UserBillingService,
    protected userService: UserService,
    protected userEc2VivoService: UserEc2VivoService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ userBilling }) => {
      this.updateForm(userBilling);

      this.userService.query().subscribe((res: HttpResponse<IUser[]>) => (this.users = res.body || []));

      this.userEc2VivoService
        .query({ filter: 'userbilling-is-null' })
        .pipe(
          map((res: HttpResponse<IUserEc2Vivo[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IUserEc2Vivo[]) => {
          if (!userBilling.user2vivo || !userBilling.user2vivo.id) {
            this.user2vivos = resBody;
          } else {
            this.userEc2VivoService
              .find(userBilling.user2vivo.id)
              .pipe(
                map((subRes: HttpResponse<IUserEc2Vivo>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IUserEc2Vivo[]) => (this.user2vivos = concatRes));
          }
        });
    });
  }

  updateForm(userBilling: IUserBilling): void {
    this.editForm.patchValue({
      id: userBilling.id,
      eventId: userBilling.eventId,
      ticketId: userBilling.ticketId,
      billingDate: userBilling.billingDate,
      email: userBilling.email,
      discount: userBilling.discount,
      price: userBilling.price,
      user: userBilling.user,
      user2vivo: userBilling.user2vivo,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const userBilling = this.createFromForm();
    if (userBilling.id !== undefined) {
      this.subscribeToSaveResponse(this.userBillingService.update(userBilling));
    } else {
      this.subscribeToSaveResponse(this.userBillingService.create(userBilling));
    }
  }

  private createFromForm(): IUserBilling {
    return {
      ...new UserBilling(),
      id: this.editForm.get(['id'])!.value,
      eventId: this.editForm.get(['eventId'])!.value,
      ticketId: this.editForm.get(['ticketId'])!.value,
      billingDate: this.editForm.get(['billingDate'])!.value,
      email: this.editForm.get(['email'])!.value,
      discount: this.editForm.get(['discount'])!.value,
      price: this.editForm.get(['price'])!.value,
      user: this.editForm.get(['user'])!.value,
      user2vivo: this.editForm.get(['user2vivo'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IUserBilling>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }
}
