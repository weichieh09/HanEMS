import { Authority } from '@/shared/security/authority';

const Wcc102Component = () => import('@/customized/wcc102/item-view.vue');
const Wcc103Component = () => import('@/customized/wcc103/person-update.vue');
const Wcc104Component = () => import('@/customized/wcc104/person-update.vue');
// const Wcc105Component = () => import('@/customized/wcc105/wcc105.vue');
// const Wcc105Update = () => import('@/customized/wcc105/wcc105Update.vue');
// const Wcc105Details = () => import('@/customized/wcc105/wcc105Details.vue');
// const Wcc106Component = () => import('@/customized/wcc106/wcc106.vue');
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
  // {
  //   path: '/wcc105',
  //   name: 'Wcc105',
  //   component: Wcc105Component,
  //   meta: { authorities: [Authority.USER] },
  // },
  // {
  //   path: '/wcc105/new',
  //   name: 'Wcc105Create',
  //   component: Wcc105Update,
  //   meta: { authorities: [Authority.USER] },
  // },
  // {
  //   path: '/wcc105/:eqItemId/edit',
  //   name: 'Wcc105Edit',
  //   component: Wcc105Update,
  //   meta: { authorities: [Authority.USER] },
  // },
  // {
  //   path: '/wcc105/:eqItemId/view',
  //   name: 'Wcc105View',
  //   component: Wcc105Details,
  //   meta: { authorities: [Authority.USER] },
  // },
  // {
  //   path: '/wcc106',
  //   name: 'Wcc106',
  //   component: Wcc106Component,
  //   meta: { authorities: [Authority.USER] },
  // },
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
