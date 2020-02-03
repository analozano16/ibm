var app = angular.module("Clientes", ['ngRoute']);

app.config(function($routeProvider) {
	$routeProvider.when('Clientes', {template: 'Gutten'});
	$routeProvider.when('Asesores', {template: 'Hello'});	
});


app.controller("ClienteController", function($scope){
	
	
		  console.log("dddddd");
		 angular.element(document).ready(function () {
	        
	      
	    });
   
});