<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/includeTag.jsp"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>新增角色</title>
        <%@include file="/WEB-INF/view/include/includeCss.jsp" %></head>
    <body class="hold-transition skin-blue sidebar-mini">
        <div class="row">
            <div class="col-md-12">
                <form id="roleAddForm">
                    <input id="action" type="hidden" name="action" value="do" />
                    <div class="box box-info" style="margin-top:10px;">
                        <div class="box-header with-border">
                            <h3 class="box-title">新增角色</h3></div>
                        <div class="box-body">
                            <div class="form-group">
                                <label>角色名称</label>
                                <div class="input-group" style="width: 100%">
                                    <input id="roledesc" type="text" class="form-control" name="roledesc" value=""></div>
                            </div>
                            <div class="form-group">
                                <label>角色说明</label>
                                <textarea name="content" style="resize: none;" class="form-control" rows="3"></textarea>
                            </div>
                            <div class="form-group">
                                <label>角色状态</label>
                                <select name="visible" style="resize: none;" id="visible" class="form-control">
                                    <option value=1>正常</option>
                                    <option value="0">禁用</option></select>
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
<%@include file="/WEB-INF/view/include/includeJs.jsp"%>

<script type="text/javascript">
function submintForm() {
    $("#roleAddForm").ajaxSubmit({
        type: 'POST',
        url: 'se_role_add_action.do',
        beforeSubmit: function() {
            if ($("#roledesc").val() == '') {
                showFormWarm('roledesc', '角色名称不为空！');
                return false;
            }
            showLoad();
        },
        dataType: "json",
        success: function(data) {
            parent.roleManage.reloadItemInit();
            closeLoad();
            showSuccess(data.msg);
        },
        error: function(XmlHttpRequest, textStatus, errorThrown) {
            parent.roleManage.reloadItemInit();
            closeLoad();
            showError(XmlHttpRequest, textStatus, errorThrown);
        }
    });
}
</script>