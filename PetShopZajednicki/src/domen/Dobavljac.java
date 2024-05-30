/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class Dobavljac extends AbstractDomainObject implements Serializable {
    
    private Long dobavljacID;
    private String nazivDobavljaca;
    private String drzava;
    private String kontaktOsoba;
    private String kontakt;

    @Override
    public String toString() {
        return nazivDobavljaca;
    }

    public Dobavljac(Long dobavljacID, String nazivDobavljaca, String drzava, String kontaktOsoba, String kontakt) {
        this.dobavljacID = dobavljacID;
        this.nazivDobavljaca = nazivDobavljaca;
        this.drzava = drzava;
        this.kontaktOsoba = kontaktOsoba;
        this.kontakt = kontakt;
    }

    public Dobavljac() {
    }
    
    @Override
    public String nazivTabele() {
        return " dobavljac ";
    }

    @Override
    public String alijas() {
        return " d ";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            Dobavljac d = new Dobavljac(rs.getLong("DobavljacID"),
                    rs.getString("NazivDobavljaca"), rs.getString("Drzava"),
                    rs.getString("KontaktOsoba"), rs.getString("Kontakt"));

            lista.add(d);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return "";
    }

    @Override
    public String vrednostZaPrimarniKljuc() {
        return " DobavljacID = " + dobavljacID;
    }

    @Override
    public String vrednostiZaInsert() {
        return "";
    }

    @Override
    public String vrednostiZaUpdate() {
        return "";
    }

    @Override
    public String uslov() {
        return "";
    }

    public Long getDobavljacID() {
        return dobavljacID;
    }

    public void setDobavljacID(Long dobavljacID) {
        this.dobavljacID = dobavljacID;
    }

    public String getNazivDobavljaca() {
        return nazivDobavljaca;
    }

    public void setNazivDobavljaca(String nazivDobavljaca) {
        this.nazivDobavljaca = nazivDobavljaca;
    }

    public String getDrzava() {
        return drzava;
    }

    public void setDrzava(String drzava) {
        this.drzava = drzava;
    }

    public String getKontaktOsoba() {
        return kontaktOsoba;
    }

    public void setKontaktOsoba(String kontaktOsoba) {
        this.kontaktOsoba = kontaktOsoba;
    }

    public String getKontakt() {
        return kontakt;
    }

    public void setKontakt(String kontakt) {
        this.kontakt = kontakt;
    }
    
}
