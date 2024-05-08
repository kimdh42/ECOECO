window.onload = function () {

    // const loginForm = document.getElementById("loginButton");
    // loginForm.addEventListener("click", loginCheck);
    // function loginCheck() {
    //     event.preventDefault();     // 제출동작 방지
    //
    //     const userId = document.querySelector(".inputId").value;
    //     const userPwd = document.querySelector(".inputPwd").value;
    //
    //     // AJAX 요청
    //     const xhr = new XMLHttpRequest();
    //
    //     xhr.open("POST", "/user/mypage/login");
    //     xhr.setRequestHeader("Content-Type", "application/json");
    //     xhr.onreadystatechange = function () {
    //
    //         if (xhr.readyState === XMLHttpRequest.DONE) {
    //             if (xhr.status === 200) {
    //                 try {
    //                     const response = JSON.parse(xhr.responseText) //json타입의 데이터를 , Object타입으로 파싱
    //                     console.log("response = " + response);
    //                     if (response.success) {
    //                         // 로그인 성공
    //                         if(response.managerYN === 'Y') {
    //                             window.location.href = "/manager/mmain/mmain"; // mmain 페이지로 이동
    //                             console.log(response.managerYN);
    //                         } else {
    //                             /* user 인 경우 */
    //                             window.location.href = "/main"; // main 페이지로 이동
    //                             console.log(response.managerYN);
    //                         }
    //                     } else if (response.message === "password incorrect") {
    //                         alert("비밀번호가 틀렸습니다.");
    //                     } else {
    //                         alert("회원정보가 없습니다.");
    //                     }
    //                 } catch (error) {
    //                     console.error("JSON 파싱 오류:", error);
    //                 }
    //             } else {
    //                 console.error("요청 실패: " + xhr.status);
    //             }
    //         }
    //     };
    //     xhr.send(JSON.stringify({ userId: userId, userPwd: userPwd }));
    // }


    //  const loginForm = document.getElementById("loginButton");
    //  loginForm.addEventListener("click", loginCheck);
    //
    // async function loginCheck(event) {
    //      event.preventDefault(); // 제출 동작 방지
    //
    //      const userId = document.querySelector(".inputId").value;
    //      const userPwd = document.querySelector(".inputPwd").value;
    //
    //      try {
    //          const response = await fetch("/user/mypage/loginAction", {
    //              method: "POST",
    //              headers: {
    //                  "Content-Type": "application/json"
    //              },
    //              body: JSON.stringify({ userId, userPwd })
    //          });
    //          console.log(response);
    //
    //          if (response.ok) {
    //              const responseData = await response.json();
    //              console.log("response =", responseData);
    //              if (responseData.success) {
    //                  // 로그인 성공
    //                  if (responseData.userRole === "ADMIN") {
    //                      window.location.href = "/manager/mmain/mmain"; // mmain 페이지로 이동
    //                      console.log(responseData.userRole);
    //                      alert("이거 되나admin");
    //                  } else if(responseData.userRole === "USER") {
    //                      /* user 인 경우 */
    //                      window.location.href = "/user/mypage/mypageMain"; // main 페이지로 이동
    //                      console.log(responseData.userRole);
    //                      alert("이거 되나user");
    //                  }
    //              } else if (responseData.message === "password incorrect") {
    //                  alert("비밀번호가 틀렸습니다.");
    //              } else {
    //                  alert("회원정보가 없습니다.");
    //              }
    //          } else {
    //              console.error("요청 실패:", response.status);
    //          }
    //      } catch (error) {
    //          console.error("오류 발생:", error);
    //      }
    //  }
}