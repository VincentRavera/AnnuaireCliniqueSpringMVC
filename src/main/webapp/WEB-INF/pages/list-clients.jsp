

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html lang="fr">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title><spring:message code="index.pagetitle" /></title>

    <!-- Bootstrap Core CSS -->
    <link href="../resources/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="../resources/metisMenu/dist/metisMenu.min.css" rel="stylesheet">

    <!-- Timeline CSS -->
    <link href="../resources/css/timeline.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="../resources/css/sb-admin-2.css" rel="stylesheet">

    <!-- Morris Charts CSS -->
    <link href="../resources/morrisjs/morris.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="../resources/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

    <div id="wrapper">

        <!-- Navigation -->
        <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
            <%@include file="top-links.jsp" %>

            <%@include file="navbar.jsp" %>
        </nav>

        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Dashboard</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            
            <%@include file="row1.jsp" %>
            
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    
                    <!-- /.panel -->
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <i class="fa fa-bar-chart-o fa-fw"></i> <spring:message code="list-clients.title" />
                            <div class="pull-right">
                                <div class="btn-group">
                                    <button type="button" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown">
                                        Actions
                                        <span class="caret"></span>
                                    </button>
                                    <ul class="dropdown-menu pull-right" role="menu">
                                        <li><a href="edit.html"><spring:message code="list-clients.action.add" /></a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-lg-12">
                                    <div class="table-responsive">
                                        <table class="table table-bordered table-hover table-striped">
                                            <thead>
                                                <tr>
                                                    <th>#</th>
                                                    <th><spring:message code="list-clients.lastname" /></th>
                                                    <th><spring:message code="list-clients.firstname" /></th>
                                                    <th><spring:message code="list-clients.phonenumber" /></th>
                                                    <th><spring:message code="list-clients.address" /></th>
                                                    <th><spring:message code="list-clients.animals" /></th>
                                                    <th colspan="2"><spring:message code="list-clients.action" /></th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                            <c:url value="/animaux/list-animals.html" var="listUrl"/>
                                                <c:forEach items="${clients}" var="client">
                                                
                                                    <tr>
                                                        <td><c:out value="${client.id}"></c:out></td>
                                                        <td><c:out value="${client.nom}"></c:out></td>
                                                        <td><c:out value="${client.prenom}"></c:out></td>
                                                        <td><c:out value="${client.numero}"></c:out></td>
                                                        <td><a href="address-client.html?id=${client.id}"><spring:message code="list-clients.action.address" /></a></td>
                                                        <td><a href="${listUrl}?id=${client.id}"><spring:message code="list-clients.action.animal" /></a></td>
                                                        <td><a href="edit.html?id=${client.id}"><spring:message code="list-clients.action.modify" /></a></td>
                                                        <td>
                                                        <form:form action="delete.html" method="POST">
                                                            <input type="hidden" name="id" value="${client.id}"/>
                                                            <input type="submit" value="<spring:message code="list-clients.action.delete" />" />
                                                        </form:form>
                                                        </td>
                                                    
                                                    </tr>
                                                    
                                                 </c:forEach>
                                                 
                                            </tbody>
                                            <tfoot>
                                                <tr>
                                                    <td colspan="8" style = "text-align:center">
                                                        <a href="edit.html"><spring:message code="list-clients.action.add" /></a>
                                                    </td>
                                                </tr>
                                            </tfoot>
                                        </table>
                                    </div>
                                    <!-- /.table-responsive -->
                                </div>
                                <!-- /.col-lg-8 (nested) -->
                            </div>
                            <!-- /.row -->
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-8 -->
            </div>
            <!-- /.row -->
        </div>
        <!-- /#page-wrapper -->

    </div>
    <!-- /#wrapper -->

    <!-- jQuery -->
    <script src="../resources/jquery/dist/jquery.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="../resources/bootstrap/dist/js/bootstrap.min.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="../resources/metisMenu/dist/metisMenu.min.js"></script>

    <!-- Morris Charts JavaScript -->
    <script src="../resources/raphael/raphael-min.js"></script>
    <script src="../resources/morrisjs/morris.min.js"></script>
    <script src="../resources/js/morris-data.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="../resources/js/sb-admin-2.js"></script>

</body>

</html>