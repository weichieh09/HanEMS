import { Authority } from '@/shared/security/authority';

const Wcc102Component = () => import('@/customized/wcc102/item-view.vue');
const Wcc103Component = () => import('@/customized/wcc103/person-update.vue');
const Wcc104Component = () => import('@/customized/wcc104/person-update.vue');
const Wcc105Component = () => import('@/customized/wcc105/person.vue');
const Wcc105Edit = () => import('@/customized/wcc105/equipment.vue');
const BlackList = () => import('@/customized/wcc106/black-list.vue');
const BlackListUpdate = () => import('@/customized/wcc106/black-list-update.vue');
const BlackListDetails = () => import('@/customized/wcc106/black-list-details.vue');
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
  // {
  //   path: '/wcc106/new',
  //   name: 'Wcc106Create',
  //   component: Wcc106Update,
  //   meta: { authorities: [Authority.USER] },
  // },
  // {
  //   path: '/wcc106/:eqViewId/edit',
  //   name: 'Wcc106Edit',
  //   component: Wcc106Update,
  //   meta: { authorities: [Authority.USER] },
  // },
  // {
  //   path: '/wcc106/:eqViewId/view',
  //   name: 'Wcc106View',
  //   component: Wcc106Details,
  //   meta: { authorities: [Authority.USER] },
  // },
];
