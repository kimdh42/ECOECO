
/* 수정하기 버튼 모달 열기 */
function openSaveModal() {
    document.getElementById("savemodal").style.display = "block";
}
/* 수정하기 버튼 모달 닫기 */
function closeSaveModal() {
    document.getElementById("savemodal").style.display = "none";
}

/* 수정 내용 저장 */
function saveChanges() {
    /* 수정된 내용 가져오기 */
    const userPwd = document.getElementById('userPwd').value;
    const userPwdConfirm = document.getElementById('userPwd2').value;
    const userPnum = document.getElementById('userPnum').value;
    const userEmail = document.getElementById('userEmail').value;
    const userGender = document.querySelector('input[name="userGender"]:checked').value;
    const newBirthYear = document.getElementById('birth-year').value;
    const newBirthMonth = document.getElementById('birth-month').value;
    const newBirthDay = document.getElementById('birth-day').value;
    const userBirth = newBirthYear + "-" + newBirthMonth + "-" + newBirthDay;

    /* AJAX로 서버로 수정 내용 전송 */
    fetch('/user/mypage/updateUserInfo', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            userPwd: userPwd,
            userPwdConfirm: userPwdConfirm,
            userPnum: userPnum,
            userEmail: userEmail,
            userGender: userGender,
            userBirth: userBirth
        }),
    })
        .then(response => {
            if (response.ok) {
                alert("수정을 완료하였습니다.")
                location.reload(); // 수정 성공 시 페이지 새로고침
            } else {
                throw new Error('Failed to update user info.');
            }
        })
        .catch(error => {
            console.error('Error updating user info:', error);
        });
}

/* 탈퇴하기 모달창 열기 */
function openDeleteModal() {
    document.getElementById("deletemodal").style.display = "block";
}
/* 탈퇴하기 모달창 닫기 */
function closeUserInfoDeleteButton() {
    document.getElementById("deletemodal").style.display = "none";
}
/* 탈퇴하기 */
function userInfoDeleteButton() {
    // '네'를 누르면 탈퇴가 되어 로그아웃 되는 로직 추가

        // 회원 탈퇴를 위한 Ajax 요청
        fetch('/user/mypage/deleteUserInfo', {
            method: 'POST', // POST 요청으로 회원 탈퇴를 서버에 전달합니다.
            headers: {
                'Content-Type': 'application/json' // 요청의 본문 형식을 JSON으로 지정합니다.
            }
        })
            .then(response => {
                // 응답을 확인하고 응답에 따른 동작을 수행합니다.
                if (response.ok) {
                    // 응답이 성공적으로 왔을 경우, 회원 탈퇴가 완료되었음을 알리고 메인 페이지로 리다이렉트합니다.
                    alert("탈퇴가 완료되었습니다.");
                    window.location.href = '/user/mypage/logout'; // 로그아웃 후 메인 페이지로 이동합니다.
                } else {
                    // 응답이 오류 상태인 경우, 오류 메시지를 표시합니다.
                    alert("회원 탈퇴 중 오류가 발생했습니다.");
                }

            })
    }

/* 프로필 사진 변경 */

    const fileInput = document.getElementById("file");
    if (fileInput) {
        fileInput.addEventListener("change", function() {
            const file = this.files[0];
            if (file) {
                const reader = new FileReader();
                reader.onload = function(e) {
                    document.getElementById("previewImage").src = e.target.result;
                }
                reader.readAsDataURL(file);
            }
        });
    }


/* 프로필 사진 업로드 후 처리 */
document.getElementById("profileImageForm").addEventListener("submit", function(event) {
    event.preventDefault(); // 기본 제출 이벤트 방지

    const formData = new FormData(this); // 폼 데이터 생성

    fetch('/user/mypage/uploadProfileImage', {
        method: 'POST', // POST 요청으로 이미지 업로드
        body: formData // 폼 데이터를 요청의 본문으로 설정
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('프로필 사진 업로드에 실패했습니다.');
            }
            return response.text(); // 텍스트로 변환
        })
        .then(data => {
            // 업로드 성공 시 처리할 내용 작성
            console.log('프로필 사진 업로드 성공:', data);
            alert('프로필 사진이 성공적으로 업로드되었습니다.');
        })
        .catch(error => {
            // 업로드 실패 시 처리할 내용 작성
            console.error('프로필 사진 업로드 실패:', error);
            alert('프로필 사진 업로드에 실패했습니다.');
        });
});
