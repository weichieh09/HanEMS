<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="eqItem">
        <h2 class="jh-entity-heading" data-cy="eqItemDetailsHeading">
          <span v-text="$t('hanEmsApp.eqItem.detail.title')">EqItem</span> {{ eqItem.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('hanEmsApp.eqItem.name')">Name</span>
          </dt>
          <dd>
            <span>{{ eqItem.name }}</span>
          </dd>
          <dt>
            <span v-text="$t('hanEmsApp.eqItem.description')">Description</span>
          </dt>
          <dd>
            <span>{{ eqItem.description }}</span>
          </dd>
          <dt>
            <span v-text="$t('hanEmsApp.eqItem.createDate')">Create Date</span>
          </dt>
          <dd>
            <span v-if="eqItem.createDate">{{ $d(Date.parse(eqItem.createDate), 'long') }}</span>
          </dd>
          <dt>
            <span v-text="$t('hanEmsApp.eqItem.modifyDate')">Modify Date</span>
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

<script lang="ts" src="./eq-item-details.component.ts"></script>
