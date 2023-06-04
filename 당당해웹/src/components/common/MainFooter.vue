<template>
  <!-- https://codepen.io/scanfcode/pen/MEZPNd -->
  <div>
    <div class="footer-top-margin">
      <!-- Site footer -->
      <footer class="site-footer">
        <div class="container">
          <div class="row">
            <div class="col-sm-12 col-md-6">
              <h6>About</h6>
              <p class="text-justify" style="color:#737373; font-size:0.8em;">
                 안녕하세요. 당당해입니다. 당당해는 젊은 당뇨를 앓고 있는 사람들을 위한 솔루션입니다. 저희의 프로젝트를 어여삐 봐주시면 감사하겠습니다. <br> 하루 세번 아침 점심 저녁 30분씩 운동하시고 당뇨를 함께 이겨냅시다! <br> 옆에서 산하가 하품하네요. "산하: 하품이 산하하네요" 참고로 지은도 졸립니다. 프로젝트 무사히 마쳐줘주라!
              </p>
            </div>
            
          </div>
          <hr style="margin-top:2em;margin-bottom:2em;" />
        </div>
        <div class="container">
          <div class="row">
            <div class="col-md-8 col-sm-6 col-xs-12">
              <p class="copyright-text" style="color:#737373; font-size:0.8em;">
                Copyright &copy; 2023 All Rights Reserved by
                <router-link to="/">ShinCha</router-link>.
              </p>
            </div>

            <!-- <div class="col-md-4 col-sm-6 col-xs-12">
            <ul class="social-icons">
              <li><a class="facebook" href="#"><i class="fa fa-facebook"></i></a></li>
              <li><a class="twitter" href="#"><i class="fa fa-twitter"></i></a></li>
              <li><a class="dribbble" href="#"><i class="fa fa-dribbble"></i></a></li>
              <li><a class="linkedin" href="#"><i class="fa fa-linkedin"></i></a></li>   
            </ul>
          </div> -->
          </div>
        </div>
      </footer>
    </div>
  </div>
</template>

<script>
export default {
  name: "MainFooter",
};
</script>

<style>
.site-footer {
  background-color: #f5f1ed;
  padding: 45px 0 20px;
  font-size: 15px;
  line-height: 24px;
  color: #737373;
}
.site-footer hr {
  border-top-color: #bbb;
  opacity: 0.5;
}
.site-footer hr.small {
  margin: 20px 0;
}
.site-footer h6 {
  color: #918686;
  font-size: 16px;
  text-transform: uppercase;
  margin-top: 5px;
  letter-spacing: 2px;
}
.site-footer a {
  color: #737373;
}
.site-footer a:hover {
  color: #ffcea4;
  text-decoration: none;
}
.footer-links {
  padding-left: 0;
  list-style: none;
}
.footer-links li {
  display: block;
}
.footer-links a {
  color: #737373;
}
.footer-links a:active,
.footer-links a:focus,
.footer-links a:hover {
  color: #ffcea4;
  text-decoration: none;
}
.footer-links.inline li {
  display: inline-block;
}
.site-footer .social-icons {
  text-align: right;
}
.site-footer .social-icons a {
  width: 40px;
  height: 40px;
  line-height: 40px;
  margin-left: 6px;
  margin-right: 0;
  border-radius: 100%;
  background-color: #d3c8c1;
}
.copyright-text {
  margin: 0;
}
@media (max-width: 991px) {
  .site-footer [class^="col-"] {
    margin-bottom: 30px;
  }
}
@media (max-width: 767px) {
  .site-footer {
    padding-bottom: 0;
  }
  .site-footer .copyright-text,
  .site-footer .social-icons {
    text-align: center;
  }
}
.social-icons {
  padding-left: 0;
  margin-bottom: 0;
  list-style: none;
}
.social-icons li {
  display: inline-block;
  margin-bottom: 4px;
}
.social-icons li.title {
  margin-right: 15px;
  text-transform: uppercase;
  color: #96a2b2;
  font-weight: 700;
  font-size: 13px;
}
.social-icons a {
  background-color: #eceeef;
  color: #818a91;
  font-size: 16px;
  display: inline-block;
  line-height: 44px;
  width: 44px;
  height: 44px;
  text-align: center;
  margin-right: 8px;
  border-radius: 100%;
  -webkit-transition: all 0.2s linear;
  -o-transition: all 0.2s linear;
  transition: all 0.2s linear;
}
.social-icons a:active,
.social-icons a:focus,
.social-icons a:hover {
  color: #fff;
  background-color: #ffcea4;
}
.social-icons.size-sm a {
  line-height: 34px;
  height: 34px;
  width: 34px;
  font-size: 14px;
}
.social-icons a.facebook:hover {
  background-color: #e4dedb;
}
.social-icons a.twitter:hover {
  background-color: #bdb0ab;
}
.social-icons a.linkedin:hover {
  background-color: #bdb0ab;
}
.social-icons a.dribbble:hover {
  background-color: #bdb0ab;
}
@media (max-width: 767px) {
  .social-icons li.title {
    display: block;
    margin-right: 0;
    font-weight: 600;
  }
}

.footer-top-margin{
  margin-top:15em;
}

</style>
