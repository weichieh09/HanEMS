import { Component, Inject } from 'vue-property-decorator';

import { mixins } from 'vue-class-component';
import JhiDataUtils from '@/shared/data/data-utils.service';

import { IEquipment } from '@/shared/model/equipment.model';
import EquipmentService from './equipment.service';
import AlertService from '@/shared/alert/alert.service';

@Component
export default class EquipmentDetails extends mixins(JhiDataUtils) {
  @Inject('equipmentService') private equipmentService: () => EquipmentService;
  @Inject('alertService') private alertService: () => AlertService;

  public equipment: IEquipment = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.equipmentId) {
        vm.retrieveEquipment(to.params.equipmentId);
      }
    });
  }

  public retrieveEquipment(equipmentId) {
    this.equipmentService()
      .find(equipmentId)
      .then(res => {
        this.equipment = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
