<template>
  <v-app>

    <navigation></navigation>

    <v-main style="margin: 0;padding: 0;">
      <tool-bar @checkOut="checkOut" @downloadImages="downloadImages" @deleteImages="deleteImages"></tool-bar>
      <v-divider></v-divider>
      <show-picture :images="images" ref="showPicture"></show-picture>
      <delete-results :delete-results="deleteResults" ref="deleteResults"></delete-results>
    </v-main>
  </v-app>
</template>

<script>
  import ToolBar from "@/views/picturePage/components/toolBar/ToolBar";
  import Navigation from "@/views/picturePage/components/navigation/Navigation";
  import ShowPicture from "@/views/picturePage/components/showPicture/showPicture";
  import DeleteResults from "@/views/picturePage/components/showPicture/components/DeleteResults";
  import {initPage} from "@/publicMethods/methods";
  import {network} from "@/network/request";

  export default {
    name: "PicturePage",
    components: {ShowPicture, Navigation, ToolBar, DeleteResults},
    data: () => ({
      deleteResults: ''
    }),
    computed: {
      images() {
          return this.$store.state.images
      }
    },
    methods: {
      checkOut() {
        this.$refs.showPicture.$data.isSelected = !this.$refs.showPicture.$data.isSelected
        this.$refs.showPicture.$data.selectedImages = []
      },
      downloadImages() {
        let imagesName = this.$refs.showPicture.$data.selectedImages

        for (let i = 0; i < imagesName.length; i++) {
          let imgUrl = '/api/image/' + imagesName[i]

          let xhr = new XMLHttpRequest()
          xhr.open('get', imgUrl, true)
          xhr.responseType = 'blob'
          xhr.onload = function () {
            if (xhr.status === 200) {
              let blob = new Blob([xhr.response], {'type': 'application/octet-stream'})
              saveAs(blob, imagesName[i])
            }
          }
          xhr.send()
        }

        this.$refs.showPicture.$data.selectedImages = []
      },
      deleteImages() {
        let images = this.$refs.showPicture.$data.selectedImages

        network({
          url: '/deleteImages',
          method: 'post',
          data: {
            images
          }
        }).then(res => {
          this.deleteResults = res
          this.$refs.deleteResults.$data.show = true
        })
      }
    },
    created() {
      initPage().then(res => {
        this.$store.commit('setImages', res)
      })
    }
  }
</script>

<style scoped>

</style>