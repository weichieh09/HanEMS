<template>
  <div>
    <h2 id="page-heading" data-cy="EquipmentHeading">
      <span id="equipment-heading">[管理] 設備</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span>更新</span>
        </button>
        <router-link :to="{ name: 'Wcc108Create' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-equipment"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span>新增</span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <b-container fluid>
      <b-row>
        <b-col cols="4">
          <select class="form-control" id="black-list-person" data-cy="person" name="person">
            <option v-bind:value="0">類別</option>
            <option v-bind:value="itemOption.id" v-for="itemOption in items" :key="itemOption.id">
              {{ itemOption.name }}
            </option>
          </select>
        </b-col>
        <b-col cols="6">
          <input type="text" class="form-control" placeholder="名稱" />
        </b-col>
        <b-col cols="2">
          <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
            <font-awesome-icon icon="search" :spin="isFetching"></font-awesome-icon>
            <span>搜尋</span>
          </button>
        </b-col>
      </b-row>
    </b-container>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && equipment && equipment.length === 0">
      <span>沒有設備資料</span>
    </div>
    <div class="table-responsive" v-if="equipment && equipment.length > 0">
      <table class="table table-striped" aria-describedby="equipment">
        <thead>
          <tr>
            <th scope="row" v-on:click="changeOrder('id')">
              <span>編號</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('idno')">
              <span>設備序號</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'idno'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('name')">
              <span>名稱</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'name'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('description')">
              <span>描述</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'description'"></jhi-sort-indicator>
            </th>
            <!-- <th scope="row" v-on:click="changeOrder('createDate')">
              <span v-text="$t('hanEmsApp.equipment.createDate')">Create Date</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'createDate'"></jhi-sort-indicator>
            </th> -->
            <!-- <th scope="row" v-on:click="changeOrder('modifyDate')">
              <span v-text="$t('hanEmsApp.equipment.modifyDate')">Modify Date</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'modifyDate'"></jhi-sort-indicator>
            </th> -->
            <th scope="row" v-on:click="changeOrder('item.id')">
              <span>類別</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'item.id'"></jhi-sort-indicator>
            </th>
            <!-- <th scope="row" v-on:click="changeOrder('status.id')">
              <span>狀態</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse"
                :field-name="'status.id'"></jhi-sort-indicator>
            </th> -->
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="equipment in equipment" :key="equipment.id" data-cy="entityTable">
            <td>{{ equipment.id }}</td>
            <td>{{ equipment.idno }}</td>
            <td>{{ equipment.name }}</td>
            <td>{{ equipment.description }}</td>
            <!-- <td>{{ equipment.createDate ? $d(Date.parse(equipment.createDate), 'short') : '' }}</td> -->
            <!-- <td>{{ equipment.modifyDate ? $d(Date.parse(equipment.modifyDate), 'short') : '' }}</td> -->
            <td>
              <div v-if="equipment.item">
                {{ equipment.item.name }}
              </div>
            </td>
            <!-- <td>
              <div v-if="equipment.status.id == 1">
                <span style="background-color: springgreen; color: green">{{ equipment.status.name }}</span>
              </div>
              <div v-if="equipment.status.id == 2">
                <span style="background-color: pink; color: red">{{ equipment.status.name }}</span>
              </div>
              <div v-if="equipment.status.id == 3">
                <span style="background-color: darkgrey; color: black">{{ equipment.status.name }}</span>
              </div>
            </td> -->

            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'Wcc108View', params: { equipmentId: equipment.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'Wcc108Edit', params: { equipmentId: equipment.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(equipment)"
                  variant="danger"
                  class="btn btn-sm"
                  data-cy="entityDeleteButton"
                  v-b-modal.removeEntity
                >
                  <font-awesome-icon icon="times"></font-awesome-icon>
                  <span class="d-none d-md-inline" v-text="$t('entity.action.delete')">Delete</span>
                </b-button>
              </div>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
    <b-modal ref="removeEntity" id="removeEntity">
      <span slot="modal-title"
        ><span id="hanEmsApp.equipment.delete.question" data-cy="equipmentDeleteDialogHeading" v-text="$t('entity.delete.title')"
          >Confirm delete operation</span
        ></span
      >
      <div class="modal-body">
        <p id="jhi-delete-equipment-heading" v-text="$t('hanEmsApp.equipment.delete.question', { id: removeId })">
          Are you sure you want to delete this Equipment?
        </p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-equipment"
          data-cy="entityConfirmDeleteButton"
          v-text="$t('entity.action.delete')"
          v-on:click="removeEquipment()"
        >
          Delete
        </button>
      </div>
    </b-modal>
    <div v-show="equipment && equipment.length > 0">
      <div class="row justify-content-center">
        <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
      </div>
      <div class="row justify-content-center">
        <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage" :change="loadPage(page)"></b-pagination>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./equipment.component.ts"></script>
