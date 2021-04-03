<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

    <jsp:include page="WEB-INF/comment/head.jsp"/>

</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-12">
            <h1>SSM-CURD</h1>
        </div>
    </div>
    <div class="row">
        <div class="col-md-4 col-md-offset-8">
            <%--            直接在标签内部进行绑定，实现点击出现模态框。也可以使用单击事件--%>
            <%--            <button id="add_emp_btn" class="btn btn-primary" data-toggle="modal" data-target="#add_emp">增加</button>--%>
            <button id="add_emp_btn" class="btn btn-primary">增加</button>
            <button id="del_emp_btn" class="btn btn-danger">删除</button>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12">
            <table class="table table-hover" id="page_table">
                <thead>
                <tr>
                    <td>#</td>
                    <td>lastName</td>
                    <td>email</td>
                    <td>salary</td>
                    <td>departmentName</td>
                    <td>操作</td>
                </tr>
                </thead>
                <tbody>

                </tbody>

            </table>
        </div>
    </div>
    <div class="row">
        <%--分页信息--%>
        <div class="col-md-5" id="page_info"></div>
        <%--分页条--%>
        <div class="col-md-12 col-md-offset-8" id="pages">
            <nav aria-label="Page navigation">
                <ul class="pagination"></ul>
            </nav>
        </div>
    </div>
</div>

