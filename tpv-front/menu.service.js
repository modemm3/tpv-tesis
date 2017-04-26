(function(){

'use strict';

  angular.module('common.services')
  .factory('menu', [
      '$location',
      '$rootScope',
      function ($location) {

        var sections = [{
          name: 'Inicio',
          state: 'home.gettingstarted',
          type: 'link',
          icon: 'M0 0h18v18h-18z'
        }];

        sections.push({
          name: 'Catálogos',
          type: 'toggle',
          pages: [{
            name: 'Categoría de Productos',
            type: 'link',
            state: 'home.beers.ipas',
            icon: 'fa fa-group'
          }, {
            name: 'Proveedores',
            state: 'home.beers.porters',
            type: 'link',
            icon: 'fa fa-map-marker'
          },
            {
              name: 'Clientes',
              state: 'home.beers.wheat',
              type: 'link',
              icon: 'fa fa-plus'
            }]
        });

        sections.push({
          name: 'Operaciónes',
          type: 'toggle',
          pages: [{
            name: 'Ventas',
            type: 'link',
            state: 'munchies.cheetos',
            icon: 'fa fa-group'
          }, {
            name: 'Corte de Caja',
            state: 'munchies.bananachips',
            type: 'link',
            icon: 'fa fa-map-marker'
          },
            {
              name: 'Inventarios',
              state: 'munchies.donuts',
              type: 'link',
              icon: 'fa fa-map-marker'
            }]
        });

        var self;

        return self = {
          sections: sections,

          toggleSelectSection: function (section) {
            self.openedSection = (self.openedSection === section ? null : section);
          },
          isSectionSelected: function (section) {
            return self.openedSection === section;
          },

          selectPage: function (section, page) {
            page && page.url && $location.path(page.url);
            self.currentSection = section;
            self.currentPage = page;
          }
        };

        function sortByHumanName(a, b) {
          return (a.humanName < b.humanName) ? -1 :
            (a.humanName > b.humanName) ? 1 : 0;
        }

      }])
      
})();

                