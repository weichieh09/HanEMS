export interface IPerson {
  id?: number;
  idno?: string | null;
  name?: string;
  phone?: string | null;
  pending?: number | null;
  // wccCode
  reasonId?: string | null;
  // wccCode
  createDate?: Date | null;
  modifyDate?: Date | null;
}

export class Person implements IPerson {
  constructor(
    public id?: number,
    public idno?: string | null,
    public name?: string,
    public phone?: string | null,
    public pending?: number | null,
    // wccCode
    public reasonId?: string | null,
    // wccCode
    public createDate?: Date | null,
    public modifyDate?: Date | null
  ) {}
}
