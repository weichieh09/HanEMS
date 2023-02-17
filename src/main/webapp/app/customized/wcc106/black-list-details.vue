<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="blackList">
        <h2 class="jh-entity-heading" data-cy="blackListDetailsHeading"><span>黑名單</span> {{ blackList.id }}</h2>
        <dl class="row jh-entity-details">
          <dt>
            <span>姓名</span>
          </dt>
          <dd>
            <div v-if="blackList.person">{{ blackList.person.name }}</div>
          </dd>
          <dt>
            <span>日期</span>
          </dt>
          <dd>
            <span v-if="blackList.blackDate">{{ $d(Date.parse(blackList.blackDate), 'long') }}</span>
          </dd>
          <dt>
            <span>原因</span>
          </dt>
          <dd>
            <span>{{ blackList.description }}</span>
          </dd>
          <!-- <dt>
            <span v-text="$t('hanEmsApp.blackList.createDate')">Create Date</span>
          </dt>
          <dd>
            <span v-if="blackList.createDate">{{ $d(Date.parse(blackList.createDate), 'long') }}</span>
          </dd>
          <dt>
            <span v-text="$t('hanEmsApp.blackList.modifyDate')">Modify Date</span>
          </dt>
          <dd>
            <span v-if="blackList.modifyDate">{{ $d(Date.parse(blackList.modifyDate), 'long') }}</span>
          </dd> -->
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <router-link v-if="blackList.id" :to="{ name: 'Wcc106Edit', params: { blackListId: blackList.id } }" custom v-slot="{ navigate }">
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./black-list-details.component.ts"></script>
