import axios from 'axios';

import buildPaginationQueryOpts from '@/shared/sort/sorts';

import { IEqReturnView } from '@/shared/model/eq-return-view.model';

const baseApiUrl = 'api/eq-return-views';

export default class EqReturnViewService {
  public find(id: number): Promise<IEqReturnView> {
    return new Promise<IEqReturnView>((resolve, reject) => {
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

  public retrieve(paginationQuery?: any): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .get(baseApiUrl + `?${buildPaginationQueryOpts(paginationQuery)}`)
        .then(res => {
          resolve(res);
        })
        .catch(err => {
          reject(err);
        });
    });
  }
}
