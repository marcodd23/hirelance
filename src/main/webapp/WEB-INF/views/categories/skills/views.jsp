<script type="text/javascript" charset="utf-8">
$("#skills").addClass("current");

	$(document).ready(function() {
		$('#skills_table').dataTable({
			"bProcessing": true,
			"bJQueryUI": true,
			"bServerSide": true,
			"sAjaxDataProp": "rows",
			"aoColumns":[
		                {"mData":"skillID"},
		                {"mData":"name"},
		                {"mData":"description"},
		                {"mData":"mainCategory.name", "sDefaultContent": ""},
		                {"sName": "skillID",
		                    "bSearchable": false,
		                    "bSortable": false,
		                    "sDefaultContent": "",
		                    "fnRender": function (oObj) {
		                       return "<a href='${pageContext.request.contextPath}/categories/skill/update?id=" + oObj.aData['skillID'] + "'><i title='edit' class='icon-pencil'></i></a>" + " | "+ 
		                       		  "<a href='${pageContext.request.contextPath}/categories/skill/delete?id=" + oObj.aData['skillID'] + "'><i title='trash' class='icon-trash'></i></a>";
		                    	
		                     }
		                  }
            ],
            "sAjaxSource": "${pageContext.request.contextPath}/categories/skill/findAllSkillsPaginated?main_id=${requestScope.main_id}",
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
					<li>View skills</li>
				</ul>

			</div>
			<!-- end seven -->

		</div>
		<!-- end container -->

	</div>
	<!-- end .top_title_holder -->
	
	<div class="container">
		<div class="sixteen columns add-top add-bottom">
		<p><a  class="standard_button large" href="${pageContext.request.contextPath}/categories/skill/create">Add</a></p>
			<table id="skills_table">
				<thead>
					<tr>
					<th>ID</th>
					<th>Name</th>
					<th>Description</th>
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