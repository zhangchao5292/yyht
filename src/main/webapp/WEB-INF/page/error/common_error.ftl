<#import "../base/head.ftl" as jksk>
<#import "../base/nav.ftl" as nav>
<#import "../base/top.ftl" as top>
<#import "../base/footer.ftl" as footer>
<@jksk.htmlHead cssList="select2_metro.css,DT_bootstrap.css">
</@jksk.htmlHead>
<@nav.leftNav index=82>
</@nav.leftNav>
<@top.htmlTop>
</@top.htmlTop>



<style type="text/css">
  #wrapper1 a,fieldset,img{border:0;}
  #wrapper1 a{color:#221919;text-decoration:none;outline:none;}
  #wrapper1 a:hover{color:#3366cc;text-decoration:underline;}
  #wrapper1 h1,#wrapper p{-webkit-transition:opacity 0.5s ease-in-out;-moz-transition:opacity 0.5s ease-in-out;transition:opacity 0.5s ease-in-out;}
  #wrapper1{font-size:24px;color:#B7AEB4;text-align:center;margin:100px auto;width:594px;}
  #wrapper1 .link{border:0; background:#1AB394;; color:#fff;padding:10px 30px;border-radius: 8px;}
  #wrapper1 .link:hover{background:#40E0D0;}
  #wrapper1 h1{text-shadow:0px 1px 2px white;font-size:24px;opacity:0;}
  #wrapper1 img{-webkit-transition:opacity 1s ease-in-out;-moz-transition:opacity 1s ease-in-out;transition:opacity 1s ease-in-out;height:202px;width:199px;opacity:0;}
  #wrapper1 p{text-shadow:0px 1px 2px white;font-weight:normal;font-weight:200;opacity:0;}
  #wrapper1 .fade{opacity:1;}
  @media only screen and (min-device-width:320px) and (max-device-width:480px){
      #wrapper1{margin:40px auto;text-align:center;width:280px;}
  }
 </style>
<div class="wrapper wrapper-content animated fadeInRight">
	<div class="row">
    <div class="col-lg-12">
       <div id="wrapper1">
        <a href="javascript:;"><img class="fade" src="${base}/resources/img/500_icon.png"></a>
        <div>
          <h1 class="fade">Error</h1>
          <h1 class="fade">温馨提示：系统出现问题了！</h1>
          <p class="fade">你正在寻找的页面无法找到。</p>
          <button class="link" onclick="history.go(-1)">返回</button>
        </div>
      </div>
	  </div> 
  </div>
</div>


<!-- END PAGE -->
<!-- END CONTAINER -->
<!-- BEGIN FOOTER -->
<@footer.foot>
</@footer.foot>

<!-- END FOOTER -->

</body>

<!-- END BODY -->

</html>