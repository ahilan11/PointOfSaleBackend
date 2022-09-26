/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.za.carolsstore.resources;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.za.carolsstore.sale.model.SaleLineItem;
import com.za.carolsstore.sale.service.InventoryService;
import com.za.carolsstore.sale.service.InventoryServiceInt;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 
 */
@Path("inventory")
public class InventoryResource {
    private InventoryServiceInt service;
    public InventoryResource(){
        service = new InventoryService();
    }
    @GET
    public Response ping(){
        return Response
                .ok("ping Jakarta EE")
                .build();
    }
    private String stringJson(Object o) {
        try {
            return new ObjectMapper().writeValueAsString(o);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(InventoryResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "failure";
    }
    @Path("checkInventory/{storeID}/{productID}/{quantity}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response checkInventory(@PathParam("storeID")String storeID, @PathParam("productID")String productID ,@PathParam("quantity") String quantity){
        return Response.status(Response.Status.OK).entity(stringJson(service.checkAvailableStock(productID, storeID, quantity))).build();
    }
    
    @Path("increaseInventory")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response increaseInventory(SaleLineItem sli){
        return Response.status(Response.Status.OK).entity(service.addStock(sli.getProduct(), sli.getStoreID(), sli.getQuantity())).build();
    }
     @Path("decreaseInventory")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
         @Consumes(MediaType.APPLICATION_JSON)
    public Response decreaseInventory(SaleLineItem sli){
        return Response.status(Response.Status.OK).entity(service.decreaseStock(sli.getProduct(), sli.getStoreID(), sli.getQuantity())).build();
    }
}
