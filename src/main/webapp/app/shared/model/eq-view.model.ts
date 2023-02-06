export interface IEqView {
  id?: number;
  eqItemId?: number | null;
  eqItemName?: string | null;
  eqName?: string | null;
  eqDescription?: string | null;
  eqCreateDate?: Date | null;
  eqModifyDate?: Date | null;
}

export class EqView implements IEqView {
  constructor(
    public id?: number,
    public eqItemId?: number | null,
    public eqItemName?: string | null,
    public eqName?: string | null,
    public eqDescription?: string | null,
    public eqCreateDate?: Date | null,
    public eqModifyDate?: Date | null
  ) {}
}
