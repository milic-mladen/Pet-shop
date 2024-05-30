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
public class StavkaPorudzbine extends AbstractDomainObject implements Serializable {

    private Porudzbina porudzbina;
    private int rbStavke;
    private int kolicina;
    private double cenaStavke;
    private Proizvod proizvod;

    public StavkaPorudzbine(Porudzbina porudzbina, int rbStavke, int kolicina, double cenaStavke, Proizvod proizvod) {
        this.porudzbina = porudzbina;
        this.rbStavke = rbStavke;
        this.kolicina = kolicina;
        this.cenaStavke = cenaStavke;
        this.proizvod = proizvod;
    }

    public StavkaPorudzbine() {
    }

    @Override
    public String nazivTabele() {
        return " stavkaPorudzbine ";
    }

    @Override
    public String alijas() {
        return " sp ";
    }

    @Override
    public String join() {
        return " JOIN porudzbina p USING (porudzbinaid) "
                + "JOIN proizvod pr USING (proizvodID) "
                + "JOIN dobavljac d ON (d.dobavljacid = pr.dobavljacid) "
                + "JOIN kategorijaproizvoda kp ON (kp.kategorijaid = pr.kategorijaid) "
                + "JOIN kupac k ON (k.kupacid = p.kupacid) "
                + "JOIN administrator a ON (a.administratorid = p.administratorid) ";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            Administrator a = new Administrator(rs.getLong("AdministratorID"),
                    rs.getString("Ime"), rs.getString("Prezime"),
                    rs.getString("Username"), rs.getString("Password"));
            
            Dobavljac d = new Dobavljac(rs.getLong("DobavljacID"),
                    rs.getString("NazivDobavljaca"), rs.getString("Drzava"),
                    rs.getString("KontaktOsoba"), rs.getString("Kontakt"));
            
            Kategorija k = new Kategorija(rs.getLong("KategorijaID"),
                    rs.getString("NazivKategorije"));
            
            Proizvod pr = new Proizvod(rs.getLong("proizvodID"), rs.getString("nazivProizvoda"), 
                    rs.getString("opis"), rs.getDouble("cena"), k, d);
            
            Kupac kup = new Kupac(rs.getLong("KupacID"),
                    rs.getString("ImeKupca"), rs.getString("PrezimeKupca"),
                    rs.getString("KontaktTelefon"), rs.getString("Adresa"), rs.getString("Status"));
            
            Porudzbina p = new Porudzbina(rs.getLong("porudzbinaID"), 
                    rs.getTimestamp("datumVreme"), rs.getDate("datumDostave"), 
                    rs.getDouble("iznosBezPopusta"), rs.getDouble("popust"), 
                    rs.getDouble("iznosSaPopustom"), kup, a, null);
            
            StavkaPorudzbine sp = new StavkaPorudzbine(p, rs.getInt("rbStavke"), 
                    rs.getInt("kolicina"), rs.getDouble("cenaStavke"), pr);

            lista.add(sp);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (porudzbinaID, rbStavke, kolicina, cenaStavke, proizvodID) ";
    }

    @Override
    public String vrednostZaPrimarniKljuc() {
        return " porudzbinaID = " + porudzbina.getPorudzbinaID();
    }

    @Override
    public String vrednostiZaInsert() {
        return porudzbina.getPorudzbinaID() + ", " + rbStavke + ", "
                + kolicina + ", " + cenaStavke + ", " + proizvod.getProizvodID();
    }

    @Override
    public String vrednostiZaUpdate() {
        return "";
    }

    @Override
    public String uslov() {
        return " WHERE P.PORUDZBINAID = " + porudzbina.getPorudzbinaID();
    }

    public Porudzbina getPorudzbina() {
        return porudzbina;
    }

    public void setPorudzbina(Porudzbina porudzbina) {
        this.porudzbina = porudzbina;
    }

    public int getRbStavke() {
        return rbStavke;
    }

    public void setRbStavke(int rbStavke) {
        this.rbStavke = rbStavke;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

    public double getCenaStavke() {
        return cenaStavke;
    }

    public void setCenaStavke(double cenaStavke) {
        this.cenaStavke = cenaStavke;
    }

    public Proizvod getProizvod() {
        return proizvod;
    }

    public void setProizvod(Proizvod proizvod) {
        this.proizvod = proizvod;
    }

}
