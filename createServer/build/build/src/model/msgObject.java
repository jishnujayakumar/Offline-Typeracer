/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 

*/
package model;

import java.io.Serializable;

public class msgObject implements Serializable {
    
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
String name ;
double countword;
public  msgObject(String s,double v  ){
this.countword=v;
this.name=s;
}


}
