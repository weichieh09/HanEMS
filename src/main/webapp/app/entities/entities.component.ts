import { Component, Provide, Vue } from 'vue-property-decorator';

import UserService from '@/entities/user/user.service';
import EquipmentService from './equipment/equipment.service';
import StatusService from './status/status.service';
import ItemService from './item/item.service';
import PersonService from './person/person.service';
import ReasonService from './reason/reason.service';
import LendReturnRecordService from './lend-return-record/lend-return-record.service';
import BlackListService from './black-list/black-list.service';
import ItemViewService from './item-view/item-view.service';
// jhipster-needle-add-entity-service-to-entities-component-import - JHipster will import entities services here

@Component
export default class Entities extends Vue {
  @Provide('userService') private userService = () => new UserService();
  @Provide('equipmentService') private equipmentService = () => new EquipmentService();
  @Provide('statusService') private statusService = () => new StatusService();
  @Provide('itemService') private itemService = () => new ItemService();
  @Provide('personService') private personService = () => new PersonService();
  @Provide('reasonService') private reasonService = () => new ReasonService();
  @Provide('lendReturnRecordService') private lendReturnRecordService = () => new LendReturnRecordService();
  @Provide('blackListService') private blackListService = () => new BlackListService();
  @Provide('itemViewService') private itemViewService = () => new ItemViewService();
  // jhipster-needle-add-entity-service-to-entities-component - JHipster will import entities services here
}
