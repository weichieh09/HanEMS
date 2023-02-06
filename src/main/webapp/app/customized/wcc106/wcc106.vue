<template>
  <div>
    <h2 id="page-heading" data-cy="EqViewHeading">
      <span v-text="$t('hanEmsApp.eqView.home.title')" id="eq-view-heading">Eq Views</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="$t('hanEmsApp.eqView.home.refreshListLabel')">Refresh List</span>
        </button>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && eqViews && eqViews.length === 0">
      <span v-text="$t('hanEmsApp.eqView.home.notFound')">No eqViews found</span>
    </div>
    <div class="table-responsive" v-if="eqViews && eqViews.length > 0">
      <table class="table table-striped" aria-describedby="eqViews">
        <thead>
          <tr>
            <th scope="row" v-on:click="changeOrder('id')">
              <span v-text="$t('global.field.id')">ID</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('eqItemId')">
              <span v-text="$t('hanEmsApp.eqView.eqItemId')">Eq Item Id</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'eqItemId'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('eqItemName')">
              <span v-text="$t('hanEmsApp.eqView.eqItemName')">Eq Item Name</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'eqItemName'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('eqName')">
              <span v-text="$t('hanEmsApp.eqView.eqName')">Eq Name</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'eqName'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('eqDescription')">
              <span v-text="$t('hanEmsApp.eqView.eqDescription')">Eq Description</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'eqDescription'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('eqCreateDate')">
              <span v-text="$t('hanEmsApp.eqView.eqCreateDate')">Eq Create Date</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'eqCreateDate'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('eqModifyDate')">
              <span v-text="$t('hanEmsApp.eqView.eqModifyDate')">Eq Modify Date</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'eqModifyDate'"></jhi-sort-indicator>
            </th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="eqView in eqViews" :key="eqView.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'EqViewView', params: { eqViewId: eqView.id } }">{{ eqView.id }}</router-link>
            </td>
            <td>{{ eqView.eqItemId }}</td>
            <td>{{ eqView.eqItemName }}</td>
            <td>{{ eqView.eqName }}</td>
            <td>{{ eqView.eqDescription }}</td>
            <td>{{ eqView.eqCreateDate ? $d(Date.parse(eqView.eqCreateDate), 'short') : '' }}</td>
            <td>{{ eqView.eqModifyDate ? $d(Date.parse(eqView.eqModifyDate), 'short') : '' }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'EqViewView', params: { eqViewId: eqView.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                  </button>
                </router-link>
              </div>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
    <b-modal ref="removeEntity" id="removeEntity">
      <span slot="modal-title"
        ><span id="hanEmsApp.eqView.delete.question" data-cy="eqViewDeleteDialogHeading" v-text="$t('entity.delete.title')"
          >Confirm delete operation</span
        ></span
      >
      <div class="modal-body">
        <p id="jhi-delete-eqView-heading" v-text="$t('hanEmsApp.eqView.delete.question', { id: removeId })">
          Are you sure you want to delete this Eq View?
        </p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-eqView"
          data-cy="entityConfirmDeleteButton"
          v-text="$t('entity.action.delete')"
          v-on:click="removeEqView()"
        >
          Delete
        </button>
      </div>
    </b-modal>
    <div v-show="eqViews && eqViews.length > 0">
      <div class="row justify-content-center">
        <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
      </div>
      <div class="row justify-content-center">
        <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage" :change="loadPage(page)"></b-pagination>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./wcc106.component.ts"></script>
