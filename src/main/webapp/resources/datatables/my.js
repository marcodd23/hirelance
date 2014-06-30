function objectFindByKey(array, key, value) {
    for (var i = 0; i < array.length; i++) {
        if (array[i][key] === value) {
            return array[i];
        }
    }
    return null;
}
function getSortCol(array) {
	var iSortColValue = objectFindByKey(array, 'name', 'iSortCol_0').value;
	var mPropsValue = objectFindByKey(array, 'name', 'mDataProp_' + iSortColValue).value;
	return mPropsValue;
	
}

function getSortDir(array) {
	var iSortDirValue = objectFindByKey(array, 'name', 'sSortDir_0').value;
	return iSortDirValue;
	
}


function addsortparams( aoData ) {
	aoData.push( { "name": "sortCol", "value": getSortCol(aoData) } );
	aoData.push( { "name": "sortDir", "value": getSortDir(aoData) } );
}
