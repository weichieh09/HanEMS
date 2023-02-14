<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2 id="hanEmsApp.person.home.createOrEditLabel" data-cy="PersonCreateUpdateHeading">歸還</h2>
        <div>
          <div class="form-group" v-if="person.id">
            <label for="id">編號</label>
            <input type="text" class="form-control" id="id" name="id" v-model="person.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="person-idno">學號</label>
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
            <label class="form-control-label" for="person-name">姓名</label>
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
              <small class="form-text text-danger" v-if="!$v.person.name.required"> 必填 </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="person-phone">手機</label>
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
              <small class="form-text text-danger" v-if="!$v.person.phone.pattern"> 格式錯誤 </small>
            </div>
          </div>
          <div style="display: none" class="form-group">
            <label class="form-control-label" for="reasonId">用途</label>
            <select class="form-control" id="person-reasonId" data-cy="reasonId" name="reasonId" v-model="person.reasonId">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="person.reasonId && reasonOption.id === person.reasonId ? person.reasonId : reasonOption.id"
                v-for="reasonOption in reasons"
                :key="reasonOption.id"
              >
                {{ reasonOption.name }}
              </option>
            </select>
          </div>
          <div style="display: none" class="form-group">
            <label class="form-control-label" v-text="$t('hanEmsApp.person.pending')" for="person-pending">待處理</label>
            <input
              type="number"
              class="form-control"
              name="pending"
              id="person-pending"
              data-cy="pending"
              :class="{ valid: !$v.person.pending.$invalid, invalid: $v.person.pending.$invalid }"
              v-model.number="$v.person.pending.$model"
            />
          </div>
          <div style="display: none" class="form-group">
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
          <div style="display: none" class="form-group">
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
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span>送出</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./person-update.component.ts"></script>
