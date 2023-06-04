<template>
  <div>
    <!--배너자리-->
    <store-header />
    <div style="width: 1200px; margin: auto">
      <router-link to="/store">

      <div class="self-banner-img">
        <img class="self-banner-img-fit" src="@/assets/StoreBanner1.png" />
      </div>
      </router-link>
      <div class="self-text-banner">지금 보면 좋은 상품</div>
      <!--https://codepen.io/Netology/pen/wPqXwv-->
      <!--상품 시작 v-for로 12개 상품 보여줌-->
      <div class="products">
        <article v-for="(p,index) in 4" :key="p" class="product">
          <div class="product__imgbox">
            <img
              :src="productsByLiving[index].image"
              class="product__preview"
              alt="Apple iPhone 7"
            />
          </div>
          <div class="product__body">
            <h3 class="product__name">
              {{productsByLiving[index].company}}<br>{{productsByLiving[index].name}}
            </h3>
            <span class="product__price">{{productsByLiving[index].point}}p</span>
          </div>
        </article>
      </div>
      <div class="self-text-banner" style="margin-top:4em;">건강에는 이게 딱!</div>
      <div class="products">
        <article v-for="(p,index) in 4" :key="p" class="product">
          <div class="product__imgbox">
            <img
              :src="productsByFood[index].image"
              class="product__preview"
              alt="Apple iPhone 7"
            />
          </div>
          <div class="product__body">
            <h3 class="product__name">
              {{productsByFood[index].company}} <br />{{productsByLiving[index].name}}
            </h3>
            <span class="product__price">{{productsByLiving[index].point}}p</span>
          </div>
        </article>
      </div>
      <router-link to="/main">
            <div class="self-banner-img" style="margin-top: 12em;">
        <img class="self-banner-img-fit" src="@/assets/DDHBanner1.png" />
      </div>
      </router-link>
      <div class="self-text-banner">추천 상품 보셨나요?</div>
      <div class="products">
        <article v-for="(p,index) in 3" :key="p" class="product">
          <div class="product__imgbox">
            <img
              :src="productsByElec[index].image"
              class="product__preview"
              alt="Apple iPhone 7"
            />
          </div>
          <div class="product__body">
            <h3 class="product__name">
              {{productsByElec[index].company}} <br />{{productsByElec[index].name}}
            </h3>
            <span class="product__price">{{productsByElec[index].point}}p</span>
          </div>
        </article>
      </div>
      <!--상품 끝-->
    </div>
    <main-footer/>
  </div>
</template>

<script>
import MainFooter from '../components/common/MainFooter.vue';
import StoreHeader from "../components/common/StoreHeader.vue";
import {mapGetters} from "vuex";
export default {
  components: { StoreHeader, MainFooter },
  name: "StorePreview",
  created(){
    this.$store.dispatch("setProductByType","식료품");
    this.$store.dispatch("setProductByType","생활용품");
    this.$store.dispatch("setProductByType","가전제품");
  },
  computed:{
    ...mapGetters(['productsByFood']),
    ...mapGetters(['productsByLiving']),
    ...mapGetters(['productsByElec']),
  },
  
};
</script>

<style scoped>
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
  margin-top: 5%;
  font-size: 1.2em;
  line-height: 135%;
  font-weight: 500;
  /* text-align: left; */
}
/* .product__price {
  text-align: left;
} */
.banner {
  /* 배너 css */
  width: 100%;
  height: 300px;
  background-color: grey;
  margin-bottom: 5%;
}

.self-text-banner {
  height: 3em;
  text-align: center;
  margin-top: 2.1em;
  font-size: 2em;
  font-weight: 400;
}

.self-banner-img {
  width: 1000px;
  height: 150px;
  margin: auto;
  margin-top: 12em;
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

.product-value{
  font-size: 1.4em;
  font-weight: 400;
  margin-left:6em;
  margin-bottom: 1em;
}
</style>
