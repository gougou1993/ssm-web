package cn.jxh.ssm.entity;


import java.util.Date;

public class SeMenu {

    private Long id;

    private String menucode;

    private String parentcode;

    private String captionname;

    private Long menuorder;

    private String sourceurl;

    private String classname;

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

    public String getMenucode() {
        return menucode;
    }

    public void setMenucode(String menucode) {
        this.menucode = menucode;
    }

    public String getParentcode() {
        return parentcode;
    }

    public void setParentcode(String parentcode) {
        this.parentcode = parentcode;
    }

    public String getCaptionname() {
        return captionname;
    }

    public void setCaptionname(String captionname) {
        this.captionname = captionname;
    }

    public Long getMenuorder() {
        return menuorder;
    }

    public void setMenuorder(Long menuorder) {
        this.menuorder = menuorder;
    }

    public String getSourceurl() {
        return sourceurl;
    }

    public void setSourceurl(String sourceurl) {
        this.sourceurl = sourceurl;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
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
