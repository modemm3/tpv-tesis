'use strict';
angular
	.module('app.core')
	.controller('EmployeeCategoryCtrl',['$scope','$log','uiGridConstants','EmployeeCategoryService','$q','$http','$interval','$mdDialog','$filter','TipoAccionEnum',
		function($scope,$log, uiGridConstants,EmployeeCategoryService,$q,$http,$interval,$mdDialog,$filter,TipoAccionEnum){

		$scope.tipoAccionEnum=TipoAccionEnum;
		
        $scope.label = "{page: 'Página:', rowsPerPage: 'Registros por página:', of: 'de'}";
        $scope.optionsPage=[15,25,35];
        $scope.messageResponseDTO=null;

 		 $scope.requestTO={
 		 	initPage:0,
 		 	endPage:$scope.optionsPage[0],
 		 	dataTO:{
                id:null,
                name:null
 		 	}
 		 };

         $scope.employeeCategory={
            id:null,
            name:null
         };

         $scope.inicializaRequest=function(){
            $scope.requestTO.dataTO={};
            $scope.requestTO.dataTO.id=$scope.employeeCategory.id;
            $scope.requestTO.dataTO.name=$scope.employeeCategory.name;
         };

 		 $scope.employeeCategoryLst=null;

 		 $scope.delete=function(form){
            $scope.inicializaRequest();
            $scope.requestTO.dataTO=JSON.stringify($scope.requestTO.dataTO);

 		 	EmployeeCategoryService.Delete($scope.requestTO)
            .success(
 		 		function(response){
                    $scope.employeeCategoryLst=JSON.parse(response.dtoLst);
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

 		 	 EmployeeCategoryService.Update($scope.requestTO).success(
 		 		function(response){
                    $scope.employeeCategoryLst=JSON.parse(response.dtoLst);
                    $scope.numberRecords=response.numberRecords;
 		 			$scope.clean(form);
 		 	});
 		 };

 		 $scope.save=function(form, event){
 		 	if($scope.employeeCategory.id==null){
                $scope.inicializaRequest();
                $scope.requestTO.dataTO=JSON.stringify($scope.requestTO.dataTO);
	 		 	EmployeeCategoryService.Save($scope.requestTO).success(
	 		 		function(response){
                        if(response.messageResponseDTO.code==1){
                            $scope.requestTO.initPage=response.pageInit;
                            $scope.requestTO.endPage=response.pageEnd;
                            $scope.query.page=response.numberPage;
                            $scope.employeeCategoryLst=JSON.parse(response.dtoLst);
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
                $scope.employeeCategory={};
                $scope.formEmployeeCategory.$setPristine();
                $scope.formEmployeeCategory.$setUntouched();
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
			$scope.employeeCategory.id=entity.id;
 		 	$scope.employeeCategory.name=entity.name;
		}


 		$scope.getList=function(){
 		 	EmployeeCategoryService.EmployeeCategoryPagination($scope.requestTO).success(
 		 				function(responseDTO){
 		 					$scope.employeeCategoryLst=JSON.parse(responseDTO.dtoLst);
 		 					$scope.numberRecords=responseDTO.numberRecords;

 		 	});
 		};
   
 		$scope.getList();
        $scope.clean($scope.formEmployeeCategory);
}]);