package org.commerce.auth;

public class Admin {
    private int tryCount;
    private boolean isLoin = false;

    public Admin() {
        resetTryCount();
    }

    public Boolean login(String username, String password) throws IllegalAccessException {
        String username1 = "Admin";
        String password1 = "password123";
        if(username1.equals(username) && password1.equals(password)) {
            resetTryCount();
            this.isLoin = true;
            return true;
        }
        increaseTryCount();
        throw new IllegalAccessException("로그인 실패.");
    }

    public void logout() {
        this.isLoin = false;
        resetTryCount();
    }

    public boolean checkIsLoin() {
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
