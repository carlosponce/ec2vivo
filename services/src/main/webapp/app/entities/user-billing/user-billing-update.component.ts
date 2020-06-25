import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IUserBilling, UserBilling } from 'app/shared/model/user-billing.model';
import { UserBillingService } from './user-billing.service';
import { IUser } from 'app/core/user/user.model';
import { UserService } from 'app/core/user/user.service';

@Component({
  selector: 'jhi-user-billing-update',
  templateUrl: './user-billing-update.component.html',
})
export class UserBillingUpdateComponent implements OnInit {
  isSaving = false;
  users: IUser[] = [];
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
  });

  constructor(
    protected userBillingService: UserBillingService,
    protected userService: UserService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ userBilling }) => {
      this.updateForm(userBilling);

      this.userService.query().subscribe((res: HttpResponse<IUser[]>) => (this.users = res.body || []));
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

  trackById(index: number, item: IUser): any {
    return item.id;
  }
}
