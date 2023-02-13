import { Component, Inject } from 'vue-property-decorator';

import { mixins } from 'vue-class-component';
import JhiDataUtils from '@/shared/data/data-utils.service';

import { IReason } from '@/shared/model/reason.model';
import ReasonService from './reason.service';
import AlertService from '@/shared/alert/alert.service';

@Component
export default class ReasonDetails extends mixins(JhiDataUtils) {
  @Inject('reasonService') private reasonService: () => ReasonService;
  @Inject('alertService') private alertService: () => AlertService;

  public reason: IReason = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.reasonId) {
        vm.retrieveReason(to.params.reasonId);
      }
    });
  }

  public retrieveReason(reasonId) {
    this.reasonService()
      .find(reasonId)
      .then(res => {
        this.reason = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
