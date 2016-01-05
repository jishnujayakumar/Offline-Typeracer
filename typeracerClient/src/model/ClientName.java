/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.io.Serializable;

/**
 *
 * @author Jishnu
 */
public class ClientName implements Serializable{
    
    private String name;
    
    public ClientName(String NAME){
        
        this.name = NAME;
        
    }
    
    public void setName(String NAME){
    
        this.name = NAME;
    
    }
    
    public String getName(){
    
        return this.name;
    
    }
    
}
