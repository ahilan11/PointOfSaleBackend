/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package com.za.carolsstore.resources;


import com.za.carolsstore.product.model.InterBranchTransfer;
import com.za.carolsstore.product.model.KeepAside;
import com.za.carolsstore.product.model.Product;
import com.za.carolsstore.product.service.ProductServiceImp;
import com.za.carolsstore.product.service.iProductService;
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

/**
 * REST Web Service
 *
 * @author Vijay
 */
@Path("product")
public class ProductResource {

    @Context
    private UriInfo context;
    
    private final iProductService productService;
    /**
     * Creates a new instance of ProductResource
     */
    public ProductResource() {
        this.productService = new ProductServiceImp();
    }

  
    
    
      @Path("/getProduct/{productID}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProduct(@PathParam("productID")String product) {

      return Response.status(Response.Status.OK).entity(productService.getProduct(product)).build();

                
    }
@Path("/getStoresByProduct/{productID}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStoresByProduct(@PathParam("productID")String productID) {

      return Response.status(Response.Status.OK).entity(productService.getStoresByProduct(productID)).build();

                
    }
      @Path("/updateProduct")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateProduct(Product product) {
                
    
      return Response.status(Response.Status.OK).entity(productService.updateProduct(product)).build();
             
    }
      @Path("/deleteProduct/{productID}")
    @POST
    
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteProduct(@PathParam("productID")String product) {     
        return Response.status(Response.Status.OK).entity(productService.deleteProduct(product)).build();
  
    }
    
    @Path("/addIbt")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addIBT(InterBranchTransfer product) {
        return Response.status(Response.Status.OK).entity(productService.addIBT(product)).build();
        
    }

      @Path("/approveIbt")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response approveIBT(InterBranchTransfer ibt) {

        return Response.status(Response.Status.OK).entity(productService.approveIbt(ibt)).build();
                
    }

      @Path("/receiveIbt")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response receiveIBT(InterBranchTransfer product) {
                
    
        return Response.status(Response.Status.OK).entity(productService.receiveIbt(product)).build();
             
    }
      @Path("/deleteIbt/{ibt}")
    @DELETE
    
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteIBT(@PathParam("ibt")String ibt) {
        
        
        return Response.status(Response.Status.OK).entity(productService.deleteIbt(ibt)).build();
  
    }
    @Path("/getKeepAside/{keepAsideID}")
    @GET
    
    @Produces(MediaType.APPLICATION_JSON)
    public Response getKeepAside(@PathParam("keepAsideID")String keepAsideID) {
        return Response.status(Response.Status.OK).entity(productService.getKeepAside(keepAsideID)).build();
    }
    
    @Path("/getKeepAsidesByStore/{storeID}")
    @GET
    
    @Produces(MediaType.APPLICATION_JSON)
    public Response getKeepAsidesByStore(@PathParam("storeID")String storeID) {
     return Response.status(Response.Status.OK).entity(productService.getKeepAsidesByStore(storeID)).build();
    }

    @Path("/getAllKeepAsides")
    @GET
    
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllKeepAsides() {
      
        return Response.status(Response.Status.OK).entity(productService.getAllKeepAsides()).build();
    }
      @Path("/addKeepAside")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
       public Response addKeepAside(KeepAside keepAside){
            return Response.status(Response.Status.OK).entity(productService.addKeepAside(keepAside)).build();
        }
       
         @Path("/updateKeepAside")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
   public Response updateKeepAside(KeepAside keepAside){
        return Response.status(Response.Status.OK).entity(productService.updateKeepAside(keepAside)).build();
    }
   
    @Path("/deleteKeepAside/{keepAsideID}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
   public Response deleteKeepAside(@PathParam("keepAsideID")String keepAsideID){
        return Response.status(Response.Status.OK).entity(productService.deleteKeepAside(keepAsideID)).build();
    }

}
