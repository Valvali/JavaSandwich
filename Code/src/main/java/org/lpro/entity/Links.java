/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lpro.entity;


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Links implements java.io.Serializable {

    @Id
    private String id;
    private String href;
    @ManyToMany(mappedBy = "links")
    private Set<Sandwich> sandwich = new HashSet<Sandwich>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public Set<Sandwich> getSandwich() {
        return sandwich;
    }

    public void setSandwich(Set<Sandwich> sandwich) {
        this.sandwich = sandwich;
    }

    public Links() {
    }
    
}

