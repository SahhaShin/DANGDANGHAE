import Vue from "vue";
import VueRouter from "vue-router";
// 부모는 앞의 경로를 ..로 작성
import Home from "../views/Home.vue";
import MainPage from "../views/MainPage.vue";
import Challenge from "../views/Challenge.vue";
import CommunityBoard from "../views/CommunityBoard.vue";
import MyPage from "../views/MyPage.vue";
import Ranking from "../views/Ranking.vue";
import Store from "../views/Store.vue";
import User from "../views/User.vue";
// 자식은 앞의 경로를 @로 작성
import ChallengeBoardMain from "@/views/challengeChildren/ChallengeBoardMain.vue";
import ChallengeBoardDetail from "@/views/challengeChildren/ChallengeBoardDetail.vue";
import ChallengeBoardModify from "@/views/challengeChildren/ChallengeBoardModify.vue";
import ChallengeBoardWrite from "@/views/challengeChildren/ChallengeBoardWrite.vue";
import CommunityBoardMain from "@/views/communityBoardChildren/CommunityBoardMain.vue";
import CommunityBoardDetail from "@/views/communityBoardChildren/CommunityBoardDetail.vue";
import CommunityBoardWrite from "@/views/communityBoardChildren/CommunityBoardWrite.vue";
import CommunityBoardModify from "@/views/communityBoardChildren/CommunityBoardModify.vue";
import MyPageChallenge from "@/views/myPageChildren/MyPageChallenge.vue";
import MyPageStore from "@/views/myPageChildren/MyPageStore.vue";
import MyPageMain from "@/views/myPageChildren/MyPageMain.vue";
// import { component } from "vue/types/umd";

// import LoginForm from "@/components/LoginForm.vue";
// import User from "../views/User.vue";
// import UserList from "@/components/user/UserList.vue";
// import UserRegist from "@/components/user/UserRegist.vue";
// import UserDetail from "@/components/user/UserDetail.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "Home",
    component: Home,
  },
  {
    path: "/main",
    name: "MainPage",
    component: MainPage,
  },
  {
    path: "/user",
    name: "User",
    component: User,
  },
  {
    path: "/mypage",
    name: "MyPage",
    component: MyPage,
    children: [
      {
        path: "",
        name: "MyPageMain",
        component: MyPageMain,
      },
      {
        path: "challenge",
        name: "MyPageChallenge",
        component: MyPageChallenge,
      },
      {
        path: "store",
        name: "MyPageStore",
        component: MyPageStore,
      },
    ],
  },
  {
    path: "/challenge",
    name: "Challenge",
    component: Challenge,
    children: [
      {
        path: "",
        name: "ChallengeBoardMain",
        component: ChallengeBoardMain,
      },
      {
        path: "write",
        name: "ChallengeBoardWrite",
        component: ChallengeBoardWrite,
      },
      {
        path: "detail/:no",
        name: "ChallengeBoardDetail",
        component: ChallengeBoardDetail,
      },
      {
        path: "modify/:no",
        name: "ChallengeBoardModify",
        component: ChallengeBoardModify,
      },
    ],
  },
  {
    path: "/community",
    name: "CommunityBoard",
    component: CommunityBoard,
    children: [
      {
        path: "",
        name: "CommunityBoardMain",
        component: CommunityBoardMain,
      },
      {
        path: "write",
        name: "CommunityBoardWrite",
        component: CommunityBoardWrite,
      },
      {
        path: "detail/:no",
        name: "CommunityBoardDetail",
        component: CommunityBoardDetail,
      },
      {
        path: "modify/:no",
        name: "CommunityBoardModify",
        component: CommunityBoardModify,
      },
    ],
  },
  {
    path: "/ranking",
    name: "Ranking",
    component: Ranking,
  },
  {
    path: "/store",
    name: "Store",
    component: Store,
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
  scrollBehavior() {
    return { x: 0, y: 0 };
  },
});

export default router;
