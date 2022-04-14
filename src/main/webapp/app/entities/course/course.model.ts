import { IOrder } from 'app/entities/order/order.model';
import { IDeliveryMan } from 'app/entities/delivery-man/delivery-man.model';

export interface ICourse {
  id?: number;
  iDcourse?: number;
  iddelveryman?: number;
  orders?: IOrder[] | null;
  deliveryMan?: IDeliveryMan | null;
}

export class Course implements ICourse {
  constructor(
    public id?: number,
    public iDcourse?: number,
    public iddelveryman?: number,
    public orders?: IOrder[] | null,
    public deliveryMan?: IDeliveryMan | null
  ) {}
}

export function getCourseIdentifier(course: ICourse): number | undefined {
  return course.id;
}
