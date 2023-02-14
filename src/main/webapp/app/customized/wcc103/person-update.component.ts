import { Component, Vue, Inject, Provide } from 'vue-property-decorator';

import { required } from 'vuelidate/lib/validators';
import dayjs from 'dayjs';
import { DATE_TIME_LONG_FORMAT } from '@/shared/date/filters';

import AlertService from '@/shared/alert/alert.service';

import { IPerson, Person } from '@/shared/model/person.model';
import { IReason } from '@/shared/model/reason.model';
import PersonService from './person.service';
import ReasonService from './reason.service';

const validations: any = {
  person: {
    idno: {},
    name: {
      required,
    },
    phone: {},
    pending: {},
    createDate: {},
    modifyDate: {},
  },
};

@Component({
  validations,
})
export default class PersonUpdate extends Vue {
  @Provide('personService') private personService = () => new PersonService();
  @Provide('reasonService') private reasonService = () => new ReasonService();
  @Inject('alertService') private alertService: () => AlertService;

  public person: IPerson = new Person();
  public isSaving = false;
  public currentLanguage = '';

  public reasons: IReason[] = [];

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.personId) {
        vm.retrievePerson(to.params.personId);
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
    // wccCode
    this.person.pending = 1;
    // wccCode
    if (this.person.id) {
      this.personService()
        .update(this.person)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('hanEmsApp.person.updated', { param: param.id });
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

  public convertDateTimeFromServer(date: Date): string {
    if (date && dayjs(date).isValid()) {
      return dayjs(date).format(DATE_TIME_LONG_FORMAT);
    }
    return null;
  }

  public updateInstantField(field, event) {
    if (event.target.value) {
      this.person[field] = dayjs(event.target.value, DATE_TIME_LONG_FORMAT);
    } else {
      this.person[field] = null;
    }
  }

  public updateZonedDateTimeField(field, event) {
    if (event.target.value) {
      this.person[field] = dayjs(event.target.value, DATE_TIME_LONG_FORMAT);
    } else {
      this.person[field] = null;
    }
  }

  public retrievePerson(personId): void {
    this.personService()
      .find(personId)
      .then(res => {
        res.createDate = new Date(res.createDate);
        res.modifyDate = new Date(res.modifyDate);
        this.person = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {
    this.reasonService()
      .retrieve()
      .then(res => {
        this.reasons = res.data;
      });
  }
}
