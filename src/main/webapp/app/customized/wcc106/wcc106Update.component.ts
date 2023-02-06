import { Component, Vue, Inject, Provide } from 'vue-property-decorator';

import dayjs from 'dayjs';
import { DATE_TIME_LONG_FORMAT } from '@/shared/date/filters';

import AlertService from '@/shared/alert/alert.service';

import { IEqView, EqView } from '@/shared/model/eq-view.model';
import { IEqItem } from '@/shared/model/eq-item.model';
import EqViewService from './eq-view.service';
import EqItemService from './eq-item.service';
import Wcc106Service from './wcc106.service';

const validations: any = {
  eqView: {
    eqItemId: {},
    eqName: {},
    eqDescription: {},
    eqCreateDate: {},
    eqModifyDate: {},
  },
};

@Component({
  validations,
})
export default class Wcc106Update extends Vue {
  @Provide('eqViewService') private eqViewService = () => new EqViewService();
  @Provide('wcc106Service') private wcc106Service = () => new Wcc106Service();
  @Inject('alertService') private alertService: () => AlertService;

  public eqView: IEqView = new EqView();
  public isSaving = false;
  public currentLanguage = '';
  private options = null;
  public eqItems: IEqItem[] = [];

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.eqViewId) {
        vm.retrieveEqView(to.params.eqViewId);
      }
    });
  }

  created(): void {
    this.currentLanguage = this.$store.getters.currentLanguage;
    this.$store.watch(
      () => this.$store.getters.currentLanguage,
      () => {
        this.currentLanguage = this.$store.getters.currentLanguage;
      }
    );
    this.getEqItemList();
  }

  public getEqItemList(): void {
    this.wcc106Service()
      .getEqItemList()
      .then(
        res => {
          this.options = res.data;
        },
        err => {
          this.alertService().showHttpError(this, err.response);
        }
      );
  }

  public save(): void {
    this.isSaving = true;
    if (this.eqView.id) {
      this.wcc106Service()
        .update(this.eqView)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('hanEmsApp.eqView.updated', { param: param.id });
          return (this.$root as any).$bvToast.toast(message.toString(), {
            toaster: 'b-toaster-top-center',
            title: 'Info',
            variant: 'info',
            solid: true,
            autoHideDelay: 5000,
          });
        })
        .catch(error => {
          this.isSaving = false;
          this.alertService().showHttpError(this, error.response);
        });
    } else {
      this.wcc106Service()
        .create(this.eqView)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('hanEmsApp.eqView.created', { param: param.id });
          (this.$root as any).$bvToast.toast(message.toString(), {
            toaster: 'b-toaster-top-center',
            title: 'Success',
            variant: 'success',
            solid: true,
            autoHideDelay: 5000,
          });
        })
        .catch(error => {
          this.isSaving = false;
          this.alertService().showHttpError(this, error.response);
        });
    }
  }

  public convertDateTimeFromServer(date: Date): string {
    if (date && dayjs(date).isValid()) {
      return dayjs(date).format(DATE_TIME_LONG_FORMAT);
    }
    return null;
  }

  public updateInstantField(field, event) {
    if (event.target.value) {
      this.eqView[field] = dayjs(event.target.value, DATE_TIME_LONG_FORMAT);
    } else {
      this.eqView[field] = null;
    }
  }

  public updateZonedDateTimeField(field, event) {
    if (event.target.value) {
      this.eqView[field] = dayjs(event.target.value, DATE_TIME_LONG_FORMAT);
    } else {
      this.eqView[field] = null;
    }
  }

  public retrieveEqView(eqViewId): void {
    this.eqViewService()
      .find(eqViewId)
      .then(res => {
        res.createDate = new Date(res.createDate);
        res.modifyDate = new Date(res.modifyDate);
        this.eqView = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {}
}
