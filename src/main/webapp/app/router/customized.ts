import { Authority } from '@/shared/security/authority';

const Wcc102Component = () => import('@/customized/wcc102/wcc102.vue');
const Wcc103Component = () => import('@/customized/wcc103/wcc103.vue');
const Wcc104Component = () => import('@/customized/wcc104/wcc104.vue');
const Wcc105Component = () => import('@/customized/wcc105/wcc105.vue');
const Wcc105Update = () => import('@/customized/wcc105/wcc105Update.vue');
const Wcc105Details = () => import('@/customized/wcc105/wcc105Details.vue');

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
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/wcc105/new',
    name: 'Wcc105Create',
    component: Wcc105Update,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/wcc105/:eqItemId/edit',
    name: 'Wcc105Edit',
    component: Wcc105Update,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/wcc105/:eqItemId/view',
    name: 'Wcc105View',
    component: Wcc105Details,
    meta: { authorities: [Authority.USER] },
  },
];
