package com.nyw.domain.domain.bean.response.user;

public class MineInfo {
    private UserInfo userInfoVo;
    private UserIncome userIncomeVo;

    public UserInfo getUserInfoVo() {
        return userInfoVo;
    }

    public void setUserInfoVo(UserInfo userInfoVo) {
        this.userInfoVo = userInfoVo;
    }

    public UserIncome getUserIncomeVo() {
        return userIncomeVo;
    }

    public void setUserIncomeVo(UserIncome userIncomeVo) {
        this.userIncomeVo = userIncomeVo;
    }
}
