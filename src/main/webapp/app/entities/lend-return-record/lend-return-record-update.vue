<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="hanEmsApp.lendReturnRecord.home.createOrEditLabel"
          data-cy="LendReturnRecordCreateUpdateHeading"
          v-text="$t('hanEmsApp.lendReturnRecord.home.createOrEditLabel')"
        >
          Create or edit a LendReturnRecord
        </h2>
        <div>
          <div class="form-group" v-if="lendReturnRecord.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="lendReturnRecord.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('hanEmsApp.lendReturnRecord.lendDate')" for="lend-return-record-lendDate"
              >Lend Date</label
            >
            <div class="d-flex">
              <input
                id="lend-return-record-lendDate"
                data-cy="lendDate"
                type="datetime-local"
                class="form-control"
                name="lendDate"
                :class="{ valid: !$v.lendReturnRecord.lendDate.$invalid, invalid: $v.lendReturnRecord.lendDate.$invalid }"
                required
                :value="convertDateTimeFromServer($v.lendReturnRecord.lendDate.$model)"
                @change="updateInstantField('lendDate', $event)"
              />
            </div>
            <div v-if="$v.lendReturnRecord.lendDate.$anyDirty && $v.lendReturnRecord.lendDate.$invalid">
              <small class="form-text text-danger" v-if="!$v.lendReturnRecord.lendDate.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
              <small
                class="form-text text-danger"
                v-if="!$v.lendReturnRecord.lendDate.ZonedDateTimelocal"
                v-text="$t('entity.validation.ZonedDateTimelocal')"
              >
                This field should be a date and time.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('hanEmsApp.lendReturnRecord.returnDate')" for="lend-return-record-returnDate"
              >Return Date</label
            >
            <div class="d-flex">
              <input
                id="lend-return-record-returnDate"
                data-cy="returnDate"
                type="datetime-local"
                class="form-control"
                name="returnDate"
                :class="{ valid: !$v.lendReturnRecord.returnDate.$invalid, invalid: $v.lendReturnRecord.returnDate.$invalid }"
                :value="convertDateTimeFromServer($v.lendReturnRecord.returnDate.$model)"
                @change="updateInstantField('returnDate', $event)"
              />
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('hanEmsApp.lendReturnRecord.description')" for="lend-return-record-description"
              >Description</label
            >
            <textarea
              class="form-control"
              name="description"
              id="lend-return-record-description"
              data-cy="description"
              :class="{ valid: !$v.lendReturnRecord.description.$invalid, invalid: $v.lendReturnRecord.description.$invalid }"
              v-model="$v.lendReturnRecord.description.$model"
            ></textarea>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('hanEmsApp.lendReturnRecord.createDate')" for="lend-return-record-createDate"
              >Create Date</label
            >
            <div class="d-flex">
              <input
                id="lend-return-record-createDate"
                data-cy="createDate"
                type="datetime-local"
                class="form-control"
                name="createDate"
                :class="{ valid: !$v.lendReturnRecord.createDate.$invalid, invalid: $v.lendReturnRecord.createDate.$invalid }"
                :value="convertDateTimeFromServer($v.lendReturnRecord.createDate.$model)"
                @change="updateInstantField('createDate', $event)"
              />
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('hanEmsApp.lendReturnRecord.modifyDate')" for="lend-return-record-modifyDate"
              >Modify Date</label
            >
            <div class="d-flex">
              <input
                id="lend-return-record-modifyDate"
                data-cy="modifyDate"
                type="datetime-local"
                class="form-control"
                name="modifyDate"
                :class="{ valid: !$v.lendReturnRecord.modifyDate.$invalid, invalid: $v.lendReturnRecord.modifyDate.$invalid }"
                :value="convertDateTimeFromServer($v.lendReturnRecord.modifyDate.$model)"
                @change="updateInstantField('modifyDate', $event)"
              />
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('hanEmsApp.lendReturnRecord.equipment')" for="lend-return-record-equipment"
              >Equipment</label
            >
            <select
              class="form-control"
              id="lend-return-record-equipment"
              data-cy="equipment"
              name="equipment"
              v-model="lendReturnRecord.equipment"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  lendReturnRecord.equipment && equipmentOption.id === lendReturnRecord.equipment.id
                    ? lendReturnRecord.equipment
                    : equipmentOption
                "
                v-for="equipmentOption in equipment"
                :key="equipmentOption.id"
              >
                {{ equipmentOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('hanEmsApp.lendReturnRecord.person')" for="lend-return-record-person"
              >Person</label
            >
            <select class="form-control" id="lend-return-record-person" data-cy="person" name="person" v-model="lendReturnRecord.person">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  lendReturnRecord.person && personOption.id === lendReturnRecord.person.id ? lendReturnRecord.person : personOption
                "
                v-for="personOption in people"
                :key="personOption.id"
              >
                {{ personOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('hanEmsApp.lendReturnRecord.reason')" for="lend-return-record-reason"
              >Reason</label
            >
            <select class="form-control" id="lend-return-record-reason" data-cy="reason" name="reason" v-model="lendReturnRecord.reason">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  lendReturnRecord.reason && reasonOption.id === lendReturnRecord.reason.id ? lendReturnRecord.reason : reasonOption
                "
                v-for="reasonOption in reasons"
                :key="reasonOption.id"
              >
                {{ reasonOption.id }}
              </option>
            </select>
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
            :disabled="$v.lendReturnRecord.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./lend-return-record-update.component.ts"></script>
