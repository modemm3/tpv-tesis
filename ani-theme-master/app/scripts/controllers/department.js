'use strict';
angular
	/*.module('app.core')                           
	.controller('DepartmentCtrl',['$scope','$log','$q','$http',
	'$interval','$filter','TipoAccionEnum','DepartmentService',
		function($scope,$log,$q,$http,
		$interval,$filter,TipoAccionEnum,DepartmentService){
*/
.module('app.core') 
	.controller('DepartmentCtrl', ['$scope', '$log', 'DepartmentService','ModalService','$element', function ($scope, $log, DepartmentService, ModalService, $element) {

	$scope.departments=[{
		id: 1,
		name: 'lacteos'
	},{
		id: 2,
		name: 'embutidos'
	},{
		id: 3,
		name: 'jugos'
	},{
		id: 4,
		name: 'refrescos'
	},{
		id: 5,
		name: 'sabritas'
	},{
		id: 6,
		name: 'dulces'
	}];

  $scope.itemsByPage=3;

    function generateRandomItem() {

		var id='3';
		var nombre='nombre provisional';

       return {
            id: id,
            name: nombre,
        }
    }

    $scope.addRandomItem = function addRandomItem() {
        $scope.departments.push(generateRandomItem());
        id++;
    };

    $scope.removeItem = function removeItem(row) {
        var index = $scope.departments.indexOf(row);
        if (index !== -1) {
            $scope.departments.splice(index, 1);
        }
    }

	$scope.show= function editItem() {
		console.log('entra');
        ModalService.showModal({
            templateUrl: 'modal.html',
            controller: 'DepartmentCtrl'
        }).then(function(modal) {
			console.log('muestras'+ modal.element);
           modal.element.modal();
            modal.close.then(function(result) {
                $scope.message = "You said " + result;
            });
        }).catch(function(error) {
  // error contains a detailed error message.
  console.log('Errorsote=> '+ error);
});
	}
	
		/*$scope.tipoAccionEnum=TipoAccionEnum;
		console.log("Entra de partment");
        $scope.label = "{page: 'Página:', rowsPerPage: 'Registros por página:', of: 'de'}";
        $scope.optionsPage=[15,25,35];
        $scope.messageResponseDTO=null;

 		 $scope.requestTO={
 		 	initPage:0,
 		 	endPage:$scope.optionsPage[0],
 		 	dataTO:{
                id:null,
                name:null,
 		 	}
 		 };

         $scope.department={
            id:null,
            name:null,
         };

         $scope.inicializaRequest=function(){
            $scope.requestTO.dataTO={};
            $scope.requestTO.dataTO.id=$scope.department.id;
            $scope.requestTO.dataTO.name=$scope.department.name;
         };

 		 $scope.departmentLst=null;

 		 $scope.delete=function(form){
                $scope.inicializaRequest();
                $scope.requestTO.dataTO=JSON.stringify($scope.requestTO.dataTO);

     		 	DepartmentService.Delete($scope.requestTO)
                .success(
     		 		function(response){
                        $scope.departmentLst=JSON.parse(response.dtoLst);
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
 		 	   DepartmentService.Update($scope.requestTO).success(
 		 		function(response){
                    $scope.departmentLst=JSON.parse(response.dtoLst);
                    $scope.numberRecords=response.numberRecords;
 		 			$scope.clean(form);
 		 	});
 		 };

 		 $scope.save=function(form, event){
			  console.log("Entra a guardar");
 		 	if($scope.department.id==null && ($scope.department.name!=null && $scope.department.name!='') ){
                $scope.inicializaRequest();
                $scope.requestTO.dataTO=JSON.stringify($scope.requestTO.dataTO);
	 		 	DepartmentService.Save($scope.requestTO).success(
	 		 		function(response){
                        if(response.messageResponseDTO.code==1){
                            $scope.requestTO.initPage=response.pageInit;
                            $scope.requestTO.endPage=response.pageEnd;
                            $scope.query.page=response.numberPage;
                            $scope.departmentLst=JSON.parse(response.dtoLst);
                            $scope.numberRecords=response.numberRecords;

    	 		 			$scope.clean(form);
                        }
	 		 		});
 		 	}else{
 		 		$scope.showConfirm(form, TipoAccionEnum.UPDATE, event);
 		 	}
 		 };

 		 $scope.clean=function(form){
			  console.log('Entra a limpiar');
 		 	if(form){
				  console.log('Entra')
                $scope.department={};
				//$scope.departmentForm={};
               // $scope.departmentForm.$setPristine();
               // $scope.departmentForm.$setUntouched();
 		 	}

 		 };

 		 $scope.showConfirm=function(form, action, event){
 		 	var title=null;
 		 	var titleContent=null;

 		 	if(action==TipoAccionEnum.UPDATE){
 		 		title=$filter('translate')('mensaje.actualizar');
 		 		$scope.showDialog(form,action, title, event);
 		 	}else if(action==TipoAccionEnum.DELETE){
                if($scope.department.id!=null){
 		 		  title=$filter('translate')('mensaje.eliminar');
 		 		  $scope.showDialog(form, action, title, event);
                }else{
                    $scope.showDialog(null, null, $filter('translate')('mensaje.requiredIdDelete')); 
                }
 		 	}
 		 };

 		 $scope.showDialog=function(form, action, title, event){
 		 	 	/*var confirm=$mdDialog.confirm()
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
 		 		});*/
 		 /*};

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
			$scope.department.id=entity.id;
 		 	$scope.department.name=entity.name;
		}


 		$scope.getList=function(){
 		 	DepartmentService.DepartmentPagination($scope.requestTO).success(
 		 				function(responseDTO){
 		 					$scope.departmentLst=JSON.parse(responseDTO.dtoLst);
 		 					$scope.numberRecords=responseDTO.numberRecords;

 		 	});
 		};
   
 		$scope.getList();
	   $scope.clean($scope.departmentForm);*/
}])

.controller('ModalController', function($scope, close) {

  // when you need to close the modal, call close
  close("Success!");
});
