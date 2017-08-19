<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/includeTag.jsp"%>
<!DOCTYPE html>
<html>

    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>修改用户</title>
        <%@include file="/WEB-INF/view/include/includeCss.jsp" %></head>

    <body class="hold-transition skin-blue sidebar-mini">
        <div class="row">
            <div class="col-md-12">
                <form id="userEditForm">
                    <input id="usercode" type="hidden" name="usercode" value="<c:out value='${info.seUser.usercode}' escapeXml='false' />" />
                    <input id="action" type="hidden" name="action" value="do" />
                    <div class="box box-info" style="margin-top:10px;">
                        <div class="box-header with-border">
                            <h3 class="box-title">修改用户</h3></div>
                        <div class="box-body">
                            <div class="row">
                                <div class="col-sm-6">
                                    <div class="form-group">
                                        <label>账号</label>
                                        <div class="input-group" style="width: 100%">
                                            <input id="uname" type="text" class="form-control" name="uname" value="<c:out value='${info.seUser.uname}' escapeXml='false' />" readonly></div>
                                    </div>
                                </div>
                                <div class="col-sm-6">
                                    <div class="form-group">
                                        <label>昵称</label>
                                        <div class="input-group" style="width: 100%">
                                            <input id="pname" type="text" class="form-control" name="pname" value="<c:out value='${info.seUser.pname}' escapeXml='false' />"></div>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-6">
                                    <div class="form-group">
                                        <label>密码</label>
                                        <div class="input-group" style="width: 100%">
                                            <input id="password1" type="password" class="form-control" name="password1" value=""></div>
                                    </div>
                                </div>
                                <div class="col-sm-6">
                                    <div class="form-group">
                                        <label>密码确认</label>
                                        <div class="input-group" style="width: 100%">
                                            <input id="password2" type="password" class="form-control" name="password2" value=""></div>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-6">
                                    <div class="form-group">
                                        <label>出生日期</label>
                                        <div class="input-group" style="width: 100%">
                                            <input id="birthym" type="text" class="form-control" name="birthym" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" value="<fmt:formatDate value='${info.seUser.birthym }' pattern='yyyy-MM-dd' />"></div>
                                    </div>
                                </div>
                                <div class="col-sm-6">
                                    <div class="form-group">
                                        <label>联系方式</label>
                                        <div class="input-group" style="width: 100%">
                                            <input id="telcode" type="text" class="form-control" name="telcode" value="<c:out value='${info.seUser.telcode}' escapeXml='false' />"></div>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-6">
                                    <div class="form-group">
                                        <label>邮箱</label>
                                        <div class="input-group" style="width: 100%">
                                            <input id="email" type="text" class="form-control" name="email" value="<c:out value='${info.seUser.email}' escapeXml='false' />"></div>
                                    </div>
                                </div>
                                <div class="col-sm-6">
                                    <div class="form-group">
                                        <label>用户状态</label>
                                        <select name="visible" style="resize: none;" id="visible" class="form-control">
                                            <option <c:if test="${info.seUser.visible=='1' }">selected="selected"</c:if>value=1>正常</option>
                                            <option <c:if test="${info.seUser.visible=='0' }">selected="selected"</c:if>value=0>禁用</option></select>
                                    </div>
                                </div>
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

    $("#userEditForm").ajaxSubmit({
        type: 'POST',
        url: 'se_user_edit_action.do',
        beforeSubmit: function() {
            if ($("#uname").val() == '') {
                showFormWarm('uname', '账号不能为空！');
                return false;
            }
            if ($("#pname").val() == '') {
                showFormWarm('pname', '昵称不能为空！');
                return false;
            }
            if ($("#password1").val() != $("#password2").val()) {
                showFormWarm('password1', '两次密码不一致！');
                return false;
            }
            showLoad();
        },
        dataType: "json",
        success: function(data) {
            parent.userManage.reloadItemInit();
            closeLoad();
            showSuccess(data.msg);
        },
        error: function(XmlHttpRequest, textStatus, errorThrown) {
            parent.userManage.reloadItemInit();
            closeLoad();
            showError(XmlHttpRequest, textStatus, errorThrown);
        }
    });
}

</script>