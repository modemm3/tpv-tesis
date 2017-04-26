var tpv=angular.module('tpvApp',['ngRoute']);

tpv.config(['$routeProvider', function($routeProvider){
	$routeProvider.when('/productCategory',{
		templateUrl:'templates/productCategory.html',
		controller:'ctrlCategoryProduct'
	})
	.when('/paymentType',{
		templateUrl:'templates/paymentType.html'
	})
}]);