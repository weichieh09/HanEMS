import { Component, Inject } from 'vue-property-decorator';

import { mixins } from 'vue-class-component';
import JhiDataUtils from '@/shared/data/data-utils.service';

import { IBlackList } from '@/shared/model/black-list.model';
import BlackListService from './black-list.service';
import AlertService from '@/shared/alert/alert.service';

@Component
export default class BlackListDetails extends mixins(JhiDataUtils) {
  @Inject('blackListService') private blackListService: () => BlackListService;
  @Inject('alertService') private alertService: () => AlertService;

  public blackList: IBlackList = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.blackListId) {
        vm.retrieveBlackList(to.params.blackListId);
      }
    });
  }

  public retrieveBlackList(blackListId) {
    this.blackListService()
      .find(blackListId)
      .then(res => {
        this.blackList = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
