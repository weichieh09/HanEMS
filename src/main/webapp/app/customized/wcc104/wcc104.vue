<template>
  <div>
    <div class="row justify-content-center">
      <div class="col-md-8 toastify-container">
        <h1>歸還</h1>
        <div class="alert alert-danger" v-if="success" role="alert"><strong>錯誤!</strong> 請檢查您的...</div>
      </div>
      <br /><br /><br />
    </div>
    <div class="row justify-content-center">
      <div class="col-md-8">
        <form id="rent-form" name="rentForm" role="form" v-on:submit.prevent="rent()" v-if="!success" no-validate>
          <div class="form-group">
            <label class="form-control-label">學號</label>
            <input type="text" class="form-control" v-model="$v.returnAccount.idno" />
          </div>
          <div class="form-group">
            <label class="form-control-label">姓名</label>
            <input
              type="text"
              class="form-control"
              :class="{ valid: !$v.returnAccount.name.$invalid, invalid: $v.returnAccount.name.$invalid }"
              v-model="$v.returnAccount.name.$model"
              minlength="1"
              maxlength="50"
              required
            />
            <div v-if="$v.returnAccount.name.$anyDirty && $v.returnAccount.name.$invalid">
              <small class="form-text text-danger" v-if="!$v.returnAccount.name.required">必填</small>
              <small class="form-text text-danger" v-if="!$v.returnAccount.name.minLength">最少一字</small>
              <small class="form-text text-danger" v-if="!$v.returnAccount.name.maxLength">最多五十字</small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label">電話</label>
            <input type="text" class="form-control" v-model="$v.returnAccount.phone" />
          </div>
          <div class="form-group">
            <label class="form-control-label">用途</label>
            <input type="text" class="form-control" />
          </div>
          <button type="submit" :disabled="$v.$invalid" class="btn btn-primary">送出</button>
          <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info">返回</button>
        </form>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./wcc104.component.ts"></script>
