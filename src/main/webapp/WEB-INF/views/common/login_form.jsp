<%@taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<div class="container">
	<div class="sixteen columns add-top add-bottom">
		<security:authorize access="!isAuthenticated()">
		<c:if test="${error}">
		<div class="notification error">
			<img src="${pageContext.request.contextPath}/resources/merlin/images/icons/error.png" alt="">
			<p><spring:message code="login.error"/></p>
		</div>
		</c:if>
		<c:if test="${ready}">
		<div class="notification success">
			<img src="${pageContext.request.contextPath}/resources/merlin/images/icons/success.png" alt="">
			<p><spring:message code="login.success"/></p>
		</div>
		</c:if>
		<c:if test="${change_ok}">
		<div class="notification success">
			<img src="${pageContext.request.contextPath}/resources/merlin/images/icons/success.png" alt="">
			<p><spring:message code="login.change_ok"/></p>
		</div>
		</c:if>
		<c:if test="${retrival_ok}">
		<div class="notification info">
			<img src="${pageContext.request.contextPath}/resources/merlin/images/icons/info.png" alt="">
			<p><spring:message code="login.retrival_ok" /> </p>
		</div>
		</c:if>
		<c:if test="${retrival_exception}">
		<div class="notification error">
			<img src="${pageContext.request.contextPath}/resources/merlin/images/icons/error.png" alt="">
			<p><spring:message code="login.retrival_exception" /></p>
		</div>
		</c:if>
		<form name="loginform" class="form-signin" method="post" 
		action="${pageContext.request.contextPath}/j_spring_security_check">
        <h3><spring:message code="login.please"/></h3>
        <input type="text"placeholder="Username" name="j_username">
        <input type="password" placeholder="Password" name="j_password">
        <button class="standard_button large" type="submit"><spring:message code="common.signin"/></button><br><br>
        	<span><spring:message code="login.forgot_password"/> <a href="#" onclick="$( '#dialog-form' ).dialog( 'open' );"><spring:message code="common.clic"/></a></span><br>
        	<span><spring:message code="login.not_registred"/> <a href="${pageContext.request.contextPath}/users/create"><spring:message code="common.clic"/></a></span>
      </form>
      </security:authorize>
      <security:authorize access="isAuthenticated()">
      <p><spring:message code="login.already_logged"/></p>
      <p><a href="${pageContext.request.contextPath}/j_spring_security_logout"><spring:message code="common.logout"/></a></p>
      </security:authorize>
	</div>
</div>
<script>
  $(function() {
    var  email = $( "#email" ),
      allFields = $( [] ).add( email ),
      tips = $( ".validateTips" );
 
    function updateTips( t ) {
      tips
        .text( t )
        .addClass( "ui-state-highlight" );
      setTimeout(function() {
        tips.removeClass( "ui-state-highlight", 1500 );
      }, 500 );
    }
 
    function checkLength( o, n, min, max ) {
      if ( o.val().length > max || o.val().length < min ) {
        o.addClass( "ui-state-error" );
        updateTips( "Length of " + n + " must be between " +
          min + " and " + max + "." );
        return false;
      } else {
        return true;
      }
    }
 
    function checkRegexp( o, regexp, n ) {
      if ( !( regexp.test( o.val() ) ) ) {
        o.addClass( "ui-state-error" );
        updateTips( n );
        return false;
      } else {
        return true;
      }
    }
 
    $( "#dialog-form" ).dialog({
      autoOpen: false,
      height: 300,
      width: 350,
      modal: true,
      buttons: {
        "<spring:message code='common.confirm'/>": function() {
          var bValid = true;
          allFields.removeClass( "ui-state-error" );
 
          bValid = bValid && checkLength( email, "email", 6, 80 );
 
          // From jquery.validate.js (by joern), contributed by Scott Gonzalez: http://projects.scottsplayground.com/email_address_validation/
          bValid = bValid && checkRegexp( email, /^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i, "eg. ui@jquery.com" );
 
          if ( bValid ) {
            //sendCredentials();
            $("#retrivalForm").submit();
            $( this ).dialog( "close" );
          }
        },
        "<spring:message code='common.cancel'/>": function() {
          $( this ).dialog( "close" );
        }
      },
      close: function() {
        allFields.val( "" ).removeClass( "ui-state-error" );
      }
    });
  });
  </script>

<div id="dialog-form" title='<spring:message code="login.find"/>'>
  <p class="validateTips"><spring:message code="login.find_info"/></p>
 
  <form id="retrivalForm" method="post" action="${pageContext.request.contextPath}/users/retrivalCredentials">
  <fieldset>
    <label for="email">Email</label>
    <input type="text" name="email" id="email" value="" class="text ui-widget-content ui-corner-all">
  </fieldset>
  </form>
</div>