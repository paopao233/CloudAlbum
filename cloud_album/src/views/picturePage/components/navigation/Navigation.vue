<template>
  <v-navigation-drawer clipped permanent app color="#F7F7F7">
    <v-list style="text-align: center;">
      <v-list-item link exact to="/picture" color="#09AAFF">
        <v-list-item-content>
          <v-list-item-title @click="init">
            <v-icon>mdi-image-multiple-outline</v-icon>
              全部照片
          </v-list-item-title>
        </v-list-item-content>
      </v-list-item>
      <v-list-item color="#09AAFF" v-for="classify in classifies" :key="classify.classifyKey" link :to="'/picture/' + classify.classifyKey">
        <v-list-item-content>
          <v-list-item-title @click="selectClassify(classify.classifyKey)">{{ classify.classify }}</v-list-item-title>
        </v-list-item-content>
      </v-list-item>
    </v-list>
  </v-navigation-drawer>
</template>

<script>
import {localhost, network} from "@/network/request";
import {initPage} from "@/publicMethods/methods";

  export default {
    name: "Navigation",
    data: () => ({
      classifies: ''
    }),
    methods: {
      getClassifies() {
        network({
          url: '/getAllClassify',
          method: 'get'
        }).then(res => {
          this.classifies = res
        })
      },
      selectClassify(key) {
        network({
          url: '/selectClassify',
          method: 'get',
          params: {
            classify: key
          }
        }).then(res => {
          this.$store.state.images = res
        })
      },
      init() {
        initPage().then(res => {
          this.$store.state.images = res
        })
      }
    },
    created() {
      this.getClassifies()
    }
  }
</script>

<style scoped>

</style>