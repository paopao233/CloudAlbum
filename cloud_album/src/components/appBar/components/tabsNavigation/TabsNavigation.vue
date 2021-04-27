<template>
  <v-tabs height="100%">
    <span v-for="tab in tabs" style="margin: 0;padding: 0;height: 100%;">
      <v-tab :to="tab.address">{{ tab.text }}</v-tab>
    </span>
  </v-tabs>
</template>

<script>
  export default {
    name: "TabsNavigation",
    data: () => ({
      tabs: [{
        text: '照片',
        address: 'picture'
      },{
        text: '精彩时刻',
        address: 'moment'
      }]
    })
  }
</script>

<style scoped>

</style>