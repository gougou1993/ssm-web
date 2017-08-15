<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/includeTag.jsp"%>
<!DOCTYPE html>
<html>

    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>关联用户</title>
        <%@include file="/WEB-INF/view/include/includeCss.jsp" %>
    </head>

<body class="hold-transition skin-blue sidebar-mini">
	<div class="row">
		<div class="col-md-12">
			<form id="pageFormTo" enctype="multipart/form-data">
				<input type="hidden" name="rolecode" id="rolecode" value="${info.rolecode}">
				<div class="box box-info" style="margin-top:10px;">
					<div class="box-header with-border">
						<h3 class="box-title">角色用户关联</h3>
					</div>

					<div class="box-body">
						<div class="col-xs-12">
							<div  class="form-group text-center">
								<table style="width:100%;">
									<tr style="height:40px;">
										<td style="text-align: center;" >
											<input style="width:150px;heigth:30px;" type="text" id="name" name="name" value="" class="form-control pull-left" >
											<button type="button" class="btn btn-default " onclick="checkInfo();">
												<i class="fa fa-search"></i>
											</button>
										</td><td></td><td></td>
									</tr>
									<tr>
										<td>待选人员</td>
										<td></td>
										<td>已选人员</td>
									</tr>
									<tr style="height:300px;">
										<td style="width: 200px;">
											<div class="form-group">
								                <select multiple class="form-control"  id="waitSelectStaff" name="waitSelectStaff" style="height: 300px;"></select>
							            	</div>
										</td>
										<td>
											<input type="button" class="inputbutton" style="width: 50px;" value=" &gt; " onclick="javascript:move('waitSelectStaff','selectedStaff');" />
											<br/><br/>
											<input type="button" class="inputbutton" style="width: 50px;" value=" &gt;&gt; " onclick="javascript:moveAll('waitSelectStaff','selectedStaff')" />
											<br/><br/>
											<input type="button" class="inputbutton" style="width: 50px;" value=' &lt; ' onclick="move('selectedStaff','waitSelectStaff');" />
											<br /><br/>
											<input type="button" class="inputbutton" style="width: 50px;" value=" &lt;&lt " onclick="javascript:moveAll('selectedStaff','waitSelectStaff')" />
										</td>
										<td style="width: 200px;">
											<div class="form-group">
								                <select multiple class="form-control" id="selectedStaff" name="selectedStaff" style="height: 300px;">
								                	<c:forEach items="${info.seUserList }" var="item">
								                		<option value="${item.usercode }"> ${item.pname }</option>
								                	</c:forEach>
								                </select>
							            	</div>
										</td>
									</tr>
								</table>
							</div>
						</div>
					</div>
					<div class="form-group text-center">
						<button type="button" class="btn btn-default" onclick="closeWindows()">取消</button>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button" class="btn btn-primary" onclick="submintForm();">提交</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>

<%@include file="/WEB-INF/view/include/includeJs.jsp"%>
<script type="text/javascript">

function submintForm() {
    var list = window.document.getElementById("selectedStaff");
    var rolecode = $("#rolecode").val();
    var vlaues = "";
    var text = "";
    for (i = 0; i < list.options.length; i++) {
        if (vlaues == "") {
            vlaues = list.options[i].value;
            text = list.options[i].text;
        } else {
            vlaues += "," + list.options[i].value;
            text += "," + list.options[i].text;
        }
    }
    alert(vlaues);
    $.ajax({
        type: 'post',
        url: "se_role_user_action.do",
        data: {
            'action': 'do',
            'vlaues': vlaues,
            'rolecode': rolecode
        },
        beforeSubmit: function() {
            //提交加载层
            showLoad();
        },
        dataType: "json",
        success: function(data) {
            closeLoad();
            showSuccess(data.msg);
        },
        error: function(XmlHttpRequest, textStatus, errorThrown) {
            closeLoad();
            showError(XmlHttpRequest, textStatus, errorThrown);
        }
    });
}

/*根据条件查询信息*/
function checkInfo() {
    var name = $("#name").val();
    if (name != "") {
        $.ajax({
            type: "post",
            url: "se_role_user_search.do",
            async: false,
            cache: false,
            dataType: "json",
            data: {
                'name': name
            },
            success: function(data) {
                if (data.status == 0) {
                    showWarm(data.msg);
                } else {
                    callbackDataHandler(data.seUserList);
                }
            },
            error: function(XmlHttpRequest, textStatus, errorThrown) {
                showError(XmlHttpRequest, textStatus, errorThrown);
            }
        });
    }
}

/*将查询返回值显示在页面上*/
function callbackDataHandler(data) {
    var waitSelectlist = window.document.getElementById("waitSelectStaff");
    if (waitSelectlist != null) {
        for (i = waitSelectlist.options.length; i >= 0; i--) {
            waitSelectlist.remove(0);
        }
    }
    if (data != null) {
        for (var i = 0; i < data.length; i++) {
            var addOption = document.createElement("option");
            addOption.value = data[i].usercode;
            addOption.text = data[i].pname;
            waitSelectlist.add(addOption);
        }
    }
}
function isExist(id, controlTarget) {
    var targetlist = window.document.getElementById(controlTarget);
    for (var i = 0; i < targetlist.options.length; i++) {
        if (id == targetlist.options[i].value) {
            return true;
        }
    }
    return false;
}
/*移动一个*/
function move(controlSource, controlTarget) {
    var waitSelectlist = window.document.getElementById(controlSource);
    var selectedlist = window.document.getElementById(controlTarget);
    var selectMode = "${selectMode}";
    if (selectMode == "single" && controlSource == "waitSelectStaff") {
        moveAll('selectedStaff', 'waitSelectStaff');
        if (selectedlist != null) {
            for (i = selectedlist.options.length; i >= 0; i--) {
                selectedlist.remove(0);
            }
        }
    }
    if (waitSelectlist != null && selectedlist != null) {
        var totalRecords = waitSelectlist.options.length;

        for (i = totalRecords - 1; i >= 0; i--) {
            if (waitSelectlist.options[i].selected) {
                if (!isExist(waitSelectlist.options[i].value, controlTarget)) {
                    var addOption = document.createElement("option");
                    addOption.value = waitSelectlist.options[i].value;
                    addOption.text = waitSelectlist.options[i].text;
                    selectedlist.add(addOption);
                }

                waitSelectlist.remove(i);
            }

        }
    }
}
/*移动多个*/
function moveAll(controlSource, controlTarget) {
    var waitSelectlist = window.document.getElementById(controlSource);
    var selectedlist = window.document.getElementById(controlTarget);

    if (waitSelectlist != null && selectedlist != null) {
        var totalRecords = waitSelectlist.options.length;

        for (i = totalRecords - 1; i >= 0; i--) {
            if (!isExist(waitSelectlist.options[i].value, controlTarget)) {
                var addOption = document.createElement("option");
                addOption.value = waitSelectlist.options[i].value;
                addOption.text = waitSelectlist.options[i].text;;
                selectedlist.add(addOption);
            }
            waitSelectlist.remove(i);
        }
    }
}
</script>