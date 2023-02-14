<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="person">
        <h2 class="jh-entity-heading" data-cy="personDetailsHeading">
          <span v-text="$t('hanEmsApp.person.detail.title')">Person</span> {{ person.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('hanEmsApp.person.idno')">Idno</span>
          </dt>
          <dd>
            <span>{{ person.idno }}</span>
          </dd>
          <dt>
            <span v-text="$t('hanEmsApp.person.name')">Name</span>
          </dt>
          <dd>
            <span>{{ person.name }}</span>
          </dd>
          <dt>
            <span v-text="$t('hanEmsApp.person.phone')">Phone</span>
          </dt>
          <dd>
            <span>{{ person.phone }}</span>
          </dd>
          <dt>
            <span v-text="$t('hanEmsApp.person.pending')">Pending</span>
          </dt>
          <dd>
            <span>{{ person.pending }}</span>
          </dd>
          <dt>
            <span v-text="$t('hanEmsApp.person.createDate')">Create Date</span>
          </dt>
          <dd>
            <span v-if="person.createDate">{{ $d(Date.parse(person.createDate), 'long') }}</span>
          </dd>
          <dt>
            <span v-text="$t('hanEmsApp.person.modifyDate')">Modify Date</span>
          </dt>
          <dd>
            <span v-if="person.modifyDate">{{ $d(Date.parse(person.modifyDate), 'long') }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <router-link v-if="person.id" :to="{ name: 'PersonEdit', params: { personId: person.id } }" custom v-slot="{ navigate }">
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./person-details.component.ts"></script>
