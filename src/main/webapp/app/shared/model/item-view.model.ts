export interface IItemView {
  id?: number;
  name?: string | null;
  total?: string | null;
  ready?: string | null;
  working?: string | null;
  canNot?: string | null;
}

export class ItemView implements IItemView {
  constructor(
    public id?: number,
    public name?: string | null,
    public total?: string | null,
    public ready?: string | null,
    public working?: string | null,
    public canNot?: string | null
  ) {}
}
