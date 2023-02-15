import { Component, Vue, Inject, Provide } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { IPerson } from '@/shared/model/person.model';

import PersonService from './person.service';
import AlertService from '@/shared/alert/alert.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class Person extends Vue {
  @Provide('personService') private personService = () => new PersonService();
  @Inject('alertService') private alertService: () => AlertService;

  private removeId: number = null;
  public itemsPerPage = 20;
  public queryCount: number = null;
  public page = 1;
  public previousPage = 1;
  public propOrder = 'id';
  public reverse = false;
  public totalItems = 0;
  private timerRefresh = null;

  public people: IPerson[] = [];

  public isFetching = false;

  // 跳轉到其他頁關閉 timer
  public beforeDestroy(): void {
    clearInterval(this.timerRefresh);
  }

  public created(): void {
    this.retrieveAllPersons();
    this.timerRefresh = setInterval(this.retrieveAllPersons, 5000);
  }

  public mounted(): void {
    // this.retrieveAllPersons();
    // setInterval(this.retrieveAllPersons, 5000);
  }

  public clear(): void {
    this.page = 1;
    this.retrieveAllPersons();
  }

  public retrieveAllPersons(): void {
    this.isFetching = true;
    const paginationQuery = {
      page: this.page - 1,
      size: this.itemsPerPage,
      sort: this.sort(),
    };
    this.personService()
      .retrieve(paginationQuery)
      .then(
        res => {
          this.people = res.data;
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

  public prepareRemove(instance: IPerson): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removePerson(): void {
    this.personService()
      .delete(this.removeId)
      .then(() => {
        const message = this.$t('hanEmsApp.person.deleted', { param: this.removeId });
        this.$bvToast.toast(message.toString(), {
          toaster: 'b-toaster-top-center',
          title: 'Info',
          variant: 'danger',
          solid: true,
          autoHideDelay: 5000,
        });
        this.removeId = null;
        this.retrieveAllPersons();
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
    this.retrieveAllPersons();
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
