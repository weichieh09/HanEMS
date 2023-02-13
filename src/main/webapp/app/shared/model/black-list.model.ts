import { IPerson } from '@/shared/model/person.model';

export interface IBlackList {
  id?: number;
  description?: string | null;
  blackDate?: Date;
  createDate?: Date | null;
  modifyDate?: Date | null;
  person?: IPerson | null;
}

export class BlackList implements IBlackList {
  constructor(
    public id?: number,
    public description?: string | null,
    public blackDate?: Date,
    public createDate?: Date | null,
    public modifyDate?: Date | null,
    public person?: IPerson | null
  ) {}
}
