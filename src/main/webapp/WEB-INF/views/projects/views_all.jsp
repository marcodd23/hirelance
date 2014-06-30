<script type="text/javascript" charset="utf-8">
$("#projects").addClass("current");

	$(document).ready(function() {
		$('#projects_table').dataTable({
			"bProcessing": true,
			"bJQueryUI": true,
			"bServerSide": true,
			"sAjaxDataProp": "rows",
			"aoColumns":[
		                {"mData":"projectID"},
		                {"mData":"title"},
		                {"mData":"status"},		                
		                {"mData":"clientOwner.clientName"},
		                { "sName": "projectID",
		                    "bSearchable": false,
		                    "bSortable": false,
		                    "sDefaultContent": "",
		                    "fnRender": function (oObj) {
		                       return "<a href='${pageContext.request.contextPath}/projects/update?projectID=" + oObj.aData['projectID'] + "'><i title='edit' class='icon-pencil'></i></a>" + " | "+ 
		                       		  "<a href='${pageContext.request.contextPath}/projects/delete?projectID=" + oObj.aData['projectID'] + "'><i title='trash' class='icon-trash'></i></a>";
		                    	
		                     }
		                  }
            ],
            "sAjaxSource": "${pageContext.request.contextPath}/projects/findAllProjectsPaginated",
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

				<h3>Manage projets</h3>
				<p>View, update or remove a project</p>

			</div>
			<!-- end nine -->

			<div class="seven columns">

				<ul>
					<li><a href="${pageContext.request.contextPath}">Home</a></li>
					<li>&#187;</li>
					<li>View projects</li>
				</ul>

			</div>
			<!-- end seven -->

		</div>
		<!-- end container -->

	</div>
	<!-- end .top_title_holder -->


	<div class="container">
		<div class="sixteen columns add-top add-bottom">
		<p><a class="standard_button large" href="${pageContext.request.contextPath}/projetcs/create">Add</a></p>
			<table id="projects_table">
				<thead>
					<tr>
					   <th>ID</th>
					   <th>Title</th>
					   <th>Status</th>
					   <th>Client</th>
					   <th>Actions</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
	<!-- end container -->

</section>
<!-- end #main_content -->