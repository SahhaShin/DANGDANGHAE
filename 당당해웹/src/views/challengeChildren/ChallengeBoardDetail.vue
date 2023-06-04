<template>
  <div>
    <!-- 챌린지 상세 html
    ChallengeForm.html에서 일부만 변경하여 사용합니다. -->
    <router-view />
    <!-- 기간이 끝난 챌린지는 종료된 챌린지입니다 알림이 뜹니다. -->

    <!-- 가입버튼을 누르면 ChallengeJoinModal.vue 혹은 ChallengeAlreadyJoinModal.vue가 열립니다. -->

    <!-- 특정 유저의 닉네임을 누르면 ChallengeRecordModal.vue가 열립니다. -->

    <!-- 기간이 끝난 챌린지는 승리 / 패배 모달 ChallengeResultModal.vue이 열립니다. -->
    <div class="backgroundgra"></div>
    <div>
      <div class="main" style="background-color: white">
        <div class="form-margin">
          <!-- 여기에 챌린지 이름 연동해조~~~ -->
          <p class="title">
            {{ challenge.title }}
            <span style="font-size: 16px">참가비 {{ challenge.point }}pt</span>
          </p>

          <div class="content-margin">
            <p class="subtitle">챌린지</p>

            <div class="title-bar">
              <input class="input" v-model="challengeInfo.title" readonly />
            </div>
            <p class="subtitle">챌린지 기간</p>
            <div class="title-bar">
              <input
                type="text"
                class="input"
                v-model="challengeInfo.start_date"
                readonly
              />
            </div>
            <div class="title-bar">
              <input
                type="text"
                class="input"
                v-model="challengeInfo.end_date"
                readonly
              />
            </div>
            <p class="subtitle">챌린지 목표</p>
            <div class="b-title-bar">
              <textarea
                class="b-input"
                v-model="challengeInfo.content"
                readonly
              ></textarea>
            </div>

            <div>
              <div style="width: 55rem">
                <p class="subtitle" style="padding-top: 3rem">당당하게 살자!</p>
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
                        <div @click="user_info(user.id)">{{ user.nick_name }}</div>
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
                    <li
                      v-for="(user, index) in team2"
                      :key="index"
                      class="li-oneline"
                      style="margin-top: 2.5rem"
                    >
                      <div
                        :class="{ 'li-name-on': team2_on, 'li-name': team1_on }"
                      >
                        <div @click="user_info(user.id)">{{ user.nick_name }}</div>
                      </div>
                      <div
                        :class="{ 'li-rank-on': team2_on, 'li-rank': team1_on }"
                      >
                        {{ user.point }} pt
                      </div>
                    </li>
                  </ul>
                </div>
                <div
                  v-if="loginUser.id != challenge.organizer"
                  style="color: red; margin-top: 3%"
                >
                  * 대기중에 탈퇴하셔도 포인트는 돌려드리지 않으니 신중히 결정
                  부탁드립니다.
                </div>
                <div class="button-margin">
                  <div class="button-margin-left">
                    <router-link to="/challenge">
                      <button class="btn">목록</button>
                    </router-link>
                  </div>
                  <div class="button-margin-right">
                    <!-- 챌린지가 대기중인 상태에만 가입이 가능하다. -->
                    <!-- 유저의 포인트가 참가비 만큼 있으면 가입이 가능하다. -->
                    <button
                      @click="open_modal_join"
                      v-if="
                        challenge.organizer != loginUser.id &&
                        challenge.status == 0 &&
                        loginUser.point >= challenge.point
                      "
                      class="btn"
                    >
                      가입하기
                    </button>
                    <ChallengeJoinModal
                      v-if="join_challenge_modal"
                      @join_challenge="join_challenge"
                      @close_modal_join="close_modal_join"
                    />
                    <router-link :to="`/challenge/modify/${challenge.no}`"
                      ><button
                        v-if="challenge.organizer == loginUser.id"
                        class="btn"
                      >
                        수정하기
                      </button></router-link
                    >
                    <!-- 방장은 탈퇴버튼을 누르면 안된다! 모달이 뜹니당 -->
                    <!-- 가입하지 않은 멤버는 탈퇴버튼이 안보이도록..? -->
                    <!-- 가입한 멤버는 가입하기 버튼이 안보이거나 눌렀을때 이미 가입되어있습니다 모달이 뜨도록... -->
                    <button
                      @click="open_modal_exit"
                      v-if="
                        challenge.organizer != loginUser.id &&
                        challenge.status == 0
                      "
                      class="btn"
                    >
                      탈퇴
                    </button>
                    <ChallengeExitModal
                      v-if="exit_challenge_modal"
                      @exit_challenge="exit_challenge"
                      @close_modal_exit="close_modal_exit"
                    />
                    <!-- 삭제버튼은 대기중인 방에만 뜬다. -->
                    <button
                      @click="delete_challenge_open"
                      v-if="
                        challenge.organizer == loginUser.id &&
                        challenge.status == 0
                      "
                      class="btn"
                    >
                      삭제하기
                    </button>
                    <ChallengeDeleteModal
                      @close_modal_challenge="close_modal_challenge"
                      @delete_challenge="delete_challenge"
                      v-if="delete_challenge_modal"
                    />
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>

      <!-- 챌린지에 참여한 각 회원의 정보 보여줌 -->
      <ChallengeUserInfoModal v-if="challengeExerciseModal" :dailyExercise="dailyExercise" @close_modal_exe="close_modal_exe" />
      <div style="display:flex">

    <button
      @click="game_start"
      class="start_btn"
      v-if="challenge.organizer == loginUser.id && challenge.status == 0"
    >
      START
    </button>
    <button
      @click="game_end"
      class="start_btn"
      v-if="challenge.organizer == loginUser.id && challenge.status == 1"
    >
      END
    </button>

      </div>
    </div>

    <ChallengeResultWinModal v-if="winModal" @close_win="close_win" />
  </div>
