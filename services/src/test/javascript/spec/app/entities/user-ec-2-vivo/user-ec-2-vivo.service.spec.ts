import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { UserEc2VivoService } from 'app/entities/user-ec-2-vivo/user-ec-2-vivo.service';
import { IUserEc2Vivo, UserEc2Vivo } from 'app/shared/model/user-ec-2-vivo.model';

describe('Service Tests', () => {
  describe('UserEc2Vivo Service', () => {
    let injector: TestBed;
    let service: UserEc2VivoService;
    let httpMock: HttpTestingController;
    let elemDefault: IUserEc2Vivo;
    let expectedResult: IUserEc2Vivo | IUserEc2Vivo[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(UserEc2VivoService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new UserEc2Vivo('ID', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', currentDate);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            registerDate: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        service.find('123').subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a UserEc2Vivo', () => {
        const returnedFromService = Object.assign(
          {
            id: 'ID',
            registerDate: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            registerDate: currentDate,
          },
          returnedFromService
        );

        service.create(new UserEc2Vivo()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a UserEc2Vivo', () => {
        const returnedFromService = Object.assign(
          {
            name: 'BBBBBB',
            lastName: 'BBBBBB',
            phone: 'BBBBBB',
            email: 'BBBBBB',
            userName: 'BBBBBB',
            userPassword: 'BBBBBB',
            loginSource: 'BBBBBB',
            registerDate: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            registerDate: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of UserEc2Vivo', () => {
        const returnedFromService = Object.assign(
          {
            name: 'BBBBBB',
            lastName: 'BBBBBB',
            phone: 'BBBBBB',
            email: 'BBBBBB',
            userName: 'BBBBBB',
            userPassword: 'BBBBBB',
            loginSource: 'BBBBBB',
            registerDate: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            registerDate: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a UserEc2Vivo', () => {
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
