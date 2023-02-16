import { IItem } from '@/shared/model/item.model';
import { IStatus } from '@/shared/model/status.model';

export interface IEquipment {
  id?: number;
  idno?: string | null;
  name?: string;
  description?: string | null;
  createDate?: Date | null;
  modifyDate?: Date | null;
  item?: IItem | null;
  status?: IStatus | null;
  itemId?: number;
  itemName?: string;
  statusId?: number;
  statusName?: string;
}

export class Equipment implements IEquipment {
  constructor(
    public id?: number,
    public idno?: string | null,
    public name?: string,
    public description?: string | null,
    public createDate?: Date | null,
    public modifyDate?: Date | null,
    public item?: IItem | null,
    public status?: IStatus | null,
    public itemId?: number,
    public itemName?: string,
    public statusId?: number,
    public statusName?: string
  ) {}
}
