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
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import session.Session;
import transfer.Request;
import transfer.Response;
import transfer.util.ResponseStatus;
import transfer.util.Operation;

/**
 *
 * @author User
 */
public class ClientController {

    private static ClientController instance;

    public ClientController() {
    }

    public static ClientController getInstance() {
        if (instance == null) {
            instance = new ClientController();
        }
        return instance;
    }

    public Administrator login(Administrator administrator) throws Exception {
        return (Administrator) sendRequest(Operation.LOGIN, administrator);
    }

    public void addKupac(Kupac kupac) throws Exception {
        sendRequest(Operation.ADD_KUPAC, kupac);
    }

    public void addProizvod(Proizvod proizvod) throws Exception {
        sendRequest(Operation.ADD_PROIZVOD, proizvod);
    }

    public void addPorudzbina(Porudzbina porudzbina) throws Exception {
        sendRequest(Operation.ADD_PORUDZBINA, porudzbina);
    }

    public void addStavkaPorudzbine(StavkaPorudzbine stavkaPorudzbine) throws Exception {
        sendRequest(Operation.ADD_STAVKA_PORUDZBINE, stavkaPorudzbine);
    }

    public void deleteKupac(Kupac kupac) throws Exception {
        sendRequest(Operation.DELETE_KUPAC, kupac);
    }

    public void deletePorudzbina(Porudzbina porudzbina) throws Exception {
        sendRequest(Operation.DELETE_PORUDZBINA, porudzbina);
    }

    public void deleteStavkaPorudzbine(StavkaPorudzbine stavkaPorudzbine) throws Exception {
        sendRequest(Operation.DELETE_STAVKA_PORUDZBINE, stavkaPorudzbine);
    }

    public void updateKupac(Kupac kupac) throws Exception {
        sendRequest(Operation.UPDATE_KUPAC, kupac);
    }
public void updateProizvod(Proizvod proizvod) throws Exception {
        sendRequest(Operation.UPDATE_PROIZVOD, proizvod);
    }
    public void updatePorudzbina(Porudzbina porudzbina) throws Exception {
        sendRequest(Operation.UPDATE_PORUDZBINA, porudzbina);
    }

    public ArrayList<Administrator> getAllAdministrator() throws Exception {
        return (ArrayList<Administrator>) sendRequest(Operation.GET_ALL_ADMINISTRATOR, null);
    }

    public ArrayList<Kupac> getAllKupac() throws Exception {
        return (ArrayList<Kupac>) sendRequest(Operation.GET_ALL_KUPAC, null);
    }

    public ArrayList<Porudzbina> getAllPorudzbina() throws Exception {
        return (ArrayList<Porudzbina>) sendRequest(Operation.GET_ALL_PORUDZBINA, null);
    }

    public ArrayList<StavkaPorudzbine> getAllStavkaPorudzbine(Porudzbina p) throws Exception {
        return (ArrayList<StavkaPorudzbine>) sendRequest(Operation.GET_ALL_STAVKA_PORUDZBINE, p);
    }

    public ArrayList<Dobavljac> getAllDobavljac() throws Exception {
        return (ArrayList<Dobavljac>) sendRequest(Operation.GET_ALL_DOBAVLJAC, null);
    }

    public ArrayList<Kategorija> getAllKategorijaProizvoda() throws Exception {
        return (ArrayList<Kategorija>) sendRequest(Operation.GET_ALL_KATEGORIJA_PROIZVODA, null);
    }

    public ArrayList<Proizvod> getAllProizvod(Kategorija k) throws Exception {
        return (ArrayList<Proizvod>) sendRequest(Operation.GET_ALL_PROIZVOD, k);
    }

   

    private Object sendRequest(int operation, Object data) throws Exception {
        Request request = new Request(operation, data);
        ObjectOutputStream out = new ObjectOutputStream(Session.getInstance().getSocket().getOutputStream());
        out.writeObject(request);
        ObjectInputStream in = new ObjectInputStream(Session.getInstance().getSocket().getInputStream());
        Response response = (Response) in.readObject();
        if (response.getResponseStatus().equals(ResponseStatus.Error)) {
            throw response.getError();
        } else {
            return response.getData();
        }
    }
}
