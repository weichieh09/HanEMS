<template>
  <div>
    <h2 id="page-heading" data-cy="EqReturnViewHeading">
      <span v-text="$t('hanEmsApp.eqReturnView.home.title')" id="eq-return-view-heading">Eq Return Views</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="$t('hanEmsApp.eqReturnView.home.refreshListLabel')">Refresh List</span>
        </button>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && eqReturnViews && eqReturnViews.length === 0">
      <span v-text="$t('hanEmsApp.eqReturnView.home.notFound')">No eqReturnViews found</span>
    </div>
    <div class="table-responsive" v-if="eqReturnViews && eqReturnViews.length > 0">
      <table class="table table-striped" aria-describedby="eqReturnViews">
        <thead>
          <tr>
            <th scope="row" v-on:click="changeOrder('id')">
              <span v-text="$t('global.field.id')">ID</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('idno')">
              <span v-text="$t('hanEmsApp.eqReturnView.idno')">Idno</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'idno'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('name')">
              <span v-text="$t('hanEmsApp.eqReturnView.name')">Name</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'name'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('itemId')">
              <span v-text="$t('hanEmsApp.eqReturnView.itemId')">Item Id</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'itemId'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('statusId')">
              <span v-text="$t('hanEmsApp.eqReturnView.statusId')">Status Id</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'statusId'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('personId')">
              <span v-text="$t('hanEmsApp.eqReturnView.personId')">Person Id</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'personId'"></jhi-sort-indicator>
            </th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="eqReturnView in eqReturnViews" :key="eqReturnView.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'EqReturnViewView', params: { eqReturnViewId: eqReturnView.id } }">{{
                eqReturnView.id
              }}</router-link>
            </td>
            <td>{{ eqReturnView.idno }}</td>
            <td>{{ eqReturnView.name }}</td>
            <td>{{ eqReturnView.itemId }}</td>
            <td>{{ eqReturnView.statusId }}</td>
            <td>{{ eqReturnView.personId }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'EqReturnViewView', params: { eqReturnViewId: eqReturnView.id } }" custom v-slot="{ navigate }">
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
        ><span id="hanEmsApp.eqReturnView.delete.question" data-cy="eqReturnViewDeleteDialogHeading" v-text="$t('entity.delete.title')"
          >Confirm delete operation</span
        ></span
      >
      <div class="modal-body">
        <p id="jhi-delete-eqReturnView-heading" v-text="$t('hanEmsApp.eqReturnView.delete.question', { id: removeId })">
          Are you sure you want to delete this Eq Return View?
        </p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-eqReturnView"
          data-cy="entityConfirmDeleteButton"
          v-text="$t('entity.action.delete')"
          v-on:click="removeEqReturnView()"
        >
          Delete
        </button>
      </div>
    </b-modal>
    <div v-show="eqReturnViews && eqReturnViews.length > 0">
      <div class="row justify-content-center">
        <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
      </div>
      <div class="row justify-content-center">
        <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage" :change="loadPage(page)"></b-pagination>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./eq-return-view.component.ts"></script>
