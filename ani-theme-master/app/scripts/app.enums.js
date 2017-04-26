'use strict'
angular
		.module('app.enums',[])
		.constant('TipoAccionEnum', {
			NEW: 1,
			UPDATE:2,
			DELETE:3
		})
		.constant('EstatusDescuentoEnum', [{
			id:1, name:'Activo', value:true
		},{id:2, name:'Inactivo', value:false}])
		;