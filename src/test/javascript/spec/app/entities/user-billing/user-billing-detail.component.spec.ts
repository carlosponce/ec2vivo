import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { Ec2VivoTestModule } from '../../../test.module';
import { UserBillingDetailComponent } from 'app/entities/user-billing/user-billing-detail.component';
import { UserBilling } from 'app/shared/model/user-billing.model';

describe('Component Tests', () => {
  describe('UserBilling Management Detail Component', () => {
    let comp: UserBillingDetailComponent;
    let fixture: ComponentFixture<UserBillingDetailComponent>;
    const route = ({ data: of({ userBilling: new UserBilling('123') }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [Ec2VivoTestModule],
        declarations: [UserBillingDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(UserBillingDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(UserBillingDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load userBilling on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.userBilling).toEqual(jasmine.objectContaining({ id: '123' }));
      });
    });
  });
});
