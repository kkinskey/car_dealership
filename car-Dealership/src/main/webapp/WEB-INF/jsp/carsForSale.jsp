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
      <h3 class="masthead-brand">Car Dealership</h3>
      <nav class="nav nav-masthead justify-content-center">
        <a class="nav-link active" href="/">Home</a>
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
    <h1>Cars for Sale</h1>
    <a class="nav-link" href="/search">Search For Vehicle</a>
		<div>
	<c:choose>
		<c:when test="${not empty vehicles}">
		<table>
		<thead>
		<tr>
		<th>
		Status
		</th>
		<th>
		Contact
		</th>
		<th>
		Price
		</th>
		<th>
		Discounted Price
		</th>
		<th>
		Condition
		</th>
		<th>
		Miles
		</th>
		<th>
		Year
		</th>
		<th>
		Make
		</th>
		<th>
		Model
		</th>
		<th>
		Posted Date
		</th>
		<th>
		Picture
		</th>
		</tr>
		</thead>
		<tbody>
		<c:forEach var="item" items="${vehicles}">
		<tr>
		<td>${item.status}</td>
		<td>${item.sellerEmail}</td>
		<td>$${item.price}</td>
		<td>$${item.discountedPrice}</td>
		<td>${item.condition}</td>
		<td>${item.miles}</td>
		<td>${item.year}</td>
		<td>${item.make}</td>
		<td>${item.model}</td>
		<td>${item.postedDate}</td>
		<td>
		<a target="_blank" href="/resources/images/${item.carPicture}">
		<img style="border:1px solid #ddd; border-radius:4px; padding:5px;width:150px;" src="<spring:url value='/resources/images/${item.carPicture}' />">
		</a>
        </td>
		<td><a href="buycar-${item.sellerEmail}-${item.miles}-${item.price}">Purchase</a> </td>
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