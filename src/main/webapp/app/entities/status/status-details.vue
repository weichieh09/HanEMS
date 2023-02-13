<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="status">
        <h2 class="jh-entity-heading" data-cy="statusDetailsHeading">
          <span v-text="$t('hanEmsApp.status.detail.title')">Status</span> {{ status.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('hanEmsApp.status.name')">Name</span>
          </dt>
          <dd>
            <span>{{ status.name }}</span>
          </dd>
          <dt>
            <span v-text="$t('hanEmsApp.status.description')">Description</span>
          </dt>
          <dd>
            <span>{{ status.description }}</span>
          </dd>
          <dt>
            <span v-text="$t('hanEmsApp.status.createDate')">Create Date</span>
          </dt>
          <dd>
            <span v-if="status.createDate">{{ $d(Date.parse(status.createDate), 'long') }}</span>
          </dd>
          <dt>
            <span v-text="$t('hanEmsApp.status.modifyDate')">Modify Date</span>
          </dt>
          <dd>
            <span v-if="status.modifyDate">{{ $d(Date.parse(status.modifyDate), 'long') }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <router-link v-if="status.id" :to="{ name: 'StatusEdit', params: { statusId: status.id } }" custom v-slot="{ navigate }">
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./status-details.component.ts"></script>
