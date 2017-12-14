/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lpro.boundary;

import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import org.lpro.entity.Sandwich;


@Path("/sandwichs")
public class SandwichResource {
   
    @Inject
    SandwichManager sm;
@GET
@Produces("application/json")
public Response getSandwichs(@QueryParam("type") String ptype) {
 GenericEntity<List<Sandwich>> liste = new GenericEntity<List<Sandwich>>(this.sm.findAll()) {
        };
        return Response.ok(liste).build();
}
}
