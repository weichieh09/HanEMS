import { Component, Inject, Provide } from 'vue-property-decorator';

import { mixins } from 'vue-class-component';
import JhiDataUtils from '@/shared/data/data-utils.service';

import { required } from 'vuelidate/lib/validators';
import dayjs from 'dayjs';
import { DATE_TIME_LONG_FORMAT } from '@/shared/date/filters';

import AlertService from '@/shared/alert/alert.service';

import ItemService from '@/entities/item/item.service';
import { IItem } from '@/shared/model/item.model';

import StatusService from '@/entities/status/status.service';
import { IStatus } from '@/shared/model/status.model';

import { IEquipment, Equipment } from '@/shared/model/equipment.model';
import EquipmentService from './equipment.service';

const validations: any = {
  equipment: {
    idno: {},
    name: {
      required,
    },
    description: {},
    createDate: {},
    modifyDate: {},
    item: {
      required,
    },
    status: {
      required,
    },
  },
};

@Component({
  validations,
})
export default class EquipmentUpdate extends mixins(JhiDataUtils) {
  @Provide('equipmentService') private equipmentService = () => new EquipmentService();
  @Inject('alertService') private alertService: () => AlertService;

  public equipment: IEquipment = new Equipment();

  @Provide('itemService') private itemService = () => new ItemService();

  public items: IItem[] = [];

  @Provide('statusService') private statusService = () => new StatusService();

  public statuses: IStatus[] = [];
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.equipmentId) {
        vm.retrieveEquipment(to.params.equipmentId);
      }
      vm.initRelationships();
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
    if (this.equipment.id) {
      this.equipmentService()
        .update(this.equipment)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('hanEmsApp.equipment.updated', { param: param.id });
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
      this.equipmentService()
        .create(this.equipment)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('hanEmsApp.equipment.created', { param: param.id });
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
      this.equipment[field] = dayjs(event.target.value, DATE_TIME_LONG_FORMAT);
    } else {
      this.equipment[field] = null;
    }
  }

  public updateZonedDateTimeField(field, event) {
    if (event.target.value) {
      this.equipment[field] = dayjs(event.target.value, DATE_TIME_LONG_FORMAT);
    } else {
      this.equipment[field] = null;
    }
  }

  public retrieveEquipment(equipmentId): void {
    this.equipmentService()
      .find(equipmentId)
      .then(res => {
        res.createDate = new Date(res.createDate);
        res.modifyDate = new Date(res.modifyDate);
        this.equipment = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {
    this.itemService()
      .retrieve()
      .then(res => {
        this.items = res.data;
      });
    this.statusService()
      .retrieve()
      .then(res => {
        this.statuses = res.data;
      });
  }
}
