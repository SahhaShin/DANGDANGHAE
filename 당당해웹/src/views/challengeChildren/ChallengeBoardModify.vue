<template>
  <div>
    <!-- 챌린지 수정 html -->
    <!-- 등록 버튼을 누르면 RegistModal.vue가 열립니다. 
    * alert로 변경 가능-->

    <!-- 목록 / 취소 버튼을 누르면 RegistNotModal.vue가 열립니다.-->
    <div class="backgroundgra"></div>
    <div>
      <div class="main" style="background-color: white">
        <div class="form-margin">
          <p class="title">챌린지 수정  
          </p>
          <div class="content-margin">
            <p class="subtitle">챌린지 <span style="color:#bcbcbc;font-size:12px">(50자 제한)</span></p>
            <div class="title-bar">
              <input class="input" v-model="challengeInfo.title" />
            </div>
            
            <p class="subtitle">챌린지 기간</p>
            <div class="title-bar">
              <input
                v-model="challengeInfo.start_date"
                type="text"
                class="input"
              />
            </div>
            <div class="title-bar">
              <input
                v-model="challengeInfo.end_date"
                type="text"
                class="input"
              />
            </div>
            <p class="subtitle">챌린지 목표<span style="color:#bcbcbc;font-size:12px">(500자 제한)</span></p>
            <div class="b-title-bar">
              <textarea
                class="b-input"
                v-model="challengeInfo.content"
              ></textarea>
            </div>

            <div>
              <div style="width: 55rem">
                <p class="subtitle" style="padding-top: 3rem;">
                  당당하게 살자!
                </p>
                <div
                  :class="{ 'team-box': team1_on, 'team-box-off': team2_on }"
                >
                  <button
                    @click="
                      (challengeInfo.organizer_team = 1),
                        (team1_on = true),
                        (team2_on = false)
                    "
                    :class="{
                      'team-box-btn-on': team1_on,
                      'team-box-btn-off': team2_on,
                    }"
                    type="button"
                  >
                    멋지당
                  </button>
                  <ul>
                    <li
                      v-for="(user, index) in team1"
                      :key="index"
                      class="li-oneline"
                      style="margin-top: 2.5rem"
                    >
                      <div
                        :class="{ 'li-name-on': team1_on, 'li-name': team2_on }"
                      >
                        {{ user.nick_name }}
                      </div>
                      <div
                        :class="{ 'li-rank-on': team1_on, 'li-rank': team2_on }"
                      >
                        {{ user.point }} pt
                      </div>
                    </li>
                  </ul>
                </div>
                <div
                  :class="{ 'team-box': team2_on, 'team-box-off': team1_on }"
                  style="float: right"
                >
                  <button
                    @click="
                      (challengeInfo.organizer_team = 2),
                        (team1_on = false),
                        (team2_on = true)
                    "
                    :class="{
                      'team-box-btn-on': team2_on,
                      'team-box-btn-off': team1_on,
                    }"
                    type="button"
                  >
                    예쁘당
                  </button>
                  <ul>
                    <!-- 여기서만큼은 내가 1등이야 핰핰핰핰핰 -->
                    <li
                      v-for="(user, index) in team2"
                      :key="index"
                      class="li-oneline"
                      style="margin-top: 2.5rem"
                    >
                      <div
                        :class="{ 'li-name-on': team2_on, 'li-name': team1_on }"
                      >
                       {{ user.nick_name }}
                      </div>
                      <div
                        :class="{ 'li-rank-on': team2_on, 'li-rank': team1_on }"
                      >
                        {{ user.point }} pt
                      </div>
                    </li>
                  </ul>
                </div>
                <div style="color:red;margin-top:3%;">
                  * 가입한 팀은 변경할 수 없습니다.
                </div>
                <div class="button-margin">
                  <div class="button-margin-left">
                    <router-link to="/challenge">
                      <button class="btn">목록</button>
                    </router-link>
                  </div>
                  <div class="button-margin-right">
                    <!-- 수정버튼 -->
                    <button
                      @click="update_challenge"
                      v-if="challenge.organizer == loginUser.id"
                      class="btn"
                    >
                      수정하기
                    </button>
                    <!-- 수정 안하고 다시 상세로 돌아가는 버튼 -->
                    <router-link :to="`/challenge/detail/${challenge.no}`"
                      ><button
                        v-if="challenge.organizer == loginUser.id"
                        class="btn"
                      >
                        돌아가기
                      </button></router-link
                    >
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapGetters } from "vuex";
// import $ from "jquery";
export default {
  name: "ChallegeBoardModify",
  created() {
    var no = this.$route.params.no;
    this.$store.dispatch("getchallenge", no);
    this.$store.dispatch("getTeams", no);
  },
  computed: {
    ...mapGetters(["challenge"]),
    ...mapGetters(["team1"]),
    ...mapGetters(["team2"]),
    ...mapGetters(["loginUser"]),
  },
  data() {
    return {
      challengeInfo: {
        no: 0,
        title: "",
        content: "",
        start_date: "",
        end_date: "",
        point: 0,
        status: 0,
        image: "",
        organizer: "",
        organizer_team: 1,
        modify: false,
      },
      team1_on: true,
      team2_on: false,
    };
  },
  watch: {
    challenge() {
      this.challengeInfo.no = this.challenge.no;
      this.challengeInfo.title = this.challenge.title;
      this.challengeInfo.content = this.challenge.content;
      this.challengeInfo.start_date = this.challenge.start_date;
      this.challengeInfo.end_date = this.challenge.end_date;
      this.challengeInfo.point = this.challenge.point;
      this.challengeInfo.status = this.challenge.status;
      this.challengeInfo.image = this.challenge.image;
      this.challengeInfo.organizer = this.challenge.organizer;
      this.challengeInfo.organizer_team = this.challenge.organizer_team;
      this.challengeInfo.modify = true;
    },
  },
  methods: {
    update_challenge() {
      this.$store.dispatch("updateChallenge", this.challengeInfo);

      this.$router.push(`/challenge/detail/${this.challenge.no}`);
    },

  },
};
</script>

