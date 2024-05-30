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
public class Proizvod extends AbstractDomainObject implements Serializable {

    private Long proizvodID;
    private String nazivProizvoda;
    private String opis;
    private double cena;
    private Kategorija kategorija;
    private Dobavljac dobavljac;

    @Override
    public String toString() {
        return nazivProizvoda + " (Cena: " + cena + "din)";
    }

    public Proizvod(Long proizvodID, String nazivProizvoda, String opis, double cena, Kategorija kategorija, Dobavljac dobavljac) {
        this.proizvodID = proizvodID;
        this.nazivProizvoda = nazivProizvoda;
        this.opis = opis;
        this.cena = cena;
        this.kategorija = kategorija;
        this.dobavljac = dobavljac;
    }

    public Proizvod() {
    }

    @Override
    public String nazivTabele() {
        return " proizvod ";
    }

    @Override
    public String alijas() {
        return " p ";
    }

    @Override
    public String join() {
        return " JOIN dobavljac d ON (d.dobavljacid = p.dobavljacid) "
                + "JOIN kategorijaproizvoda kp ON (kp.kategorijaid = p.kategorijaid) ";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            Dobavljac d = new Dobavljac(rs.getLong("DobavljacID"),
                    rs.getString("NazivDobavljaca"), rs.getString("Drzava"),
                    rs.getString("KontaktOsoba"), rs.getString("Kontakt"));

            Kategorija k = new Kategorija(rs.getLong("KategorijaID"),
                    rs.getString("NazivKategorije"));

            Proizvod p = new Proizvod(rs.getLong("proizvodID"), rs.getString("nazivProizvoda"),
                    rs.getString("opis"), rs.getDouble("cena"), k, d);

            lista.add(p);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (nazivProizvoda, opis, cena, KategorijaID, DobavljacID) ";
    }

    @Override
    public String vrednostZaPrimarniKljuc() {
        return " proizvodID = " + proizvodID;
    }

    @Override
    public String vrednostiZaInsert() {
        return "'" + nazivProizvoda + "', '" + opis + "', "
                + cena + ", " + kategorija.getKategorijaID() + ", " + dobavljac.getDobavljacID();
    }

    @Override
    public String vrednostiZaUpdate() {
        return " Opis = '" + opis + "', Cena = " + cena + " "+", KategorijaID= "+kategorija.getKategorijaID()+", DobavljacID= "+dobavljac.getDobavljacID();
    }

    @Override
    public String uslov() {
        if (kategorija == null) {
            return "";
        }
        return " WHERE KP.KATEGORIJAID = " + kategorija.getKategorijaID();
    }

    public Long getProizvodID() {
        return proizvodID;
    }

    public void setProizvodID(Long proizvodID) {
        this.proizvodID = proizvodID;
    }

    public String getNazivProizvoda() {
        return nazivProizvoda;
    }

    public void setNazivProizvoda(String nazivProizvoda) {
        this.nazivProizvoda = nazivProizvoda;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public Kategorija getKategorija() {
        return kategorija;
    }

    public void setKategorija(Kategorija kategorija) {
        this.kategorija = kategorija;
    }

    public Dobavljac getDobavljac() {
        return dobavljac;
    }

    public void setDobavljac(Dobavljac dobavljac) {
        this.dobavljac = dobavljac;
    }
}
