<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<link href="<c:url value="/resources/css/index.css" />" rel="stylesheet">
<meta charset="UTF-8">
</head>
<body>
<header class="masthead mb-auto">
    <div class="inner">
      <h3 class="masthead-brand">Cover</h3>
      <nav class="nav nav-masthead justify-content-center">
        <a class="nav-link active" href="/">Home</a>
        <a class="nav-link" href="#">Features</a>
        <a class="nav-link" href="#">Contact</a>
      </nav>
    </div>
  </header>
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
	<main class="form-signin">
		<div class="search">
			<form:form class="row g-3" method="post" action="/searchposts"
				modelAttribute="vehicle">
				<div class="col-md-6">
					<label for="inputPassword4" class="form-label">Price</label>
					<form:input path="price" type="text" class="form-control"
						id="inputPassword4"/>
				</div>
				<div class="col-md-6">
					<label for="inputEmail4" class="form-label">Make</label>
					<form:input path="make" type="text" class="form-control"
						id="inputEmail4" />
				</div>
				<div class="col-md-6">
					<label for="inputPassword4" class="form-label">Model</label>
					<form:input path="model" type="text" class="form-control"
						id="inputPassword4"/>
				</div>
		<%-- 		<div class="col-md-6">
					<label for="inputPassword4" class="form-label">Year</label>
					<form:input path="condition" type="text" class="form-control"
						id="inputPassword4"/>
				</div>
				<div class="col-md-6">
					<label for="inputPassword4" class="form-label">Condition (NEW or USED)</label>
					<form:input path="condition" type="text" class="form-control"
						id="inputPassword4"/>
				</div> --%>
<%-- 				<div class="col-md-6">
					<label for="inputPassword4" class="form-label">Used</label>
					<form:input path="condition" type="text" class="form-control"
						id="inputPassword4"/>
				</div> --%>
				<div class="col-12">
					<button type="submit" class="btn btn-primary">Search</button>
				</div>
			</form:form>
		</div>
	</main>
</body>
</html>