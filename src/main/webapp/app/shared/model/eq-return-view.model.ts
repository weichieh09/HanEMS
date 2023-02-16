export interface IEqReturnView {
  id?: number;
  idno?: string | null;
  name?: string | null;
  itemId?: number | null;
  itemName?: string | null;
  statusId?: number | null;
  statusName?: string | null;
  personId?: number | null;
  personName?: string | null;
}

export class EqReturnView implements IEqReturnView {
  constructor(
    public id?: number,
    public idno?: string | null,
    public name?: string | null,
    public itemId?: number | null,
    public itemName?: string | null,
    public statusId?: number | null,
    public statusName?: string | null,
    public personId?: number | null,
    public personName?: string | null
  ) {}
}
