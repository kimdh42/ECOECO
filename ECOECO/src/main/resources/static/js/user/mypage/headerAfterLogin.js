// 로그인 상태 확인 엔드포인트
const loginStatusEndpoint = '/user/mypage/loginStatus';

// 버튼 요소 선택
const loginButton = document.querySelector('.login');
const joinButton = document.querySelector('.join');

// 로그인 상태 확인 요청 보내기
fetch(loginStatusEndpoint)
    .then(response => response.json())
    .then(data => {
        // 서버에서 반환한 로그인 상태에 따라 버튼 텍스트와 클릭 이벤트 조작
        if (data.loginStatus) {
            loginButton.textContent = '마이페이지';
            loginButton.onclick = function() {
                location.href = '/user/mypage/mypageMain';
            };
            joinButton.textContent = '로그아웃';
            joinButton.onclick = function() {
                location.href = '/user/mypage/logout';
            };
        } else {
            loginButton.textContent = '로그인';
            loginButton.onclick = function() {
                location.href = '/login';
            };
            joinButton.textContent = '회원가입';
            joinButton.onclick = function() {
                location.href = '/joinus';
            };
        }
    })
    .catch(error => {
        console.error('Error fetching login status:', error);
    });
