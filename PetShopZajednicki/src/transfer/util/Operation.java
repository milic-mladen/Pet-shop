/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transfer.util;

/**
 *
 * @author User
 */
public interface Operation {

    public static final int LOGIN = 0;

    public static final int GET_ALL_ADMINISTRATOR = 1;

    public static final int ADD_KUPAC = 2;
    public static final int DELETE_KUPAC = 3;
    public static final int UPDATE_KUPAC = 4;
    public static final int GET_ALL_KUPAC = 5;
    
    public static final int GET_ALL_DOBAVLJAC = 6;
    
    public static final int GET_ALL_KATEGORIJA_PROIZVODA = 7;
    
    public static final int ADD_PORUDZBINA = 9;
    public static final int DELETE_PORUDZBINA = 10;
    public static final int UPDATE_PORUDZBINA = 11;
    public static final int GET_ALL_PORUDZBINA = 12;

    public static final int ADD_STAVKA_PORUDZBINE = 13;
    public static final int DELETE_STAVKA_PORUDZBINE = 14;
    public static final int GET_ALL_STAVKA_PORUDZBINE = 15;
    
    public static final int ADD_PROIZVOD = 16;
    public static final int DELETE_PROIZVOD = 17;
    public static final int UPDATE_PROIZVOD = 18;
    public static final int GET_ALL_PROIZVOD = 19;
    public static final int GET_ALL_PROIZVOD_2=20;
    
}
