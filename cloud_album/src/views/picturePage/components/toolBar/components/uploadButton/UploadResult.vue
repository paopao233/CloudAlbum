<template>
  <v-dialog v-model="isEnding" width="660px">
    <v-card color="#FFFFFF" class="result">

      <v-card-title>上传结果</v-card-title>

      <div v-for="result in this.results">
        <v-simple-table>
          <template v-slot:default>
            <thead>
            <tr>
              <th class="text-center">
                状态
              </th>
              <th class="text-center">
                名称
              </th>
              <th class="text-center">
                结果
              </th>
              <th class="text-center">
                分类
              </th>
            </tr>
            </thead>
            <tbody>
            <tr v-for="res in result">
              <td>
                <v-icon :color="iconColor(res.status)">{{ icon(res.status) }}</v-icon>
              </td>
              <td>
                {{ res.key }}
              </td>
              <td>
                {{ res.value }}
              </td>
              <td>
                {{ res.classify }}
              </td>
            </tr>
            </tbody>
          </template>
        </v-simple-table>
      </div>

      <v-card-actions>
        <v-row class="uploadButton">
          <v-spacer></v-spacer>
          <v-btn @click="updateImages">确定</v-btn>
        </v-row>
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>

<script>
import {initPage} from "@/publicMethods/methods";

export default {
  name: "UploadResult",

  data() {
    return {
      /*  判断上传是否完成  */
      isEnding: false,
    }
  },

  computed: {
    icon() {
      return function (status) {
        if (status === 200 || status === 0) {
          return 'mdi-checkbox-marked-circle-outline'
        } else {
          return 'mdi-window-close'
        }
      }
    },

    iconColor() {
      return function (status) {
        if (status === 200 || status === 0) {
          return '#00FF00'
        } else {
          return '#FF0000'
        }
      }
    }
  },

  props: {
    /*  上传结果  */
    results: {}
  },

  methods: {
    updateImages() {
      this.isEnding = !this.isEnding

      initPage().then(res => {
        this.$store.commit('setImages', res)
      })

      this.$emit('updateImages')
    }
  }
}
</script>

<style scoped>
  .result {
    text-align: center;
    padding: 20px;
  }
  .uploadButton {
    padding: 10px;
  }
</style>