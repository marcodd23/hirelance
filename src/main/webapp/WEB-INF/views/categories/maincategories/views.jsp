<script type="text/javascript" charset="utf-8">
$("#main").addClass("current");

	$(document).ready(function() {
		$('#mainCategories_table').dataTable({
			"bProcessing": true,
			"bJQueryUI": true,
			"bServerSide": true,
			"sAjaxDataProp": "rows",
			"aoColumns":[
		                {"mData":"catID"},
		                {"mData":"name"},		                
		                {"sName": "catID",
		                    "bSearchable": false,
		                    "bSortable": false,
		                    "sDefaultContent": "",
		                    "fnRender": function (oObj) {
		                       return "<a href='${pageContext.request.contextPath}/categories/main/update?id=" + oObj.aData['catID'] + "'><i title='edit' class='icon-pencil'></i></a>" + " | "+ 
		                       		  "<a href='${pageContext.request.contextPath}/categories/main/delete?id=" + oObj.aData['catID'] + "'><i title='trash' class='icon-trash'></i></a>" + " | "+
		                       		  "<a href='${pageContext.request.contextPath}/categories/skill/views?main_id=" + oObj.aData['catID'] + "'><i title='view skills' class='icon-eye'></i></a>" + " | "+
		                       		  "<a href='${pageContext.request.contextPath}/categories/sub/views?main_id=" + oObj.aData['catID'] + "'><i title='view subcategories' class='icon-flow-tree'></i></a>";
		                    	
		                     }
		                  }
            ],
            "sAjaxSource": "${pageContext.request.contextPath}/categories/main/findAllMainCategoriesPaginated",
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

				<h3>Manage main categories</h3>
				<p>View all main categories, update or delete a main category</p>

			</div>
			<!-- end nine -->

			<div class="seven columns">

				<ul>
					<li><a href="${pageContext.request.contextPath}">Home</a></li>
					<li>&#187;</li>
					<li>View main categories</li>
				</ul>

			</div>
			<!-- end seven -->

		</div>
		<!-- end container -->

	</div>
	<!-- end .top_title_holder -->
	
	<div class="container">
		<div class="sixteen columns add-top add-bottom">
			<p><a  class="standard_button large" href="${pageContext.request.contextPath}/categories/main/create">Add</a></p>
			<table id="mainCategories_table">
				<thead>
					<tr>
					<th>ID</th>
					<th>Name</th>
					<th>Actions</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
	<!-- end container -->

</section>
<!-- end #main_content -->