

//******************************************************************************************** */


//반려식물 등록창 팝업
document.querySelector("#addYourPlant").addEventListener("click", function() {
	document.querySelector(".myplantAdd").classList.add("active");
});
//반려식물 등록창 닫음(취소)
document.querySelector(".myplantAdd .close-btn").addEventListener("click", function() {
	document.querySelector(".myplantAdd").classList.remove("active");
});

//반려식물 등록창 저장 후 닫음
document.querySelector(".myplantAdd .save-close-btn").addEventListener("click", function() {
	document.querySelector(".myplantAdd").classList.remove("active");
});

//******************************************************************************************** */
