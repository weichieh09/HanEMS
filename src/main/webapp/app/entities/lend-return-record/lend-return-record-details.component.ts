import { Component, Inject } from 'vue-property-decorator';

import { mixins } from 'vue-class-component';
import JhiDataUtils from '@/shared/data/data-utils.service';

import { ILendReturnRecord } from '@/shared/model/lend-return-record.model';
import LendReturnRecordService from './lend-return-record.service';
import AlertService from '@/shared/alert/alert.service';

@Component
export default class LendReturnRecordDetails extends mixins(JhiDataUtils) {
  @Inject('lendReturnRecordService') private lendReturnRecordService: () => LendReturnRecordService;
  @Inject('alertService') private alertService: () => AlertService;

  public lendReturnRecord: ILendReturnRecord = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.lendReturnRecordId) {
        vm.retrieveLendReturnRecord(to.params.lendReturnRecordId);
      }
    });
  }

  public retrieveLendReturnRecord(lendReturnRecordId) {
    this.lendReturnRecordService()
      .find(lendReturnRecordId)
      .then(res => {
        this.lendReturnRecord = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
