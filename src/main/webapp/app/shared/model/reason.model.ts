export interface IReason {
  id?: number;
  name?: string;
  description?: string | null;
  createDate?: Date | null;
  modifyDate?: Date | null;
}

export class Reason implements IReason {
  constructor(
    public id?: number,
    public name?: string,
    public description?: string | null,
    public createDate?: Date | null,
    public modifyDate?: Date | null
  ) {}
}
