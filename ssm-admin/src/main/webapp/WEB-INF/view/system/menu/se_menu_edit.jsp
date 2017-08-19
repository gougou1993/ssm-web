<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/includeTag.jsp"%>
<!DOCTYPE html>

<%@include file="/WEB-INF/view/include/includeJs.jsp"%>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>修改菜单</title>
        <%@include file="/WEB-INF/view/include/includeCss.jsp" %>
    </head>
    <body class="hold-transition skin-blue sidebar-mini">
        <div class="row">
            <div class="col-md-12">
                <form id="menuEditForm">
                    <input id="menucode" type="hidden" name="menucode" value="<c:out value='${info.seMenu.menucode}' escapeXml='false' />" />
                    <input id="parentcode" type="hidden" name="parentcode" value="<c:out value='${info.seMenu.parentcode}' escapeXml='false' />" />
                    <input id="action" type="hidden" name="action" value="do" />
                    <div class="box box-info" style="margin-top:10px;">
                        <div class="box-header with-border">
                            <h3 class="box-title">修改菜单</h3></div>
                        <div class="box-body">
                            <div class="form-group">
                                <label>菜单名称</label>
                                <div class="input-group" style="width: 100%">
                                    <input id="captionname" type="text" class="form-control" name="captionname" value="<c:out value='${info.seMenu.captionname}' escapeXml='false' />"></div>
                            </div>
                            <div class="form-group">
                                <label>URL</label>
                                <div class="input-group" style="width: 100%">
                                    <input id="sourceurl" type="text" class="form-control" name="sourceurl" value="<c:out value='${info.seMenu.sourceurl}' escapeXml='false' />"></div>
                            </div>
                            <div class="form-group">
                                <label>图标</label>
                                <div class="input-group" style="width: 100%">
                                    <input id="classname" type="text" class="form-control" name="classname" value="<c:out value='${info.seMenu.classname}' escapeXml='false' />"></div>
                            </div>
                            <div class="form-group">
                                <label>排序</label>
                                <div class="input-group" style="width: 100%">
                                    <input id="menuorder" type="text" class="form-control" name="menuorder" value="<c:out value='${info.seMenu.menuorder}' escapeXml='false' />"></div>
                            </div>
                            <div class="form-group">
                                <label>菜单状态</label>
                                <select name="visible" style="resize: none;" id="visible" class="form-control">
                                    <option <c:if test="${info.seMenu.visible=='1' }">selected="selected"</c:if>value=1>正常</option>
                                    <option <c:if test="${info.seMenu.visible==0 }">selected="selected"</c:if>value="0">禁用</option></select>
                            </div>
                            <div class="form-group text-center">
                                <button type="button" class="btn btn-default" onclick="closeWindows()">取消</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                <button type="button" class="btn btn-primary" onclick="submintForm();">提交</button></div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>
<script type="text/javascript">
function submintForm() {
    $("#menuEditForm").ajaxSubmit({
        type: 'POST',
        url: 'se_menu_edit_action.do',
        beforeSubmit: function() {
            if ($("#captionname").val() == '') {
                showFormWarm('captionname', '菜单名称不为空！');
                return false;
            }
            showLoad();
        },
        dataType: "json",
        success: function(data) {
            parent.menuManage.reloadItemInit();
            parent.refreshzTree();
            closeLoad();
            showSuccess(data.msg);
        },
        error: function(XmlHttpRequest, textStatus, errorThrown) {
            parent.menuManage.reloadItemInit();
            parent.refreshzTree();
            closeLoad();
            showError(XmlHttpRequest, textStatus, errorThrown);
        }
    });
}
</script>