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
            <label class="form-control-label" v-text="$t('hanEmsApp.equipment.eqItemId')" for="equipment-eqItemId">Eq Item Id</label>
            <input
              type="number"
              class="form-control"
              name="eqItemId"
              id="equipment-eqItemId"
              data-cy="eqItemId"
              :class="{ valid: !$v.equipment.eqItemId.$invalid, invalid: $v.equipment.eqItemId.$invalid }"
              v-model.number="$v.equipment.eqItemId.$model"
            />
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
            <label class="form-control-label" v-text="$t('hanEmsApp.equipment.description')" for="equipment-description">Description</label>
            <input
              type="text"
              class="form-control"
              name="description"
              id="equipment-description"
              data-cy="description"
              :class="{ valid: !$v.equipment.description.$invalid, invalid: $v.equipment.description.$invalid }"
              v-model="$v.equipment.description.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('hanEmsApp.equipment.createDate')" for="equipment-createDate">Create Date</label>
            <div class="d-flex">
              <input
                id="equipment-createDate"
                data-cy="createDate"
                type="datetime-local"
                class="form-control"
                name="createDate"
                :class="{ valid: !$v.equipment.createDate.$invalid, invalid: $v.equipment.createDate.$invalid }"
                :value="convertDateTimeFromServer($v.equipment.createDate.$model)"
                @change="updateInstantField('createDate', $event)"
              />
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('hanEmsApp.equipment.modifyDate')" for="equipment-modifyDate">Modify Date</label>
            <div class="d-flex">
              <input
                id="equipment-modifyDate"
                data-cy="modifyDate"
                type="datetime-local"
                class="form-control"
                name="modifyDate"
                :class="{ valid: !$v.equipment.modifyDate.$invalid, invalid: $v.equipment.modifyDate.$invalid }"
                :value="convertDateTimeFromServer($v.equipment.modifyDate.$model)"
                @change="updateInstantField('modifyDate', $event)"
              />
            </div>
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
