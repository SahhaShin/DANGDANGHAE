<template>
  <div>
    <router-link to="/store">
      <div class="self-banner-img">
        <img class="self-banner-img-fit" src="@/assets/ChallengeBanner2.png" />
      </div>
    </router-link>
    <router-view />
    <!-- 유저 정보 -->

    <!-- 참여중인 챌린지 -->
    <!-- 챌린지 -->
    <!-- https://codepen.io/ikramcg/pen/PNbzWb -->
    <!-- 나의 챌린지 시작 -->
    <body>
      <!-- <div class="border"></div> -->
      <div class="wrapper">
        <header class="main_header clearfix">
          <div class="logo">
            <router-link to="/challenge"><h1>Challenge</h1></router-link>
          </div>
          <div class="nav">
            <router-link to="/Challenge" class="current">Board</router-link>
            <router-link to="/myPage/challenge">My-Challenge</router-link>
          </div>
        </header>
        <ul id="timeline">
          <!-- 한칸 시작 -->
          <li class="date">나의 챌린지</li>

          <li
            v-for="(list, index) in myChallenge"
            :key="index"
            class="listing clearfix"
            @click="movePage_detail(list.no)"
          >
            <div class="image_wrapper">
              <!-- 이미지 정보 받아와주세용~~~ -->
              <img src="@/assets/DDHMainHeader2.png" alt="Thumbnail" />
            </div>
            <div class="info">
              <!-- 정보 받아와주세용~~~ -->

              <!-- <span class="job_people"> -->
                <span class="job_title">{{ list.title }}</span>
                <!-- <img
                  src="@/assets/UserIcon.png"
                  style="width: 15px; height: 15px; margin-right: 5px"
                />
                3 / 6명 -->
              <!-- </span> -->
              <span class="job_info"
                ><div style="margin: 5px 0px 5px 0px">
                  {{ list.start_date }} ~ {{ list.end_date }}
                </div>
                <div class="challenge-detail-fontsize">
                  {{ list.content }}
                </div>
              </span>
            </div>
            <span class="job_type full_time"
              ><button
                :class="{
                  wait_btn: readyActive1,
                  start_btn: startActive1,
                  finish_btn: finalActive1,
                }"
              >
                {{
                      list.status == 0
                        ? "대기"
                        : list.status == 1
                        ? "진행"
                        : list.status == 2
                        ? "종료"
                        : "종료"
                    }}
              </button></span
            >
          </li>
          <!-- 한칸 끝 -->
        </ul>
        <div class="load_more">
          <button class="button">Load More</button>
        </div>
      </div>
    </body>
    <!-- 나의 챌린지 끝 -->
  </div>
</template>

<script>
import {mapGetters} from "vuex";
export default {
  name: "MyPageChallenge",
  computed:{
    ...mapGetters(["myChallenge"]),
    ...mapGetters(["loginUser"]),
  },
  created(){
    this.$store.dispatch("getMyChallenge",this.loginUser.id);
    console.log(this.loginUser.id);
  },
  data() {
    return {
      readyActive1: true,
      startActive1: false,
      finalActive1: false,

      readyActive2: false,
      startActive2: true,
      finalActive2: false,

      readyActive3: false,
      startActive3: false,
      finalActive3: true,
    };
  },
  methods:{
    movePage_detail(no){
      this.$router.push(`/challenge/detail/${no}`);
    }
  }
};
</script>

<style scoped>
/* 프리뷰 시작 */
body {
  font-family: sans-serif;
  margin: 0px;
  padding-bottom: 5em;
}
/* .border {
  height: 6px;
  border: 0;
  background-color: #fd7576;
  background-image: linear-gradient(
    to right,
    #fd7576,
    #fd7576 25%,
    #fec85d 25%,
    #fec85d 50%,
    #4db8de 50%,
    #4db8de 75%,
    #a378fc 75%,
    #a378fc 100%,
    #a378fc 100%
  );
  -webkit-background-size: 50% 100%;
  -moz-background-size: 50% 100%;
  background-size: 50% 100%;
} */

