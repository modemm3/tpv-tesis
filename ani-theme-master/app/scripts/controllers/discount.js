'use strict';
angular
	.module('app.core')
	.controller('DiscountCtrl',['$scope','$log','uiGridConstants','DiscountService','$q','$http','$interval','$mdDialog','$filter','TipoAccionEnum','TypeDiscountService','ProductService','EstatusDescuentoEnum',
		function($scope,$log, uiGridConstants,DiscountService,$q,$http,$interval,$mdDialog,$filter,TipoAccionEnum,TypeDiscountService,ProductService,EstatusDescuentoEnum){

		$scope.tipoAccionEnum=TipoAccionEnum;
		
        $scope.label = "{page: 'Página:', rowsPerPage: 'Registros por página:', of: 'de'}";
        $scope.optionsPage=[15,25,35];
        $scope.messageResponseDTO=null;
        $scope.typeDiscountLst=null;
        $scope.typeStatusDiscount=EstatusDescuentoEnum;

 		 $scope.requestTO={
 		 	initPage:0,
 		 	endPage:$scope.optionsPage[0],
 		 	dataTO:{
                id:null,
                name:null,
                typeDiscountId:null,
                amount:null,
                startDate:null,
                endDate:null,
                productId:null,
                status:null
 		 	}
 		 };

         $scope.discount={
                id:null,
                name:null,
                typeDiscountId:null,
                amount:null,
                startDate:null,
                endDate:null,
                productId:null,
                status:null
         };

         $scope.inicializaRequest=function(){
            $scope.requestTO.dataTO={};
            $scope.requestTO.dataTO.id=$scope.discount.id;
            $scope.requestTO.dataTO.name=$scope.discount.name;
            $scope.requestTO.dataTO.typeDiscountId=$scope.discount.typeDiscountId;
            $scope.requestTO.dataTO.amount=$scope.discount.amount;
            $scope.requestTO.dataTO.startDate=$scope.discount.startDate;
            $scope.requestTO.dataTO.endDate=$scope.discount.endDate;
            $scope.requestTO.dataTO.productId=$scope.discount.productId;
            $scope.requestTO.dataTO.status=$scope.discount.status;
         };

 		 $scope.discountLst=null;

 		 $scope.delete=function(form){
            $scope.inicializaRequest();
            $scope.requestTO.dataTO=JSON.stringify($scope.requestTO.dataTO);

 		 	DiscountService.Delete($scope.requestTO)
            .success(
 		 		function(response){
                    $scope.discountLst=JSON.parse(response.dtoLst);
                    $scope.numberRecords=response.numberRecords;
 		 			$scope.clean(form);
 		 		}
 		 	)
            .error(
                function(response){
                    $scope.messageResponseDTO=response.messageResponseDTO;
                    $scope.showDialog(null, null, $scope.messageResponseDTO.message);
                }
            );
 		 }

 		 $scope.update=function(form){
            $scope.inicializaRequest();
            $scope.requestTO.dataTO=JSON.stringify($scope.requestTO.dataTO);

 		 	 DiscountService.Update($scope.requestTO).success(
 		 		function(response){
                    $scope.discountLst=JSON.parse(response.dtoLst);
                    $scope.numberRecords=response.numberRecords;
 		 			$scope.clean(form);
 		 	});
 		 };

 		 $scope.save=function(form, event){
 		 	if($scope.discount.id==null){
                $scope.inicializaRequest();
                $scope.requestTO.dataTO=JSON.stringify($scope.requestTO.dataTO);
	 		 	DiscountService.Save($scope.requestTO).success(
	 		 		function(response){
                        if(response.messageResponseDTO.code==1){
                            $scope.requestTO.initPage=response.pageInit;
                            $scope.requestTO.endPage=response.pageEnd;
                            $scope.query.page=response.numberPage;
                            $scope.discountLst=JSON.parse(response.dtoLst);
                            $scope.numberRecords=response.numberRecords;

    	 		 			$scope.clean(form);
                        }
	 		 		});
 		 	}else{
 		 		$scope.showConfirm(form, TipoAccionEnum.UPDATE, event);
 		 	}
 		 };

 		 $scope.clean=function(form){

 		 	if(form){
                $scope.discount={};
                $scope.formDiscount.$setPristine();
                $scope.formDiscount.$setUntouched();
 		 	}

 		 };

 		 $scope.showConfirm=function(form, action, event){
 		 	var title=null;
 		 	var titleContent=null;

 		 	if(action==TipoAccionEnum.UPDATE){
 		 		title=$filter('translate')('mensaje.actualizar');
 		 		$scope.showDialog(form,action, title, event);
 		 	}else if(action==TipoAccionEnum.DELETE){
 		 		title=$filter('translate')('mensaje.eliminar');
 		 		$scope.showDialog(form, action, title, event);
 		 	}
 		 };

 		 $scope.showDialog=function(form, action, title, event){
 		 	 	var confirm=$mdDialog.confirm()
 		 		.title(title)
 		 		.ariaLabel('Lucky day')
 		 		.targetEvent(event)
 		 		.ok($filter('translate')('ok'))
 		 		.cancel($filter('translate')('cancel'));
 		 		
 		 		$mdDialog.show(confirm).
                then(function(){
                    $mdDialog.hide();
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

		$scope.rowSelection = function rowSelection(entity) { 
			$scope.discount.id=entity.id;
 		 	$scope.discount.name=entity.name;
            $scope.discount.typeDiscountId=entity.typeDiscountId;
            $scope.discount.startDate=entity.startDate;
            $scope.discount.endDate=entity.endDate;
            $scope.discount.productId=entity.productId;
            $scope.discount.status=entity.status
		}

 		$scope.getList=function(){
 		 	DiscountService.DiscountPagination($scope.requestTO).success(
 		 				function(responseDTO){
 		 					$scope.discountLst=JSON.parse(responseDTO.dtoLst);
 		 					$scope.numberRecords=responseDTO.numberRecords;

 		 	});
 		};

        $scope.getTypeDiscountLst=function(){
            TypeDiscountService.TypeDiscountLst().success(
                function(response){
                    $scope.typeDiscountLst=response;
                });
        };

        $scope.getProductLst=function(){
            ProductService.ProductLst().success(
                function(response){
                    $scope.ProductLst=response;
                });
        };
   
 		$scope.getList();
        $scope.getProductLst();
        $scope.getTypeDiscountLst();
        $scope.clean($scope.formDiscount);
}]);