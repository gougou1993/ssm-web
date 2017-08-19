package cn.jxh.ssm.entity;


import java.io.Serializable;
import java.util.Date;

public class SeUser implements Serializable {

    private Long id;

    private String usercode;

    private String pname;

    private String password;

    private String uname;

    private Date birthym;

    private Byte[] pic;

    private String telcode;

    private String email;

    private Integer visible;

    private Date entrytime;

    private Date updatetime;

    private Long updateid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsercode() {
        return usercode;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public Date getBirthym() {
        return birthym;
    }

    public void setBirthym(Date birthym) {
        this.birthym = birthym;
    }

    public Byte[] getPic() {
        return pic;
    }

    public void setPic(Byte[] pic) {
        this.pic = pic;
    }

    public String getTelcode() {
        return telcode;
    }

    public void setTelcode(String telcode) {
        this.telcode = telcode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getVisible() {
        return visible;
    }

    public void setVisible(Integer visible) {
        this.visible = visible;
    }

    public Date getEntrytime() {
        return entrytime;
    }

    public void setEntrytime(Date entrytime) {
        this.entrytime = entrytime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public Long getUpdateid() {
        return updateid;
    }

    public void setUpdateid(Long updateid) {
        this.updateid = updateid;
    }
}
