SET sql_mode = '';

drop database ecoeco;
create database ecoeco;
grant all privileges on ecoeco.* to 'ohgiraffers'@'%';
show grants for 'ohgiraffers'@'%';
use ecoeco;


DROP TABLE `USER_INFO`;
DROP TABLE `USER_PROFILE_IMAGE`;
DROP TABLE `BANK_ACCOUNT`;
DROP TABLE `NOTICE`;
DROP TABLE `NOTICE_IMAGE`;
DROP TABLE `PAYMENT`;
DROP TABLE `SETTLEMENT`;
DROP TABLE `REFUND`;
DROP TABLE `ORDER`;
DROP TABLE `PROJECT`;
DROP TABLE `MAKER`;
DROP TABLE `NEWS`;
DROP TABLE `STORY`;
DROP TABLE `CATEGORY`;
DROP TABLE `REWARD`;
DROP TABLE `SUPPORT_REVIEWS`;
DROP TABLE `US_GREEN`;
DROP TABLE `US_GREEN_GOOD`;
DROP TABLE `US_GREEN_IMAGE`;
DROP TABLE `US_GREEN_COMMENT`;
DROP TABLE `US_GREEN_COMMENT_GOOD`;




-- 계좌 변경됨 
CREATE TABLE `BANK_ACCOUNT` (
  `accountNo` varchar(255) NOT NULL COMMENT '계좌번호',
  `bankName` varchar(255) NOT NULL COMMENT '은행명',
  PRIMARY KEY (`accountNo`)
) ENGINE=InnoDB COMMENT='계좌';

CREATE TABLE `CATEGORY` (
  `categoryCode` tinyint NOT NULL AUTO_INCREMENT COMMENT '카테고리번호',
  `categoryName` varchar(20) NOT NULL COMMENT '카테고리명',
  PRIMARY KEY (`categoryCode`)
) ENGINE=InnoDB COMMENT='카테고리';

-- 메이커정보 변경됨 
CREATE TABLE `MAKER` (
  `makerNo` int NOT NULL AUTO_INCREMENT COMMENT '메이커번호',
  `makerName` varchar(10) NOT NULL COMMENT '메이커명',
  `prImage` varchar(100) DEFAULT NULL COMMENT '메이커프로필',
  `email` varchar(30) NOT NULL COMMENT '이메일',
  `phone` varchar(15) NOT NULL COMMENT '전화번호',
  `userNo` int NOT NULL COMMENT '회원번호',
  `projectNo` int NOT NULL COMMENT '프로젝트 번호',
  PRIMARY KEY (`makerNo`)
) ENGINE=InnoDB COMMENT='메이커정보';

CREATE TABLE `NEWS` (
  `newsNo` int NOT NULL AUTO_INCREMENT COMMENT '새소식 번호',
  `newsCategory` varchar(10) NOT NULL COMMENT '새소식 분류',
  `newsTitle` varchar(20) NOT NULL COMMENT '새소식 제목',
  `newsContent` varchar(150) NOT NULL COMMENT '새소식 내용',
  `newsDate` date NOT NULL DEFAULT (curdate()) COMMENT '새소식 작성날짜',
  `newsImage` varchar(100) DEFAULT NULL COMMENT '새소식 이미지',
  `projectNo` int DEFAULT NULL COMMENT '프로젝트 번호',
  `userNo` int DEFAULT NULL COMMENT '회원번호',
  PRIMARY KEY (`newsNo`)
) ENGINE=InnoDB COMMENT='새소식';

CREATE TABLE `NOTICE` (
  `noticeNo` int NOT NULL AUTO_INCREMENT COMMENT '공지사항 번호',
  `noticeCategory` varchar(5) NOT NULL COMMENT '공지사항 분류',
  `noticeSubCategory` varchar(10) DEFAULT NULL COMMENT '공지사항 소분류',
  `noticeTitle` varchar(255) NOT NULL COMMENT '공지사항 제목',
  `noticeDetail` varchar(150) NOT NULL COMMENT '공지사항 내용',
  `noticeDate` date NOT NULL DEFAULT (curdate()) COMMENT '작성날짜',
  `noticeStatus` char(1) NOT NULL DEFAULT 'Y' COMMENT '공지사항 삭제여부',
  `userNo` int NOT NULL COMMENT '회원번호',
  PRIMARY KEY (`noticeNo`)
) ENGINE=InnoDB COMMENT='공지사항';

