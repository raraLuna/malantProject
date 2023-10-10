/* 테이블 제거 */
DROP TABLE "MB_SOCIAL" CASCADE CONSTRAINTS;
DROP TABLE "MB_WITHDRAWAL" CASCADE CONSTRAINTS;
DROP TABLE "MB_DORMANT" CASCADE CONSTRAINTS;
DROP TABLE "MEMBER" CASCADE CONSTRAINTS;
DROP TABLE "CUSTOMER_SERVICE" CASCADE CONSTRAINTS;
DROP TABLE "CM_BOARD" CASCADE CONSTRAINTS;
DROP TABLE "CM_COMMENT" CASCADE CONSTRAINTS;
DROP TABLE "NOTICE" CASCADE CONSTRAINTS;
DROP TABLE "CM_HASHTAG" CASCADE CONSTRAINTS;
DROP TABLE "ST_PRODUCT" CASCADE CONSTRAINTS;
DROP TABLE "ST_CATEGORY" CASCADE CONSTRAINTS;
DROP TABLE "ST_ORDER" CASCADE CONSTRAINTS;
DROP TABLE "MY_CALENDAR" CASCADE CONSTRAINTS;
DROP TABLE "MY_PLANT" CASCADE CONSTRAINTS;
DROP TABLE "MY_DIARY" CASCADE CONSTRAINTS;
DROP TABLE "ARBORETUM" CASCADE CONSTRAINTS;
DROP TABLE "SEARCH_HISTORY" CASCADE CONSTRAINTS;
DROP TABLE "PLANT_INFO" CASCADE CONSTRAINTS;
DROP TABLE "MB_REPORTED" CASCADE CONSTRAINTS;
DROP TABLE "CM_BOARD_HASHTAG" CASCADE CONSTRAINTS;
DROP TABLE "ADMIN" CASCADE CONSTRAINTS;
DROP TABLE "CM_BOARD_LIKE" CASCADE CONSTRAINTS;
DROP TABLE "ST_PROD_CATE" CASCADE CONSTRAINTS;
DROP TABLE "AR_EDU_PROGRAM" CASCADE CONSTRAINTS;
DROP TABLE "CM_COMMENT_LIKE" CASCADE CONSTRAINTS;
DROP TABLE "ST_SELLER" CASCADE CONSTRAINTS;

/* 탈퇴회원 */
CREATE TABLE mb_withdrawal (
	user_no VARCHAR2(10) NOT NULL, /* 회원고유식별번호 */
	deactivation_date DATE NOT NULL, /* 회원탈퇴 날짜 */
	data_deletion_date DATE NOT NULL /* 정보 완전삭제일 */
);

CREATE UNIQUE INDEX PK_mb_withdrawal
	ON mb_withdrawal (
		user_no ASC
	);

ALTER TABLE mb_withdrawal
	ADD
		CONSTRAINT PK_mb_withdrawal
		PRIMARY KEY (
			user_no
		);

/* 소셜 로그인 */
CREATE TABLE mb_social (
	social_id VARCHAR2(30) NOT NULL, /* 소셜 제공 식별자 */
	user_no VARCHAR2(10) NOT NULL, /* 회원고유식별번호 */
	social_type VARCHAR2(15) NOT NULL, /* 소셜 로그인 유형 */
	email VARCHAR2(255) NOT NULL, /* 이메일 */
	nickname VARCHAR2(24) NOT NULL, /* 닉네임 */
	profile_image VARCHAR2(255), /* 프로필사진url */
	last_login_date DATE DEFAULT SYSDATE NOT NULL /* 마지막 접속일 */
);

CREATE UNIQUE INDEX PK_mb_social
	ON mb_social (
		social_id ASC
	);

ALTER TABLE mb_social
	ADD
		CONSTRAINT PK_mb_social
		PRIMARY KEY (
			social_id
		);

/* 휴면회원 */
CREATE TABLE mb_dormant (
	user_no VARCHAR2(10) NOT NULL, /* 회원고유식별번호 */
	dormant_date DATE NOT NULL, /* 휴면 전환일 */
	reactivation_date DATE /* 휴면 해제일 */
);

CREATE UNIQUE INDEX PK_mb_dormant
	ON mb_dormant (
		user_no ASC
	);

ALTER TABLE mb_dormant
	ADD
		CONSTRAINT PK_mb_dormant
		PRIMARY KEY (
			user_no
		);

