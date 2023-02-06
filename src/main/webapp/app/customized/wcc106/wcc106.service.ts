import axios from 'axios';

import buildPaginationQueryOpts from '@/shared/sort/sorts';

import { IEquipment } from '@/shared/model/equipment.model';
import { IEqView } from '@/shared/model/eq-view.model';

const baseApiUrl = 'api/wcc106';

export default class Wcc106Service {
  public create(entity: IEqView): Promise<IEqView> {
    return new Promise<IEqView>((resolve, reject) => {
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

  public update(entity: IEqView): Promise<IEqView> {
    return new Promise<IEqView>((resolve, reject) => {
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
}
