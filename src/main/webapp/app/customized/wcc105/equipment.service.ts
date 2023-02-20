import axios from 'axios';

import buildPaginationQueryOpts from '@/shared/sort/sorts';

import { IEquipment } from '@/shared/model/equipment.model';

const baseApiUrl = 'api/equipment';

export default class EquipmentService {
  public find(id: number): Promise<IEquipment> {
    return new Promise<IEquipment>((resolve, reject) => {
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

  public retrieve(nameContains, itemIdEquals, paginationQuery?: any): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .get(
          baseApiUrl +
            '?name.contains=' +
            nameContains +
            '&itemId.equals=' +
            itemIdEquals +
            '&' +
            `${buildPaginationQueryOpts(paginationQuery)}`
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

  public create(entity: IEquipment): Promise<IEquipment> {
    return new Promise<IEquipment>((resolve, reject) => {
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

  public update(entity: IEquipment): Promise<IEquipment> {
    return new Promise<IEquipment>((resolve, reject) => {
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

  public partialUpdate(entity: IEquipment): Promise<IEquipment> {
    return new Promise<IEquipment>((resolve, reject) => {
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
