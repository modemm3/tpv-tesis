'use strict';

/**
 * @ngdoc overview
 * @name yapp
 * @description
 * # yapp
 *
 * Main module of the application.
 */
angular
  .module('app.routes', [
    'ui.router'
  ])
  .config(function($stateProvider, $urlRouterProvider) {

    $urlRouterProvider.when('/dashboard', '/dashboard/overview');
    $urlRouterProvider.otherwise('/login');

    $stateProvider
      .state('base', {
        abstract: true,
        url: '',
        templateUrl: 'views/base.html'
      })
        .state('login', {
          url: '/login',
          parent: 'base',
          templateUrl: 'views/login.html',
          controller: 'LoginCtrl'
        })
        .state('dashboard', {
          url: '/dashboard',
          parent: 'base',
          templateUrl: 'views/dashboard.html',
          controller: 'DashboardCtrl'
        })
          .state('overview', {
            url: '/overview',
            parent: 'dashboard',
            templateUrl: 'views/dashboard/overview.html'        
          })
          .state('reports', {
            url: '/reports',
            parent: 'dashboard',
            templateUrl: 'views/dashboard/reports.html'
          })
          .state('billingdata',{
            url: '/billingdata',
            parent: 'dashboard', 
            templateUrl: 'views/dashboard/billingdata.html',
            controller: 'BillingDataCtrl'
          })
          .state('category',{
            url: '/category',
            parent: 'dashboard', 
            templateUrl: 'views/dashboard/category.html',
            controller:'CategoryCtrl'
          })
          .state('department',{
            url: '/department',
            parent: 'dashboard', 
            templateUrl: 'views/dashboard/department.html',
            controller: 'DepartmentCtrl'
          })
          .state('discount',{
            url: '/discount',
            parent: 'dashboard', 
            templateUrl: 'views/dashboard/discount.html',
            controller: 'DiscountCtrl'
          })
           .state('employeecategory',{
            url: '/employeecategory',
            parent: 'dashboard', 
            templateUrl: 'views/dashboard/employeecategory.html',
            controller: 'EmployeeCategoryCtrl'
          })
          .state('paymenttype',{
            url: '/paymenttype',
            parent: 'dashboard', 
            templateUrl: 'views/dashboard/paymenttype.html',
            controller: 'PaymentTypeCtrl'
          })
          .state('provider',{
            url: '/provider',
            parent: 'dashboard', 
            templateUrl: 'views/dashboard/provider.html'
          })
          .state('typediscount',{
            url: '/typediscount',
            parent: 'dashboard', 
            templateUrl: 'views/dashboard/typediscount.html',
            controller: 'TypeDiscountCtrl'
          });

  });
