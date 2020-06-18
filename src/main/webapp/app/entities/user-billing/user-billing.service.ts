import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IUserBilling } from 'app/shared/model/user-billing.model';

type EntityResponseType = HttpResponse<IUserBilling>;
type EntityArrayResponseType = HttpResponse<IUserBilling[]>;

@Injectable({ providedIn: 'root' })
export class UserBillingService {
  public resourceUrl = SERVER_API_URL + 'api/user-billings';

  constructor(protected http: HttpClient) {}

  create(userBilling: IUserBilling): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(userBilling);
    return this.http
      .post<IUserBilling>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(userBilling: IUserBilling): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(userBilling);
    return this.http
      .put<IUserBilling>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http
      .get<IUserBilling>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IUserBilling[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(userBilling: IUserBilling): IUserBilling {
    const copy: IUserBilling = Object.assign({}, userBilling, {
      billingDate: userBilling.billingDate && userBilling.billingDate.isValid() ? userBilling.billingDate.format(DATE_FORMAT) : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.billingDate = res.body.billingDate ? moment(res.body.billingDate) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((userBilling: IUserBilling) => {
        userBilling.billingDate = userBilling.billingDate ? moment(userBilling.billingDate) : undefined;
      });
    }
    return res;
  }
}
