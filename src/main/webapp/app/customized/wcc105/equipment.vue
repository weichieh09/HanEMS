<template>
  <div>
    <h2 id="page-heading" data-cy="EquipmentHeading">
      <span id="equipment-heading">設備</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span>更新</span>
        </button>
        <!-- <router-link :to="{ name: 'EquipmentCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-equipment"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="$t('hanEmsApp.equipment.home.createLabel')"> Create a new Equipment </span>
          </button>
        </router-link> -->
      </div>
    </h2>
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
              <span>序號</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'idno'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('name')">
              <span>名稱</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'name'"></jhi-sort-indicator>
            </th>
            <!-- <th scope="row" v-on:click="changeOrder('description')">
              <span>描述</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'description'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('createDate')">
              <span v-text="$t('hanEmsApp.equipment.createDate')">Create Date</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'createDate'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('modifyDate')">
              <span v-text="$t('hanEmsApp.equipment.modifyDate')">Modify Date</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'modifyDate'"></jhi-sort-indicator>
            </th> -->
            <th scope="row" v-on:click="changeOrder('item.id')">
              <span>類別</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'item.id'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('status.id')">
              <span>狀態</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'status.id'"></jhi-sort-indicator>
            </th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="equipment in equipment" :key="equipment.id" data-cy="entityTable">
            <td>{{ equipment.id }}</td>
            <td>{{ equipment.idno }}</td>
            <td>{{ equipment.name }}</td>
            <!-- <td>{{ equipment.description }}</td>
            <td>{{ equipment.createDate ? $d(Date.parse(equipment.createDate), 'short') : '' }}</td>
            <td>{{ equipment.modifyDate ? $d(Date.parse(equipment.modifyDate), 'short') : '' }}</td> -->
            <td>{{ equipment.item.name }}</td>
            <td>{{ equipment.status.name }}</td>
            <td class="text-right">
              <div class="btn-group">
                <!-- <router-link :to="{ name: 'EquipmentView', params: { equipmentId: equipment.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                  </button>
                </router-link> -->
                <button @click="rentEq(equipment.id)" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                  <font-awesome-icon icon="chevron-circle-left"></font-awesome-icon>
                  <span class="d-none d-md-inline">出借</span>
                </button>
                <button @click="returnEq(equipment.id)" :disabled="true" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                  <span class="d-none d-md-inline">歸還</span>
                  <font-awesome-icon icon="chevron-circle-right"></font-awesome-icon>
                </button>
                <!-- <b-button
                  v-on:click="prepareRemove(equipment)"
                  variant="danger"
                  class="btn btn-sm"
                  data-cy="entityDeleteButton"
                  v-b-modal.removeEntity
                >
                  <font-awesome-icon icon="times"></font-awesome-icon>
                  <span class="d-none d-md-inline" v-text="$t('entity.action.delete')">Delete</span>
                </b-button> -->
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
