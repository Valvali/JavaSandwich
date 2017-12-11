/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lpro.boundary;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.GET;
import static javax.ws.rs.HttpMethod.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Path("/sandwichs")
public class SandwichResource {
    @PersistenceContext
    EntityManager em;
@GET
@Produces("application/json")
public Response getSandwichs(@QueryParam("type") String ptype) {
// ptype contient "baguette"
Query query = em.createQuery("Select * from sandwich where type_pain ='"+ptype + "';" );
Response r = query.getResultList().map().build();
return r;
}
}
