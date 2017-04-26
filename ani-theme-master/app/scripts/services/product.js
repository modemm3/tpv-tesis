'use strict';
	angular.module('app.services')
			.factory('ProductService', ['$resource','urlBase','$http',function($resource,urlBase,$http){
				return{
					getProductByCode:function(requestTO){
						return $http({method: 'POST',url:urlBase+'productService/getProduct', 
								headers:{'Content-Type':'application/json'}, data:requestTO});
					},
					ProductPagination: function(requestDTO){
						return $http({method: 'POST', url:urlBase+'productService/pagination',
						headers:{'Content-Type':'application/json'}, data:requestDTO});
					},
					ProductLst:function(){
						return $http({method:'POST', url:urlBase+'productService/getListProduct',
								headers:{'Content-Type':'application/json'}, data:{}});
					},
					Save:function(requestDTO){
						return $http({method:'POST', url:urlBase+'productService/saveProduct',
								headers:{'Content-Type':'application/json'}, data:requestDTO});
					},
					Update:function(requestDTO){
						return $http({method:'POST', url:urlBase+'productService/updateProduct',
								headers:{'Content-Type':'application/json'}, data:requestDTO});
					},
					Delete:function(requestDTO){
						return $http({method:'POST', url:urlBase+'productService/deleteProduct',
								headers:{'Content-Type':'application/json'}, data:requestDTO});
					},

				};
			}]);
