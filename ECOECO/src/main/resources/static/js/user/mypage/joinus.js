window.onload = function() {

    if(document.getElementById("userPwd")) {

        const userPwd = document.querySelector('#userPwd');
        const userPwd2 = document.querySelector('#userPwd2');
        const userPwdCheckMsg1 = document.querySelector('#userPwdCheckMsg1');
        const userPwdCheckMsg2 = document.querySelector('#userPwdCheckMsg2');
        const pwValidation = /^.*(?=^.{8,20}$)(?=.*\d)(?=.*[a-zA-Z])(?=.*[~,!,^,@,#,$,*,(,),=,+,_,.,|]).*$/;

        function userPwd_check() {

            userPwdCheckMsg1.innerHTML = '';
            userPwdCheckMsg2.innerHTML = '';

            if(userPwd.value.length < 8 || !pwValidation.test(userPwd.value)) {
                userPwdCheckMsg1.innerHTML = '8자리 이상 영문, 숫자, 특수문자를 포함해야 합니다.';
                userPwdCheckMsg1.style.color = 'red';
                userPwdCheckMsg1.style.display = 'block';
            } else if( userPwd.value !== userPwd2.value ) {
                userPwdCheckMsg2.innerHTML = '비밀번호를 확인해 주세요';
                userPwdCheckMsg2.style.color = 'red';
                userPwdCheckMsg2.style.display = 'block';
            } else {
                userPwdCheckMsg1.innerHTML = '사용 하실 수 있습니다.';
                userPwdCheckMsg1.style.color = 'green';
                userPwdCheckMsg1.style.display = 'block';
                userPwdCheckMsg2.innerHTML = '사용 하실 수 있습니다.';
                userPwdCheckMsg2.style.color = 'green';
                userPwdCheckMsg2.style.display = 'block';
            }
        }
        userPwd.addEventListener('focusout', userPwd_check);
        userPwd2.addEventListener('focusout', userPwd_check);
    }

    if(document.getElementById('userBirth')) {
        /* 년도(birth-year) 옵션 생성 */
        const yearSelect = document.getElementById("birth-year");
        const currentYear = new Date().getFullYear();
        for(let i = currentYear; i >= currentYear - 100; i-- ) {
            const option = document.createElement("option");
            option.text = i;
            option.value = i;
            yearSelect.add(option);
        }

        /* 월(birth-month) 옵션 생성 */
        const monthSelect = document.getElementById("birth-month");
        for(let i = 1; i <= 12; i++) {
            const option = document.createElement("option");
            option.text = i < 10 ? "0" + i : i;
            /* 한자리 숫자는 앞에 0이 붙음 */
            option.value = i < 10 ? "0" + i : i;
            monthSelect.add(option);
        }

        /* 일(birth-day) 옵션 생성 */
        const daySelect = document.getElementById("birth-day");
        for(let i = 1; i <= 31; i++) {
            const option = document.createElement("option");
            option.text = i;
            /* 한자리 숫자는 앞에 0이 붙음 */
            option.value = i < 10 ? "0" + i : i;
            daySelect.add(option);
        }
    }

    // 이메일 주소와 도메인을 조합하여 hidden input에 저장
    const userEmailId = document.getElementById("userEmailId");
    const emailAddress = document.getElementById("emailAddress");
    const userEmailInput = document.getElementById("userEmail");

    function updateEmailInput() {
        const emailId = userEmailId.value.trim();
        const userEmailAddress = emailAddress.value.trim();
        userEmailInput.value = emailId + '@' + userEmailAddress;
    }

    userEmailId.addEventListener("input", updateEmailInput);
    emailAddress.addEventListener("input", updateEmailInput);

    // 년, 월, 일을 조합하여 hidden input에 저장
    const birthYearSelect = document.getElementById("birth-year");
    const birthMonthSelect = document.getElementById("birth-month");
    const birthDaySelect = document.getElementById("birth-day");
    const userBirthInput = document.getElementById("userBirth");

    function updateBirthInput() {
        const year = birthYearSelect.value;
        const month = birthMonthSelect.value.padStart(2, '0');
        const day = birthDaySelect.value.padStart(2, '0');

        userBirthInput.value = `${year}-${month}-${day}`;
    }

    birthYearSelect.addEventListener("change", updateBirthInput);
    birthMonthSelect.addEventListener("change", updateBirthInput);
    birthDaySelect.addEventListener("change", updateBirthInput);

    /* 아이디 중복 검사 */
    document.getElementById('duplicationCheck').addEventListener('click', function() {
        const userId = document.getElementById('userId').value;

        // AJAX 요청
        const xhr = new XMLHttpRequest();
        xhr.open('GET', '/user/mypage/selectUserById?userId=' + userId, true);
        xhr.onload = function() {
            if (xhr.status === 200) {
                const response = xhr.responseText;
                if (response === "true") {
                    document.getElementById('duplicationResult').innerText = "이미 사용 중인 아이디입니다.";
                    document.getElementById('duplicationResult').style.color = "red";
                } else {
                    document.getElementById('duplicationResult').innerText = "사용할 수 있는 아이디입니다.";
                    document.getElementById('duplicationResult').style.color = "green";
                }
            } else {
                console.error('Request failed. Error code: ' + xhr.status);
            }
        };
        xhr.send();
    });

    /* 회원가입 가입하기 버튼 */
    if(document.getElementById("joinusform")) {
        /* 가입하기 버튼 클릭 했을때 이벤트 처리 */
        const singUpButton = document.getElementById("signUp");
        const joinusForm = document.getElementById("joinusform");

        singUpButton.addEventListener("click", function (event) {

            if (!validateForm()) {
                event.preventDefault();     // 기본 동작 차단
                alert("모든 항목을 입력해주세요");
            } else {
                alert("회원가입이 완료 되었습니다.");       // 알림메세지
                window.location.href = '/templates/login.html';        // 로그인 페이지로 이동
            }
        });

        function validateForm() {
            /* 필수항목 입력 검사 */
            const userId = document.getElementById("userId").value;
            const userPwd = document.getElementById("userPwd").value;
            const userPwd2 = document.getElementById("userPwd2").value;
            const userName = document.getElementById("userName").value;
            const userEmailId = document.getElementById("userEmailId").value;
            const birthYear = document.getElementById("birth-year").value;
            const birthMonth = document.getElementById("birth-month").value;
            const birthDay = document.getElementById("birth-day").value;
            const userGender = document.getElementById("input[name='userGender']:checked").value;

            if (userId === "" ||
                userPwd === "" ||
                userPwd2 === "" ||
                userName === "" ||
                userEmailId === "" ||
                birthYear === "년도" ||
                birthMonth === "월" ||
                birthDay === "일" ||
                !userGender) {
                return false;       // 모듣 항목이 입력되지 않았을때 false 반환
            }

            /* 필수 동의 항목 체크 여부 */
            const mustAgreeCheckboxes = document.querySelectorAll('.mustAgree');
            let mustAgreeChecked = true;
            mustAgreeCheckboxes.forEach(function (checkbox) {
                if (!checkbox.checked) {
                    mustAgreeChecked = false;
                }
            })

            if (!mustAgreeChecked) {
                alert("필수 동의 항목을 모두 체크해주세요.");
                return false;       // 필수 동의 항목 모두 체크 안됬을때 false 반환
            }
            return true;        // 모든 유효성 검사가 됬을때 true 반환
        }

        /* 체크박스 전체동의 체크 및 해제 */
        const agreeAllCheckbox = document.getElementById('agree-all');
        agreeAllCheckbox.addEventListener('click', function () {
            const checkboxes = document.querySelectorAll('input[name="agree"]');
            checkboxes.forEach(function (checkbox) {
                checkbox.checked = agreeAllCheckbox.checked;
            });
        });
    }
}
