var myModule = angular.module('tpv', []);

myModule.controller('mainCtrl', function(tpvModel){
	var main=this;
	main.products=tpvModel.getProducts();

	main.createProducts = function() {
main.products.push({
title:'New Story',
description:'Description pending.',
type:'Feature'
});
};

});

myModule.directive('productsdirective', function(){
	var valoresInternos={};

	valoresInternos.scope={
		nameProduc:'@name',
		descriptionProduc:'@description'
	}

	valoresInternos.restrict='E';
	valoresInternos.template="¡Bienvenido {{nameProduc}} {{descriptionProduc}}!";
	return valoresInternos;
});

myModule.service('tpvModel', function(){
	var service=this,
	products=[{
		title:'leche',
		description:'blanca sola',
		type:'lacteos',
		price:23,
		date:1442638800000
	},{

		title:'coca',
		description:'gaseosa',
		type:'refresco',
		price:23,
		date:1442638800000
	
	},{
		title:'arroz',
		description:'aleguminosa',
		type:'alimentos',
		price:23,
		date:1442638800000
	},{
		title:'lechosa',
		description:'blanca sola lechosa',
		type:'lacteos',
		price:23,
		date:1442638800000
	}];
	service.getProducts=function(){
		return products;
	};
});

myModule.filter('cambiaProducto', function(){
	var cambiaProfuctoFilter= function(cambiaDatosOriginales, argumento, segundoArgumento){
		return cambiaDatosOriginales.replace(RegExp(segundoArgumento,"g"),argumento);
	}
	return cambiaProfuctoFilter;

});
/*myModule.factory('tpvHelper', function(){
	var buildIndex=function(source, property){
		var tempArray=[];

		for(var i=0, len=source.length; i<len; ++i){
			tempArray[source[i][property]]=source[i];
		}
		return tempArray;
	};
	return {
		buildIndex: buildIndex
	};
});

myModule.directive('products',function(){
	return {
		scope:true,
		replace:true,
		template:'<div><h4> {{products.title}}</h4><p>{{products.description}}</p></div>'
	}
});*/

/*myModule.config(function($routeProvider) {
$routeProvider.when('/', {
templateUrl: 'tpv-front/index.html',
controller: 'productsboardCtrl',
controllerAs: 'productsboard'
});
});*/
/*myModule.controller('mainCtrl', function() {
var main = this;

this.title='Hola';

main.products = [
{
title: 'Leche lalal',
description: 'Nutritivo',
criteria: 'Criteria pending.',
total:40,
}
];
});*/