import axios from 'axios';

import buildPaginationQueryOpts from '@/shared/sort/sorts';

import { ILendReturnRecord } from '@/shared/model/lend-return-record.model';

const baseApiUrl = 'api';

export default class LendReturnRecordService {
  public create(entity: ILendReturnRecord, type): Promise<ILendReturnRecord> {
    return new Promise<ILendReturnRecord>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}/wcc105` + type, entity)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }
}
