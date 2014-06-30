<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<aside class="sidebar">
				<article class="widget search">
					<input id="visibleSearch" onkeyup="addSearch();" type="text" placeholder="<spring:message code='common.search'/>...">
 				</article><!-- end .search -->					
				<article class="widget latest_posts">
					<div class="widget_title">
						<h2> <span class="bold"><spring:message code="common.categories"/></span> </h2>
						<div class="title_sep_container"><div class="title_sep"></div></div>
					</div><!-- end .title -->
					<ul id="categoryList">
					<c:forEach var="mainCategory" items="${categories}">
					<li> <a id="${mainCategory.catID}" href="javascript:addCategory('${mainCategory.catID}');">${mainCategory.name}</a> </li>
					</c:forEach>	
					</ul>	
				</article><!-- end .latest_posts -->
				<article class="widget">
					<div class="widget_title">
						<h2> <span class="bold">Skills</span> </h2>
						<div class="title_sep_container"><div class="title_sep"></div></div>
					</div><!-- end .title -->
					
					<ul id="skillList" class="tagList">
						<c:forEach var="skill" items="${skills}">
						<li id="${skill.name}"><a href="javascript:addSkill('${skill.name}');">${skill.name}</a></li>
						</c:forEach>
					</ul>
					<!-- end ul.tagList -->
				</article>
			</aside>