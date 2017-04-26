'use strict';
	angular.module('app.services')
			.factory('SaleService', ['$resource','urlBase','$http',function($resource,urlBase,$http){
				return{
					getProductByCode:function(requestTO){
						return $http({method: 'POST',url:urlBase+'productService/getProduct', 
								headers:{'Content-Type':'application/json'}, data:requestTO});
					}


				};
			}]);
