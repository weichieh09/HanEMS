import Component from 'vue-class-component';
import { Inject, Provide, Vue } from 'vue-property-decorator';
import AlertService from '@/shared/alert/alert.service';

/* wccCode */
import Vue2Filters from 'vue2-filters';
import { IEqItem } from '@/shared/model/eq-item.model';
import EqItemService from './eq-item.service';
/* wccCode */

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class Wcc105 extends Vue {
  @Provide('eqItemService') private eqItemService = () => new EqItemService();
  @Inject('alertService') private alertService: () => AlertService;

  private removeId: number = null;
  public itemsPerPage = 20;
  public queryCount: number = null;
  public page = 1;
  public previousPage = 1;
  public propOrder = 'id';
  public reverse = false;
  public totalItems = 0;

  public eqItems: IEqItem[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllEqItems();
  }

  public clear(): void {
    this.page = 1;
    this.retrieveAllEqItems();
  }

  public retrieveAllEqItems(): void {
    this.isFetching = true;
    const paginationQuery = {
      page: this.page - 1,
      size: this.itemsPerPage,
      sort: this.sort(),
    };
    this.eqItemService()
      .retrieve(paginationQuery)
      .then(
        res => {
          this.eqItems = res.data;
          this.totalItems = Number(res.headers['x-total-count']);
          this.queryCount = this.totalItems;
          this.isFetching = false;
        },
        err => {
          this.isFetching = false;
          this.alertService().showHttpError(this, err.response);
        }
      );
  }

  public handleSyncList(): void {
    this.clear();
  }

  public prepareRemove(instance: IEqItem): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeEqItem(): void {
    this.eqItemService()
      .delete(this.removeId)
      .then(() => {
        const message = this.$t('hanEmsApp.eqItem.deleted', { param: this.removeId });
        this.$bvToast.toast(message.toString(), {
          toaster: 'b-toaster-top-center',
          title: 'Info',
          variant: 'danger',
          solid: true,
          autoHideDelay: 5000,
        });
        this.removeId = null;
        this.retrieveAllEqItems();
        this.closeDialog();
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public sort(): Array<any> {
    const result = [this.propOrder + ',' + (this.reverse ? 'desc' : 'asc')];
    if (this.propOrder !== 'id') {
      result.push('id');
    }
    return result;
  }

  public loadPage(page: number): void {
    if (page !== this.previousPage) {
      this.previousPage = page;
      this.transition();
    }
  }

  public transition(): void {
    this.retrieveAllEqItems();
  }

  public changeOrder(propOrder): void {
    this.propOrder = propOrder;
    this.reverse = !this.reverse;
    this.transition();
  }

  public closeDialog(): void {
    (<any>this.$refs.removeEntity).hide();
  }
}
