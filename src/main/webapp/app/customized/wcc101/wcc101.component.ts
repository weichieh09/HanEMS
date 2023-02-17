import Component from 'vue-class-component';
import { Watch, Inject, Provide, Vue } from 'vue-property-decorator';
import LoginService from '@/account/login.service';
import AlertService from '@/shared/alert/alert.service';
/* wccCode */
import Wcc101Service from './wcc101.service';
import moment from 'moment';
/* wccCode */

@Component
export default class Wcc101 extends Vue {
  @Inject('loginService')
  private loginService: () => LoginService;
  @Inject('alertService')
  private alertService: () => AlertService;

  public nowDate = null;
  private timerRefresh = null;

  public openLogin(): void {
    this.loginService().openLogin((<any>this).$root);
  }

  public get authenticated(): boolean {
    return this.$store.getters.authenticated;
  }

  public get username(): string {
    return this.$store.getters.account?.login ?? '';
  }

  /* wccCode */
  @Provide('wcc101Service')
  private wcc101Service = () => new Wcc101Service();

  /* created:在模板渲染成html前調用，即通常初始化某些屬性值，然後再渲染成視圖。 */
  public created(): void {
    console.log('Wcc101Created()');
    this.timerRefresh = setInterval(this.timeFresh, 1000);
  }

  /* mounted:在模板渲染成html後調用，通常是初始化頁面完成後，再對html的dom節點進行一些需要的操作。 */
  public mounted(): void {
    console.log('Wcc101Mounted()');
  }

  @Watch('authenticated')
  public watchAuthenticated(newValue, oldValue) {
    console.log('watchAuthenticated()');
  }
  /* wccCode */

  public timeFresh(): void {
    this.nowDate = moment(new Date()).format('yyyy-MM-DD hh:mm:ss');
  }

  // 跳轉到其他頁關閉 timer
  public beforeDestroy(): void {
    clearInterval(this.timerRefresh);
  }
}
