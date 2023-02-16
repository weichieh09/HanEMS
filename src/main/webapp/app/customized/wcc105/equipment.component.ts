import { mixins } from 'vue-class-component';
import { Component, Vue, Inject, Provide } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { IEquipment, Equipment } from '@/shared/model/equipment.model';
import { ILendReturnRecord, LendReturnRecord } from '@/shared/model/lend-return-record.model';
import { IPerson, Person } from '@/shared/model/person.model';

import JhiDataUtils from '@/shared/data/data-utils.service';

import EquipmentService from './equipment.service';
import LendReturnRecordService from './lend-return-record.service';
import PersonService from './person.service';
import AlertService from '@/shared/alert/alert.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class MyEquipment extends mixins(JhiDataUtils) {
  @Provide('equipmentService') private equipmentService = () => new EquipmentService();
  @Provide('LendReturnRecordService') private lendReturnRecordService = () => new LendReturnRecordService();
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
  public personId = null;
  public personPending = null;

  public equipment: IEquipment[] = [];
  public lendReturnRecord: ILendReturnRecord = new LendReturnRecord();
  public person: IPerson[] = [];

  public isFetching = false;
  public isSaving = false;

  beforeRouteEnter(to, from, next) {
    next(vm => {
      console.log('personId: ' + to.params.personId + ', personPending: ' + to.params.personPending);
      vm.personId = to.params.personId;
      vm.personPending = to.params.personPending;
      // if (to.params.personId) {
      //   vm.retrievePerson(to.params.personId);
      // }
    });
  }

  public mounted(): void {
    this.retrieveAllEquipments();
  }

  public clear(): void {
    this.page = 1;
    this.retrieveAllEquipments();
  }

  public rentEq(equipmentId): void {
    var person = new Person();
    person.id = this.personId;

    var equipment = new Equipment();
    equipment.id = Number(equipmentId);

    var lendReturnRecord = new LendReturnRecord();
    lendReturnRecord.lendDate = new Date();
    lendReturnRecord.person = person;
    lendReturnRecord.equipment = equipment;
    this.lendReturnRecord = lendReturnRecord;

    this.saveRecord('Rent');
  }

  public returnEq(equipmentId): void {
    var person = new Person();
    person.id = this.personId;

    var equipment = new Equipment();
    equipment.id = Number(equipmentId);

    var lendReturnRecord = new LendReturnRecord();
    lendReturnRecord.lendDate = new Date();
    lendReturnRecord.returnDate = new Date();
    lendReturnRecord.person = person;
    lendReturnRecord.equipment = equipment;
    this.lendReturnRecord = lendReturnRecord;

    this.saveRecord('Return');
  }

  public retrieveAllEquipments(): void {
    if (this.personPending == 1) {
      this.isFetching = true;
      const paginationQuery = {
        page: this.page - 1,
        size: this.itemsPerPage,
        sort: this.sort(),
      };
      this.equipmentService()
        .retrieve(paginationQuery)
        .then(
          res => {
            this.equipment = res.data;
            this.totalItems = Number(res.headers['x-total-count']);
            this.queryCount = this.totalItems;
            this.isFetching = false;
          },
          err => {
            this.isFetching = false;
            this.alertService().showHttpError(this, err.response);
          }
        );
    } else {
    }
  }

  public handleSyncList(): void {
    this.clear();
  }

  public prepareRemove(instance: IEquipment): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeEquipment(): void {
    this.equipmentService()
      .delete(this.removeId)
      .then(() => {
        const message = this.$t('hanEmsApp.equipment.deleted', { param: this.removeId });
        this.$bvToast.toast(message.toString(), {
          toaster: 'b-toaster-top-center',
          title: 'Info',
          variant: 'danger',
          solid: true,
          autoHideDelay: 5000,
        });
        this.removeId = null;
        this.retrieveAllEquipments();
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
    this.retrieveAllEquipments();
  }

  public changeOrder(propOrder): void {
    this.propOrder = propOrder;
    this.reverse = !this.reverse;
    this.transition();
  }

  public closeDialog(): void {
    (<any>this.$refs.removeEntity).hide();
  }

  public finish(): void {
    var person = new Person();
    person.id = this.personId;
    person.name = '';
    person.pending = 0;
    this.person = person;
    this.savePerson();
  }

  public saveRecord(type): void {
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
        .create(this.lendReturnRecord, type)
        .then(param => {
          this.isSaving = false;
          // this.$router.go(-1);
          this.retrieveAllEquipments();
          const message = this.$t('操作成功!');
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

  public savePerson(): void {
    this.isSaving = true;
    if (this.person.id) {
      this.personService()
        .update(this.person)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('操作成功!');
          return (this.$root as any).$bvToast.toast(message.toString(), {
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
    } else {
      this.personService()
        .create(this.person)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('借用登記成功，識別碼為 ' + param.id + ' ');
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
}
