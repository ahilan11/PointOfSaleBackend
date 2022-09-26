/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package com.za.carolsstore.resources;


import com.za.carolsstore.message.model.Email;
import com.za.carolsstore.message.service.EmailService;
import com.za.carolsstore.message.service.iEmailService;
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
@Path("email")
public class EmailResource {

    @Context
    private UriInfo context;

    private final iEmailService emailService;
    /**
     * Creates a new instance of EmailResource
     */
    public EmailResource() {
        emailService = new EmailService();
    }

    /**
     * Retrieves representation of an instance of com.carolsboutique.pointofsale.rest.EmailResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of EmailResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
    
      @POST
    @Path("addEmail")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addEmail(Email email){
        
        return Response.status(Response.Status.OK).entity(emailService.addEmail(email)).build();
    }
    
    @POST
    @Path("sendEmailNotification")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response sendEmailNotification(Email email){
        
        return Response.status(Response.Status.OK).entity(emailService.sendEmailNotifiction(email.getAddress())).build();
    }
    
     @POST
    @Path("sendEmailReceipt")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response sendEmailReceipt(Email email){
        
        return Response.status(Response.Status.OK).entity(emailService.sendEmailRecipt(email.getAddress(),null)).build();
    }
}
