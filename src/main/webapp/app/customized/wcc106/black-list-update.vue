<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2 v-if="blackList.id" data-cy="BlackListCreateUpdateHeading">[編輯] 黑名單</h2>
        <h2 v-else data-cy="BlackListCreateUpdateHeading">[新增] 黑名單</h2>
        <div>
          <div class="form-group" v-if="blackList.id">
            <label for="id">編號</label>
            <input type="text" class="form-control" id="id" name="id" v-model="blackList.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="black-list-person">姓名</label>
            <select class="form-control" id="black-list-person" data-cy="person" name="person" v-model="blackList.person">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="blackList.person && personOption.id === blackList.person.id ? blackList.person : personOption"
                v-for="personOption in people"
                :key="personOption.id"
              >
                {{ personOption.name }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="black-list-description">描述</label>
            <textarea
              class="form-control"
              name="description"
              id="black-list-description"
              data-cy="description"
              :class="{ valid: !$v.blackList.description.$invalid, invalid: $v.blackList.description.$invalid }"
              v-model="$v.blackList.description.$model"
            ></textarea>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="black-list-blackDate">日期</label>
            <div class="d-flex">
              <input
                id="black-list-blackDate"
                data-cy="blackDate"
                type="datetime-local"
                class="form-control"
                name="blackDate"
                :class="{ valid: !$v.blackList.blackDate.$invalid, invalid: $v.blackList.blackDate.$invalid }"
                required
                :value="convertDateTimeFromServer($v.blackList.blackDate.$model)"
                @change="updateInstantField('blackDate', $event)"
              />
            </div>
            <div v-if="$v.blackList.blackDate.$anyDirty && $v.blackList.blackDate.$invalid">
              <small class="form-text text-danger" v-if="!$v.blackList.blackDate.required"> 必填 </small>
              <small
                class="form-text text-danger"
                v-if="!$v.blackList.blackDate.ZonedDateTimelocal"
                v-text="$t('entity.validation.ZonedDateTimelocal')"
              >
                This field should be a date and time.
              </small>
            </div>
          </div>
          <div style="display: none" class="form-group">
            <label class="form-control-label" v-text="$t('hanEmsApp.blackList.createDate')" for="black-list-createDate">Create Date</label>
            <div class="d-flex">
              <input
                id="black-list-createDate"
                data-cy="createDate"
                type="datetime-local"
                class="form-control"
                name="createDate"
                :class="{ valid: !$v.blackList.createDate.$invalid, invalid: $v.blackList.createDate.$invalid }"
                :value="convertDateTimeFromServer($v.blackList.createDate.$model)"
                @change="updateInstantField('createDate', $event)"
              />
            </div>
          </div>
          <div style="display: none" class="form-group">
            <label class="form-control-label" v-text="$t('hanEmsApp.blackList.modifyDate')" for="black-list-modifyDate">Modify Date</label>
            <div class="d-flex">
              <input
                id="black-list-modifyDate"
                data-cy="modifyDate"
                type="datetime-local"
                class="form-control"
                name="modifyDate"
                :class="{ valid: !$v.blackList.modifyDate.$invalid, invalid: $v.blackList.modifyDate.$invalid }"
                :value="convertDateTimeFromServer($v.blackList.modifyDate.$model)"
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
            :disabled="$v.blackList.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./black-list-update.component.ts"></script>
