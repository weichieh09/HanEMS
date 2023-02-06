<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <div v-if="eqView.id">
          <h2 id="hanEmsApp.eqView.home.createOrEditLabel" data-cy="EqViewCreateUpdateHeading">[編輯] 設備</h2>
        </div>
        <div v-else>
          <h2 id="hanEmsApp.eqView.home.createOrEditLabel" data-cy="EqViewCreateUpdateHeading">[新增] 設備</h2>
        </div>
        <div>
          <div class="form-group" v-if="eqView.id">
            <label for="id">編號</label>
            <input type="text" class="form-control" id="id" name="id" v-model="eqView.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="eq-view-name">類別</label>
            <b-form-select v-model="$v.eqView.eqItemId.$model" :options="options"></b-form-select>
            <!-- <input
              type="text"
              class="form-control"
              name="eqItemId"
              id="eq-view-eqItemId"
              data-cy="eqItemId"
              :class="{ valid: !$v.eqView.eqItemId.$invalid, invalid: $v.eqView.eqItemId.$invalid }"
              v-model="$v.eqView.eqItemId.$model"
            /> -->
          </div>
          <div class="form-group">
            <label class="form-control-label" for="eq-view-name">名稱</label>
            <input
              type="text"
              class="form-control"
              name="name"
              id="eq-view-name"
              data-cy="name"
              :class="{ valid: !$v.eqView.eqName.$invalid, invalid: $v.eqView.eqName.$invalid }"
              v-model="$v.eqView.eqName.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="eq-view-description">描述</label>
            <input
              type="text"
              class="form-control"
              name="description"
              id="eq-view-description"
              data-cy="description"
              :class="{ valid: !$v.eqView.eqDescription.$invalid, invalid: $v.eqView.eqDescription.$invalid }"
              v-model="$v.eqView.eqDescription.$model"
            />
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
            :disabled="$v.eqView.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./wcc106Update.component.ts"></script>
