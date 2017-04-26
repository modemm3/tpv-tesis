'use strict'
	angular.module('app.services')
			.factory('DiscountService', ['$resource','urlBase', '$http',function($resource,urlBase, $http){
				return{
					DiscountPagination:function(requestDTO){
						return $http({method: 'POST',url:urlBase+'discountService/pagination', 
								headers:{'Content-Type':'application/json'}, data:requestDTO});
					},
					DiscountLst:function(){
						return $http({method: 'POST',url:urlBase+'discountService/getListDiscount', 
								headers:{'Content-Type':'application/json'}, data:{}});
					},
					Save:function(requestDTO){
						return $http({method: 'POST', url:urlBase+'discountService/saveDiscount',
								headers:{'Content-Type':'application/json'},data:requestDTO});
					},
					Update:function(requestDTO){										  
						return $http({method:'POST', url:urlBase+'discountService/updateDiscount',
								headers:{'Content-Type':'application/json'}, data:requestDTO});
					},
					Delete:function(requestDTO){
					   return $http({method:'POST', url:urlBase+'discountService/deleteDiscount',
								headers:{'Content-Type':'application/json'},data:requestDTO});
					},
				};
			}])

