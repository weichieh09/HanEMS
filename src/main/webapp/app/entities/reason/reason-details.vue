<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="reason">
        <h2 class="jh-entity-heading" data-cy="reasonDetailsHeading">
          <span v-text="$t('hanEmsApp.reason.detail.title')">Reason</span> {{ reason.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('hanEmsApp.reason.name')">Name</span>
          </dt>
          <dd>
            <span>{{ reason.name }}</span>
          </dd>
          <dt>
            <span v-text="$t('hanEmsApp.reason.description')">Description</span>
          </dt>
          <dd>
            <span>{{ reason.description }}</span>
          </dd>
          <dt>
            <span v-text="$t('hanEmsApp.reason.createDate')">Create Date</span>
          </dt>
          <dd>
            <span v-if="reason.createDate">{{ $d(Date.parse(reason.createDate), 'long') }}</span>
          </dd>
          <dt>
            <span v-text="$t('hanEmsApp.reason.modifyDate')">Modify Date</span>
          </dt>
          <dd>
            <span v-if="reason.modifyDate">{{ $d(Date.parse(reason.modifyDate), 'long') }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <router-link v-if="reason.id" :to="{ name: 'ReasonEdit', params: { reasonId: reason.id } }" custom v-slot="{ navigate }">
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./reason-details.component.ts"></script>
