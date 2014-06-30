<script type="text/javascript" charset="utf-8">
$("#users").addClass("current");

	$(document).ready(function() {
		$('#users_table').dataTable({
			"bProcessing": true,
			"bJQueryUI": true,
			"bServerSide": true,
			"sAjaxDataProp": "rows",
			"aoColumns":[
		                {"mData":"userID"},
		                {"mData":"username"},
		                {"mData":"name"},		                
		                {"mData":"surname"},
		                {"mData":"email"},
		                { "sName": "userID",
		                    "bSearchable": false,
		                    "bSortable": false,
		                    "sDefaultContent": "",
		                    "fnRender": function (oObj) {
		                       return "<a href='${pageContext.request.contextPath}/users/update?user_id=" + oObj.aData['userID'] + "'><i title='edit' class='icon-pencil'></i></a>" + " | "+ 
		                       		  "<a href='${pageContext.request.contextPath}/users/delete?user_id=" + oObj.aData['userID'] + "'><i title='trash' class='icon-trash'></i></a>";
		                    	
		                     }
		                  }
            ],
            "sAjaxSource": "${pageContext.request.contextPath}/users/findAllUsersPaginated",
            "oLanguage": {"sUrl": "${pageContext.request.contextPath}/resources/datatables/i18n/italian.properties"},
            "fnServerParams": addsortparams
		});
	});
</script>
<section id="main_content">
	<div class="top_title_holder add-bottom">

		<div class="top_title_border"></div>
		<div class="bottom_title_border"></div>

		<div class="container">

			<div class="nine columns">

				<h3>Join in Hirelance</h3>
				<p>Fill the form below and register your account (all fields are required).</p>

			</div>
			<!-- end nine -->

			<div class="seven columns">

				<ul>
					<li><a href="${pageContext.request.contextPath}">Home</a></li>
					<li>&#187;</li>
					<li>View users</li>
				</ul>

			</div>
			<!-- end seven -->

		</div>
		<!-- end container -->

	</div>
	<!-- end .top_title_holder -->


	<div class="container">
		<div class="sixteen columns add-top add-bottom">
		<p><a class="standard_button large" href="${pageContext.request.contextPath}/users/create">Add</a></p>
			<table id="users_table">
				<thead>
					<tr>
					   <th>ID</th>
					   <th>Username</th>
					   <th>Name</th>
					   <th>Surname</th>
					   <th>Email</th>
					   <th>Actions</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
	<!-- end container -->

</section>
<!-- end #main_content -->