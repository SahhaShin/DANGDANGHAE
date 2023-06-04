<template>
  <div>
    <header>
      <!-- 메인 헤더 시작 -->
      <!-- https://codepen.io/cgruber/pen/RaYoMV -->

      <body class="gd-sldm">
        <div class="sldm">
          <div class="sldm-widget-container">
            <!-- 검색 위젯... 쓸일이 있을까? -->
            <!-- <div class="sldm-widget sldm-search-widget sldm-hidden" id="sldm-search-widget">
                <form action="#" method="get">
                    <input class="sldm-input" type="text" name="keyword" value="" placeholder="enter keyword...">
                    <button class="sldm-button" type="submit">Search <i class="fa fa-angle-right"></i></button>
                </form>
            </div> -->
            <div
              class="sldm-widget sldm-phone-widget sldm-hidden"
              id="sldm-phone-widget"
            >
              <div class="sldm-phone-widget-content">
                Contact us @ 010.0426.0107
              </div>
            </div>
            <div class="sldm-clear"></div>
          </div>
          <div class="sldm-bar">
            <div class="sldm-clear">
              <!-- 상단바 로고 시작 -->
              <router-link
                :to="{ name: 'MainPage' }"
                class="sldm-brand"
                title="Slide Menu"
                ><img
                  src="@/assets/DDHLogo.png"
                  style="width: 35px; margin-top: 10px"
              /></router-link>
              <router-link
                :to="{ name: 'MainPage' }"
                class="sldm-brand"
                title="Slide Menu"
              >
                <img
                  src="@/assets/DDHLogoHangeul.png"
                  style="width: 90px; margin-top: -14px; margin-left: -3px"
                />
              </router-link>
              <!-- 상단바 로고 끝 -->
              <!-- 상단바 오른쪽 아이콘 시작 -->
              <div class="sldm-icons">
                <!-- 필수 아이콘 변경 -->
                <!-- 전화아이콘 -->
                <a
                  href="#"
                  class="sldm-widget-toggle"
                  data-target="#sldm-phone-widget"
                  ><img
                    src="@/assets/PhoneIcon.png"
                    style="width: 39px; margin-top: 10px"
                /></a>

                <!-- 선택 -->
                <!-- 검색 쓰게 될 때 활성화 아이콘 -->
                <!-- <a href="#" class="sldm-widget-toggle" data-target="#sldm-search-widget"><i class="fa fa-search"></i></a>

                    <div class="sldm-clear"></div> -->
                <!-- 필수 아이콘 -->

                <!-- 1. 유저아이콘 -->
                <!--로그인 완료 후 표시할 이미지(이거 누르면 마이페이지로 감)-->
                <router-link
                  v-if="loginUser"
                  to="/mypage"
                  class="sldm-widget-toggle"
                  data-target="#sldm-search-widget"
                  ><img
                    src="@/assets/UserLoginIcon.png"
                    style="width: 39px; margin-top: 10px"
                /></router-link>

                <!--산하 : 필수 : 로그아웃 아이콘 바꿔야함!-->
                <img
                  v-if="loginUser"
                  class="sldm-widget-toggle"
                  src="@/assets/UserLogoutIcon.png"
                  style="width: 39px; margin-top: 10px; margin-right: 10px"
                  @click="logout"
                />
                <!-- <button v-if="loginUser" style="margin: 5px" @click="logout">로그아웃</button> -->

                <!--로그아웃-->

                <!-- serach-widget을 손을 봐야하는걸까... 모르겠엉 -->
                <!--로그인 안했을 떄 나오는 아이콘-->
                <router-link
                  v-if="loginUser == null"
                  :to="{ name: 'User' }"
                  class="sldm-widget-toggle"
                  data-target="#sldm-search-widget"
                  ><img
                    src="@/assets/UserLoginIcon.png"
                    style="width: 39px; margin-top: 10px"
                /></router-link>
                <div class="sldm-clear"></div>
              </div>
              <!-- 상단바 오른쪽 아이콘 끝 -->
            </div>
            <div class="sldm-overlay"></div>
          </div>
          <!-- 왼쪽 내브 바 시작 -->
          <nav>
            <div class="sldm-nav-container">
              <div class="sldm-header">
                <div class="sldm-header-image-wrapper">
                  <!-- Add Header Image Here -->
                  <img
                    src="@/assets/NavbarBanner.png"
                    alt=""
                    class="sldm-header-background-image"
                  />
                </div>
              </div>
              <div class="sldm-nav">
                <ul class="sldm-default">
                  <li>
                    <router-link :to="{ name: 'MainPage' }">Home</router-link>
                  </li>
                  <li>
                    <router-link :to="{ name: 'Home' }">About</router-link>
                  </li>
                  <li class="sldm-submenu">
                    <a href="#" class="">Challenge</a>
                    <ul class="sldm-default" style="display: none">
                      <li>
                        <router-link to="/challenge">챌린지 게시판</router-link>
                      </li>
                      <li>
                        <router-link :to="{ name: 'MyPageChallenge' }"
                          >나의 챌린지</router-link
                        >
                      </li>
                    </ul>
                  </li>
                  <li class="sldm-submenu">
                    <a href="#" class="">Community</a>
                    <ul class="sldm-default" style="display: none">
                      <li>
                        <router-link to="/community"
                          >자유게시판</router-link
                        >
                      </li>
                      <li>
                        <router-link to="/community"
                          >정보게시판</router-link
                        >
                      </li>
                    </ul>
                  </li>
                  <li class="sldm-submenu">
                    <a href="#" class="">Ranking</a>
                    <ul class="sldm-default" style="display: none">
                      <li>
                        <router-link to="/ranking">랭킹게시판</router-link>
                      </li>
                      <li>
                        <router-link :to="{ name: 'MyPageMain' }"
                          >나의 랭킹</router-link
                        >
                      </li>
                    </ul>
                  </li>
                  <li class="sldm-submenu">
                    <a href="#" class="">Store</a>
                    <ul class="sldm-default" style="display: none">
                      <li>
                        <router-link to="/store">구매하당</router-link>
                      </li>
                      <li>
                        <router-link :to="{ name: 'MyPageMain' }"
                          >나의 포인트</router-link
                        >
                      </li>
                    </ul>
                  </li>
                </ul>
                <!-- 선택 -->
                <!-- 왼쪽 바 아래 칸 시작 (시간이 되면 폼 만들어서 링크 연결되게 하면 그럴싸할 거 같지만 지금은 어려우니까~!!! 일단 놔두기만 합니당)-->
                <!-- <ul class="sldm-social">
                  <li>
                    <a href="#" class="sldm-facebook"
                      ><i class="fa fa-facebook"></i
                    ></a>
                  </li>
                  <li>
                    <a href="#" class="sldm-google-plus"
                      ><i class="fa fa-google-plus"></i
                    ></a>
                  </li>
                  <li>
                    <a href="#" class="sldm-twitter"
                      ><i class="fa fa-twitter"></i
                    ></a>
                  </li>
                  <li>
                    <a href="#" class="sldm-pinterest"
                      ><i class="fa fa-pinterest"></i
                    ></a>
                  </li>
                  <li>
                    <a href="#" class="sldm-youtube"
                      ><i class="fa fa-youtube-play"></i
                    ></a>
                  </li>
                </ul> -->
                <!-- 왼쪽 바 아래 칸 끝 -->
              </div>
            </div>
            <!-- 필수 -->
            <!-- 왼쪽 바 움직이는 아이콘입니다. 메뉴 아이콘으로 나중에 바꿔줄 예정입니다. 시작-->
            <a class="sldm-toggle">
              <img
                src="@/assets/MenuBarIcon.png"
                class="fa fa-times sldm-close"
                style="width: 35px; margin-left: 12px; margin-top: 10px"
              />
              <i class="fa fa-bars sldm-open"></i>
            </a>
            <!-- 왼쪽 바 움직이는 아이콘입니다. 메뉴 아이콘으로 나중에 바꿔줄 예정입니다. 끝-->
          </nav>
          <!-- 왼쪽 내브바 끝 -->
        </div>
        <div class="border"></div>
      </body>

      <!-- 메인 해더 끝 -->
    </header>
  </div>
