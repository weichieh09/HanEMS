import Component from 'vue-class-component';
import { Inject, Provide, Vue } from 'vue-property-decorator';
import AlertService from '@/shared/alert/alert.service';

/* wccCode */
import { maxLength, minLength, required } from 'vuelidate/lib/validators';
import Wcc103Service from './wcc103.service';

const validations: any = {
  returnAccount: {
    name: {
      required,
      minLength: minLength(1),
      maxLength: maxLength(50),
    },
  },
};
/* wccCode */

@Component({
  validations,
})
export default class Wcc103 extends Vue {
  @Inject('alertService')
  private alertService: () => AlertService;

  public get authenticated(): boolean {
    return this.$store.getters.authenticated;
  }

  public get username(): string {
    return this.$store.getters.account?.login ?? '';
  }

  /* wccCode */
  @Provide('wcc103Service')
  private wcc103Service = () => new Wcc103Service();

  public returnAccount: any = {
    idno: undefined,
    name: undefined,
    phone: undefined,
  };

  public success = false;

  /* created:在模板渲染成html前調用，即通常初始化某些屬性值，然後再渲染成視圖。 */
  public created(): void {
    console.log('Wcc103Created()');
  }

  /* mounted:在模板渲染成html後調用，通常是初始化頁面完成後，再對html的dom節點進行一些需要的操作。 */
  public mounted(): void {
    console.log('Wcc103Mounted()');
  }

  public rent(): void {
    console.log('rent()');
    this.success = true;
  }

  public previousState() {
    this.$router.go(-1);
  }
  /* wccCode */
}
