import dayjs from 'dayjs/esm';
import { IOrderContent } from 'app/entities/order-content/order-content.model';
import { ICourse } from 'app/entities/course/course.model';
import { ICustomer } from 'app/entities/customer/customer.model';
import { ICooperative } from 'app/entities/cooperative/cooperative.model';
import { State } from 'app/entities/enumerations/state.model';

export interface IOrder {
  id?: number;
  iDorder?: number;
  iDcooperative?: number;
  iDcustomer?: number;
  iDcourse?: number;
  totalPrice?: number | null;
  date?: dayjs.Dayjs | null;
  state?: State | null;
  orderContents?: IOrderContent[] | null;
  course?: ICourse | null;
  customer?: ICustomer | null;
  cooperative?: ICooperative | null;
}

export class Order implements IOrder {
  constructor(
    public id?: number,
    public iDorder?: number,
    public iDcooperative?: number,
    public iDcustomer?: number,
    public iDcourse?: number,
    public totalPrice?: number | null,
    public date?: dayjs.Dayjs | null,
    public state?: State | null,
    public orderContents?: IOrderContent[] | null,
    public course?: ICourse | null,
    public customer?: ICustomer | null,
    public cooperative?: ICooperative | null
  ) {}
}

export function getOrderIdentifier(order: IOrder): number | undefined {
  return order.id;
}
