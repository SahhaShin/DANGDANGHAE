import Vue from "vue";
import Vuex from "vuex";
import axios from "axios";
// import router from "../router";
import createPersistedState from "vuex-persistedstate";

Vue.use(Vuex);

// //모든 함수의 alert는 기능 확인용 잘 되면 지울 예정

export default new Vuex.Store({
  plugins: [createPersistedState()],
  state: {
    //0. 추가 정보 입력 모달 오픈 여부
    open_modal: false,

    // 1. 유저 관련
    users: [],
    user: {},
    loginUser: null,
    dailyBlood: [], //로그인 유저의 당기록
    avg_blood: 0, //로그인 유저의 평균 혈당
    dailyExercise: [], //로그인 유저의 운동기록
    dailyExerciseNew: [], //로그인 유저의 최신순 운동기록
    afterthird: [], //4등 이후 유저들
    myrank: 0,
    daily_exercise_times: [], //일마다 몇 회 운동했는가?

    //2. 챌린지 관련
    challenges: [], //챌린지 전체 목록
    popchallenges: [], //인기 챌린지 3개
    challenge: {}, //클릭한 특정 챌린지
    myChallenge: [], //특정 회원의 챌린지
    team1: [], //특정 챌린지 1팀
    team2: [], //특정 챌린지 2팀
    winner: "",
    prizeFee: "",

    //3. 커뮤니티 관련
    board: {}, //내가 선택한 하나의 게시물 정보
    boards: [], //타입 상관없는 전체 게시물
    boardsTypeInfo: [], //정보 게시판 전체 글 (최신순 or 최신 인기) -- 메인, 글검색
    boardsTypeFree: [], //자유 게시판 전체 글 (최신순 or 최신 인기) -- 메인, 글검색
    comment: {}, //특정 게시물의 특정 댓글
    comments: [], //특정 게시물의 전체 댓글

    //4. 상품 관련
    products: [], //전체 상품
    productsByFood: [], //타입에 따른 상품(식료품)
    productsByLiving: [], //타입에 따른 상품(생활용품)
    productsByElec: [], //타입에 따른 상품(가전)
    //product: {}, //내가 클릭한 특정 상품(현재 기획상으로 구현 안함)
  },
  getters: {
    //0.추가정보 입력 모달
    open_modal: function (state) {
      return state.open_modal;
    },
    //1. 유저관련
    userCnt: function (state) {
      return state.users.length;
    },
    dailyBlood: function (state) {
      return state.dailyBlood;
    },
    dailyExercise: function (state) {
      return state.dailyExercise;
    },
    loginUser: function (state) {
      return state.loginUser;
    },
    users: function (state) {
      return state.users;
    },
    afterthird: function (state) {
      return state.afterthird;
    },
    myrank: function (state) {
      return state.myrank;
    },
    daily_exercise_times: function (state) {
      return state.daily_exercise_times;
    },
    dailyExerciseNew: function (state) {
      return state.dailyExerciseNew;
    },
    avg_blood: function (state) {
      return state.avg_blood;
    },
    prizeFee: function (state) {
      return state.prizeFee;
    },

    //2. 챌린지 관련
    challenges(state) {
      return state.challenges;
    },
    challenge(state) {
      return state.challenge;
    },
    myChallenge(state) {
      return state.myChallenge;
    },
    team1(state) {
      return state.team1;
    },
    team2(state) {
      return state.team2;
    }, //특정 챌린지 2팀
    winner(state) {
      return state.winner;
    },
    popchallenges(state) {
      return state.popchallenges;
    },

    //3. 커뮤니티 관련
    board(state) {
      return state.board;
    },
    boardsTypeInfo(state) {
      return state.boardsTypeInfo;
    },
    boardsTypeFree(state) {
      return state.boardsTypeFree;
    },
    comment(state) {
      return state.comment;
    },
    comments(state) {
      return state.comments;
    },
    boards(state){
      return state.boards;
    },

    //4. 상품관련

    //추가되는 상품이 있을 때마다 리스트 감시 및 변경
    products(state) {
      return state.products;
    },

    //가져오는 상품 타입에 따라 이 리스트가 달라짐
    productsByFood(state) {
      return state.productsByFood;
    },
    productsByLiving(state) {
      return state.productsByLiving;
    },
    productsByElec(state) {
      return state.productsByElec;
    },

    //내가 클릭한 특정 상품이 달라질 때 마다 감시 및 변경
    // product(state) {
    //   return state.product;
    // },
  },
  mutations: {
    //1. 유저 관련
    CREATE_USER: function (state, user) {
      state.users.push(user);
      state.loginUser = user;
    },
    SET_USERS: function (state, users) {
      state.users = users;

      //4위~ 그이후 유저들 따로 담음
      state.afterthird = [];

      if (state.loginUser.id != null) {
        for (var j = 0; j < 3; j++) {
          //나의 랭킹 기록하기
          if (state.loginUser.id == state.users[j].id) {
            state.myrank = j + 1;
          }
        }
      }
      for (var i = 3; i < users.length; i++) {
        state.afterthird.push(users[i]);
        //나의 랭킹 기록하기
        if (state.loginUser.id == state.users[i].id) {
          state.myrank = i + 1;
        }
      }
    },
    SET_USER: function (state, user) {
      state.user = user;
    },
    SET_LOGIN_USER: function (state, user) {
      //현재 위의 유저는 아이디 비번만 가지고 있어서
      //마이페이지에서 활용하기 위해 전체 정보를 가져와야 함
      for (let i = 0; i < state.users.length; i++) {
        if (user.id == state.users[i].id) {
          state.loginUser = state.users[i];
          break;
        }
      }
    },
    LOGOUT: function (state) {
      state.loginUser = null;
    },

    //특정 회원의 데일리 당 기록
    SET_DAILY_SUGAR: function (state, blood) {
      state.dailyBlood.push(blood);
    },

    GET_DAILY_SUGAR: function (state, sugar_list) {
      state.dailyBlood = sugar_list;

      let total_sugar = 0;
      for (let i = 0; i < 7; i++) {
        total_sugar += sugar_list[i].blood_sugar;
        console.log(total_sugar);
      }

      if (sugar_list.length != 0) {
        state.avg_blood = total_sugar / sugar_list.length;
      }
    },

    GET_MY_CHALLENGE: function (state, challenge) {
      state.myChallenge = challenge;
    },

    SET_RECORD_EXERCISE: function (state, record_time) {
      state.dailyExercise.push(record_time); //하나 짜리 기록임

      //스트릭 다시 계산
      //각 일 수마다 몇 회 운동했는지 횟수 저장
      //30분 이상 운동했어야 인정
      if (record_time.length > 0) {
        var prevYear = 2023;
        var prevMonth = 5;
        var prevDay = 24;

        var count = 0;
        state.daily_exercise_times = []; //초기화

        var first_date = new Date(record_time[0].end_time.split(" ")[0]); //첫 기록
        prevYear = first_date.getFullYear();
        prevMonth = first_date.getMonth() + 1;
        prevDay = first_date.getDate();

        for (var i = 0; i < record_time.length; i++) {
          var date = new Date(record_time[i].end_time.split(" ")[0]);

          // if(i==0){
          //   prevYear = date.getFullYear();
          //   prevMonth = date.getMonth()+1;
          //   prevDay = date.getDate();
          // }

          var year = date.getFullYear();
          var month = date.getMonth() + 1;
          var day = date.getDate();

          var total =
            Number(record_time[i].total_time.split(":")[0]) +
            Number(record_time[i].total_time.split(":")[1]) / 60;
          console.log(total);

          if (prevYear == year && prevMonth == month && prevDay == day) {
            //같은 일자
            if (total >= 30) {
              count++;
            }
            //마지막 순서라면 그냥 넣어주기
            if (i == record_time.length - 1)
              state.daily_exercise_times.push(count);
          } else {
            //다른 일자면 현재 count 를 daily_exercise_times 추가
            state.daily_exercise_times.push(count);

            //다른일자기 때문에 1부터 다시 count 시작
            //30분 이상이 아니면 0부터 시작
            if (total >= 30) count = 1;
            else count = 0;

            // console.log(count);
          }
          // console.log(month + " " + day + " " + total);
          // console.log(state.daily_exercise_times);

          //이전 날짜 데이터 변경
          prevYear = year;
          prevMonth = month;
          prevDay = day;
        }
      }
    },
    GET_RECORD_EXERCISE: function (state, record_time) {
      if (record_time == []) {
        state.dailyExercise = [];
        state.daily_exercise_times = [];
        return;
      }
      state.dailyExercise = record_time;

      //각 일 수마다 몇 회 운동했는지 횟수 저장
      //30분 이상 운동했어야 인정
      if (record_time.length > 0) {
        var prevYear = 2023;
        var prevMonth = 5;
        var prevDay = 24;

        var count = 0;
        state.daily_exercise_times = []; //초기화

        var first_date = new Date(record_time[0].end_time.split(" ")[0]); //첫 기록
        prevYear = first_date.getFullYear();
        prevMonth = first_date.getMonth() + 1;
        prevDay = first_date.getDate();

        for (var i = 0; i < record_time.length; i++) {
          var date = new Date(record_time[i].end_time.split(" ")[0]);
          var year = date.getFullYear();
          var month = date.getMonth() + 1;
          var day = date.getDate();

          var total =
            Number(record_time[i].total_time.split(":")[0]) +
            Number(record_time[i].total_time.split(":")[1]) / 60;

          if (prevYear == year && prevMonth == month && prevDay == day) {
            //같은 일자
            if (total >= 30) {
              count++;
            }
            //마지막 순서라면 그냥 넣어주기
            if (i == record_time.length - 1)
              state.daily_exercise_times.push(count);
          } else {
            //다른 일자면 현재 count 를 daily_exercise_times 추가
            state.daily_exercise_times.push(count);

            //다른일자기 때문에 1부터 다시 count 시작
            //30분 이상이 아니면 0부터 시작
            if (total >= 30) count = 1;
            else count = 0;
          }

          //이전 날짜 데이터 변경
          prevYear = year;
          prevMonth = month;
          prevDay = day;
        }
      }
    },
    GET_RECORD_EXERCISE_NEW: function (state, record_time) {
      state.dailyExerciseNew = record_time;
    },
    CHANGE_RANK: function (state, user) {
      for (let i = 0; i < state.users.length; i++) {
        if (state.users[i].id == user.id) {
          state.users[i] = user;
          break;
        }
      }
    },
    UPDATE_USER: function (state, user) {
      for (let i = 0; i < state.users.length; i++) {
        if (state.users[i].id == user.id) {
          state.users[i] = user;
          state.loginUser = user;
          break;
        }
      }
    },

    //2.  챌린지 관련

    //전체 챌린지 가져오기
    GET_CHALLENGES: function (state, challenges) {
      state.challenges = challenges;

      state.popchallenges = [];
      for (var i = 0; i < challenges.length; i++) {
        if (i == 3) break;
        state.popchallenges.push(challenges[i]);
      }
    },

    //특정 챌린지 가져오기
    GET_CHALLENGE: function (state, challenge) {
      state.challenge = challenge;
    },

    //챌린지 만들기
    MAKE_CHALLENGE: function (state, challenge) {
      state.challenges.push(challenge);
      state.myChallenge.push(challenge);
    },

    //특정 챌린지 팀 맴버 정보 가져오기
    GET_TEAMS: function (state, members) {
      state.team1 = members[0];
      state.team2 = members[1];
    },

    GET_MY_CHALLENGES: function (state, challenges) {
      state.myChallenge = challenges;
    },

    //챌린지 + 팀 가입하기
    SIGN_TEAM: function () {
      //nothing
    },

    //챌린지 + 팀 탈퇴하기 => 안되면 수정 필요
    EXIT_TEAM: function () {
      // if(team_no)
      // for(let i=0;i<state.challenges.length;i++){
      //     if(state.challenges[i].no==no){
      //         state.challenges[i].status=status;
      //     }
      // }
      //nothing
    },

    //챌린지 상태 변경 (대기/시작/종료)
    //no, status
    CHANGE_CHALLENGE_STATUS: function (state, challenge) {
      for (let i = 0; i < state.challenges.length; i++) {
        if (state.challenges[i].no == challenge.no) {
          state.challenges[i].status = challenge.status;
        }
      }
    },

    //챌린지 수정하기
    UPDATE_CHALLENGE: function (state, challenge) {
      for (let i = 0; i < state.challenges.length; i++) {
        if (state.challenges[i].no == challenge.no) {
          state.challenges[i] = challenge;
        }
      }
    },

    //챌린지 삭제하기
    DELETE_CHALLENGE: function (state, no) {
      for (let i = 0; i < state.challenges.length; i++) {
        if (state.challenges[i].no == no) {
          state.challenges.splice(i, 1);
        }
      }
    },

    //승리 여부 판단하기
    GET_WINNER: function (state, winner) {
      var winTeam = winner.split(" ")[0]; //1 : 멋지당 2: 예쁘당
      var prizeFee = winner.split(" ")[1];
      console.log("winTeam = " + winTeam);
      console.log("prizeFee = " + prizeFee);

      if (winTeam == "team1") state.winner = "멋지당";
      else if (winTeam == "team2") state.winner = "예쁘당";
      else state.winner = "모든";

      state.prizeFee = prizeFee;
    },

    //3. 커뮤니티 관련
    SET_COMMENT_LIST: function (state, cmts) {
      state.comments = cmts;
    },

    REMOVE_COMMENT: function (state, comment_no) {
      for (var i = 0; i < state.comments.length; i++) {
        if (state.comments[i].no == comment_no) {
          state.comments.splice(i, 1);
          break;
        }
      }
    },

    UPDATE_COMMENT: function (state, comment) {
      for (var i = 0; i < state.comments.length; i++) {
        if (state.comments[i].no == comment.no) {
          state.comments[i].comment_content = comment.comment_content;
          break;
        }
      }
    },
    CREATE_COMMENT: function (state, comment) {
      state.comments.push(comment);
    },

    DETAIL_BOARD: function (state, brd) {
      state.board = brd;
    },

    UPDATE_BOARD: function (state, brd) {
      for (var i = 0; i < state.boards.length; i++) {
        if (state.boards[i].no == brd.no) state.board[i] = brd;
      }

      if (brd.type == "정보") {
        for (var j = 0; j < state.boardsTypeInfo.length; j++) {
          if (state.boardsTypeInfo[j].no == brd.no)
            state.boardsTypeInfo[j] = brd;
        }
      }
      if (brd.type == "자유") {
        for (var k = 0; k < state.boardsTypeFree.length; k++) {
          if (state.boardsTypeFree[k].no == brd.no)
            state.boardsTypeFree[k] = brd;
        }
      }
    },

    //게시판 전체 글 가져오기
    GET_ALL_BOARD: function (state, brds) {
      state.boards = brds;
      console.log(brds);
      console.log(state.boards);
    },
    DELETE_BOARD: function (state, no) {
      for (var i = 0; i < state.boards.length; i++) {
        if (state.boards[i].no == no) {
          state.boards.splice(i, 1);
          if (state.boards[i].type == "정보") {
            state.boardsTypeInfo.splice(i, 1);
          }
          if (state.boards[i].type == "자유") {
            state.boardsTypeInfo.splice(i, 1);
          }
          break;
        }
      }
    },

    CREATE_BOARD: function (state, brd) {
      state.boards.push(brd);
      if (brd.type == "정보") {
        state.boardsTypeInfo.push(brd);
      } else if (brd.type == "자유") {
        state.boardsTypeFree.push(brd);
      }
    },

    SEARCH_BOARDS: function (state, brds, type) {
      if (type == "정보") {
        state.boardsTypeInfo = brds;
      } else if (type == "자유") {
        state.boardsTypeFree = brds;
      }
    },

    GET_FREE_BOARDS: function (state, brd) {
      state.boardsTypeFree = brd;
    },

    GET_FREE_BOARD: function (state, brds) {
      state.boardsTypeFree = brds;
    },

    GET_INFO_BOARDS: function (state, brds) {
      state.boardsTypeInfo = brds;
    },
    GET_INFO_BOARD: function (state, brds) {
      state.boardsTypeInfo = brds;
    },

    //4. 상품관련
    //상품 추가는 거래처를 엄선하여 관리자가 진행함
    //---전체 상품 정보 최신순으로 불러오기(최신순) -- 메인
    SET_PRODUCT_LIST: function (state, prds) {
      state.products = prds;
    },

    //---타입에 따른 상품 셋팅하기 (타입 : 식료품, 생활용품, 가전) -- 상품페이지
    //셋팅 후 ...mapGetters([products])로 쓸 것
    SET_PRODUCT_BY_TYPE: function (state, prds) {
      console.log(prds);
      if (prds[0].type == "식료품") {
        state.productsByFood = prds;
      } else if (prds[0].type == "생활용품") {
        state.productsByLiving = prds;
      } else if (prds[0].type == "가전제품") {
        state.productsByElec = prds;
      }
    },
  },
  actions: {
    //로그아웃은 api 날릴 필요 없음
    logout: function ({ commit }) {
      commit("LOGOUT");
    },

    //회원가입
    createUser: function ({ state, commit }, user) {
      console.log(user);
      const API_URL = `http://localhost:9999/api-user/registUser`;
      axios({
        url: API_URL,
        method: "POST",
        data: user,
      })
        .then(() => {
          commit("CREATE_USER", user);
          state.open_modal = true;
          //   alert("등록되었습니다.");
        })
        .catch((err) => {
          state.open_modal = false;
          console.log(err);
        });
    },

    //랭킹을 매기기 위해 전체 유저를 불러옴 (포인트 높은 순)
    setUsers: function ({ commit }) {
      const API_URL = `http://localhost:9999/api-user/rank`;
      return axios({
        url: API_URL,
        method: "GET",
      })
        .then((res) => {
          // console.log("user setting...");
          commit("SET_USERS", res.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },

    //회원 정보 수정
    //req는 user
    updateUser: function ({ commit }, req) {
      console.log(commit);
      const API_URL = `http://localhost:9999/api-user/userUpdate`;
      axios({
        url: API_URL,
        method: "PUT",
        data: req,
      })
        .then(() => {
          commit("UPDATE_USER", req);
          alert("수정이 완료되었습니다.");
        })
        .catch((err) => {
          console.log(err);
        });
    },

    //특정 유저 정보 반환
    setUser: function ({ commit }, id) {
      const API_URL = `http://localhost:9999/api-user/userInfo/${id}`;
      axios({
        url: API_URL,
        method: "GET",
      })
        .then((res) => {
          commit("SET_USER", res.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },

    //유저 로그인(토큰 형식은 아님 근데, 백엔 해놔서 할 수는 있음)
    setLoginUser: function ({ commit }, user) {
      const API_URL = `http://localhost:9999/api-user/loginUser`;
      axios({
        url: API_URL,
        method: "POST",
        data: user,
      })
        .then(() => {
          commit("SET_LOGIN_USER", user);
          alert("로그인 성공!");
          // this.$router.push("/main");
          // if(res!=null){
          // }else{
          //     alert("로그인 실패");
          // }
        })
        .catch((err) => {
          alert("로그인 실패");
          console.log(err);
        });
    },

    //특정 회원의 데일리 당 기록
    setDailySugar: function ({ commit }, blood) {
      const API_URL = `http://localhost:9999/api-user/sugar_blood_chart`;
      axios({
        url: API_URL,
        method: "POST",
        data: blood,
      })
        .then(() => {
          commit("SET_DAILY_SUGAR", blood);
          alert("하루 당 기록 완료");
        })
        .catch((err) => {
          console.log(err);
        });
    },

    //차트 정보를 위한 특정 회원의 당 기록 가져오기
    getDailySugar: function ({ commit }, userId) {
      const API_URL = `http://localhost:9999/api-user/sugar_blood_chart/${userId}`;
      axios({
        url: API_URL,
        method: "GET",
      })
        .then((response) => {
          commit("GET_DAILY_SUGAR", response.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },

    //나의 챌린지 가져오기
    getMyChallenge: function ({ commit }, userId) {
      const API_URL = `http://localhost:9999/api-user/myChallenge/${userId}`;
      axios({
        url: API_URL,
        method: "GET",
      })
        .then((response) => {
          console.log(response.data);
          commit("GET_MY_CHALLENGE", response.data);
          // alert("유저 챌린지 리스트 가져오기 성공!");
        })
        .catch((err) => {
          console.log(err);
        });
    },

    //유저의 데일리 운동 기록하기
    //record_time 는 One_day_exercise 객체
    setRecordExercise: function ({ commit }, record_time) {
      const API_URL = `http://localhost:9999/api-user/daily`;
      axios({
        url: API_URL,
        method: "POST",
        data: record_time,
      })
        .then(() => {
          commit("SET_RECORD_EXERCISE", record_time);
          alert("유저 하루 운동 기록 성공!");
        })
        .catch((err) => {
          console.log(err);
        });
    },

    //유저의 데일리 운동 기록 불러오기 (스트릭)
    getRecordExercise: function ({ commit }, userId) {
      const API_URL = `http://localhost:9999/api-user/daily/${userId}`;
      axios({
        url: API_URL,
        method: "GET",
      })
        .then((response) => {
          commit("GET_RECORD_EXERCISE", response.data);
          // alert("유저 하루 운동 기록 가져왔다!");
        })
        .catch((err) => {
          console.log(err);
        });
    },

    //유저의 데일리 운동 기록 불러오기 최신순 (스트릭)
    getRecordExerciseNew: function ({ commit }, userId) {
      const API_URL = `http://localhost:9999/api-user/dailyNew/${userId}`;
      axios({
        url: API_URL,
        method: "GET",
      })
        .then((response) => {
          commit("GET_RECORD_EXERCISE_NEW", response.data);
          // alert("유저 하루 운동 기록 가져왔다!");
        })
        .catch((err) => {
          console.log(err);
        });
    },

    //랭킹 등급 변경 요청
    changeRank: function ({ commit }, user) {
      const API_URL = `http://localhost:9999/api-user/rank`;
      axios({
        url: API_URL,
        method: "PUT",
        data: user,
      })
        .then((response) => {
          commit("CHANGE_RANK", response.data);
          alert("랭킹 변동");
        })
        .catch((err) => {
          console.log(err);
        });
    },

    //2.  챌린지 관련

    //전체 챌린지 가져오기
    getChallenges: function ({ commit }) {
      const API_URL = `http://localhost:9999/api-challenge/challengeList`;
      axios({
        url: API_URL,
        method: "GET",
      })
        .then((response) => {
          commit("GET_CHALLENGES", response.data);
          // alert("챌린지 가져오기 완료!");
        })
        .catch((err) => {
          console.log(err);
        });
    },

    //특정 챌린지 가져오기
    getchallenge: function ({ commit }, no) {
      const API_URL = `http://localhost:9999/api-challenge/getChallenge/${no}`;
      axios({
        url: API_URL,
        method: "GET",
      })
        .then((response) => {
          commit("GET_CHALLENGE", response.data);
          // alert("챌린지 가져오기 완료!");
        })
        .catch((err) => {
          console.log(err);
        });
    },

    //챌린지 만들기
    makeChallenge: function ({ commit }, challenge) {
      const API_URL = `http://localhost:9999/api-challenge/makeChallenge`;
      axios({
        url: API_URL,
        method: "POST",
        data: challenge,
      })
        .then((res) => {
          commit("MAKE_CHALLENGE", challenge, res.data);
          alert("챌린지 개설 완료!");
        })
        .catch((err) => {
          console.log(err);
        });
    },

    //특정 챌린지 팀 맴버 정보 가져오기
    getTeams: function ({ commit }, challenge_no) {
      const API_URL = `http://localhost:9999/api-challenge/teammember/${challenge_no}`;
      axios({
        url: API_URL,
        method: "GET",
      })
        .then((res) => {
          commit("GET_TEAMS", res.data);
          //alert("챌린지 개설 완료!");
        })
        .catch((err) => {
          console.log(err);
        });
    },

    //내 챌린지 가져오기
    getMyChallenges: function ({ commit }, users_id) {
      console.log(users_id);
      const API_URL = `http://localhost:9999/api-challenge/member/${users_id}`;
      axios({
        url: API_URL,
        method: "GET",
      })
        .then((res) => {
          console.log(res.data);
          commit("GET_MY_CHALLENGES", res.data);
          // alert("챌린지 개설 완료!");
        })
        .catch((err) => {
          console.log(err);
        });
    },

    //챌린지 + 팀 가입하기
    signTeam: function ({ commit }, user_have_challenge) {
      // console.log("gg"+user_have_challenge.users_id+" "+user_have_challenge.challenge_no+" "+user_have_challenge.team_no);
      const API_URL = `http://localhost:9999/api-challenge/registTeam`;
      axios({
        url: API_URL,
        method: "POST",
        data: user_have_challenge,
      })
        .then(() => {
          commit("SIGN_TEAM");
          alert("팀 가입 완료!");
        })
        .catch((err) => {
          alert("팀 가입 불가!");
          console.log(err);
        });
    },

    //챌린지 + 팀 탈퇴하기
    exitTeam: function ({ commit }, user_have_challenge) {
      // console.log(users_id+" "+challenge_no+" "+team_no);
      const API_URL = `http://localhost:9999/api-challenge/deleteTeam`;
      axios({
        url: API_URL,
        method: "DELETE",
        data: user_have_challenge,
      })
        .then(() => {
          commit("EXIT_TEAM");
          alert("팀 탈퇴 완료!");
        })
        .catch((err) => {
          alert("탈퇴할 수 없는 상태입니다!");
          console.log(err);
        });
    },

    //챌린지 상태 변경 (대기/시작/종료)
    //no, status
    changeChallengeStatus: function ({ commit }, challenge) {
      const API_URL = `http://localhost:9999/api-challenge/changeStatus`;
      axios({
        url: API_URL,
        method: "POST",
        data: challenge,
      })
        .then(() => {
          commit("CHANGE_CHALLENGE_STATUS", challenge);
          alert("챌린지 상태 변경 완료!");
        })
        .catch((err) => {
          console.log(err);
        });
    },

    //챌린지 수정하기
    //여기 일단.... 이미지 파일 어케 보내는 지 몰라서 안보냄...
    updateChallenge: function ({ commit }, challenge) {
      const API_URL = `http://localhost:9999/api-challenge/challengeUpdate`;
      axios({
        url: API_URL,
        method: "PUT",
        data: challenge,
      })
        .then(() => {
          commit("UPDATE_CHALLENGE", challenge);
          alert("챌린지 수정 완료!");
        })
        .catch((err) => {
          console.log(err);
        });
    },

    //챌린지 삭제하기
    deleteChallenge: function ({ commit }, no) {
      const API_URL = `http://localhost:9999/api-challenge/challengeDelete/${no}`;
      axios({
        url: API_URL,
        method: "DELETE",
      })
        .then(() => {
          commit("DELETE_CHALLENGE", no);
          alert("챌린지 삭제 완료!");
        })
        .catch((err) => {
          console.log(err);
        });
    },

    //승리 여부 판단하기
    getWinner: function ({ commit }, challenge_no) {
      const API_URL = `http://localhost:9999/api-challenge/winner/${challenge_no}`;
      axios({
        url: API_URL,
        method: "GET",
      })
        .then((response) => {
          console.log(response);
          //response는 이긴자와 받는금액이 옴 " "을 사이에 두고!
          commit("GET_WINNER", response.data);
          alert("승리자 판결 완료!");
        })
        .catch((err) => {
          console.log(err);
        });
    },

    //3. 커뮤니티 관련
    //--게시판 전체 글 가져오기
    getBoards: function ({ commit }) {
      const API_URL = `http://localhost:9999/api-community/communityAll`;
      axios({
        url: API_URL,
        method: "GET",
      })
        .then((response) => {
          console.log(response);
          commit("GET_ALL_BOARD", response.data);
          // alert("전체글 가져오기 완료");
        })
        .catch((err) => {
          console.log(err);
        });
    },

    //---정보 게시판 글 가져오기 (최신 중 인기 - 메인)
    getInfoBoard: function ({ commit }) {
      const API_URL = `http://localhost:9999/api-community/communityInfoPop`;
      axios({
        url: API_URL,
        method: "GET",
      })
        .then((response) => {
          commit("GET_INFO_BOARD", response.data);
          alert("인기정보글 가져오기 완료");
        })
        .catch((err) => {
          console.log(err);
        });
    },

    //---자유 게시판 글 가져오기 (최신 중 인기 - 메인)
    getFreeBoard: function ({ commit }) {
      const API_URL = `http://localhost:9999/api-community/communityfreePop`;
      axios({
        url: API_URL,
        method: "GET",
      })
        .then((response) => {
          commit("GET_FREE_BOARD", response.data);
          alert("인기정보글 가져오기 완료");
        })
        .catch((err) => {
          console.log(err);
        });
    },

    //---정보 게시판 글 가져오기 (최신)
    getInfoBoards: function ({ commit }) {
      const API_URL = `http://localhost:9999/api-community/communityInfo`;
      axios({
        url: API_URL,
        method: "GET",
      })
        .then((response) => {
          commit("GET_INFO_BOARDS", response.data);
          alert("정보글 가져오기 완료");
        })
        .catch((err) => {
          console.log(err);
        });
    },

    //---자유 게시판 글 가져오기 (최신)
    getFreeBoards: function ({ commit }) {
      const API_URL = `http://localhost:9999/api-community/communityfree`;
      axios({
        url: API_URL,
        method: "GET",
      })
        .then((response) => {
          commit("GET_FREE_BOARDS", response.data);
          alert("정보글 가져오기 완료");
        })
        .catch((err) => {
          console.log(err);
        });
    },

    //---게시판 검색 결과 가져오기(정보/자유 따로 요청)
    //이렇게 2개 파라미터로 넣어도 되는지 확인
    searchBoards: function ({ commit }, word, type) {
      const API_URL = `http://localhost:9999/api-community/searchReview/${word}/${type}`;
      axios({
        url: API_URL,
        method: "GET",
      })
        .then((response) => {
          commit("SEARCH_BOARDS", response, type);
          alert("검색 완료!");
        })
        .catch((err) => {
          console.log(err);
        });
    },

    //---게시판 등록
    createBoard: function ({ commit }, board) {
      const API_URL = `http://localhost:9999/api-community/board`;
      axios({
        url: API_URL,
        method: "POST",
        data: board,
      })
        .then(() => {
          commit("CREATE_BOARD", board);
          alert("게시판 생성 완료");
        })
        .catch((err) => {
          console.log(err);
        });
    },

    //---게시판 삭제
    deleteBoard: function ({ commit }, no) {
      const API_URL = `http://localhost:9999/api-community/board/${no}`;
      axios({
        url: API_URL,
        method: "DELETE",
      })
        .then(() => {
          commit("DELETE_BOARD", no);
          alert("게시판 삭제 완료");
        })
        .catch((err) => {
          console.log(err);
        });
    },

    //---게시판 수정
    updateBoard: function ({ commit }, board) {
      const API_URL = `http://localhost:9999/api-community/board`;
      axios({
        url: API_URL,
        method: "PUT",
        data: board,
      })
        .then(() => {
          commit("UPDATE_BOARD", board);
          alert("게시판 수정 완료");
        })
        .catch((err) => {
          console.log(err);
        });
    },

    //---게시판 상세보기
    detailBoard: function ({ commit }, no) {
      const API_URL = `http://localhost:9999/api-community/board/${no}`;
      axios({
        url: API_URL,
        method: "GET",
      })
        .then((response) => {
          commit("DETAIL_BOARD", response.data);
          // alert("게시판 상세보기 완료");
        })
        .catch((err) => {
          console.log(err);
        });
    },

    //---특정 게시물 댓글 등록
    createComments: function ({ commit }, comment) {
      const API_URL = `http://localhost:9999/api-community/comment`;
      axios({
        url: API_URL,
        method: "POST",
        data: comment,
      })
        .then(() => {
          commit("CREATE_COMMENT", comment);
          alert("댓글 생성 완료!");
        })
        .catch((err) => {
          console.log(err);
        });
    },

    //---특정 게시물 댓글 수정
    updateComments: function ({ commit }, comment) {
      const API_URL = `http://localhost:9999/api-community/comment`;
      axios({
        url: API_URL,
        method: "PUT",
        data: comment,
      })
        .then(() => {
          commit("UPDATE_COMMENT", comment);
          alert("댓글 수정 완료!");
        })
        .catch((err) => {
          console.log(err);
        });
    },

    //---특정 게시물 댓글 삭제
    removeComments: function ({ commit }, comment_no) {
      const API_URL = `http://localhost:9999/api-community/comment/${comment_no}`;
      axios({
        url: API_URL,
        method: "DELETE",
      })
        .then(() => {
          commit("REMOVE_COMMENT", comment_no);
          alert("댓글 삭제 완료!");
        })
        .catch((err) => {
          console.log(err);
        });
    },

    //---특정 게시물 댓글들 불러오기
    setComments: function ({ commit }, board_no) {
      const API_URL = `http://localhost:9999/api-community/comment/${board_no}`;
      axios({
        url: API_URL,
        method: "GET",
      })
        .then((response) => {
          //response는 전체 상품 리스트 최신순
          let commentList = response.data;
          if (commentList != null && commentList.length > 0) {
            commit("SET_COMMENT_LIST", response.data);
            // alert("댓글 가져오기 완료!");
          } else {
            // alert("댓글이 하나도 없습니다.");
          }
        })
        .catch((err) => {
          console.log(err);
        });
    },

    //4. 상품관련(상품 추가는 거래처를 엄선하여 관리자가 진행함)
    //---시간남아서 상세페이지 들어가는 경로 필요하면 백엔드 구현할 것임

    //---전체 상품 정보 최신순으로 불러오기(최신순) -- 메인
    setProducts: function ({ commit }) {
      const API_URL = `http://localhost:9999/api-product/productList`;
      axios({
        url: API_URL,
        method: "GET",
      })
        .then((response) => {
          //response는 전체 상품 리스트 최신순
          let productList = response.data;
          if (productList != null && productList.length > 0) {
            commit("SET_PRODUCT_LIST", response.data);
            // alert("상품 가져오기 완료!");
          } else {
            // alert("준비된 타입의 상품이 없습니다.");
          }
        })
        .catch((err) => {
          console.log(err);
        });
    },

    //---타입에 따른 상품 셋팅하기 (타입 : 식료품, 생활용품, 가전) -- 상품페이지
    //셋팅 후 ...mapGetters([products])로 쓸 것
    setProductByType: function ({ commit }, type) {
      const API_URL = `http://localhost:9999/api-product/productByType/${type}`;
      axios({
        url: API_URL,
        method: "GET",
      })
        .then((response) => {
          //response는 특정 타입의 상품 리스트
          let productListByType = response.data;
          if (productListByType != null && productListByType.length > 0) {
            commit("SET_PRODUCT_BY_TYPE", response.data);
            
          } else {
            // alert("준비된 타입의 상품이 없습니다.");
          }
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
  modules: {},
});