/* 회원 */
CREATE TABLE member (
	user_no VARCHAR2(10) NOT NULL, /* 회원고유식별번호 */
	user_id VARCHAR2(12) UNIQUE NOT NULL, /* 아이디 */
	user_pwd VARCHAR2(64), /* 비밀번호 */
	nickname VARCHAR2(24) NOT NULL, /* 닉네임 */
	email VARCHAR2(255) NOT NULL, /* 이메일 */
	profile_img VARCHAR2(255), /* 프로필사진 */
	user_name VARCHAR2(15), /* 이름 */
	phone VARCHAR2(13), /* 휴대폰 번호 */
	address VARCHAR2(255), /* 주소 */
	sign_type VARCHAR2(15) DEFAULT 'COMMON' NOT NULL, /* 로그인 유형 */
	password_update_date DATE DEFAULT SYSDATE, /* 비밀번호 업데이트 날짜 */
	alarm_yn CHAR(1) DEFAULT 'Y' NOT NULL, /* 식물 관리 알림 이메일 수신 여부 */
	notice_yn CHAR(1) DEFAULT 'Y' NOT NULL, /* 광고 관련 이메일 수신 여부 */
	blocked_yn CHAR(1) DEFAULT 'N' NOT NULL, /* 로그인 제한 여부 */
	dormant_yn CHAR(1) DEFAULT 'N' NOT NULL, /* 휴면여부 */
	withdrawal_yn CHAR(1) DEFAULT 'N' NOT NULL, /* 탈퇴여부 */
	created_date DATE DEFAULT SYSDATE NOT NULL, /* 회원가입일 */
	last_login_date DATE DEFAULT SYSDATE NOT NULL /* 마지막 접속일 */
);

CREATE UNIQUE INDEX PK_member
	ON member (
		user_no ASC
	);

CREATE UNIQUE INDEX UIX_member
	ON member (
		user_id ASC,
		email ASC,
		phone ASC
	);

ALTER TABLE member
	ADD
		CONSTRAINT PK_member
		PRIMARY KEY (
			user_no
		);

ALTER TABLE member
	ADD
		CONSTRAINT UK_member
		UNIQUE (
			user_id,
			email,
			phone
		);

/* 고객센터 */
CREATE TABLE customer_service (
	inquiry_no NUMBER NOT NULL, /* 문의 접수번호 */
	inquirer_no VARCHAR2(10) NOT NULL, /* 문의회원 번호 */
	inquirer_id VARCHAR2(10) NOT NULL, /* 문의회원 아이디 */
	inquirer_email VARCHAR2(255) NOT NULL, /* 문의회원 이메일 */
	inquiry_date DATE DEFAULT SYSDATE NOT NULL, /* 문의 일자 */
	inquiry_type VARCHAR2(15) NOT NULL, /* 문의 유형 */
	inquiry_title VARCHAR2(90) NOT NULL, /* 문의 제목 */
	inquiry_content VARCHAR2(3000) NOT NULL, /* 문의 내용 */
	inquiry_path VARCHAR2(255) NOT NULL, /* 문의 경로 */
	replier_no VARCHAR2(10), /* 담당자 번호 */
	replier_name VARCHAR2(10), /* 담당자 이름 */
	reply_content VARCHAR2(3000), /* 답변 내용 */
	status VARCHAR2(15) DEFAULT '접수대기' NOT NULL, /* 처리 상태 */
	completion_date DATE /* 처리 완료일 */
);

CREATE UNIQUE INDEX PK_customer_service
	ON customer_service (
		inquiry_no ASC
	);

ALTER TABLE customer_service
	ADD
		CONSTRAINT PK_customer_service
		PRIMARY KEY (
			inquiry_no
		);

/* 게시글 */
CREATE TABLE cm_board (
	board_no NUMBER NOT NULL, /* 게시글 식별번호 */
	user_no VARCHAR2(10) NOT NULL, /* 회원고유식별번호 */
	nickname VARCHAR2(24) NOT NULL, /* 닉네임 */
	board_title VARCHAR2(90) NOT NULL, /* 게시글제목 */
	board_content VARCHAR2(900) NOT NULL, /* 게시글내용 */
	board_like NUMBER DEFAULT 0 NOT NULL, /* 게시글좋아요 수 */
	board_date DATE DEFAULT SYSDATE NOT NULL, /* 게시글등록일 */
	board_photo VARCHAR2(255), /* 사진 */
	view_count NUMBER DEFAULT 0 NOT NULL, /* 조회수 */
	reported_yn CHAR(1) DEFAULT 'N' NOT NULL /* 신고여부 */
);

CREATE UNIQUE INDEX PK_cm_board
	ON cm_board (
		board_no ASC
	);

ALTER TABLE cm_board
	ADD
		CONSTRAINT PK_cm_board
		PRIMARY KEY (
			board_no
		);

/* 댓글 */
CREATE TABLE cm_comment (
	board_no NUMBER NOT NULL, /* 게시글 식별번호 */
	comment_no NUMBER NOT NULL, /* 댓글번호 */
	user_no VARCHAR2(10) NOT NULL, /* 댓글회원 */
	nickname VARCHAR2(24) NOT NULL, /* 댓글닉네임 */
	comment_like NUMBER DEFAULT 0 NOT NULL, /* 댓글좋아요 */
	comment_content VARCHAR2(300) NOT NULL, /* 댓글내용 */
	comment_date DATE DEFAULT SYSDATE NOT NULL, /* 댓글등록일 */
	reported_yn CHAR(1) DEFAULT 'N' /* 신고여부 */
);

CREATE UNIQUE INDEX PK_cm_comment
	ON cm_comment (
		board_no ASC,
		comment_no ASC
	);

