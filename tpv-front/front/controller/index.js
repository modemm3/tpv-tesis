var myapp = angular.module('myapp', ["ui.router"])
myapp.config(function($stateProvider, $urlRouterProvider){

$urlRouterProvider.otherwise("login")
	$stateProvider.state('uno',{
		templateUrl:"uno.html"
		/*template:"<h1>Hola de nuevo  kkkk</h1>"*/
	}).state("dos",{
		/*url:"dos.html",*/
		template:"<h1>Hola de nuevo</h1>"
	})
})