<template>
  <div>
    <b-dropdown-item to="/equipment">
      <font-awesome-icon icon="asterisk" />
      <span v-text="$t('global.menu.entities.equipment')">Equipment</span>
    </b-dropdown-item>
    <b-dropdown-item to="/status">
      <font-awesome-icon icon="asterisk" />
      <span v-text="$t('global.menu.entities.status')">Status</span>
    </b-dropdown-item>
    <b-dropdown-item to="/item">
      <font-awesome-icon icon="asterisk" />
      <span v-text="$t('global.menu.entities.item')">Item</span>
    </b-dropdown-item>
    <b-dropdown-item to="/person">
      <font-awesome-icon icon="asterisk" />
      <span v-text="$t('global.menu.entities.person')">Person</span>
    </b-dropdown-item>
    <b-dropdown-item to="/reason">
      <font-awesome-icon icon="asterisk" />
      <span v-text="$t('global.menu.entities.reason')">Reason</span>
    </b-dropdown-item>
    <b-dropdown-item to="/lend-return-record">
      <font-awesome-icon icon="asterisk" />
      <span v-text="$t('global.menu.entities.lendReturnRecord')">Lend Return Record</span>
    </b-dropdown-item>
    <b-dropdown-item to="/black-list">
      <font-awesome-icon icon="asterisk" />
      <span v-text="$t('global.menu.entities.blackList')">Black List</span>
    </b-dropdown-item>
    <!-- jhipster-needle-add-entity-to-menu - JHipster will add entities to the menu here -->
  </div>
</template>

<script lang="ts" src="./entities-menu.component.ts"></script>