ALTER TABLE cm_comment
	ADD
		CONSTRAINT PK_cm_comment
		PRIMARY KEY (
			board_no,
			comment_no
		);

/* 공지&이벤트&배너 */
CREATE TABLE notice (
	notice_no VARCHAR2(10) NOT NULL, /* 공지 번호 */
	admin_no VARCHAR2(10) NOT NULL, /* 관리자 번호 */
	notice_type VARCHAR2(15) DEFAULT 'NOTICE' NOT NULL, /* 공지 유형 */
	title VARCHAR2(90) NOT NULL, /* 공지 제목 */
	content VARCHAR2(3000) NOT NULL, /* 공지 내용 */
	thumbnail VARCHAR2(255), /* 공지 썸네일 */
	content_img VARCHAR2(255), /* 공지글 이미지 */
	priority NUMBER DEFAULT 1 NOT NULL, /* 우선순위 */
	post_date DATE DEFAULT SYSDATE NOT NULL, /* 공지 등록일 */
	start_date DATE, /* 시작일 */
	end_date DATE, /* 종료일 */
	viewcount NUMBER DEFAULT 0 NOT NULL /* 조회수 */
);

CREATE UNIQUE INDEX PK_notice
	ON notice (
		notice_no ASC
	);

ALTER TABLE notice
	ADD
		CONSTRAINT PK_notice
		PRIMARY KEY (
			notice_no
		);

/* 해시태그 */
CREATE TABLE cm_hashtag (
	hashtag_no NUMBER NOT NULL, /* 해시태그 식별번호 */
	hashtag_content VARCHAR2(30) NOT NULL /* 해시태그내용 */
);

CREATE UNIQUE INDEX PK_cm_hashtag
	ON cm_hashtag (
		hashtag_no ASC
	);

ALTER TABLE cm_hashtag
	ADD
		CONSTRAINT PK_cm_hashtag
		PRIMARY KEY (
			hashtag_no
		);

/* 상품 */
CREATE TABLE st_product (
	product_id VARCHAR2(10) NOT NULL, /* 상품 식별번호 */
	product_name VARCHAR2(150) NOT NULL, /* 상품명 */
	seller_no VARCHAR2(10) NOT NULL, /* 판매자 식별번호 */
	seller_name VARCHAR2(50) NOT NULL, /* 상호명 */
	price NUMBER DEFAULT 0 NOT NULL, /* 가격 */
	delivery_charge NUMBER DEFAULT 0 NOT NULL, /* 배송비 */
	exposure_yn CHAR(1) DEFAULT 'N' NOT NULL, /* 노출/미노출 */
	product_thumbnail_img VARCHAR2(255), /* 대표사진주소 */
	product_detail_img VARCHAR2(255), /* 세부사진주소 */
	thumbnail_description VARCHAR2(900), /* 썸네일 설명 */
	product_description VARCHAR2(3000), /* 상품 설명 */
	product_regist_date DATE DEFAULT SYSDATE NOT NULL, /* 상품 등록일 */
	product_update_date DATE DEFAULT SYSDATE, /* 상품 수정일 */
	parent_product_id VARCHAR2(10) /* 부모 상품 식별번호 */
);

CREATE UNIQUE INDEX PK_st_product
	ON st_product (
		product_id ASC
	);

ALTER TABLE st_product
	ADD
		CONSTRAINT PK_st_product
		PRIMARY KEY (
			product_id
		);

/* 카테고리 */
CREATE TABLE st_category (
	category_id VARCHAR2(10) NOT NULL, /* 카테고리 식별번호 */
	category_name VARCHAR2(30) NOT NULL, /* 카테고리명 */
	parent_category_id VARCHAR2(10) /* 부모 카테고리 */
);

CREATE UNIQUE INDEX PK_st_category
	ON st_category (
		category_id ASC
	);

ALTER TABLE st_category
	ADD
		CONSTRAINT PK_st_category
		PRIMARY KEY (
			category_id
		);

/* 주문 */
CREATE TABLE st_order (
	order_id VARCHAR2(10) NOT NULL, /* 주문번호 */
	product_id VARCHAR2(10) NOT NULL, /* 상품 식별번호 */
	user_no VARCHAR2(10) NOT NULL, /* 회원고유식별번호 */
	quantity NUMBER DEFAULT 1 NOT NULL, /* 수량 */
	order_date DATE DEFAULT SYSDATE NOT NULL, /* 주문일 */
	buyer_name VARCHAR2(15) NOT NULL, /* 주문자이름 */
	buyer_contact VARCHAR2(13) NOT NULL, /* 주문자연락처 */
	recipient VARCHAR2(15) NOT NULL, /* 배송받는사람 */
	recipient_contact VARCHAR2(13) NOT NULL, /* 배송지연락처 */
	delivery_address VARCHAR2(255) NOT NULL, /* 배송지주소 */
	delivery_ZIP_code CHAR(5) NOT NULL, /* 배송지우편번호 */
	delivery_note VARCHAR2(90), /* 배송메모 */
	payment_status CHAR(1) DEFAULT 'N' NOT NULL, /* 결제여부 */
	tracking_no VARCHAR2(26) /* 송장번호 */
);

