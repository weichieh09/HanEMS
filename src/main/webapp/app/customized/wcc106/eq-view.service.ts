import axios from 'axios';

import buildPaginationQueryOpts from '@/shared/sort/sorts';

import { IEqView } from '@/shared/model/eq-view.model';

const baseApiUrl = 'api/eq-views';

export default class EqViewService {
  public find(id: number): Promise<IEqView> {
    return new Promise<IEqView>((resolve, reject) => {
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
