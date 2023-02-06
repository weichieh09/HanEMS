import { Component, Vue, Inject } from 'vue-property-decorator';

import dayjs from 'dayjs';
import { DATE_TIME_LONG_FORMAT } from '@/shared/date/filters';

import AlertService from '@/shared/alert/alert.service';

import { IEqItem, EqItem } from '@/shared/model/eq-item.model';
import EqItemService from './eq-item.service';

const validations: any = {
  eqItem: {
    name: {},
    description: {},
    createDate: {},
    modifyDate: {},
  },
};

@Component({
  validations,
})
export default class EqItemUpdate extends Vue {
  @Inject('eqItemService') private eqItemService: () => EqItemService;
  @Inject('alertService') private alertService: () => AlertService;

  public eqItem: IEqItem = new EqItem();
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.eqItemId) {
        vm.retrieveEqItem(to.params.eqItemId);
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
  }

  public save(): void {
    this.isSaving = true;
    if (this.eqItem.id) {
      this.eqItemService()
        .update(this.eqItem)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('hanEmsApp.eqItem.updated', { param: param.id });
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
      this.eqItemService()
        .create(this.eqItem)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('hanEmsApp.eqItem.created', { param: param.id });
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
      this.eqItem[field] = dayjs(event.target.value, DATE_TIME_LONG_FORMAT);
    } else {
      this.eqItem[field] = null;
    }
  }

  public updateZonedDateTimeField(field, event) {
    if (event.target.value) {
      this.eqItem[field] = dayjs(event.target.value, DATE_TIME_LONG_FORMAT);
    } else {
      this.eqItem[field] = null;
    }
  }

  public retrieveEqItem(eqItemId): void {
    this.eqItemService()
      .find(eqItemId)
      .then(res => {
        res.createDate = new Date(res.createDate);
        res.modifyDate = new Date(res.modifyDate);
        this.eqItem = res;
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
