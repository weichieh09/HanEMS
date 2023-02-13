import { Component, Inject } from 'vue-property-decorator';

import { mixins } from 'vue-class-component';
import JhiDataUtils from '@/shared/data/data-utils.service';

import { required } from 'vuelidate/lib/validators';
import dayjs from 'dayjs';
import { DATE_TIME_LONG_FORMAT } from '@/shared/date/filters';

import AlertService from '@/shared/alert/alert.service';

import EquipmentService from '@/entities/equipment/equipment.service';
import { IEquipment } from '@/shared/model/equipment.model';

import PersonService from '@/entities/person/person.service';
import { IPerson } from '@/shared/model/person.model';

import ReasonService from '@/entities/reason/reason.service';
import { IReason } from '@/shared/model/reason.model';

import { ILendReturnRecord, LendReturnRecord } from '@/shared/model/lend-return-record.model';
import LendReturnRecordService from './lend-return-record.service';

const validations: any = {
  lendReturnRecord: {
    lendDate: {
      required,
    },
    returnDate: {},
    description: {},
    createDate: {},
    modifyDate: {},
  },
};

@Component({
  validations,
})
export default class LendReturnRecordUpdate extends mixins(JhiDataUtils) {
  @Inject('lendReturnRecordService') private lendReturnRecordService: () => LendReturnRecordService;
  @Inject('alertService') private alertService: () => AlertService;

  public lendReturnRecord: ILendReturnRecord = new LendReturnRecord();

  @Inject('equipmentService') private equipmentService: () => EquipmentService;

  public equipment: IEquipment[] = [];

  @Inject('personService') private personService: () => PersonService;

  public people: IPerson[] = [];

  @Inject('reasonService') private reasonService: () => ReasonService;

  public reasons: IReason[] = [];
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.lendReturnRecordId) {
        vm.retrieveLendReturnRecord(to.params.lendReturnRecordId);
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
    if (this.lendReturnRecord.id) {
      this.lendReturnRecordService()
        .update(this.lendReturnRecord)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('hanEmsApp.lendReturnRecord.updated', { param: param.id });
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
      this.lendReturnRecordService()
        .create(this.lendReturnRecord)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('hanEmsApp.lendReturnRecord.created', { param: param.id });
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
      this.lendReturnRecord[field] = dayjs(event.target.value, DATE_TIME_LONG_FORMAT);
    } else {
      this.lendReturnRecord[field] = null;
    }
  }

  public updateZonedDateTimeField(field, event) {
    if (event.target.value) {
      this.lendReturnRecord[field] = dayjs(event.target.value, DATE_TIME_LONG_FORMAT);
    } else {
      this.lendReturnRecord[field] = null;
    }
  }

  public retrieveLendReturnRecord(lendReturnRecordId): void {
    this.lendReturnRecordService()
      .find(lendReturnRecordId)
      .then(res => {
        res.lendDate = new Date(res.lendDate);
        res.returnDate = new Date(res.returnDate);
        res.createDate = new Date(res.createDate);
        res.modifyDate = new Date(res.modifyDate);
        this.lendReturnRecord = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {
    this.equipmentService()
      .retrieve()
      .then(res => {
        this.equipment = res.data;
      });
    this.personService()
      .retrieve()
      .then(res => {
        this.people = res.data;
      });
    this.reasonService()
      .retrieve()
      .then(res => {
        this.reasons = res.data;
      });
  }
}
