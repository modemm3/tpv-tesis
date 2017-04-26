'use strict';
	angular.module('app.services')
			.factory('CategoryService', ['$resource','urlBase','$http',function($resource,urlBase,$http){
				return{
					CategoryPagination:function(requestDTO){
						return $http({method: 'POST',url:urlBase+'categoryService/pagination', 
								headers:{'Content-Type':'application/json'}, data:requestDTO});
					},
					CategoryLst:function(){
						return $http({method: 'POST',url:urlBase+'categoryService/getListCategory', 
								headers:{'Content-Type':'application/json'}, data:{}});
					},
					Save:function(requestDTO){
						return $http({method: 'POST', url:urlBase+'categoryService/saveCategory',
								headers:{'Content-Type':'application/json'},data:requestDTO});
					},
					Update:function(requestDTO){
						return $http({method:'POST', url:urlBase+'categoryService/updateCategory',
								headers:{'Content-Type':'application/json'}, data:requestDTO});
					},
					Delete:function(requestDTO){
					   return $http({method:'POST', url:urlBase+'categoryService/deleteCategory',
								headers:{'Content-Type':'application/json'},data:requestDTO});
					},
				};
			}]);
