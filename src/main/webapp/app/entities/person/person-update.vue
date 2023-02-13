<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="hanEmsApp.person.home.createOrEditLabel"
          data-cy="PersonCreateUpdateHeading"
          v-text="$t('hanEmsApp.person.home.createOrEditLabel')"
        >
          Create or edit a Person
        </h2>
        <div>
          <div class="form-group" v-if="person.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="person.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('hanEmsApp.person.idno')" for="person-idno">Idno</label>
            <input
              type="text"
              class="form-control"
              name="idno"
              id="person-idno"
              data-cy="idno"
              :class="{ valid: !$v.person.idno.$invalid, invalid: $v.person.idno.$invalid }"
              v-model="$v.person.idno.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('hanEmsApp.person.name')" for="person-name">Name</label>
            <input
              type="text"
              class="form-control"
              name="name"
              id="person-name"
              data-cy="name"
              :class="{ valid: !$v.person.name.$invalid, invalid: $v.person.name.$invalid }"
              v-model="$v.person.name.$model"
              required
            />
            <div v-if="$v.person.name.$anyDirty && $v.person.name.$invalid">
              <small class="form-text text-danger" v-if="!$v.person.name.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('hanEmsApp.person.phone')" for="person-phone">Phone</label>
            <input
              type="text"
              class="form-control"
              name="phone"
              id="person-phone"
              data-cy="phone"
              :class="{ valid: !$v.person.phone.$invalid, invalid: $v.person.phone.$invalid }"
              v-model="$v.person.phone.$model"
            />
            <div v-if="$v.person.phone.$anyDirty && $v.person.phone.$invalid">
              <small
                class="form-text text-danger"
                v-if="!$v.person.phone.pattern"
                v-text="$t('entity.validation.pattern', { pattern: 'Phone' })"
              >
                This field should follow pattern for "Phone".
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('hanEmsApp.person.createDate')" for="person-createDate">Create Date</label>
            <div class="d-flex">
              <input
                id="person-createDate"
                data-cy="createDate"
                type="datetime-local"
                class="form-control"
                name="createDate"
                :class="{ valid: !$v.person.createDate.$invalid, invalid: $v.person.createDate.$invalid }"
                :value="convertDateTimeFromServer($v.person.createDate.$model)"
                @change="updateInstantField('createDate', $event)"
              />
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('hanEmsApp.person.modifyDate')" for="person-modifyDate">Modify Date</label>
            <div class="d-flex">
              <input
                id="person-modifyDate"
                data-cy="modifyDate"
                type="datetime-local"
                class="form-control"
                name="modifyDate"
                :class="{ valid: !$v.person.modifyDate.$invalid, invalid: $v.person.modifyDate.$invalid }"
                :value="convertDateTimeFromServer($v.person.modifyDate.$model)"
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
            :disabled="$v.person.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./person-update.component.ts"></script>
