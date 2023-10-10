

//일기 등록창 열기
document.querySelector("#newDiaryGo").addEventListener("click", function() {
	document.querySelector(".newDiaryBox").classList.add("active");
});

//일기 등록창 닫음(취소)
document.querySelector(".newDiaryBox .close-btn").addEventListener("click", function() {
	document.querySelector(".newDiaryBox").classList.remove("active");
});

//일기 등록창 저장 후 닫음
document.querySelector(".newDiaryBox .save-close-btn").addEventListener("click", function() {
	document.querySelector(".newDiaryBox").classList.remove("active");
});