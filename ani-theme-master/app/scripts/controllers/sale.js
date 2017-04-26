'use strict';
angular
.module('app.core')
.controller('SaleCtrl', ['$scope','$mdDialog','ProductService', 
	function($scope,$mdDialog,ProductService){

	
	$scope.selected=[];
	$scope.amount=0.0;
	$scope.query={
		order:'name',
		limit:5,
		page:1
	};
	$scope.data=[{
		code:'00000000001',
		description:'Coca Cola',
		salePrice:'10',
		quantity:1,
		amount:10,
		existence:10
	}];

	$scope.requestTO={
 		initPage:10,
 		endPage:10,
 		dataTO:{
            id:null,
            name:null,
            code:'13455666'
 		}
 	};

	$scope.addProduct = function(){


		$scope.requestTO.dataTO={
			id:null,
            name:null,
            code:'1234567899'
		}
/*$scope.requestTO.dataTO=JSON.stringify($scope.requestTO.dataTO);
		ProductService.getProductByCode($scope.requestTO).success(
 		 		function(response){
                    $scope.paymentTypeLst=JSON.parse(response.dtoLst);
                    $scope.numberRecords=response.numberRecords;
 		 			$scope.clean(form);
 		 		}
 		 	);*/
		alert=$mdDialog.alert({
			title:'Find',
			textContent:'Find Product',
			ok:'Close'
		});
		$mdDialog.show(alert).finally(function() {
          alert = undefined;
        });
        $scope.data.push({
        			code:'00000000001',
		description:'Coca Cola',
		salePrice:'10',
		quantity:1,
		amount:10,
		existence:10
        });
        $scope.amount=$scope.amount+10;

	}
	$scope.delete = function(){
		$scope.msg="Delete";
		alert=$mdDialog.alert({
			title:'Attention',
			textContent:'This is an example',
			ok:'Close'
		});
		$mdDialog.show(alert).finally(function() {
          alert = undefined;
        });
        /*.title('Attention, ' + $scope.msg)
        .textContent('This is an example of how easy dialogs can be!')
        .ok('Close');*/
	}

}]);