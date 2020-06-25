import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { Ec2VivoTestModule } from '../../../test.module';
import { UserBillingComponent } from 'app/entities/user-billing/user-billing.component';
import { UserBillingService } from 'app/entities/user-billing/user-billing.service';
import { UserBilling } from 'app/shared/model/user-billing.model';

describe('Component Tests', () => {
  describe('UserBilling Management Component', () => {
    let comp: UserBillingComponent;
    let fixture: ComponentFixture<UserBillingComponent>;
    let service: UserBillingService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [Ec2VivoTestModule],
        declarations: [UserBillingComponent],
      })
        .overrideTemplate(UserBillingComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(UserBillingComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(UserBillingService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new UserBilling('123')],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.userBillings && comp.userBillings[0]).toEqual(jasmine.objectContaining({ id: '123' }));
    });
  });
});
