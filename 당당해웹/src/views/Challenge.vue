<template>
  <div>
    <challenge-header />
    <router-view />
    <main-footer/>
  </div>
</template>

<script>
import ChallengeHeader from "../components/common/ChallengeHeader.vue";
import MainFooter from '../components/common/MainFooter.vue';
export default {
  components: { ChallengeHeader, MainFooter },
  name: "Challenge",
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
