<template>
  <div>
    <my-page-header/>
    <router-view />
    <main-footer/>
  </div>
</template>

<script>
import MainFooter from '../components/common/MainFooter.vue';
import MyPageHeader from '../components/common/MyPageHeader.vue';
export default {
  components: { MyPageHeader, MainFooter },
  name: "MyPage",
};
</script>

<style>

@font-face {
  font-family: "Pretendard-Regular";
  src: url("https://cdn.jsdelivr.net/gh/Project-Noonnu/noonfonts_2107@1.1/Pretendard-Regular.woff")
    format("woff");
  font-weight: 400;
  font-style: normal;
}
* {
  font-family: "Pretendard-Regular";
}

</style>
