<!DOCTYPE HTML>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head> 
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE-edge">
	<meta http-equiv="Pragma" content="no-cache">
	<meta http-equiv="Cache-Control" content="no-cache">
	<meta http-equiv="Expires" content="Wed, 01 Feb 2017 00:00:00GMT">
    
    <title>Ekholabs | Home </title>
    
    <link href="/static/css/bootstrap.min.css" rel="stylesheet">    
    <link href="/static/css/style.css" rel="stylesheet">
    <script src="/static/js/jquery.min.js"></script>
    <script src="/static/js/bootstrap.min.js"></script>    
    
</head>
<body>

	<div role="navigation">
		<div class="navbar navbar-inverse">
			<a href='/admin' class="navbar-brand">Ekholabs</a>
			<div class="navbar-collapse collapse">
				<ul class="nav navbar-nav">
					<li><a href="/admin/new-employee">New Employee</a></li>
					<li><a href="/admin/all-employees">All Employees</a></li>
					<li><a href="/admin/search-employee">Search Employees</a></li>
					<li><a href="/report/op">Operating Plan</a></li>
				</ul>		
				<ul class="nav navbar-nav navbar-right">
          <li><p class="navbar-text">${user}</p></li>
        </ul>		
			</div>		
		</div>
		<c:choose>
			<c:when test="${mode == 'MODE_HOME'}">
				<div class="container" id="homeDiv">
					<div class="jumbotron text-center">
						<h1>Welcome to Employee Manager</h1>
					</div>		
				</div>
			</c:when>
			<c:when test="${mode == 'MODE_EMPLOYEES'}">
				<div class="container text-center" id="employeeDiv">
					<h3>My Employees</h3>
					<hr>
					<div class="table-responsive">
						<table class="table table-striped table-bordered text-left">
							<thead>
								<tr>
									<th>Id</th>
									<th>Name</th>
									<th>Email</th>
									<th>Business Unit</th>
									<th>Entered Date</th>
									<th></th>
									<th></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="employee" items="${employees}">
									<tr>
										<td>${employee.id}</td>
										<td>${employee.fullName}</td>
										<td>${employee.email}</td>
										<td>${employee.businessUnit}</td>
										<td><fmt:formatDate pattern="yyyy-MM-dd" value="${employee.enteredDate}"/></td>
										<td><a href="update-employee?id=${employee.id}"><span class="glyphicon glyphicon-pencil"></span></a></td>
										<td><a href="delete-employee?id=${employee.id}"><span class="glyphicon glyphicon-trash"></span></a></td>
									</tr>							
								</c:forEach>
							</tbody>
						</table>
					</div>					
				</div>
			</c:when>
			<c:when test="${mode == 'MODE_NEW' || mode == 'MODE_UPDATE'}">
				<div class="container text-left">
					<h3>Manage Employee</h3>
					<hr>
					<form class="form-horizontal" method="POST" action="save-employee">
						<input type="hidden" name="id" value="${employee.id}"/>
						<div class="form-group">
							<label class="control-label col-md-3">Name</label>
							<div class="col-md-7">
								<input type="text" class="form-control" name="fullName" value="${employee.fullName}"/>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">Email</label>
							<div class="col-md-7">
								<input type="text" class="form-control" name="email" value="${employee.email}"/>
							</div>
						</div>		
						<div class="dropdown">
								<label class="control-label col-md-3">Business Unit</label> 
								<select name="businessUnit" id="businessUnit" path="businessUnit.id">
									<c:forEach var="businessUnit" items="${businessUnits}">
										<option value="${businessUnit.businessUnit.id}" ${businessUnit.businessUnit == employee.businessUnit ? 'selected="selected"' : ''}>${businessUnit.description}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="pull-right">
							<input type="submit" class="btn btn-primary" value="Save"/>
						</div>
					</form>
				</div>
			</c:when>
			<c:when test="${mode == 'MODE_SEARCH'}">
				<div class="container text-center">
					<h3>Search Employee</h3>
					<hr>
					<form class="form-horizontal" method="GET" action="result-search-employee" modelAttribute="employee">
						<div class="form-group">
							<label class="control-label col-md-3">Name</label>
							<div class="col-md-7">
								<input type="text" class="form-control" name="fullName" value="${employee.fullName}"/>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">Email</label>
							<div class="col-md-7">
								<input type="text" class="form-control" name="email" value="${employee.email}"/>
							</div>
						</div>
						<div class="pull-right">
							<input type="submit" class="btn btn-primary" value="Search"/>
						</div>
					</form>
				</div>
				<div class="container text-center" id="employeeDiv">
					<h3>Results</h3>
					<hr>
					<div class="table-responsive">
						<table class="table table-striped table-bordered text-left">
							<thead>
								<tr>
									<th>Id</th>
									<th>Name</th>
									<th>Email</th>
                  <th>Entered Date</th>
									<th></th>
									<th></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="employee" items="${employees}">
									<tr>
										<td>${employee.id}</td>
										<td>${employee.fullName}</td>
										<td>${employee.email}</td>
										<td><fmt:formatDate pattern="yyyy-MM-dd" value="${employee.enteredDate}"/></td>
										<td><a href="update-employee?id=${employee.id}"><span class="glyphicon glyphicon-pencil"></span></a></td>
										<td><a href="delete-employee?id=${employee.id}"><span class="glyphicon glyphicon-trash"></span></a></td>
									</tr>							
								</c:forEach>
							</tbody>
						</table>
					</div>					
				</div>
			</c:when>
		</c:choose>	

</body>
</html>