'use strict';
angular
	.module('app.core')
	.controller('PaymentTypeCtrl',['$scope','$log','uiGridConstants','PaymentTypeService','$q','$http','$interval','$mdDialog','$filter','TipoAccionEnum',
		function($scope,$log, uiGridConstants,PaymentTypeService,$q,$http,$interval,$mdDialog,$filter,TipoAccionEnum){

		$scope.tipoAccionEnum=TipoAccionEnum;
		
        $scope.label = "{page: 'Página:', rowsPerPage: 'Registros por página:', of: 'de'}";
        $scope.optionsPage=[15,25,35];

 		 $scope.requestTO={
 		 	initPage:0,
 		 	endPage:$scope.optionsPage[0],
 		 	dataTO:{
                id:null,
                name:null
 		 	}
 		 };

 		 $scope.paymentTypeLst=null;

 		 $scope.delete=function(form){
            $scope.requestTO.dataTO=JSON.stringify($scope.requestTO.dataTO);

 		 	PaymentTypeService.deletePT($scope.requestTO).success(
 		 		function(response){
                    $scope.paymentTypeLst=JSON.parse(response.dtoLst);
                    $scope.numberRecords=response.numberRecords;
 		 			$scope.clean(form);
 		 		}
 		 	);
 		 }

 		 $scope.update=function(form){

            $scope.requestTO.dataTO=JSON.stringify($scope.requestTO.dataTO);

 		 	 PaymentTypeService.UpdatePT($scope.requestTO).success(
 		 		function(response){
                    $scope.paymentTypeLst=JSON.parse(response.dtoLst);
                    $scope.numberRecords=response.numberRecords;
 		 			$scope.clean(form);
 		 	});
 		 };

 		 $scope.save=function(form, $event){
 		 	if($scope.requestTO.dataTO.id==null){
                $scope.requestTO.dataTO=JSON.stringify($scope.requestTO.dataTO);
	 		 	PaymentTypeService.SavePT($scope.requestTO).success(
	 		 		function(response){
                        if(response.messageResponseDTO.code==1){
                            $scope.requestTO.initPage=response.pageInit;
                            $scope.requestTO.endPage=response.pageEnd;
                          //  $scope.query.limit=response.pageEnd;
                            $scope.query.page=response.numberPage;
                            $scope.paymentTypeLst=JSON.parse(response.dtoLst);
                            $scope.numberRecords=response.numberRecords;

    	 		 			$scope.clean(form);
                        }
	 		 		});
 		 	}else{
 		 		$scope.showConfirm(form, TipoAccionEnum.UPDATE);
 		 	}
 		 };

 		 $scope.clean=function(form){

 		 	if(form){
                $scope.requestTO.dataTO={};
                $scope.formPaymentType.$setPristine();
                $scope.formPaymentType.$setUntouched();
 		 	}

 		 };

 		 $scope.showConfirm=function(form, action){
 		 	var title=null;
 		 	var titleContent=null;

 		 	if(action==TipoAccionEnum.UPDATE){
 		 		title=$filter('translate')('mensaje.actualizar');
 		 		$scope.showDialog(form,action, title);
 		 	}else if(action==TipoAccionEnum.DELETE){
 		 		title=$filter('translate')('mensaje.eliminar');
 		 		$scope.showDialog(form, action, title);
 		 	}
 		 };

 		 $scope.showDialog=function(form, action, title){
 		 	 	var confirm=$mdDialog.confirm()
 		 		.title(title)
 		 		.ariaLabel('Lucky day')
 		 		.targetEvent(event)
 		 		.ok($filter('translate')('ok'))
 		 		.cancel($filter('translate')('cancel'));
 		 		
 		 		$mdDialog.show(confirm).then(function(){
 		 			if(action==TipoAccionEnum.UPDATE){
 		 				$scope.update(form);
 		 			}else if(action==TipoAccionEnum.DELETE){
 		 				$scope.delete(form);
 		 			}

 		 		},function(){
					$mdDialog.cancel();
 		 		});
 		 };



            $scope.query = {
                limit: $scope.optionsPage[0],
                page: 1
            };

            $scope.onPaginate = function (page,limit) {
            	var obtenerInit;
            	var initPage;
                $scope.query.limit=limit;
            	obtenerInit=page*limit;
 				 if(obtenerInit!=limit){
 				 	initPage=obtenerInit-limit;
 				 }else{
 				 	initPage=0;
 				 }
 				 $scope.requestTO.initPage=initPage;
                 $scope.requestTO.endPage=limit;
 				 $scope.getList();
			}

		$scope.rowSelection = function rowSelection( entity ) { 
			$scope.requestTO.dataTO.id=entity.id;
 		 	$scope.requestTO.dataTO.name=entity.name;
		}


 		$scope.getList=function(){
 		 	PaymentTypeService.PaymentTypePagination($scope.requestTO).success(
 		 				function(responseDTO){
 		 					$scope.paymentTypeLst=JSON.parse(responseDTO.dtoLst);
 		 					$scope.numberRecords=responseDTO.numberRecords;

 		 	});
 		};
   
 		$scope.getList();
        $scope.clean($scope.formPaymentType);
}]);