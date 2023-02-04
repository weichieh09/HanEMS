<template>
  <div>
    <div class="row justify-content-center">
      <div class="col-md-8 toastify-container">
        <h1>借用</h1>
        <div class="alert alert-danger" v-if="success" role="alert"><strong>錯誤!</strong> 請檢查您的...</div>
      </div>
      <br /><br /><br />
    </div>
    <div class="row justify-content-center">
      <div class="col-md-8">
        <form id="rent-form" name="rentForm" role="form" v-on:submit.prevent="rent()" v-if="!success" no-validate>
          <div class="form-group">
            <label class="form-control-label">學號</label>
            <input type="text" class="form-control" v-model="$v.rentAccount.idno" />
          </div>
          <div class="form-group">
            <label class="form-control-label">姓名</label>
            <input
              type="text"
              class="form-control"
              :class="{ valid: !$v.rentAccount.name.$invalid, invalid: $v.rentAccount.name.$invalid }"
              v-model="$v.rentAccount.name.$model"
              minlength="1"
              maxlength="50"
              required
            />
            <div v-if="$v.rentAccount.name.$anyDirty && $v.rentAccount.name.$invalid">
              <small class="form-text text-danger" v-if="!$v.rentAccount.name.required">必填</small>
              <small class="form-text text-danger" v-if="!$v.rentAccount.name.minLength">最少一字</small>
              <small class="form-text text-danger" v-if="!$v.rentAccount.name.maxLength">最多五十字</small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label">電話</label>
            <input type="text" class="form-control" v-model="$v.rentAccount.phone" />
          </div>
          <div class="form-group">
            <label class="form-control-label">用途</label>
            <input type="text" class="form-control" />
          </div>
          <button type="submit" :disabled="$v.$invalid" class="btn btn-primary btn-lg">送出</button>
          <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info btn-lg">返回</button>
        </form>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./wcc102.component.ts"></script>
