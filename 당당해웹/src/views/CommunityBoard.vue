<template>
  <div>
    <community-header />
    <router-view />
  </div>
</template>

<script>
import CommunityHeader from "../components/common/CommunityHeader.vue";
export default {
  components: { CommunityHeader },
  name: "CommunityBoard",
};
</script>

<style></style>
