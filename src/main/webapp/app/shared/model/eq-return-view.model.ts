export interface IEqReturnView {
  id?: number;
  idno?: string | null;
  name?: string | null;
  itemId?: number | null;
  statusId?: number | null;
  personId?: number | null;
}

export class EqReturnView implements IEqReturnView {
  constructor(
    public id?: number,
    public idno?: string | null,
    public name?: string | null,
    public itemId?: number | null,
    public statusId?: number | null,
    public personId?: number | null
  ) {}
}
