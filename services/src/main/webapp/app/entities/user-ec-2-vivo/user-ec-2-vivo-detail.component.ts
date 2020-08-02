import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IUserEc2Vivo } from 'app/shared/model/user-ec-2-vivo.model';

@Component({
  selector: 'jhi-user-ec-2-vivo-detail',
  templateUrl: './user-ec-2-vivo-detail.component.html',
})
export class UserEc2VivoDetailComponent implements OnInit {
  userEc2Vivo: IUserEc2Vivo | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ userEc2Vivo }) => (this.userEc2Vivo = userEc2Vivo));
  }

  previousState(): void {
    window.history.back();
  }
}
