import { Moment } from 'moment';
import { IUser } from 'app/core/user/user.model';
import { IUserEc2Vivo } from 'app/shared/model/user-ec-2-vivo.model';

export interface IUserBilling {
  id?: string;
  eventId?: string;
  ticketId?: string;
  billingDate?: Moment;
  email?: string;
  discount?: number;
  price?: number;
  user?: IUser;
  user2vivo?: IUserEc2Vivo;
}

export class UserBilling implements IUserBilling {
  constructor(
    public id?: string,
    public eventId?: string,
    public ticketId?: string,
    public billingDate?: Moment,
    public email?: string,
    public discount?: number,
    public price?: number,
    public user?: IUser,
    public user2vivo?: IUserEc2Vivo
  ) {}
}
