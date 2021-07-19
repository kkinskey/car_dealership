<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<header class="masthead mb-auto">
    <div class="inner">
      <h3 class="masthead-brand">Cover</h3>
      <nav class="nav nav-masthead justify-content-center">
        <a class="nav-link active" href="/home">Home</a>
        <a class="nav-link" href="#">Features</a>
        <a class="nav-link" href="#">Contact</a>
        <a class="nav-link" href="/logout">Logout</a>
      </nav>
    </div>
  </header>
<meta charset="UTF-8">
</head>
<body>
    
   <%-- <jsp:include page="components/header.jsp"/> --%>

    <div class="slider-area ">

        <div class="single-slider slider-height2 d-flex align-items-center" data-background="assets/img/hero/category.jpg">
            <div class="container">
                <div class="row">
                    <div class="col-xl-12">
                        <div class="hero-cap text-center">
                            <h2><!-- Users --> <i class="fa fa-users" aria-hidden="true"></i></h2>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div>
    <h1>Vehicles for Sale</h1>
		<div>
	<c:choose>
		<c:when test="${not empty vehicles}">
		<table>
		<thead>
		<tr>
		<th>
		Vin Number
		</th>
		<th>
		Status
		</th>
		<th>
		Posted Date
		</th>
		<th>
		Purchase Date
		</th>
		<th>
		Seller Contact
		</th>
		<th>
		Buyer Contact
		</th>
		<th>
		Condition
		</th>
		<th>
		Posted Price
		</th>
		<th>
		Price Sold For
		</th>
		<th>
		Miles
		</th>
		<th>
		Make
		</th>
		<th>
		Model
		</th>
		<th>
		Year
		</th>
		</tr>
		</thead>
		<tbody>
		<c:forEach var="item" items="${vehicles}">
		<tr>
		<td>${item.vinNumber}</td>
		<td>${item.status}</td>
		<td>${item.postedDate}</td>
		<td>${item.purchaseDate}</td>
		<td>${item.sellerEmail}</td>
		<td>${item.customerEmail}</td>
		<td>${item.condition}</td>
		<td>${item.price}</td>
		<td>${item.discountedPrice}</td>
		<td>${item.miles}</td>
		<td>${item.make}</td>
		<td>${item.model}</td>
		<td>${item.year}</td>
		<td>
		<a target="_blank" href="/resources/images/${item.carPicture}">
		<img style="border:1px solid #ddd; border-radius:4px; padding:5px;width:150px;" src="<spring:url value='/resources/images/${item.carPicture}' />">
		</a>
        </td>
		</tr>
		</c:forEach>
		</tbody>
		</table>
		</c:when>
		<c:otherwise>
		<h3>No recent sales</h3>
		</c:otherwise>
		</c:choose>
	</div>

</body>
</html>