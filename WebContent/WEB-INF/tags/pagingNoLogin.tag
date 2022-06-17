<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ tag import="org.springframework.util.StringUtils"%>
<%@tag pageEncoding="UTF-8"%>

<%
String categoryId = request.getParameter("categoryId");
%>
<%
String page = request.getParameter("page");
%>

<nav>
	<ul class="pagination pagination-lg justify-content-center">
		<li class="page-item  ${pageListHolder.firstPage ? 'disabled' : '' }">
			<a class="page-link"
			href="
					<c:url value="nologin/home.htm">	
						<c:if test='<%=categoryId != null%>'>
							<c:param name="categoryId" value='<%=categoryId%>' />
						</c:if>
						<c:if test='<%=page != null%>'>
							<c:param name="page" value='<%=Integer.parseInt(request.getParameter("page")) - 1 + ""%>' />
						</c:if>
					</c:url>
				">
				<span>&laquo;</span>
		</a>
		</li>
		<c:forEach begin="${pageListHolder.firstLinkedPage}"
			end="${pageListHolder.lastLinkedPage}" var="page">
			<li
				class="page-item ${page == pageListHolder.getPage() ? 'active' : ''}">
				<a class="page-link"
					href="
						<c:url value="nologin/home.htm">	
							<c:if test='<%= categoryId != null %>'>
								<c:param name="categoryId" value='<%= categoryId %>' />
							</c:if>
							<c:param name="page" value="${page + 1}" />
						</c:url>
					">
					${page + 1}
				</a>
			</li>
		</c:forEach>

		<li class="page-item  ${pageListHolder.lastPage ? 'disabled' : '' }">
			<a class="page-link"
			href="
					<c:url value="nologin/home.htm">	
						<c:if test='<%=categoryId != null%>'>
							<c:param name="categoryId" value='<%=categoryId%>' />
						</c:if>
						<c:param name="page" value='<%=Integer.parseInt(page == null ? "1" : page) + 1 + ""%>' />
					</c:url>
				">
				<span>&raquo;</span>
		</a>
		</li>
	</ul>
</nav>