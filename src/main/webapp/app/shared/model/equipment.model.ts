import { IItem } from '@/shared/model/item.model';
import { IStatus } from '@/shared/model/status.model';

export interface IEquipment {
  id?: number;
  name?: string;
  description?: string | null;
  createDate?: Date | null;
  modifyDate?: Date | null;
  item?: IItem | null;
  status?: IStatus | null;
}

export class Equipment implements IEquipment {
  constructor(
    public id?: number,
    public name?: string,
    public description?: string | null,
    public createDate?: Date | null,
    public modifyDate?: Date | null,
    public item?: IItem | null,
    public status?: IStatus | null
  ) {}
}