<%--员工添加模态框--%>
<div class="modal fade" tabindex="-1" id="add_emp" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">员工添加</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <div class="form-group">
                        <label for="lastName" class="col-sm-2 control-label">LastName</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="lastName" id="lastName"
                                   placeholder="lastName">
                            <span class="help-block"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="email" class="col-sm-2 control-label">Email</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="email" id="email" placeholder="email">
                            <span class="help-block"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="salary" class="col-sm-2 control-label">Salary</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="salary" id="salary" placeholder="salary">
                            <span class="help-block"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">DepartmentName</label>
                        <div class="col-sm-4">
                            <select id="deptSelect" class="form-control" name="departmentId"></select>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" id="emp_save_btn">保存</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<script type="text/javascript">
    $(function () {
        to_page(1);
    });
    var saveTotalPage;

    //向数据库发送Ajax请求的函数
    function to_page(pn) {
        $.ajax({
            url: "${requestScope.path}emps/" + pn,
            type: "GET",
            success: function (result) {
                show_table(result);
                show_page(result);
                show_line(result);
                if ((result.extend.pageInfo.total) % result.extend.pageInfo.pageSize == 0)
                    saveTotalPage = result.extend.pageInfo.pages + 1;
                else
                    saveTotalPage = result.extend.pageInfo.pages;

            }
        });
    }

    //显示员工数据
    function show_table(result) {
        $("#page_table tbody").empty();
        $.each(result.extend.pageInfo.list, function (index, item) {
            var eid = $("<td></td>").append(item.employeeId);
            var lastName = $("<td></td>").append(item.lastName);
            var email = $("<td></td>").append(item.email);
            var salary = $("<td></td>").append(item.salary);
            var deptName = $("<td></td>").append(item.department == null ? "null" : item.department.departmentName);
            var edit = $("<button></button>").append("编辑")
                .append($("<span></span>").addClass("glyphicon glyphicon-pencil"))
                .addClass("btn-primary btn-sm");
            var del = $("<button></button>").append("删除")
                .append($("<span></span>").addClass("glyphicon glyphicon-trash"))
                .addClass("btn-danger btn-sm");
            var option = $("<td></td>").append(edit).append(" ").append(del);
            $("<tr></tr>").append(eid)
                .append(lastName)
                .append(email)
                .append(salary)
                .append(deptName)
                .append(option)
                .appendTo("#page_table tbody");
        });
    }

    //显示分页数据
    function show_page(result) {
        $("#page_info").empty();
        $("#page_info").append("当前第" + result.extend.pageInfo.pageNum + "页，共有" + result.extend.pageInfo.pages + "页，总计" + result.extend.pageInfo.total + "条记录");
    }

    //显示导航条
    function show_line(result) {
        var line = $("#pages nav ul");
        line.empty();
        var firstPage = $("<li></li>").append($("<a></a>").append("首页").attr("href", "#"));
        var prePage = $("<li></li>").append($("<a></a>").attr("href", "#").attr("aria-hidden", "true").append("&laquo;"));
        if (result.extend.pageInfo.hasPreviousPage == false) {
            firstPage.addClass("disabled");
            prePage.addClass("disabled");
        } else {
            firstPage.click(function () {
                to_page(1);
            });
            prePage.click(function () {
                to_page(result.extend.pageInfo.prePage);
            });
        }
        line.append(firstPage).append(prePage);
        $.each(result.extend.pageInfo.navigatepageNums, function (index, item) {
            var pageItem = $("<li></li>").append($("<a></a>").append(item).attr("href", "#"));
            if (item == result.extend.pageInfo.pageNum) {
                pageItem.addClass("active");
            }
            pageItem.click(function () {
                to_page(item);
            });
            line.append(pageItem);
        });
        var endPage = $("<li></li>").append($("<a></a>").append("末页").attr("href", "#"));
        var nextPage = $("<li></li>").append($("<a></a>").attr("href", "#").attr("aria-hidden", "true").append("&raquo;"));

        if (result.extend.pageInfo.hasNextPage == false) {
            endPage.addClass("disabled");
            nextPage.addClass("disabled");
        } else {
            endPage.click(function () {
                to_page(result.extend.pageInfo.pages);
            });
            nextPage.click(function () {
                to_page(result.extend.pageInfo.nextPage);
            })
        }
        line.append(nextPage).append(endPage);
    }

    function form_reset (ele){
        $(ele)[0].reset();
        // $(ele).find("*")
        $(ele).find(".help-block").text("");
    }


    //    点击保存员工
    $("#add_emp_btn").click(function () {
        //重置表单，防止Ajax第一次验证后失效
        // $("#add_emp form")[0].reset();
        form_reset("#add_emp form");
        $.ajax({
            url: "${requestScope.path}deptName",
            type: "GET",
            success: function (result) {
                show_deptName(result);
            }
        });
        $("#add_emp").modal({
            backdrop: "static"
        });
    });

    //发送ajax请求，保存员工信息
    $("#emp_save_btn").click(function () {
        if (!check_emp_form()) {
            return false;
        }

        if($("#emp_save_btn").attr("ajax-vali")=="error"){
            return false;
        }

        //保存员工的Ajax
        $.ajax({
            url: "${requestScope.path}save",
            type: "POST",
            data: $("#add_emp form").serialize(),
            success: function (result) {
                if(result.code==200){
                    if (result.extend.errField.email!=undefined) {
                        check_change_status("#email","error",result.extend.errField.email)
                    }
                }else{
                    $("#add_emp").modal("hide");
                    to_page(saveTotalPage);
                }
            }
        });
    });

    //校验表单数据
    function check_emp_form() {
        if (!(/(^[a-z0-9_-]{3,16}$)|(^[\u2E80-\u9FFF]+$)/).test($("#lastName").val())) {
            check_change_status("#lastName","error","用户名必须是汉字、字母、数字");
            return false;
        } else {
            check_change_status("#lastName","success","");
            // if (!(/^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/).test($("#email").val())) {
            //     check_change_status("#email","error","邮箱不合法");
            //     return false;
            // } else {
                check_change_status("#email","success","");
                if (!(/^[0-9]{0,8}$/).test($("#salary").val())) {
                    check_change_status("#salay","error","薪资需要为0-8位的整数或小数");
                    return false;
                } else {
                    check_change_status("#salay","success","");
                    return true;
                }
            }
        // }
    }

    //提取公共部分作为函数，校验只需要完成对函数的调用即可
    function check_change_status(ele,status,mes){
        $(ele).parent().removeClass("has-success has-error");
        $(ele).next("span").text("");
        if(status==="success"){
            $(ele).parent().addClass("has-success");
            $(ele).next("span").text(mes);
        }else{
            $(ele).parent().addClass("has-error");
            $(ele).next("span").text(mes);
        }
    }

    //发送Ajax校验用户名是否可用
        $("#lastName").change(function(){
            $.ajax({
                url:"${requestScope.path}selectByName/"+$("#lastName").val(),
                type:"GET",
                success:function(result){
                    if(result.code==100){
                        check_change_status("#lastName","success","用户名可用");
                        $("#emp_save_btn").attr("ajax-vali","success");
                    }
                    if(result.code==200){
                        check_change_status("#lastName","error",result.extend.va_mes);
                        $("#emp_save_btn").attr("ajax-vali","error");
                    }
                },

            });
        });

    //展示全部部门的名字
    function show_deptName(result) {
        $.each(result.extend.depts, function (index, item) {
            var dept = $("<option></option>").append(item.departmentName).attr("value", item.departmentId).appendTo("#deptSelect");
        });
    }
</script>
</body>

</html>
