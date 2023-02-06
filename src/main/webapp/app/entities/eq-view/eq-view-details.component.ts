import { Component, Vue, Inject } from 'vue-property-decorator';

import { IEqView } from '@/shared/model/eq-view.model';
import EqViewService from './eq-view.service';
import AlertService from '@/shared/alert/alert.service';

@Component
export default class EqViewDetails extends Vue {
  @Inject('eqViewService') private eqViewService: () => EqViewService;
  @Inject('alertService') private alertService: () => AlertService;

  public eqView: IEqView = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.eqViewId) {
        vm.retrieveEqView(to.params.eqViewId);
      }
    });
  }

  public retrieveEqView(eqViewId) {
    this.eqViewService()
      .find(eqViewId)
      .then(res => {
        this.eqView = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
