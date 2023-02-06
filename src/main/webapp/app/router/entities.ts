import { Authority } from '@/shared/security/authority';
/* tslint:disable */
// prettier-ignore
const Entities = () => import('@/entities/entities.vue');

// prettier-ignore
const EqItem = () => import('@/entities/eq-item/eq-item.vue');
// prettier-ignore
const EqItemUpdate = () => import('@/entities/eq-item/eq-item-update.vue');
// prettier-ignore
const EqItemDetails = () => import('@/entities/eq-item/eq-item-details.vue');
// prettier-ignore
const Equipment = () => import('@/entities/equipment/equipment.vue');
// prettier-ignore
const EquipmentUpdate = () => import('@/entities/equipment/equipment-update.vue');
// prettier-ignore
const EquipmentDetails = () => import('@/entities/equipment/equipment-details.vue');
// jhipster-needle-add-entity-to-router-import - JHipster will import entities to the router here

export default {
  path: '/',
  component: Entities,
  children: [
    {
      path: 'eq-item',
      name: 'EqItem',
      component: EqItem,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'eq-item/new',
      name: 'EqItemCreate',
      component: EqItemUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'eq-item/:eqItemId/edit',
      name: 'EqItemEdit',
      component: EqItemUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'eq-item/:eqItemId/view',
      name: 'EqItemView',
      component: EqItemDetails,
      meta: { authorities: [Authority.USER] },
    },
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
    // jhipster-needle-add-entity-to-router - JHipster will add entities to the router here
  ],
};
