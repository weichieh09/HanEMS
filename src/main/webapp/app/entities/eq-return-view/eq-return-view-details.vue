<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="eqReturnView">
        <h2 class="jh-entity-heading" data-cy="eqReturnViewDetailsHeading">
          <span v-text="$t('hanEmsApp.eqReturnView.detail.title')">EqReturnView</span> {{ eqReturnView.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('hanEmsApp.eqReturnView.idno')">Idno</span>
          </dt>
          <dd>
            <span>{{ eqReturnView.idno }}</span>
          </dd>
          <dt>
            <span v-text="$t('hanEmsApp.eqReturnView.name')">Name</span>
          </dt>
          <dd>
            <span>{{ eqReturnView.name }}</span>
          </dd>
          <dt>
            <span v-text="$t('hanEmsApp.eqReturnView.itemId')">Item Id</span>
          </dt>
          <dd>
            <span>{{ eqReturnView.itemId }}</span>
          </dd>
          <dt>
            <span v-text="$t('hanEmsApp.eqReturnView.itemName')">Item Name</span>
          </dt>
          <dd>
            <span>{{ eqReturnView.itemName }}</span>
          </dd>
          <dt>
            <span v-text="$t('hanEmsApp.eqReturnView.statusId')">Status Id</span>
          </dt>
          <dd>
            <span>{{ eqReturnView.statusId }}</span>
          </dd>
          <dt>
            <span v-text="$t('hanEmsApp.eqReturnView.statusName')">Status Name</span>
          </dt>
          <dd>
            <span>{{ eqReturnView.statusName }}</span>
          </dd>
          <dt>
            <span v-text="$t('hanEmsApp.eqReturnView.personId')">Person Id</span>
          </dt>
          <dd>
            <span>{{ eqReturnView.personId }}</span>
          </dd>
          <dt>
            <span v-text="$t('hanEmsApp.eqReturnView.personName')">Person Name</span>
          </dt>
          <dd>
            <span>{{ eqReturnView.personName }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./eq-return-view-details.component.ts"></script>
