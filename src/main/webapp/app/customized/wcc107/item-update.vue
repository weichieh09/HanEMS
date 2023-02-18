<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2 v-if="item.id" data-cy="ItemCreateUpdateHeading">[編輯] 類別</h2>
        <h2 v-else data-cy="ItemCreateUpdateHeading">[新增] 類別</h2>
        <div>
          <div class="form-group" v-if="item.id">
            <label for="id">編號</label>
            <input type="text" class="form-control" id="id" name="id" v-model="item.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="item-name">名稱</label>
            <input
              type="text"
              class="form-control"
              name="name"
              id="item-name"
              data-cy="name"
              :class="{ valid: !$v.item.name.$invalid, invalid: $v.item.name.$invalid }"
              v-model="$v.item.name.$model"
              required
            />
            <div v-if="$v.item.name.$anyDirty && $v.item.name.$invalid">
              <small class="form-text text-danger" v-if="!$v.item.name.required">必填</small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="item-description">描述</label>
            <textarea
              class="form-control"
              name="description"
              id="item-description"
              data-cy="description"
              :class="{ valid: !$v.item.description.$invalid, invalid: $v.item.description.$invalid }"
              v-model="$v.item.description.$model"
            ></textarea>
          </div>
          <div style="display: none" class="form-group">
            <label class="form-control-label" v-text="$t('hanEmsApp.item.createDate')" for="item-createDate">Create Date</label>
            <div class="d-flex">
              <input
                id="item-createDate"
                data-cy="createDate"
                type="datetime-local"
                class="form-control"
                name="createDate"
                :class="{ valid: !$v.item.createDate.$invalid, invalid: $v.item.createDate.$invalid }"
                :value="convertDateTimeFromServer($v.item.createDate.$model)"
                @change="updateInstantField('createDate', $event)"
              />
            </div>
          </div>
          <div style="display: none" class="form-group">
            <label class="form-control-label" v-text="$t('hanEmsApp.item.modifyDate')" for="item-modifyDate">Modify Date</label>
            <div class="d-flex">
              <input
                id="item-modifyDate"
                data-cy="modifyDate"
                type="datetime-local"
                class="form-control"
                name="modifyDate"
                :class="{ valid: !$v.item.modifyDate.$invalid, invalid: $v.item.modifyDate.$invalid }"
                :value="convertDateTimeFromServer($v.item.modifyDate.$model)"
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
            :disabled="$v.item.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./item-update.component.ts"></script>
