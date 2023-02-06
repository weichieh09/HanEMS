import { Component, Vue, Inject } from 'vue-property-decorator';

import { IEqItem } from '@/shared/model/eq-item.model';
import EqItemService from './eq-item.service';
import AlertService from '@/shared/alert/alert.service';

@Component
export default class EqItemDetails extends Vue {
  @Inject('eqItemService') private eqItemService: () => EqItemService;
  @Inject('alertService') private alertService: () => AlertService;

  public eqItem: IEqItem = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.eqItemId) {
        vm.retrieveEqItem(to.params.eqItemId);
      }
    });
  }

  public retrieveEqItem(eqItemId) {
    this.eqItemService()
      .find(eqItemId)
      .then(res => {
        this.eqItem = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
