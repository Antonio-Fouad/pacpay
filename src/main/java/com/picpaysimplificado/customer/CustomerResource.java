package com.picpaysimplificado.customer;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/customers")
public class CustomerResource {

    @Inject
    CustomerService service;

    @POST
    public Response create(CustomerDTO customer) {

        service.create(customer);
        return Response.status(Response.Status.CREATED).build();
    }
    @GET
    public Response read() {
        List<CustomerDTO> customer = service.selectAll();
        return Response.status(Response.Status.OK).entity(customer).build();
    }

    @PUT
    public Response updateAll() {
        String msg =  "{ \"msg\": \"Método PUT\"}";
        return Response.status(Response.Status.OK).entity(msg).build();
    }

    @PATCH
    public Response updatePartial() {
        String msg =  "{ \"msg\": \"Método PATCH\"}";
        return Response.status(Response.Status.OK).entity(msg).build();
    }

    @DELETE
    public Response delete() {
        String msg =  "{ \"msg\": \"Método DELETE\"}";
        return Response.status(Response.Status.NO_CONTENT).entity(msg).build();
    }
}
