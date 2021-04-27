<template>
  <div>
    <v-dialog v-model="isUpload" width="400px">
      <template v-slot:activator="{ on, attrs }">
        <v-btn color="#09AAFF" style="color:#FFFFFF;" v-bind="attrs" v-on="on">
          <v-icon>mdi-upload</v-icon>
          上传
        </v-btn>
      </template>

      <v-card>
        <v-card-title>上传图片</v-card-title>

        <v-file-input accept="image/*" multiple label="点击选择图片" class="uploadInput" v-model="images"></v-file-input>

        <v-card-actions>
          <v-row class="uploadButton">
            <v-spacer></v-spacer>
            <v-btn @click="uploadImages" color="#09AAFF" outlined>上传</v-btn>
          </v-row>
        </v-card-actions>
      </v-card>

      <v-snackbar v-model="snackbar" :timeout="timeout" color="red darken-2" outlined>
        {{ isNULL }}
      </v-snackbar>
    </v-dialog>
    <upload-result :results="results" ref="result"></upload-result>
  </div>
</template>

<script>
import {localhost, network} from "@/network/request";
import UploadResult from "@/views/picturePage/components/toolBar/components/uploadButton/UploadResult";
import {initPage} from "@/publicMethods/methods";

  export default {
    name: "UploadButton",
    components: {UploadResult},
    data: () => ({
      isUpload: false,
      isNULL: '未选择图片',
      snackbar: false,
      timeout: 1000,
      openUploadBox: false,
      images: [],
      results: ''
    }),
    methods: {
      uploadImages() {
        if (this.images.length === 0) {
          this.snackbar = !this.snackbar
          return
        }

        let formData = new FormData()

        for (let i = 0; i < this.images.length; i++) {
          formData.append("img", this.images[i])
        }

        this.isUpload = false

        network({
          url: '/uploadImage',
          method: 'post',
          data: formData,
          headers: {
            'Content-Type': 'multipart/form-data'
          }
        }).then(res => {
          this.results = res
          this.$refs.result.$data.isEnding = true
        })
      }
    }
  }
</script>

<style scoped>
  .uploadButton {
    margin: 0 24px;
    padding: 10px;
  }

  .uploadInput {
    margin: 0 24px;
  }
</style>