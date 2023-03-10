import axios from 'axios';

import buildPaginationQueryOpts from '@/shared/sort/sorts';

import { ILendReturnRecord } from '@/shared/model/lend-return-record.model';

const baseApiUrl = 'api/lend-return-records';

export default class LendReturnRecordService {
  public find(id: number): Promise<ILendReturnRecord> {
    return new Promise<ILendReturnRecord>((resolve, reject) => {
      axios
        .get(`${baseApiUrl}/${id}`)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public retrieve(pIdno, pName, eName, rDate, paginationQuery?: any): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .get(
          'api/wcc105GetAll?' +
            'personIdno.contains=' +
            pIdno +
            '&personName.contains=' +
            pName +
            '&equipmentName.contains=' +
            eName +
            '&returnDate.specified=' +
            rDate +
            `&${buildPaginationQueryOpts(paginationQuery)}`
        )
        .then(res => {
          resolve(res);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public delete(id: number): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .delete(`${baseApiUrl}/${id}`)
        .then(res => {
          resolve(res);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public create(entity: ILendReturnRecord): Promise<ILendReturnRecord> {
    return new Promise<ILendReturnRecord>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}`, entity)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public update(entity: ILendReturnRecord): Promise<ILendReturnRecord> {
    return new Promise<ILendReturnRecord>((resolve, reject) => {
      axios
        .put(`${baseApiUrl}/${entity.id}`, entity)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public partialUpdate(entity: ILendReturnRecord): Promise<ILendReturnRecord> {
    return new Promise<ILendReturnRecord>((resolve, reject) => {
      axios
        .patch(`${baseApiUrl}/${entity.id}`, entity)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }
}
