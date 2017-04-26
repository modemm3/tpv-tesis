'use strict';
	angular.module('app.services')
			.factory('EmployeeCategoryService', ['$resource','urlBase','$http',function($resource,urlBase,$http){
				return{
					EmployeeCategoryPagination:function(requestDTO){
						return $http({method: 'POST',url:urlBase+'employeeCategoryService/pagination', 
								headers:{'Content-Type':'application/json'}, data:requestDTO});
					},
					EmployeeCategoryLst:function(){
						return $http({method: 'POST',url:urlBase+'employeeCategoryService/getListEmployeeCategory', 
								headers:{'Content-Type':'application/json'}, data:{}});
					},
					Save:function(requestDTO){
						return $http({method: 'POST', url:urlBase+'employeeCategoryService/saveEmployeeCategory',
								headers:{'Content-Type':'application/json'},data:requestDTO});
					},
					Update:function(requestDTO){										  
						return $http({method:'POST', url:urlBase+'employeeCategoryService/updateEmployeeCategory',
								headers:{'Content-Type':'application/json'}, data:requestDTO});
					},
					Delete:function(requestDTO){
					   return $http({method:'POST', url:urlBase+'employeeCategoryService/deleteEmployeeCategory',
								headers:{'Content-Type':'application/json'},data:requestDTO});
					},
				};
			}]);
