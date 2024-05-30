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
public class Kupac extends AbstractDomainObject implements Serializable {
    
    private Long kupacID;
    private String imeKupca;
    private String prezimeKupca;
    private String kontaktTelefon;
    private String adresa;
    private String status;

    @Override
    public String toString() {
        return imeKupca + " " + prezimeKupca;
    }

    public Kupac(Long kupacID, String imeKupca, String prezimeKupca, String kontaktTelefon, String adresa, String status) {
        this.kupacID = kupacID;
        this.imeKupca = imeKupca;
        this.prezimeKupca = prezimeKupca;
        this.kontaktTelefon = kontaktTelefon;
        this.adresa = adresa;
        this.status = status;
    }

    public Kupac() {
    }
    
    @Override
    public String nazivTabele() {
        return " kupac ";
    }

    @Override
    public String alijas() {
        return " k ";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            Kupac k = new Kupac(rs.getLong("KupacID"),
                    rs.getString("ImeKupca"), rs.getString("PrezimeKupca"),
                    rs.getString("KontaktTelefon"), rs.getString("Adresa"), rs.getString("Status"));

            lista.add(k);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (ImeKupca, PrezimeKupca, KontaktTelefon, Adresa, Status) ";
    }

    @Override
    public String vrednostZaPrimarniKljuc() {
        return " KupacID = " + kupacID;
    }

    @Override
    public String vrednostiZaInsert() {
        return "'" + imeKupca + "', '" + prezimeKupca + "', "
                + "'" + kontaktTelefon + "', '" + adresa + "', '" + status + "' ";
    }

    @Override
    public String vrednostiZaUpdate() {
        return " kontaktTelefon = '" + kontaktTelefon + "', adresa = '" + adresa + "', "
                + "status = '" + status + "' ";
    }

    @Override
    public String uslov() {
        return "";
    }

    public Long getKupacID() {
        return kupacID;
    }

    public void setKupacID(Long kupacID) {
        this.kupacID = kupacID;
    }

    public String getImeKupca() {
        return imeKupca;
    }

    public void setImeKupca(String imeKupca) {
        this.imeKupca = imeKupca;
    }

    public String getPrezimeKupca() {
        return prezimeKupca;
    }

    public void setPrezimeKupca(String prezimeKupca) {
        this.prezimeKupca = prezimeKupca;
    }

    public String getKontaktTelefon() {
        return kontaktTelefon;
    }

    public void setKontaktTelefon(String kontaktTelefon) {
        this.kontaktTelefon = kontaktTelefon;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
}
