import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IUserEc2Vivo, UserEc2Vivo } from 'app/shared/model/user-ec-2-vivo.model';
import { UserEc2VivoService } from './user-ec-2-vivo.service';
import { UserEc2VivoComponent } from './user-ec-2-vivo.component';
import { UserEc2VivoDetailComponent } from './user-ec-2-vivo-detail.component';
import { UserEc2VivoUpdateComponent } from './user-ec-2-vivo-update.component';

@Injectable({ providedIn: 'root' })
export class UserEc2VivoResolve implements Resolve<IUserEc2Vivo> {
  constructor(private service: UserEc2VivoService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IUserEc2Vivo> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((userEc2Vivo: HttpResponse<UserEc2Vivo>) => {
          if (userEc2Vivo.body) {
            return of(userEc2Vivo.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new UserEc2Vivo());
  }
}

export const userEc2VivoRoute: Routes = [
  {
    path: '',
    component: UserEc2VivoComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ec2VivoApp.userEc2Vivo.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: UserEc2VivoDetailComponent,
    resolve: {
      userEc2Vivo: UserEc2VivoResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ec2VivoApp.userEc2Vivo.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: UserEc2VivoUpdateComponent,
    resolve: {
      userEc2Vivo: UserEc2VivoResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ec2VivoApp.userEc2Vivo.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: UserEc2VivoUpdateComponent,
    resolve: {
      userEc2Vivo: UserEc2VivoResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ec2VivoApp.userEc2Vivo.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
