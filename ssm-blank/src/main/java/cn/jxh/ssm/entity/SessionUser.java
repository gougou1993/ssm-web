package cn.jxh.ssm.entity;

import java.util.Set;

public class SessionUser {

    // 用户基本信息
    private String workno;
    private String pname;
    private String email;
    private String telcode;

    // 用户登录的部门及角色信息
    private String deptCode;
    private String deptName;

    // 拥有角色集合
    private Set<Long> roleIds;

    // 流程平台中sessionId
    private String processSessionId;

    public String getWorkno() {
        return workno;
    }

    public void setWorkno(String workno) {
        this.workno = workno;
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

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Set<Long> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(Set<Long> roleIds) {
        this.roleIds = roleIds;
    }

    public String getProcessSessionId() {
        return processSessionId;
    }

    public void setProcessSessionId(String processSessionId) {
        this.processSessionId = processSessionId;
    }

}
