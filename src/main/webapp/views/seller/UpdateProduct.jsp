<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="store.product.model.vo.ProductDetail, java.util.ArrayList, member.model.vo.Member"%>
<%
	ArrayList<ProductDetail> sellplist = (ArrayList<ProductDetail>)session.getAttribute("sellplist"); 
 %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 등록 페이지</title>
<style>
  body {
    margin: 0;
    font-family: Arial, sans-serif;
    background-color: #f0f0f0;
  }

  .container {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
  }

  .content-box {
    background-color: #fff;
    padding: 20px;
    border-radius: 10px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    width: 800px;
    max-width: 100%;
  }

  .dropdown {
    width: 100%;
    padding: 10px;
    border: 1px solid #ccc;
    border-radius: 5px;
    margin-bottom: 10px;
  }

  input[type="text"],
  input[type="file"],
  textarea {
    width: 100%;
    padding: 10px;
    margin-bottom: 10px;
    border: 1px solid #ccc;
    border-radius: 5px;
  }

  input[type="submit"] {
    background-color: #007BFF;
    color: #fff;
    border: none;
    padding: 15px 40px;
    border-radius: 5px;
    cursor: pointer;
    transition: background-color 0.3s;
    font-size: 16px;
    font-weight: bold;
  }

  input[type="submit"]:hover {
    background-color: #0056b3;
  }

  .left-section {
    text-align: center;
    margin-top: 20px;
    float: left;
    width: 45%;
    padding: 10px;
    box-sizing: border-box;
  }

  .right-section {
    float: right;
    width: 45%;
    padding: 10px;
    box-sizing: border-box;
  }

  #thumbnail_preview {
    max-width: 100px;
    display: none;
  }

  #detail_images_preview {
    max-width: 100px;
    display: none;
    margin-top: 10px;
  }
</style>
</head>
<body>
<div class="container">
    <div class="content-box">
        <form action="/malant/sellpinsert" method="post" enctype="multipart/form-data">
            <div class="left-section">
                <select id="dropdown1" class="dropdown" onchange="populateDropdown1()" name="option1">
                    <option value="옵션미선택">옵션을 선택해 주세요</option>
                    <option value="식물">식물</option>
                    <option value="화분">화분</option>
                    <option value="자갈/모래/흙">자갈/모래/흙</option>
                    <option value="영양제/비료">영양제/비료</option>
                    <option value="식물조명">식물조명</option>
                    <option value="기타">기타</option>
                </select>
                <select id="dropdown2" class="dropdown" name="option2"></select>
                <select id="dropdown3" class="dropdown" name="option3"></select>
                <select id="dropdown4" class="dropdown" name="option4"></select>
                <select id="dropdown5" class="dropdown" name="option5"></select>
                <select id="dropdown6" class="dropdown" name="option6"></select>
                <select id="dropdown7" class="dropdown" name="option7"></select>
                <select id="dropdown8" class="dropdown" name="option8"></select>
            </div>

            <div class="right-section">
                <label for="product_name">상품명:</label>
                <input type="text" id="product_name" name="product_name"><br>

                <label for="store_name">상점명:</label>
                <input type="text" id="store_name" name="store_name"><br>

                <label for="price">가격:</label>
                <input type="text" id="price" name="price"><br>

                <label for="shipping_cost">배송비:</label>
                <input type="text" id="shipping_cost" name="shipping_cost"><br>

                <label for="thumbnail_image">대표이미지 (썸네일):</label>
                <input type="file" id="thumbnail_image" name="thumbnail_image"><br>
                <img id="thumbnail_preview" src="#" alt="대표 이미지 미리 보기">

                <label for="detail_images">세부이미지 (세부설명이 들어있는 이미지):</label>
                <input type="file" id="detail_images" name="detail_images"><br>
                <div id="detail_images_preview">
                 
                </div>

                <label for="detail_description">세부내용 (텍스트):</label><br>
                <textarea id="detail_description" name="detail_description" rows="4" cols="50"></textarea>
            </div>

            <div style="clear: both;"></div>
			<input type="hidden" name="sellerNo" value="<%= sellplist.get(0).getSellerNo() %>">
            <div class="left-section">
                <input type="submit" value="등록">
            </div>
        </form>
    </div>
</div>


