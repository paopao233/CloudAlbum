<template>
  <v-text-field v-model="imageName" outlined background-color="#F1F2F4" label="搜索" append-icon="mdi-image-search-outline" dense hide-details class="searchInput" @click:append="searchImage"></v-text-field>
</template>

<script>
import {localhost, network} from "@/network/request";
import {initPage} from "@/publicMethods/methods";

  export default {
    name: "SearchImages",
    data: () => ({
      imageName: ''
    }),
    methods: {
      searchImage() {
        if (this.imageName === '') {
          initPage().then(res => {
            this.$store.commit('setImages', res)
          })
        }

        let imageName = this.imageName

        network({
          url: '/search',
          method: 'get',
          params: {
            imageName
          }
        }).then(res => {
          this.$store.state.images = res
        })
      }
    }
  }
</script>

<style scoped>
  .searchInput {
    width: 0;
  }
</style>