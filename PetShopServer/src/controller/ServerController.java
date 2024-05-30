/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import domen.Administrator;
import domen.Dobavljac;
import domen.Kategorija;
import domen.Kupac;
import domen.Porudzbina;
import domen.Proizvod;
import domen.StavkaPorudzbine;
import java.util.ArrayList;
import so.administrator.SOGetAllAdministrator;
import so.dobavljac.SOGetAllDobavljac;
import so.kategorijaProizvoda.SOGetAllKategorijaProizvoda;
import so.kupac.SOAddKupac;
import so.kupac.SODeleteKupac;
import so.kupac.SOGetAllKupac;
import so.kupac.SOUpdateKupac;
import so.login.SOLogin;
import so.porudzbina.SOAddPorudzbina;
import so.porudzbina.SODeletePorudzbina;
import so.porudzbina.SOGetAllPorudzbina;
import so.porudzbina.SOUpdatePorudzbina;
import so.proizvod.SOAddProizvod;
import so.proizvod.SOGetAllProizvod;
import so.proizvod.SOUpdateProizvod;
import so.stavkePorudzbine.SOAddStavkaPorudzbine;
import so.stavkePorudzbine.SODeleteStavkaPorudzbine;
import so.stavkePorudzbine.SOGetAllStavkaPorudzbine;

/**
 *
 * @author User
 */
public class ServerController {

    private static ServerController instance;

    public ServerController() {
    }

    public static ServerController getInstance() {
        if (instance == null) {
            instance = new ServerController();
        }
        return instance;
    }

    public Administrator login(Administrator administrator) throws Exception {
        SOLogin so = new SOLogin();
        so.templateExecute(administrator);
        return so.getAdmin();
    }

    public void addKupac(Kupac kupac) throws Exception {
        (new SOAddKupac()).templateExecute(kupac);
    }

    public void addProizvod(Proizvod proizvod) throws Exception {
        (new SOAddProizvod()).templateExecute(proizvod);
    }

    public void addPorudzbina(Porudzbina porudzbina) throws Exception {
        (new SOAddPorudzbina()).templateExecute(porudzbina);
    }

    public void addStavkaPorudzbine(StavkaPorudzbine stavkaPorudzbine) throws Exception {
        (new SOAddStavkaPorudzbine()).templateExecute(stavkaPorudzbine);
    }

    public void deleteKupac(Kupac kupac) throws Exception {
        (new SODeleteKupac()).templateExecute(kupac);
    }

    public void deletePorudzbina(Porudzbina porudzbina) throws Exception {
        (new SODeletePorudzbina()).templateExecute(porudzbina);
    }

    public void deleteStavkaPorudzbine(StavkaPorudzbine stavkaPorudzbine) throws Exception {
        (new SODeleteStavkaPorudzbine()).templateExecute(stavkaPorudzbine);
    }

    public void updateKupac(Kupac kupac) throws Exception {
        (new SOUpdateKupac()).templateExecute(kupac);
    }
 public void updateProizvod(Proizvod proizvod) throws Exception {
        (new SOUpdateProizvod()).templateExecute(proizvod);
    }
    public void updatePorudzbina(Porudzbina porudzbina) throws Exception {
        (new SOUpdatePorudzbina()).templateExecute(porudzbina);
    }

    public ArrayList<Administrator> getAllAdministrator() throws Exception {
        SOGetAllAdministrator so = new SOGetAllAdministrator();
        so.templateExecute(new Administrator());
        return so.getLista();
    }

    public ArrayList<Kupac> getAllKupac() throws Exception {
        SOGetAllKupac so = new SOGetAllKupac();
        so.templateExecute(new Kupac());
        return so.getLista();
    }

    public ArrayList<Porudzbina> getAllPorudzbina() throws Exception {
        SOGetAllPorudzbina so = new SOGetAllPorudzbina();
        so.templateExecute(new Porudzbina());
        return so.getLista();
    }

    public ArrayList<StavkaPorudzbine> getAllStavkaPorudzbine(Porudzbina p) throws Exception {
        SOGetAllStavkaPorudzbine so = new SOGetAllStavkaPorudzbine();

        StavkaPorudzbine sp = new StavkaPorudzbine();
        sp.setPorudzbina(p);

        so.templateExecute(sp);
        return so.getLista();
    }

    public ArrayList<Proizvod> getAllProizvod(Kategorija k) throws Exception {
        SOGetAllProizvod so = new SOGetAllProizvod();

        Proizvod p = new Proizvod();
        if (k != null) {
            p.setKategorija(k);
        }
        so.templateExecute(p);
        return so.getLista();
    }

    public ArrayList<Kategorija> getAllKategorija() throws Exception {
        SOGetAllKategorijaProizvoda so = new SOGetAllKategorijaProizvoda();
        so.templateExecute(new Kategorija());
        return so.getLista();
    }

    public ArrayList<Dobavljac> getAllDobavljac() throws Exception {
        SOGetAllDobavljac so = new SOGetAllDobavljac();
        so.templateExecute(new Dobavljac());
        return so.getLista();
    }

}
