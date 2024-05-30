/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import controller.ClientController;
import domen.Proizvod;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author User
 */
public class TableModelProizvodi extends AbstractTableModel implements Runnable {

    private ArrayList<Proizvod> lista;
    private String[] kolone = {"ID", "Naziv", "Opis", "Cena", "Kategorija", "Dobavljac"};
    private String parametar = "";

    public TableModelProizvodi() {
        try {
            lista = ClientController.getInstance().getAllProizvod(null);
        } catch (Exception ex) {
            Logger.getLogger(TableModelProizvodi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public String getColumnName(int i) {
        return kolone[i];
    }

    @Override
    public Object getValueAt(int row, int column) {
        Proizvod p = lista.get(row);

        switch (column) {
            case 0:
                return p.getProizvodID();
            case 1:
                return p.getNazivProizvoda();
            case 2:
                return p.getOpis();
            case 3:
                return p.getCena();
            case 4:
                return p.getKategorija();
            case 5:
                return p.getDobavljac();

            default:
                return null;
        }
    }

    public Proizvod getSelectedProizvod(int row) {
        return lista.get(row);
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                Thread.sleep(10000);
                refreshTable();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(TableModelProizvodi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setParametar(String parametar) {
        this.parametar = parametar;
        refreshTable();
    }

    public void refreshTable() {
        try {
            lista = ClientController.getInstance().getAllProizvod(null);
            if (!parametar.equals("")) {
                ArrayList<Proizvod> novaLista = new ArrayList<>();
                for (Proizvod p : lista) {
                    if (p.getNazivProizvoda().toLowerCase().contains(parametar.toLowerCase())
                            ) {
                        novaLista.add(p);
                    }
                }
                lista = novaLista;
            }

            fireTableDataChanged();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
