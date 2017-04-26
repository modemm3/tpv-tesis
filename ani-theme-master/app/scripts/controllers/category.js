'use strict';
angular
	.module('app.core')
	.controller('CategoryCtrl',['$scope','$log','uiGridConstants','CategoryService','$q','$http','$interval','$mdDialog','$filter','TipoAccionEnum',
		function($scope,$log, uiGridConstants,CategoryService,$q,$http,$interval,$mdDialog,$filter,TipoAccionEnum){

		$scope.tipoAccionEnum=TipoAccionEnum;
		
        $scope.label = "{page: 'Página:', rowsPerPage: 'Registros por página:', of: 'de'}";
        $scope.optionsPage=[15,25,35];
        $scope.messageResponseDTO=null;

 		 $scope.requestTO={
 		 	initPage:0,
 		 	endPage:$scope.optionsPage[0],
 		 	dataTO:{
                id:null,
                name:null,
                description:null
 		 	}
 		 };

         $scope.category={
            id:null,
            name:null,
            description:null
         };

         $scope.inicializaRequest=function(){
            $scope.requestTO.dataTO={};
            $scope.requestTO.dataTO.id=$scope.category.id;
            $scope.requestTO.dataTO.name=$scope.category.name;
            $scope.requestTO.dataTO.description=$scope.category.description;
         };

 		 $scope.categoryLst=null;

 		 $scope.delete=function(form){
            $scope.inicializaRequest();
            $scope.requestTO.dataTO=JSON.stringify($scope.requestTO.dataTO);

 		 	CategoryService.Delete($scope.requestTO)
            .success(
 		 		function(response){
                    $scope.categoryLst=JSON.parse(response.dtoLst);
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

 		 	 CategoryService.Update($scope.requestTO).success(
 		 		function(response){
                    $scope.categoryLst=JSON.parse(response.dtoLst);
                    $scope.numberRecords=response.numberRecords;
 		 			$scope.clean(form);
 		 	});
 		 };

 		 $scope.save=function(form, event){
 		 	if($scope.category.id==null){
                $scope.inicializaRequest();
                $scope.requestTO.dataTO=JSON.stringify($scope.requestTO.dataTO);
	 		 	CategoryService.Save($scope.requestTO).success(
	 		 		function(response){
                        if(response.messageResponseDTO.code==1){
                            $scope.requestTO.initPage=response.pageInit;
                            $scope.requestTO.endPage=response.pageEnd;
                            $scope.query.page=response.numberPage;
                            $scope.categoryLst=JSON.parse(response.dtoLst);
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
                $scope.category={};
                $scope.formCategory.$setPristine();
                $scope.formCategory.$setUntouched();
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
			$scope.category.id=entity.id;
 		 	$scope.category.name=entity.name;
 		 	$scope.category.description=entity.description;
		}


 		$scope.getList=function(){
 		 	CategoryService.CategoryPagination($scope.requestTO).success(
 		 				function(responseDTO){
 		 					$scope.categoryLst=JSON.parse(responseDTO.dtoLst);
 		 					$scope.numberRecords=responseDTO.numberRecords;

 		 	});
 		};
   
 		$scope.getList();
        $scope.clean($scope.formCategory);
}]);