.clearfix:before,
.clearfix:after {
  content: " ";
  display: table;
}
.clearfix:after {
  clear: both;
}
.wrapper {
  width: 1000px;
  max-width: 90%;
  margin: 0 auto;
}
.main_header {
  margin: 5em 0 3em 0;
}
.logo img {
  width: 75px;
  float: left;
}
.logo h1 {
  float: left;
  margin: 0 0 0 1em;
  text-transform: uppercase;
  font-size: 1.7em;
  padding-top: 1.8em;
  color: #585858;
}
.logo h1 span {
  font-weight: 400;
}
.nav {
  float: right;
  padding-top: 2.7em;
}
.nav a {
  color: #74718f;
  margin-left: 1em;
  text-decoration: none;
  font-weight: 550;
}
.current {
  color: #34324b;
  font-weight: 700;
}
#timeline {
  width: 100%;
  position: relative;
  list-style: none;
  padding: 0;
}
#timeline a {
  text-decoration: none;
}
#timeline:after {
  content: "";
  width: 1px;
  background: 1px #e6e7e9;
  height: 90%;
  position: absolute;
  top: 3em;
  left: 3em;
  z-index: 0;
}
#timeline .listing {
  background-color: white;
  border: 1px solid #e6e7e9;
  border-radius: 2em;
  padding: 1em;
  margin-bottom: 1em;
  position: relative;
  z-index: 1;
  display: table;
  width: 100%;
  height: 100px;
  transition: all 0.3s ease-in-out;
}
.listing:hover {
  cursor: pointer;
  box-shadow: 0px 2px 40px 0 rgba(0, 0, 0, 0.1);
}
#timeline .listing img,
.info,
.job_type {
  display: table-cell;
  vertical-align: middle;
  font-size: 0.9em;
  color: #74718f;
}
#timeline .image_wrapper {
  width: 150px;
}
#timeline .image_wrapper img {
  width: 150px;
  height: 150px;
  object-fit: cover;
  display: block;
  margin: 0 auto;
  border-radius: 1.7em;
}
.info {
  line-height: 1.5;
  width: 640px;
  /* padding-left:; */
}
.job_title {
  font-weight: 700;
  font-size: 1.5em;
  color: #34324b;
  padding-right: 3%;
}
.job_info {
  color: #74718f;
  display: block;
}
.job_info span {
  margin: 0 0.75em;
}
.job_type {
  text-transform: uppercase;
  font-weight: 700;
  font-size: 0.75em;
  position: relative;
  white-space: nowrap;
  padding-top: 0.6em;
  width: 100px;
}
.job_type:before {
  background-color: #fd7576;
  width: 10px;
  height: 10px;
  border-radius: 100%;
  position: absolute;
  left: -20px;
  top: 27px;
}
.full_time {
  color: #fd7576;
}
.full_time:before {
  background-color: #fd7576;
}
.part_time {
  color: #fec85d;
}
.part_time:before {
  background-color: #fec85d;
}
.freelance {
  color: #4db8de;
}
.freelance:before {
  background-color: #4db8de;
}
.contract {
  color: #a378fc;
}
.contract:before {
  background-color: #a378fc;
}
.date {
  display: inline-block;
  padding: 1em 0;
  width: 110px;
  background-color: white;
  border: 1px solid #e6e7e9;
  border-radius: 3em;
  font-weight: 700;
  font-size: 0.9em;
  text-align: center;
  z-index: 2;
  position: relative;
  margin: 1em 0 2em 0;
  color: #34324b;
}
.load_more {
  margin-top: 7em;
  text-align: center;
}
.button {
  border: 2px solid #fb9f4c;
  outline: none;
  padding: 1em 3.5em;
  text-align: center;
  background: transparent;
  border-radius: 3em;
  color: #fb9f4c;
  transition: all 0.3s ease-in-out;
  font-weight: 500;
  height: 6em;
  margin: auto;
}
.button:hover {
  background-color: #fb9f4c;
  color: white;
}

@media only screen and (max-width: 768px) {
  .main_header {
    margin: 3em 0;
  }
  .logo {
    display: block;
    text-align: center;
  }
  .logo img {
    float: none;
  }
  .logo h1 {
    float: none;
    margin: 0 auto;
    text-transform: uppercase;
    font-size: 1.1em;
  }
  .nav {
    float: none;
    text-align: center;
  }
  .nav a {
    margin: 0 0.5em;
    text-decoration: none;
  }
}
/* 프리뷰 끝 */

/* 버튼 시작 */
.finish_btn {
  width: 6em;
  height: 3em;
  border-color: #4db8de;
  border: solid 1px;
  background-color: white;
  border-radius: 10px;
  color: #4db8de;
  font-size: 1.2em;
  font-weight: 600;
}
.finish_btn:hover {
  color: white;
  background-color: #4db8de;
}

.start_btn {
  width: 6em;
  height: 3em;
  border-color: #fd7576;
  border: solid 1px;
  background-color: white;
  border-radius: 10px;
  color: #fd7576;
  font-size: 1.2em;
  font-weight: 600;
}
.start_btn:hover {
  color: white;
  background-color: #fd7576;
}

.wait_btn {
  width: 6em;
  height: 3em;
  border-color: #fec85d;
  border: solid 1px;
  background-color: white;
  border-radius: 10px;
  color: #fec85d;
  font-size: 1.2em;
  font-weight: 600;
}
.wait_btn:hover {
  color: white;
  background-color: #fec85d;
}

/* 버튼 끝 */

.job_people {
  display: flex;
  align-items: center;
}

.challenge-detail-fontsize {
  padding-top: 0.8em;
  font-size: 1.2em;
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