</template>

<script>
import $ from "jquery";
import { mapGetters } from "vuex";
export default {
  name: "MainHeader",
  mounted() {
    $(document).ready(function () {
      $(".sldm-toggle, .sldm-overlay").on("click", function (e) {
        e.preventDefault();
        $(".sldm").toggleClass("sldm-active");
      });

      $(".sldm-submenu > a").on("click", function (e) {
        e.preventDefault();
        $(this).toggleClass("sldm-open");
        $(this).parent().find(">ul").slideToggle(450);
      });

      $(".sldm-widget-toggle").on("click", function (e) {
        e.preventDefault();
        $($(this).data("target")).slideToggle(300);
      });
    });
  },
  computed: {
    ...mapGetters(["loginUser"]), //로그인 유저가 변경될 때 마다 여기에 저장
  },
  methods: {
    logout() {
      this.$store.dispatch("logout");
    },
  },
};
</script>

<style>
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

/* 메인 헤더 시작 */
body {
  margin: 0;
  padding: 0;
}
.gd-sldm * {
  -webkit-box-sizing: border-box;
  -moz-box-sizing: border-box;
  -o-box-sizing: border-box;
  -ms-box-sizing: border-box;
  box-sizing: border-box;
}

.gd-sldm .sldm {
  font-family: "Open Sans", sans-serif;
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  z-index: 150;
}

