/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package com.za.carolsstore.resources;


import com.za.carolsstore.product.model.Category;
import com.za.carolsstore.product.model.InterBranchTransfer;
import com.za.carolsstore.product.model.KeepAside;
import com.za.carolsstore.product.model.Product;
import com.za.carolsstore.employee.model.Employee;
import com.za.carolsstore.identity.service.IdentityGenerator;
import com.za.carolsstore.identity.service.iIdentityGenerator;
import com.za.carolsstore.message.model.Email;
import com.za.carolsstore.sale.model.Sale;
import com.za.carolsstore.store.model.Store;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author Vijay
 */
@Path("identity")
public class IdentityResource {

    @Context
    private UriInfo context;

    private iIdentityGenerator ig;
    /**
     * Creates a new instance of IdentityResource
     */
    public IdentityResource() {
        ig = new IdentityGenerator();
    }

    /**
     * Retrieves representation of an instance of com.carolsboutique.pointofsale.rest.IdentityResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
return "h";    }

    /**
     * PUT method for updating or creating an instance of IdentityResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
    
    @POST
    @Path("generateIDCategory")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response generateID(Category category){
        return  Response.status(Response.Status.OK).entity(ig.generateID(category)).build();
    }
    
    @POST
    @Path("generateIDEmail")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response generateID(Email email){
        return  Response.status(Response.Status.OK).entity(ig.generateID(email)).build();
    }
    
    @POST
    @Path("generateIDEmployee")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response generateID(Employee employee){
        return  Response.status(Response.Status.OK).entity(ig.generateID(employee)).build();
    }
    
    @POST
    @Path("generateIDIbt")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response generateID(InterBranchTransfer ibt){
        return  Response.status(Response.Status.OK).entity(ig.generateID(ibt)).build();
    }
    
    @POST
    @Path("generateIDKeepAside")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response generateID(KeepAside keepAside){
        return  Response.status(Response.Status.OK).entity(ig.generateID(keepAside)).build();
    }
    
    @POST
    @Path("generateIDProduct")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response generateID(Product product){
        return  Response.status(Response.Status.OK).entity(ig.generateID(product)).build();
    }
    
     @POST
    @Path("generateIDSale")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response generateID(Sale sale){
        return  Response.status(Response.Status.OK).entity(ig.generateID(sale)).build();
    }
    
     @POST
    @Path("generateIDStore")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response generateID(Store store){
        return  Response.status(Response.Status.OK).entity(ig.generateID(store)).build();
    }
    
     @POST
    @Path("generateIDCatProd")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response generateID(Category category, Product product){
        return  Response.status(Response.Status.OK).entity(ig.generateID(category, product)).build();
    }
}
