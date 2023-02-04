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
    // jhipster-needle-add-entity-to-router - JHipster will add entities to the router here
  ],
};