CREATE TABLE `NOTICE_IMAGE` (
  `noticeImgNo` int NOT NULL AUTO_INCREMENT COMMENT '공지 이미지번호',
  `noticeOriginFileName` varchar(255) COMMENT '공지 이미지 원본명',
  `noticeSaveName` varchar(255) COMMENT '공지 이미지 저장명',
  `noticePath` varchar(1000) COMMENT '공지 이미지 경로명',
  `noticeImageStatus` CHAR(1) NOT NULL DEFAULT 'Y' COMMENT '공지사항 이미지 삭제여부',
  `noticeNo` int NOT NULL COMMENT '공지사항 번호',
  PRIMARY KEY (`noticeImgNo`)
) ENGINE=InnoDB COMMENT='공지사항 이미지';

-- 주문 변경됨     
CREATE TABLE `ORDER` (
  `orderNo` int NOT NULL AUTO_INCREMENT COMMENT '주문번호',
  `orderPrice` int DEFAULT NULL COMMENT '주문금액',
  `orderStatus` varchar(255) NOT NULL COMMENT '주문상태',
  `orderCategory` varchar(255) DEFAULT NULL COMMENT '주문분류',
  `paymentCategory` varchar(255) DEFAULT NULL COMMENT '결제분류',
  `paymentStatus` varchar(255) DEFAULT NULL COMMENT '결제상태',
  `orderDate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '주문일시',
  `buyerName` varchar(15) DEFAULT NULL COMMENT '수령인',
  `buyerTel` varchar(15) DEFAULT NULL COMMENT '연락처',
  `buyerAddr` varchar(255) DEFAULT NULL COMMENT '배송지',
  `buyerAccount` varchar(255) DEFAULT NULL,
  `userNo` int NOT NULL COMMENT '주문자번호',
  `projectNo` int DEFAULT NULL COMMENT '프로젝트 번호',
  `rewardNo` int DEFAULT NULL COMMENT '리워드번호',
  `accountNo` int DEFAULT NULL,
  PRIMARY KEY (`orderNo`)
) ENGINE=InnoDB COMMENT='주문';

-- 결제 변경됨     
CREATE TABLE `PAYMENT` (
  `paymentNo` int NOT NULL AUTO_INCREMENT COMMENT '결제 번호',
  `paymentPrice` int NOT NULL COMMENT '결제 금액',
  `paymentDate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '결제일시',
  `apiPayNo` varchar(255) DEFAULT NULL COMMENT 'api결제번호',
  `projectNo` int DEFAULT NULL COMMENT '프로젝트 번호',
  `orderNo` int NOT NULL COMMENT '주문번호',
  `rewardNo` int DEFAULT NULL COMMENT '리워드번호',
  `userNo` int NOT NULL COMMENT '결제자번호',
  PRIMARY KEY (`paymentNo`)
) ENGINE=InnoDB COMMENT='결제';

-- 프로젝트 변경됨     
CREATE TABLE `PROJECT` (
  `projectNo` int NOT NULL AUTO_INCREMENT COMMENT '프로젝트 번호',
  `projectName` varchar(255) NOT NULL COMMENT '프로젝트명',
  `projectStatus` int NOT NULL COMMENT '프로젝트 상태',
  `projectSorN` char(1) DEFAULT NULL COMMENT '성공여부',
  `deliveryYN` char(1) NOT NULL COMMENT '배송여부',
  `serviceCharge` tinyint NOT NULL COMMENT '서비스요금(수수료)',
  `targetAmount` int NOT NULL COMMENT '목표금액',
  `requestDate` date DEFAULT NULL COMMENT '요청일',
  `approvalDate` date DEFAULT NULL COMMENT '승인일',
  `petitionDate` date DEFAULT NULL COMMENT '반려일',
  `startDate` date NOT NULL COMMENT '시작일',
  `endDate` date NOT NULL COMMENT '종료일',
  `achievedAmount` bigint DEFAULT NULL COMMENT '달성액',
  `dueDate` date DEFAULT NULL COMMENT '마감일',
  `thumbnail` varchar(100) DEFAULT NULL COMMENT '썸네일',
  `categoryCode` tinyint NOT NULL COMMENT '카테고리번호',
  PRIMARY KEY (`projectNo`)
) ENGINE=InnoDB COMMENT='프로젝트';
    
