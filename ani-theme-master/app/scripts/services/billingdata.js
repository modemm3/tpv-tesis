'use strict';
	angular.module('app.services')
			.factory('BillingDataService', ['$resource','urlBase','$http',function($resource,urlBase,$http){
				return{
					BillingDataPagination:function(requestDTO){
						return $http({method: 'POST',url:urlBase+'billingDataService/pagination', 
								headers:{'Content-Type':'application/json'}, data:requestDTO});
					},
					BillingDataLst:function(){
						return $http({method: 'POST',url:urlBase+'billingDataService/getListBillingData', 
								headers:{'Content-Type':'application/json'}, data:{}});
					},
					Save:function(requestDTO){
						return $http({method: 'POST', url:urlBase+'billingDataService/saveBillingData',
								headers:{'Content-Type':'application/json'},data:requestDTO});
					},
					Update:function(requestDTO){										  
						return $http({method:'POST', url:urlBase+'billingDataService/updateBillingData',
								headers:{'Content-Type':'application/json'}, data:requestDTO});
					},
					Delete:function(requestDTO){
					   return $http({method:'POST', url:urlBase+'billingDataService/deleteBillingData',
								headers:{'Content-Type':'application/json'},data:requestDTO});
					},
				};
			}]);
