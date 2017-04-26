'use strict';
angular  					 
	.module('app.mensajes',['pascalprecht.translate'])
	.config(['$translateProvider', function($translateProvider){
		//$translateProvider.useSanitizeValueStrategy('sanitize',{
		$translateProvider.translations('es',{
			/*botones*/
			'save':'Guardar',
			'new':'Nuevo',
			'delete':'Eliminar',
			'ok':'Aceptar',
			'cancel':'Cancelar',

			/*mensajes*/
			'mensaje.actualizar':'¿Desea continuar con la actualizacion del registro?',
			'mensaje.eliminar':'¿Desea continuar con la eliminación del registro?',

			/*ProductCategory*/
			'name':'Nombre',
			'id':'Id',
			'mensaje.maxlength':'El nombre acepta solo máximo 50 caracteres',

			/*BillingData*/

			'address':'Dirección',
			'telephone':'Teléfono',
			'rfc':'RFC',
			'zipCode':'Código Postal',
			         
			/*Category*/
			'description':'Descripcion',

			/*Discount*/
			'typeDiscountId  ':'Tipo de descuento',
			'amount':'Cantidad',
			'startDate':'Fecha de inicio de descuento',
			'endDate':'Fecha fin de descuento',
			'productId':'Producto',
			'status':'Estatus',

			/*mensajes standar*/
			'mensaje.required':'El dato es requerido',
			'mensaje.requiredIdDelete':'Es necesario seleccionar el elemento a eliminar',


		});
		$translateProvider.preferredLanguage('es');

	}]);