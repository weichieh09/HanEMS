<template>
  <div>
    <b-container fluid>
      <br />
      <b-row>
        <b-col md="12">
          <h4 v-if="authenticated">管理員，你好!</h4>
          <h4 v-else>訪客，你好!</h4>
          <br />
        </b-col>
        <b-col md="12">
          <h1 align="center">{{ nowDate }}</h1>
          <br />
        </b-col>
        <b-col md="4">
          <router-link :to="{ name: 'Wcc102' }" custom v-slot="{ navigate }">
            <div style="padding: 5px 0px; cursor: pointer" @click="navigate">
              <b-card border-variant="secondary" header=" " header-border-variant="secondary" align="center" title="總覽">
                <b-card-text>Overview</b-card-text>
              </b-card>
            </div>
          </router-link>
        </b-col>
        <b-col md="4">
          <router-link :to="{ name: 'Wcc103' }" custom v-slot="{ navigate }">
            <div style="padding: 5px 0px; cursor: pointer" @click="navigate">
              <b-card border-variant="secondary" header=" " header-border-variant="secondary" align="center" title="借用">
                <b-card-text>Rent</b-card-text>
              </b-card>
            </div>
          </router-link>
        </b-col>
        <b-col md="4">
          <router-link :to="{ name: 'Wcc104' }" custom v-slot="{ navigate }">
            <div style="padding: 5px 0px; cursor: pointer" @click="navigate">
              <b-card border-variant="secondary" header=" " header-border-variant="secondary" align="center" title="歸還">
                <b-card-text>Return</b-card-text>
              </b-card>
            </div>
          </router-link>
        </b-col>
      </b-row>
      <br /><br /><br />
    </b-container>
  </div>
</template>

<script lang="ts" src="./wcc101.component.ts"></script>
