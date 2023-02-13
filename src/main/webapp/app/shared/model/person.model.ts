export interface IPerson {
  id?: number;
  idno?: string | null;
  name?: string;
  phone?: string | null;
  createDate?: Date | null;
  modifyDate?: Date | null;
}

export class Person implements IPerson {
  constructor(
    public id?: number,
    public idno?: string | null,
    public name?: string,
    public phone?: string | null,
    public createDate?: Date | null,
    public modifyDate?: Date | null
  ) {}
}
