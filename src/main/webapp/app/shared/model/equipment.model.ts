export interface IEquipment {
  id?: number;
  eqItemId?: number | null;
  name?: string | null;
  description?: string | null;
  createDate?: Date | null;
  modifyDate?: Date | null;
}

export class Equipment implements IEquipment {
  constructor(
    public id?: number,
    public eqItemId?: number | null,
    public name?: string | null,
    public description?: string | null,
    public createDate?: Date | null,
    public modifyDate?: Date | null
  ) {}
}
