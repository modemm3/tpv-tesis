'use strict';
	angular.module('app.services')
			.factory('TypeDiscountService', ['$resource','urlBase','$http',function($resource,urlBase,$http){
				return{
					TypeDiscountPagination:function(requestDTO){
						return $http({method: 'POST',url:urlBase+'typeDiscountService/pagination', 
								headers:{'Content-Type':'application/json'}, data:requestDTO});
					},
					TypeDiscountLst:function(){
						return $http({method: 'POST',url:urlBase+'typeDiscountService/getListTypeDiscount', 
								headers:{'Content-Type':'application/json'}, data:{}});
					},
					Save:function(requestDTO){
						return $http({method: 'POST', url:urlBase+'typeDiscountService/saveTypeDiscount',
								headers:{'Content-Type':'application/json'},data:requestDTO});
					},
					Update:function(requestDTO){
						return $http({method:'POST', url:urlBase+'typeDiscountService/updateTypeDiscount',
								headers:{'Content-Type':'application/json'}, data:requestDTO});
					},
					Delete:function(requestDTO){
					   return $http({method:'POST', url:urlBase+'typeDiscountService/deleteTypeDiscount',
								headers:{'Content-Type':'application/json'},data:requestDTO});
					},
				};
			}]);
