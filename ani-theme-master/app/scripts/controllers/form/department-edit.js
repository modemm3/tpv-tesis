'use strict';
angular
	/*.module('app.core')                           
	.controller('DepartmentCtrl',['$scope','$log','$q','$http',
	'$interval','$filter','TipoAccionEnum','DepartmentService',
		function($scope,$log,$q,$http,
		$interval,$filter,TipoAccionEnum,DepartmentService){
*/
.module('app.core') 
	.controller('DepartmentEditCtrl', ['$modalInstance', 'PersonSchema', 'grid', 'row',
     function ($modalInstance, PersonSchema, grid, row)
     {
        $scope.vm=this;
        $scope.vm.entity = angular.copy(row.entity);


        $scope.save=function(){
       
            console.log('Entra a guardar al editar' + row.entity);
 		};

}])