.gd-sldm .sldm a {
  text-decoration: none;
}

.gd-sldm .sldm a:hover {
  text-decoration: none;
  background-color: #ffffff;
}

.gd-sldm .sldm .sldm-overlay {
  display: none;
}

.gd-sldm .sldm .sldm-bar {
  position: relative;
  z-index: 10;
  min-height: 60px;
  padding: 0 0 0 48px;
  background: #fff;
  -webkit-box-shadow: 0 -1px 5px rgba(0, 0, 0, 0.1);
  -moz-box-shadow: 0 -1px 5px rgba(0, 0, 0, 0.1);
  -o-box-shadow: 0 -1px 5px rgba(0, 0, 0, 0.1);
  -ms-box-shadow: 0 -1px 5px rgba(0, 0, 0, 0.1);
  box-shadow: 0 -1px 5px rgba(0, 0, 0, 0.1);
}

.gd-sldm .sldm .sldm-bar .sldm-brand {
  color: #949494;
  height: 60px;
  line-height: 60px;
  display: block;
  float: left;
  font-weight: 500;
  font-size: 20px;
  text-decoration: none;
  display: inline-block;
  padding-left: 20px;
  -webkit-text-shadow: none;
  -moz-text-shadow: none;
  -o-text-shadow: none;
  -ms-text-shadow: none;
  text-shadow: none;
  background-color: #ffffff;

  -webkit-backface-visibility: hidden;
}

.gd-sldm .sldm .sldm-bar .sldm-brand:hover {
  background: rgba(255, 255, 255, 0.84);
}

.gd-sldm .sldm .sldm-bar .sldm-icons {
  float: right;
}

.gd-sldm .sldm .sldm-bar .sldm-icons a {
  width: 60px;
  height: 60px;
  display: block;
  float: left;
  color: #969696;
  font-size: 24px;
  line-height: px;
  text-align: center;
  -webkit-text-shadow: none;
  -moz-text-shadow: none;
  -o-text-shadow: none;
  -ms-text-shadow: none;
  text-shadow: none;

  -webkit-backface-visibility: hidden;

  -webkit-transform: scale(0.9, 0.9);
  -moz-transform: scale(0.9, 0.9);
  -o-transform: scale(0.9, 0.9);
  transform: scale(0.9, 0.9);

  -webkit-transition: 0.35s all;
  -moz-transition: 0.35s all;
  -o-transition: 0.35s all;
  -ms-transition: 0.35s all;
  transition: 0.35s all;
}

.gd-sldm .sldm .sldm-bar .sldm-icons a:hover {
  background: rgba(0, 0, 0, 0.1);

  -webkit-transform: scale(1, 1);
  -moz-transform: scale(1, 1);
  -o-transform: scale(1, 1);
  transform: scale(1, 1);
}
/* 왼쪽 바 위치 조정 */
.gd-sldm .sldm nav {
  z-index: 30;
  position: fixed;
  top: 0;
  width: 270px;
  height: 100%;
  background: #fff;

  -webkit-transform: translateX(-270px);
  -moz-transform: translateX(-270px);
  -o-transform: translateX(-270px);
  transform: translateX(-270px);

  -webkit-transition: 0.35s all;
  -moz-transition: 0.35s all;
  -o-transition: 0.35s all;
  -ms-transition: 0.35s all;
  transition: 0.35s all;
}
/* 기본컬러 */
.gd-sldm .sldm nav .sldm-nav-container {
  overflow-x: hidden;
  overflow-y: auto;
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: #ffffff;
}

