<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="hanEmsApp.status.home.createOrEditLabel"
          data-cy="StatusCreateUpdateHeading"
          v-text="$t('hanEmsApp.status.home.createOrEditLabel')"
        >
          Create or edit a Status
        </h2>
        <div>
          <div class="form-group" v-if="status.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="status.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('hanEmsApp.status.name')" for="status-name">Name</label>
            <input
              type="text"
              class="form-control"
              name="name"
              id="status-name"
              data-cy="name"
              :class="{ valid: !$v.status.name.$invalid, invalid: $v.status.name.$invalid }"
              v-model="$v.status.name.$model"
              required
            />
            <div v-if="$v.status.name.$anyDirty && $v.status.name.$invalid">
              <small class="form-text text-danger" v-if="!$v.status.name.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('hanEmsApp.status.description')" for="status-description">Description</label>
            <textarea
              class="form-control"
              name="description"
              id="status-description"
              data-cy="description"
              :class="{ valid: !$v.status.description.$invalid, invalid: $v.status.description.$invalid }"
              v-model="$v.status.description.$model"
            ></textarea>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('hanEmsApp.status.createDate')" for="status-createDate">Create Date</label>
            <div class="d-flex">
              <input
                id="status-createDate"
                data-cy="createDate"
                type="datetime-local"
                class="form-control"
                name="createDate"
                :class="{ valid: !$v.status.createDate.$invalid, invalid: $v.status.createDate.$invalid }"
                :value="convertDateTimeFromServer($v.status.createDate.$model)"
                @change="updateInstantField('createDate', $event)"
              />
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('hanEmsApp.status.modifyDate')" for="status-modifyDate">Modify Date</label>
            <div class="d-flex">
              <input
                id="status-modifyDate"
                data-cy="modifyDate"
                type="datetime-local"
                class="form-control"
                name="modifyDate"
                :class="{ valid: !$v.status.modifyDate.$invalid, invalid: $v.status.modifyDate.$invalid }"
                :value="convertDateTimeFromServer($v.status.modifyDate.$model)"
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
            :disabled="$v.status.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./status-update.component.ts"></script>
