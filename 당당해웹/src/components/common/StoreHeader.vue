<template>
  <header>
    <div>
      <div style="height: 700px; width: 100%; margin: auto">
        <!-- <jquerybanner /> -->
        <div id="banner">
          <div
            id="bannertext"
            style="
              box-shadow: 0 5px 18px -7px rgba(0, 0, 0, 0.32);
              text-align: center;
            "
          ></div>
        </div>
      </div>
    </div>
  </header>
</template>

<script>
export default {
  name: "StoreHeader",
};
</script>

<style scoped>
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
#banner {
  background: url("../../assets/StoreHeader.png") no-repeat center;
  min-width: 100%;
  max-width: 100%;
  min-height: 100%;
  max-height: 100%;
  background-size: cover;
  object-fit: cover;
  color: #fff;
  width: 100%;
  box-shadow: inset 0px 0px 15px 0px rgba(34, 34, 34, 0.102);
}

</style>
