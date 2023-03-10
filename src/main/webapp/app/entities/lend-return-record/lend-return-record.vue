<template>
  <div>
    <h2 id="page-heading" data-cy="LendReturnRecordHeading">
      <span v-text="$t('hanEmsApp.lendReturnRecord.home.title')" id="lend-return-record-heading">Lend Return Records</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="$t('hanEmsApp.lendReturnRecord.home.refreshListLabel')">Refresh List</span>
        </button>
        <router-link :to="{ name: 'LendReturnRecordCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-lend-return-record"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="$t('hanEmsApp.lendReturnRecord.home.createLabel')"> Create a new Lend Return Record </span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && lendReturnRecords && lendReturnRecords.length === 0">
      <span v-text="$t('hanEmsApp.lendReturnRecord.home.notFound')">No lendReturnRecords found</span>
    </div>
    <div class="table-responsive" v-if="lendReturnRecords && lendReturnRecords.length > 0">
      <table class="table table-striped" aria-describedby="lendReturnRecords">
        <thead>
          <tr>
            <th scope="row" v-on:click="changeOrder('id')">
              <span v-text="$t('global.field.id')">ID</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('lendDate')">
              <span v-text="$t('hanEmsApp.lendReturnRecord.lendDate')">Lend Date</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'lendDate'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('returnDate')">
              <span v-text="$t('hanEmsApp.lendReturnRecord.returnDate')">Return Date</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'returnDate'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('description')">
              <span v-text="$t('hanEmsApp.lendReturnRecord.description')">Description</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'description'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('createDate')">
              <span v-text="$t('hanEmsApp.lendReturnRecord.createDate')">Create Date</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'createDate'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('modifyDate')">
              <span v-text="$t('hanEmsApp.lendReturnRecord.modifyDate')">Modify Date</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'modifyDate'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('equipment.id')">
              <span v-text="$t('hanEmsApp.lendReturnRecord.equipment')">Equipment</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'equipment.id'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('person.id')">
              <span v-text="$t('hanEmsApp.lendReturnRecord.person')">Person</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'person.id'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('reason.id')">
              <span v-text="$t('hanEmsApp.lendReturnRecord.reason')">Reason</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'reason.id'"></jhi-sort-indicator>
            </th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="lendReturnRecord in lendReturnRecords" :key="lendReturnRecord.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'LendReturnRecordView', params: { lendReturnRecordId: lendReturnRecord.id } }">{{
                lendReturnRecord.id
              }}</router-link>
            </td>
            <td>{{ lendReturnRecord.lendDate ? $d(Date.parse(lendReturnRecord.lendDate), 'short') : '' }}</td>
            <td>{{ lendReturnRecord.returnDate ? $d(Date.parse(lendReturnRecord.returnDate), 'short') : '' }}</td>
            <td>{{ lendReturnRecord.description }}</td>
            <td>{{ lendReturnRecord.createDate ? $d(Date.parse(lendReturnRecord.createDate), 'short') : '' }}</td>
            <td>{{ lendReturnRecord.modifyDate ? $d(Date.parse(lendReturnRecord.modifyDate), 'short') : '' }}</td>
            <td>
              <div v-if="lendReturnRecord.equipment">
                <router-link :to="{ name: 'EquipmentView', params: { equipmentId: lendReturnRecord.equipment.id } }">{{
                  lendReturnRecord.equipment.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="lendReturnRecord.person">
                <router-link :to="{ name: 'PersonView', params: { personId: lendReturnRecord.person.id } }">{{
                  lendReturnRecord.person.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="lendReturnRecord.reason">
                <router-link :to="{ name: 'ReasonView', params: { reasonId: lendReturnRecord.reason.id } }">{{
                  lendReturnRecord.reason.id
                }}</router-link>
              </div>
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'LendReturnRecordView', params: { lendReturnRecordId: lendReturnRecord.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'LendReturnRecordEdit', params: { lendReturnRecordId: lendReturnRecord.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(lendReturnRecord)"
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
        ><span
          id="hanEmsApp.lendReturnRecord.delete.question"
          data-cy="lendReturnRecordDeleteDialogHeading"
          v-text="$t('entity.delete.title')"
          >Confirm delete operation</span
        ></span
      >
      <div class="modal-body">
        <p id="jhi-delete-lendReturnRecord-heading" v-text="$t('hanEmsApp.lendReturnRecord.delete.question', { id: removeId })">
          Are you sure you want to delete this Lend Return Record?
        </p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-lendReturnRecord"
          data-cy="entityConfirmDeleteButton"
          v-text="$t('entity.action.delete')"
          v-on:click="removeLendReturnRecord()"
        >
          Delete
        </button>
      </div>
    </b-modal>
    <div v-show="lendReturnRecords && lendReturnRecords.length > 0">
      <div class="row justify-content-center">
        <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
      </div>
      <div class="row justify-content-center">
        <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage" :change="loadPage(page)"></b-pagination>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./lend-return-record.component.ts"></script>
