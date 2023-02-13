<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="itemView">
        <h2 class="jh-entity-heading" data-cy="itemViewDetailsHeading">
          <span v-text="$t('hanEmsApp.itemView.detail.title')">ItemView</span> {{ itemView.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('hanEmsApp.itemView.name')">Name</span>
          </dt>
          <dd>
            <span>{{ itemView.name }}</span>
          </dd>
          <dt>
            <span v-text="$t('hanEmsApp.itemView.total')">Total</span>
          </dt>
          <dd>
            <span>{{ itemView.total }}</span>
          </dd>
          <dt>
            <span v-text="$t('hanEmsApp.itemView.ready')">Ready</span>
          </dt>
          <dd>
            <span>{{ itemView.ready }}</span>
          </dd>
          <dt>
            <span v-text="$t('hanEmsApp.itemView.working')">Working</span>
          </dt>
          <dd>
            <span>{{ itemView.working }}</span>
          </dd>
          <dt>
            <span v-text="$t('hanEmsApp.itemView.canNot')">Can Not</span>
          </dt>
          <dd>
            <span>{{ itemView.canNot }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./item-view-details.component.ts"></script>
