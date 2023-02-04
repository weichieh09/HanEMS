<template>
  <div>
    <b-container fluid>
      <br /><br /><br />
      <b-row>
        <b-col md="12">
          <h1 align="center">2023/2/4 23:07</h1>
          <br />
        </b-col>
        <b-col md="4">
          <!-- <router-link :to="{ name: 'Wcc102', params: { partnerViewId: IPartnerView.id } }" custom
                v-slot="{ navigate }"> -->
          <div style="padding: 5px 0px; cursor: pointer">
            <b-card border-variant="secondary" header=" " header-border-variant="secondary" align="center" title="總覽">
              <b-card-text>Overview</b-card-text>
              <!-- </router-link> -->
            </b-card>
          </div>
          <!-- </router-link> -->
        </b-col>
        <b-col md="4">
          <!-- <router-link :to="{ name: 'Wcc102', params: { partnerViewId: IPartnerView.id } }" custom
                v-slot="{ navigate }"> -->
          <div style="padding: 5px 0px; cursor: pointer">
            <b-card border-variant="secondary" header=" " header-border-variant="secondary" align="center" title="借用">
              <b-card-text>Rent</b-card-text>
              <!-- </router-link> -->
            </b-card>
          </div>
          <!-- </router-link> -->
        </b-col>
        <b-col md="4">
          <!-- <router-link :to="{ name: 'Wcc102', params: { partnerViewId: IPartnerView.id } }" custom
                v-slot="{ navigate }"> -->
          <div style="padding: 5px 0px; cursor: pointer">
            <b-card border-variant="secondary" header=" " header-border-variant="secondary" align="center" title="歸還">
              <b-card-text>Return</b-card-text>
              <!-- </router-link> -->
            </b-card>
          </div>
          <!-- </router-link> -->
        </b-col>
      </b-row>
      <br /><br /><br />
    </b-container>
  </div>
</template>

<script lang="ts" src="./wcc101.component.ts"></script>
