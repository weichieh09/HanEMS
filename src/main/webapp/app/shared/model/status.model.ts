export interface IStatus {
  id?: number;
  name?: string;
  description?: string | null;
  createDate?: Date | null;
  modifyDate?: Date | null;
}

export class Status implements IStatus {
  constructor(
    public id?: number,
    public name?: string,
    public description?: string | null,
    public createDate?: Date | null,
    public modifyDate?: Date | null
  ) {}
}
