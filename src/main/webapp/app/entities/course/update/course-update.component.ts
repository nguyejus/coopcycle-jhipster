import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import { ICourse, Course } from '../course.model';
import { CourseService } from '../service/course.service';
import { IDeliveryMan } from 'app/entities/delivery-man/delivery-man.model';
import { DeliveryManService } from 'app/entities/delivery-man/service/delivery-man.service';

@Component({
  selector: 'jhi-course-update',
  templateUrl: './course-update.component.html',
})
export class CourseUpdateComponent implements OnInit {
  isSaving = false;

  deliveryMenSharedCollection: IDeliveryMan[] = [];

  editForm = this.fb.group({
    id: [],
    iDcourse: [null, [Validators.required]],
    iddelveryman: [null, [Validators.required]],
    deliveryMan: [],
  });

  constructor(
    protected courseService: CourseService,
    protected deliveryManService: DeliveryManService,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ course }) => {
      this.updateForm(course);

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const course = this.createFromForm();
    if (course.id !== undefined) {
      this.subscribeToSaveResponse(this.courseService.update(course));
    } else {
      this.subscribeToSaveResponse(this.courseService.create(course));
    }
  }

  trackDeliveryManById(_index: number, item: IDeliveryMan): number {
    return item.id!;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICourse>>): void {
    result.pipe(finalize(() => this.onSaveFinalize())).subscribe({
      next: () => this.onSaveSuccess(),
      error: () => this.onSaveError(),
    });
  }

  protected onSaveSuccess(): void {
    this.previousState();
  }

  protected onSaveError(): void {
    // Api for inheritance.
  }

  protected onSaveFinalize(): void {
    this.isSaving = false;
  }

  protected updateForm(course: ICourse): void {
    this.editForm.patchValue({
      id: course.id,
      iDcourse: course.iDcourse,
      iddelveryman: course.iddelveryman,
      deliveryMan: course.deliveryMan,
    });

    this.deliveryMenSharedCollection = this.deliveryManService.addDeliveryManToCollectionIfMissing(
      this.deliveryMenSharedCollection,
      course.deliveryMan
    );
  }

  protected loadRelationshipsOptions(): void {
    this.deliveryManService
      .query()
      .pipe(map((res: HttpResponse<IDeliveryMan[]>) => res.body ?? []))
      .pipe(
        map((deliveryMen: IDeliveryMan[]) =>
          this.deliveryManService.addDeliveryManToCollectionIfMissing(deliveryMen, this.editForm.get('deliveryMan')!.value)
        )
      )
      .subscribe((deliveryMen: IDeliveryMan[]) => (this.deliveryMenSharedCollection = deliveryMen));
  }

  protected createFromForm(): ICourse {
    return {
      ...new Course(),
      id: this.editForm.get(['id'])!.value,
      iDcourse: this.editForm.get(['iDcourse'])!.value,
      iddelveryman: this.editForm.get(['iddelveryman'])!.value,
      deliveryMan: this.editForm.get(['deliveryMan'])!.value,
    };
  }
}
