<template>
  <div>
    <h2 id="page-heading" data-cy="ReasonHeading">
      <span v-text="$t('hanEmsApp.reason.home.title')" id="reason-heading">Reasons</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="$t('hanEmsApp.reason.home.refreshListLabel')">Refresh List</span>
        </button>
        <router-link :to="{ name: 'ReasonCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-reason"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="$t('hanEmsApp.reason.home.createLabel')"> Create a new Reason </span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && reasons && reasons.length === 0">
      <span v-text="$t('hanEmsApp.reason.home.notFound')">No reasons found</span>
    </div>
    <div class="table-responsive" v-if="reasons && reasons.length > 0">
      <table class="table table-striped" aria-describedby="reasons">
        <thead>
          <tr>
            <th scope="row" v-on:click="changeOrder('id')">
              <span v-text="$t('global.field.id')">ID</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('name')">
              <span v-text="$t('hanEmsApp.reason.name')">Name</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'name'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('description')">
              <span v-text="$t('hanEmsApp.reason.description')">Description</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'description'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('createDate')">
              <span v-text="$t('hanEmsApp.reason.createDate')">Create Date</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'createDate'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('modifyDate')">
              <span v-text="$t('hanEmsApp.reason.modifyDate')">Modify Date</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'modifyDate'"></jhi-sort-indicator>
            </th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="reason in reasons" :key="reason.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'ReasonView', params: { reasonId: reason.id } }">{{ reason.id }}</router-link>
            </td>
            <td>{{ reason.name }}</td>
            <td>{{ reason.description }}</td>
            <td>{{ reason.createDate ? $d(Date.parse(reason.createDate), 'short') : '' }}</td>
            <td>{{ reason.modifyDate ? $d(Date.parse(reason.modifyDate), 'short') : '' }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'ReasonView', params: { reasonId: reason.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'ReasonEdit', params: { reasonId: reason.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(reason)"
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
        ><span id="hanEmsApp.reason.delete.question" data-cy="reasonDeleteDialogHeading" v-text="$t('entity.delete.title')"
          >Confirm delete operation</span
        ></span
      >
      <div class="modal-body">
        <p id="jhi-delete-reason-heading" v-text="$t('hanEmsApp.reason.delete.question', { id: removeId })">
          Are you sure you want to delete this Reason?
        </p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-reason"
          data-cy="entityConfirmDeleteButton"
          v-text="$t('entity.action.delete')"
          v-on:click="removeReason()"
        >
          Delete
        </button>
      </div>
    </b-modal>
    <div v-show="reasons && reasons.length > 0">
      <div class="row justify-content-center">
        <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
      </div>
      <div class="row justify-content-center">
        <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage" :change="loadPage(page)"></b-pagination>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./reason.component.ts"></script>