.gd-sldm .sldm nav .sldm-nav-container .sldm-header {
  height: 180px;
  position: relative;
}

.gd-sldm .sldm nav .sldm-nav-container .sldm-header a {
  display: block;
  position: absolute;
  left: 40%;
  top: 90%;
  margin-top: -45px;
  width: 95px;
  height: 95px;
  overflow: hidden;
  -webkit-border-radius: 100%;
  -moz-border-radius: 100%;
  -o-border-radius: 100%;
  -ms-border-radius: 100%;
  border-radius: 100%;
  -khtml-border-radius: 100%;
  -moz-background-clip: padding;
  -webkit-background-clip: padding-box;
  background-clip: padding-box;
  z-index: 999;

  -webkit-backface-visibility: hidden;

  -webkit-transform: scale(0.9, 0.9);
  -moz-transform: scale(0.9, 0.9);
  -o-transform: scale(0.9, 0.9);
  transform: scale(0.9, 0.9);

  -webkit-transition: 0.35s all;
  -moz-transition: 0.35s all;
  -o-transition: 0.35s all;
  -ms-transition: 0.35s all;
  transition: 0.35s all;
}
.gd-sldm .sldm nav .sldm-nav-container .sldm-header a:hover {
  -webkit-transform: scale(1, 1);
  -moz-transform: scale(1, 1);
  -o-transform: scale(1, 1);
  transform: scale(1, 1);
}
.gd-sldm .sldm nav .sldm-nav-container .sldm-header a img {
  max-width: 100%;
  display: block;
  border: 6px solid #eeeeee;
  -webkit-border-radius: 100%;
  -moz-border-radius: 100%;
  -o-border-radius: 100%;
  -ms-border-radius: 100%;
  border-radius: 100%;
  -khtml-border-radius: 100%;
  -moz-background-clip: padding;
  -webkit-background-clip: padding-box;
  background-clip: padding-box;
}

/* 내브바 배너 */
.gd-sldm .sldm nav .sldm-nav-container .sldm-header .sldm-header-image-wrapper {
  height: 180px;
  overflow: hidden;
  position: relative;
}
.gd-sldm
  .sldm
  nav
  .sldm-nav-container
  .sldm-header
  .sldm-header-background-image {
  position: absolute;
  top: 0;
  left: 0;
  max-width: 100%;
  right: 0;
  width: 100%;
  height: 100%;
}

.gd-sldm .sldm nav .sldm-nav-container .sldm-nav {
  padding: 0 0 75px;
}

.gd-sldm .sldm nav .sldm-nav-container .sldm-nav ul {
  margin: 0;
  padding: 0;
  list-style: none;
  position: relative;
}

.gd-sldm .sldm nav .sldm-nav-container .sldm-nav ul.sldm-default li {
  line-height: 1.3;
  font-size: 18px;
  font-weight: 300;
}
/* 내브바 폰트 */
.gd-sldm .sldm nav .sldm-nav-container .sldm-nav ul.sldm-default li a {
  color: #b09587;
  display: block;
  font-weight: 400;
  padding: 12px 30px 12px 20px;
  text-decoration: none;
  position: relative;

  -webkit-transition: 0.35s all;
  -moz-transition: 0.35s all;
  -o-transition: 0.35s all;
  -ms-transition: 0.35s all;
  transition: 0.35s all;
  border-radius: 0px 0px 10px 10px;
}
.gd-sldm .sldm nav .sldm-nav-container .sldm-nav ul.sldm-default li a:hover,
.gd-sldm .sldm nav .sldm-nav-container .sldm-nav ul.sldm-default li a:focus {
  background-color: rgba(219, 143, 99, 0.15);
  border-radius: 0px 0px 10px 10px;
}
/* 내브바 얇은 줄 */
.gd-sldm .sldm nav .sldm-nav-container .sldm-nav ul.sldm-default li + li {
  border-top: 1px solid #efebe9;
  border-radius: 0px 0px 10px 10px;
}

