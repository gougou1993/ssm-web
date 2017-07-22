package cn.jxh.ssm.common.utils;

import cn.jxh.ssm.entity.SeMenu;

import java.util.ArrayList;
import java.util.List;

public class MenuTree {
    private StringBuffer html = new StringBuffer();
    private SeMenu rootSeMenu;
    private List<SeMenu> seMenusList;


    public MenuTree(SeMenu rootSeMenu ,List<SeMenu> seMenusList) {
        this.rootSeMenu = rootSeMenu;
        this.seMenusList = seMenusList;
    }

    public String buildTree() {
        // html.append("<li class=\"treeview\">");
        for (SeMenu seMenu : seMenusList) {
            if (seMenu.getParentcode().equals(rootSeMenu.getMenucode())) {
                if (!getChildren(seMenu).isEmpty()) {
                    html.append("<li class=\"treeview\">");
                    html.append("\r\n<a href=\"#\" > <i class=\"" + seMenu.getClassname() + "\"></i><span>" + seMenu.getCaptionname() + "</span> <span class=\"pull-right-container\"> <i class=\"fa fa-angle-left pull-right\"></i> </span> </a>");
                    build(seMenu);
                    html.append("\r\n</li>");
                } else {
                    html.append("<li id=\"menu_" + seMenu.getMenucode() + "\">");
                    html.append("\r\n<a href=\"#\" class=\"treeview-page\" onclick=\"javascript:redirectPage('" + seMenu.getMenucode() + "','" + seMenu.getSourceurl() + "');\"> <i class=\"" + seMenu.getClassname() + "\"></i><span>" + seMenu.getCaptionname() + "</span> </a>");
                    html.append("</li>");
                }
            }
        }
        // html.append("\r\n</li>");
        return html.toString();
    }

    private void build(SeMenu seMenu) {
        List<SeMenu> children = getChildren(seMenu);
        if (!children.isEmpty()) {
            html.append("\r\n<ul class=\"treeview-menu\">");
            for (SeMenu child : children) {
                if (!getChildren(child).isEmpty()) {
                    html.append("\r\n<li id='menu_" + child.getMenucode() + "'><a href=\"#\" class=\"treeview-multi\" ><i class=\"" + child.getClassname() + "\"></i>" + child.getCaptionname() + "<span class=\"pull-right-container\"> <i class=\"fa fa-angle-left pull-right\"></i> </span></a>");
                    build(child);
                    html.append("</li>");
                } else {
                    html.append("\r\n<li id='menu_" + child.getMenucode() + "'><a href=\"#\" class=\"treeview-multi\"  onclick=\"javascript:redirectPage('" + child.getMenucode() + "','" + child.getSourceurl() + "');\"><i class=\"" + child.getClassname() + "\"></i>" + child.getCaptionname() + "</a>");
                    build(child);
                    html.append("</li>");
                }
            }
            html.append("\r\n</ul>");
        }

        // else {
        // for (SeMenu child : children) {
        // html.append("<a href=\"#\"><i class=\"" + child.getClassname() +
        // "\"></i> Level Two <span class=\"pull-right-container\"> <i class=\"fa fa-angle-left pull-right\"></i> </span>  </a>");
        // }
        // }
    }

    private List<SeMenu> getChildren(SeMenu seMenu) {
        List<SeMenu> children = new ArrayList<SeMenu>();
        String ucode = seMenu.getMenucode();
        for (SeMenu child : seMenusList) {
            if (ucode.equals(child.getParentcode())) {
                children.add(child);
            }
        }
        return children;
    }
}
