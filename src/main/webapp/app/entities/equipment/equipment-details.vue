<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="equipment">
        <h2 class="jh-entity-heading" data-cy="equipmentDetailsHeading">
          <span v-text="$t('hanEmsApp.equipment.detail.title')">Equipment</span> {{ equipment.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('hanEmsApp.equipment.name')">Name</span>
          </dt>
          <dd>
            <span>{{ equipment.name }}</span>
          </dd>
          <dt>
            <span v-text="$t('hanEmsApp.equipment.totalEle')">Total Ele</span>
          </dt>
          <dd>
            <span>{{ equipment.totalEle }}</span>
          </dd>
          <dt>
            <span v-text="$t('hanEmsApp.equipment.rentedEle')">Rented Ele</span>
          </dt>
          <dd>
            <span>{{ equipment.rentedEle }}</span>
          </dd>
          <dt>
            <span v-text="$t('hanEmsApp.equipment.currentEle')">Current Ele</span>
          </dt>
          <dd>
            <span>{{ equipment.currentEle }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <router-link
          v-if="equipment.id"
          :to="{ name: 'EquipmentEdit', params: { equipmentId: equipment.id } }"
          custom
          v-slot="{ navigate }"
        >
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./equipment-details.component.ts"></script>
