<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <div v-if="eqItem.id">
          <h2 id="hanEmsApp.eqItem.home.createOrEditLabel" data-cy="EqItemCreateUpdateHeading">編輯 類別</h2>
        </div>
        <div v-else>
          <h2 id="hanEmsApp.eqItem.home.createOrEditLabel" data-cy="EqItemCreateUpdateHeading">新增 類別</h2>
        </div>
        <div>
          <div class="form-group" v-if="eqItem.id">
            <label for="id">編號</label>
            <input type="text" class="form-control" id="id" name="id" v-model="eqItem.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="eq-item-name">名稱</label>
            <input
              type="text"
              class="form-control"
              name="name"
              id="eq-item-name"
              data-cy="name"
              :class="{ valid: !$v.eqItem.name.$invalid, invalid: $v.eqItem.name.$invalid }"
              v-model="$v.eqItem.name.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="eq-item-description">描述</label>
            <input
              type="text"
              class="form-control"
              name="description"
              id="eq-item-description"
              data-cy="description"
              :class="{ valid: !$v.eqItem.description.$invalid, invalid: $v.eqItem.description.$invalid }"
              v-model="$v.eqItem.description.$model"
            />
          </div>
          <div v-if="eqItem.id" class="form-group">
            <label class="form-control-label" for="eq-item-createDate">建立時間</label>
            <div class="d-flex">
              <input
                id="eq-item-createDate"
                data-cy="createDate"
                type="datetime-local"
                class="form-control"
                name="createDate"
                :class="{ valid: !$v.eqItem.createDate.$invalid, invalid: $v.eqItem.createDate.$invalid }"
                :value="convertDateTimeFromServer($v.eqItem.createDate.$model)"
                @change="updateInstantField('createDate', $event)"
              />
            </div>
          </div>
          <div v-if="eqItem.id" class="form-group">
            <label class="form-control-label" for="eq-item-modifyDate">建立時間</label>
            <div class="d-flex">
              <input
                id="eq-item-modifyDate"
                data-cy="modifyDate"
                type="datetime-local"
                class="form-control"
                name="modifyDate"
                :class="{ valid: !$v.eqItem.modifyDate.$invalid, invalid: $v.eqItem.modifyDate.$invalid }"
                :value="convertDateTimeFromServer($v.eqItem.modifyDate.$model)"
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
            :disabled="$v.eqItem.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./wcc105Update.component.ts"></script>