CREATE TABLE `REFUND` (
  `refundNo` int NOT NULL AUTO_INCREMENT COMMENT '환불번호',
  `refundRequestDate` date NOT NULL DEFAULT (curdate()) COMMENT '환불요청일',
  `refundDate` date DEFAULT NULL COMMENT '환불완료일',
  `refundStatus` varchar(6) DEFAULT NULL COMMENT '환불상태',
  `paymentNo` int NOT NULL COMMENT '결제 번호',
  PRIMARY KEY (`refundNo`)
) ENGINE=InnoDB COMMENT='환불';

CREATE TABLE `REWARD` (
  `rewardNo` int NOT NULL AUTO_INCREMENT COMMENT '리워드번호',
  `rewardName` varchar(255) NOT NULL COMMENT '리워드명',
  `rewardPrice` int NOT NULL COMMENT '금액',
  `rewardInfo` varchar(255) NOT NULL COMMENT '리워드설명',
  `projectNo` int NOT NULL COMMENT '프로젝트 번호',
  PRIMARY KEY (`rewardNo`)
) ENGINE=InnoDB COMMENT='리워드';

-- 정산 변경됨 
CREATE TABLE `SETTLEMENT` (
  `settlementNo` int NOT NULL AUTO_INCREMENT COMMENT '정산번호',
  `settlementStatus` varchar(1) NOT NULL COMMENT '정산상태',
  `settlementPrice` bigint NOT NULL COMMENT '정산금액',
  `settlementDate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '정산일시',
  `userNo` int DEFAULT NULL COMMENT '메이커번호',
  `projectNo` int DEFAULT NULL COMMENT '프로젝트 번호',
  PRIMARY KEY (`settlementNo`)
) ENGINE=InnoDB COMMENT='정산';

CREATE TABLE `STORY` (
  `storyNo` int NOT NULL AUTO_INCREMENT COMMENT '스토리번호',
  `storySummary` varchar(100) NOT NULL COMMENT '요약',
  `storyImg` varchar(100) NOT NULL COMMENT '대표이미지',
  `storyInfo` varchar(500) NOT NULL COMMENT '스토리내용',
  `projectNo` int NOT NULL COMMENT '프로젝트 번호',
  PRIMARY KEY (`storyNo`)
) ENGINE=InnoDB COMMENT='스토리';

CREATE TABLE `SUPPORT_REVIEWS` (
  `supportReviewsNo` int NOT NULL AUTO_INCREMENT COMMENT '응원리뷰번호',
  `supportReviewsDate` date NOT NULL DEFAULT (curdate()) COMMENT '작성날짜',
  `supportReviewsContent` varchar(150) NOT NULL COMMENT '응원리뷰내용',
  `supportReviewsCategory` int NOT NULL COMMENT '응원리뷰분류',
  `projectNo` int DEFAULT NULL COMMENT '프로젝트 번호',
  `userNo` int DEFAULT NULL COMMENT '회원번호',
  PRIMARY KEY (`supportReviewsNo`)
) ENGINE=InnoDB COMMENT='응원/리뷰';

CREATE TABLE `US_GREEN` (
  `comuNo` int NOT NULL AUTO_INCREMENT COMMENT '커뮤니티번호',
  `comuCategory` varchar(255) NOT NULL COMMENT '커뮤니티 분류',
  `comuTitle` varchar(255) NOT NULL COMMENT '커뮤니티제목',
  `comuDetail` varchar(300) NOT NULL COMMENT '커뮤니티내용',
  `comuDate` date NOT NULL DEFAULT (curdate()) COMMENT '커뮤니티작성일',
  `comuStatus` char(1) NOT NULL DEFAULT 'Y' COMMENT '삭제여부',
  `userNo` int NOT NULL COMMENT '회원번호',
  PRIMARY KEY (`comuNo`)
) ENGINE=InnoDB COMMENT='우리가그린(커뮤니티)';

CREATE TABLE `US_GREEN_COMMENT` (
  `commentNo` int NOT NULL AUTO_INCREMENT COMMENT '댓글번호',
  `commentDetail` varchar(150) NOT NULL COMMENT '댓글내용',
  `commentDate` date NOT NULL DEFAULT (curdate()) COMMENT '작성일',
  `commentDelete` char(1) NOT NULL DEFAULT 'N' COMMENT '삭제여부',
  `comuNo` int NOT NULL COMMENT '커뮤니티번호',
  `userNo` int NOT NULL COMMENT '회원번호',
  PRIMARY KEY (`commentNo`)
) ENGINE=InnoDB COMMENT='우리가그린(댓글)';

CREATE TABLE `US_GREEN_COMMENT_GOOD` (
  `cLikeNo` int NOT NULL AUTO_INCREMENT COMMENT '번호',
  `cLikeCount` int DEFAULT NULL COMMENT '좋아요',
  `commentNo` int NOT NULL COMMENT '댓글번호',
  `userNo` int NOT NULL COMMENT '회원번호',
  PRIMARY KEY (`cLikeNo`)
) ENGINE=InnoDB COMMENT='우리가그린(댓글) 좋아요';

CREATE TABLE `US_GREEN_GOOD` (
  `likeNo` int NOT NULL AUTO_INCREMENT COMMENT '번호',
  `likeCount` int DEFAULT NULL COMMENT '좋아요',
  `badCount` int DEFAULT NULL COMMENT '아쉬워요',
  `userNo` int NOT NULL COMMENT '회원번호',
  `comuNo` int NOT NULL COMMENT '커뮤니티번호',
  PRIMARY KEY (`likeNo`)
) ENGINE=InnoDB COMMENT='우리가그린(커뮤니티) 좋아요';

CREATE TABLE `US_GREEN_IMAGE` (
  `comuImgNo` int NOT NULL AUTO_INCREMENT COMMENT '커뮤니티 이미지번호',
  `comuOriginFileName` varchar(255) NOT NULL COMMENT '커뮤니티 이미지 원본명',
  `comuSaveName` varchar(255) NOT NULL COMMENT '커뮤니티 이미지 저장명',
  `comuPath` varchar(255) NOT NULL COMMENT '커뮤니티 이미지 경로명',
  `comuNo` int NOT NULL COMMENT '커뮤니티번호',
  PRIMARY KEY (`comuImgNo`)
) ENGINE=InnoDB COMMENT='우리가그린(커뮤니티) 이미지';

-- 회원정보 변경됨
CREATE TABLE `USER_INFO` (
  `userNo` int NOT NULL AUTO_INCREMENT COMMENT '회원번호',
  `userId` varchar(20) NOT NULL COMMENT '회원아이디',
  `userPwd` varchar(255) NOT NULL COMMENT '비밀번호',
  `userName` varchar(5) NOT NULL COMMENT '회원명',
  `userPnum` varchar(15) DEFAULT NULL COMMENT '전화번호',
  `userEmail` varchar(255) NOT NULL COMMENT '이메일',
  `userBirth` date DEFAULT (curdate()) COMMENT '생년월일',
  `userGender` char(1) DEFAULT NULL COMMENT '성별',
  `userGrade` int DEFAULT NULL COMMENT '등급',
  `userPoint` int DEFAULT NULL COMMENT '포인트',
  `userDate` date NOT NULL DEFAULT (curdate()) COMMENT '가입일',
  `userAccount` varchar(255) DEFAULT NULL COMMENT '계좌번호',
  `userAddress` varchar(255) DEFAULT NULL COMMENT '주소',
  `userRole` varchar(255) DEFAULT 'USER' COMMENT '관리자 권한 여부',
  `accountNo` int DEFAULT NULL,
  PRIMARY KEY (`userNo`)
) ENGINE=InnoDB COMMENT='회원정보';

CREATE TABLE `USER_PROFILE_IMAGE` (
  `profileImageNo` int NOT NULL AUTO_INCREMENT COMMENT '프로필 이미지번호',
  `profileImageFileName` varchar(255) NOT NULL COMMENT '프로필 이미지 원본명',
  `profileImageSaveName` varchar(255) NOT NULL COMMENT '프로필 이미지 저장명',
  `profileImagePath` varchar(1000) NOT NULL COMMENT '프로필 이미지 경로명',
  `userNo` int NOT NULL COMMENT '회원번호',
  PRIMARY KEY (`profileImageNo`)
) ENGINE=InnoDB COMMENT='회원 프로필 이미지';