.gd-sldm
  .sldm
  nav
  .sldm-nav-container
  .sldm-nav
  ul.sldm-default
  li.sldm-submenu
  > a:after {
  display: block;
  content: "\f107";
  font-family: "FontAwesome";
  height: 20px;
  width: 20px;
  line-height: 20px;
  margin-top: -10px;
  position: absolute;
  top: 50%;
  right: 10px;
  text-align: center;

  -webkit-transition: 0.35s all;
  -moz-transition: 0.35s all;
  -o-transition: 0.35s all;
  -ms-transition: 0.35s all;
  transition: 0.35s all;
}

.gd-sldm
  .sldm
  nav
  .sldm-nav-container
  .sldm-nav
  ul.sldm-default
  li.sldm-submenu
  > a:hover:after,
.gd-sldm
  .sldm
  nav
  .sldm-nav-container
  .sldm-nav
  ul.sldm-default
  li.sldm-submenu
  > a:active:after {
  right: 10px;
  color: #394c53;
}

.gd-sldm
  .sldm
  nav
  .sldm-nav-container
  .sldm-nav
  ul.sldm-default
  li.sldm-submenu
  > a.sldm-open:after {
  -webkit-transform: rotate(-180deg);
  -moz-transform: rotate(-180deg);
  -ms-transform: rotate(-180deg);
  -o-transform: rotate(-180deg);
  transform: rotate(-180deg);
}
/* 열리는 내브바 설정 */
.gd-sldm
  .sldm
  nav
  .sldm-nav-container
  .sldm-nav
  ul.sldm-default
  li.sldm-submenu
  ul {
  display: none;
  padding-left: 20px;
  border-top: 1px solid #efebe9;
  background: #d5cac5;
}
/* 상세보기 (챌린지게시판) */
.gd-sldm
  .sldm
  nav
  .sldm-nav-container
  .sldm-nav
  ul.sldm-default
  li.sldm-submenu
  ul
  li {
  background: #fff;
}

.gd-sldm
  .sldm
  nav
  .sldm-nav-container
  .sldm-nav
  ul.sldm-default
  li.sldm-submenu
  ul
  li
  a {
  font-size: 15px;
}

.gd-sldm .sldm nav .sldm-nav-container .sldm-nav ul.sldm-default + ul {
  margin-top: 60px;
}
/* 내브바에 있는 한줄 */
.gd-sldm .sldm nav .sldm-nav-container .sldm-nav ul.sldm-default + ul:before {
  content: "";
  display: block;
  width: 60px;
  height: 3px;
  background: #cfc5c1;
  position: absolute;
  top: -25px;
  left: 50%;
  margin-left: -30px;
}

.gd-sldm .sldm nav .sldm-nav-container .sldm-nav ul.sldm-social {
  /* border-top: 1px solid #fff;
  border-bottom: 1px solid #fff; */
}

.gd-sldm .sldm nav .sldm-nav-container .sldm-nav ul.sldm-social:after,
.gd-sldm .sldm nav .sldm-nav-container .sldm-nav ul.sldm-social:before {
  clear: both;
  content: " ";
  display: table;
}

