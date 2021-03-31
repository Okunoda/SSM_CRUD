<link href="static/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
<%
    String path;
    path = request.getScheme()+"://"+
            request.getServerName()+":"+
            request.getServerPort()+
            request.getContextPath()+"/";
    request.setAttribute("path",path);
%>
<%--<script src="${path}webapp/static/js/jquery-1.12.4.min.js"></script>--%>
<base href="<%=path%>">
<script src="static/js/jquery-1.12.4.min.js"></script>
<script src="static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="static/bootstrap-3.3.7-dist/css/bootstrap.min.css" type="text/css"/>
<link rel="stylesheet" href="static/bootstrap-3.3.7-dist/css/bootstrap.css.map" type="text/css"/>
<link rel="stylesheet" href="static/bootstrap-3.3.7-dist/css/bootstrap.css" type="text/css"/>
<link rel="stylesheet" href="static/bootstrap-3.3.7-dist/css/bootstrap.min.css.map" type="text/css"/>