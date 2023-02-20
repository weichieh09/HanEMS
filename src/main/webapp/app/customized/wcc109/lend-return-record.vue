<template>
  <div>
    <h2 id="page-heading" data-cy="LendReturnRecordHeading">
      <span id="lend-return-record-heading">[紀錄] 借出/歸還</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span>更新</span>
        </button>
      </div>
    </h2>
    <br />

    <b-container fluid>
      <b-row>
        <b-col cols="6">
          <input type="text" class="form-control" v-model="pIdno" placeholder="借出人學號" />
        </b-col>
        <b-col cols="6">
          <input type="text" class="form-control" v-model="pName" placeholder="借出人姓名" />
        </b-col> </b-row
      ><br />
      <b-row>
        <b-col cols="6">
          <input type="text" class="form-control" v-model="eName" placeholder="設備名稱" />
        </b-col>
        <b-col cols="4">
          <select class="form-control" id="black-list-person" data-cy="person" v-model="rDate" name="person">
            <option value="all">狀態</option>
            <option value="true">已歸還</option>
            <option value="false">未歸還</option>
          </select>
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
    <div class="alert alert-warning" v-if="!isFetching && lendReturnRecords && lendReturnRecords.length === 0">
      <span>沒有借出/歸還資料</span>
    </div>
    <div class="table-responsive" v-if="lendReturnRecords && lendReturnRecords.length > 0">
      <table class="table table-striped" aria-describedby="lendReturnRecords">
        <thead>
          <tr>
            <th scope="row" v-on:click="changeOrder('id')">
              <span>編號</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('equipment.id')">
              <span>設備名稱</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'equipment.id'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('person.idno')">
              <span>借出人學號</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'person.idno'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('person.id')">
              <span>借出人名稱</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'person.id'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('lendDate')">
              <span>借出日期</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'lendDate'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('returnDate')">
              <span>歸還日期</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'returnDate'"></jhi-sort-indicator>
            </th>
            <!-- <th scope="row" v-on:click="changeOrder('description')">
                <span v-text="$t('hanEmsApp.lendReturnRecord.description')">Description</span>
                <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'description'"></jhi-sort-indicator>
              </th> -->
            <!-- <th scope="row" v-on:click="changeOrder('createDate')">
                <span v-text="$t('hanEmsApp.lendReturnRecord.createDate')">Create Date</span>
                <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'createDate'"></jhi-sort-indicator>
              </th> -->
            <!-- <th scope="row" v-on:click="changeOrder('modifyDate')">
                <span v-text="$t('hanEmsApp.lendReturnRecord.modifyDate')">Modify Date</span>
                <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'modifyDate'"></jhi-sort-indicator>
              </th> -->
            <!-- <th scope="row" v-on:click="changeOrder('reason.id')">
                <span v-text="$t('hanEmsApp.lendReturnRecord.reason')">Reason</span>
                <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'reason.id'"></jhi-sort-indicator>
              </th> -->
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="lendReturnRecord in lendReturnRecords" :key="lendReturnRecord.id" data-cy="entityTable">
            <td>{{ lendReturnRecord.id }}</td>
            <td>
              <div v-if="lendReturnRecord.equipment">{{ lendReturnRecord.equipment.name }}</div>
            </td>
            <td>
              <div v-if="lendReturnRecord.person">{{ lendReturnRecord.person.idno }}</div>
            </td>
            <td>
              <div v-if="lendReturnRecord.person">{{ lendReturnRecord.person.name }}</div>
            </td>
            <td>{{ lendReturnRecord.lendDate ? $d(Date.parse(lendReturnRecord.lendDate), 'short') : '' }}</td>
            <td>{{ lendReturnRecord.returnDate ? $d(Date.parse(lendReturnRecord.returnDate), 'short') : '' }}</td>
            <!-- <td>{{ lendReturnRecord.description }}</td> -->
            <!-- <td>{{ lendReturnRecord.createDate ? $d(Date.parse(lendReturnRecord.createDate), 'short') : '' }}</td> -->
            <!-- <td>{{ lendReturnRecord.modifyDate ? $d(Date.parse(lendReturnRecord.modifyDate), 'short') : '' }}</td> -->
            <!-- <td>
                <div v-if="lendReturnRecord.reason">
                  <router-link :to="{ name: 'ReasonView', params: { reasonId: lendReturnRecord.reason.id } }">{{
                    lendReturnRecord.reason.id
                  }}</router-link>
                </div>
              </td> -->
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'Wcc109View', params: { lendReturnRecordId: lendReturnRecord.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                  </button>
                </router-link>
                <!-- <router-link
                    :to="{ name: 'LendReturnRecordEdit', params: { lendReturnRecordId: lendReturnRecord.id } }"
                    custom
                    v-slot="{ navigate }"
                  >
                    <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                      <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                      <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                    </button>
                  </router-link> -->
                <!-- <b-button
                    v-on:click="prepareRemove(lendReturnRecord)"
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
