<template>
  <div>
    <!-- https://codepen.io/razwan/pen/gOpYqBb -->
    <div style="margin-top: 5em"></div>
    <div class="content">
      <input
        style="
          margin-top: 1.5em;
          margin-bottom: 1.5em;
          color: #fb9f4c;
          font-size: 5em;
          border: none;
        "
        placeholder="게시글 제목"
        v-model="title"
      >
       
     
      <div style="display: flex">
        <div style="width: 40%">
          작성자
          <div style="font-size: 0.8em">{{loginUser.id}}</div>
        </div>
        <div style="width: 40%">
          날짜
          <div style="font-size: 0.8em">{{now}}</div>
        </div>
        <div style="width: 20%">
          조회수
          <div style="font-size: 0.8em">0</div>
        </div>
      </div>
      <hr style="height: 1px; background: #ececec" />
      <form>
        <input
        v-model="content"
          type="textarea"
          placeholder="내용을 입력해주세요"
          style="border: none; width: 100%; height: 20em"
        />
      </form>

      <hr style="height: 1px; background: #ececec" />

      <div style="display: flex; margin-bottom: -5em">
        <router-link to="/community"
          ><div class="button">목록</div></router-link
        >

        <div style="margin-left: auto">
          <div style="display: flex">
            <div>
              <div class="button" @click="regist_board">등록</div>
            </div>
            <div>
              <router-link to="/community"
                ><div class="button">취소</div></router-link
              >
            </div>
          </div>
        </div>
      </div>
    </div>
    <main-footer />
  </div>
</template>

<script>
import {mapGetters} from "vuex";
import MainFooter from "../../components/common/MainFooter.vue";
export default {
  components: { MainFooter },
  name: "CommunityBoardWrite",
  computed:{
    ...mapGetters(["loginUser"]),
  },
  data(){
    return{
      now : "00:00:00",
      title:"",
      content:"",
    }
    
  },
  mounted:{
    time: function(){
      var date = new Date();
      this.now = date.getHours()+":"+
      date.getMinutes()+":"+
      date.getSeconds();
    }
  },
  methods:{
    regist_board(){
      var board = {
        users_id: this.loginUser.id,
        title : this.title,
        content : this.content,
        type : "정보",
        write_date : this.now

      }
      this.$store.dispatch("createBoard",board);
      this.$router.push("/community");
    }
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
:root {
  --theme-font-size-breakpoint: 1440;
  --theme-font-size-minimum-value: 16;
  --theme-font-size-minimum-value-breakpoint: 320;
  --theme-font-size-slope-adjust: 0.6;
  --theme-addon-multiplier: 0;
  --theme-addon-unit: calc(1vw - var(--theme-font-size-breakpoint) * 1px / 100);
  --theme-addon: calc(var(--theme-addon-unit) * var(--theme-addon-multiplier));
  --ms-ratio: 1.2;
  --ms-0: 16;
  --ms-1: calc(var(--ms-0) * var(--ms-ratio));
  --ms-2: calc(var(--ms-1) * var(--ms-ratio));
  --ms-3: calc(var(--ms-2) * var(--ms-ratio));
  --ms-4: calc(var(--ms-3) * var(--ms-ratio));
  --ms-5: calc(var(--ms-4) * var(--ms-ratio));
  --ms-6: calc(var(--ms-5) * var(--ms-ratio));
  --ms-7: calc(var(--ms-6) * var(--ms-ratio));
  --ms-8: calc(var(--ms-7) * var(--ms-ratio));
}
@media only screen and (min-width: 1440px) {
  :root {
    --theme-font-size-slope-adjust: 0.2;
    --theme-addon-multiplier: 1;
  }
}

* {
  --x0: var(--theme-font-size-minimum-value-breakpoint);
  --y0: var(--theme-font-size-minimum-value);
  --x1: var(--theme-font-size-breakpoint);
  --y1: var(--font-size);
  --y0-new: calc(
    var(--y1) - (var(--y1) - var(--y0)) * var(--theme-font-size-slope-adjust)
  );
  --a: calc((var(--y1) - var(--y0-new)) / (var(--x1) - var(--x0)));
  --b: calc(var(--y0-new) - var(--a) * var(--x0));
  --y2: calc(var(--a) * 100vw + var(--b) * 1px);
  --final-font-size: calc(var(--y2) + var(--theme-addon));
}

body {
  --font-size: var(--ms-1);
  font-size: var(--final-font-size);
  line-height: 1.5;
  font-family: Galano Grotesque Alt, sans-serif;
}
p {
  line-height: inherit;
}

h1,
h2,
h3,
h4,
h5,
h6,
blockquote {
  font-family: Galano Classic, sans-serif;
}

h1 {
  --font-size: var(--ms-8);
  font-size: var(--final-font-size);
  line-height: 1.2;
}

h2 {
  --font-size: var(--ms-5);
  font-size: var(--final-font-size);
}

.lead,
blockquote {
  --font-size: var(--ms-2);
  font-size: var(--final-font-size);
}

.content {
  width: 1000px;
  margin-left: auto;
  margin-right: auto;
}
.content > * {
  margin-top: 2rem;
}

a {
  color: #fb9f4c;
}

.button {
  position: relative;
  height: 3em;
  width: 6em;
  font-size: 1.2rem;
  display: inline-block;
  padding: 0.75em 1.25em;
  border: 0;
  text-decoration: none;
  color: #fb9f4c;
  background-color: white;
  border: 1px solid #fb9f4c;
  border-radius: 10px;
  transition: all 0.2s cubic-bezier(0, 0, 0.58, 1);
  transition-property: transform, background, background-color, color,
    box-shadow;
  cursor: pointer;
  -webkit-font-smoothing: antialiased;
  -webkit-tap-highlight-color: rgba(0, 0, 0, 0);
  -moz-osx-font-smoothing: grayscale;
}
.button:hover {
  transform: scale(1.05) translateY(-3px);
  box-shadow: 0 10px 20px -10px rgba(136, 98, 76, 0.7);
}
.button:active,
.button:focus {
  transform: scale(1.02) translateY(1px);
  transition-duration: 0.1s;
}
</style>
