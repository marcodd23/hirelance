<script type="text/javascript" charset="utf-8">
$("#sub").addClass("current");

	$(document).ready(function() {
		$('#subCategories_table').dataTable({
			"bProcessing": true,
			"bJQueryUI": true,
			"bServerSide": true,
			"sAjaxDataProp": "rows",
			"aoColumns":[
		                {"mData":"catID"},
		                {"mData":"name"},
		                {"mData":"parentCategory.name", "sDefaultContent": ""},
		                {"sName": "catID",
		                    "bSearchable": false,
		                    "bSortable": false,
		                    "sDefaultContent": "",
		                    "fnRender": function (oObj) {
		                       return "<a href='${pageContext.request.contextPath}/categories/sub/update?id=" + oObj.aData['catID'] + "'><i title='edit' class='icon-pencil'></i></a>" + " | "+ 
		                       		  "<a href='${pageContext.request.contextPath}/categories/sub/delete?id=" + oObj.aData['catID'] + "'><i title='trash' class='icon-trash'></i></a>";
		                    	
		                     }
		                  }
            ],
            "sAjaxSource": "${pageContext.request.contextPath}/categories/sub/findAllSubCategoriesPaginated?main_id=${requestScope.main_id}",
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

				<h3>Manage sub-categories</h3>
				<p>View all sub-categories, update or delete a sub category</p>

			</div>
			<!-- end nine -->

			<div class="seven columns">

				<ul>
					<li><a href="${pageContext.request.contextPath}">Home</a></li>
					<li>&#187;</li>
					<li>View sub-categories</li>
				</ul>

			</div>
			<!-- end seven -->

		</div>
		<!-- end container -->

	</div>
	<!-- end .top_title_holder -->
	
	<div class="container">
		<div class="sixteen columns add-top add-bottom">
		<p><a class="standard_button large" href="${pageContext.request.contextPath}/categories/sub/create">Add</a></p>
			<table id="subCategories_table">
				<thead>
					<tr>
					<th>ID</th>
					<th>Name</th>
					<th>Parent Category</th>
					<th>Actions</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
	<!-- end container -->

</section>
<!-- end #main_content -->