import { Component, Vue, Inject } from 'vue-property-decorator';

import AlertService from '@/shared/alert/alert.service';

import { IEquipment, Equipment } from '@/shared/model/equipment.model';
import EquipmentService from './equipment.service';

const validations: any = {
  equipment: {
    name: {},
    totalEle: {},
    rentedEle: {},
    currentEle: {},
  },
};

@Component({
  validations,
})
export default class EquipmentUpdate extends Vue {
  @Inject('equipmentService') private equipmentService: () => EquipmentService;
  @Inject('alertService') private alertService: () => AlertService;

  public equipment: IEquipment = new Equipment();
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.equipmentId) {
        vm.retrieveEquipment(to.params.equipmentId);
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

  public retrieveEquipment(equipmentId): void {
    this.equipmentService()
      .find(equipmentId)
      .then(res => {
        this.equipment = res;
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
