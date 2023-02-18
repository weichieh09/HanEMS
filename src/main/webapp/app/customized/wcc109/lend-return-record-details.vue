<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="lendReturnRecord">
        <h2 class="jh-entity-heading" data-cy="lendReturnRecordDetailsHeading">
          <span v-text="$t('hanEmsApp.lendReturnRecord.detail.title')">[檢視] 借出/歸還 </span> {{ lendReturnRecord.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span>設備名稱</span>
          </dt>
          <dd>
            <div v-if="lendReturnRecord.equipment">
              {{ lendReturnRecord.equipment.name }}
            </div>
          </dd>
          <dt>
            <span>借出人名稱</span>
          </dt>
          <dd>
            <div v-if="lendReturnRecord.person">{{ lendReturnRecord.person.name }}</div>
          </dd>
          <dt>
            <span>借出時間</span>
          </dt>
          <dd>
            <span v-if="lendReturnRecord.lendDate">{{ $d(Date.parse(lendReturnRecord.lendDate), 'long') }}</span>
          </dd>
          <dt>
            <span>歸還時間</span>
          </dt>
          <dd>
            <span v-if="lendReturnRecord.returnDate">{{ $d(Date.parse(lendReturnRecord.returnDate), 'long') }}</span>
          </dd>
          <!-- <dt>
            <span v-text="$t('hanEmsApp.lendReturnRecord.description')">Description</span>
          </dt>
          <dd>
            <span>{{ lendReturnRecord.description }}</span>
          </dd> -->
          <!-- <dt>
            <span v-text="$t('hanEmsApp.lendReturnRecord.createDate')">Create Date</span>
          </dt>
          <dd>
            <span v-if="lendReturnRecord.createDate">{{ $d(Date.parse(lendReturnRecord.createDate), 'long') }}</span>
          </dd> -->
          <!-- <dt>
            <span v-text="$t('hanEmsApp.lendReturnRecord.modifyDate')">Modify Date</span>
          </dt>
          <dd>
            <span v-if="lendReturnRecord.modifyDate">{{ $d(Date.parse(lendReturnRecord.modifyDate), 'long') }}</span>
          </dd> -->
          <!-- <dt>
            <span v-text="$t('hanEmsApp.lendReturnRecord.reason')">Reason</span>
          </dt>
          <dd>
            <div v-if="lendReturnRecord.reason">
              <router-link :to="{ name: 'ReasonView', params: { reasonId: lendReturnRecord.reason.id } }">{{
                lendReturnRecord.reason.id
              }}</router-link>
            </div>
          </dd> -->
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <!-- <router-link
          v-if="lendReturnRecord.id"
          :to="{ name: 'LendReturnRecordEdit', params: { lendReturnRecordId: lendReturnRecord.id } }"
          custom
          v-slot="{ navigate }"
        >
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
          </button>
        </router-link> -->
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./lend-return-record-details.component.ts"></script>
