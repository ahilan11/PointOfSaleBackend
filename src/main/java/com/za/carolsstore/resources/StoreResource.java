/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package com.za.carolsstore.resources;


import com.za.carolsstore.store.model.Review;
import com.za.carolsstore.store.model.Store;
import com.za.carolsstore.store.service.StoreService;
import com.za.carolsstore.store.service.iStoreService;
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
@Path("store")
public class StoreResource {

    @Context
    private UriInfo context;

    private iStoreService storeService;
    /**
     * Creates a new instance of StoreResource
     */
    public StoreResource() {
        storeService = new StoreService();
    }

    /**
     * Retrieves representation of an instance of com.carolsboutique.pointofsale.rest.StoreResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of StoreResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
    
     @Path("/addStore")
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces(MediaType.APPLICATION_JSON)
    public Response addStore(Store store) {
        return Response.status(Response.Status.OK).entity(storeService.addStore(store)).build();

    }

    @Path("/getStore/{storeID}")
    @GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStore(@PathParam("storeID")String store) {

        return Response.status(Response.Status.OK).entity(storeService.getStore(store)).build();

    }

    @Path("/updateStore")
    @PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateStore(Store store) {

        return Response.status(Response.Status.OK).entity(storeService.updateStore(store)).build();

    }

    @Path("/deleteStore/{storeID}")
    @DELETE
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteStore(@PathParam("storeID")String store) {

        return Response.status(Response.Status.OK).entity(storeService.deleteStore(store)).build();

    }

    @Path("/addReview")
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces(MediaType.APPLICATION_JSON)
    public Response addReview(Review review) {

        return Response.status(Response.Status.OK).entity(storeService.addReview(review)).build();

    }

    @Path("/getReview/{reviewID}")
    @GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces(MediaType.APPLICATION_JSON)
    public Response getReview(@PathParam("reviewID")String review) {

        return Response.status(Response.Status.OK).entity(storeService.getReview(review)).build();

    }

    @Path("/getReviewsByStore/{storeID}")
    @GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces(MediaType.APPLICATION_JSON)
    public Response getReviewsByStore(@PathParam("storeID")String store) {

        return Response.status(Response.Status.OK).entity(storeService.getReviewsByStore(store)).build();

    }

    @Path("/getAllReviews")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllReviews() {

        return Response.status(Response.Status.OK).entity(storeService.getAllReviews()).build();

    }

    
    @Path("/deleteReview/{reviewID}")
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteReview(@PathParam("reviewID")String review) {

        return Response.status(Response.Status.OK).entity(storeService.deleteReview(review)).build();

    }
}
