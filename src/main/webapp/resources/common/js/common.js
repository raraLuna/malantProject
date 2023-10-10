document.addEventListener("DOMContentLoaded", function () {
  var header = document.querySelector("#header_container");
  fetch("views/common/sidebar.html")
    .then((res) => res.text())
    .then((data) => (header.innerHTML = data))
    .then(function (response) {
      console.log("response : " + response);
    });
});
