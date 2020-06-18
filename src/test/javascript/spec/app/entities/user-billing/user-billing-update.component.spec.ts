import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { Ec2VivoTestModule } from '../../../test.module';
import { UserBillingUpdateComponent } from 'app/entities/user-billing/user-billing-update.component';
import { UserBillingService } from 'app/entities/user-billing/user-billing.service';
import { UserBilling } from 'app/shared/model/user-billing.model';

describe('Component Tests', () => {
  describe('UserBilling Management Update Component', () => {
    let comp: UserBillingUpdateComponent;
    let fixture: ComponentFixture<UserBillingUpdateComponent>;
    let service: UserBillingService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [Ec2VivoTestModule],
        declarations: [UserBillingUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(UserBillingUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(UserBillingUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(UserBillingService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new UserBilling('123');
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new UserBilling();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
