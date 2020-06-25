import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { UserBillingService } from 'app/entities/user-billing/user-billing.service';
import { IUserBilling, UserBilling } from 'app/shared/model/user-billing.model';

describe('Service Tests', () => {
  describe('UserBilling Service', () => {
    let injector: TestBed;
    let service: UserBillingService;
    let httpMock: HttpTestingController;
    let elemDefault: IUserBilling;
    let expectedResult: IUserBilling | IUserBilling[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(UserBillingService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new UserBilling('ID', 'AAAAAAA', 'AAAAAAA', currentDate, 'AAAAAAA', 0, 0);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            billingDate: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        service.find('123').subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a UserBilling', () => {
        const returnedFromService = Object.assign(
          {
            id: 'ID',
            billingDate: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            billingDate: currentDate,
          },
          returnedFromService
        );

        service.create(new UserBilling()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a UserBilling', () => {
        const returnedFromService = Object.assign(
          {
            eventId: 'BBBBBB',
            ticketId: 'BBBBBB',
            billingDate: currentDate.format(DATE_FORMAT),
            email: 'BBBBBB',
            discount: 1,
            price: 1,
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            billingDate: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of UserBilling', () => {
        const returnedFromService = Object.assign(
          {
            eventId: 'BBBBBB',
            ticketId: 'BBBBBB',
            billingDate: currentDate.format(DATE_FORMAT),
            email: 'BBBBBB',
            discount: 1,
            price: 1,
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            billingDate: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a UserBilling', () => {
        service.delete('123').subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
