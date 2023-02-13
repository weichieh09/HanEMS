import axios from 'axios';

import buildPaginationQueryOpts from '@/shared/sort/sorts';

import { IItemView } from '@/shared/model/item-view.model';

const baseApiUrl = 'api/customized/item-views';

export default class ItemViewService {
  public find(id: number): Promise<IItemView> {
    return new Promise<IItemView>((resolve, reject) => {
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
