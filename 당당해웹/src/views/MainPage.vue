<!-- 메인페이지입니다. -->
<template>
  <div style="height: 700px; width: 100%; margin: auto; background-color:none;">
    <router-view />
    <!-- <jquerybanner /> -->
    <div id="banner">
      <div
        id="bannertext"
        style="
          box-shadow: 0 5px 18px -7px rgba(0, 0, 0, 0.32);
          text-align: center;
        "
      >
        <h1
          class="font-spring"
          style="text-shadow: 0px 3px 5px rgba(0, 0, 0, 0.32)"
        >
          DangDangHae
        </h1>
        <!-- <img src="@/assets/DDHLogo.png" style="margin-top:-30px"> -->
        <p
          style="
            text-shadow: 0px 3px 5px rgba(0, 0, 0, 0.32);
            color: white;
            font-size: 1.7rem;
          "
        >
          Do you wanna be DangDang?
        </p>
      </div>
    </div>
  <router-link to="/challenge">
    <div class="self-banner-img">
        <img class="self-banner-img-fit" src="@/assets/ChallengeBanner1.png" style="margin-top:1em"/>
      </div>
</router-link>
    <challenge-preview />
    <community-preview />
    <!-- 스토어 시작 -->

      <router-link to="/store">
    <div class="self-banner-img">
        <img class="self-banner-img-fit" src="@/assets/StoreBanner1.png" />
      </div>
        </router-link>
    <div>
      <div style="width: 1200px; margin: auto">
        <!--배너자리-->
        <div class="margin-banner"></div>
        <router-link to="/store">
        <div class="title-text">STORE</div>
</router-link>
        <!--https://codepen.io/Netology/pen/wPqXwv-->
        <!--상품 시작 v-for로 12개 상품 보여줌-->
        <div class="products">
          <article v-for="(p,index) in 4" :key="p" class="product">
            <div class="product__imgbox">
              <img
                :src="products[index].image"
                class="product__preview"
                alt="Apple iPhone 7"
              />
            </div>
            <div class="product__body">
              <h3 class="product__name">
                {{products[index].company}} <br />{{products[index].name}}
              </h3>
              <span class="product__price">{{products[index].point}}p</span>
            </div>
          </article>
        </div>
        <!--상품 끝-->
      </div>
      <!-- 스토어 끝 -->
    </div>
    <main-footer/>
  </div>
</template>

<script>
import ChallengePreview from "../components/challenge/ChallengePreview.vue";
import CommunityPreview from "../components/community/CommunityPreview.vue";
import $ from "jquery";
import MainFooter from '../components/common/MainFooter.vue';
import {mapGetters} from "vuex";
export default {
  components: {
    ChallengePreview,
    CommunityPreview,
    MainFooter,
  },
  name: "MainPage",
  computed:{
    ...mapGetters(["products"]),
  },
  created(){
    this.$store.dispatch("setProducts");
  },

  mounted() {
    function EasyPeasyParallax() {
      const scrollPos = $(document).scrollTop();
      // $("#banner").css({
      //   "background-position": "50% " + -scrollPos / 4 + "px",
      // });
      $("#bannertext").css({
        "margin-top": scrollPos / 4 + "px",
        opacity: 1 - scrollPos / 150,
      });
    }
    $(document).ready(function () {
      $(window).scroll(function () {
        EasyPeasyParallax();
      });
    });
  },
};
</script>

<style scoped>
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

@font-face {
  font-family: "HSBombaram21-Regular";
  src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2302@1.0/HSBombaram21-Regular.woff2")
    format("woff2");
  font-weight: normal;
  font-style: normal;
}

.font-spring {
  font-family: "HSBombaram21-Regular";
}

html {
  height: 100%;
}
body {
  background: #fff;
  font: 400 1.5em/1.5 "Droid Serif", serif;
  color: #111;
  text-align: center;
  height: 100%;
}

h1 {
  font: 700 5.6em/1.2 "Droid Sans", sans-serif;
}
h2 {
  font: 700 1.5em/1.5 "Droid Sans", sans-serif;
  margin: 1em 0;
}

#banner {
  background: url("../assets/MainPageHeader.png") no-repeat center;
  min-width: 100%;
  max-width: 100%;
  min-height: 100%;
  max-height: 100%;
  background-size: cover;
  object-fit: cover;
  color: #fff;
  width: 100%;
  
}

#bannertext {
  width: 48em;
  position: fixed;
  top: 16%;
  left: 50%;
  border: solid white 3px;
  background-color: rgba(197, 170, 158, 0.719);
  margin-left: -24em;
  padding: 2em 0;
}

#content {
  max-width: 28em;
  text-align: justify;
  margin: 0 auto;
  padding: 2em;
}

#content p {
  margin: 0 0 2em;
}

/* 스토어 시작 */
div {
  display: block;
  justify-content: center;
}
article {
  display: block;
}
.products {
  display: flex;
  flex-wrap: wrap;
}

.product {
  box-sizing: border-box;
  width: 19%;
  padding: 1.5em;
  margin: 5px 10px 5px 10px;
  font-size: 14.5px;
  box-shadow: 0 1px 3px rgba(0, 0, 0, 0.25);
  text-align: center;
  border-radius: 5px;
}
.product__preview {
  width: 100%;
  display: block;
  margin-right: auto;
  margin-left: auto;
  border-radius: 5px;
}
img {
  overflow-clip-margin: content-box;
  overflow: clip;
}
.product__name {
  margin-top: 10%;
  line-height: 135%;
  font-size: 1.2em;
  font-weight: 500;
  /* text-align: left; */
}
/* .product__price {
  text-align: left;
} */
.marginbanner {
  /* 배너 css */
  width: 100%;
  height: 100px;
  background-color: none;
  margin-bottom: 5%;
}

/* 스토어 끝 */

.title-text {
  margin-left: 10%;
  font-size: 24px;
  font-weight: 600;
  margin-bottom: 2%;
  color: #585858;
}

.main-banner-fix {
  height: 100vh;
  background: url("../assets/DDHMainHeader.png") no-repeat center;
  background-size: cover;
  position: relative;
}

.self-text-banner {
  height: 3em;
  margin-left: 17.6em;
  margin-top: 2.1em;
  font-size: 2em;
  font-weight: 400;
}

.self-banner-img {
  width: 1000px;
  height: 150px;
  margin: auto;
  margin-top: 7em;
  margin-bottom: 7em;
}

.self-banner-img-fit {
  min-width: 100%;
  max-width: 100%;
  min-height: 100%;
  max-height: 100%;
  object-fit: cover;
  border-radius: 10px;
}

</style>
