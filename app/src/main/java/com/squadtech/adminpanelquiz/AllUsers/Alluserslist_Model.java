package com.squadtech.adminpanelquiz.AllUsers;

public class Alluserslist_Model
{
    private String id;
    private String user_dp;
    private String user_name;
    private String user_email;

    public Alluserslist_Model()
    {
    }

    public Alluserslist_Model(String image, String user_name, String user_email, String id) {
        this.user_dp = image;
        this.user_name = user_name;
        this.user_email = user_email;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
