import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IUserEc2Vivo, UserEc2Vivo } from 'app/shared/model/user-ec-2-vivo.model';
import { UserEc2VivoService } from './user-ec-2-vivo.service';

@Component({
  selector: 'jhi-user-ec-2-vivo-update',
  templateUrl: './user-ec-2-vivo-update.component.html',
})
export class UserEc2VivoUpdateComponent implements OnInit {
  isSaving = false;
  registerDateDp: any;

  editForm = this.fb.group({
    id: [],
    name: [],
    lastName: [],
    phone: [],
    email: [],
    userName: [],
    userPassword: [],
    loginSource: [],
    registerDate: [],
  });

  constructor(protected userEc2VivoService: UserEc2VivoService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ userEc2Vivo }) => {
      this.updateForm(userEc2Vivo);
    });
  }

  updateForm(userEc2Vivo: IUserEc2Vivo): void {
    this.editForm.patchValue({
      id: userEc2Vivo.id,
      name: userEc2Vivo.name,
      lastName: userEc2Vivo.lastName,
      phone: userEc2Vivo.phone,
      email: userEc2Vivo.email,
      userName: userEc2Vivo.userName,
      userPassword: userEc2Vivo.userPassword,
      loginSource: userEc2Vivo.loginSource,
      registerDate: userEc2Vivo.registerDate,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const userEc2Vivo = this.createFromForm();
    if (userEc2Vivo.id !== undefined) {
      this.subscribeToSaveResponse(this.userEc2VivoService.update(userEc2Vivo));
    } else {
      this.subscribeToSaveResponse(this.userEc2VivoService.create(userEc2Vivo));
    }
  }

  private createFromForm(): IUserEc2Vivo {
    return {
      ...new UserEc2Vivo(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
      lastName: this.editForm.get(['lastName'])!.value,
      phone: this.editForm.get(['phone'])!.value,
      email: this.editForm.get(['email'])!.value,
      userName: this.editForm.get(['userName'])!.value,
      userPassword: this.editForm.get(['userPassword'])!.value,
      loginSource: this.editForm.get(['loginSource'])!.value,
      registerDate: this.editForm.get(['registerDate'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IUserEc2Vivo>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }
}
