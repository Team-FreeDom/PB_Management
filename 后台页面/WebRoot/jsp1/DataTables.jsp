<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%--引入JSTL标签库的核心库 --%>
<html>
<head>
<meta charset="utf-8" />
<title>jQueryDataTable实例</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="../css/jquery.dataTables.min.css">
    <link type="text/css" rel="stylesheet" href="../css/dataTables.tableTools.css" />
    <link type="text/css" rel="stylesheet" href="../css/dataTables.bootstrap.css" />
    
    
    <script type="text/javascript" src="../js/jquery.js"></script>
    <script type="text/javascript" src="../js/jquery.dataTables.min.js"></script>
    <script src="../js/bootstrap.min.js"></script>
    
</head>
<body>
	<table id="example" class="display" width="100%" cellspacing="0">
        <thead>
            <tr>
                <th>Name</th>
                <th>Position</th>
                <th>Office</th>
                
            </tr>
        </thead>
        <tfoot>
            <tr>
                <th>Name</th>
                <th>Position</th>
                <th>Office</th>
               
            </tr>
        </tfoot>
    </table>

	
	<script type="text/javascript">
	
	

		$(document).ready(function() {
    $('#example').DataTable( {
        "processing": true,
        "serverSide": true,
        "ajax": {
            "url": "tableDemoAjax.do",
            "type": "POST"
        },
        "columns": [
            { "data": "name" },
            { "data": "position" },
            { "data": "office" },
           
        ]
    } );
} );
	</script>

</body>
</html>
