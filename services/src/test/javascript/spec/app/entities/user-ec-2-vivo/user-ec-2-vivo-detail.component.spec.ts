import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { Ec2VivoTestModule } from '../../../test.module';
import { UserEc2VivoDetailComponent } from 'app/entities/user-ec-2-vivo/user-ec-2-vivo-detail.component';
import { UserEc2Vivo } from 'app/shared/model/user-ec-2-vivo.model';

describe('Component Tests', () => {
  describe('UserEc2Vivo Management Detail Component', () => {
    let comp: UserEc2VivoDetailComponent;
    let fixture: ComponentFixture<UserEc2VivoDetailComponent>;
    const route = ({ data: of({ userEc2Vivo: new UserEc2Vivo('123') }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [Ec2VivoTestModule],
        declarations: [UserEc2VivoDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(UserEc2VivoDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(UserEc2VivoDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load userEc2Vivo on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.userEc2Vivo).toEqual(jasmine.objectContaining({ id: '123' }));
      });
    });
  });
});
