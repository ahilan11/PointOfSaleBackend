/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package com.za.carolsstore.resources;


import com.za.carolsstore.employee.model.Employee;
import com.za.carolsstore.employee.service.EmployeeServiceImp;
import com.za.carolsstore.employee.service.iEmployeeService;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.DELETE;

/**
 * REST Web Service
 *
 * @author Vijay
 */
@Path("employee")
public class EmployeeResource {


    private iEmployeeService employeeService;
    /**
     * Creates a new instance of EmployeeResource
     */
    public EmployeeResource() {
        employeeService = new EmployeeServiceImp();
    }

    /**
     * Retrieves representation of an instance of com.carolsboutique.pointofsale.rest.EmployeeResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of EmployeeResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
    
     @Path("/addEmployee")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addEmployee(Employee employee){
        return Response.status(Response.Status.OK).entity(employeeService.addEmployee(employee)).build();
    }
    
    @Path("/updateEmployee")
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateEmployee(Employee employee){
        return Response.status(Response.Status.OK).entity(employeeService.updateEmployee(employee)).build();
    }
    
    @Path("/updateEmployeeRole")
    @PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateEmployeeRole(Employee employee){
        return Response.status(Response.Status.OK).entity(employeeService.updateEmployeeRole(employee)).build();
    }
    
    @Path("/deleteEmployee/{employeeID}")
    @DELETE
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteEmployee(@PathParam("employeeID")String employee){
        return Response.status(Response.Status.OK).entity(employeeService.deleteEmployee(employee)).build();
    }
    
    @Path("/getAllEmployees")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllEmployees(){
        return Response.status(Response.Status.OK).entity(employeeService.getAllEmployees()).build();
    }
    
    @Path("/getEmployee/{employeeID}")
    @GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmployee(@PathParam("employeeID")String employee){
        return Response.status(Response.Status.OK).entity(employeeService.getEmployee(employee)).build();
    }
    
    @Path("/getEmployeesByStore/{storeID}")
    @GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStoreEmployees(@PathParam("storeID")String store){
        return Response.status(Response.Status.OK).entity(employeeService.getEmployeesByStore(store)).build();
    }
}
