<template>
  <div>
    <h2 id="page-heading" data-cy="ItemViewHeading">
      <span id="item-view-heading">總覽</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span>更新</span>
        </button>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && itemViews && itemViews.length === 0">
      <span v-text="$t('hanEmsApp.itemView.home.notFound')">沒有總覽資料</span>
    </div>
    <div class="table-responsive" v-if="itemViews && itemViews.length > 0">
      <table class="table table-striped" aria-describedby="itemViews">
        <thead>
          <tr>
            <th scope="row" v-on:click="changeOrder('id')">
              <span>編號</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('name')">
              <span>名稱</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'name'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('total')">
              <span>總數</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'total'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('ready')">
              <span>可出借</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'ready'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('working')">
              <span>出借中</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'working'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('canNot')">
              <span>不可出借</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'canNot'"></jhi-sort-indicator>
            </th>
            <!-- <th scope="row"><span>操作</span></th> -->
          </tr>
        </thead>
        <tbody>
          <tr v-for="itemView in itemViews" :key="itemView.id" data-cy="entityTable">
            <td>{{ itemView.id }}</td>
            <td>{{ itemView.name }}</td>
            <td>{{ itemView.total }}</td>
            <td>{{ itemView.ready }}</td>
            <td>{{ itemView.working }}</td>
            <td>{{ itemView.canNot }}</td>
            <!-- <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'ItemViewView', params: { itemViewId: itemView.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                  </button>
                </router-link>
              </div>
            </td> -->
          </tr>
        </tbody>
      </table>
    </div>
    <b-modal ref="removeEntity" id="removeEntity">
      <span slot="modal-title"
        ><span id="hanEmsApp.itemView.delete.question" data-cy="itemViewDeleteDialogHeading" v-text="$t('entity.delete.title')"
          >Confirm delete operation</span
        ></span
      >
      <div class="modal-body">
        <p id="jhi-delete-itemView-heading" v-text="$t('hanEmsApp.itemView.delete.question', { id: removeId })">
          Are you sure you want to delete this Item View?
        </p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-itemView"
          data-cy="entityConfirmDeleteButton"
          v-text="$t('entity.action.delete')"
          v-on:click="removeItemView()"
        >
          Delete
        </button>
      </div>
    </b-modal>
    <div v-show="itemViews && itemViews.length > 0">
      <div class="row justify-content-center">
        <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
      </div>
      <div class="row justify-content-center">
        <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage" :change="loadPage(page)"></b-pagination>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./item-view.component.ts"></script>
