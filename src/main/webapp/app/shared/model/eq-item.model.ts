export interface IEqItem {
  id?: number;
  name?: string | null;
  description?: string | null;
  createDate?: Date | null;
  modifyDate?: Date | null;
}

export class EqItem implements IEqItem {
  constructor(
    public id?: number,
    public name?: string | null,
    public description?: string | null,
    public createDate?: Date | null,
    public modifyDate?: Date | null
  ) {}
}