</template>

<script>
import { mapGetters } from "vuex";
import ChallengeDeleteModal from "../../components/modal/ChallengeDeleteModal.vue";
import ChallengeJoinModal from "../../components/modal/ChallengeJoinModal.vue";
import ChallengeExitModal from "../../components/modal/ChallengeExitModal.vue";
import ChallengeResultWinModal from "../../components/modal/ChallengeResultWinModal.vue";
import ChallengeUserInfoModal from "../../components/modal/ChallengeUserInfoModal.vue";
// import $ from "jquery";
export default {
  name: "ChallengeBoardDetail",
  created() {
    var no = this.$route.params.no;
    this.$store.dispatch("getchallenge", no);
    this.$store.dispatch("getTeams", no);
  },

  components: {
    ChallengeDeleteModal,
    ChallengeJoinModal,
    ChallengeExitModal,
    ChallengeResultWinModal,
    ChallengeUserInfoModal,
  },

  computed: {
    ...mapGetters(["challenge"]),
    ...mapGetters(["team1"]),
    ...mapGetters(["team2"]),
    ...mapGetters(["loginUser"]),
    ...mapGetters(["winner"]),
    ...mapGetters(["prizeFee"]),
    ...mapGetters(["dailyExercise"]),
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

      delete_challenge_modal: false,
      join_challenge_modal: false,
      exit_challenge_modal: false,
      team1_on: true,
      team2_on: false,
      winModal: false,
      challengeExerciseModal : false,
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
    close_modal_exe(){
      this.challengeExerciseModal = false;
    },
    user_info(user_id){
      // 같이 챌린지 하는 사람들의 운동 정보 확인 가능
      this.$store.dispatch("getRecordExercise",user_id);//유저 아이디로 운동 기록 가져오기
      this.challengeExerciseModal=true;
    },
    exit_challenge() {
      var user_have_challenge = {
        users_id: this.loginUser.id,
        challenge_no: this.challenge.no,
        team_no: this.challengeInfo.organizer_team,
      };
      this.$store.dispatch("exitTeam", user_have_challenge);
    },
    open_modal_exit() {
      this.exit_challenge_modal = true;
    },
    close_modal_exit() {
      this.exit_challenge_modal = false;
    },
    join_challenge() {
      var user_have_challenge = {
        users_id: this.loginUser.id,
        challenge_no: this.challenge.no,
        team_no: this.challengeInfo.organizer_team,
      };
      // console.log("123"+user_have_challenge.users_id);
      // console.log("123"+user_have_challenge.challenge_no);
      // console.log("123"+user_have_challenge.team_no);
      this.$store.dispatch("signTeam", user_have_challenge);
      this.join_challenge_modal = false;
    },
    open_modal_join() {
      this.join_challenge_modal = true;
    },
    close_modal_join() {
      this.join_challenge_modal = false;
    },
    close_modal_challenge() {
      this.delete_challenge_modal = false;
    },
    delete_challenge_open() {
      this.delete_challenge_modal = true;
    },
    delete_challenge() {
      this.$store.dispatch("deleteChallenge", this.challenge.no);
      this.$router.push("/challenge");
    },
    game_start() {
      //진행중이라고 상태 바꿔야 함
      //상세페이지로 다시 와야함
      //스타트 버튼이 안보이고 종료버튼이 보여야함
      var no = this.$route.params.no;
      var challenge = {
        no: no,
        title: this.challengeInfo.title,
        content: this.challengeInfo.content,
        start_date: this.challengeInfo.start_date,
        end_date: this.challengeInfo.end_date,
        point: this.challengeInfo.point,
        status: 1,
        image: "",
        organizer: this.loginUser.id,
        organizer_team: 1,
        modify: false,
      };
      this.$store.dispatch("changeChallengeStatus", challenge);
      this.$router.push(`/challenge/detail/${no}`);
    },
    game_end() {
      this.$store.dispatch("getWinner", this.challenge.no);
      this.winModal = true;
    },

    close_win() {
      this.winModal = false;
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

.start_btn {
  margin: 0 auto;
  width: 100px;
  height: 40px;
  border: 1px solid #afafaf;
  color: white;
  background-color: white;
  border-radius: 5px;
  background-color: #afafaf;
  font-size: 25px;
}
.start_btn:hover {
  color: white;
  border: 1px solid #fd7577;
  background-color: #fd7577;
}
</style>