CREATE UNIQUE INDEX PK_st_order
	ON st_order (
		order_id ASC
	);

ALTER TABLE st_order
	ADD
		CONSTRAINT PK_st_order
		PRIMARY KEY (
			order_id
		);

/* 캘린더 */
CREATE TABLE my_calendar (
	user_no VARCHAR2(10) NOT NULL, /* 회원고유식별번호 */
	calendar_date DATE DEFAULT SYSDATE NOT NULL, /* 날짜 */
	myplant_id VARCHAR2(10) NOT NULL, /* 반려식물 식별번호 */
	diary_no NUMBER, /* 다이어리글번호 */
	calendar_memo VARCHAR2(90), /* 달력메모 */
	mgr_prun_trim VARCHAR2(30), /* 관리-정지전정 */
	mgr_fertilization VARCHAR2(30), /* 관리-시비 */
	mgr_pest_disease VARCHAR2(30), /* 관리-병충해 */
	mgr_watering VARCHAR2(30), /* 관리-관수 */
	mgr_ventilation VARCHAR2(30), /* 관리-환기 */
	mgr_replant VARCHAR2(30), /* 관리-화분교체 */
	mgr_soil_change VARCHAR2(30), /* 관리-흙교체 */
	mgr_disposal VARCHAR2(30), /* 관리-고사식물제거 */
	mgr_water_change VARCHAR2(30), /* 관리-물교체 */
	mgr_relocation VARCHAR2(30), /* 관리-위치변경 */
	mgr_flowering VARCHAR2(30), /* 관리-개화 */
	mgr_harvest VARCHAR2(30), /* 관리-수확 */
	mgr_nutrient VARCHAR2(30), /* 관리-영양제 */
	mgr_sprout VARCHAR2(30), /* 관리-새싹 */
	mgr_dormancy VARCHAR2(30) /* 관리-시듦 */
);

CREATE UNIQUE INDEX PK_my_calendar
	ON my_calendar (
		user_no ASC,
		calendar_date ASC
	);

ALTER TABLE my_calendar
	ADD
		CONSTRAINT PK_my_calendar
		PRIMARY KEY (
			user_no,
			calendar_date
		);

/* 반려식물정보 */
CREATE TABLE my_plant (
	myplant_id VARCHAR2(10) NOT NULL, /* 반려식물 식별번호 */
	user_no VARCHAR2(10) NOT NULL, /* 회원고유식별번호 */
	myplant_name VARCHAR2(24) NOT NULL, /* 식물애칭 */
	myplant_variety VARCHAR2(30), /* 식물품종 */
	myplant_image_url VARCHAR2(255), /* 식물사진url */
	myplant_memo VARCHAR2(90), /* 식물메모 */
	myplant_start_date DATE, /* 키우기시작한날 */
	pos_window CHAR(1) DEFAULT 'N', /* 위치_창가 */
	pos_veranda CHAR(1) DEFAULT 'N', /* 위치_베란다 */
	pos_desk CHAR(1) DEFAULT 'N', /* 위치_책상위 */
	pos_yard CHAR(1) DEFAULT 'N', /* 위치_마당 */
	pos_garden CHAR(1) DEFAULT 'N', /* 위치_텃밭 */
	env_sunny CHAR(1) DEFAULT 'N', /* 조건_볕이좋은 */
	env_shady CHAR(1) DEFAULT 'N', /* 조건_그늘진 */
	env_windy CHAR(1) DEFAULT 'N', /* 조건_바람이부는 */
	env_dry CHAR(1) DEFAULT 'N', /* 조건_건조한 */
	env_humid CHAR(1) DEFAULT 'N', /* 조건_습한 */
	with_pet CHAR(1) DEFAULT 'N', /* 함께_애완동물과함께 */
	with_plant CHAR(1) DEFAULT 'N', /* 함께_여러식물들과함께 */
	with_child CHAR(1) DEFAULT 'N', /* 함께_어린아이와함께 */
	with_friend CHAR(1) DEFAULT 'N', /* 함께_친구와함께 */
	with_alone CHAR(1) DEFAULT 'N', /* 함께_혼자서 */
	created_date DATE DEFAULT SYSDATE NOT NULL /* 등록일 */
);

CREATE UNIQUE INDEX PK_my_plant
	ON my_plant (
		myplant_id ASC
	);

ALTER TABLE my_plant
	ADD
		CONSTRAINT PK_my_plant
		PRIMARY KEY (
			myplant_id
		);

