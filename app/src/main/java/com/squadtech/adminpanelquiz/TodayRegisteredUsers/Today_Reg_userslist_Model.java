package com.squadtech.adminpanelquiz.TodayRegisteredUsers;

public class Today_Reg_userslist_Model
{
    private String user_dp;
    private String user_name;
    private String user_email;
    private String registered_date;

    public Today_Reg_userslist_Model()
    {
    }

    public Today_Reg_userslist_Model(String image, String user_name, String user_email, String registered_date) {
        this.user_dp = image;
        this.user_name = user_name;
        this.user_email = user_email;
        this.registered_date = registered_date;
    }

    public String getRegistered_date() {
        return registered_date;
    }

    public void setRegistered_date(String registered_date) {
        this.registered_date = registered_date;
    }

    public String getUser_dp() {
        return user_dp;
    }

    public void setUser_dp(String user_dp) {
        this.user_dp = user_dp;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }
}
