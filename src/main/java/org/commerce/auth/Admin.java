package org.commerce.auth;

public class Admin {
    private int tryCount;
    private boolean isLoin = false;

    private String username = "Admin";
    private String password = "password123";

    public Admin() {
        resetTryCount();
    }

    public Boolean login(String username, String password) throws IllegalAccessException {
        if(this.username.equals(username) && this.password.equals(password)) {
            resetTryCount();
            this.isLoin = true;
            return true;
        }else{
            increaseTryCount();
            throw new IllegalAccessException("로그인 실패.");
        }
    }

    public void logout() {
        this.isLoin = false;
        resetTryCount();
    }

    public boolean getIsLoin() {
        return isLoin;
    }

    ///  시도 횟수 증가
    private void increaseTryCount() {
        this.tryCount++;
        checkTryCount();
    }
    ///  시도 횟수 초기화
    private void resetTryCount() {
        this.tryCount = 0;
    }

    /// 시도 횟수 확인
    private void checkTryCount() {
        if(this.tryCount >= 3) {
            resetTryCount();
            throw new RuntimeException("3회 이상 로그인 실패, 처음부터 다시 시도해주세요.");
        }
    }
}
