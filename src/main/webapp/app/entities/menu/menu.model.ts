import dayjs from 'dayjs/esm';
import { IProduct } from 'app/entities/product/product.model';
import { ICooperative } from 'app/entities/cooperative/cooperative.model';

export interface IMenu {
  id?: number;
  iDmenu?: number;
  iDcooperative?: number;
  lastupdate?: dayjs.Dayjs | null;
  products?: IProduct[] | null;
  cooperative?: ICooperative | null;
}

export class Menu implements IMenu {
  constructor(
    public id?: number,
    public iDmenu?: number,
    public iDcooperative?: number,
    public lastupdate?: dayjs.Dayjs | null,
    public products?: IProduct[] | null,
    public cooperative?: ICooperative | null
  ) {}
}

export function getMenuIdentifier(menu: IMenu): number | undefined {
  return menu.id;
}