.gd-sldm .sldm nav .sldm-nav-container .sldm-nav ul.sldm-social li {
  float: left;
  width: 20%;
  font-size: 24px;
}
/* 소셜 로그인 */
/* .gd-sldm .sldm nav .sldm-nav-container .sldm-nav ul.sldm-social li a {
  padding: 12px 0;
  text-align: center;
  display: block;
  background: #dcdcdc;
  color: #585858;

  -webkit-transition: 0.35s all;
  -moz-transition: 0.35s all;
  -o-transition: 0.35s all;
  -ms-transition: 0.35s all;
  transition: 0.35s all;
  margin-left: 1px;
  margin-right: 1px;

  border-radius: 10px;
}

.gd-sldm .sldm nav .sldm-nav-container .sldm-nav ul.sldm-social li a:hover {
  background: #fff;
}

.gd-sldm
  .sldm
  nav
  .sldm-nav-container
  .sldm-nav
  ul.sldm-social
  li
  a:hover.sldm-facebook {
  color: #326477;
}

.gd-sldm
  .sldm
  nav
  .sldm-nav-container
  .sldm-nav
  ul.sldm-social
  li
  a:hover.sldm-google-plus {
  color: #dd4b39;
}

.gd-sldm
  .sldm
  nav
  .sldm-nav-container
  .sldm-nav
  ul.sldm-social
  li
  a:hover.sldm-twitter {
  color: #394c53;
}

.gd-sldm
  .sldm
  nav
  .sldm-nav-container
  .sldm-nav
  ul.sldm-social
  li
  a:hover.sldm-pinterest {
  color: #bd2125;
}

.gd-sldm
  .sldm
  nav
  .sldm-nav-container
  .sldm-nav
  ul.sldm-social
  li
  a:hover.sldm-youtube {
  color: #d22222;
} */
/* 내브바 버튼 */
.gd-sldm .sldm nav .sldm-toggle {
  position: absolute;
  top: 0;
  right: -60px;
  width: 60px;
  height: 60px;
  overflow: hidden;
  display: block;
  cursor: pointer;
  color: #969696;
  background: #fff;
  -webkit-text-shadow: none;
  -moz-text-shadow: none;
  -o-text-shadow: none;
  -ms-text-shadow: none;
  text-shadow: none;

  -webkit-transform: translateY(0);
  -moz-transform: translateY(0);
  -o-transform: translateY(0);
  transform: translateY(0);

  -webkit-transition: 0.35s all;
  -moz-transition: 0.35s all;
  -o-transition: 0.35s all;
  -ms-transition: 0.35s all;
  transition: 0.35s all;
  border-radius: 0px 3px 3px 0px;
}

.gd-sldm .sldm nav .sldm-toggle i {
  position: absolute;
  display: block;
  width: 100%;
  height: 100%;
  line-height: 60px;
  font-size: 24px;
  text-align: center;
  left: 0;

  -webkit-transform: translateY(0);
  -moz-transform: translateY(0);
  -o-transform: translateY(0);
  transform: translateY(0);

  -webkit-transition: 0.35s all;
  -moz-transition: 0.35s all;
  -o-transition: 0.35s all;
  -ms-transition: 0.35s all;
  transition: 0.35s all;
}

.gd-sldm .sldm nav .sldm-toggle i.sldm-close {
  -webkit-transform: translateY(-60px);
  -moz-transform: translateY(-60px);
  -o-transform: translateY(-60px);
  transform: translateY(-60px);
}

.gd-sldm .sldm nav .sldm-toggle i:hover {
  background: rgba(0, 0, 0, 0.1);
}
.gd-sldm .sldm.sldm-active nav .sldm-toggle i {
  -webkit-transform: translateY(60px);
  -moz-transform: translateY(60px);
  -o-transform: translateY(60px);
  transform: translateY(60px);
}

.gd-sldm .sldm.sldm-active nav .sldm-toggle i.sldm-close {
  -webkit-transform: translateY(0);
  -moz-transform: translateY(0);
  -o-transform: translateY(0);
  transform: translateY(0);
}
/* 내브바 켜지면 오버레이값 */
.gd-sldm .sldm.sldm-active .sldm-overlay {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: rgba(0, 0, 0, 0.7);
  display: block;
  z-index: 20;
}

.gd-sldm .sldm.sldm-active .sldm-overlay.sldm-white {
  background: rgba(255, 255, 255, 0.6);
}

.gd-sldm .sldm.sldm-active nav {
  -webkit-transform: translateX(0);
  -moz-transform: translateX(0);
  -o-transform: translateX(0);
  transform: translateX(0);

  -webkit-box-shadow: -1px 0 5px rgba(0, 0, 0, 0.15);
  -moz-box-shadow: -1px 0 5px rgba(0, 0, 0, 0.15);
  -o-box-shadow: -1px 0 5px rgba(0, 0, 0, 0.15);
  -ms-box-shadow: -1px 0 5px rgba(0, 0, 0, 0.15);
  box-shadow: -1px 0 5px rgba(0, 0, 0, 0.15);
}

