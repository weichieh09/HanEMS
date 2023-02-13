import { Component, Inject } from 'vue-property-decorator';

import { mixins } from 'vue-class-component';
import JhiDataUtils from '@/shared/data/data-utils.service';

import { required } from 'vuelidate/lib/validators';
import dayjs from 'dayjs';
import { DATE_TIME_LONG_FORMAT } from '@/shared/date/filters';

import AlertService from '@/shared/alert/alert.service';

import { IStatus, Status } from '@/shared/model/status.model';
import StatusService from './status.service';

const validations: any = {
  status: {
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
export default class StatusUpdate extends mixins(JhiDataUtils) {
  @Inject('statusService') private statusService: () => StatusService;
  @Inject('alertService') private alertService: () => AlertService;

  public status: IStatus = new Status();
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.statusId) {
        vm.retrieveStatus(to.params.statusId);
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
    if (this.status.id) {
      this.statusService()
        .update(this.status)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('hanEmsApp.status.updated', { param: param.id });
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
      this.statusService()
        .create(this.status)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('hanEmsApp.status.created', { param: param.id });
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
      this.status[field] = dayjs(event.target.value, DATE_TIME_LONG_FORMAT);
    } else {
      this.status[field] = null;
    }
  }

  public updateZonedDateTimeField(field, event) {
    if (event.target.value) {
      this.status[field] = dayjs(event.target.value, DATE_TIME_LONG_FORMAT);
    } else {
      this.status[field] = null;
    }
  }

  public retrieveStatus(statusId): void {
    this.statusService()
      .find(statusId)
      .then(res => {
        res.createDate = new Date(res.createDate);
        res.modifyDate = new Date(res.modifyDate);
        this.status = res;
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
