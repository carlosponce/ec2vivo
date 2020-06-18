import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IUserBilling } from 'app/shared/model/user-billing.model';

@Component({
  selector: 'jhi-user-billing-detail',
  templateUrl: './user-billing-detail.component.html',
})
export class UserBillingDetailComponent implements OnInit {
  userBilling: IUserBilling | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ userBilling }) => (this.userBilling = userBilling));
  }

  previousState(): void {
    window.history.back();
  }
}
