import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IUserEc2Vivo } from 'app/shared/model/user-ec-2-vivo.model';

type EntityResponseType = HttpResponse<IUserEc2Vivo>;
type EntityArrayResponseType = HttpResponse<IUserEc2Vivo[]>;

@Injectable({ providedIn: 'root' })
export class UserEc2VivoService {
  public resourceUrl = SERVER_API_URL + 'api/user-ec-2-vivos';

  constructor(protected http: HttpClient) {}

  create(userEc2Vivo: IUserEc2Vivo): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(userEc2Vivo);
    return this.http
      .post<IUserEc2Vivo>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(userEc2Vivo: IUserEc2Vivo): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(userEc2Vivo);
    return this.http
      .put<IUserEc2Vivo>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http
      .get<IUserEc2Vivo>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IUserEc2Vivo[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(userEc2Vivo: IUserEc2Vivo): IUserEc2Vivo {
    const copy: IUserEc2Vivo = Object.assign({}, userEc2Vivo, {
      registerDate:
        userEc2Vivo.registerDate && userEc2Vivo.registerDate.isValid() ? userEc2Vivo.registerDate.format(DATE_FORMAT) : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.registerDate = res.body.registerDate ? moment(res.body.registerDate) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((userEc2Vivo: IUserEc2Vivo) => {
        userEc2Vivo.registerDate = userEc2Vivo.registerDate ? moment(userEc2Vivo.registerDate) : undefined;
      });
    }
    return res;
  }
}
