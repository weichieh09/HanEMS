import { Component, Inject, Provide } from 'vue-property-decorator';

import { mixins } from 'vue-class-component';
import JhiDataUtils from '@/shared/data/data-utils.service';

import { required } from 'vuelidate/lib/validators';
import dayjs from 'dayjs';
import { DATE_TIME_LONG_FORMAT } from '@/shared/date/filters';

import AlertService from '@/shared/alert/alert.service';

import PersonService from '@/entities/person/person.service';
import { IPerson } from '@/shared/model/person.model';

import { IBlackList, BlackList } from '@/shared/model/black-list.model';
import BlackListService from './black-list.service';

const validations: any = {
  blackList: {
    description: {},
    blackDate: {
      required,
    },
    createDate: {},
    modifyDate: {},
    personIdno: {
      required,
    },
    personName: {},
  },
};

@Component({
  validations,
})
export default class BlackListUpdate extends mixins(JhiDataUtils) {
  @Provide('blackListService') private blackListService = () => new BlackListService();
  @Inject('alertService') private alertService: () => AlertService;

  public blackList: IBlackList = new BlackList();

  @Provide('personService') private personService = () => new PersonService();

  public people: IPerson[] = [];
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.blackListId) {
        vm.retrieveBlackList(to.params.blackListId);
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
    if (this.blackList.id) {
      this.blackListService()
        .update(this.blackList)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('hanEmsApp.blackList.updated', { param: param.id });
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
      this.blackListService()
        .create(this.blackList)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('hanEmsApp.blackList.created', { param: param.id });
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
      this.blackList[field] = dayjs(event.target.value, DATE_TIME_LONG_FORMAT);
    } else {
      this.blackList[field] = null;
    }
  }

  public updateZonedDateTimeField(field, event) {
    if (event.target.value) {
      this.blackList[field] = dayjs(event.target.value, DATE_TIME_LONG_FORMAT);
    } else {
      this.blackList[field] = null;
    }
  }

  public retrieveBlackList(blackListId): void {
    this.blackListService()
      .find(blackListId)
      .then(res => {
        res.blackDate = new Date(res.blackDate);
        res.createDate = new Date(res.createDate);
        res.modifyDate = new Date(res.modifyDate);
        this.blackList = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {
    this.personService()
      .retrieve()
      .then(res => {
        this.people = res.data;
      });
  }
}
