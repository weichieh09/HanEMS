import { Component, Provide, Vue } from 'vue-property-decorator';

import UserService from '@/entities/user/user.service';
import EqItemService from './eq-item/eq-item.service';
import EquipmentService from './equipment/equipment.service';
// jhipster-needle-add-entity-service-to-entities-component-import - JHipster will import entities services here

@Component
export default class Entities extends Vue {
  @Provide('userService') private userService = () => new UserService();
  @Provide('eqItemService') private eqItemService = () => new EqItemService();
  @Provide('equipmentService') private equipmentService = () => new EquipmentService();
  // jhipster-needle-add-entity-service-to-entities-component - JHipster will import entities services here
}
