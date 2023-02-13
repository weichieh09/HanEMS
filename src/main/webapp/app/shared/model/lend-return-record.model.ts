import { IEquipment } from '@/shared/model/equipment.model';
import { IPerson } from '@/shared/model/person.model';
import { IReason } from '@/shared/model/reason.model';

export interface ILendReturnRecord {
  id?: number;
  lendDate?: Date;
  returnDate?: Date | null;
  description?: string | null;
  createDate?: Date | null;
  modifyDate?: Date | null;
  equipment?: IEquipment | null;
  person?: IPerson | null;
  reason?: IReason | null;
}

export class LendReturnRecord implements ILendReturnRecord {
  constructor(
    public id?: number,
    public lendDate?: Date,
    public returnDate?: Date | null,
    public description?: string | null,
    public createDate?: Date | null,
    public modifyDate?: Date | null,
    public equipment?: IEquipment | null,
    public person?: IPerson | null,
    public reason?: IReason | null
  ) {}
}
