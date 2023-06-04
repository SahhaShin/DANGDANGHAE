<template>
  <div>
    <!-- 여기를 추가해주세요! 시작 -->
    <div class="backgroundgra"></div>
    <div>
      <div class="main" style="background-color: white">
        <!-- 여기를 추가해주세요! 끝 -->
        <div class="form-margin">
          <p class="title">챌린지 만들기</p>
          <div class="content-margin">
            <p class="subtitle">
              챌린지
              <span style="color: #bcbcbc; font-size: 12px">(50자 제한)</span>
            </p>
            <div class="title-bar">
              <input
                v-model="challengeInfo.title"
                class="input"
                placeholder="달성 목표를 적어주세요"
              />
            </div>
            <p class="subtitle">챌린지 기간</p>
            <div class="title-bar">
              <input
                v-model="challengeInfo.start_date"
                type="date"
                class="input"
                placeholder="달성 기간을 적어주세요"
              />
            </div>
            <div class="title-bar">
              <input
                v-model="challengeInfo.end_date"
                type="date"
                class="input"
                placeholder="달성 기간을 적어주세요"
              />
            </div>
            <p class="subtitle">
              챌린지 내용<span style="color: #bcbcbc; font-size: 12px"
                >(50자 제한)</span
              >
            </p>
            <div class="b-title-bar">
              <textarea
                v-model="challengeInfo.content"
                class="b-input"
                placeholder="어떤 목표를 달성할 건가요?"
              ></textarea>
            </div>

            <p class="subtitle">가입 포인트</p>
            <div class="title-bar">
              <input
                v-model="challengeInfo.point"
                type="number"
                min="0"
                max="1000"
                class="input"
                placeholder="챌린지 가입 포인트를 적어주세요"
              />
            </div>

            <div>
              <div style="width: 55rem">
                <p class="subtitle" style="padding-top: 3rem; color: #ff871e">
                  팀을 선택해주세요!
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
                    <!-- <li
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
                    </li> -->
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
                    <!-- <li
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
                    </li> -->
                  </ul>
                </div>
                <div class="button-margin">
                  <div class="button-margin-left">
                    <router-link to="/challenge">
                      <button class="btn">목록</button>
                    </router-link>
                  </div>
                  <div class="button-margin-right">
                    <button @click="challenge_regist" class="btn">등록</button>
                    <router-link to="/challenge"
                      ><button class="btn">취소</button></router-link
                    >
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <MainFooter />
  </div>
</template>

<script>
import { mapGetters } from "vuex";
import MainFooter from "../../components/common/MainFooter.vue";
// import $ from "jquery";
export default {
  name: "ChallengeBoardWrite",
  component: { MainFooter },
  computed: {
    ...mapGetters(["loginUser"]),
    ...mapGetters(["team1"]),
    ...mapGetters(["team2"]),
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

  methods: {
    challenge_regist() {
      var challenge = {
        no: 0,
        title: this.challengeInfo.title,
        content: this.challengeInfo.content,
        start_date: this.challengeInfo.start_date,
        end_date: this.challengeInfo.end_date,
        point: this.challengeInfo.point,
        status: 0,
        image: "",
        organizer: this.loginUser.id,
        organizer_team: this.challengeInfo.organizer_team,
        modify: false,
      };

      this.$store.dispatch("makeChallenge", challenge);

      this.$router.push("/challenge", challenge);
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
  /* position: fixed; */
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
