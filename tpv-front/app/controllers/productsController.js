angular.module('tpv.productsboard')
.controller('productsboardCtrl', function(){
	var productsboard=this;

	productsboard.products=[{
		"assignee":"1",
		"criteria":"It tests!",
		"description":"This is a test",
		"id":"1",
		"reporter":"2",
		"status":"To Do",
		"title":"First products",
		"type":"Spike"
	},{
		"assignee":"2",
		"criteria":"It works!",
		"description":"testing something",
		"id":"2",
		"reporter":"1",
		"status":"In progress",
		"title":"Second Products",
		"type":"Enhancement"
	}];

	productsboard.statuses=[
		{name:'To Do'},
		{name:'In Progress'},
		{name:'Code Review'},
		{name:'QA Review'},
		{name:'Verified'}
	];
})