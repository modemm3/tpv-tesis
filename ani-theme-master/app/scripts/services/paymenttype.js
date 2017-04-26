'use strict';
	angular.module('app.services')
			.factory('PaymentTypeService', ['$resource','urlBase','$http',function($resource,urlBase,$http){
				return{
					PaymentTypePagination:function(requestTO){
						return $http({method: 'POST',url:urlBase+'paymentTypeService/pagination', 
								headers:{'Content-Type':'application/json'}, data:requestTO});
					},
					PaymentTypeLst:function(){
						return $http({method: 'POST',url:urlBase+'paymentTypeService/getListPaymentType', 
								headers:{'Content-Type':'application/json'}, data:{}});
					},
					SavePT:function(paymentTypeDTO){
						return $http({method: 'POST', url:urlBase+'paymentTypeService/savePaymentType',
								headers:{'Content-Type':'application/json'},data:paymentTypeDTO});
					},
					UpdatePT:function(paymentTypeDTO){
						return $http({method:'POST', url:urlBase+'paymentTypeService/updatePaymentType',
								headers:{'Content-Type':'application/json'}, data:paymentTypeDTO});
					},
					deletePT:function(paymentTypeDTO){
					   return $http({method:'POST', url:urlBase+'paymentTypeService/deletePaymentType',
								headers:{'Content-Type':'application/json'},data:paymentTypeDTO});
					},


				};
			}]);
