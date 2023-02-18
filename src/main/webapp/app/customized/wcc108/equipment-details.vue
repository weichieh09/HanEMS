<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="equipment">
        <h2 class="jh-entity-heading" data-cy="equipmentDetailsHeading"><span>[檢視] 設備 </span> {{ equipment.id }}</h2>
        <dl class="row jh-entity-details">
          <dt>
            <span>設備序號</span>
          </dt>
          <dd>
            <span>{{ equipment.idno }}</span>
          </dd>
          <dt>
            <span>名稱</span>
          </dt>
          <dd>
            <span>{{ equipment.name }}</span>
          </dd>
          <dt>
            <span>描述</span>
          </dt>
          <dd>
            <span>{{ equipment.description }}</span>
          </dd>
          <dt>
            <span>建立時間</span>
          </dt>
          <dd>
            <span v-if="equipment.createDate">{{ $d(Date.parse(equipment.createDate), 'long') }}</span>
          </dd>
          <!-- <dt>
              <span v-text="$t('hanEmsApp.equipment.modifyDate')">Modify Date</span>
            </dt>
            <dd>
              <span v-if="equipment.modifyDate">{{ $d(Date.parse(equipment.modifyDate), 'long') }}</span>
            </dd> -->
          <dt>
            <span>類別</span>
          </dt>
          <dd>
            <div v-if="equipment.item">
              {{ equipment.item.name }}
            </div>
          </dd>
          <dt>
            <span>狀態</span>
          </dt>
          <dd>
            <div v-if="equipment.status">
              <div v-if="equipment.status.id == 1">
                <span style="background-color: springgreen; color: green">{{ equipment.status.name }}</span>
              </div>
              <div v-if="equipment.status.id == 2">
                <span style="background-color: pink; color: red">{{ equipment.status.name }}</span>
              </div>
              <div v-if="equipment.status.id == 3">
                <span style="background-color: darkgrey; color: black">{{ equipment.status.name }}</span>
              </div>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <router-link v-if="equipment.id" :to="{ name: 'Wcc108Edit', params: { equipmentId: equipment.id } }" custom v-slot="{ navigate }">
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./equipment-details.component.ts"></script>
