<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="hanEmsApp.equipment.home.createOrEditLabel"
          data-cy="EquipmentCreateUpdateHeading"
          v-text="$t('hanEmsApp.equipment.home.createOrEditLabel')"
        >
          Create or edit a Equipment
        </h2>
        <div>
          <div class="form-group" v-if="equipment.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="equipment.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('hanEmsApp.equipment.name')" for="equipment-name">Name</label>
            <input
              type="text"
              class="form-control"
              name="name"
              id="equipment-name"
              data-cy="name"
              :class="{ valid: !$v.equipment.name.$invalid, invalid: $v.equipment.name.$invalid }"
              v-model="$v.equipment.name.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('hanEmsApp.equipment.totalEle')" for="equipment-totalEle">Total Ele</label>
            <input
              type="number"
              class="form-control"
              name="totalEle"
              id="equipment-totalEle"
              data-cy="totalEle"
              :class="{ valid: !$v.equipment.totalEle.$invalid, invalid: $v.equipment.totalEle.$invalid }"
              v-model.number="$v.equipment.totalEle.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('hanEmsApp.equipment.rentedEle')" for="equipment-rentedEle">Rented Ele</label>
            <input
              type="number"
              class="form-control"
              name="rentedEle"
              id="equipment-rentedEle"
              data-cy="rentedEle"
              :class="{ valid: !$v.equipment.rentedEle.$invalid, invalid: $v.equipment.rentedEle.$invalid }"
              v-model.number="$v.equipment.rentedEle.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('hanEmsApp.equipment.currentEle')" for="equipment-currentEle">Current Ele</label>
            <input
              type="number"
              class="form-control"
              name="currentEle"
              id="equipment-currentEle"
              data-cy="currentEle"
              :class="{ valid: !$v.equipment.currentEle.$invalid, invalid: $v.equipment.currentEle.$invalid }"
              v-model.number="$v.equipment.currentEle.$model"
            />
          </div>
        </div>
        <div>
          <button type="button" id="cancel-save" data-cy="entityCreateCancelButton" class="btn btn-secondary" v-on:click="previousState()">
            <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
          </button>
          <button
            type="submit"
            id="save-entity"
            data-cy="entityCreateSaveButton"
            :disabled="$v.equipment.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./equipment-update.component.ts"></script>
