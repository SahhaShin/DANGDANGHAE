<template>
  <div>

    <!--배너자리-->
    <div class="banner"></div>
   
   <!--https://codepen.io/Netology/pen/wPqXwv-->
   <!--상품 시작 v-for로 12개 상품 보여줌-->
    <div class="products">
      <article v-for="p in 12" :key="p" class="product">
        <div class="product__imgbox">
          <img
            src="@/assets/food1.png"
            class="product__preview"
            alt="Apple iPhone 7"
          />
        </div>
        <div class="product__body">
          <h3 class="product__name">[고온어다이어트] <br>시즌3 도시락 5종</h3>
          <span class="product__price">5200p</span>
        </div>
      </article>
    </div>
    <!--상품 끝-->
  </div>
</template>

<script>

export default {
  name: "StorePreview",
 
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
  width: 20%;
  padding: 1.5em;
  margin: 5px 10px 5px 10px;
  font-size: 16px;
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
  margin-top:5%;
  /* text-align: left; */

}
/* .product__price {
  text-align: left;
} */
.banner{
  /* 배너 css */
  width:100%;
  height:300px;
  background-color: grey;
  margin-bottom: 5%;
}
</style>