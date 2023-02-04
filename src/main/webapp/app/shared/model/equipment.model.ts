export interface IEquipment {
  id?: number;
  name?: string | null;
  totalEle?: number | null;
  rentedEle?: number | null;
  currentEle?: number | null;
}

export class Equipment implements IEquipment {
  constructor(
    public id?: number,
    public name?: string | null,
    public totalEle?: number | null,
    public rentedEle?: number | null,
    public currentEle?: number | null
  ) {}
}
