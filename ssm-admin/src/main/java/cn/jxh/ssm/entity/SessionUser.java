package cn.jxh.ssm.entity;

import java.util.List;

public class SessionUser {

    private Long userid;
    private String usercode;
    private String uname;
    private String pname;
    private String email;
    private String telcode;
    private List<SeRole> seRoleList;

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public String getUsercode() {
        return usercode;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelcode() {
        return telcode;
    }

    public void setTelcode(String telcode) {
        this.telcode = telcode;
    }

    public List<SeRole> getSeRoleList() {
        return seRoleList;
    }

    public void setSeRoleList(List<SeRole> seRoleList) {
        this.seRoleList = seRoleList;
    }
}
