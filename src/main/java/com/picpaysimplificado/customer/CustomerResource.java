package com.picpaysimplificado.customer;

import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.reactive.RestResponse;

import java.util.List;

@Path("/customers")
public class CustomerResource {

    @Inject
    CustomerService service;

    @POST
    public Response create(@Valid CustomerDTO customer) {
        try {
            service.create(customer);
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            return Response.status(422).entity("{ \"msg\": \"" + e.getMessage() + "\"}").build();
        }
    }

    @GET
    public Response read() {
        List<CustomerDTO> customers = service.selectAll();
        return Response.status(Response.Status.OK).entity(customers).build();
    }

    @GET
    @Path("/{id}")
    public Response selectById(Long id) {
        try {
            CustomerDTO customer = service.selectById(id);
            return Response.status(Response.Status.OK).entity(customer).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).entity("{ \"msg\": \"Registro não encontrado\"}").build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response updateAll(Long id, @Valid CustomerDTO customer) {
        try {
            service.updateAll(id, customer);
            String msg = "{ \"msg\": \"SUCESSO\"}";
            return Response.status(Response.Status.OK).entity(msg).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).entity("{ \"msg\": \"Registro não encontrado\"}").build();
        }
    }

    @PATCH
    @Path("/{id}")
    public Response updatePartial(Long id, @Valid CustomerDTO customer) {
        service.updatePartial(id, customer);
        String msg = "{ \"msg\": \"SUCESSO\"}";
        return Response.status(Response.Status.OK).entity(msg).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(Long id) {
        try {
            service.delete(id);
            String msg = "{ \"msg\": \"SUCESSO\"}";
            return Response.status(Response.Status.NO_CONTENT).entity(msg).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).entity("{ \"msg\": \"" + e.getMessage() + "\"}").build();
        }
    }
}
