/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author User
 */
public class Porudzbina extends AbstractDomainObject implements Serializable {

    private Long porudzbinaID;
    private Date datumVreme;
    private Date datumDostave;
    private double iznosBezPopusta;
    private double popust;
    private double iznosSaPopustom;
    private Kupac kupac;
    private Administrator administrator;
    private ArrayList<StavkaPorudzbine> stavkePorudzbine;

    public Porudzbina(Long porudzbinaID, Date datumVreme, Date datumDostave, double iznosBezPopusta, double popust, double iznosSaPopustom, Kupac kupac, Administrator administrator, ArrayList<StavkaPorudzbine> stavkePorudzbine) {
        this.porudzbinaID = porudzbinaID;
        this.datumVreme = datumVreme;
        this.datumDostave = datumDostave;
        this.iznosBezPopusta = iznosBezPopusta;
        this.popust = popust;
        this.iznosSaPopustom = iznosSaPopustom;
        this.kupac = kupac;
        this.administrator = administrator;
        this.stavkePorudzbine = stavkePorudzbine;
    }

    public Porudzbina() {
    }

    @Override
    public String nazivTabele() {
        return " porudzbina ";
    }

    @Override
    public String alijas() {
        return " p ";
    }

    @Override
    public String join() {
        return " JOIN kupac k ON (k.kupacid = p.kupacid) "
                + "JOIN administrator a ON (a.administratorid = p.administratorid) ";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            Administrator a = new Administrator(rs.getLong("AdministratorID"),
                    rs.getString("Ime"), rs.getString("Prezime"),
                    rs.getString("Username"), rs.getString("Password"));
            
            Kupac k = new Kupac(rs.getLong("KupacID"),
                    rs.getString("ImeKupca"), rs.getString("PrezimeKupca"),
                    rs.getString("KontaktTelefon"), rs.getString("Adresa"), rs.getString("Status"));
            
            Porudzbina p = new Porudzbina(rs.getLong("porudzbinaID"), 
                    rs.getTimestamp("datumVreme"), rs.getDate("datumDostave"), 
                    rs.getDouble("iznosBezPopusta"), rs.getDouble("popust"), 
                    rs.getDouble("iznosSaPopustom"), k, a, null);

            lista.add(p);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (datumVreme, datumDostave, iznosBezPopusta, popust, iznosSaPopustom, kupacID, administratorID) ";
    }

    @Override
    public String vrednostZaPrimarniKljuc() {
        return " porudzbinaID = " + porudzbinaID;
    }

    @Override
    public String vrednostiZaInsert() {
        return "'" + new Timestamp(datumVreme.getTime()) + "', "
                + "'" + new java.sql.Date(datumDostave.getTime()) + "', "
                + iznosBezPopusta + ", " + popust + ", " + iznosSaPopustom + ", "
                + kupac.getKupacID() + ", " + administrator.getAdministratorID();
    }

    @Override
    public String vrednostiZaUpdate() {
        return " datumDostave = '" + new Timestamp(datumDostave.getTime())+ "', "
                + "iznosBezPopusta = " + iznosBezPopusta + ", "
                + "popust = " + popust + ", iznosSaPopustom = " + iznosSaPopustom + " ";
    }

    @Override
    public String uslov() {
        return "";
    }

    public Long getPorudzbinaID() {
        return porudzbinaID;
    }

    public void setPorudzbinaID(Long porudzbinaID) {
        this.porudzbinaID = porudzbinaID;
    }

    public Date getDatumVreme() {
        return datumVreme;
    }

    public void setDatumVreme(Date datumVreme) {
        this.datumVreme = datumVreme;
    }

    public Date getDatumDostave() {
        return datumDostave;
    }

    public void setDatumDostave(Date datumDostave) {
        this.datumDostave = datumDostave;
    }

    public double getIznosBezPopusta() {
        return iznosBezPopusta;
    }

    public void setIznosBezPopusta(double iznosBezPopusta) {
        this.iznosBezPopusta = iznosBezPopusta;
    }

    public double getPopust() {
        return popust;
    }

    public void setPopust(double popust) {
        this.popust = popust;
    }

    public double getIznosSaPopustom() {
        return iznosSaPopustom;
    }

    public void setIznosSaPopustom(double iznosSaPopustom) {
        this.iznosSaPopustom = iznosSaPopustom;
    }

    public Kupac getKupac() {
        return kupac;
    }

    public void setKupac(Kupac kupac) {
        this.kupac = kupac;
    }

    public Administrator getAdministrator() {
        return administrator;
    }

    public void setAdministrator(Administrator administrator) {
        this.administrator = administrator;
    }

    public ArrayList<StavkaPorudzbine> getStavkePorudzbine() {
        return stavkePorudzbine;
    }

    public void setStavkePorudzbine(ArrayList<StavkaPorudzbine> stavkePorudzbine) {
        this.stavkePorudzbine = stavkePorudzbine;
    }

}
