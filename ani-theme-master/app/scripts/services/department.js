'use strict';
	angular.module('app.services')
			.service('DepartmentService', ['$resource','urlBase','$http',function($resource,urlBase,$http){
				return{
					DepartmentPagination:function(requestDTO){
						return $http({method: 'POST',url:urlBase+'departmentService/pagination', 
								headers:{'Content-Type':'application/json'}, data:requestDTO});
					},
					DepartmentLst:function(){
						return $http({method: 'POST',url:urlBase+'departmentService/getListDepartment', 
								headers:{'Content-Type':'application/json'}, data:{}});
					},
					Save:function(requestDTO){
						return $http({method: 'POST', url:urlBase+'departmentService/saveDepartment',
								headers:{'Content-Type':'application/json'},data:requestDTO});
					},
					Update:function(requestDTO){
						return $http({method:'POST', url:urlBase+'departmentService/updateDepartment',
								headers:{'Content-Type':'application/json'}, data:requestDTO});
					},
					Delete:function(requestDTO){
					   return $http({method:'POST', url:urlBase+'departmentService/deleteDepartment',
								headers:{'Content-Type':'application/json'},data:requestDTO});
					},
				};
			}]);
