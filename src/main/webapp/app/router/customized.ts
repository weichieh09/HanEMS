import { Authority } from '@/shared/security/authority';

const Wcc102Component = () => import('@/customized/wcc102/wcc102.vue');
const Wcc103Component = () => import('@/customized/wcc103/wcc103.vue');
const Wcc104Component = () => import('@/customized/wcc104/wcc104.vue');

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
];
