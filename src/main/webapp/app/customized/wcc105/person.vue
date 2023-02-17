<template>
  <div>
    <h2 id="page-heading" data-cy="PersonHeading">
      <span id="person-heading">申請單</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span>更新</span>
        </button>
        <!-- <router-link :to="{ name: 'PersonCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-person"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="$t('hanEmsApp.person.home.createLabel')"> Create a new Person </span>
          </button>
        </router-link> -->
      </div>
    </h2>
    <small class="form-text text-danger" style="text-align: right"> {{ nowDate }} </small>
    <small class="form-text text-danger" style="text-align: right"> *每五秒自動更新一次 </small>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && people && people.length === 0">
      <span>沒有申請單</span>
    </div>
    <div class="table-responsive" v-if="people && people.length > 0">
      <table class="table table-striped" aria-describedby="people">
        <thead>
          <tr>
            <th scope="row" v-on:click="changeOrder('id')">
              <span>編號</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('idno')">
              <span>學號</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'idno'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('name')">
              <span>姓名</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'name'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('phone')">
              <span>手機</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'phone'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('pending')">
              <span>類型</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'pending'"></jhi-sort-indicator>
            </th>
            <!-- <th scope="row" v-on:click="changeOrder('createDate')">
              <span v-text="$t('hanEmsApp.person.createDate')">Create Date</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'createDate'"></jhi-sort-indicator>
            </th> -->
            <th scope="row" v-on:click="changeOrder('modifyDate')">
              <span>時間</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'modifyDate'"></jhi-sort-indicator>
            </th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="person in people" :key="person.id" data-cy="entityTable">
            <td>{{ person.id }}</td>
            <td>{{ person.idno }}</td>
            <td>{{ person.name }}</td>
            <td>{{ person.phone }}</td>
            <td>{{ person.pending == 1 ? '借出' : '歸還' }}</td>
            <!-- <td>{{ person.createDate ? $d(Date.parse(person.createDate), 'short') : '' }}</td> -->
            <td>{{ person.modifyDate ? $d(Date.parse(person.modifyDate), 'short') : '' }}</td>
            <td class="text-right">
              <div class="btn-group">
                <!-- <router-link :to="{ name: 'PersonView', params: { personId: person.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                  </button>
                </router-link> -->
                <router-link
                  :to="{ name: 'Wcc105Edit', params: { personId: person.id, personPending: person.pending } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-primary btn edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline">開始</span>
                  </button>
                </router-link>
                <b-button v-on:click="reject(person.id)" variant="danger" class="btn btn" data-cy="entityDeleteButton">
                  <font-awesome-icon icon="times"></font-awesome-icon>
                  <span class="d-none d-md-inline">拒絕</span>
                </b-button>
              </div>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
    <b-modal ref="removeEntity" id="removeEntity">
      <span slot="modal-title"
        ><span id="hanEmsApp.person.delete.question" data-cy="personDeleteDialogHeading" v-text="$t('entity.delete.title')"
          >Confirm delete operation</span
        ></span
      >
      <div class="modal-body">
        <p id="jhi-delete-person-heading" v-text="$t('hanEmsApp.person.delete.question', { id: removeId })">
          Are you sure you want to delete this Person?
        </p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-person"
          data-cy="entityConfirmDeleteButton"
          v-text="$t('entity.action.delete')"
          v-on:click="removePerson()"
        >
          Delete
        </button>
      </div>
    </b-modal>
    <div v-show="people && people.length > 0">
      <div class="row justify-content-center">
        <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
      </div>
      <div class="row justify-content-center">
        <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage" :change="loadPage(page)"></b-pagination>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./person.component.ts"></script>
