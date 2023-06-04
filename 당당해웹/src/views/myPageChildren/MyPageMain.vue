<template>
  <div>
    <div class="mainpage-body">
      <router-link to="/main">
        <div class="self-banner-img">
          <img
            class="self-banner-img-fit"
            src="@/assets/DDHBanner1.png"
            style="margin-top: 1em"
          />
        </div>
      </router-link>
      <!-- 마이페이지입니다. -->
      <!-- 나의 정보 시작-->
      <!--https://codepen.io/GeorgePark/pen/VXrwOP-->
      <div class="profile" id="demo">
        <div class="profile-image">
          <img
            src="https://images.unsplash.com/photo-1513721032312-6a18a42c8763?w=152&h=152&fit=crop&crop=faces"
            alt=""
          />
        </div>

        <!-- 이 안에 넣으면 한 줄로 표현됨 -->
        <div class="profile-user-settings">
          <div class="profile-user-oneline">
            <div class="profile-user-name">{{ loginUser.name }}</div>
            <div class="profile-user-rank">{{ myrank }}위</div>
            <div class="profile-user-rank">({{ loginUser.point }}pt)</div>
            <button @click="open" class="btn profile-edit-btn">
              Edit Profile
            </button>

            <button @click="pass_open" class="btn profile-settings-btn">
              <i class="fas fa-cog" aria-hidden="true"></i>
            </button>
          </div>
        </div>

        <div class="profile-stats">
          <ul>
            <li style="display: flex">
              몸무게
              <span class="profile-stat-count" style="margin-left: 3%"
                >{{ loginUser.weight }} kg</span
              >
            </li>
            <br />
            <li style="display: flex">
              근육량
              <span class="profile-stat-count" style="margin-left: 3%"
                >{{ loginUser.muscle }} %</span
              >
            </li>
            <br />
            <li style="display: flex">
              HbA1c
              <span class="profile-stat-count" style="margin-left: 3%"
                >{{ loginUser.hbA1c }} (mg/dL)</span
              >
            </li>
            <br />
          </ul>
        </div>
      </div>
      <!-- 나의 정보 끝 -->

      <!-- 차트 정보 -->
      <!-- 혈당 입력하기를 누르면 UserInfoBloodSugarModal.vue가 뜹니다.-->
      <div class="title-text" style="display: flex">
        Daily Blood Sugar (mg/l)
        <input
          @click="open_blood_sugar"
          class="blood-sugar-btn"
          type="button"
          value="입력하기"
          style="margin-left: 2%; margin-top: -10px; color: white"
        />
      </div>

      <div id="demo">
        <div
          class="table-responsive-vertical shadow-z-1"
          style="border-radius: 30px"
        >
          <!-- Table starts here -->
          <table id="table" class="table table-hover table-mc-light-blue">
            <thead>
              <tr>
                <th style="font-weight: 500">NO</th>
                <th style="font-weight: 600">일시</th>
                <th style="font-weight: 600">혈당</th>
                <th style="font-weight: 600">평균 혈당</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="(list, index) in dailyBlood" :key="index">
                <td data-title="ID">{{ index + 1 }}</td>
                <td data-title="Name">
                  <router-link to="/challenge">{{ list.cur_date }}</router-link>
                </td>
                <td data-title="Link">{{ list.blood_sugar }}</td>
                <td data-title="Status">{{ avg_blood }}</td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>

      <!-- <section>
        <div class="graph stack1">
          <span>75%</span>    
        </div>
        <div class="graph stack2">
          <span>60%</span>    
        </div>
        <div class="graph stack3">
          <span>25%</span>    
        </div>
        <div class="graph stack4">
          <span>25%</span>    
        </div>
        <div class="graph stack5">
          <span>25%</span>    
        </div>
        <div class="graph stack6">
          <span>25%</span>    
        </div>
        <div class="graph stack7">
          <span>25%</span>    
        </div>

      </section> -->
      <UserBloodSugarModal
        v-if="open_blood_sugar_form"
        @close_modal_sugar="close_modal_sugar"
        @close_blood_sugar="close_blood_sugar"
      />

      <!-- 수정버튼을 누르면 UserInfoModifyModal.vue(유저 정보 수정)가 뜹니다. -->
      <UserInfoModifyModal v-if="open_modal_my" @close_modal="close" />

      <!--비밀번호 수정 모달-->
      <UserInfoPasswordModal
        v-if="open_modal_pass"
        @close_modal_pass="close_modal_pass"
      />

      <challenge-my-list />
      <router-link to="/challenge">
        <div class="self-banner-img">
          <img
            class="self-banner-img-fit"
            src="@/assets/ChallengeBanner1.png"
            style="margin-top: 15em"
          />
        </div>
      </router-link>
      <!-- 타이머 -->
      <!-- 스탑워치의 정지버튼을 누르면 ChallengeStopWatchModal.vue가 뜹니다. -->
      <div class="timeline">
        <div class="stopwatch">
          <div class="clockwrapper">
            <div class="outerdot paused"></div>
            <div class="clock">00 : 00 : 00</div>
          </div>
        </div>
        <!-- 블록스트릿 -->
        <div class="total_strick" style="margin-left: 15%">
          <!--타이머 버튼들-->
          <div class="controls">
            <div class="start btn_start">Start</div>
            <div class="stop btn_end disabled" @click="exercise_end">Reset</div>
          </div>

          <!-- 1라인 -> 20개 -->
          <div class="parent_strick" @click="open_modal_exe">
            <span
              class="strick"
              :class="{
                one_time: daily_exercise_times[0] == 1,
                two_time: daily_exercise_times[0] == 2,
                three_time: daily_exercise_times[0] >= 3,
              }"
            ></span>
            <span
              class="strick"
              :class="{
                one_time: daily_exercise_times[1] == 1,
                two_time: daily_exercise_times[1] == 2,
                three_time: daily_exercise_times[1] >= 3,
              }"
            ></span>
            <span
              class="strick"
              :class="{
                one_time: daily_exercise_times[2] == 1,
                two_time: daily_exercise_times[2] == 2,
                three_time: daily_exercise_times[2] >= 3,
              }"
            ></span>
            <span
              class="strick"
              :class="{
                one_time: daily_exercise_times[3] == 1,
                two_time: daily_exercise_times[3] == 2,
                three_time: daily_exercise_times[3] >= 3,
              }"
            ></span>
            <span
              class="strick"
              :class="{
                one_time: daily_exercise_times[4] == 1,
                two_time: daily_exercise_times[4] == 2,
                three_time: daily_exercise_times[4] >= 3,
              }"
            ></span>
            <span
              class="strick"
              :class="{
                one_time: daily_exercise_times[5] == 1,
                two_time: daily_exercise_times[5] == 2,
                three_time: daily_exercise_times[5] >= 3,
              }"
            ></span>
            <span
              class="strick"
              :class="{
                one_time: daily_exercise_times[6] == 1,
                two_time: daily_exercise_times[6] == 2,
                three_time: daily_exercise_times[6] >= 3,
              }"
            ></span>
            <span
              class="strick"
              :class="{
                one_time: daily_exercise_times[7] == 1,
                two_time: daily_exercise_times[7] == 2,
                three_time: daily_exercise_times[7] >= 3,
              }"
            ></span>
            <span
              class="strick"
              :class="{
                one_time: daily_exercise_times[8] == 1,
                two_time: daily_exercise_times[8] == 2,
                three_time: daily_exercise_times[8] >= 3,
              }"
            ></span>
            <span
              class="strick"
              :class="{
                one_time: daily_exercise_times[9] == 1,
                two_time: daily_exercise_times[9] == 2,
                three_time: daily_exercise_times[9] >= 3,
              }"
            ></span>
            <span
              class="strick"
              :class="{
                one_time: daily_exercise_times[10] == 1,
                two_time: daily_exercise_times[10] == 2,
                three_time: daily_exercise_times[10] >= 3,
              }"
            ></span>
            <span
              class="strick"
              :class="{
                one_time: daily_exercise_times[11] == 1,
                two_time: daily_exercise_times[11] == 2,
                three_time: daily_exercise_times[11] >= 3,
              }"
            ></span>
            <span
              class="strick"
              :class="{
                one_time: daily_exercise_times[12] == 1,
                two_time: daily_exercise_times[12] == 2,
                three_time: daily_exercise_times[12] >= 3,
              }"
            ></span>
            <span
              class="strick"
              :class="{
                one_time: daily_exercise_times[13] == 1,
                two_time: daily_exercise_times[13] == 2,
                three_time: daily_exercise_times[13] >= 3,
              }"
            ></span>
            <span
              class="strick"
              :class="{
                one_time: daily_exercise_times[14] == 1,
                two_time: daily_exercise_times[14] == 2,
                three_time: daily_exercise_times[14] >= 3,
              }"
            ></span>
            <span
              class="strick"
              :class="{
                one_time: daily_exercise_times[15] == 1,
                two_time: daily_exercise_times[15] == 2,
                three_time: daily_exercise_times[15] >= 3,
              }"
            ></span>
            <span
              class="strick"
              :class="{
                one_time: daily_exercise_times[16] == 1,
                two_time: daily_exercise_times[16] == 2,
                three_time: daily_exercise_times[16] >= 3,
              }"
            ></span>
            <span
              class="strick"
              :class="{
                one_time: daily_exercise_times[17] == 1,
                two_time: daily_exercise_times[17] == 2,
                three_time: daily_exercise_times[17] >= 3,
              }"
            ></span>
            <span
              class="strick"
              :class="{
                one_time: daily_exercise_times[18] == 1,
                two_time: daily_exercise_times[18] == 2,
                three_time: daily_exercise_times[18] >= 3,
              }"
            ></span>
            <span
              class="strick"
              :class="{
                one_time: daily_exercise_times[19] == 1,
                two_time: daily_exercise_times[19] == 2,
                three_time: daily_exercise_times[19] >= 3,
              }"
            ></span>
          </div>
          <div class="parent_strick" @click="open_modal_exe">
            <span
              class="strick"
              :class="{
                one_time: daily_exercise_times[20] == 1,
                two_time: daily_exercise_times[20] == 2,
                three_time: daily_exercise_times[20] >= 3,
              }"
            ></span>
            <span
              class="strick"
              :class="{
                one_time: daily_exercise_times[21] == 1,
                two_time: daily_exercise_times[21] == 2,
                three_time: daily_exercise_times[21] >= 3,
              }"
            ></span>
            <span
              class="strick"
              :class="{
                one_time: daily_exercise_times[22] == 1,
                two_time: daily_exercise_times[22] == 2,
                three_time: daily_exercise_times[22] >= 3,
              }"
            ></span>
            <span
              class="strick"
              :class="{
                one_time: daily_exercise_times[23] == 1,
                two_time: daily_exercise_times[23] == 2,
                three_time: daily_exercise_times[23] >= 3,
              }"
            ></span>
            <span
              class="strick"
              :class="{
                one_time: daily_exercise_times[24] == 1,
                two_time: daily_exercise_times[24] == 2,
                three_time: daily_exercise_times[24] >= 3,
              }"
            ></span>
            <span
              class="strick"
              :class="{
                one_time: daily_exercise_times[25] == 1,
                two_time: daily_exercise_times[25] == 2,
                three_time: daily_exercise_times[25] >= 3,
              }"
            ></span>
            <span
              class="strick"
              :class="{
                one_time: daily_exercise_times[26] == 1,
                two_time: daily_exercise_times[26] == 2,
                three_time: daily_exercise_times[26] >= 3,
              }"
            ></span>
            <span
              class="strick"
              :class="{
                one_time: daily_exercise_times[27] == 1,
                two_time: daily_exercise_times[27] == 2,
                three_time: daily_exercise_times[27] >= 3,
              }"
            ></span>
            <span
              class="strick"
              :class="{
                one_time: daily_exercise_times[28] == 1,
                two_time: daily_exercise_times[28] == 2,
                three_time: daily_exercise_times[28] >= 3,
              }"
            ></span>
            <span
              class="strick"
              :class="{
                one_time: daily_exercise_times[29] == 1,
                two_time: daily_exercise_times[29] == 2,
                three_time: daily_exercise_times[29] >= 3,
              }"
            ></span>
            <span
              class="strick"
              :class="{
                one_time: daily_exercise_times[30] == 1,
                two_time: daily_exercise_times[30] == 2,
                three_time: daily_exercise_times[30] >= 3,
              }"
            ></span>
            <span
              class="strick"
              :class="{
                one_time: daily_exercise_times[31] == 1,
                two_time: daily_exercise_times[31] == 2,
                three_time: daily_exercise_times[31] >= 3,
              }"
            ></span>
            <span
              class="strick"
              :class="{
                one_time: daily_exercise_times[32] == 1,
                two_time: daily_exercise_times[32] == 2,
                three_time: daily_exercise_times[32] >= 3,
              }"
            ></span>
            <span
              class="strick"
              :class="{
                one_time: daily_exercise_times[33] == 1,
                two_time: daily_exercise_times[33] == 2,
                three_time: daily_exercise_times[33] >= 3,
              }"
            ></span>
            <span
              class="strick"
              :class="{
                one_time: daily_exercise_times[34] == 1,
                two_time: daily_exercise_times[34] == 2,
                three_time: daily_exercise_times[34] >= 3,
              }"
            ></span>
            <span
              class="strick"
              :class="{
                one_time: daily_exercise_times[35] == 1,
                two_time: daily_exercise_times[35] == 2,
                three_time: daily_exercise_times[35] >= 3,
              }"
            ></span>
            <span
              class="strick"
              :class="{
                one_time: daily_exercise_times[36] == 1,
                two_time: daily_exercise_times[36] == 2,
                three_time: daily_exercise_times[36] >= 3,
              }"
            ></span>
            <span
              class="strick"
              :class="{
                one_time: daily_exercise_times[37] == 1,
                two_time: daily_exercise_times[37] == 2,
                three_time: daily_exercise_times[37] >= 3,
              }"
            ></span>
            <span
              class="strick"
              :class="{
                one_time: daily_exercise_times[38] == 1,
                two_time: daily_exercise_times[38] == 2,
                three_time: daily_exercise_times[38] >= 3,
              }"
            ></span>
            <span
              class="strick"
              :class="{
                one_time: daily_exercise_times[39] == 1,
                two_time: daily_exercise_times[39] == 2,
                three_time: daily_exercise_times[39] >= 3,
              }"
            ></span>
          </div>
          <div class="parent_strick" @click="open_modal_exe">
            <span
              class="strick"
              :class="{
                one_time: daily_exercise_times[40] == 1,
                two_time: daily_exercise_times[40] == 2,
                three_time: daily_exercise_times[40] >= 3,
              }"
            ></span>
            <span
              class="strick"
              :class="{
                one_time: daily_exercise_times[41] == 1,
                two_time: daily_exercise_times[41] == 2,
                three_time: daily_exercise_times[41] >= 3,
              }"
            ></span>
            <span
              class="strick"
              :class="{
                one_time: daily_exercise_times[42] == 1,
                two_time: daily_exercise_times[42] == 2,
                three_time: daily_exercise_times[42] >= 3,
              }"
            ></span>
            <span
              class="strick"
              :class="{
                one_time: daily_exercise_times[43] == 1,
                two_time: daily_exercise_times[43] == 2,
                three_time: daily_exercise_times[43] >= 3,
              }"
            ></span>
            <span
              class="strick"
              :class="{
                one_time: daily_exercise_times[44] == 1,
                two_time: daily_exercise_times[44] == 2,
                three_time: daily_exercise_times[44] >= 3,
              }"
            ></span>
            <span
              class="strick"
              :class="{
                one_time: daily_exercise_times[45] == 1,
                two_time: daily_exercise_times[45] == 2,
                three_time: daily_exercise_times[45] >= 3,
              }"
            ></span>
            <span
              class="strick"
              :class="{
                one_time: daily_exercise_times[46] == 1,
                two_time: daily_exercise_times[46] == 2,
                three_time: daily_exercise_times[46] >= 3,
              }"
            ></span>
            <span
              class="strick"
              :class="{
                one_time: daily_exercise_times[47] == 1,
                two_time: daily_exercise_times[47] == 2,
                three_time: daily_exercise_times[47] >= 3,
              }"
            ></span>
            <span
              class="strick"
              :class="{
                one_time: daily_exercise_times[48] == 1,
                two_time: daily_exercise_times[48] == 2,
                three_time: daily_exercise_times[48] >= 3,
              }"
            ></span>
            <span
              class="strick"
              :class="{
                one_time: daily_exercise_times[49] == 1,
                two_time: daily_exercise_times[49] == 2,
                three_time: daily_exercise_times[49] >= 3,
              }"
            ></span>
            <span
              class="strick"
              :class="{
                one_time: daily_exercise_times[50] == 1,
                two_time: daily_exercise_times[50] == 2,
                three_time: daily_exercise_times[50] >= 3,
              }"
            ></span>
            <span
              class="strick"
              :class="{
                one_time: daily_exercise_times[51] == 1,
                two_time: daily_exercise_times[51] == 2,
                three_time: daily_exercise_times[51] >= 3,
              }"
            ></span>
            <span
              class="strick"
              :class="{
                one_time: daily_exercise_times[52] == 1,
                two_time: daily_exercise_times[52] == 2,
                three_time: daily_exercise_times[52] >= 3,
              }"
            ></span>
            <span
              class="strick"
              :class="{
                one_time: daily_exercise_times[53] == 1,
                two_time: daily_exercise_times[53] == 2,
                three_time: daily_exercise_times[53] >= 3,
              }"
            ></span>
            <span
              class="strick"
              :class="{
                one_time: daily_exercise_times[54] == 1,
                two_time: daily_exercise_times[54] == 2,
                three_time: daily_exercise_times[54] >= 3,
              }"
            ></span>
            <span
              class="strick"
              :class="{
                one_time: daily_exercise_times[55] == 1,
                two_time: daily_exercise_times[55] == 2,
                three_time: daily_exercise_times[55] >= 3,
              }"
            ></span>
            <span
              class="strick"
              :class="{
                one_time: daily_exercise_times[56] == 1,
                two_time: daily_exercise_times[56] == 2,
                three_time: daily_exercise_times[56] >= 3,
              }"
            ></span>
            <span
              class="strick"
              :class="{
                one_time: daily_exercise_times[57] == 1,
                two_time: daily_exercise_times[57] == 2,
                three_time: daily_exercise_times[57] >= 3,
              }"
            ></span>
            <span
              class="strick"
              :class="{
                one_time: daily_exercise_times[58] == 1,
                two_time: daily_exercise_times[58] == 2,
                three_time: daily_exercise_times[58] >= 3,
              }"
            ></span>
            <span
              class="strick"
              :class="{
                one_time: daily_exercise_times[59] == 1,
                two_time: daily_exercise_times[59] == 2,
                three_time: daily_exercise_times[59] >= 3,
              }"
            ></span>
          </div>
          <div class="parent_strick" @click="open_modal_exe">
            <span
              class="strick"
              :class="{
                one_time: daily_exercise_times[60] == 1,
                two_time: daily_exercise_times[60] == 2,
                three_time: daily_exercise_times[60] >= 3,
              }"
            ></span>
            <span
              class="strick"
              :class="{
                one_time: daily_exercise_times[61] == 1,
                two_time: daily_exercise_times[61] == 2,
                three_time: daily_exercise_times[61] >= 3,
              }"
            ></span>
            <span
              class="strick"
              :class="{
                one_time: daily_exercise_times[62] == 1,
                two_time: daily_exercise_times[62] == 2,
                three_time: daily_exercise_times[62] >= 3,
              }"
            ></span>
            <span
              class="strick"
              :class="{
                one_time: daily_exercise_times[63] == 1,
                two_time: daily_exercise_times[63] == 2,
                three_time: daily_exercise_times[63] >= 3,
              }"
            ></span>
            <span
              class="strick"
              :class="{
                one_time: daily_exercise_times[64] == 1,
                two_time: daily_exercise_times[64] == 2,
                three_time: daily_exercise_times[64] >= 3,
              }"
            ></span>
            <span
              class="strick"
              :class="{
                one_time: daily_exercise_times[65] == 1,
                two_time: daily_exercise_times[65] == 2,
                three_time: daily_exercise_times[65] >= 3,
              }"
            ></span>
            <span
              class="strick"
              :class="{
                one_time: daily_exercise_times[66] == 1,
                two_time: daily_exercise_times[66] == 2,
                three_time: daily_exercise_times[66] >= 3,
              }"
            ></span>
            <span
              class="strick"
              :class="{
                one_time: daily_exercise_times[67] == 1,
                two_time: daily_exercise_times[67] == 2,
                three_time: daily_exercise_times[67] >= 3,
              }"
            ></span>
            <span
              class="strick"
              :class="{
                one_time: daily_exercise_times[68] == 1,
                two_time: daily_exercise_times[68] == 2,
                three_time: daily_exercise_times[68] >= 3,
              }"
            ></span>
            <span
              class="strick"
              :class="{
                one_time: daily_exercise_times[69] == 1,
                two_time: daily_exercise_times[69] == 2,
                three_time: daily_exercise_times[69] >= 3,
              }"
            ></span>
            <span
              class="strick"
              :class="{
                one_time: daily_exercise_times[70] == 1,
                two_time: daily_exercise_times[70] == 2,
                three_time: daily_exercise_times[70] >= 3,
              }"
            ></span>
            <span
              class="strick"
              :class="{
                one_time: daily_exercise_times[71] == 1,
                two_time: daily_exercise_times[71] == 2,
                three_time: daily_exercise_times[71] >= 3,
              }"
            ></span>
            <span
              class="strick"
              :class="{
                one_time: daily_exercise_times[72] == 1,
                two_time: daily_exercise_times[72] == 2,
                three_time: daily_exercise_times[72] >= 3,
              }"
            ></span>
            <span
              class="strick"
              :class="{
                one_time: daily_exercise_times[73] == 1,
                two_time: daily_exercise_times[73] == 2,
                three_time: daily_exercise_times[73] >= 3,
              }"
            ></span>
            <span
              class="strick"
              :class="{
                one_time: daily_exercise_times[74] == 1,
                two_time: daily_exercise_times[74] == 2,
                three_time: daily_exercise_times[74] >= 3,
              }"
            ></span>
            <span
              class="strick"
              :class="{
                one_time: daily_exercise_times[75] == 1,
                two_time: daily_exercise_times[75] == 2,
                three_time: daily_exercise_times[75] >= 3,
              }"
            ></span>
            <span
              class="strick"
              :class="{
                one_time: daily_exercise_times[76] == 1,
                two_time: daily_exercise_times[76] == 2,
                three_time: daily_exercise_times[76] >= 3,
              }"
            ></span>
            <span
              class="strick"
              :class="{
                one_time: daily_exercise_times[77] == 1,
                two_time: daily_exercise_times[77] == 2,
                three_time: daily_exercise_times[77] >= 3,
              }"
            ></span>
            <span
              class="strick"
              :class="{
                one_time: daily_exercise_times[78] == 1,
                two_time: daily_exercise_times[78] == 2,
                three_time: daily_exercise_times[78] >= 3,
              }"
            ></span>
            <span
              class="strick"
              :class="{
                one_time: daily_exercise_times[79] == 1,
                two_time: daily_exercise_times[79] == 2,
                three_time: daily_exercise_times[79] >= 3,
              }"
            ></span>
          </div>

          <!-- 유저 운동 리스트 -->
          <UserInfoExerciseListModal
            v-if="open_modal_ex"
            @close_modal_exe="close_modal_exe"
          />

          <div class="parent_strick">
            <span class="strick" v-for="block in 20" :key="block"></span>
          </div>
        </div>
      </div>
      <!--타이머 끝-->
      <div class="spacing-container"></div>
      <!--유튜브 검색 시작-->
      <div class="youtube_parent">
        <div class="self-oneline">
          <input
            type="text"
            v-model="keyword"
            placeholder="운동을 검색해보세요!"
            class="search"
            style="
              background-color: white;
              border: solid 1px #fb9f4c;
              border-radius: 5px 0px 0px 5px;
              width: 600px;
              height: 60px;
              font-size: 15px;
              font-weight: 400;
              color: #bcbcbc;
            "
          />
          <button class="search-btn-self" @click="search(keyword)">검색</button>
        </div>

        <div style="margin-top:5em">
          <!-- 유튜브 영상 결과 -->
          <ul class="youtube-list">
            <li v-for="video in videos" :key="video.id.videoId" style="margin-top:3em">
              <iframe
                width="560"
                height="315"
                :src="`https://www.youtube.com/embed/${
                  video.snippet.thumbnails.default.url.split('/')[4]
                }`"
                title="YouTube video player"
                frameborder="0"
                allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
                allowfullscreen
              ></iframe
              ><br />
              {{ video.snippet.title }}
            </li>
          </ul>
        </div>
      </div>
      <!--유튜브 검색 끝-->
    </div>
  </div>
</template>

<script>
import axios from "axios";
import $ from "jquery";
// import chart from "@/bloodSugar.js";
import { mapGetters, mapState } from "vuex";
import ChallengeMyList from "../../components/challenge/ChallengeMyList.vue";
import UserInfoModifyModal from "../../components/modal/UserInfoModifyModal.vue";
import UserInfoPasswordModal from "../../components/modal/UserInfoPasswordModal.vue";
import UserInfoExerciseListModal from "../../components/modal/UserInfoExerciseListModal.vue";
import UserBloodSugarModal from "../../components/modal/UserBloodSugarModal.vue";

export default {
  name: "MyPageMain",
  components: {
    ChallengeMyList,
    UserInfoModifyModal,
    UserInfoPasswordModal,
    UserInfoExerciseListModal,
    UserBloodSugarModal,
  },
  data() {
    return {
      keyword: "", //유튜브 운동키워드
      videos: [], //유튜브 비디오 받아옴
      videoUrl: "",
      open_modal_my: false,
      open_modal_pass: false,
      open_modal_ex: false,
      open_blood_sugar_form: false,
    };
  },
  methods: {
    close_modal_sugar() {
      this.open_blood_sugar_form = false;
    },
    search(value) {
      const URL = "https://www.googleapis.com/youtube/v3/search"; //요청주소
      const API_KEY = "AIzaSyA2SOD6YbS-X__oNvhHPOZizQlzL9CBnxQ";

      axios({
        url: URL,
        method: "GET",
        params: {
          key: API_KEY,
          part: "snippet",
          q: value,
          type: "video",
          maxResults: 3,
        },
      })
        .then((res) => {
          return res.data.items;
        })
        .then((res) => {
          this.videos = res;
        })
        .catch((err) => {
          console.log(err);
        });
    },

    open_blood_sugar() {
      this.open_blood_sugar_form = true;
    },
    close_blood_sugar() {
      this.open_blood_sugar_form = false;
    },

    open_modal_exe() {
      this.open_modal_ex = true;
    },

    close_modal_exe() {
      this.open_modal_ex = false;
    },

    open() {
      this.open_modal_my = true;
    },
    close() {
      this.open_modal_my = false;
    },
    pass_open() {
      this.open_modal_pass = true;
    },
    close_modal_pass() {
      this.open_modal_pass = false;
    },
    // 기록일자랑 운동한 시간만 기록하는 것이 좋겠다.
    exercise_end() {
      let today = new Date();
      let year = today.getFullYear(); // 년도
      let month = today.getMonth() + 1; // 월
      let date = today.getDate(); // 날짜
      var end_time = year + "-" + month + "-" + date;
      if (month >= 1 && month <= 9) end_time = year + "-0" + month + "-" + date;
      console.log(end_time); //2023-05-24

      //여백없이 저장을 위한 필터 과정
      var time = document.querySelector(".clock").innerHTML; //소요 시간 받기
      var reMin = time.split(":")[0].trim(" ", "");
      var reSec = time.split(":")[1].trim(" ", "");
      var reMili = time.split(":")[2].trim(" ", "");

      var reResult = reMin + ":" + reSec + ":" + reMili;
      console.log(reResult); //00:01:21

      var record_time = {
        no: 0,
        total_time: reResult,
        end_time: end_time,
        users_id: this.loginUser.id,
      };

      this.$store.dispatch("setRecordExercise", record_time);
    },
  },
  computed: {
    ...mapState(["loginUser"]),
    ...mapGetters(["myrank"]),
    ...mapGetters(["dailyExercise"]),
    ...mapGetters(["daily_exercise_times"]),
    ...mapGetters(["dailyBlood"]),
    ...mapGetters(["avg_blood"]),
  },
  created() {
    this.$store.dispatch("setUsers");
    this.$store.dispatch("setUser", this.loginUser.id);
    this.$store.dispatch("getRecordExercise", this.$store.state.loginUser.id);
    this.$store.dispatch("getRecordExerciseNew", this.loginUser.id);
    this.$store.dispatch("getDailySugar", this.loginUser.id);
  },
  updated() {
    this.$store.dispatch("getRecordExercise", this.loginUser.id);
  },
  mounted() {
    // 챌린지 테이블
    $(document).ready(function () {
      var table = $("#table");

      // Table bordered
      $("#table-bordered").change(function () {
        var value = $(this).val();
        table.removeClass("table-bordered").addClass(value);
      });

      // Table striped
      $("#table-striped").change(function () {
        var value = $(this).val();
        table.removeClass("table-striped").addClass(value);
      });

      // Table hover
      $("#table-hover").change(function () {
        var value = $(this).val();
        table.removeClass("table-hover").addClass(value);
      });

      // Table color
      $("#table-color").change(function () {
        var value = $(this).val();
        table.removeClass(/^table-mc-/).addClass(value);
      });

      //타이머
      var start = document.querySelector(".start"),
        stop = document.querySelector(".stop"),
        clock = document.querySelector(".clock"),
        seconds = document.querySelector(".outerdot"),
        timerState = "stopped", //Clock is either stopped, paused, or running
        startTime,
        elapsed,
        timer;
      //timer states

      start.addEventListener("click", function () {
        if (timerState == "stopped") {
          startTime = Date.now();
          timer = requestAnimationFrame(updateTime);
          timerState = "running";
          seconds.classList.remove("paused");
          seconds.classList.add("running");
          start.innerHTML = "Stop";
        } else if (timerState == "running") {
          cancelAnimationFrame(timer);
          timerState = "paused";
          seconds.classList.add("paused");
          stop.classList.remove("disabled");
          start.innerHTML = "Resume";
        } else if (timerState == "paused") {
          startTime = Date.now() - elapsed;
          timer = requestAnimationFrame(updateTime);
          timerState = "running";
          seconds.classList.remove("paused");
          seconds.classList.add("running");
          stop.classList.add("disabled");
          start.innerHTML = "Stop";
        }
      });

      stop.addEventListener("click", function () {
        if (!this.classList.contains("disabled")) {
          timerState = "stopped";
          seconds.classList.remove("paused", "running");
          this.classList.add("disabled");
          start.innerHTML = "Start";
          clock.innerHTML = "00 : 00 : 00";
        }
      });

      function updateTime() {
        timer = requestAnimationFrame(updateTime);
        elapsed = new Date(Date.now() - startTime);

        //minutes
        var mins = elapsed.getMinutes();
        //seconds
        var secs = elapsed.getSeconds();
        // hundredths
        var hund = Math.floor(elapsed.getMilliseconds() / 10);
        //add leading zeros
        if (mins < 10) {
          mins = "0" + mins;
        }
        if (secs < 10) {
          secs = "0" + secs;
        }
        if (hund < 10) {
          hund = "0" + hund;
        }

        //update clock
        clock.innerHTML = mins + " : " + secs + " : " + hund;
      }

      ("use strict");
      function r(f) {
        /in/.test(document.readyState) ? setTimeout("r(" + f + ")", 9) : f();
      }
      r(function () {
        if (!document.getElementsByClassName) {
          // IE8 support
          var getElementsByClassName = function (node, classname) {
            var a = [];
            var re = new RegExp("(^| )" + classname + "( |$)");
            var els = node.getElementsByTagName("*");
            for (var i = 0, j = els.length; i < j; i++)
              if (re.test(els[i].className)) a.push(els[i]);
            return a;
          };
          var videos = getElementsByClassName(document.body, "youtube");
        } else {
          videos = document.getElementsByClassName(document.body, "youtube");
        }

        var nb_videos = videos.length;
        for (var i = 0; i < nb_videos; i++) {
          // Based on the YouTube ID, we can easily find the thumbnail image
          videos[i].style.backgroundImage =
            "url(http://i.ytimg.com/vi/" + videos[i].id + "/sddefault.jpg)";

          // Overlay the Play icon to make it look like a video player
          var play = document.createElement("div");
          play.setAttribute("class", "play");
          videos[i].appendChild(play);

          videos[i].onclick = function () {
            // Create an iFrame with autoplay set to true
            var iframe = document.createElement("iframe");
            var iframe_url =
              "https://www.youtube.com/embed/" +
              this.id +
              "?autoplay=1&autohide=1";
            if (this.getAttribute("data-params"))
              iframe_url += "&" + this.getAttribute("data-params");
            iframe.setAttribute("src", iframe_url);
            iframe.setAttribute("frameborder", "0");

            // The height and width of the iFrame should be the same as parent
            iframe.style.width = this.style.width;
            iframe.style.height = this.style.height;

            // Replace the YouTube thumbnail with YouTube Player
            this.parentNode.replaceChild(iframe, this);
          };
        }
      });
    }); //ready
  },
};
</script>

<style scoped>
/* 나의 정보 시작 */

:root {
  font-size: 10px;
}

*,
*::before,
*::after {
  box-sizing: border-box;
}

body {
  font-family: "Open Sans", Arial, sans-serif;
  min-height: 100vh;
  /* background-color: #fafafa; */
  color: #262626;
  padding-bottom: 3rem;
}

img {
  display: block;
}

.container {
  max-width: 93.5rem;
  margin: 0 auto;
  padding: 0 2rem;
}

.btn {
  display: inline-block;
  font: inherit;
  background: none;
  border: none;
  color: inherit;
  padding: 0;
  cursor: pointer;
}

.btn:focus {
  outline: 0.5rem auto #4d90fe;
}

.visually-hidden {
  position: absolute !important;
  height: 1px;
  width: 1px;
  overflow: hidden;
  clip: rect(1px, 1px, 1px, 1px);
}

/* Profile Section */

.profile {
  padding: 5rem 0;
}

.profile::after {
  content: "";
  display: block;
  clear: both;
}

.profile-image {
  float: left;
  width: calc(33.333% - 1rem);
  display: flex;
  justify-content: center;
  align-items: center;
  margin-right: 3rem;
}

.profile-image img {
  border-radius: 50%;
}

.profile-user-settings,
.profile-stats,
.profile-bio {
  float: left;
  width: calc(66.666% - 2rem);
}

.profile-user-settings {
  margin-top: 1.1rem;
}

.profile-user-name {
  display: inline-block;
  font-size: 3.2rem;
  font-weight: 300;
  width: 100px;
}

.profile-edit-btn {
  width: 100px;
  font-size: 1.4rem;
  line-height: 1.8;
  border: 0.1rem solid #dbdbdb;
  border-radius: 0.3rem;
  padding: 0 2.4rem;
  margin-left: 25rem;
}

.profile-settings-btn {
  font-size: 2rem;
  margin-left: 1rem;
}

.profile-stats {
  margin-top: 2.3rem;
  color: #535353;
}

.profile-stats li {
  display: inline-block;
  font-size: 1.6rem;
  line-height: 0.4;
  margin-right: 4rem;
  cursor: pointer;
}

.profile-stats li:last-of-type {
  margin-right: 0;
}

.profile-bio {
  font-size: 1.6rem;
  font-weight: 400;
  line-height: 1.5;
  margin-top: 2.3rem;
}

.profile-real-name,
.profile-stat-count,
.profile-edit-btn {
  font-weight: 600;
}

/* Gallery Section */

.gallery {
  display: flex;
  flex-wrap: wrap;
  margin: -1rem -1rem;
  padding-bottom: 3rem;
}

.gallery-item {
  position: relative;
  flex: 1 0 22rem;
  margin: 1rem;
  color: #fff;
  cursor: pointer;
}

.gallery-item:hover .gallery-item-info,
.gallery-item:focus .gallery-item-info {
  display: flex;
  justify-content: center;
  align-items: center;
  position: absolute;
  top: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.3);
}

.gallery-item-info {
  display: none;
}

.gallery-item-info li {
  display: inline-block;
  font-size: 1.7rem;
  font-weight: 600;
}

.gallery-item-likes {
  margin-right: 2.2rem;
}

.gallery-item-type {
  position: absolute;
  top: 1rem;
  right: 1rem;
  font-size: 2.5rem;
  text-shadow: 0.2rem 0.2rem 0.2rem rgba(0, 0, 0, 0.1);
}

.fa-clone,
.fa-comment {
  transform: rotateY(180deg);
}

.gallery-image {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

/* Loader */

.loader {
  width: 5rem;
  height: 5rem;
  border: 0.6rem solid #999;
  border-bottom-color: transparent;
  border-radius: 50%;
  margin: 0 auto;
  animation: loader 500ms linear infinite;
}

/* Media Query */

@media screen and (max-width: 40rem) {
  .profile {
    display: flex;
    flex-wrap: wrap;
    padding: 4rem 0;
  }

  .profile::after {
    display: none;
  }

  .profile-image,
  .profile-user-settings,
  .profile-bio,
  .profile-stats {
    float: none;
    width: auto;
  }

  .profile-image img {
    width: 7.7rem;
  }

  .profile-user-settings {
    flex-basis: calc(100% - 10.7rem);
    display: flex;
    flex-wrap: wrap;
    margin-top: 1rem;
  }

  .profile-user-name {
    font-size: 2.2rem;
  }

  .profile-edit-btn {
    order: 1;
    padding: 0;
    text-align: center;
    margin-top: 1rem;
  }

  .profile-edit-btn {
    margin-left: 0;
  }

  .profile-bio {
    font-size: 1.4rem;
    margin-top: 1.5rem;
  }

  .profile-edit-btn,
  .profile-bio,
  .profile-stats {
    flex-basis: 100%;
  }

  .profile-stats {
    order: 1;
    margin-top: 1.5rem;
  }

  .profile-stats ul {
    display: flex;
    text-align: center;
    padding: 1.2rem 0;
    border-top: 0.1rem solid #dadada;
    border-bottom: 0.1rem solid #dadada;
  }

  .profile-stats li {
    font-size: 1.4rem;
    flex: 1;
    margin: 0;
  }

  .profile-stat-count {
    display: block;
  }
}

/* Spinner Animation */

@keyframes loader {
  to {
    transform: rotate(360deg);
  }
}

/*

The following code will only run if your browser supports CSS grid.

Remove or comment-out the code block below to see how the browser will fall-back to flexbox & floated styling.

*/

@supports (display: grid) {
  .profile {
    display: grid;
    grid-template-columns: 1fr 2fr;
    grid-template-rows: repeat(3, auto);
    grid-column-gap: 3rem;
    align-items: center;
  }

  .profile-image {
    grid-row: 1 / -1;
  }

  .gallery {
    display: grid;
    grid-template-columns: repeat(auto-fit, minmax(22rem, 1fr));
    grid-gap: 2rem;
  }

  .profile-image,
  .profile-user-settings,
  .profile-stats,
  .profile-bio,
  .gallery-item,
  .gallery {
    width: auto;
    margin: 0;
  }

  @media (max-width: 40rem) {
    .profile {
      grid-template-columns: auto 1fr;
      grid-row-gap: 1.5rem;
    }

    .profile-image {
      grid-row: 1 / 2;
    }

    .profile-user-settings {
      display: grid;
      grid-template-columns: auto 1fr;
      grid-gap: 1rem;
    }

    .profile-edit-btn,
    .profile-stats,
    .profile-bio {
      grid-column: 1 / -1;
    }

    .profile-user-settings,
    .profile-edit-btn,
    .profile-settings-btn,
    .profile-bio,
    .profile-stats {
      margin: 0;
    }
  }
}
/* 나의 정보 끝 */

/* 나의 챌린지 목록 관련 시작 */
/* -- import Roboto Font ---------------------------- */
@font-face {
  font-family: "Roboto";
  font-style: italic;
  font-weight: 100;
  src: url(https://fonts.gstatic.com/s/roboto/v30/KFOiCnqEu92Fr1Mu51QrEzQdKg.ttf)
    format("truetype");
}
@font-face {
  font-family: "Roboto";
  font-style: italic;
  font-weight: 300;
  src: url(https://fonts.gstatic.com/s/roboto/v30/KFOjCnqEu92Fr1Mu51TjASc-CsE.ttf)
    format("truetype");
}
@font-face {
  font-family: "Roboto";
  font-style: italic;
  font-weight: 400;
  src: url(https://fonts.gstatic.com/s/roboto/v30/KFOkCnqEu92Fr1Mu51xMIzc.ttf)
    format("truetype");
}
@font-face {
  font-family: "Roboto";
  font-style: italic;
  font-weight: 500;
  src: url(https://fonts.gstatic.com/s/roboto/v30/KFOjCnqEu92Fr1Mu51S7ACc-CsE.ttf)
    format("truetype");
}
@font-face {
  font-family: "Roboto";
  font-style: italic;
  font-weight: 700;
  src: url(https://fonts.gstatic.com/s/roboto/v30/KFOjCnqEu92Fr1Mu51TzBic-CsE.ttf)
    format("truetype");
}
@font-face {
  font-family: "Roboto";
  font-style: italic;
  font-weight: 900;
  src: url(https://fonts.gstatic.com/s/roboto/v30/KFOjCnqEu92Fr1Mu51TLBCc-CsE.ttf)
    format("truetype");
}
@font-face {
  font-family: "Roboto";
  font-style: normal;
  font-weight: 100;
  src: url(https://fonts.gstatic.com/s/roboto/v30/KFOkCnqEu92Fr1MmgVxMIzc.ttf)
    format("truetype");
}
@font-face {
  font-family: "Roboto";
  font-style: normal;
  font-weight: 300;
  src: url(https://fonts.gstatic.com/s/roboto/v30/KFOlCnqEu92Fr1MmSU5fABc9.ttf)
    format("truetype");
}
@font-face {
  font-family: "Roboto";
  font-style: normal;
  font-weight: 400;
  src: url(https://fonts.gstatic.com/s/roboto/v30/KFOmCnqEu92Fr1Mu5mxP.ttf)
    format("truetype");
}
@font-face {
  font-family: "Roboto";
  font-style: normal;
  font-weight: 500;
  src: url(https://fonts.gstatic.com/s/roboto/v30/KFOlCnqEu92Fr1MmEU9fABc9.ttf)
    format("truetype");
}
@font-face {
  font-family: "Roboto";
  font-style: normal;
  font-weight: 700;
  src: url(https://fonts.gstatic.com/s/roboto/v30/KFOlCnqEu92Fr1MmWUlfABc9.ttf)
    format("truetype");
}
@font-face {
  font-family: "Roboto";
  font-style: normal;
  font-weight: 900;
  src: url(https://fonts.gstatic.com/s/roboto/v30/KFOlCnqEu92Fr1MmYUtfABc9.ttf)
    format("truetype");
}
/* -- You can use this tables in Bootstrap (v3) projects. -- */
/* -- Box model ------------------------------- */
*,
*:after,
*:before {
  -webkit-box-sizing: border-box;
  -moz-box-sizing: border-box;
  box-sizing: border-box;
}
/* -- Demo style ------------------------------- */
html,
body {
  position: relative;
  min-height: 100%;
  height: 100%;
}
html {
  position: relative;
  overflow-x: hidden;
  /* margin: 16px; */
  padding: 0;
  min-height: 100%;
  font-size: 62.5%;
}
body {
  font-family: "RobotoDraft", "Roboto", "Helvetica Neue, Helvetica, Arial",
    sans-serif;
  font-style: normal;
  font-weight: 300;
  font-size: 1.4rem;
  line-height: 2rem;
  letter-spacing: 0.01rem;
  color: #585858;
  /* background-color: #f5f5f5; */
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-rendering: optimizeLegibility;
}
#demo {
  margin: 20px auto;
  max-width: 960px;
}
#demo h1 {
  font-size: 2.4rem;
  line-height: 3.2rem;
  letter-spacing: 0;
  font-weight: 300;
  color: #212121;
  text-transform: inherit;
  margin-bottom: 1rem;
  text-align: center;
}
#demo h2 {
  font-size: 1.5rem;
  line-height: 2.8rem;
  letter-spacing: 0.01rem;
  font-weight: 400;
  color: #212121;
  text-align: center;
}
.shadow-z-1 {
  -webkit-box-shadow: 0 1px 3px 0 rgba(0, 0, 0, 0.12),
    0 1px 2px 0 rgba(0, 0, 0, 0.24);
  -moz-box-shadow: 0 1px 3px 0 rgba(0, 0, 0, 0.12),
    0 1px 2px 0 rgba(0, 0, 0, 0.24);
  box-shadow: 0 1px 3px 0 rgba(0, 0, 0, 0.12), 0 1px 2px 0 rgba(0, 0, 0, 0.24);
}
/* -- Material Design Table style -------------- */
.table-radius {
  border-radius: 10px;
}

.table {
  width: 100%;
  max-width: 100%;
  margin-bottom: 2rem;
  background-color: #fff;
}
.table > thead > tr,
.table > tbody > tr,
.table > tfoot > tr {
  -webkit-transition: all 0.3s ease;
  -o-transition: all 0.3s ease;
  transition: all 0.3s ease;
}
.table > thead > tr > th,
.table > tbody > tr > th,
.table > tfoot > tr > th,
.table > thead > tr > td,
.table > tbody > tr > td,
.table > tfoot > tr > td {
  text-align: left;
  padding: 1.6rem;
  vertical-align: top;
  border-top: 0;
  -webkit-transition: all 0.3s ease;
  -o-transition: all 0.3s ease;
  transition: all 0.3s ease;
}
.table > thead > tr > th {
  font-weight: 400;
  color: #757575;
  vertical-align: bottom;
  border-bottom: 1px solid rgba(173, 173, 173, 0.12);
}
.table > caption + thead > tr:first-child > th,
.table > colgroup + thead > tr:first-child > th,
.table > thead:first-child > tr:first-child > th,
.table > caption + thead > tr:first-child > td,
.table > colgroup + thead > tr:first-child > td,
.table > thead:first-child > tr:first-child > td {
  border-top: 0;
}
.table > tbody + tbody {
  border-top: 1px solid rgba(0, 0, 0, 0.12);
}
.table .table {
  background-color: #fff;
}
.table .no-border {
  border: 0;
}
.table-condensed > thead > tr > th,
.table-condensed > tbody > tr > th,
.table-condensed > tfoot > tr > th,
.table-condensed > thead > tr > td,
.table-condensed > tbody > tr > td,
.table-condensed > tfoot > tr > td {
  padding: 0.8rem;
}
.table-bordered {
  border: 0;
}
.table-bordered > thead > tr > th,
.table-bordered > tbody > tr > th,
.table-bordered > tfoot > tr > th,
.table-bordered > thead > tr > td,
.table-bordered > tbody > tr > td,
.table-bordered > tfoot > tr > td {
  border: 0;
  border-bottom: 1px solid #e0e0e0;
}
.table-bordered > thead > tr > th,
.table-bordered > thead > tr > td {
  border-bottom-width: 2px;
}
.table-striped > tbody > tr:nth-child(odd) > td,
.table-striped > tbody > tr:nth-child(odd) > th {
  background-color: #f5f5f5;
}
.table-hover > tbody > tr:hover > td,
.table-hover > tbody > tr:hover > th {
  background-color: rgba(0, 0, 0, 0.12);
}
@media screen and (max-width: 768px) {
  .table-responsive-vertical > .table {
    margin-bottom: 0;
    background-color: transparent;
  }
  .table-responsive-vertical > .table > thead,
  .table-responsive-vertical > .table > tfoot {
    display: none;
  }
  .table-responsive-vertical > .table > tbody {
    display: block;
  }
  .table-responsive-vertical > .table > tbody > tr {
    display: block;
    border: 1px solid #e0e0e0;
    border-radius: 2px;
    margin-bottom: 1.6rem;
  }
  .table-responsive-vertical > .table > tbody > tr > td {
    background-color: #fff;
    display: block;
    vertical-align: middle;
    text-align: right;
  }
  .table-responsive-vertical > .table > tbody > tr > td[data-title]:before {
    content: attr(data-title);
    float: left;
    font-size: inherit;
    font-weight: 400;
    color: #757575;
  }
  .table-responsive-vertical.shadow-z-1 {
    -webkit-box-shadow: none;
    -moz-box-shadow: none;
    box-shadow: none;
  }
  .table-responsive-vertical.shadow-z-1 > .table > tbody > tr {
    border: none;
    -webkit-box-shadow: 0 1px 3px 0 rgba(0, 0, 0, 0.12),
      0 1px 2px 0 rgba(0, 0, 0, 0.24);
    -moz-box-shadow: 0 1px 3px 0 rgba(0, 0, 0, 0.12),
      0 1px 2px 0 rgba(0, 0, 0, 0.24);
    box-shadow: 0 1px 3px 0 rgba(0, 0, 0, 0.12), 0 1px 2px 0 rgba(0, 0, 0, 0.24);
  }
  .table-responsive-vertical > .table-bordered {
    border: 0;
  }
  .table-responsive-vertical > .table-bordered > tbody > tr > td {
    border: 0;
    border-bottom: 1px solid #e0e0e0;
  }
  .table-responsive-vertical > .table-bordered > tbody > tr > td:last-child {
    border-bottom: 0;
  }
  .table-responsive-vertical > .table-striped > tbody > tr > td,
  .table-responsive-vertical > .table-striped > tbody > tr:nth-child(odd) {
    background-color: #fff;
  }
  .table-responsive-vertical > .table-striped > tbody > tr > td:nth-child(odd) {
    background-color: #f5f5f5;
  }
  .table-responsive-vertical > .table-hover > tbody > tr:hover > td,
  .table-responsive-vertical > .table-hover > tbody > tr:hover {
    background-color: #fff;
  }
  .table-responsive-vertical > .table-hover > tbody > tr > td:hover {
    background-color: rgba(0, 0, 0, 0.12);
  }
}
.table-striped.table-mc-red > tbody > tr:nth-child(odd) > td,
.table-striped.table-mc-red > tbody > tr:nth-child(odd) > th {
  background-color: #fde0dc;
}
.table-hover.table-mc-red > tbody > tr:hover > td,
.table-hover.table-mc-red > tbody > tr:hover > th {
  background-color: #f9bdbb;
}
@media screen and (max-width: 767px) {
  .table-responsive-vertical .table-striped.table-mc-red > tbody > tr > td,
  .table-responsive-vertical
    .table-striped.table-mc-red
    > tbody
    > tr:nth-child(odd) {
    background-color: #fff;
  }
  .table-responsive-vertical
    .table-striped.table-mc-red
    > tbody
    > tr
    > td:nth-child(odd) {
    background-color: #fde0dc;
  }
  .table-responsive-vertical .table-hover.table-mc-red > tbody > tr:hover > td,
  .table-responsive-vertical .table-hover.table-mc-red > tbody > tr:hover {
    background-color: #fff;
  }
  .table-responsive-vertical .table-hover.table-mc-red > tbody > tr > td:hover {
    background-color: #f9bdbb;
  }
}
.table-striped.table-mc-pink > tbody > tr:nth-child(odd) > td,
.table-striped.table-mc-pink > tbody > tr:nth-child(odd) > th {
  background-color: #fce4ec;
}
.table-hover.table-mc-pink > tbody > tr:hover > td,
.table-hover.table-mc-pink > tbody > tr:hover > th {
  background-color: #f8bbd0;
}
@media screen and (max-width: 767px) {
  .table-responsive-vertical .table-striped.table-mc-pink > tbody > tr > td,
  .table-responsive-vertical
    .table-striped.table-mc-pink
    > tbody
    > tr:nth-child(odd) {
    background-color: #fff;
  }
  .table-responsive-vertical
    .table-striped.table-mc-pink
    > tbody
    > tr
    > td:nth-child(odd) {
    background-color: #fce4ec;
  }
  .table-responsive-vertical .table-hover.table-mc-pink > tbody > tr:hover > td,
  .table-responsive-vertical .table-hover.table-mc-pink > tbody > tr:hover {
    background-color: #fff;
  }
  .table-responsive-vertical
    .table-hover.table-mc-pink
    > tbody
    > tr
    > td:hover {
    background-color: #f8bbd0;
  }
}
.table-striped.table-mc-purple > tbody > tr:nth-child(odd) > td,
.table-striped.table-mc-purple > tbody > tr:nth-child(odd) > th {
  background-color: #f3e5f5;
}
.table-hover.table-mc-purple > tbody > tr:hover > td,
.table-hover.table-mc-purple > tbody > tr:hover > th {
  background-color: #e1bee7;
}
@media screen and (max-width: 767px) {
  .table-responsive-vertical .table-striped.table-mc-purple > tbody > tr > td,
  .table-responsive-vertical
    .table-striped.table-mc-purple
    > tbody
    > tr:nth-child(odd) {
    background-color: #fff;
  }
  .table-responsive-vertical
    .table-striped.table-mc-purple
    > tbody
    > tr
    > td:nth-child(odd) {
    background-color: #f3e5f5;
  }
  .table-responsive-vertical
    .table-hover.table-mc-purple
    > tbody
    > tr:hover
    > td,
  .table-responsive-vertical .table-hover.table-mc-purple > tbody > tr:hover {
    background-color: #fff;
  }
  .table-responsive-vertical
    .table-hover.table-mc-purple
    > tbody
    > tr
    > td:hover {
    background-color: #e1bee7;
  }
}
.table-striped.table-mc-deep-purple > tbody > tr:nth-child(odd) > td,
.table-striped.table-mc-deep-purple > tbody > tr:nth-child(odd) > th {
  background-color: #ede7f6;
}
.table-hover.table-mc-deep-purple > tbody > tr:hover > td,
.table-hover.table-mc-deep-purple > tbody > tr:hover > th {
  background-color: #d1c4e9;
}
@media screen and (max-width: 767px) {
  .table-responsive-vertical
    .table-striped.table-mc-deep-purple
    > tbody
    > tr
    > td,
  .table-responsive-vertical
    .table-striped.table-mc-deep-purple
    > tbody
    > tr:nth-child(odd) {
    background-color: #fff;
  }
  .table-responsive-vertical
    .table-striped.table-mc-deep-purple
    > tbody
    > tr
    > td:nth-child(odd) {
    background-color: #ede7f6;
  }
  .table-responsive-vertical
    .table-hover.table-mc-deep-purple
    > tbody
    > tr:hover
    > td,
  .table-responsive-vertical
    .table-hover.table-mc-deep-purple
    > tbody
    > tr:hover {
    background-color: #fff;
  }
  .table-responsive-vertical
    .table-hover.table-mc-deep-purple
    > tbody
    > tr
    > td:hover {
    background-color: #d1c4e9;
  }
}
.table-striped.table-mc-indigo > tbody > tr:nth-child(odd) > td,
.table-striped.table-mc-indigo > tbody > tr:nth-child(odd) > th {
  background-color: #e8eaf6;
}
.table-hover.table-mc-indigo > tbody > tr:hover > td,
.table-hover.table-mc-indigo > tbody > tr:hover > th {
  background-color: #c5cae9;
}
@media screen and (max-width: 767px) {
  .table-responsive-vertical .table-striped.table-mc-indigo > tbody > tr > td,
  .table-responsive-vertical
    .table-striped.table-mc-indigo
    > tbody
    > tr:nth-child(odd) {
    background-color: #fff;
  }
  .table-responsive-vertical
    .table-striped.table-mc-indigo
    > tbody
    > tr
    > td:nth-child(odd) {
    background-color: #e8eaf6;
  }
  .table-responsive-vertical
    .table-hover.table-mc-indigo
    > tbody
    > tr:hover
    > td,
  .table-responsive-vertical .table-hover.table-mc-indigo > tbody > tr:hover {
    background-color: #fff;
  }
  .table-responsive-vertical
    .table-hover.table-mc-indigo
    > tbody
    > tr
    > td:hover {
    background-color: #c5cae9;
  }
}
.table-striped.table-mc-blue > tbody > tr:nth-child(odd) > td,
.table-striped.table-mc-blue > tbody > tr:nth-child(odd) > th {
  background-color: #e7e9fd;
}
.table-hover.table-mc-blue > tbody > tr:hover > td,
.table-hover.table-mc-blue > tbody > tr:hover > th {
  background-color: #d0d9ff;
}
@media screen and (max-width: 767px) {
  .table-responsive-vertical .table-striped.table-mc-blue > tbody > tr > td,
  .table-responsive-vertical
    .table-striped.table-mc-blue
    > tbody
    > tr:nth-child(odd) {
    background-color: #fff;
  }
  .table-responsive-vertical
    .table-striped.table-mc-blue
    > tbody
    > tr
    > td:nth-child(odd) {
    background-color: #e7e9fd;
  }
  .table-responsive-vertical .table-hover.table-mc-blue > tbody > tr:hover > td,
  .table-responsive-vertical .table-hover.table-mc-blue > tbody > tr:hover {
    background-color: #fff;
  }
  .table-responsive-vertical
    .table-hover.table-mc-blue
    > tbody
    > tr
    > td:hover {
    background-color: #d0d9ff;
  }
}
.table-striped.table-mc-light-blue > tbody > tr:nth-child(odd) > td,
.table-striped.table-mc-light-blue > tbody > tr:nth-child(odd) > th {
  background-color: #e1f5fe;
}
.table-hover.table-mc-light-blue > tbody > tr:hover > td,
.table-hover.table-mc-light-blue > tbody > tr:hover > th {
  background-color: #ffcea4;
}
@media screen and (max-width: 767px) {
  .table-responsive-vertical
    .table-striped.table-mc-light-blue
    > tbody
    > tr
    > td,
  .table-responsive-vertical
    .table-striped.table-mc-light-blue
    > tbody
    > tr:nth-child(odd) {
    background-color: #fff;
  }
  .table-responsive-vertical
    .table-striped.table-mc-light-blue
    > tbody
    > tr
    > td:nth-child(odd) {
    background-color: #e1f5fe;
  }
  .table-responsive-vertical
    .table-hover.table-mc-light-blue
    > tbody
    > tr:hover
    > td,
  .table-responsive-vertical
    .table-hover.table-mc-light-blue
    > tbody
    > tr:hover {
    background-color: #fff;
  }
  .table-responsive-vertical
    .table-hover.table-mc-light-blue
    > tbody
    > tr
    > td:hover {
    background-color: #b3e5fc;
  }
}
.table-striped.table-mc-cyan > tbody > tr:nth-child(odd) > td,
.table-striped.table-mc-cyan > tbody > tr:nth-child(odd) > th {
  background-color: #e0f7fa;
}
.table-hover.table-mc-cyan > tbody > tr:hover > td,
.table-hover.table-mc-cyan > tbody > tr:hover > th {
  background-color: #b2ebf2;
}
@media screen and (max-width: 767px) {
  .table-responsive-vertical .table-striped.table-mc-cyan > tbody > tr > td,
  .table-responsive-vertical
    .table-striped.table-mc-cyan
    > tbody
    > tr:nth-child(odd) {
    background-color: #fff;
  }
  .table-responsive-vertical
    .table-striped.table-mc-cyan
    > tbody
    > tr
    > td:nth-child(odd) {
    background-color: #e0f7fa;
  }
  .table-responsive-vertical .table-hover.table-mc-cyan > tbody > tr:hover > td,
  .table-responsive-vertical .table-hover.table-mc-cyan > tbody > tr:hover {
    background-color: #fff;
  }
  .table-responsive-vertical
    .table-hover.table-mc-cyan
    > tbody
    > tr
    > td:hover {
    background-color: #b2ebf2;
  }
}
.table-striped.table-mc-teal > tbody > tr:nth-child(odd) > td,
.table-striped.table-mc-teal > tbody > tr:nth-child(odd) > th {
  background-color: #e0f2f1;
}
.table-hover.table-mc-teal > tbody > tr:hover > td,
.table-hover.table-mc-teal > tbody > tr:hover > th {
  background-color: #b2dfdb;
}
@media screen and (max-width: 767px) {
  .table-responsive-vertical .table-striped.table-mc-teal > tbody > tr > td,
  .table-responsive-vertical
    .table-striped.table-mc-teal
    > tbody
    > tr:nth-child(odd) {
    background-color: #fff;
  }
  .table-responsive-vertical
    .table-striped.table-mc-teal
    > tbody
    > tr
    > td:nth-child(odd) {
    background-color: #e0f2f1;
  }
  .table-responsive-vertical .table-hover.table-mc-teal > tbody > tr:hover > td,
  .table-responsive-vertical .table-hover.table-mc-teal > tbody > tr:hover {
    background-color: #fff;
  }
  .table-responsive-vertical
    .table-hover.table-mc-teal
    > tbody
    > tr
    > td:hover {
    background-color: #b2dfdb;
  }
}
.table-striped.table-mc-green > tbody > tr:nth-child(odd) > td,
.table-striped.table-mc-green > tbody > tr:nth-child(odd) > th {
  background-color: #d0f8ce;
}
.table-hover.table-mc-green > tbody > tr:hover > td,
.table-hover.table-mc-green > tbody > tr:hover > th {
  background-color: #a3e9a4;
}
@media screen and (max-width: 767px) {
  .table-responsive-vertical .table-striped.table-mc-green > tbody > tr > td,
  .table-responsive-vertical
    .table-striped.table-mc-green
    > tbody
    > tr:nth-child(odd) {
    background-color: #fff;
  }
  .table-responsive-vertical
    .table-striped.table-mc-green
    > tbody
    > tr
    > td:nth-child(odd) {
    background-color: #d0f8ce;
  }
  .table-responsive-vertical
    .table-hover.table-mc-green
    > tbody
    > tr:hover
    > td,
  .table-responsive-vertical .table-hover.table-mc-green > tbody > tr:hover {
    background-color: #fff;
  }
  .table-responsive-vertical
    .table-hover.table-mc-green
    > tbody
    > tr
    > td:hover {
    background-color: #a3e9a4;
  }
}
.table-striped.table-mc-light-green > tbody > tr:nth-child(odd) > td,
.table-striped.table-mc-light-green > tbody > tr:nth-child(odd) > th {
  background-color: #f1f8e9;
}
.table-hover.table-mc-light-green > tbody > tr:hover > td,
.table-hover.table-mc-light-green > tbody > tr:hover > th {
  background-color: #dcedc8;
}
@media screen and (max-width: 767px) {
  .table-responsive-vertical
    .table-striped.table-mc-light-green
    > tbody
    > tr
    > td,
  .table-responsive-vertical
    .table-striped.table-mc-light-green
    > tbody
    > tr:nth-child(odd) {
    background-color: #fff;
  }
  .table-responsive-vertical
    .table-striped.table-mc-light-green
    > tbody
    > tr
    > td:nth-child(odd) {
    background-color: #f1f8e9;
  }
  .table-responsive-vertical
    .table-hover.table-mc-light-green
    > tbody
    > tr:hover
    > td,
  .table-responsive-vertical
    .table-hover.table-mc-light-green
    > tbody
    > tr:hover {
    background-color: #fff;
  }
  .table-responsive-vertical
    .table-hover.table-mc-light-green
    > tbody
    > tr
    > td:hover {
    background-color: #dcedc8;
  }
}
.table-striped.table-mc-lime > tbody > tr:nth-child(odd) > td,
.table-striped.table-mc-lime > tbody > tr:nth-child(odd) > th {
  background-color: #f9fbe7;
}
.table-hover.table-mc-lime > tbody > tr:hover > td,
.table-hover.table-mc-lime > tbody > tr:hover > th {
  background-color: #f0f4c3;
}
@media screen and (max-width: 767px) {
  .table-responsive-vertical .table-striped.table-mc-lime > tbody > tr > td,
  .table-responsive-vertical
    .table-striped.table-mc-lime
    > tbody
    > tr:nth-child(odd) {
    background-color: #fff;
  }
  .table-responsive-vertical
    .table-striped.table-mc-lime
    > tbody
    > tr
    > td:nth-child(odd) {
    background-color: #f9fbe7;
  }
  .table-responsive-vertical .table-hover.table-mc-lime > tbody > tr:hover > td,
  .table-responsive-vertical .table-hover.table-mc-lime > tbody > tr:hover {
    background-color: #fff;
  }
  .table-responsive-vertical
    .table-hover.table-mc-lime
    > tbody
    > tr
    > td:hover {
    background-color: #f0f4c3;
  }
}
.table-striped.table-mc-yellow > tbody > tr:nth-child(odd) > td,
.table-striped.table-mc-yellow > tbody > tr:nth-child(odd) > th {
  background-color: #fffde7;
}
.table-hover.table-mc-yellow > tbody > tr:hover > td,
.table-hover.table-mc-yellow > tbody > tr:hover > th {
  background-color: #fff9c4;
}
@media screen and (max-width: 767px) {
  .table-responsive-vertical .table-striped.table-mc-yellow > tbody > tr > td,
  .table-responsive-vertical
    .table-striped.table-mc-yellow
    > tbody
    > tr:nth-child(odd) {
    background-color: #fff;
  }
  .table-responsive-vertical
    .table-striped.table-mc-yellow
    > tbody
    > tr
    > td:nth-child(odd) {
    background-color: #fffde7;
  }
  .table-responsive-vertical
    .table-hover.table-mc-yellow
    > tbody
    > tr:hover
    > td,
  .table-responsive-vertical .table-hover.table-mc-yellow > tbody > tr:hover {
    background-color: #fff;
  }
  .table-responsive-vertical
    .table-hover.table-mc-yellow
    > tbody
    > tr
    > td:hover {
    background-color: #fff9c4;
  }
}
.table-striped.table-mc-amber > tbody > tr:nth-child(odd) > td,
.table-striped.table-mc-amber > tbody > tr:nth-child(odd) > th {
  background-color: #fff8e1;
}
.table-hover.table-mc-amber > tbody > tr:hover > td,
.table-hover.table-mc-amber > tbody > tr:hover > th {
  background-color: #ffecb3;
}
@media screen and (max-width: 767px) {
  .table-responsive-vertical .table-striped.table-mc-amber > tbody > tr > td,
  .table-responsive-vertical
    .table-striped.table-mc-amber
    > tbody
    > tr:nth-child(odd) {
    background-color: #fff;
  }
  .table-responsive-vertical
    .table-striped.table-mc-amber
    > tbody
    > tr
    > td:nth-child(odd) {
    background-color: #fff8e1;
  }
  .table-responsive-vertical
    .table-hover.table-mc-amber
    > tbody
    > tr:hover
    > td,
  .table-responsive-vertical .table-hover.table-mc-amber > tbody > tr:hover {
    background-color: #fff;
  }
  .table-responsive-vertical
    .table-hover.table-mc-amber
    > tbody
    > tr
    > td:hover {
    background-color: #ffecb3;
  }
}
.table-striped.table-mc-orange > tbody > tr:nth-child(odd) > td,
.table-striped.table-mc-orange > tbody > tr:nth-child(odd) > th {
  background-color: #fff3e0;
}
.table-hover.table-mc-orange > tbody > tr:hover > td,
.table-hover.table-mc-orange > tbody > tr:hover > th {
  background-color: #ffe0b2;
}
@media screen and (max-width: 767px) {
  .table-responsive-vertical .table-striped.table-mc-orange > tbody > tr > td,
  .table-responsive-vertical
    .table-striped.table-mc-orange
    > tbody
    > tr:nth-child(odd) {
    background-color: #fff;
  }
  .table-responsive-vertical
    .table-striped.table-mc-orange
    > tbody
    > tr
    > td:nth-child(odd) {
    background-color: #fff3e0;
  }
  .table-responsive-vertical
    .table-hover.table-mc-orange
    > tbody
    > tr:hover
    > td,
  .table-responsive-vertical .table-hover.table-mc-orange > tbody > tr:hover {
    background-color: #fff;
  }
  .table-responsive-vertical
    .table-hover.table-mc-orange
    > tbody
    > tr
    > td:hover {
    background-color: #ffe0b2;
  }
}
.table-striped.table-mc-deep-orange > tbody > tr:nth-child(odd) > td,
.table-striped.table-mc-deep-orange > tbody > tr:nth-child(odd) > th {
  background-color: #fbe9e7;
}
.table-hover.table-mc-deep-orange > tbody > tr:hover > td,
.table-hover.table-mc-deep-orange > tbody > tr:hover > th {
  background-color: #ffccbc;
}
@media screen and (max-width: 767px) {
  .table-responsive-vertical
    .table-striped.table-mc-deep-orange
    > tbody
    > tr
    > td,
  .table-responsive-vertical
    .table-striped.table-mc-deep-orange
    > tbody
    > tr:nth-child(odd) {
    background-color: #fff;
  }
  .table-responsive-vertical
    .table-striped.table-mc-deep-orange
    > tbody
    > tr
    > td:nth-child(odd) {
    background-color: #fbe9e7;
  }
  .table-responsive-vertical
    .table-hover.table-mc-deep-orange
    > tbody
    > tr:hover
    > td,
  .table-responsive-vertical
    .table-hover.table-mc-deep-orange
    > tbody
    > tr:hover {
    background-color: #fff;
  }
  .table-responsive-vertical
    .table-hover.table-mc-deep-orange
    > tbody
    > tr
    > td:hover {
    background-color: #ffccbc;
  }
}
/* 나의 챌린지 목록 끝 */

/* 타이머 시작 */
.stopwatch {
  /* 타이머 전체 위치 */
  margin: 150px;
  padding: 0;
}

.clockwrapper {
  position: relative;
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 25px;
  border: 2px solid #ffe1c6;
  border-radius: 50%;
  width: 230px;
  height: 230px;
  color: #fd7577;
  font-size: 2em;
  margin-left: 65%;
  font-weight: 400;
}
.outerdot {
  position: absolute;
  left: -21px;
  top: -21px;
  width: 120%;
  height: 120%;
  border: 2px solid #fb9f4c;
  padding: 20px;
  border-radius: 50%;
}
.outerdot:after {
  position: relative;
  content: ".";
  color: #fd7577;
  font-size: 4em;
  top: -55px;
  left: 100px;
}
.outerdot.running {
  animation: 1s infinite linear seconds;
}
.outerdot.paused {
  animation-play-state: paused;
}
.btn_start,
.btn_end {
  font-weight: 700;
  transition: opacity 150ms ease-out;
  opacity: 1;
  background: #fb9f4c;
  border-radius: 20px;
  display: block;
  width: 120px;
  padding: 15px;
  color: white;
  margin: 1.5em;
  margin-top: 2.5em;
  text-align: center;
  text-transform: uppercase;
  cursor: pointer;
  margin-top: 160px;
}
.btn_end.stop {
  background: #fd7577;
}
.btn_end.disabled {
  opacity: 0;
}

/* 버튼 2개 컨트롤임 */
.controls {
  display: flex;
}
@-moz-keyframes seconds {
  from {
    transform: rotate(0deg);
  }
  to {
    transform: rotate(360deg);
  }
}
@-webkit-keyframes seconds {
  from {
    transform: rotate(0deg);
  }
  to {
    transform: rotate(360deg);
  }
}
@-o-keyframes seconds {
  from {
    transform: rotate(0deg);
  }
  to {
    transform: rotate(360deg);
  }
}
@keyframes seconds {
  from {
    transform: rotate(0deg);
  }
  to {
    transform: rotate(360deg);
  }
}

/* 타이머 끝 */

/* 스트릭 도전! */
.total_strick {
  height: 100px;
}
.parent_strick {
  height: 20px;
  display: flex;
}
.strick {
  width: 15px;
  height: 15px;
  background-color: white;
  border-radius: 4px;
  margin-right: 2px;
  box-shadow: 1px 2px 1px #dadada;
  margin-top: 5%;
}

.timeline {
  display: flex;
  margin-right: 500px;
}
/* 스트릭 끝 */

/* 운동 시작 */
.search {
  width: 500px;
  height: 50px;
}
.youtube_parent {
  display: flex;
  flex-direction: column;
  align-items: center;
}
/* 운동 끝 */

.title-text {
  margin-left: 11%;
  font-size: 20px;
  font-weight: 400;
  margin-top: 5%;
  margin-bottom: 2%;
  color: #535353;
}

.mainpage-body {
  width: 1200px;
  margin: auto;
}

.profile-user-name {
  font-size: 30px;
  font-weight: 600;
  color: #fb9f4c;
}

.profile-user-oneline {
  display: flex;
  text-align: center;
  margin-bottom: 5%;
  margin-top: 5%;
}

.profile-user-rank {
  width: 80px;
  margin-left: 2%;
  font-size: 20px;
  font-weight: 600;
  color: #fb9f4c;
}

.spacing-container {
  height: 50px;
}

.search-btn-self {
  font-weight: 700;
  transition: opacity 150ms ease-out;
  opacity: 1;
  background: #fb9f4c;
  border-radius: 0px 10px 10px 0px;
  display: block;
  width: 120px;
  height: 62px;
  padding: 15px;
  color: white;
  text-align: center;
  text-transform: uppercase;
  cursor: pointer;
  border: 0px;
}

.blood-sugar-btn {
  font-weight: 700;
  opacity: 1;
  background: #fb9f4c;
  border-radius: 10px 10px 10px 10px;
  display: block;
  width: 100px;
  height: 40px;
  padding: 15px;
  color: white;
  text-align: center;
  text-transform: uppercase;
  cursor: pointer;
  border: 0px;
  font-size: 16px;
}

.self-oneline {
  display: flex;
  align-items: center;
}

/* 블록 스트릭 색깔 */
.one_time {
  background-color: #ffcea4;
}
.two_time {
  background-color: #fb9f4c;
}
.three_time {
  background-color: #fd7577;
}

/* statistics start */
.graph {
  background-color: #ccc;
  height: 100vh;
  border-radius: 40px;
  margin: 50px;
}
@keyframes stack {
  0% {
    height: 0;
    opacity: 0;
  }
  100% {
    height: 75%;
    opacity: 1;
  }
}
@keyframes stack2 {
  0% {
    height: 0;
    opacity: 0;
  }
  100% {
    height: 60%;
    opacity: 1;
  }
}
@keyframes stack3 {
  0% {
    height: 0;
    opacity: 0;
  }
  100% {
    height: 25%;
    opacity: 1;
  }
}
section {
  display: flex;
  /*   flex-direction:column;   */
  justify-content: center;
  /* border: 2px solid red; */
  height: 500px;
  transform: rotate(180deg);
}

/* 그래프 */
.graph {
  height: 400px;
}

.graph span {
  display: flex;
  height: 40px;
  line-height: 40px;
  text-align: right;
  /*   width: 75%; */
  /*   background-color:pink; */
  border-radius: 40px;
  padding: 0 10px;
  box-sizing: border-box;
  color: white;
  transform: rotate(180deg);
}
/* .graph.stack1 span{
  background-color:#fb9f4c;
  height:75%;
  animation:stack 2s 1;
}
.graph.stack2 span{
  background-color:#fb9f4c;
  height:60%;
  animation:stack2 2s 1;
}
.graph.stack3 span{
  background-color:#fb9f4c;
  height:25%;
  animation:stack3 2s 1;
}
.graph.stack4 span{
  background-color:#fb9f4c;
  height:25%;
  animation:stack3 2s 1;
}
.graph.stack5 span{
  background-color:#fb9f4c;
  height:25%;
  animation:stack3 2s 1;
}
.graph.stack6 span{
  background-color:#fb9f4c;
  height:25%;
  animation:stack3 2s 1;
}
.graph.stack7 span{
  background-color:#fb9f4c;
  height:25%;
  animation:stack3 2s 1;
} */
/* statistics end */

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

::placeholder {
  padding-left: 2em;
  color: #fb9f4c;
}
</style>
