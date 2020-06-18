import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IUserBilling, UserBilling } from 'app/shared/model/user-billing.model';
import { UserBillingService } from './user-billing.service';
import { UserBillingComponent } from './user-billing.component';
import { UserBillingDetailComponent } from './user-billing-detail.component';
import { UserBillingUpdateComponent } from './user-billing-update.component';

@Injectable({ providedIn: 'root' })
export class UserBillingResolve implements Resolve<IUserBilling> {
  constructor(private service: UserBillingService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IUserBilling> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((userBilling: HttpResponse<UserBilling>) => {
          if (userBilling.body) {
            return of(userBilling.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new UserBilling());
  }
}

export const userBillingRoute: Routes = [
  {
    path: '',
    component: UserBillingComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ec2VivoApp.userBilling.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: UserBillingDetailComponent,
    resolve: {
      userBilling: UserBillingResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ec2VivoApp.userBilling.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: UserBillingUpdateComponent,
    resolve: {
      userBilling: UserBillingResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ec2VivoApp.userBilling.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: UserBillingUpdateComponent,
    resolve: {
      userBilling: UserBillingResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ec2VivoApp.userBilling.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
