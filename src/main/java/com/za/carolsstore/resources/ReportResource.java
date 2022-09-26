/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package com.za.carolsstore.resources;

import com.za.carolsstore.product.model.Product;
import com.za.carolsstore.report.model.Report;
import com.za.carolsstore.report.service.ReportGeneratorImp;
import com.za.carolsstore.report.service.iReportGenerator;
import com.za.carolsstore.store.model.Store;
import java.time.Month;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import jakarta.ws.rs.PathParam;

/**
 * REST Web Service
 *
 * @author Vijay
 */
@Path("/report")
public class ReportResource {

    private final iReportGenerator reportGenerator;

//    @Context
//    private UriInfo context;
    /**
     * Creates a new instance of ReportResource
     *
     * @return
     */
    public ReportResource() {
        reportGenerator = new ReportGeneratorImp();
    }

    /**
     * Retrieves representation of an instance of
     * com.carolsboutique.pointofsale.rest.ReportResource
     *
     * @return an instance of java.lang.String
     */
    /**
     * PUT method for updating or creating an instance of ReportResource
     *
     * @param content representation for the resource
     */
    @PUT
    @Path("/getProduct/{productID}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }

    @Path("/dailyTarget")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response reportStoreDailyTarget() {
        return Response.status(Response.Status.OK).entity(reportGenerator.dailyTarget()).build();
    }

    @Path("/monthlyTarget/{month}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response reportStoresAchievedTarget(@PathParam("month") Integer month) {
        return Response.status(Response.Status.OK).entity(reportGenerator.monthlyTarget(month)).build();
    }

    @Path("/topSellingStores/{limit}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response reportStoresTopSelling(@PathParam("limit") Integer amount) {
        return Response.status(Response.Status.OK).entity(reportGenerator.topSellingStores(amount)).build();
    }

 
    @Path("/topRatedStores/{month}/{limit}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response reportStoresTopRated(@PathParam("month")String month, @PathParam("limit")String amount) {
               int mt = Integer.parseInt(month);
        int mts = Integer.parseInt(amount);

        return Response.status(Response.Status.OK).entity(reportGenerator.topRatedStores(mt,mts)).build();
    }



    @Path("/leastSellingStores/{months}/{amount}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response reportStoresLeastSelling(@PathParam("months")String months, @PathParam("amount")String amount) {
               int mt = Integer.parseInt(months);
        int mts = Integer.parseInt(amount);

        return Response.status(Response.Status.OK).entity(reportGenerator.leastSellingStores(mt, mts)).build();
    }

    @Path("/topStoreEmployees/{store}/{limit}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response reportEmployeesTopSelling(@PathParam("store")String store, @PathParam("limit")String limit) {
        Store st = new Store();
        st.setStoreId(store);
                int mt = Integer.parseInt(limit);

        return Response.status(Response.Status.OK).entity(reportGenerator.topStoreEmployees(st,mt)).build();
    }


  
    @Path("/topEmployees/{amount}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response reportEmployeesTopSelling(@PathParam("amount")Integer amount) {
        return Response.status(Response.Status.OK).entity(reportGenerator.topEmployees(amount)).build();
    }

    @Path("/topProducts/{product}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response reportProduct( @PathParam("product")String product) {
        Product pd = new Product();
        pd.setId(product);
        return Response.status(Response.Status.OK).entity(reportGenerator.topProducts(pd)).build();
    }

    @Path("/topSellingProducts")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response reportProductsTop40Selling() {
        return Response.status(Response.Status.OK).entity(reportGenerator.topSellingProducts()).build();
    }

    @Path("/topSales/{store}/{month}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response reportSales(@PathParam("store")String store, @PathParam("month")String month) {
        Store st = new Store();
        st.setStoreId(store);
        int mt = Integer.parseInt(month);
        return Response.status(Response.Status.OK).entity(reportGenerator.topSales(st, mt)).build();
    }
}
