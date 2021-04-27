<template>
  <v-dialog v-model="show">
    <v-card color="#FFFFFF" class="result">

      <v-card-title>删除结果</v-card-title>

      <div>
        <v-simple-table>
          <template v-slot:default>
            <thead>
              <tr>
                <th class="text-center">
                  名称
                </th>
                <th class="text-center">
                  结果
                </th>
              </tr>
            </thead>
            <tbody  v-for="result in deleteResults">
              <tr>
                <td>
                  {{ result.name }}
                </td>
                <td>
                  {{ resultT(result.result) }}
                </td>
              </tr>
            </tbody>
          </template>
        </v-simple-table>
      </div>

      <v-card-actions>
        <v-row class="uploadButton">
          <v-spacer></v-spacer>
          <v-btn @click="close()">确定</v-btn>
        </v-row>
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>

<script>
  import {initPage} from "@/publicMethods/methods";

  export default {
    name: "DeleteResults",
    data() {
      return {
        show: false
      }
    },
    computed: {
      resultT() {
        return function (result) {
          if (result === "success") {
            return "成功"
          } else {
            return "失败"
          }
        }
      }
    },
    props: {
      deleteResults: ''
    },
    methods: {
      close() {
        this.show = false
        initPage().then(res => {
          this.$store.commit('setImages', res)
        })
      }
    }
  }
</script>

<style scoped>
  .result {
    text-align: center;
    padding: 20px;
  }
</style>