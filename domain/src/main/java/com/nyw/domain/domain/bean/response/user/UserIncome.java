package com.nyw.domain.domain.bean.response.user;

public class UserIncome {
    private int todayForecastIncome;
    private int thisMonthForecastIncome;
    private int lastMonthForecastIncome;
    private int yesterdayForecastIncome;

    public int getTodayForecastIncome() {
        return todayForecastIncome;
    }

    public void setTodayForecastIncome(int todayForecastIncome) {
        this.todayForecastIncome = todayForecastIncome;
    }

    public int getThisMonthForecastIncome() {
        return thisMonthForecastIncome;
    }

    public void setThisMonthForecastIncome(int thisMonthForecastIncome) {
        this.thisMonthForecastIncome = thisMonthForecastIncome;
    }

    public int getLastMonthForecastIncome() {
        return lastMonthForecastIncome;
    }

    public void setLastMonthForecastIncome(int lastMonthForecastIncome) {
        this.lastMonthForecastIncome = lastMonthForecastIncome;
    }

    public int getYesterdayForecastIncome() {
        return yesterdayForecastIncome;
    }

    public void setYesterdayForecastIncome(int yesterdayForecastIncome) {
        this.yesterdayForecastIncome = yesterdayForecastIncome;
    }
}