/* 다이어리 */
CREATE TABLE my_diary (
	diary_id VARCHAR2(10) NOT NULL, /* 다이어리 식별번호 */
	user_no VARCHAR2(10) NOT NULL, /* 회원고유식별번호 */
	myplant_id VARCHAR2(10), /* 반려식물 식별번호 */
	diary_writing_date DATE DEFAULT SYSDATE NOT NULL, /* 다이어리작성일 */
	diary_content VARCHAR2(3000) NOT NULL, /* 일기내용 */
	diary_image1_url VARCHAR2(255), /* 첨부사진1 url */
	diary_image2_url VARCHAR2(255), /* 첨부사진2 url */
	diary_image3_url VARCHAR2(255), /* 첨부사진3 url */
	diary_image4_url VARCHAR2(255), /* 첨부사진4 url */
	diary_managing1 VARCHAR2(30), /* 식물관리1 */
	diary_managing2 VARCHAR2(30), /* 식물관리2 */
	diary_managing3 VARCHAR2(30), /* 식물관리3 */
	diary_managing4 VARCHAR2(30) /* 식물관리4 */
);

CREATE UNIQUE INDEX PK_my_diary
	ON my_diary (
		diary_id ASC
	);

ALTER TABLE my_diary
	ADD
		CONSTRAINT PK_my_diary
		PRIMARY KEY (
			diary_id
		);

/* 수목원 */
CREATE TABLE arboretum (
	arboretum_id VARCHAR2(10) NOT NULL, /* 수목원 식별번호 */
	arboretum_name VARCHAR2(50) NOT NULL, /* 수목원명 */
	arboretum_address VARCHAR2(255) NOT NULL, /* 수목원주소 */
	arboretum_latitude NUMBER(15,8) DEFAULT 37.75977618 NOT NULL, /* 위도 */
	arboretum_longitude NUMBER(15,8) DEFAULT 127.16891924 NOT NULL, /* 경도 */
	arboretum_homepage VARCHAR2(255), /* 홈페이지 */
	arboretum_tel VARCHAR2(13), /* 전화번호 */
	entrance_fee_yn CHAR(1) DEFAULT 'N' NOT NULL, /* 입장료 여부 */
	fee_adult NUMBER DEFAULT 0, /* 입장료 - 성인 */
	fee_teenage NUMBER DEFAULT 0, /* 입장료 - 청소년 */
	fee_child NUMBER DEFAULT 0, /* 입장료 - 어린이 */
	fee_disabled NUMBER DEFAULT 0, /* 입장료 - 장애인 */
	fee_etc VARCHAR2(900), /* 입장료 - 기타 */
	open_days VARCHAR2(255), /* 개관일 */
	closed_days VARCHAR2(255), /* 휴관일 */
	with_pet_yn CHAR(1) DEFAULT 'N', /* 반려동물 동반가능여부 */
	with_guidedog_yn CHAR(1) DEFAULT 'N', /* 안내견 동반가능여부 */
	edu_program_yn CHAR(1) DEFAULT 'N' /* 교육프로그램 운영여부 */
);

CREATE UNIQUE INDEX PK_arboretum
	ON arboretum (
		arboretum_id ASC
	);

ALTER TABLE arboretum
	ADD
		CONSTRAINT PK_arboretum
		PRIMARY KEY (
			arboretum_id
		);

/* 인기 검색어 */
CREATE TABLE search_history (
	search_no NUMBER NOT NULL, /* 검색 식별번호 */
	keyword VARCHAR2(90) NOT NULL, /* 검색 키워드 */
	search_date DATE DEFAULT SYSDATE NOT NULL, /* 검색 일시 */
	search_ip VARCHAR2(15) NOT NULL, /* 검색 ip */
	search_user_id VARCHAR2(10) /* 검색 회원 아이디 */
);

CREATE UNIQUE INDEX PK_search_history
	ON search_history (
		search_no ASC
	);

ALTER TABLE search_history
	ADD
		CONSTRAINT PK_search_history
		PRIMARY KEY (
			search_no
		);

