import { Component, Vue, Inject, Provide } from 'vue-property-decorator';

import { IItemView } from '@/shared/model/item-view.model';
import ItemViewService from './item-view.service';
import AlertService from '@/shared/alert/alert.service';

@Component
export default class ItemViewDetails extends Vue {
  @Provide('itemViewService') private itemViewService = () => new ItemViewService();
  @Inject('alertService') private alertService: () => AlertService;

  public itemView: IItemView = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.itemViewId) {
        vm.retrieveItemView(to.params.itemViewId);
      }
    });
  }

  public retrieveItemView(itemViewId) {
    this.itemViewService()
      .find(itemViewId)
      .then(res => {
        this.itemView = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
