
/*카테고리 내비게이션 */
const gnbCate = document.querySelector('.gnb-cate');
const subCategoryArea = document.querySelector('.sub-category-area');

// gnbCate에 마우스가 올라갔을 때
gnbCate.addEventListener('mouseover', function(event) {
subCategoryArea.style.display = 'block';
console.log(event.currentTarget);
});

// gnbCate에서 마우스가 떠났을 때
gnbCate.addEventListener('mouseout', function(event) {
// 마우스 이벤트가 .gnb-cate 및 .sub-category-area 영역을 벗어나지 않았을 때에만.sub-category-area를 유지합니다.
console.log(event.relatedTarget);
if (!event.relatedTarget || !event.relatedTarget.closest('.sub-category-area')) {
    subCategoryArea.style.display = 'none';
}
});

// subCategoryArea에 마우스가 올라갔을 때
subCategoryArea.addEventListener('mouseover', function() {
subCategoryArea.style.display = 'block';
});

// subCategoryArea에서 마우스가 떠났을 때
subCategoryArea.addEventListener('mouseout', function(event) {
console.log(event.relatedTarget);
// 마우스 이벤트가 .gnb-cate 영역을 벗어나지 않았을 때에만 .sub-category-area를 유지합니다.
if (!event.relatedTarget || !event.relatedTarget.closest('.gnb-cate')) {
    subCategoryArea.style.display = 'none';
}
});

// mouseover 했을떄 relatedTarget -> 현재 mouseover 된 요소 직전에 mouseevent가 발생한 요소를 가르킴
// mouseout 했을떄 relatedTarget ->  target한 요소에서 빠져나왔을때  가르키는 요소를 가르킴


// ----------------------------------------------------------------------

/* 커뮤니티 내비게이션 */
const gnbCommunity = document.querySelector('.gnb-community');
const subCommunityArea = document.querySelector('.sub-community-area');

// gnbCommunity에 마우스가 올라갔을 때
gnbCommunity.addEventListener('mouseover', function() {
    subCommunityArea.style.display = 'block';
});

// gnbCommunity에서 마우스가 떠났을 때
gnbCommunity.addEventListener('mouseout', function(event) {
console.log(event.relatedTarget);
if (!event.relatedTarget || !event.relatedTarget.closest('.sub-community-area')) {
    subCommunityArea.style.display = 'none';
}
});

// subCommunityArea에 마우스가 올라갔을 때
subCommunityArea.addEventListener('mouseover', function() {
    subCommunityArea.style.display = 'block';
});

// subCommunityArea에서 마우스가 떠났을 때
subCommunityArea.addEventListener('mouseout', function(event) {
console.log(event.relatedTarget);
// 마우스 이벤트가 .gnb-cate 영역을 벗어나지 않았을 때에만 .sub-category-area를 유지합니다.
if (!event.relatedTarget || !event.relatedTarget.closest('.gnb-community')) {
    subCommunityArea.style.display = 'none';
}
});
