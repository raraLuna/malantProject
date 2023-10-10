/*resources/js/scroll_behavior.js*/

$(document).ready(function () {
  var sidebar = $(".sidebar");
  var offsetTop = sidebar.offset().top;

  $(window).scroll(function () {
    var scrollTop = $(window).scrollTop();
    if (scrollTop >= offsetTop) {
      sidebar.addClass("fixed-sidebar");
      $(".main-content").addClass("scrolling"); // 스크롤이 발생하면 scrolling 클래스 추가
    } else {
      sidebar.removeClass("fixed-sidebar");
      $(".main-content").removeClass("scrolling"); // 스크롤이 없으면 scrolling 클래스 제거
    }
  });
});
