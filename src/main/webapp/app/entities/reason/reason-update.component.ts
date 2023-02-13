import { Component, Inject } from 'vue-property-decorator';

import { mixins } from 'vue-class-component';
import JhiDataUtils from '@/shared/data/data-utils.service';

import { required } from 'vuelidate/lib/validators';
import dayjs from 'dayjs';
import { DATE_TIME_LONG_FORMAT } from '@/shared/date/filters';

import AlertService from '@/shared/alert/alert.service';

import { IReason, Reason } from '@/shared/model/reason.model';
import ReasonService from './reason.service';

const validations: any = {
  reason: {
    name: {
      required,
    },
    description: {},
    createDate: {},
    modifyDate: {},
  },
};

@Component({
  validations,
})
export default class ReasonUpdate extends mixins(JhiDataUtils) {
  @Inject('reasonService') private reasonService: () => ReasonService;
  @Inject('alertService') private alertService: () => AlertService;

  public reason: IReason = new Reason();
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.reasonId) {
        vm.retrieveReason(to.params.reasonId);
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
    if (this.reason.id) {
      this.reasonService()
        .update(this.reason)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('hanEmsApp.reason.updated', { param: param.id });
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
      this.reasonService()
        .create(this.reason)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('hanEmsApp.reason.created', { param: param.id });
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
      this.reason[field] = dayjs(event.target.value, DATE_TIME_LONG_FORMAT);
    } else {
      this.reason[field] = null;
    }
  }

  public updateZonedDateTimeField(field, event) {
    if (event.target.value) {
      this.reason[field] = dayjs(event.target.value, DATE_TIME_LONG_FORMAT);
    } else {
      this.reason[field] = null;
    }
  }

  public retrieveReason(reasonId): void {
    this.reasonService()
      .find(reasonId)
      .then(res => {
        res.createDate = new Date(res.createDate);
        res.modifyDate = new Date(res.modifyDate);
        this.reason = res;
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