@media screen and (max-width: 360px) {
  .gd-sldm .sldm.sldm-active nav .sldm-toggle {
    right: 0;
    z-index: 160;
  }
}

@media screen and (max-width: 330px) {
  .gd-sldm .sldm.sldm-active nav {
    width: 100% !important;
  }
}

.sldm-widget-container {
  position: fixed;
  top: 60px;
  left: 0;
  width: 100%;
  z-index: 5;
  -webkit-box-sizing: border-box;
  -moz-box-sizing: border-box;
  -o-box-sizing: border-box;
  -ms-box-sizing: border-box;
  box-sizing: border-box;
}

.sldm-widget-container * {
  -webkit-box-sizing: border-box;
  -moz-box-sizing: border-box;
  -o-box-sizing: border-box;
  -ms-box-sizing: border-box;
  box-sizing: border-box;
}
/* 밑으로 내려오는 내브바 */
.sldm-widget-container .sldm-widget {
  width: 100%;
  background: #696f71;
  height: 60px;
  padding: 10px;
  -webkit-box-shadow: 0 -1px 5px rgba(0, 0, 0, 0.1);
  -moz-box-shadow: 0 -1px 5px rgba(0, 0, 0, 0.1);
  -o-box-shadow: 0 -1px 5px rgba(0, 0, 0, 0.1);
  -ms-box-shadow: 0 -1px 5px rgba(0, 0, 0, 0.1);
  box-shadow: 0 -1px 5px rgba(0, 0, 0, 0.1);
  border-top: 3px solid rgba(255, 255, 255, 0.13);
}
/* 
.sldm-widget-container .sldm-widget.sldm-search-widget form {
  position: relative;
  margin: 0;
  padding: 0;
}

.sldm-widget-container .sldm-widget.sldm-search-widget form .sldm-input {
  font-family: "Open Sans", sans-serif;
  display: block;
  width: 100%;
  font-size: 18px;
  line-height: 18px;
  padding: 9px 145px 9px 10px;
  font-weight: 100;
  background: #fff;
  color: #777;
  border: none;
  height: 40px;
  -webkit-box-sizing: border-box;
  -moz-box-sizing: border-box;
  -o-box-sizing: border-box;
  -ms-box-sizing: border-box;
  box-sizing: border-box;
}

.sldm-widget-container .sldm-widget.sldm-search-widget form .sldm-input:focus {
  border: none;
  color: #444;
  outline: none;
  -webkit-box-shadow: none;
  -moz-box-shadow: none;
  -o-box-shadow: none;
  -ms-box-shadow: none;
  box-shadow: none;
}

.sldm-widget-container .sldm-widget.sldm-search-widget form .sldm-button {
  font-family: "Open Sans", sans-serif;
  display: block;
  width: 130px;
  position: absolute;
  right: 0;
  top: 0;
  height: 40px;
  line-height: 37px;
  border: none;
  font-size: 18px;
  text-transform: uppercase;
  font-weight: 100;
  background: #4f758b;
  -webkit-text-shadow: 0 0 0 #32b8e5;
  -moz-text-shadow: 0 0 0 #32b8e5;
  -o-text-shadow: 0 0 0 #32b8e5;
  -ms-text-shadow: 0 0 0 #32b8e5;
  color: rgba(255, 255, 255, 0.8);
  text-align: center;
  cursor: pointer;

  -webkit-transition: 0.35s all;
  -moz-transition: 0.35s all;
  -o-transition: 0.35s all;
  -ms-transition: 0.35s all;
  transition: 0.35s all;
}
.sldm-widget-container .sldm-widget.sldm-search-widget form .sldm-button:hover,
.sldm-widget-container .sldm-widget.sldm-search-widget form .sldm-button:focus {
  background-color: #b58500;
}
.sldm-widget-container .sldm-widget.sldm-search-widget form .sldm-button i {
  margin-left: 10px; 
} */
.sldm-widget-container
  .sldm-widget.sldm-phone-widget
  .sldm-phone-widget-content {
  color: #ffffff;
  text-align: center;
  line-height: 30px;
}
.sldm-clear {
  clear: both;
  overflow: hidden;
}

.sldm-hidden {
  display: none;
}

/* 메인 헤더 끝 */
</style>
