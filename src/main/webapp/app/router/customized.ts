import { Authority } from '@/shared/security/authority';

const Wcc102Component = () => import('@/customized/wcc102/item-view.vue');
const Wcc103Component = () => import('@/customized/wcc103/person-update.vue');
const Wcc104Component = () => import('@/customized/wcc104/person-update.vue');
const Wcc105Component = () => import('@/customized/wcc105/person.vue');
const Wcc105Edit = () => import('@/customized/wcc105/equipment.vue');
const BlackList = () => import('@/customized/wcc106/black-list.vue');
const BlackListUpdate = () => import('@/customized/wcc106/black-list-update.vue');
const BlackListDetails = () => import('@/customized/wcc106/black-list-details.vue');
const Item = () => import('@/customized/wcc107/item.vue');
const ItemUpdate = () => import('@/customized/wcc107/item-update.vue');
const ItemDetails = () => import('@/customized/wcc107/item-details.vue');
const Equipment = () => import('@/customized/wcc108/equipment.vue');
const EquipmentUpdate = () => import('@/customized/wcc108/equipment-update.vue');
const EquipmentDetails = () => import('@/customized/wcc108/equipment-details.vue');
// const Wcc106Update = () => import('@/customized/wcc106/wcc106Update.vue');
// const Wcc106Details = () => import('@/customized/wcc106/wcc106Details.vue');

export default [
  {
    path: '/wcc102',
    name: 'Wcc102',
    component: Wcc102Component,
  },
  {
    path: '/wcc103',
    name: 'Wcc103',
    component: Wcc103Component,
  },
  {
    path: '/wcc104',
    name: 'Wcc104',
    component: Wcc104Component,
  },
  {
    path: '/wcc105',
    name: 'Wcc105',
    component: Wcc105Component,
    meta: { authorities: [Authority.ADMIN] },
  },
  {
    path: '/wcc105/:personId/:personPending/edit',
    name: 'Wcc105Edit',
    component: Wcc105Edit,
    meta: { authorities: [Authority.ADMIN] },
  },
  {
    path: '/wcc106',
    name: 'Wcc106',
    component: BlackList,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/wcc106/new',
    name: 'Wcc106Create',
    component: BlackListUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/wcc106/:blackListId/edit',
    name: 'Wcc106Edit',
    component: BlackListUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/wcc106/:blackListId/view',
    name: 'Wcc106View',
    component: BlackListDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/wcc107',
    name: 'Wcc107',
    component: Item,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/wcc107/new',
    name: 'Wcc107Create',
    component: ItemUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/wcc107/:itemId/edit',
    name: 'Wcc107Edit',
    component: ItemUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/wcc107/:itemId/view',
    name: 'Wcc107View',
    component: ItemDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/wcc108',
    name: 'Wcc108',
    component: Equipment,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/wcc108/new',
    name: 'Wcc108Create',
    component: EquipmentUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/wcc108/:equipmentId/edit',
    name: 'Wcc108Edit',
    component: EquipmentUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/wcc108/:equipmentId/view',
    name: 'Wcc108View',
    component: EquipmentDetails,
    meta: { authorities: [Authority.USER] },
  },
];
