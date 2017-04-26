(function(){
  'use strict';
  
  angular.module('myMenuApp.controllers')
.controller('HomeCtrl', [
      '$rootScope',
      '$log',
      '$state',
      '$timeout',
      '$location',
      '$scope', 
      '$mdSidenav',
      'menu',

      function ($rootScope, $log, $state, $timeout, $location,$scope,$mdSidenav, menu) {

        var vm = this;
        var aboutMeArr = ['Family', 'Location', 'Lifestyle'];
        var budgetArr = ['Housing', 'LivingExpenses', 'Healthcare', 'Travel'];
        var incomeArr = ['SocialSecurity', 'Savings', 'Pension', 'PartTimeJob'];
        var advancedArr = ['Assumptions', 'BudgetGraph', 'AccountBalanceGraph', 'IncomeBalanceGraph'];

        //functions for menu-link and menu-toggle
        vm.isOpen = isOpen;
        vm.toggleOpen = toggleOpen;
        vm.autoFocusContent = false;
        vm.menu = menu;


        vm.status = {
          isFirstOpen: true,
          isFirstDisabled: false
        };

    $scope.toggleSidenav = function(menuId) {
      $mdSidenav(menuId).toggle();
    };
        function isOpen(section) {
          return menu.isSectionSelected(section);
        }

        function toggleOpen(section) {
          menu.toggleSelectSection(section);
        }

      }])
})();

                