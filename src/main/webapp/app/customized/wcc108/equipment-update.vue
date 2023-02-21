<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2 v-if="equipment.id" data-cy="EquipmentCreateUpdateHeading">[編輯] 設備</h2>
        <h2 v-else data-cy="EquipmentCreateUpdateHeading">[新增] 設備</h2>
        <div>
          <div class="form-group" v-if="equipment.id">
            <label for="id">編號</label>
            <input type="text" class="form-control" id="id" name="id" v-model="equipment.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="equipment-idno">設備序號</label>
            <input
              type="text"
              class="form-control"
              name="idno"
              id="equipment-idno"
              data-cy="idno"
              :class="{ valid: !$v.equipment.idno.$invalid, invalid: $v.equipment.idno.$invalid }"
              v-model="$v.equipment.idno.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="equipment-name">名稱</label>
            <input
              type="text"
              class="form-control"
              name="name"
              id="equipment-name"
              data-cy="name"
              :class="{ valid: !$v.equipment.name.$invalid, invalid: $v.equipment.name.$invalid }"
              v-model="$v.equipment.name.$model"
              required
            />
            <div v-if="$v.equipment.name.$anyDirty && $v.equipment.name.$invalid">
              <small class="form-text text-danger" v-if="!$v.equipment.name.required"> 必填 </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="equipment-description">描述</label>
            <textarea
              class="form-control"
              name="description"
              id="equipment-description"
              data-cy="description"
              :class="{ valid: !$v.equipment.description.$invalid, invalid: $v.equipment.description.$invalid }"
              v-model="$v.equipment.description.$model"
            ></textarea>
          </div>
          <div style="display: none" class="form-group">
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
          <div style="display: none" class="form-group">
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
          <div class="form-group">
            <label class="form-control-label" for="equipment-item">類別</label>
            <select
              class="form-control"
              id="equipment-item"
              data-cy="item"
              name="item"
              v-model="equipment.item"
              required
              :class="{ valid: !$v.equipment.item.$invalid, invalid: $v.equipment.item.$invalid }"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="equipment.item && itemOption.id === equipment.item.id ? equipment.item : itemOption"
                v-for="itemOption in items"
                :key="itemOption.id"
              >
                {{ itemOption.name }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="equipment-status">狀態</label>
            <select
              class="form-control"
              id="equipment-status"
              data-cy="status"
              name="status"
              v-model="equipment.status"
              required
              :class="{ valid: !$v.equipment.status.$invalid, invalid: $v.equipment.status.$invalid }"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="equipment.status && statusOption.id === equipment.status.id ? equipment.status : statusOption"
                v-for="statusOption in statuses"
                :key="statusOption.id"
              >
                {{ statusOption.name }}
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
