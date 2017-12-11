/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lpro.boundary;

import java.math.BigDecimal;
import java.util.Optional;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import org.lpro.entity.Categorie;

/**
 *
 * @author vali
 */
@Stateless
@Path("categories")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CategorieRessource {
    
    @Inject
    CategorieManager cm;
    
    @GET
    public Response getCategories() {
        JsonObject json = Json.createObjectBuilder()
                .add("type", "collection")
                .add("categories", getCategorieList())
                .build();
        return Response.ok(json).build();
    }
    
    @GET
    @Path("{id}")
    public Response getOneCategorie(@PathParam("id") long id, @Context UriInfo uriInfo) {
        return Optional.ofNullable(cm.findById(id))
                .map(c -> Response.ok(categorie2Json(c)).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }
    
    private JsonObject categorie2Json(Categorie c) {
        JsonObject json = Json.createObjectBuilder()
                .add("type", "ressource")
                .add("categorie", buildJson(c))
                .build();
        return json;
    
}
    
    private JsonArray getCategorieList() {
        JsonArrayBuilder jab = Json.createArrayBuilder();
        this.cm.findAll().forEach((c) -> {
            jab.add(buildJson(c));
            
        });
        return jab.build();
    }
    
    private JsonObject buildJson(Categorie c) {
        return Json.createObjectBuilder()
                .add("id", c.getId())
                .add("nom", c.getNom())
                .add("desc", c.getDescription())
                .build();
    }
}
