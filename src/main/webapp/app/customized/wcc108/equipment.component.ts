import { mixins } from 'vue-class-component';
import { Component, Vue, Inject, Provide } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { IEquipment } from '@/shared/model/equipment.model';
import { IItem, Item } from '@/shared/model/item.model';

import JhiDataUtils from '@/shared/data/data-utils.service';

import EquipmentService from './equipment.service';
import ItemService from './item.service';
import AlertService from '@/shared/alert/alert.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class Equipment extends mixins(JhiDataUtils) {
  @Provide('equipmentService') private equipmentService = () => new EquipmentService();
  @Provide('ItemService') private itemService = () => new ItemService();
  @Inject('alertService') private alertService: () => AlertService;

  private removeId: number = null;
  public itemsPerPage = 20;
  public queryCount: number = null;
  public page = 1;
  public previousPage = 1;
  public propOrder = 'id';
  public reverse = false;
  public totalItems = 0;

  public nameContains = '';
  public itemIdEquals = 0;

  public equipment: IEquipment[] = [];
  public items: IItem[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllEquipments();
    this.retrieveAllItems();
  }

  public clear(): void {
    this.page = 1;
    this.retrieveAllEquipments();
  }

  public retrieveAllEquipments(): void {
    this.isFetching = true;
    const paginationQuery = {
      page: this.page - 1,
      size: this.itemsPerPage,
      sort: this.sort(),
    };
    this.equipmentService()
      .retrieve(this.nameContains, this.itemIdEquals != 0 ? this.itemIdEquals : '', paginationQuery)
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

  public retrieveAllItems(): void {
    this.isFetching = true;
    this.itemService()
      .retrieve()
      .then(
        res => {
          this.items = res.data;
        },
        err => {
          this.isFetching = false;
          this.alertService().showHttpError(this, err.response);
        }
      );
  }
}