/* 실내정원용 식물 상세 */
CREATE TABLE plant_info (
	plant_no VARCHAR2(10) NOT NULL, /* 식물 번호 */
	plant_name VARCHAR2(90) NOT NULL, /* 식물명 */
	scientific_name VARCHAR2(300), /* 학명 */
	english_name VARCHAR2(300), /* 영명 */
	family_name VARCHAR2(90), /* 과 명 */
	original_habitat VARCHAR2(30), /* 원산지 */
	plant_img VARCHAR2(255), /* 원본 이미지 */
	plant_thumbnail VARCHAR2(255), /* 썸네일 이미지 */
	usecase VARCHAR2(90), /* 용도 */
	growth_width NUMBER DEFAULT 0, /* 성장 넓이 */
	growth_height NUMBER DEFAULT 0, /* 성장 높이 */
	leaf_shape VARCHAR2(90), /* 잎 형태 */
	leaf_pattern VARCHAR2(30), /* 잎 무늬 */
	leaf_color VARCHAR2(90), /* 잎색 */
	root_shape VARCHAR2(90), /* 뿌리 형태 */
	growth_form VARCHAR2(30), /* 생육 형태 */
	flower_color VARCHAR2(90), /* 꽃색 */
	fruit_color VARCHAR2(90), /* 과일색 */
	ecology VARCHAR2(30), /* 생태 */
	view_type VARCHAR2(30), /* 보기 분류 */
	smell VARCHAR2(30), /* 냄새 */
	toxicity VARCHAR2(90), /* 독성 */
	difficulty VARCHAR2(30), /* 관리수준(난이도) */
	required_management VARCHAR2(90), /* 관리요구도 */
	growth_rate VARCHAR2(30), /* 생장 속도 */
	temperature VARCHAR2(30), /* 생육 온도 */
	winter_temperature VARCHAR2(30), /* 월동 온도 */
	light VARCHAR2(300), /* 광요구도 */
	humidity VARCHAR2(30), /* 습도 */
	fertilizer VARCHAR2(90), /* 비료 */
	soil VARCHAR2(90), /* 토양 */
	watering_spring VARCHAR2(90), /* 물주기-봄 */
	watering_summer VARCHAR2(90), /* 물주기-여름 */
	watering_autumn VARCHAR2(90), /* 물주기-가을 */
	watering_winter VARCHAR2(90), /* 물주기-겨울 */
	effect_purification CHAR(1) DEFAULT 'N', /* 공기정화효과 */
	placement VARCHAR2(900), /* 배치장소 */
	season_blooming VARCHAR2(90), /* 발화 계절 */
	season_fruiting VARCHAR2(90), /* 과일 계절 */
	season_propagation VARCHAR2(90), /* 번식 시기 */
	propagation_method VARCHAR2(90), /* 번식방법 */
	managing_diseases_pests VARCHAR2(300), /* 병충해 관리 */
	useful_info VARCHAR2(900), /* 기능성 정보 */
	managing_tips VARCHAR2(900), /* 관리팁 */
	created_date DATE, /* 정보 등록일 */
	update_date DATE, /* 정보 수정일 */
	view_count NUMBER DEFAULT 0 /* 조회수 */
);

CREATE UNIQUE INDEX PK_plant_info
	ON plant_info (
		plant_no ASC
	);

ALTER TABLE plant_info
	ADD
		CONSTRAINT PK_plant_info
		PRIMARY KEY (
			plant_no
		);

/* 신고 회원 관리 */
CREATE TABLE mb_reported (
	reported_user_no VARCHAR2(10) NOT NULL, /* 신고회원번호 */
	report_count NUMBER DEFAULT 1 NOT NULL, /* 신고된 횟수 */
	suspension_start DATE, /* 정지 시작일 */
	suspension_end DATE, /* 정지 종료일 */
	created_date DATE DEFAULT SYSDATE, /* 등록 일자 */
	modified_date DATE DEFAULT SYSDATE /* 수정 일자 */
);

CREATE UNIQUE INDEX PK_mb_reported
	ON mb_reported (
		reported_user_no ASC
	);

ALTER TABLE mb_reported
	ADD
		CONSTRAINT PK_mb_reported
		PRIMARY KEY (
			reported_user_no
		);

/* 게시글-해시태그 */
CREATE TABLE cm_board_hashtag (
	board_no NUMBER NOT NULL, /* 게시글 식별번호 */
	hashtag_no NUMBER NOT NULL /* 해시태그 식별번호 */
);

CREATE UNIQUE INDEX PK_cm_board_hashtag
	ON cm_board_hashtag (
		board_no ASC,
		hashtag_no ASC
	);

ALTER TABLE cm_board_hashtag
	ADD
		CONSTRAINT PK_cm_board_hashtag
		PRIMARY KEY (
			board_no,
			hashtag_no
		);

/* 관리자 */
CREATE TABLE admin (
	admin_no VARCHAR2(10) NOT NULL, /* 관리자 번호 */
	admin_id VARCHAR2(10) NOT NULL, /* 관리자 아이디 */
	password VARCHAR2(64) NOT NULL, /* 관리자 비밀번호 */
	name VARCHAR2(15) NOT NULL, /* 관리자 이름 */
	admin_type VARCHAR2(15) NOT NULL, /* 관리자 유형 */
	regist_date DATE DEFAULT SYSDATE NOT NULL /* 관리자 등록일 */
);

CREATE UNIQUE INDEX PK_admin
	ON admin (
		admin_no ASC
	);

ALTER TABLE admin
	ADD
		CONSTRAINT PK_admin
		PRIMARY KEY (
			admin_no
		);

/* 게시글 좋아요 */
CREATE TABLE cm_board_like (
	board_no NUMBER NOT NULL, /* 게시글 식별번호 */
	like_date DATE DEFAULT SYSDATE NOT NULL, /* 좋아요 날짜 */
	user_no VARCHAR2(10) NOT NULL, /* 회원고유식별번호 */
	like_yn CHAR(1) DEFAULT 'Y' NOT NULL /* 좋아요 체크 여부 */
);

CREATE UNIQUE INDEX PK_cm_board_like
	ON cm_board_like (
		board_no ASC,
		like_date ASC
	);

ALTER TABLE cm_board_like
	ADD
		CONSTRAINT PK_cm_board_like
		PRIMARY KEY (
			board_no,
			like_date
		);

/* 상품-카테고리 */
CREATE TABLE st_prod_cate (
	product_id VARCHAR2(10) NOT NULL, /* 상품 식별번호 */
	category_id VARCHAR2(10) NOT NULL /* 카테고리 식별번호 */
);

