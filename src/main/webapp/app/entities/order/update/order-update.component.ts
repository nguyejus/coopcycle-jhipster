import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import dayjs from 'dayjs/esm';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';

import { IOrder, Order } from '../order.model';
import { OrderService } from '../service/order.service';
import { IProduct } from 'app/entities/product/product.model';
import { ProductService } from 'app/entities/product/service/product.service';
import { ICourse } from 'app/entities/course/course.model';
import { CourseService } from 'app/entities/course/service/course.service';
import { ICustomer } from 'app/entities/customer/customer.model';
import { CustomerService } from 'app/entities/customer/service/customer.service';
import { ICooperative } from 'app/entities/cooperative/cooperative.model';
import { CooperativeService } from 'app/entities/cooperative/service/cooperative.service';
import { State } from 'app/entities/enumerations/state.model';

@Component({
  selector: 'jhi-order-update',
  templateUrl: './order-update.component.html',
})
export class OrderUpdateComponent implements OnInit {
  isSaving = false;
  stateValues = Object.keys(State);

  productsSharedCollection: IProduct[] = [];
  coursesSharedCollection: ICourse[] = [];
  customersSharedCollection: ICustomer[] = [];
  cooperativesSharedCollection: ICooperative[] = [];

  editForm = this.fb.group({
    id: [],
    iDorder: [null, [Validators.required]],
    iDcooperative: [null, [Validators.required]],
    iDcustomer: [null, [Validators.required]],
    iDcourse: [null, [Validators.required]],
    iDproduct: [null, [Validators.required]],
    totalPrice: [null, [Validators.min(3), Validators.max(300)]],
    date: [],
    state: [],
    quantityAsked: [],
    productAvailable: [],
    products: [null, Validators.required],
    course: [],
    customer: [],
    cooperative: [],
  });

  constructor(
    protected orderService: OrderService,
    protected productService: ProductService,
    protected courseService: CourseService,
    protected customerService: CustomerService,
    protected cooperativeService: CooperativeService,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ order }) => {
      if (order.id === undefined) {
        const today = dayjs().startOf('day');
        order.date = today;
      }

      this.updateForm(order);

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const order = this.createFromForm();
    if (order.id !== undefined) {
      this.subscribeToSaveResponse(this.orderService.update(order));
    } else {
      this.subscribeToSaveResponse(this.orderService.create(order));
    }
  }

  trackProductById(_index: number, item: IProduct): number {
    return item.id!;
  }

  trackCourseById(_index: number, item: ICourse): number {
    return item.id!;
  }

  trackCustomerById(_index: number, item: ICustomer): number {
    return item.id!;
  }

  trackCooperativeById(_index: number, item: ICooperative): number {
    return item.id!;
  }

  getSelectedProduct(option: IProduct, selectedVals?: IProduct[]): IProduct {
    if (selectedVals) {
      for (const selectedVal of selectedVals) {
        if (option.id === selectedVal.id) {
          return selectedVal;
        }
      }
    }
    return option;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IOrder>>): void {
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

  protected updateForm(order: IOrder): void {
    this.editForm.patchValue({
      id: order.id,
      iDorder: order.iDorder,
      iDcooperative: order.iDcooperative,
      iDcustomer: order.iDcustomer,
      iDcourse: order.iDcourse,
      iDproduct: order.iDproduct,
      totalPrice: order.totalPrice,
      date: order.date ? order.date.format(DATE_TIME_FORMAT) : null,
      state: order.state,
      quantityAsked: order.quantityAsked,
      productAvailable: order.productAvailable,
      products: order.products,
      course: order.course,
      customer: order.customer,
      cooperative: order.cooperative,
    });

    this.productsSharedCollection = this.productService.addProductToCollectionIfMissing(
      this.productsSharedCollection,
      ...(order.products ?? [])
    );
    this.coursesSharedCollection = this.courseService.addCourseToCollectionIfMissing(this.coursesSharedCollection, order.course);
    this.customersSharedCollection = this.customerService.addCustomerToCollectionIfMissing(this.customersSharedCollection, order.customer);
    this.cooperativesSharedCollection = this.cooperativeService.addCooperativeToCollectionIfMissing(
      this.cooperativesSharedCollection,
      order.cooperative
    );
  }

  protected loadRelationshipsOptions(): void {
    this.productService
      .query()
      .pipe(map((res: HttpResponse<IProduct[]>) => res.body ?? []))
      .pipe(
        map((products: IProduct[]) =>
          this.productService.addProductToCollectionIfMissing(products, ...(this.editForm.get('products')!.value ?? []))
        )
      )
      .subscribe((products: IProduct[]) => (this.productsSharedCollection = products));

    this.courseService
      .query()
      .pipe(map((res: HttpResponse<ICourse[]>) => res.body ?? []))
      .pipe(map((courses: ICourse[]) => this.courseService.addCourseToCollectionIfMissing(courses, this.editForm.get('course')!.value)))
      .subscribe((courses: ICourse[]) => (this.coursesSharedCollection = courses));

    this.customerService
      .query()
      .pipe(map((res: HttpResponse<ICustomer[]>) => res.body ?? []))
      .pipe(
        map((customers: ICustomer[]) =>
          this.customerService.addCustomerToCollectionIfMissing(customers, this.editForm.get('customer')!.value)
        )
      )
      .subscribe((customers: ICustomer[]) => (this.customersSharedCollection = customers));

    this.cooperativeService
      .query()
      .pipe(map((res: HttpResponse<ICooperative[]>) => res.body ?? []))
      .pipe(
        map((cooperatives: ICooperative[]) =>
          this.cooperativeService.addCooperativeToCollectionIfMissing(cooperatives, this.editForm.get('cooperative')!.value)
        )
      )
      .subscribe((cooperatives: ICooperative[]) => (this.cooperativesSharedCollection = cooperatives));
  }

  protected createFromForm(): IOrder {
    return {
      ...new Order(),
      id: this.editForm.get(['id'])!.value,
      iDorder: this.editForm.get(['iDorder'])!.value,
      iDcooperative: this.editForm.get(['iDcooperative'])!.value,
      iDcustomer: this.editForm.get(['iDcustomer'])!.value,
      iDcourse: this.editForm.get(['iDcourse'])!.value,
      iDproduct: this.editForm.get(['iDproduct'])!.value,
      totalPrice: this.editForm.get(['totalPrice'])!.value,
      date: this.editForm.get(['date'])!.value ? dayjs(this.editForm.get(['date'])!.value, DATE_TIME_FORMAT) : undefined,
      state: this.editForm.get(['state'])!.value,
      quantityAsked: this.editForm.get(['quantityAsked'])!.value,
      productAvailable: this.editForm.get(['productAvailable'])!.value,
      products: this.editForm.get(['products'])!.value,
      course: this.editForm.get(['course'])!.value,
      customer: this.editForm.get(['customer'])!.value,
      cooperative: this.editForm.get(['cooperative'])!.value,
    };
  }
}
