import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { Ec2VivoTestModule } from '../../../test.module';
import { UserEc2VivoComponent } from 'app/entities/user-ec-2-vivo/user-ec-2-vivo.component';
import { UserEc2VivoService } from 'app/entities/user-ec-2-vivo/user-ec-2-vivo.service';
import { UserEc2Vivo } from 'app/shared/model/user-ec-2-vivo.model';

describe('Component Tests', () => {
  describe('UserEc2Vivo Management Component', () => {
    let comp: UserEc2VivoComponent;
    let fixture: ComponentFixture<UserEc2VivoComponent>;
    let service: UserEc2VivoService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [Ec2VivoTestModule],
        declarations: [UserEc2VivoComponent],
      })
        .overrideTemplate(UserEc2VivoComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(UserEc2VivoComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(UserEc2VivoService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new UserEc2Vivo('123')],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.userEc2Vivos && comp.userEc2Vivos[0]).toEqual(jasmine.objectContaining({ id: '123' }));
    });
  });
});
