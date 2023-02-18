<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="item">
        <h2 class="jh-entity-heading" data-cy="itemDetailsHeading"><span>[檢視] 類別 </span> {{ item.id }}</h2>
        <dl class="row jh-entity-details">
          <dt>
            <span>名稱</span>
          </dt>
          <dd>
            <span>{{ item.name }}</span>
          </dd>
          <dt>
            <span>描述</span>
          </dt>
          <dd>
            <span>{{ item.description }}</span>
          </dd>
          <dt>
            <span>建立時間</span>
          </dt>
          <dd>
            <span v-if="item.createDate">{{ $d(Date.parse(item.createDate), 'long') }}</span>
          </dd>
          <!-- <dt>
            <span v-text="$t('hanEmsApp.item.modifyDate')">Modify Date</span>
          </dt>
          <dd>
            <span v-if="item.modifyDate">{{ $d(Date.parse(item.modifyDate), 'long') }}</span>
          </dd> -->
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <router-link v-if="item.id" :to="{ name: 'Wcc107Edit', params: { itemId: item.id } }" custom v-slot="{ navigate }">
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./item-details.component.ts"></script>
