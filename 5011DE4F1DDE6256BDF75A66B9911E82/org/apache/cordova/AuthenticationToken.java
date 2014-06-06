package org.apache.cordova;

public class AuthenticationToken {
    private String password;
    private String userName;

    public String getPassword() {
        return this.password;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setPassword(String r1_String) {
        this.password = r1_String;
    }

    public void setUserName(String r1_String) {
        this.userName = r1_String;
    }
}