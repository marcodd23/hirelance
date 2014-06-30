<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="isFreelance" value="${userLogged.freelanceProfile eq null}"></c:set>
<c:set var="isClient" value="${userLogged.clientProfile eq null}"></c:set>
<script type="text/javascript" >
var category="<spring:message code='common.category'/>";
var name_table="<spring:message code='common.name'/>";
$("#account").addClass("current");
var isFreelance="${isFreelance}";
var isClient="${isClient}";

var info="<spring:message code='project.info'/>";
var view_workroom="<spring:message code='project.view_workroom'/>";
var watchlist_remove="<spring:message code='project.watchlist_remove'/>";
var proposal_add="<spring:message code='project.proposal_add'/>";
var remove="<spring:message code='project.remove'/>";
var do_complete="<spring:message code='project.do_complete'/>";
var rate_this= "<spring:message code='project.rate'/>";
var reply= "<spring:message code='project.reply'/>";
var proposals="<spring:message code='project.proposal'/>";
var proposal_remove="<spring:message code='project.proposal_remove'/>";


//alert(isClient);
//alert(isFreelance);
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/merlin/js/myProjectsFilter.js" ></script>

<div id="watchlist_dialog" title="<spring:message code='project.watchlist_remove'/>">
<p>
    <span class="ui-icon ui-icon-circle-check" style="float:left; margin:0 7px 50px 0;"></span>
	  <spring:message code="project.watchlist_remove_ok"/>
	  
	  </p>
</div>
<script>$( "#watchlist_dialog" ).dialog({ 
	autoOpen: false,
	modal: true,
	//position: { my: "center top", at: "top top", of: window },
	buttons: {
        Ok: function() {
          $( this ).dialog( "close" );
        }
      }
});

</script>
<div id="dialog" title="<spring:message code="project.rate" />">
  <p>
    <span class="ui-icon ui-icon-circle-check" style="float:left; margin:0 7px 50px 0;"></span>
  <spring:message code="project.rate_done" />
  </p>
</div>
