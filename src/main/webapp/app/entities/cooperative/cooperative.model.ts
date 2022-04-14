import { IMenu } from 'app/entities/menu/menu.model';
import { IOrder } from 'app/entities/order/order.model';

export interface ICooperative {
  id?: number;
  iD?: number;
  name?: string | null;
  surname?: string | null;
  telephone?: string | null;
  address?: string;
  menus?: IMenu[] | null;
  orders?: IOrder[] | null;
}

export class Cooperative implements ICooperative {
  constructor(
    public id?: number,
    public iD?: number,
    public name?: string | null,
    public surname?: string | null,
    public telephone?: string | null,
    public address?: string,
    public menus?: IMenu[] | null,
    public orders?: IOrder[] | null
  ) {}
}

export function getCooperativeIdentifier(cooperative: ICooperative): number | undefined {
  return cooperative.id;
}
