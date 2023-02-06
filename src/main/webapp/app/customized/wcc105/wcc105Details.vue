<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="eqItem">
        <h2 class="jh-entity-heading" data-cy="eqItemDetailsHeading">[檢視] 類別</h2>
        <dl class="row jh-entity-details">
          <dt>
            <span>編號</span>
          </dt>
          <dd>
            <span>{{ eqItem.id }}</span>
          </dd>
          <dt>
            <span>名稱</span>
          </dt>
          <dd>
            <span>{{ eqItem.name }}</span>
          </dd>
          <dt>
            <span>描述</span>
          </dt>
          <dd>
            <span>{{ eqItem.description }}</span>
          </dd>
          <dt>
            <span>建立時間</span>
          </dt>
          <dd>
            <span v-if="eqItem.createDate">{{ $d(Date.parse(eqItem.createDate), 'long') }}</span>
          </dd>
          <dt>
            <span>更新時間</span>
          </dt>
          <dd>
            <span v-if="eqItem.modifyDate">{{ $d(Date.parse(eqItem.modifyDate), 'long') }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <router-link v-if="eqItem.id" :to="{ name: 'EqItemEdit', params: { eqItemId: eqItem.id } }" custom v-slot="{ navigate }">
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./wcc105Details.component.ts"></script>
