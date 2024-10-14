<%@page import="vn.iotstar.models.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- Header Section Begin -->
<header class="header">
	<div class="header__top">
		<div class="container">
			<div class="row">
				<div class="col-lg-6 col-md-7">
					<div class="header__top__left">
						<p>Free shipping, 30-day return or refund guarantee.</p>
					</div>
				</div>
				<div class="col-lg-6 col-md-5">
					<div class="header__top__right">
						<div class="header__top__links">
							<div class="container">
								<div class="row">
									<c:choose>
										<c:when test="${sessionScope.account == null}">
											<a href="${pageContext.request.contextPath}/login">Login</a>
                                | <a
												href="${pageContext.request.contextPath}/register">Register</a>
										</c:when>
										<c:otherwise>
											<!-- Profile Start -->
											<div class="profile-box ml-3">
												<div class="dropdown">
													<!-- Trigger button for dropdown -->
													<button
														class="btn dropdown-toggle bg-transparent border-0 d-flex align-items-center"
														type="button" id="profileDropdown"
														data-bs-toggle="dropdown" aria-expanded="false">
														<div class="profile-info d-flex align-items-center">
															<!-- Avatar image (rounded-circle ensures it's circular) -->
															<div class="image">
																<img
																	src="${pageContext.request.contextPath}/uploads/${imageUrl}"
																	alt="Profile Image" class="rounded-circle" width="40"
																	height="40">
															</div>
															<!-- Profile Name and Role -->
															<div class="ml-3">
																<h6 class="fw-bold mb-0">Adam Joe</h6>
																<p class="mb-0">Admin</p>
															</div>
														</div>
													</button>

													<!-- Dropdown Menu -->
													<ul class="dropdown-menu dropdown-menu-end"
														aria-labelledby="profileDropdown">
														<!-- Profile Info in Dropdown -->
														<li class="dropdown-item d-flex align-items-center p-2">
															<div class="image">
																<img
																	src="${pageContext.request.contextPath}/uploads/${imageUrl}"
																	alt="Profile Image" class="rounded-circle" width="40"
																	height="40">
															</div>
															<div class="ml-2">
																<h6 class="mb-0">Adam Joe</h6>
																<small class="text-muted">Email@gmail.com</small>
															</div>
														</li>
														<li><hr class="dropdown-divider"></li>
														<!-- Dropdown Menu Items -->
														<li><a
															class="dropdown-item text-dark text-capitalize"
															href="${pageContext.request.contextPath}/profile"><i
																class="fa-regular fa-user"></i> View Profile</a></li>
														<li><a
															class="dropdown-item text-dark text-capitalize" href="#0"><i
																class="fa-regular fa-bell"></i> Notifications</a></li>
														<li><a
															class="dropdown-item text-dark text-capitalize" href="#0"><i
																class="fa-regular fa-gear"></i> Settings</a></li>
														<li><hr class="dropdown-divider"></li>
														<li><a
															class="dropdown-item text-dark text-capitalize"
															href="${pageContext.request.contextPath}/logout"><i
																class="fa-solid fa-arrow-right-from-bracket"></i> Sign
																Out</a></li>

													</ul>
												</div>
											</div>
											<!-- Profile End -->

											<%-- <a href="${pageContext.request.contextPath}/member/myaccount">${sessionScope.account.fullname}</a>
                                | <a
												href="${pageContext.request.contextPath}/logout">Logout</a> --%>
										</c:otherwise>
									</c:choose>
								</div>
							</div>

						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="container">
		<div class="row">
			<div class="col-lg-3 col-md-3">
				<div class="header__logo">
					<a href="./index.html"><img src="${URL}/img/logo.png" alt=""></a>
				</div>
			</div>
			<div class="col-lg-6 col-md-6">
				<nav class="header__menu mobile-menu">
					<ul>
						<li class="active"><a href="./index.html">Home</a></li>
						<li><a href="./shop.html">Shop</a></li>
						<li><a href="#">Pages</a>
							<ul class="dropdown">
								<li><a href="./about.html">About Us</a></li>
								<li><a href="./shop-details.html">Shop Details</a></li>
								<li><a href="./shopping-cart.html">Shopping Cart</a></li>
								<li><a href="./checkout.html">Check Out</a></li>
								<li><a href="./blog-details.html">Blog Details</a></li>
							</ul></li>
						<li><a href="./blog.html">Blog</a></li>
						<li><a href="./contact.html">Contacts</a></li>
					</ul>
				</nav>
			</div>
			<div class="col-lg-3 col-md-3">
				<div class="header__nav__option">
					<a href="#" class="search-switch"><img
						src="${URL}/img/icon/search.png" alt=""></a> <a href="#"><img
						src="${URL}/img/icon/heart.png" alt=""></a> <a href="#"><img
						src="${URL}/img/icon/cart.png" alt=""> <span>0</span></a>
					<div class="price">$0.00</div>
				</div>
			</div>
		</div>
		<div class="canvas__open">
			<i class="fa fa-bars"></i>
		</div>
	</div>
</header>
<!-- Header Section End -->