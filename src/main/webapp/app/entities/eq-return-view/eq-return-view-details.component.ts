import { Component, Vue, Inject } from 'vue-property-decorator';

import { IEqReturnView } from '@/shared/model/eq-return-view.model';
import EqReturnViewService from './eq-return-view.service';
import AlertService from '@/shared/alert/alert.service';

@Component
export default class EqReturnViewDetails extends Vue {
  @Inject('eqReturnViewService') private eqReturnViewService: () => EqReturnViewService;
  @Inject('alertService') private alertService: () => AlertService;

  public eqReturnView: IEqReturnView = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.eqReturnViewId) {
        vm.retrieveEqReturnView(to.params.eqReturnViewId);
      }
    });
  }

  public retrieveEqReturnView(eqReturnViewId) {
    this.eqReturnViewService()
      .find(eqReturnViewId)
      .then(res => {
        this.eqReturnView = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
