import { ICourse } from 'app/entities/course/course.model';

export interface IDeliveryMan {
  id?: number;
  iD?: number;
  name?: string | null;
  surname?: string | null;
  telephone?: string | null;
  vehicule?: string | null;
  latitude?: number;
  longitude?: number;
  courses?: ICourse[] | null;
}

export class DeliveryMan implements IDeliveryMan {
  constructor(
    public id?: number,
    public iD?: number,
    public name?: string | null,
    public surname?: string | null,
    public telephone?: string | null,
    public vehicule?: string | null,
    public latitude?: number,
    public longitude?: number,
    public courses?: ICourse[] | null
  ) {}
}

export function getDeliveryManIdentifier(deliveryMan: IDeliveryMan): number | undefined {
  return deliveryMan.id;
}
