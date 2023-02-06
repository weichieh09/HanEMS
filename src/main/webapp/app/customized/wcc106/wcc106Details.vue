<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="eqView">
        <h2 class="jh-entity-heading" data-cy="eqItemDetailsHeading">[檢視] 設備</h2>
        <dl class="row jh-entity-details">
          <dt>
            <span>編號</span>
          </dt>
          <dd>
            <span>{{ eqView.id }}</span>
          </dd>
          <dt>
            <span>類別</span>
          </dt>
          <dd>
            <span>{{ eqView.eqItemName }}</span>
          </dd>
          <dt>
            <span>名稱</span>
          </dt>
          <dd>
            <span>{{ eqView.eqName }}</span>
          </dd>
          <dt>
            <span>描述</span>
          </dt>
          <dd>
            <span>{{ eqView.eqDescription }}</span>
          </dd>
          <dt>
            <span>建立時間</span>
          </dt>
          <dd>
            <span v-if="eqView.eqCreateDate">{{ $d(Date.parse(eqView.eqCreateDate), 'long') }}</span>
          </dd>
          <dt>
            <span>更新時間</span>
          </dt>
          <dd>
            <span v-if="eqView.eqModifyDate">{{ $d(Date.parse(eqView.eqModifyDate), 'long') }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./wcc106Details.component.ts"></script>
