tpv.controller('ctrlCategoryProduct',['$scope','prodCatModel',function($scope, prodCatModel){
	$scope.catProdList=prodCatModel.list();
	$scope.data={};

	$scope.quitarAlerta=function(){
		$scope.mensajeError='';
	}

	$scope.muestrAlerta=function(mnj){
		$scope.mensajeError=mnj;
	}

	$scope.save=function(){

		$scope.quitarAlerta();
		if($scope.data.nombre!=null)
		{
			var catProd={
				name:$scope.data.nombre,
				description:$scope.data.descripcion
			};

			prodCatModel.save(catProd);
		}
	}

	$scope.update=function(){
		$scope.quitarAlerta();
		if($scope.data.id!=null && $scope.data.id!=''){
			var catProd={
				name:$scope.data.nombre,
				description:$scope.data.descripcion
			}
			prodCatModel.update($scope.data.id, catProd);
		}
		else{
			$scope.muestrAlerta('¡Seleccione un elemento de la lista para modificar!');
			//alert('Seleccione un elemento de la lista para modificar');
		}
		  
	}

	$scope.delete=function(){
		$scope.quitarAlerta();
		if($scope.data.id!=null && $scope.data.id!=''){
			prodCatModel.remove($scope.data.id);

		}else{
			$scope.muestrAlerta('¡Seleccione un elemento de la lista para modificar!');
		}
	}

	$scope.find=function(){
		$scope.dataBusqueda='data.nombre';
		//alert($scope.data.id);
	}

	$scope.reset=function(){
		$scope.data = {};
		//angular.copy($scope.master);
		if($scope.form){
     	 $scope.form.$setPristine();
     	 $scope.form.$setUntouched();
    	}
    			$scope.quitarAlerta();

	}

	$scope.muestra=function(catProd){
			//	alert(catProd.description);
		//$scope.data={};
		$scope.quitarAlerta();

		if($scope.data!=null){
			$scope.data.id=catProd.id,
			$scope.data.nombre=catProd.name,
			$scope.data.descripcion=catProd.description
		}else
			alert('no exites');
	}

	$scope.reset();
}
])