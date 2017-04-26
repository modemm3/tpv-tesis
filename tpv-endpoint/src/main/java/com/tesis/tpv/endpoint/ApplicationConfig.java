/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tesis.tpv.endpoint;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 * */
@javax.ws.rs.ApplicationPath("services")
public class ApplicationConfig extends Application {
    
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(com.tesis.tpv.endpoint.SailDetailService.class);
        resources.add(com.tesis.tpv.endpoint.BillingDataService.class);
        resources.add(com.tesis.tpv.endpoint.BillingService.class);
        resources.add(com.tesis.tpv.endpoint.CategoryService.class);
        resources.add(com.tesis.tpv.endpoint.DepartmentService.class);
        resources.add(com.tesis.tpv.endpoint.DiscountService.class);
        resources.add(com.tesis.tpv.endpoint.EmployeeCategoryService.class);
        resources.add(com.tesis.tpv.endpoint.EmployeeService.class);
        resources.add(com.tesis.tpv.endpoint.PaymentTypeService.class);
        resources.add(com.tesis.tpv.endpoint.ProductService.class);
        resources.add(com.tesis.tpv.endpoint.ProviderProductService.class);
        resources.add(com.tesis.tpv.endpoint.ProviderService.class);
        resources.add(com.tesis.tpv.endpoint.SaleService.class);
        resources.add(com.tesis.tpv.endpoint.TypeDiscountService.class);
        resources.add(com.tesis.tpv.endpoint.CorsResponseFilter.class);
    }
    
}