CREATE UNIQUE INDEX PK_st_prod_cate
	ON st_prod_cate (
		product_id ASC,
		category_id ASC
	);

ALTER TABLE st_prod_cate
	ADD
		CONSTRAINT PK_st_prod_cate
		PRIMARY KEY (
			product_id,
			category_id
		);

/* 교육프로그램 */
CREATE TABLE ar_edu_program (
	arboretum_id VARCHAR2(10) NOT NULL, /* 수목원 식별번호 */
	edu_program_id NUMBER NOT NULL, /* 교육프로그램 식별번호 */
	program_name VARCHAR2(50) NOT NULL, /* 프로그램명 */
	edu_pro_reservation CHAR(1) DEFAULT 'N', /* 교육프로그램 예약 가능 여부 */
	period VARCHAR2(90), /* 프로그램 운영기간 */
	target VARCHAR2(90) DEFAULT '일반인', /* 참가 대상 */
	cost NUMBER DEFAULT 0 /* 참가 비용 */
);

CREATE UNIQUE INDEX PK_ar_edu_program
	ON ar_edu_program (
		arboretum_id ASC,
		edu_program_id ASC
	);

ALTER TABLE ar_edu_program
	ADD
		CONSTRAINT PK_ar_edu_program
		PRIMARY KEY (
			arboretum_id,
			edu_program_id
		);

/* 댓글 좋아요 */
CREATE TABLE cm_comment_like (
	board_no NUMBER NOT NULL, /* 게시글 식별번호 */
	comment_no NUMBER NOT NULL, /* 댓글번호 */
	like_date DATE DEFAULT SYSDATE NOT NULL, /* 좋아요 날짜 */
	user_no VARCHAR2(10) NOT NULL, /* 회원고유식별번호 */
	like_yn CHAR(1) DEFAULT 'Y' NOT NULL /* 좋아요 체크 여부 */
);

CREATE UNIQUE INDEX PK_cm_comment_like
	ON cm_comment_like (
		board_no ASC,
		comment_no ASC,
		like_date ASC
	);

ALTER TABLE cm_comment_like
	ADD
		CONSTRAINT PK_cm_comment_like
		PRIMARY KEY (
			board_no,
			comment_no,
			like_date
		);

/* 판매자 회원 */
CREATE TABLE st_seller (
	seller_no VARCHAR2(10) NOT NULL, /* 판매자 식별번호 */
	business_no VARCHAR2(30) NOT NULL, /* 통신판매업 신고번호 */
	seller_id VARCHAR2(12) NOT NULL, /* 판매자 아이디 */
	seller_pwd VARCHAR2(64) NOT NULL, /* 판매자 비밀번호 */
	contact VARCHAR2(13) NOT NULL, /* 판매자 연락처 */
	email VARCHAR2(255) NOT NULL, /* 판매자 이메일 */
	store_name VARCHAR2(50) NOT NULL, /* 상호명 */
	displayed_store_name VARCHAR2(50), /* 보여질 상호명 */
	address VARCHAR2(255) NOT NULL, /* 판매자 주소 */
	approval_yn CHAR(1) DEFAULT 'N' NOT NULL, /* 승인 여부 */
	approval_date DATE, /* 등록승인 날짜 */
	created_date DATE DEFAULT SYSDATE NOT NULL /* 회원가입일 */
);

CREATE UNIQUE INDEX PK_st_seller
	ON st_seller (
		seller_no ASC
	);

CREATE UNIQUE INDEX UIX_st_seller
	ON st_seller (
		business_no ASC,
		seller_id ASC
	);

ALTER TABLE st_seller
	ADD
		CONSTRAINT PK_st_seller
		PRIMARY KEY (
			seller_no
		);

ALTER TABLE st_seller
	ADD
		CONSTRAINT UK_st_seller
		UNIQUE (
			business_no,
			seller_id
		);

ALTER TABLE mb_withdrawal
	ADD
		CONSTRAINT FK_member_TO_mb_withdrawal
		FOREIGN KEY (
			user_no
		)
		REFERENCES member (
			user_no
		);

ALTER TABLE mb_social
	ADD
		CONSTRAINT FK_member_TO_mb_social
		FOREIGN KEY (
			user_no
		)
		REFERENCES member (
			user_no
		);

ALTER TABLE mb_dormant
	ADD
		CONSTRAINT FK_member_TO_mb_dormant
		FOREIGN KEY (
			user_no
		)
		REFERENCES member (
			user_no
		);

ALTER TABLE customer_service
	ADD
		CONSTRAINT FK_member_TO_customer_service
		FOREIGN KEY (
			inquirer_no
		)
		REFERENCES member (
			user_no
		);

ALTER TABLE customer_service
	ADD
		CONSTRAINT FK_admin_TO_customer_service
		FOREIGN KEY (
			replier_no
		)
		REFERENCES admin (
			admin_no
		);

ALTER TABLE cm_board
	ADD
		CONSTRAINT FK_member_TO_cm_board
		FOREIGN KEY (
			user_no
		)
		REFERENCES member (
			user_no
		);

