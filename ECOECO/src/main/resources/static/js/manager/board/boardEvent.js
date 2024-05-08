window.onload = function() {

    // 공지사항 작성 스크립트
    if (document.getElementById("noticeWrite")) {
        const $noticeWrite = document.getElementById("noticeWrite");
        $noticeWrite.onclick = function () {
            location.href = "/manager/board/write";
        }
    }

    // 공지사항 작성
    if(document.getElementById("writeBoard")) {
        const $writeBoard = document.getElementById("writeBoard");
        $writeBoard.onclick = function() {
            location.href = "/manager/board/write";
        }
    }

    // 공지사항 작성 시 카테고리 선택 이벤트
    if (document.getElementById('noticeCategory')) {
        document.getElementById('noticeCategory').addEventListener('change', function() {
            var category = this.value;
            var subCategorySelect = document.getElementById('noticeSubCategory');

            // FAQ 카테고리가 선택된 경우에만 서브 카테고리를 활성화
            if (category === 'FAQ') {
                subCategorySelect.disabled = false;
            } else {
                // FAQ 이외의 카테고리가 선택된 경우 서브 카테고리를 비활성화
                subCategorySelect.disabled = true;
                // 선택된 서브 카테고리를 '선택'으로 리셋
                // subCategorySelect.value = '';
            }
        });
    }
}

<!-- 공지사항 작성 페이지 카테고리가 null값일 경우 발생하는 오류 페이지 대신 모달창 띄움 -->
function validateForm() {
    var category = document.getElementById("noticeCategory").value;
    var subCategory = document.getElementById("noticeSubCategory").value;

    if (!category || category === "선택") { // category 값이 없거나 "선택"과 동일한 경우
        alert("분류를 선택해주세요.");
        return false;
    }
    if (category === "FAQ" && (!subCategory || subCategory === "선택")) { // category가 "FAQ"이고 subCategory 값이 없거나 "선택"과 동일한 경우
        alert("소분류를 선택해주세요.");
        return false;
    }
    return true;
}

<!-- 공지사항 삭제 기능 -->
function deleteNotice(noticeNo) {
    fetch('/manager/board/deleteNotice', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json; charset=UTF-8'
        },
        body: JSON.stringify({ noticeNo })
    })
        .then(response => {
            if (!response.ok) {	//  fetch API의 Response 객체에서 응답 상태 코드가 성공적인지 확인하는 구문
                throw new Error('네트워크 응답 없음');
            }
            // 삭제가 성공하면 사용자에게 메시지를 표시하고 noticeList 페이지로 redirect
            alert('게시글이 삭제되었습니다.');
            window.location.href = '/manager/board/adminNoticeList'; // noticeList 페이지로 이동
        })
        .catch(error => console.error('Error:', error));
}

<!-- 공지사항 수정 페이지 -->
function noticeModify(noticeNo) {
    // 공지사항 수정 페이지로 이동합니다. 공지사항 번호를 URL 파라미터로 전달합니다.
    window.location.href = "/manager/board/adminNoticeModify?noticeNo=" + noticeNo;
}

<!-- 공지사항 파일 선택 -->
function displayFileNames(input) {
    var fileDisplayInput = document.getElementById('file_name_display');
    if (input.files.length > 0) {
        if (input.files.length === 1) {
            fileDisplayInput.value = input.files[0].name;
        } else {
            fileDisplayInput.value = input.files.length + "개 파일 선택됨";
        }
    } else {
        fileDisplayInput.value = ''; // 파일 선택 취소 시 텍스트 필드를 비움
    }
}



<!-- -----------------------------------------------------------------------------------------------------  -->
<!-- 우리가그린 삭제 기능 -->
function deleteUsGreen(comuNo) {
    fetch('/manager/board/deleteUsGreen', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json; charset=UTF-8'
        },
        body: JSON.stringify({ comuNo })
    })
        .then(response => {
            if (!response.ok) {	//  fetch API의 Response 객체에서 응답 상태 코드가 성공적인지 확인하는 구문
                throw new Error('네트워크 응답 없음');
            }
            // 삭제가 성공하면 사용자에게 메시지를 표시하고 noticeList 페이지로 redirect
            alert('게시글이 삭제되었습니다.');
            window.location.href = '/manager/board/adminUsGreenList'; // noticeList 페이지로 이동
        })
        .catch(error => console.error('Error:', error));
}

<!-- 이미지 로드가 되지 않거나 null값일 때 -->
document.addEventListener("DOMContentLoaded", function() {
    // 이미지 요소의 id값
    var imageId = "noticeImage";

    // 해당 id값을 가진 이미지 요소 가져오기
    var imageElement = document.getElementById(imageId);

    // 이미지 요소가 존재하는지 확인
    if (imageElement) {
        // 이미지가 로드되었는지 확인
        if (!imageElement.complete || imageElement.naturalWidth === 0) {
            // 이미지가 로드되지 않은 경우 이미지 요소를 숨김
            imageElement.style.display = "none";
        }
    }
});