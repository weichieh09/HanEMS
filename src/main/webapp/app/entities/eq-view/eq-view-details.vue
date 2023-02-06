<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="eqView">
        <h2 class="jh-entity-heading" data-cy="eqViewDetailsHeading">
          <span v-text="$t('hanEmsApp.eqView.detail.title')">EqView</span> {{ eqView.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('hanEmsApp.eqView.eqItemId')">Eq Item Id</span>
          </dt>
          <dd>
            <span>{{ eqView.eqItemId }}</span>
          </dd>
          <dt>
            <span v-text="$t('hanEmsApp.eqView.eqItemName')">Eq Item Name</span>
          </dt>
          <dd>
            <span>{{ eqView.eqItemName }}</span>
          </dd>
          <dt>
            <span v-text="$t('hanEmsApp.eqView.eqName')">Eq Name</span>
          </dt>
          <dd>
            <span>{{ eqView.eqName }}</span>
          </dd>
          <dt>
            <span v-text="$t('hanEmsApp.eqView.eqDescription')">Eq Description</span>
          </dt>
          <dd>
            <span>{{ eqView.eqDescription }}</span>
          </dd>
          <dt>
            <span v-text="$t('hanEmsApp.eqView.eqCreateDate')">Eq Create Date</span>
          </dt>
          <dd>
            <span v-if="eqView.eqCreateDate">{{ $d(Date.parse(eqView.eqCreateDate), 'long') }}</span>
          </dd>
          <dt>
            <span v-text="$t('hanEmsApp.eqView.eqModifyDate')">Eq Modify Date</span>
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

<script lang="ts" src="./eq-view-details.component.ts"></script>