ALTER TABLE cm_comment
	ADD
		CONSTRAINT FK_cm_board_TO_cm_comment
		FOREIGN KEY (
			board_no
		)
		REFERENCES cm_board (
			board_no
		);

ALTER TABLE cm_comment
	ADD
		CONSTRAINT FK_member_TO_cm_comment
		FOREIGN KEY (
			user_no
		)
		REFERENCES member (
			user_no
		);

ALTER TABLE notice
	ADD
		CONSTRAINT FK_admin_TO_notice
		FOREIGN KEY (
			admin_no
		)
		REFERENCES admin (
			admin_no
		);

ALTER TABLE st_product
	ADD
		CONSTRAINT FK_st_product_TO_st_product
		FOREIGN KEY (
			parent_product_id
		)
		REFERENCES st_product (
			product_id
		);

ALTER TABLE st_product
	ADD
		CONSTRAINT FK_st_seller_TO_st_product
		FOREIGN KEY (
			seller_no
		)
		REFERENCES st_seller (
			seller_no
		);

ALTER TABLE st_category
	ADD
		CONSTRAINT FK_st_category_TO_st_category
		FOREIGN KEY (
			parent_category_id
		)
		REFERENCES st_category (
			category_id
		);

ALTER TABLE st_order
	ADD
		CONSTRAINT FK_member_TO_st_order
		FOREIGN KEY (
			user_no
		)
		REFERENCES member (
			user_no
		);

ALTER TABLE st_order
	ADD
		CONSTRAINT FK_st_product_TO_st_order
		FOREIGN KEY (
			product_id
		)
		REFERENCES st_product (
			product_id
		);

ALTER TABLE my_calendar
	ADD
		CONSTRAINT FK_my_plant_TO_my_calendar
		FOREIGN KEY (
			myplant_id
		)
		REFERENCES my_plant (
			myplant_id
		);

ALTER TABLE my_calendar
	ADD
		CONSTRAINT FK_member_TO_my_calendar
		FOREIGN KEY (
			user_no
		)
		REFERENCES member (
			user_no
		);

ALTER TABLE my_plant
	ADD
		CONSTRAINT FK_member_TO_my_plant
		FOREIGN KEY (
			user_no
		)
		REFERENCES member (
			user_no
		);

ALTER TABLE my_diary
	ADD
		CONSTRAINT FK_member_TO_my_diary
		FOREIGN KEY (
			user_no
		)
		REFERENCES member (
			user_no
		);

ALTER TABLE my_diary
	ADD
		CONSTRAINT FK_my_plant_TO_my_diary
		FOREIGN KEY (
			myplant_id
		)
		REFERENCES my_plant (
			myplant_id
		);

ALTER TABLE mb_reported
	ADD
		CONSTRAINT FK_member_TO_mb_reported
		FOREIGN KEY (
			reported_user_no
		)
		REFERENCES member (
			user_no
		);

ALTER TABLE cm_board_hashtag
	ADD
		CONSTRAINT FK_cm_board_TO_cm_board_hashtag
		FOREIGN KEY (
			board_no
		)
		REFERENCES cm_board (
			board_no
		);

ALTER TABLE cm_board_hashtag
	ADD
		CONSTRAINT FK_cm_hashtag_TO_cm_board_hashtag
		FOREIGN KEY (
			hashtag_no
		)
		REFERENCES cm_hashtag (
			hashtag_no
		);

ALTER TABLE cm_board_like
	ADD
		CONSTRAINT FK_cm_board_TO_cm_board_like
		FOREIGN KEY (
			board_no
		)
		REFERENCES cm_board (
			board_no
		);

ALTER TABLE cm_board_like
	ADD
		CONSTRAINT FK_member_TO_cm_board_like
		FOREIGN KEY (
			user_no
		)
		REFERENCES member (
			user_no
		);

ALTER TABLE st_prod_cate
	ADD
		CONSTRAINT FK_st_product_TO_st_prod_cate
		FOREIGN KEY (
			product_id
		)
		REFERENCES st_product (
			product_id
		);

ALTER TABLE st_prod_cate
	ADD
		CONSTRAINT FK_st_category_TO_st_prod_cate
		FOREIGN KEY (
			category_id
		)
		REFERENCES st_category (
			category_id
		);

ALTER TABLE ar_edu_program
	ADD
		CONSTRAINT FK_arboretum_TO_ar_edu_program
		FOREIGN KEY (
			arboretum_id
		)
		REFERENCES arboretum (
			arboretum_id
		);

ALTER TABLE cm_comment_like
	ADD
		CONSTRAINT FK_cm_comment_TO_cm_comment_like
		FOREIGN KEY (
			board_no,
			comment_no
		)
		REFERENCES cm_comment (
			board_no,
			comment_no
		);

ALTER TABLE cm_comment_like
	ADD
		CONSTRAINT FK_member_TO_cm_comment_like
		FOREIGN KEY (
			user_no
		)
		REFERENCES member (
			user_no
		);