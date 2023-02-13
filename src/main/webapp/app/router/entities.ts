import { Authority } from '@/shared/security/authority';
/* tslint:disable */
// prettier-ignore
const Entities = () => import('@/entities/entities.vue');

// prettier-ignore
const Equipment = () => import('@/entities/equipment/equipment.vue');
// prettier-ignore
const EquipmentUpdate = () => import('@/entities/equipment/equipment-update.vue');
// prettier-ignore
const EquipmentDetails = () => import('@/entities/equipment/equipment-details.vue');
// prettier-ignore
const Status = () => import('@/entities/status/status.vue');
// prettier-ignore
const StatusUpdate = () => import('@/entities/status/status-update.vue');
// prettier-ignore
const StatusDetails = () => import('@/entities/status/status-details.vue');
// prettier-ignore
const Item = () => import('@/entities/item/item.vue');
// prettier-ignore
const ItemUpdate = () => import('@/entities/item/item-update.vue');
// prettier-ignore
const ItemDetails = () => import('@/entities/item/item-details.vue');
// prettier-ignore
const Person = () => import('@/entities/person/person.vue');
// prettier-ignore
const PersonUpdate = () => import('@/entities/person/person-update.vue');
// prettier-ignore
const PersonDetails = () => import('@/entities/person/person-details.vue');
// prettier-ignore
const Reason = () => import('@/entities/reason/reason.vue');
// prettier-ignore
const ReasonUpdate = () => import('@/entities/reason/reason-update.vue');
// prettier-ignore
const ReasonDetails = () => import('@/entities/reason/reason-details.vue');
// prettier-ignore
const LendReturnRecord = () => import('@/entities/lend-return-record/lend-return-record.vue');
// prettier-ignore
const LendReturnRecordUpdate = () => import('@/entities/lend-return-record/lend-return-record-update.vue');
// prettier-ignore
const LendReturnRecordDetails = () => import('@/entities/lend-return-record/lend-return-record-details.vue');
// prettier-ignore
const BlackList = () => import('@/entities/black-list/black-list.vue');
// prettier-ignore
const BlackListUpdate = () => import('@/entities/black-list/black-list-update.vue');
// prettier-ignore
const BlackListDetails = () => import('@/entities/black-list/black-list-details.vue');
// prettier-ignore
const ItemView = () => import('@/entities/item-view/item-view.vue');
// prettier-ignore
const ItemViewDetails = () => import('@/entities/item-view/item-view-details.vue');
// jhipster-needle-add-entity-to-router-import - JHipster will import entities to the router here

export default {
  path: '/',
  component: Entities,
  children: [
    {
      path: 'equipment',
      name: 'Equipment',
      component: Equipment,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'equipment/new',
      name: 'EquipmentCreate',
      component: EquipmentUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'equipment/:equipmentId/edit',
      name: 'EquipmentEdit',
      component: EquipmentUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'equipment/:equipmentId/view',
      name: 'EquipmentView',
      component: EquipmentDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'status',
      name: 'Status',
      component: Status,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'status/new',
      name: 'StatusCreate',
      component: StatusUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'status/:statusId/edit',
      name: 'StatusEdit',
      component: StatusUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'status/:statusId/view',
      name: 'StatusView',
      component: StatusDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'item',
      name: 'Item',
      component: Item,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'item/new',
      name: 'ItemCreate',
      component: ItemUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'item/:itemId/edit',
      name: 'ItemEdit',
      component: ItemUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'item/:itemId/view',
      name: 'ItemView',
      component: ItemDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'person',
      name: 'Person',
      component: Person,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'person/new',
      name: 'PersonCreate',
      component: PersonUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'person/:personId/edit',
      name: 'PersonEdit',
      component: PersonUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'person/:personId/view',
      name: 'PersonView',
      component: PersonDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'reason',
      name: 'Reason',
      component: Reason,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'reason/new',
      name: 'ReasonCreate',
      component: ReasonUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'reason/:reasonId/edit',
      name: 'ReasonEdit',
      component: ReasonUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'reason/:reasonId/view',
      name: 'ReasonView',
      component: ReasonDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'lend-return-record',
      name: 'LendReturnRecord',
      component: LendReturnRecord,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'lend-return-record/new',
      name: 'LendReturnRecordCreate',
      component: LendReturnRecordUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'lend-return-record/:lendReturnRecordId/edit',
      name: 'LendReturnRecordEdit',
      component: LendReturnRecordUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'lend-return-record/:lendReturnRecordId/view',
      name: 'LendReturnRecordView',
      component: LendReturnRecordDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'black-list',
      name: 'BlackList',
      component: BlackList,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'black-list/new',
      name: 'BlackListCreate',
      component: BlackListUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'black-list/:blackListId/edit',
      name: 'BlackListEdit',
      component: BlackListUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'black-list/:blackListId/view',
      name: 'BlackListView',
      component: BlackListDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: '/item-view',
      name: 'ItemView',
      component: ItemView,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: '/item-view/:itemViewId/view',
      name: 'ItemViewView',
      component: ItemViewDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: '/item-view',
      name: 'ItemView',
      component: ItemView,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: '/item-view/:itemViewId/view',
      name: 'ItemViewView',
      component: ItemViewDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: '/item-view',
      name: 'ItemView',
      component: ItemView,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: '/item-view/:itemViewId/view',
      name: 'ItemViewView',
      component: ItemViewDetails,
      meta: { authorities: [Authority.USER] },
    },
    // jhipster-needle-add-entity-to-router - JHipster will add entities to the router here
  ],
};
