<template>
  <v-app>
    <app-bar v-if="showAppBar"></app-bar>

    <v-main>
      <router-view></router-view>
    </v-main>
  </v-app>
</template>

<script>
  import AppBar from "@/components/appBar/AppBar";
  export default {
    name: 'App',
    components: {AppBar},
    computed: {
      showAppBar() {
        return this.$route.path !== '/login' && this.$route.path !== '/404'
      }
    }
  };
</script>
