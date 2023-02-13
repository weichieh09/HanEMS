import { Component, Inject } from 'vue-property-decorator';

import { mixins } from 'vue-class-component';
import JhiDataUtils from '@/shared/data/data-utils.service';

import { required } from 'vuelidate/lib/validators';
import dayjs from 'dayjs';
import { DATE_TIME_LONG_FORMAT } from '@/shared/date/filters';

import AlertService from '@/shared/alert/alert.service';

import { IItem, Item } from '@/shared/model/item.model';
import ItemService from './item.service';

const validations: any = {
  item: {
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
export default class ItemUpdate extends mixins(JhiDataUtils) {
  @Inject('itemService') private itemService: () => ItemService;
  @Inject('alertService') private alertService: () => AlertService;

  public item: IItem = new Item();
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.itemId) {
        vm.retrieveItem(to.params.itemId);
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
    if (this.item.id) {
      this.itemService()
        .update(this.item)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('hanEmsApp.item.updated', { param: param.id });
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
      this.itemService()
        .create(this.item)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('hanEmsApp.item.created', { param: param.id });
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
      this.item[field] = dayjs(event.target.value, DATE_TIME_LONG_FORMAT);
    } else {
      this.item[field] = null;
    }
  }

  public updateZonedDateTimeField(field, event) {
    if (event.target.value) {
      this.item[field] = dayjs(event.target.value, DATE_TIME_LONG_FORMAT);
    } else {
      this.item[field] = null;
    }
  }

  public retrieveItem(itemId): void {
    this.itemService()
      .find(itemId)
      .then(res => {
        res.createDate = new Date(res.createDate);
        res.modifyDate = new Date(res.modifyDate);
        this.item = res;
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
