<template>
  <div>
    <h2 id="page-heading" data-cy="BlackListHeading">
      <span v-text="$t('hanEmsApp.blackList.home.title')" id="black-list-heading">Black Lists</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="$t('hanEmsApp.blackList.home.refreshListLabel')">Refresh List</span>
        </button>
        <router-link :to="{ name: 'BlackListCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-black-list"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="$t('hanEmsApp.blackList.home.createLabel')"> Create a new Black List </span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && blackLists && blackLists.length === 0">
      <span v-text="$t('hanEmsApp.blackList.home.notFound')">No blackLists found</span>
    </div>
    <div class="table-responsive" v-if="blackLists && blackLists.length > 0">
      <table class="table table-striped" aria-describedby="blackLists">
        <thead>
          <tr>
            <th scope="row" v-on:click="changeOrder('id')">
              <span v-text="$t('global.field.id')">ID</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('description')">
              <span v-text="$t('hanEmsApp.blackList.description')">Description</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'description'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('blackDate')">
              <span v-text="$t('hanEmsApp.blackList.blackDate')">Black Date</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'blackDate'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('createDate')">
              <span v-text="$t('hanEmsApp.blackList.createDate')">Create Date</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'createDate'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('modifyDate')">
              <span v-text="$t('hanEmsApp.blackList.modifyDate')">Modify Date</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'modifyDate'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('person.id')">
              <span v-text="$t('hanEmsApp.blackList.person')">Person</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'person.id'"></jhi-sort-indicator>
            </th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="blackList in blackLists" :key="blackList.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'BlackListView', params: { blackListId: blackList.id } }">{{ blackList.id }}</router-link>
            </td>
            <td>{{ blackList.description }}</td>
            <td>{{ blackList.blackDate ? $d(Date.parse(blackList.blackDate), 'short') : '' }}</td>
            <td>{{ blackList.createDate ? $d(Date.parse(blackList.createDate), 'short') : '' }}</td>
            <td>{{ blackList.modifyDate ? $d(Date.parse(blackList.modifyDate), 'short') : '' }}</td>
            <td>
              <div v-if="blackList.person">
                <router-link :to="{ name: 'PersonView', params: { personId: blackList.person.id } }">{{ blackList.person.id }}</router-link>
              </div>
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'BlackListView', params: { blackListId: blackList.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'BlackListEdit', params: { blackListId: blackList.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(blackList)"
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
        ><span id="hanEmsApp.blackList.delete.question" data-cy="blackListDeleteDialogHeading" v-text="$t('entity.delete.title')"
          >Confirm delete operation</span
        ></span
      >
      <div class="modal-body">
        <p id="jhi-delete-blackList-heading" v-text="$t('hanEmsApp.blackList.delete.question', { id: removeId })">
          Are you sure you want to delete this Black List?
        </p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-blackList"
          data-cy="entityConfirmDeleteButton"
          v-text="$t('entity.action.delete')"
          v-on:click="removeBlackList()"
        >
          Delete
        </button>
      </div>
    </b-modal>
    <div v-show="blackLists && blackLists.length > 0">
      <div class="row justify-content-center">
        <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
      </div>
      <div class="row justify-content-center">
        <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage" :change="loadPage(page)"></b-pagination>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./black-list.component.ts"></script>
