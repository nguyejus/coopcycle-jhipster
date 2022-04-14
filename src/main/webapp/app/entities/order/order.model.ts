import dayjs from 'dayjs/esm';
import { IProduct } from 'app/entities/product/product.model';
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
  iDproduct?: number;
  totalPrice?: number | null;
  date?: dayjs.Dayjs | null;
  state?: State | null;
  quantityAsked?: number | null;
  productAvailable?: boolean | null;
  products?: IProduct[];
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
    public iDproduct?: number,
    public totalPrice?: number | null,
    public date?: dayjs.Dayjs | null,
    public state?: State | null,
    public quantityAsked?: number | null,
    public productAvailable?: boolean | null,
    public products?: IProduct[],
    public course?: ICourse | null,
    public customer?: ICustomer | null,
    public cooperative?: ICooperative | null
  ) {
    this.productAvailable = this.productAvailable ?? false;
  }
}

export function getOrderIdentifier(order: IOrder): number | undefined {
  return order.id;
}
