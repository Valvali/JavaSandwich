/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lpro.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

/**
 *
 * @author vali
 */
@Entity
@NamedQuery(name="Sandwich.findAll",query="SELECT c FROM Categorie c")
public class Sandwich {
    
    
    @Id
    @GeneratedValue
    private long id;
    
}
