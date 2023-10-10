//resources/js/scroll_loading.js

$(document).ready(function () {
  var sidebar = $(".sidebar");
  var offsetTop = sidebar.offset().top;

  var productsContainer = $(".best-sellers");
  var isLoading = false;
  var loadedProducts = 0;

  function loadMoreProducts() {
    if (!isLoading) {
      isLoading = true;

      // 추가할 제품 정보를 선택
      var additionalProducts = productsContainer
        .find(".product")
        .slice(loadedProducts, loadedProducts + 4);

      // 선택한 제품 정보를 컨테이너에 추가
      productsContainer.append(additionalProducts);

      // 서서히 나타나는 효과를 위해 클래스 추가
      additionalProducts.addClass("visible");

      isLoading = false;
      loadedProducts += additionalProducts.length;
    }
  }

  loadMoreProducts();

  $(window).scroll(function () {
    var scrollTop = $(window).scrollTop();
    var windowHeight = $(window).height();
    var documentHeight = $(document).height();

    if (scrollTop + windowHeight >= documentHeight - 100) {
      loadMoreProducts();
    }

    if (scrollTop >= offsetTop) {
      sidebar.addClass("fixed-sidebar");
      $(".main-content").addClass("scrolling");
    } else {
      sidebar.removeClass("fixed-sidebar");
      $(".main-content").removeClass("scrolling");
    }
  });
});
