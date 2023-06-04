<template>
  <div>
    <div class="container-wrap">
      <div class="block-black">
        <div class="block-wrap">
          <!-- https://codepen.io/colorlib/pen/rxddKy -->
          <!-- 로그인 폼 시작 -->
          <div class="login-page">
            <div class="form">
              <form class="login-form">
                <div>소속팀이 패배하였습니다.</div>

                <div class="add_btn">
                  <router-link to=""
                    ><button style="border-radius: 5px; margin-right: 1%">
                      확인
                    </button></router-link
                  >
                </div>
                <!-- <p class="message">
                  아직 저희와 함께가 아니신가요? <br />
                  <a href="#">당당해와 함께 하러 가기</a>
                </p> -->
              </form>
            </div>
            <!-- 로그인 폼 끝 -->
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import $ from "jquery";
export default {
  name: "ChallengeDefeatModal",
  mounted() {
    $(".message a").click(function () {
      $("form").animate({ height: "toggle", opacity: "toggle" }, "slow");
    });
  },
};
</script>

<style scoped>
* {
  margin: 0;
  padding: 0;
}

/* .container-wrap {
  height: 100vh;
  background: url("../assets/DDHMainHeader2.png") no-repeat center;
  background-size: cover;
  position: relative;
} */

.block-wrap {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
}

.block-black {
  background-color: rgba(0, 0, 0, 0.4);
  width: 100%;
  height: 100%;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
}

/* 로그인 폼 시작 */
@import url(https://fonts.googleapis.com/css?family=Roboto:300);

.login-page {
  width: 360px;
  padding: 8% 0 0;
  margin: auto;
}
.form {
  position: relative;
  z-index: 1;
  background: #ffffff;
  max-width: 360px;
  margin: 0 auto 100px;
  padding: 45px;
  text-align: center;
  box-shadow: 0 0 20px 0 rgba(0, 0, 0, 0.2), 0 5px 5px 0 rgba(0, 0, 0, 0.24);
  border-radius: 5px;
}
.form input {
  font-family: "Roboto", sans-serif;
  outline: 0;
  background: #f2f2f2;
  width: 100%;
  border: 0;
  margin: 0 0 15px;
  padding: 15px;
  box-sizing: border-box;
  font-size: 14px;
}
.form button {
  font-family: "Roboto", sans-serif;
  text-transform: uppercase;
  outline: 0;
  background: #ff9090;
  width: 120px;
  border: 0;
  padding: 15px;
  color: #ffffff;
  font-size: 14px;
  -webkit-transition: all 0.3 ease;
  transition: all 0.3 ease;
  cursor: pointer;
}
.form button:hover,
.form button:active,
.form button:focus {
  background: #fd7576;
}
.form .message {
  margin: 15px 0 0;
  color: #b3b3b3;
  font-size: 12px;
}
.form .message a {
  color: #fd7576;
  text-decoration: none;
}
.form .register-form {
  display: none;
}
.container {
  position: relative;
  z-index: 1;
  max-width: 300px;
  margin: 0 auto;
}
.container:before,
.container:after {
  content: "";
  display: block;
  clear: both;
}
.container .info {
  margin: 50px auto;
  text-align: center;
}
.container .info h1 {
  margin: 0 0 15px;
  padding: 0;
  font-size: 36px;
  font-weight: 300;
  color: #1a1a1a;
}
.container .info span {
  color: #4d4d4d;
  font-size: 12px;
}
.container .info span a {
  color: #000000;
  text-decoration: none;
}
.container .info span .fa {
  color: #ef3b3a;
}
body {
  font-family: "Roboto", sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
}
.add_btn {
  display: flex;
  margin-top: 10%;
  justify-content: center;
}

/* 로그인 폼 끝 */
</style>
