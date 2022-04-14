import { IMenu } from 'app/entities/menu/menu.model';
import { IOrder } from 'app/entities/order/order.model';

export interface IProduct {
  id?: number;
  iDproduct?: number;
  iDmenu?: number;
  name?: string | null;
  price?: number | null;
  disponibility?: number | null;
  menu?: IMenu | null;
  ordercontents?: IOrder[] | null;
}

export class Product implements IProduct {
  constructor(
    public id?: number,
    public iDproduct?: number,
    public iDmenu?: number,
    public name?: string | null,
    public price?: number | null,
    public disponibility?: number | null,
    public menu?: IMenu | null,
    public ordercontents?: IOrder[] | null
  ) {}
}

export function getProductIdentifier(product: IProduct): number | undefined {
  return product.id;
}
