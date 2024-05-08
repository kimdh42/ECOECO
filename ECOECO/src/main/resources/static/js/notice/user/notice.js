// 검색 함수
function search() {
    // 선택한 옵션 가져오기
    const option = document.getElementById("option").value;

    // 검색어 가져오기
    const searchInput = document.getElementById("searchInput").value;

    // 검색결과를 표시할 공간 가져오기
    const searchResults = document.getElementById("searchResults")

    // 검색 결과 초기화
    searchResults.innerHTML = "";

    // 선택한 옵션에 따라 데이터 필터링 및 결과 출력
    const filteredData = allData.filter(function (item) {

        // 옵션에 따라 검색 조건 설정
        if (option === "제목") {
            return item.noticeTitle.includes(searchInput);

        } else if (option === "내용") {
            return item.noticeDetail.includes(searchInput);

        } else if (option === "제목+내용") {
            return item.noticeTitle.includes(searchInput) || item.noticeDetail.includes(searchInput);
        }
    });

    // 검색 결과가 없는 경우 처리
    if (filteredData.length === 0) {
        const noResultElement = document.createElement("div");
        noResultElement.textContent = "검색 결과가 없습니다.";
        searchResults.appendChild(noResultElement);

    } else {
        // 검색 결과를 화면에 표시
        filteredData.forEach(function (item) {
            const resultElement = document.createElement("div");
            resultElement.textContent = "제목: " + item.noticeTitle + ", 내용: " + item.noticeDetail;
            searchResults.appendChild(resultElement);
        });
    }
}

// 검색 입력 필드
const searchInput = document.querySelector("[data-search]");

// 검색 버튼
const searchButton = document.querySelector("[data-search-button]");

// 검색어에 따라 사용자 필터링하는 함수
function filterUsers(value) {
    // 사용자를 순회하면서 필터링
    users.forEach((user) => {
        const isVisible =
            user.name.includes(value) || user.email.includes(value);
        user.element.classList.toggle("hide", !isVisible);
    });
}

// 검색 입력 필드에 대한 keypress 이벤트 핸들러 등록 (엔터 키 입력 감지)
searchInput.addEventListener("keypress", (e) => {
    // Enter 키가 눌렸을 때 검색 수행
    if (e.key === "Enter") {
        const value = e.target.value.toLowerCase();
        filterUsers(value);
    }
});

// 검색 버튼에 대한 클릭 이벤트 핸들러 등록
searchButton.addEventListener("click", () => {
    const value = searchInput.value.toLowerCase();
    filterUsers(value);
});