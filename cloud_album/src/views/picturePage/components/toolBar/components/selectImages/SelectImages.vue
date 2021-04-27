<template>
  <div>
    <div v-if="!isSelected">
      <v-btn style="margin: 0 20px" outlined color="#09AAFF" @click="isCheckOut">选择</v-btn>
    </div>
    <div v-else>
      <v-btn outlined class="button" color="#09AAFF" @click="deleteImages">批量删除</v-btn>
      <v-btn outlined class="button" color="#09AAFF" @click="downloadImages">批量下载</v-btn>
      <v-btn outlined class="button" color="#09AAFF" @click="isCheckOut">取消选择</v-btn>
    </div>
  </div>
</template>

<script>
  export default {
    name: "SelectImages",
    data: () => ({
      isSelected: false
    }),
    methods: {
      isCheckOut() {
        this.isSelected = !this.isSelected
        this.$emit('checkOut')
      },
      deleteImages() {
        this.$emit('deleteImages')
      },
      downloadImages() {
        this.$emit('downloadImages')
      }
    }
  }
</script>

<style scoped>
  .button {
    margin: 0 10px;
  }
  .button:hover {
    color: #FB7299;
  }
</style>