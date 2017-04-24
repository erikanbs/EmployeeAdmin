<!DOCTYPE HTML>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

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
					<li><a href="/op">Operating Plan</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><p class="navbar-text">${user}</p></li>
				</ul>
			</div>	
		</div>
		</div>

		<script>			
		
		function doAjaxPost(opYear) {
			$.ajax({
			     type : "Post", 
			     url : "/op/search", 
			     data : "opYear=" + opYear,
			     success : function(response) {
			      //alert(response); 
			    	 $(".tab-content").html(response);
			     },
			     error : function(e) {
			      alert('Error: ' + e); 
			     }
			    });
		}
		
		function doAjaxPostCountry(opYear, countryId, currencyId) {
			$.ajax({
			     type : "Post", 
			     url : "/report/op/country", 
			     data : "opYear=" + opYear + "&countryId=" + countryId + "&currencyId=" + currencyId,
			     success : function(response) {
			      alert(response); 
			    	 //$(".tab-content").html(response);
			     },
			     error : function(e) {
			      alert('Error: ' + e); 
			     }
			    });
		}
		
		function doSubmitSearch() {
			//$('#opSearch').submit();
			document.getElementById("opFormDetails").submit();
		}
		
		$(document).ready(function(){
									
			$('#opYearList').on('change', function() {	
			  var opList = document.getElementById("opYearList");
        var opSelect = opList.options[opList.selectedIndex].value;
				document.getElementById("opYear").value = opSelect;				
				doSubmitSearch();
			});
			
			$('a[data-toggle="tab"]').on('shown.bs.tab', function(e){
		        var currentTab = $(e.target).text(); // get current tab
		        var LastTab = $(e.relatedTarget).text(); // get last tab
		        
		        var href = $(e.target).attr("href");
		        var countryId = href.substring(href.indexOf("b") + 1, href.indexOf("_"));
		        var currencyId = href.substring(href.indexOf("_") + 1, href.indexOf("_", href.indexOf("_") + 1) );
		        var bteId = href.substring(href.indexOf("_", href.indexOf("_") + 1) + 1 );
		        var opYear = document.getElementById("opYear").value;
		        document.getElementById("countryId").value = countryId;	
		        document.getElementById("currencyId").value = currencyId;
		        document.getElementById("bteId").value = bteId;
		        
		        e.preventDefault();
		        $('#opFormDetails').attr('action', "/report/op/country").submit();
		    });
					
		});	
		</script>

 <form:form method="post" action="/report/op/search" class="form-horizontal" modelAttribute="opFormDetails">

			<form:hidden id="opYear" path="opYear" />
			<form:hidden id="countryId" path="countryId"/>
			<form:hidden id="currencyId" path="currencyId"/>
			<form:hidden id="bteId" path="bteId"/>
			<form:hidden id="countryId" path="countryId"/>

			<div class="panel panel-default">
				<div class="panel-body">
				  <div class="dropdown">
						<label class="control-label" for="opYearList">OP Year</label> 
						<select name="opYearList" id="opYearList">
							<c:forEach var="year" items="${opYearList}">
								<option value="${year.key}"
									${year.key == opYear ? 'selected="selected"' : ''}>${year.value}</option>
							</c:forEach>
						</select>
					</div>
				</div>
			</div>

			<!-- <div class="nav nav-tabs" id="myTabs"> -->
					<ul class="nav nav-tabs" id="myTabs">
						<c:forEach var="country" varStatus="status" items="${countries}">
							<li class="${(country.ctyId == opFormDetails.countryId && country.bteId == opFormDetails.bteId ) ? 'active' : 'nav-item'}" >
							 <a class= "nav-link" 
							    data-toggle="tab"
								  href="#tab${country.ctyId}_${country.localCryId}_${country.bteId}">${country.country}(${country.localCryIsoCode})</a></li>
						</c:forEach>
					</ul>
					<!--<div class="tab-content"> <div id="myTabContents" class="tab-pane fade"> -->

					<c:choose>
						<c:when test="${mode == 'MODE_SHOW'}">
							<div class="container text-center" id="employeeDiv">
								<h3>Operating Plan</h3>
								<hr>
								<!-- <div class="table-responsive"> -->
								<table class="table table-striped table-bordered text-left">
									<thead>
										<tr>
											<th>Mail Date</th>
											<th>Close Date</th>
											<th>Country</th>
											<th>Insurer</th>
											<th>Campaign</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="op" items="${opResultList}">
											<tr>
												<td>${op.mailDate}</td>
												<td>${op.closeDate}</td>
												<td>${op.country}</td>
												<td>${op.insurer}</td>
												<td>${op.campaignNumber}</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
				  	<!--table-responsive </div> -->
				</c:when>
				</c:choose>
		</form:form>
</body>
</html>