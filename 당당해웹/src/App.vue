<template>
  <div id="app">
    <router-view />
    <main-header/>
  </div>
</template>
<script>
import MainHeader from "@/components/common/MainHeader.vue";
export default {
  name: "App",
  components: {
    MainHeader,
  },
};
</script>
<style>
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
  list-style: none;
}
.text-center {
  text-align: center;
}
.container {
  margin: 0px 30px;
}
</style>
