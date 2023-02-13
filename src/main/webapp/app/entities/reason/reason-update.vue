<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="hanEmsApp.reason.home.createOrEditLabel"
          data-cy="ReasonCreateUpdateHeading"
          v-text="$t('hanEmsApp.reason.home.createOrEditLabel')"
        >
          Create or edit a Reason
        </h2>
        <div>
          <div class="form-group" v-if="reason.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="reason.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('hanEmsApp.reason.name')" for="reason-name">Name</label>
            <input
              type="text"
              class="form-control"
              name="name"
              id="reason-name"
              data-cy="name"
              :class="{ valid: !$v.reason.name.$invalid, invalid: $v.reason.name.$invalid }"
              v-model="$v.reason.name.$model"
              required
            />
            <div v-if="$v.reason.name.$anyDirty && $v.reason.name.$invalid">
              <small class="form-text text-danger" v-if="!$v.reason.name.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('hanEmsApp.reason.description')" for="reason-description">Description</label>
            <textarea
              class="form-control"
              name="description"
              id="reason-description"
              data-cy="description"
              :class="{ valid: !$v.reason.description.$invalid, invalid: $v.reason.description.$invalid }"
              v-model="$v.reason.description.$model"
            ></textarea>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('hanEmsApp.reason.createDate')" for="reason-createDate">Create Date</label>
            <div class="d-flex">
              <input
                id="reason-createDate"
                data-cy="createDate"
                type="datetime-local"
                class="form-control"
                name="createDate"
                :class="{ valid: !$v.reason.createDate.$invalid, invalid: $v.reason.createDate.$invalid }"
                :value="convertDateTimeFromServer($v.reason.createDate.$model)"
                @change="updateInstantField('createDate', $event)"
              />
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('hanEmsApp.reason.modifyDate')" for="reason-modifyDate">Modify Date</label>
            <div class="d-flex">
              <input
                id="reason-modifyDate"
                data-cy="modifyDate"
                type="datetime-local"
                class="form-control"
                name="modifyDate"
                :class="{ valid: !$v.reason.modifyDate.$invalid, invalid: $v.reason.modifyDate.$invalid }"
                :value="convertDateTimeFromServer($v.reason.modifyDate.$model)"
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
            :disabled="$v.reason.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./reason-update.component.ts"></script>
