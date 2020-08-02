import { Moment } from 'moment';

export interface IUserEc2Vivo {
  id?: string;
  name?: string;
  lastName?: string;
  phone?: string;
  email?: string;
  userName?: string;
  userPassword?: string;
  loginSource?: string;
  registerDate?: Moment;
}

export class UserEc2Vivo implements IUserEc2Vivo {
  constructor(
    public id?: string,
    public name?: string,
    public lastName?: string,
    public phone?: string,
    public email?: string,
    public userName?: string,
    public userPassword?: string,
    public loginSource?: string,
    public registerDate?: Moment
  ) {}
}
