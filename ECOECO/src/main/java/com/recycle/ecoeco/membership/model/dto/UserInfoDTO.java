package com.recycle.ecoeco.membership.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.recycle.ecoeco.accounting.model.dto.BankAccountDTO;
import com.recycle.ecoeco.accounting.model.dto.OrderDTO;
import com.recycle.ecoeco.accounting.model.dto.PaymentDTO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.time.LocalDate;
import java.util.*;


public class UserInfoDTO implements UserDetails {
    private int userNo;     // 회원 번호
    private String userId;      // 회원아이디
    private String userPwd;     // 회원 비밀번호
    private String userName;        // 회원 이름
    private String userPnum;        // 회원 전화번호
    private String userEmail;       // 회원 이메일
    private String userBirth;       // 회원 생년월일
    private char userGender;        // 회원 성별
    private int userGrade;      // 회원 등급
    private int userPoint;      // 회원 포인트
    private LocalDate userDate;     // 회원 가입일
    private String userAccount;        // 회원 계좌번호
    private String userAddress;     // 회원 주소
    private UserRole userRole;      // 회원 권한
    private boolean passResult;
    private BankAccountDTO bankAccount;
    private int accountNo;
    private int paymentNo;
    private int orderNo;
    private PaymentDTO payment;
    private OrderDTO order;

    public UserInfoDTO() {}

    public UserInfoDTO(int userNo, String userId, String userPwd, String userName, String userPnum, String userEmail, String userBirth, char userGender, int userGrade, int userPoint, LocalDate userDate, String userAccount, String userAddress, UserRole userRole, boolean passResult, BankAccountDTO bankAccount, int accountNo) {
        this.userNo = userNo;
        this.userId = userId;
        this.userPwd = userPwd;
        this.userName = userName;
        this.userPnum = userPnum;
        this.userEmail = userEmail;
        this.userBirth = userBirth;
        this.userGender = userGender;
        this.userGrade = userGrade;
        this.userPoint = userPoint;
        this.userDate = userDate;
        this.userAccount = userAccount;
        this.userAddress = userAddress;
        this.userRole = userRole;
        this.passResult = passResult;
        this.bankAccount = bankAccount;
        this.accountNo = accountNo;
    }

    public UserInfoDTO(int userNo, String userId, String userPwd, String userName, String userPnum, String userEmail, String userBirth, char userGender, int userGrade, int userPoint, LocalDate userDate, String userAccount, String userAddress, UserRole userRole, boolean passResult, BankAccountDTO bankAccount, int accountNo, int paymentNo, int orderNo, PaymentDTO payment, OrderDTO order) {
        this.userNo = userNo;
        this.userId = userId;
        this.userPwd = userPwd;
        this.userName = userName;
        this.userPnum = userPnum;
        this.userEmail = userEmail;
        this.userBirth = userBirth;
        this.userGender = userGender;
        this.userGrade = userGrade;
        this.userPoint = userPoint;
        this.userDate = userDate;
        this.userAccount = userAccount;
        this.userAddress = userAddress;
        this.userRole = userRole;
        this.passResult = passResult;
        this.bankAccount = bankAccount;
        this.accountNo = accountNo;
        this.paymentNo = paymentNo;
        this.orderNo = orderNo;
        this.payment = payment;
        this.order = order;
    }

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();

        String roleName = "";
        if(userRole != null) roleName = userRole.name();

        System.out.println(roleName);

        return Arrays.asList(new SimpleGrantedAuthority(roleName));
    }

    @Override
    public String getPassword() {
        return userPwd;
    }

    @Override
    public String getUsername() {
        return userId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public int getUserNo() {
        return userNo;
    }

    public void setUserNo(int userNo) {
        this.userNo = userNo;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPnum() {
        return userPnum;
    }

    public void setUserPnum(String userPnum) {
        this.userPnum = userPnum;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserBirth() {
        return userBirth;
    }

    public void setUserBirth(String userBirth) {
        this.userBirth = userBirth;
    }

    public char getUserGender() {
        return userGender;
    }

    public void setUserGender(char userGender) {
        this.userGender = userGender;
    }

    public int getUserGrade() {
        return userGrade;
    }

    public void setUserGrade(int userGrade) {
        this.userGrade = userGrade;
    }

    public int getUserPoint() {
        return userPoint;
    }

    public void setUserPoint(int userPoint) {
        this.userPoint = userPoint;
    }

    public LocalDate getUserDate() {
        return userDate;
    }

    public void setUserDate(LocalDate userDate) {
        this.userDate = userDate;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public boolean isPassResult() {
        return passResult;
    }

    public void setPassResult(boolean passResult) {
        this.passResult = passResult;
    }

    public BankAccountDTO getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccountDTO bankAccount) {
        this.bankAccount = bankAccount;
    }

    public int getPaymentNo() {
        return paymentNo;
    }

    public void setPaymentNo(int paymentNo) {
        this.paymentNo = paymentNo;
    }

    public int getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(int orderNo) {
        this.orderNo = orderNo;
    }

    public PaymentDTO getPayment() {
        return payment;
    }

    public void setPayment(PaymentDTO payment) {
        this.payment = payment;
    }

    public OrderDTO getOrder() {
        return order;
    }

    public void setOrder(OrderDTO order) {
        this.order = order;
    }

    public int getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(int accountNo) {
        this.accountNo = accountNo;
    }

    @Override
    public String toString() {
        return "UserInfoDTO{" +
                "userNo=" + userNo +
                ", userId='" + userId + '\'' +
                ", userPwd='" + userPwd + '\'' +
                ", userName='" + userName + '\'' +
                ", userPnum='" + userPnum + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userBirth='" + userBirth + '\'' +
                ", userGender=" + userGender +
                ", userGrade=" + userGrade +
                ", userPoint=" + userPoint +
                ", userDate=" + userDate +
                ", userAccount='" + userAccount + '\'' +
                ", userAddress='" + userAddress + '\'' +
                ", userRole=" + userRole +
                ", passResult=" + passResult +
                ", bankAccount=" + bankAccount +
                ", accountNo=" + accountNo +
                ", paymentNo=" + paymentNo +
                ", orderNo=" + orderNo +
                ", payment=" + payment +
                ", order=" + order +
                '}';
    }
}