<script>
//1번 옵션
function populateDropdown1() {
	console.log("Dropdown 1이 변경되었습니다.")
	var dropdown1 = document.getElementById("dropdown1");
	var selectedOption = dropdown1.options[dropdown1.selectedIndex].value;

	// 드롭다운 메뉴 초기화
	var dropdown2 = document.getElementById("dropdown2");
	dropdown2.innerHTML = "";

	var dropdown3 = document.getElementById("dropdown3");
	dropdown3.innerHTML = "";

	var dropdown4 = document.getElementById("dropdown4");
	dropdown4.innerHTML = "";

	var dropdown5 = document.getElementById("dropdown5");
	dropdown5.innerHTML = "";

	var dropdown6 = document.getElementById("dropdown6");
	dropdown6.innerHTML = "";

	var dropdown7 = document.getElementById("dropdown7");
	dropdown7.innerHTML = "";

	var dropdown8 = document.getElementById("dropdown8");
	dropdown8.innerHTML = "";

	// 2~8번 드롭다운 메뉴의 옵션을 설정
	var options2 = [];
	var options3 = [];
	var options4 = [];
	var options5 = [];
	var options6 = [];
	var options7 = [];
	var options8 = [];
	if (selectedOption === "식물") {
		options2 = [ "난이도", "초보자", "경험자", "전문가" ];
		options3 = [ "일조량", "직사광선", "간접광선" ];
		options4 = [ "습도", "습한환경", "보통", "건조한환경" ];
		options5 = [ "정화능력", "정화능력있음", "정화능력없음" ]
		options6 = [ "가습효과", "가습효과있음", "가습효과없음" ]
		options7 = [ "크기", "소형", "중형", "대형" ]
		options8 = [ "용도", "관상용", "식용", "조경용" ]
	} else if (selectedOption === "화분") {
		options2 = [ "재질", "세라믹", "도자기", "플라스틱", "대리석", "시멘트", "고무" ];
		options3 = [ "크기", "소형", "중형", "대형", "특대" ];
	} else if (selectedOption === "자갈/모래/흙") {
		options2 = [ "종류", "자연토양", "인공토양" ];
	}

	// 옵션에 추가
	for (var i = 0; i < options2.length; i++) {
		var option = document.createElement("option");
		option.value = options2[i];
		option.text = options2[i];
		dropdown2.appendChild(option);
	}
	for (var i = 0; i < options3.length; i++) {
		var option = document.createElement("option");
		option.value = options3[i];
		option.text = options3[i];
		dropdown3.appendChild(option);
	}
	for (var i = 0; i < options4.length; i++) {
		var option = document.createElement("option");
		option.value = options4[i];
		option.text = options4[i];
		dropdown4.appendChild(option);
	}
	for (var i = 0; i < options5.length; i++) {
		var option = document.createElement("option");
		option.value = options5[i];
		option.text = options5[i];
		dropdown5.appendChild(option);
	}
	for (var i = 0; i < options6.length; i++) {
		var option = document.createElement("option");
		option.value = options6[i];
		option.text = options6[i];
		dropdown6.appendChild(option);
	}
	for (var i = 0; i < options7.length; i++) {
		var option = document.createElement("option");
		option.value = options7[i];
		option.text = options7[i];
		dropdown7.appendChild(option);
	}
	for (var i = 0; i < options8.length; i++) {
		var option = document.createElement("option");
		option.value = options8[i];
		option.text = options8[i];
		dropdown8.appendChild(option);
	}

	// 첫 번째 옵션이 선택되었을 때만 옵션들 표시
	if (selectedOption !== "전체") {
		dropdown2.style.display = "inline";
		dropdown3.style.display = "inline";
		dropdown4.style.display = "inline";
		dropdown5.style.display = "inline";
		dropdown6.style.display = "inline";
		dropdown7.style.display = "inline";
		dropdown8.style.display = "inline";
	} else {
		dropdown2.style.display = "none";
		dropdown3.style.display = "none";
		dropdown4.style.display = "none";
		dropdown5.style.display = "none";
		dropdown6.style.display = "none";
		dropdown7.style.display = "none";
		dropdown8.style.display = "none";
	}
}

document.getElementById("dropdown1").addEventListener("change", function() {
	populateDropdown1();
});

// 초기화
populateDropdown1();


// 대표 이미지 미리 보기 기능
var thumbnailImageInput = document.getElementById("thumbnail_image");
var thumbnailPreview = document.getElementById("thumbnail_preview");

thumbnailImageInput.addEventListener("change", function () {
    var file = thumbnailImageInput.files[0];
    if (file) {
        var reader = new FileReader();
        reader.onload = function (e) {
            thumbnailPreview.style.display = "block";
            thumbnailPreview.src = e.target.result;
        };
        reader.readAsDataURL(file);
    } else {
        thumbnailPreview.style.display = "none";
    }
});

// 세부 이미지 미리 보기 기능
var detailImagesInput = document.getElementById("detail_images");
var detailImagesPreview = document.getElementById("detail_images_preview");

detailImagesInput.addEventListener("change", function () {
    var files = detailImagesInput.files;
    if (files.length > 0) {
        detailImagesPreview.style.display = "block";
        detailImagesPreview.innerHTML = ""; // 기존의 미리 보기 내용을 초기화

        for (var i = 0; i < files.length; i++) {
            var file = files[i];
            var image = document.createElement("img");
            image.style.maxWidth = "100%";
            image.style.marginBottom = "10px";

            var reader = new FileReader();
            reader.onload = (function (img) {
                return function (e) {
                    img.src = e.target.result;
                };
            })(image);

            reader.readAsDataURL(file);
            detailImagesPreview.appendChild(image); // 미리 보기 영역에 이미지 추가
        }
    } else {
        detailImagesPreview.style.display = "none";
    }
});

</script>

</body>
</html>