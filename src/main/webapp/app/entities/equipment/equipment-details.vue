<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="equipment">
        <h2 class="jh-entity-heading" data-cy="equipmentDetailsHeading">
          <span v-text="$t('hanEmsApp.equipment.detail.title')">Equipment</span> {{ equipment.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('hanEmsApp.equipment.eqItemId')">Eq Item Id</span>
          </dt>
          <dd>
            <span>{{ equipment.eqItemId }}</span>
          </dd>
          <dt>
            <span v-text="$t('hanEmsApp.equipment.name')">Name</span>
          </dt>
          <dd>
            <span>{{ equipment.name }}</span>
          </dd>
          <dt>
            <span v-text="$t('hanEmsApp.equipment.description')">Description</span>
          </dt>
          <dd>
            <span>{{ equipment.description }}</span>
          </dd>
          <dt>
            <span v-text="$t('hanEmsApp.equipment.createDate')">Create Date</span>
          </dt>
          <dd>
            <span v-if="equipment.createDate">{{ $d(Date.parse(equipment.createDate), 'long') }}</span>
          </dd>
          <dt>
            <span v-text="$t('hanEmsApp.equipment.modifyDate')">Modify Date</span>
          </dt>
          <dd>
            <span v-if="equipment.modifyDate">{{ $d(Date.parse(equipment.modifyDate), 'long') }}</span>
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
