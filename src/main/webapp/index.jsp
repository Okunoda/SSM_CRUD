<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

    <jsp:include page="WEB-INF/comment/head.jsp"/>

</head>
<body>
<a href="emps/1" > 查看职员列表 </a>
<button type="button"  class="demo">file in load</button>
<script type="text/javascript">
    $(function(){
        $(".demo").click(function(){
            alert("hello file");
        })
    })
</script>
</body>

</html>
