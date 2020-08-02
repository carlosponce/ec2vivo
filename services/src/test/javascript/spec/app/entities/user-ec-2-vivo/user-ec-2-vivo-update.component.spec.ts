import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { Ec2VivoTestModule } from '../../../test.module';
import { UserEc2VivoUpdateComponent } from 'app/entities/user-ec-2-vivo/user-ec-2-vivo-update.component';
import { UserEc2VivoService } from 'app/entities/user-ec-2-vivo/user-ec-2-vivo.service';
import { UserEc2Vivo } from 'app/shared/model/user-ec-2-vivo.model';

describe('Component Tests', () => {
  describe('UserEc2Vivo Management Update Component', () => {
    let comp: UserEc2VivoUpdateComponent;
    let fixture: ComponentFixture<UserEc2VivoUpdateComponent>;
    let service: UserEc2VivoService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [Ec2VivoTestModule],
        declarations: [UserEc2VivoUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(UserEc2VivoUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(UserEc2VivoUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(UserEc2VivoService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new UserEc2Vivo('123');
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
        const entity = new UserEc2Vivo();
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
