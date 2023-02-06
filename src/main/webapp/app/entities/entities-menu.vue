<template>
  <div>
    <b-dropdown-item to="/eq-item">
      <font-awesome-icon icon="asterisk" />
      <span v-text="$t('global.menu.entities.eqItem')">Eq Item</span>
    </b-dropdown-item>
    <b-dropdown-item to="/equipment">
      <font-awesome-icon icon="asterisk" />
      <span v-text="$t('global.menu.entities.equipment')">Equipment</span>
    </b-dropdown-item>
    <!-- jhipster-needle-add-entity-to-menu - JHipster will add entities to the menu here -->
  </div>
</template>

<script lang="ts" src="./entities-menu.component.ts"></script>
