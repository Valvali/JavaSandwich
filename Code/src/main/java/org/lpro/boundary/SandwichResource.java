/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lpro.boundary;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.Valid;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import org.lpro.entity.Sandwich;
import org.lpro.entity.Categorie;


@Path("/sandwichs")
public class SandwichResource {
   @Inject
    CategorieManager cm;
   
    @Inject
    SandwichManager sm;
@GET
@Produces("application/json")
public Response getSandwichs(@QueryParam("type") String ptype) {
 GenericEntity<List<Sandwich>> liste = new GenericEntity<List<Sandwich>>(this.sm.findAll()) {
        };
        return Response.ok(liste).build();
    }

    @GET
    @Path("{id}/categories")
    public Response getCategories(@PathParam("id") long id, @Context UriInfo uriInfo) {
        return Optional.ofNullable(cm.findById(id))
                //.map(c -> Response.ok(categorie2Json(c)).build())
                .map(c -> Response.ok(c).build())
                //.orElseThrow(() -> new CategorieNotFound("Ressource non disponible "+ uriInfo.getPath()));
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }
    
    @GET
    @Path("{id}")
    public Response getOneSandwich(@PathParam("id") long id, @Context UriInfo uriInfo) {
        return Optional.ofNullable(sm.findById(id))
                //.map(c -> Response.ok(categorie2Json(c)).build())
                .map(s -> Response.ok(s).build())
                //.orElseThrow(() -> new CategorieNotFound("Ressource non disponible "+ uriInfo.getPath()));
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }
    
    @DELETE
    @Path("{id}")
    public Response suppression(@PathParam("id") long id) {
        this.sm.delete(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
    
    
    @POST
    @ApiOperation(value = "Crée un sandwich", notes = "Crée une sandwich partir du JSON fourni")
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "Created"),
        @ApiResponse(code = 500, message = "Internal server error")})
    public Response newSandwich(@Valid Sandwich s, @Context UriInfo uriInfo) {
        Sandwich newOne = this.sm.save(s);
        long id = newOne.getId();
        URI uri = uriInfo.getAbsolutePathBuilder().path("/"+id).build();
        return Response.created(uri).build();
    }
  
  
    
    

}
