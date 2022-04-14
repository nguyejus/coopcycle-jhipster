import { IOrder } from 'app/entities/order/order.model';

export interface ICustomer {
  id?: number;
  iD?: number;
  name?: string | null;
  surname?: string | null;
  telephone?: string | null;
  address?: string;
  orders?: IOrder[] | null;
}

export class Customer implements ICustomer {
  constructor(
    public id?: number,
    public iD?: number,
    public name?: string | null,
    public surname?: string | null,
    public telephone?: string | null,
    public address?: string,
    public orders?: IOrder[] | null
  ) {}
}

export function getCustomerIdentifier(customer: ICustomer): number | undefined {
  return customer.id;
}
