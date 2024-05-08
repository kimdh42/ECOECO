
window.onload = function() {
    let $loginUserNo = document.querySelector('#loginUserNo').value;
    // console.log("$loginUserNo type : " + typeof ($loginUserNo));
    // console.log("$loginUserNo : " + $loginUserNo);

    let url = '/user/payment/buyer?userNo=' + $loginUserNo;
    // console.log("url : " + url);
    let url2 = window.location.search;
    // console.log("url2 : " + url2);

    <!-- 리워드 셀렉트 박스를 동적으로 만드는 구문 -->
    let $projectNo = document.querySelector('#projectNo').value;
    let url3 = '/user/payment/reward?projectNo=' + $projectNo;

    fetch(url3).then(res => res.json()).then(data => {
// fetch('/user/payment/reward').then(res => res.json()).then(data => {
        const $rewardNo = document.querySelector('#rewardNo');
        let $totalPrice = document.querySelector('#totalPrice');
        let $price = document.querySelector('#price');
        let $orderPrice = document.querySelector('#orderPrice');

        data.forEach($reward => {
            const $option = document.createElement('option');
            $option.textContent = $reward.rewardName + ' ( 리워드 금액' + $reward.rewardPrice.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + ' ) ';

            $option.value = $reward.rewardNo;
            $option.setAttribute('data-price', $reward.rewardPrice.toString().replace(/\\B(?=(\\d{3})+(?!\\d))/g, ","));
            $option.setAttribute('data-rewardName', $reward.rewardName);
            $rewardNo.appendChild($option);
        });

        $rewardNo.addEventListener('change', function() {
            const selectedOption = this.options[this.selectedIndex];
            const rewardPrice = selectedOption.dataset.price; // 리워드 금액을 가져옴
            const rewardName = selectedOption.dataset.rewardName; // 리워드 네임을 가져옴

            // 가져온 리워드 금액을 사용하여 필요한 작업 수행
            $price.innerText = rewardPrice.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + "원";
            $totalPrice.innerText = rewardPrice.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + "원";
            $orderPrice.value = rewardPrice;
        });

    });

    fetch('/user/payment/delivery' + url2).then(res => res.json()).then(data => {
        data.forEach($delivery => {
            // console.log("$delivery.deliveryYN : " + $delivery.deliveryYN);
            if($delivery.deliveryYN == "Y") {
                document.getElementById("deliveryInfo").classList.remove("hidden");
                document.getElementById("projectName").innerText = $delivery.projectName;
                document.getElementById("orderCategory").value = "배송상품";
            } else {
                document.getElementById("orderCategory").value = "미배송상품";
            }
        });
    });

    fetch('/user/payment/bank').then(res => res.json()).then(data => {
        const $accountNo = document.querySelector('#accountNo');

        data.forEach($bankAccount => {
            const $option = document.createElement('option');
            $option.textContent = $bankAccount.bankName;
            $option.value = $bankAccount.accountNo;
            $accountNo.appendChild($option);
        });
    });

    fetch(url).then(res => res.json()).then(data => {
        const $buyerName = document.querySelector('#buyerName');
        const $buyerName2 = document.querySelector('#buyerName2');
        const $buyerTel = document.querySelector('#buyerTel');
        const $zipCode = document.querySelector('#zipCode');
        const $address1 = document.querySelector('#address1');
        const $address2 = document.querySelector('#address2');
        const $buyerAccount = document.querySelector('#buyerAccount');

        data.forEach($user => {
            var addrArray = $user.userAddress.split('$');
            // console.log(addrArray[0]); // 우편번호
            // console.log(addrArray[1]); // 주소
            // console.log(addrArray[2]); // 상세주소

            $buyerName.value = $user.userName;
            $buyerName2.value = $user.userName;
            $buyerTel.value = $user.userPnum;
            $zipCode.value = addrArray[0];
            $address1.value = addrArray[1];
            $address2.value = addrArray[2];

            const select = document.querySelector('#accountNo');
            for(var i = 0; i < select.options.length; i++) {
                // console.log("accountNo : " + $user.accountNo);
                if(select.options[i].value == $user.accountNo) {
                    select.options[i].selected = true;
                    break;
                }
            }

            $buyerAccount.value = $user.userAccount;
        });

    });
};


// 라디오 버튼 누르면 display 상태 변경
function showDiv() {
    var selectedValue = document.querySelector('input[name="paymentCategory"]:checked').value;

    // console.log(typeof(selectedValue));
    // console.log(selectedValue);

    if (selectedValue === "예약결제") {
        document.getElementById("bankInfo").classList.remove("hidden");
    } else if (selectedValue === "즉시결제") {
        document.getElementById("bankInfo").classList.add("hidden");
    }

}

// 폼 입력값 유효성 체크
function CheckForm() {
    // alert("rewardNo : " + orderForm.rewardNo.value);
    if( orderForm.rewardNo.value === "" ) {

        orderForm.rewardNo.focus();
        alert("리워드를 선택해 주십시오.");

        return false;
    }
}
