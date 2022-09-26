/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package com.za.carolsstore.resources;


import com.za.carolsstore.product.model.Product;
import com.za.carolsstore.employee.model.Employee;
import com.za.carolsstore.sale.model.Sale;
import com.za.carolsstore.sale.model.SaleLineItem;
import com.za.carolsstore.sale.service.SaleService;
import com.za.carolsstore.sale.service.iSaleService;
import com.za.carolsstore.store.model.Store;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * REST Web Service
 *
 * @author Vijay
 */
@Path("sale")
public class SaleResource {

    @Context
    private UriInfo context;
    
    private iSaleService saleService;

    /**
     * Creates product new instance of SaleResource
     */
    public SaleResource() {
        saleService = new SaleService();
    }

    /**
     * Retrieves representation of an instance of com.carolsboutique.pointofsale.rest.SaleResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of SaleResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
    
    
    @Path("/addProductToLineItem")
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces(MediaType.APPLICATION_JSON)
    public Response addProductToLineItem(SaleLineItem c) {
        Product p = new Product();
        p.setId(c.getProduct());
        return Response.status(Response.Status.OK).entity(saleService.addProductToLineItem(p, c.getQuantity(), c.getSaleID())).build();
    }

    @Path("/removeProductFromLineItem")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response removeProductFromLineItem(Carrier c) {
        return Response.status(Response.Status.OK).entity(saleService.removeProductFromLineItem(c.product, c.employee)).build();
    }

    @Path("/clearSaleLineItem")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response clearSaleLineItem() {
        
        return Response.status(Response.Status.OK).entity(saleService.clearSaleLineItem()).build();
    }

    @Path("/addSale")
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces(MediaType.APPLICATION_JSON)
    public Response addSale(Sale sale) {
        return Response.status(Response.Status.OK).entity(saleService.addSale(sale)).build();
    }

    @Path("/getSale/{saleID}")
    @GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSale(@PathParam("saleID")String sale) {
        return Response.status(Response.Status.OK).entity(saleService.getSale(sale)).build();
    }

    @Path("/getSalesByStore/{storeID}")
    @GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSalesByStore(@PathParam("storeID")String store) {
        return Response.status(Response.Status.OK).entity(saleService.getSalesByStore(store)).build();
    }

    @Path("/getSalesByEmployee/{employeeID}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSalesByEmployee(@PathParam("employeeID")String employee) {
        return Response.status(Response.Status.OK).entity(saleService.getSalesByEmployee(employee)).build();
    }

    @Path("/getSalesOfProduct")
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSalesOfProduct(Product product) {
        return Response.status(Response.Status.OK).entity(saleService.getSalesOfProduct(product.getId())).build();
    }

    @Path("/getSalesOfProductsByStore/{productID}/{storeID}")
    @GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSalesOfProductByStore(@PathParam("productID")String productID, @PathParam("storeID")String storeID ) {
        return Response.status(Response.Status.OK).entity(saleService.getSalesOfProductByStore(productID, storeID)).build();
    }

    @Path("/getSalesOfProductsByEmployee/{productID}/{employeeID}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSalesOfProductByEmployee(@PathParam("productID")String product, @PathParam("empoleeID")String employee) {
        return Response.status(Response.Status.OK).entity(saleService.getSalesOfProductByEmployee(product, employee)).build();
    }

    @Path("/getAllSales")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllSales() {
        return Response.status(Response.Status.OK).entity(saleService.getAllSales()).build();
    }

    @Path("/updateSale")
    @PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateSale(Sale sale) {
        return Response.status(Response.Status.OK).entity(saleService.updateSale(sale)).build();
    }

    @Path("/deleteSale/{saleID}")
    @DELETE
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteSale(@PathParam("saleID")String sale) {
        return Response.status(Response.Status.OK).entity(saleService.deleteSale(sale)).build();
    }
    
    @Path("/checkInventory/{storeID}/{productID}")
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public int checkInventory(@PathParam("storeID")String store,@PathParam("productID") String product){
        return saleService.checkInventory(store, product);
    }
    
    @Path("/increaseInventory")
    @PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces(MediaType.APPLICATION_JSON)
    public Response increaseInventory(Carrier c){
        return Response.status(Response.Status.OK).entity(saleService.increaseInventory(c.store.getStoreId(), c.product.getId(), c.number)).build();  
    }
    
    @Path("/decreaseInventory")
    @PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces(MediaType.APPLICATION_JSON)
    public Response decreaseInventory(Carrier c){
        return Response.status(Response.Status.OK).entity(saleService.decreaseInventory(c.store.getStoreId(), c.product.getId(), c.number)).build();
    }
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public class Carrier{
        Product product;
        Store store;
        Integer number;
        Employee employee;
    }
}
