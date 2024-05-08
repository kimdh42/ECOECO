/* 주문 취소 */
function openRefundModal() {
    document.getElementById("refundmodal").style.display = "block";
}

function closeRefundModal() {
    document.getElementById("refundmodal").style.display = "none";
}

function checkRefundModal() {
    const orderNo = document.getElementById('orderNo').value;

    console.log(orderNo);
    fetch('/user/mypage/refundSupportProject?orderNo=' + orderNo, { // 수정된 URL로 변경
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(response => {
            if (response.ok) {
                alert("주문 취소가 완료 되었습니다.");
                window.location.href = '/user/mypage/mypage_SupportProject';
            } else {
                alert("주문 취소에 실패하였습니다.");
            }
        })
}

document.getElementById('registNewsForm').addEventListener('submit', function(event) {
    event.preventDefault(); // 폼 기본 동작 방지

    const formData = new FormData(this); // 폼 데이터 가져오기

    fetch('/user/mypage/registNews', {
        method: 'POST',
        body: formData
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json(); // JSON 형식으로 응답 처리
        })
        .then(data => {
            // 성공적으로 저장된 경우
            console.log('Data saved:', data);
            location.href = '/user/mypage/news?projectNo=' + document.getElementById('projectNo').value; // 페이지 이동
        })
        .catch(error => {
            // 오류 발생 시 처리
            console.error('Error saving data:', error);
            // 오류 메시지 표시 등
        });
});

/* 새소식 삭제 */
function openNewsDeleteModal() {
    document.getElementById("deleteRegistNewsModal").style.display = "block";
}

function closeNewsSaveModal() {
    document.getElementById("deleteRegistNewsModal").style.display = "none";
}

function newsDelete() {
    const newsNo = document.getElementById('newsNo').value;

    fetch('/user/mypage/registNewsDelete',{
        method: 'POST', //
        headers: {
            'Content-Type': 'application/json' // 요청의 본문 형식을 JSON으로 지정합니다.
        },
        body: JSON.stringify({newsNo: newsNo})
    })
        .then(response => {
            // 응답을 확인하고 응답에 따른 동작을 수행합니다.
            if (response.ok) {
                alert("새소식 삭제가 완료 되었습니다.");
                window.location.href = '/user/mypage/news';
            } else {
                // 응답이 오류 상태인 경우, 오류 메시지를 표시합니다.
                alert("새소식 삭제에 실패하였습니다.");
            }
        })
}