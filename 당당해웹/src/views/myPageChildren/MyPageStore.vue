<template>
  <div>
    <router-view />
    <!-- 유저 정보 -->

    <!-- 구매한 상품 -->
    <store-preview/>
  </div>
</template>

<script>
import StorePreview
 from '../../components/store/StorePreview.vue';


export default {
  components: { StorePreview },

  name: "MyPageStore",
};
</script>

<style></style>
