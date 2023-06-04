<template>
  <div>
    <!-- https://codepen.io/AbstractRef/pen/XavqLP -->
    <community-preview />
    <div style="width: 1000px; margin: auto">
      <div class="container">
        <div class="table-wrapper" style="margin-top: 10em">
          <div class="table-title">
            <div class="row">
              <div class="col-sm-6" style="display: flex">
                <div style="text-align: center; width: 50%">
                  <button class="self-on-board">정보게시판</button>
                </div>
                <div style="text-align: center; width: 50%">
                  <button class="self-off-board">자유게시판</button>
                </div>
              </div>
            </div>
          </div>
          <table class="table table-striped table-hover" style="margin: auto">
            <thead>
              <tr>
                <th>아이디</th>
                <th>제목</th>
                <th>날짜</th>
                <th>조회수</th>
                <th>
                  <router-link to="/community/write">
                    <button class="write-button-ddoitnae-top">글쓰기</button>
                  </router-link>
                </th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="(board,index) in boards" :key="index">
                <td>{{board.users_id}}</td>
                <router-link :to="`/community/detail/${board.no}`"><td>{{board.title}}</td></router-link>
                <td>{{board.write_date}}</td>
                <td>{{board.view_count}}</td>
                <td><button v-if="loginUser.id==board.users_id" class="write-button-ddoitnae"><router-link to="/community/modify">수정</router-link></button></td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>
    </div>

    <!-- 페이지네이션일 경우 -->

    <!-- 인기글리스트가 보입니다. -->

    <!-- 전체글 리스트가 보입니다. -->

    <!-- 검색기능이 보입니다. -->
    <main-footer />
  </div>

  <!-- 스크롤일 경우 -->
  <!-- <router-view />
    <community-header /> -->

  <!-- 인기글리스트가 보입니다. -->
  <!-- <community-preview/> -->

  <!-- 검색기능이 보입니다. -->

  <!-- 전체글 리스트가 보입니다. -->
</template>

<script scoped>
import {mapGetters} from "vuex";
import CommunityPreview from "../../components/community/CommunityPreview.vue";
import $ from "jquery";
import MainFooter from "../../components/common/MainFooter.vue";
export default {
  components: { CommunityPreview, MainFooter },
  name: "CommunityBoardMain",
  created(){
    this.$store.dispatch("getBoards");
  },

  computed:{
    ...mapGetters(["boards"]),
    ...mapGetters(["loginUser"]),
  },
  mounted() {
    $(document).ready(function () {
      // Activate tooltip
      $('[data-toggle="tooltip"]').tooltip();

      // Select/Deselect checkboxes
      var checkbox = $('table tbody input[type="checkbox"]');
      $("#selectAll").click(function () {
        if (this.checked) {
          checkbox.each(function () {
            this.checked = true;
          });
        } else {
          checkbox.each(function () {
            this.checked = false;
          });
        }
      });
      checkbox.click(function () {
        if (!this.checked) {
          $("#selectAll").prop("checked", false);
        }
      });
    });
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
.table-wrapper {
  background: #fff;
  padding: 20px 25px;
  margin: 30px 0;
  border-radius: 3px;
  box-shadow: 0 1px 1px rgba(0, 0, 0, 0.05);
}
.table-title {
  padding-bottom: 15px;
  background: white;
  color: #fff;
  padding: 16px 30px;
  margin: -20px -25px 10px;
  border-radius: 3px 3px 0 0;
}
.table-title h2 {
  margin: 5px 0 0;
  font-size: 24px;
}
.table-title .btn-group {
  float: right;
}

.table-title .btn i {
  float: left;
  font-size: 21px;
  margin-right: 5px;
}
.table-title .btn span {
  float: left;
  margin-top: 2px;
}
table.table tr th,
table.table tr td {
  border-color: #e9e9e9;
  padding: 25px 15px;
  vertical-align: middle;
}
table.table tr th:first-child {
  width: 20%;
}
table.table tr th:last-child {
  width: 10%;
}
table.table-striped tbody tr:nth-of-type(odd) {
  background-color: #fcfcfc;
}
table.table-striped.table-hover tbody tr:hover {
  background: #f5f5f5;
}
table.table th i {
  font-size: 13px;
  margin: 0 5px;
  cursor: pointer;
}
table.table td:last-child i {
  opacity: 0.9;
  font-size: 22px;
  margin: 0 5px;
}

table.table td a:hover {
  color: #2196f3;
}
table.table td a.edit {
  color: #ffc107;
}
table.table td a.delete {
  color: #f44336;
}
table.table td i {
  font-size: 19px;
}
table.table .avatar {
  border-radius: 50%;
  vertical-align: middle;
  margin-right: 10px;
}
.pagination {
  float: right;
  margin: 0 0 5px;
}

.pagination li a:hover {
  color: #666;
}
.pagination li.active a,
.pagination li.active a.page-link {
  background: #03a9f4;
}
.pagination li.active a:hover {
  background: #0397d6;
}
.pagination li.disabled i {
  color: #ccc;
}
.pagination li i {
  font-size: 16px;
  padding-top: 6px;
}
.hint-text {
  float: left;
  margin-top: 10px;
  font-size: 13px;
}

/* 내꺼.. */

.self-on-board {
  color: #fb9f4c;
  width: 10em;
  height: 3.5em;
  border: none;
  background: none;
  font-size: 2em;
  font-weight: 600;
}

.self-off-board {
  color: #ffcea4;
  width: 10em;
  height: 3.5em;
  border: none;
  background: none;
  font-size: 2em;
  font-weight: 600;
}

.write-button-ddoitnae {
  width: 7rem;
  height: 3rem;
  border-radius: 10px;
  border: 1px solid #bcbcbc;
  background: white;
  color: #fb9f4c ;
}

.write-button-ddoitnae-top {
  width: 7rem;
  height: 3rem;
  border-radius: 10px;
  border: 1px solid #fb9f4c;
  background: #fb9f4c;
  color: white;
}
</style>
