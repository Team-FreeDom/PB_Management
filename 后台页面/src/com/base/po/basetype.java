package com.base.po;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class basetype {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    private String name;
    
    
    public basetype(int id, String name) {
	super();
	this.id = id;
	this.name = name;
    }
    
    public basetype(String name) {
	super();
	this.name = name;
    }

    public int getId() {
        return id;
    }
    public basetype() {
	super();
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
    

}
