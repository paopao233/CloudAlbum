<template>
  <v-toolbar flat height="60px">
    <upload-button></upload-button>
    <select-images @checkOut="checkOut" @downloadImages="downloadImages" @deleteImages="deleteImages"></select-images>
    <v-spacer></v-spacer>
    <search-images></search-images>
  </v-toolbar>
</template>

<script>
  import UploadButton from "@/views/picturePage/components/toolBar/components/uploadButton/UploadButton";
  import SelectImages from "@/views/picturePage/components/toolBar/components/selectImages/SelectImages";
  import SearchImages from "@/views/picturePage/components/toolBar/components/searchImages/SearchImages";
  export default {
    name: "ToolBar",
    components: {SearchImages, SelectImages, UploadButton},
    methods: {
      checkOut() {
        this.$emit('checkOut')
      },
      downloadImages() {
        this.$emit('downloadImages')
      },
      deleteImages() {
        this.$emit('deleteImages')
      }
    }
  }
</script>

<style scoped>

</style>