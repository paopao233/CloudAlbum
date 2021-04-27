<template>
  <div>
    <div v-if="images.length !== 0">
      <v-row style="margin: 0 3%">
        <v-col v-for="img in images" cols="12" sm="4" lg="3">
          <v-tooltip bottom>
            <template v-slot:activator="{ on, attrs }">
              <v-card style="margin: 20px" @click="showPicture(img)" v-bind="attrs" v-on="on">
                <v-img :src="img.thumbUrl" height="150px"></v-img>
              </v-card>
            </template>
            <span>{{ img.name }}</span>
          </v-tooltip>
          <v-checkbox v-if="isSelected" v-model="selectedImages" :value="img.name"></v-checkbox>
        </v-col>
      </v-row>

      <v-dialog v-model="imageDetailsIsShow" fullscreen dark>
        <div class="imgBox">

          <v-row style="margin: 0;padding: 0">
            <v-spacer></v-spacer>
            <v-btn icon @click="imageDetailsIsShow = !imageDetailsIsShow;isShowImageInfo = false" color="#FFFFFF" large class="closeBtn">
              <v-icon>mdi-close</v-icon>
            </v-btn>
          </v-row>

          <v-layout align-center justify-center style="height: 80vh">
            <v-img :src="image.imgUrl"  @load="getImageInfo" ref="img" :width="width" :height="height" :max-width="imageWidth" max-height="80vh" contain class="image"></v-img>
            <image-info :image-info="image" :size="size" v-if="isShowImageInfo" class="imageInfo"></image-info>
          </v-layout>

          <v-row style="margin: 0;padding: 0;" justify="center">
            <v-col v-for="item in items" cols="1">
              <v-tooltip bottom>
                <template v-slot:activator="{on, attrs}">
                  <v-btn icon color="#C3C3C3" large v-on="on" v-bind="attrs" @click="listenMethod(item.method)">
                    <v-icon>{{ item.icon }}</v-icon>
                  </v-btn>
                </template>
                <span>{{ item.text }}</span>
              </v-tooltip>
            </v-col>
          </v-row>

        </div>
      </v-dialog>

      <delete-results :delete-results="deleteResults" ref="deleteResults"></delete-results>

    </div>
    <div v-else class="isNull">
      <div style="margin: 20% 0;text-align: center">
        <v-icon color="#A0A0A0" size="36px">mdi-image</v-icon>
        相册为空,请上传照片
      </div>
    </div>
  </div>
</template>

<script>
  import ImageInfo from "@/views/picturePage/components/showPicture/components/ImageInfo";
  import { saveAs } from "file-saver";
  import {localhost, network} from "@/network/request";
  import DeleteResults from "@/views/picturePage/components/showPicture/components/DeleteResults";
  import {initPage} from "@/publicMethods/methods";

  export default {
    name: "showPicture",
    components: {DeleteResults, ImageInfo},
    data: () => ({
      imageDetailsIsShow: false,
      isShowImageInfo: false,
      image: {},
      deleteResults: '',
      width: '',
      height: '',
      isSelected: false,
      selectedImages: [],
      items: [
        {
          text: '详细信息',
          method: 'showImageInfo',
          icon: 'mdi-information-outline'
        },
        {
          text: '下载',
          method: 'downloadImage',
          icon: 'mdi-download'
        },
        {
          text: '删除',
          method: 'deleteImage',
          icon: 'mdi-delete-outline'
        }
      ]
    }),
    props: {
      images: ''
    },
    computed: {
      imageWidth() {
        return this.isShowImageInfo ? '70vh' : '80vh'
      },
      size() {
        return this.width + '*' + this.height
      }
    },
    methods: {
      showPicture(img) {
        this.image = img
        this.imageDetailsIsShow = !this.imageDetailsIsShow
      },
      getImageInfo() {
        this.width = this.$refs.img.image.width
        this.height = this.$refs.img.image.height
      },
      listenMethod(method) {
        this[method]()
      },
      showImageInfo() {
        this.isShowImageInfo = ! this.isShowImageInfo
      },
      downloadImage() {
        let imgUrl = this.image.imgUrl
        let name = imgUrl.substring(imgUrl.lastIndexOf('/') + 1)
        imgUrl = '/api/image/' + name

        let xhr = new XMLHttpRequest()
        xhr.open('get', imgUrl, true)
        xhr.responseType = 'blob'
        xhr.onload = function () {
          if (xhr.status === 200) {
            let blob = new Blob([xhr.response], {'type': 'application/octet-stream'})
            saveAs(blob, name)
          }
        }
        xhr.send()
      },
      deleteImage() {
        let images = []

        this.imageDetailsIsShow = false
        images.push(this.image.name)

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
    }
  }
</script>

<style scoped>
  .imgBox {
    height: 100vh;
    background-color: rgba(13,13,13,0.7);
    margin: 0;
    padding: 0;
    text-align: center;
  }
  .closeBtn {
    margin: 0 20px;
  }
  .image {
    margin: 0 50px;
    padding: 0;
  }
  .imageInfo {
    margin: 0 30px;
  }
  .isNull {
    font-size: 24px;
    color: #A0A0A0;
  }
</style>