<style scoped>
#contents {
  min-height: 100%;
}

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

.main {
  width: 70rem;
  margin: 2rem auto;
  box-shadow: 0 4px 4px 4px rgba(248, 210, 175, 0.247);
  border-radius: 10px;
}

.title {
  font-size: 3rem;
  padding: 3rem 0 3rem 4rem;
  border-radius: 10px 10px 0px 0px;
  background-color: #ff871e;
  color: white;
  font-weight: 600;
}

.subtitle {
  padding-top: 1.5rem;
  font-size: 1.5rem;
  font-weight: 600;
  color: #7d7d7d;
}

.input,
.b-input {
  height: 3rem;
  outline: medium;
  border: none;
  display: block;
  margin-left: 2rem;
  margin-bottom: 3rem;
}

.title-bar {
  height: 3rem;
  outline: medium;
  border: none;
  border-left: 3px solid #7d7d7d;
  display: block;
  margin-bottom: 3rem;
}
.b-title-bar {
  height: 6rem;
  outline: medium;
  border: none;
  border-left: 3px solid #7d7d7d;
  display: block;
  margin-bottom: 3rem;
}

.b-input {
  height: 6rem;
  width: 90%;
}

.write {
  padding: 10px 0;
}

.write label {
  font-weight: normal;
}

.left-l {
  display: inline-block;
  width: 23rem;
}

.btn {
  width: 8rem;
  height: 4rem;
  border: 1px solid #afafaf;
  color: #7d7d7d;
  background-color: white;
  border-radius: 5px;
}

.btn:hover {
  color: white;
  border: 1px solid #ff871e;
  background-color: #ff871e;
}

input::placeholder {
  color: #7d7d7d;
  font-weight: 600;
}
textarea::placeholder {
  color: #7d7d7d;
  line-height: 5.5rem;
  font-weight: 600;
}

.form-margin {
  margin-top: 15rem;
}

.content-margin {
  padding: 4rem 6rem 6rem 7rem;
}

.team-box {
  width: 27rem;
  height: 20rem;
  border: 0.1rem solid #ff871e;
  border-radius: 10px;
  display: inline-block;
}
.team-box-off {
  width: 27rem;
  height: 20rem;
  border: 0.1rem solid #afafaf;
  border-radius: 10px;
  display: inline-block;
}

.team-box-btn-on {
  border-radius: 10px 10px 0px 0px;
  width: 27rem;
  height: 5rem;
  border: none;
  background: #ff871e;
  color: white;
  font-size: 1.6rem;
  font-weight: 600;
}
.team-box-btn-off {
  border-radius: 10px 10px 0px 0px;
  width: 27rem;
  height: 5rem;
  border: none;
  border-right: 0.1rem solid #afafaf;
  border-bottom: 0.1rem solid #afafaf;
  background: white;
  color: #7d7d7d;
  font-size: 1.6rem;
  font-weight: 600;
}

.li-oneline {
  align-items: center;
  margin: 1.5rem 0rem 1.5rem 0rem;
  display: flex;
  font-size: 1.6rem;

  justify-content: center;
}

.li-name-on {
  width: 50%;
  padding: 0rem 2rem 0rem 5.2rem;
  font-weight: 700;
  color: #ff871e;
}

.li-name {
  width: 50%;
  padding: 0rem 2rem 0rem 5.2rem;
  font-weight: 700;
  color: #797979;
}

.li-rank-on {
  width: 40%;
  font-weight: 200;
  color: #ffcea4;
}

.li-rank {
  width: 40%;
  font-weight: 200;
  color: #aeaeae;
}

.button-margin {
  margin-top: 5rem;
  margin-bottom: 3rem;
  display: flex;
}

.button-margin-left {
}
.button-margin-right {
  margin-left: auto;
}

.date-wrap {
  width: 300px;
  margin: 0 auto;
}

input {
  margin: 0 auto;
  width: 20rem;
  position: relative;
}

::-webkit-calendar-picker-indicator {
  position: absolute;
  right: -10px;
  top: 0;
  transform: translateX(-10px);
  padding-left: 3000px;
  height: 100%;
}

input[type="date"]:valid {
  color: #7d7d7d;
  font-weight: 600;
}

input[type="date"]::-webkit-calendar-picker-indicator {
  background: transparent;
  z-index: 1;
}

input[type="date"]::-webkit-datetime-edit-text {
  opacity: 0.5;
}

input[type="date"]::-webkit-datetime-edit-month-field {
  opacity: 0.5;
}

input[type="date"]::-webkit-datetime-edit-day-field {
  opacity: 0.5;
}

input[type="date"]::-webkit-datetime-edit-year-field {
  opacity: 0.5;
}

/* 여기를 추가해주세요! 시작 */
/* 내브바 켜지면 오버레이값 */
.backgroundgra {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  /* background: rgba(230, 225, 217, 0.7); */

  display: block;
  z-index: -1;
}

/* 여기를 추가해주세요! 끝 */
</style>
