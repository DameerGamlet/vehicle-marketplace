package com.vehicle.online.controller;

import com.vehicle.online.models.Vehicle;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/vehicle")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class VehicleResource {

    @GET
    public List<Vehicle> getAllVehicles() {
        return Vehicle.listAll();
    }

    @POST
    @Transactional
    public Response addVehicle(@Valid Vehicle vehicle) {
        vehicle.persist();
        return Response.status(Response.Status.CREATED).entity(vehicle).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteVehicle(@PathParam("id") Long id) {
        boolean deleted = Vehicle.deleteById(id);

        if (!deleted) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.noContent().build();
    }

    @GET
    @Path("/search")
    public List<Vehicle> searchVehicles(
            @QueryParam("type") String type,
            @QueryParam("brand") String brand,
            @QueryParam("minPrice") Double minPrice,
            @QueryParam("maxPrice") Double maxPrice) {

        final StringBuilder query = new StringBuilder("1=1");

        if (type != null) query.append(" and type = ?1");
        if (brand != null) query.append(" and brand = ?2");
        if (minPrice != null) query.append(" and minPrice >= ?3");
        if (maxPrice != null) query.append(" and maxPrice <= ?4");

        return Vehicle.find(query.toString(), type, brand, minPrice, maxPrice).list();
    }
}
