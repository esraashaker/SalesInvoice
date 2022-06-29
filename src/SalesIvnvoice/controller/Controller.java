/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SalesIvnvoice.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JFileChooser;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import SalesIvnvoice.model.InvoiceHeader;
import SalesIvnvoice.model.InvoiceLine;
import SalesIvnvoice.model.InvoiceLineTableModel;
import SalesIvnvoice.view.SalesFrame;
import java.awt.Component;
import javax.swing.JOptionPane;

/**
 *
 * @author DELL
 */
public class Controller implements ActionListener, ListSelectionListener {

    private final SalesFrame frame;

    public Controller(SalesFrame frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        switch (actionCommand) {
            case "Load Files":
                loadFiles();
                break;
            case "Save Files":
                saveFiles();
                break;
            case "Create New Invoice":
                createNewInvoice();
                break;
            case "Delete Invoice":
                deleteInvoice();
                break;
            case "Create New Line":
                createNewLine();
                break;
            case "Delete Line":
                deleteLine();
                break;
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        System.out.println("Row Selected");
        int selectedRow = frame.getInvHeaderTable().getSelectedRow();
        System.out.println(selectedRow);
        ArrayList<InvoiceLine> lines = frame.getInvoiceHeadersList().get(selectedRow).getLines();
        frame.getInvLineTable().setModel(new InvoiceLineTableModel(lines));
    }

    private void loadFiles() {
        try {
            JFileChooser fc = new JFileChooser();
            int result = fc.showOpenDialog(frame);
            if (result == JFileChooser.APPROVE_OPTION) {
                File headerFile = fc.getSelectedFile();
                String headerStrPath = headerFile.getAbsolutePath();
                Path headerPath = Paths.get(headerStrPath);
                List<String> headerLines = Files.lines(headerPath).collect(Collectors.toList());
                // ["1,22-11-2020,Ali", "2,13-10-2021,Saleh"]
                ArrayList<InvoiceHeader> invoiceHeadersList = new ArrayList<>();
                for (String headerLine : headerLines) {
                    String[] parts = headerLine.split(",");
                    // parts = ["1", "22-11-2020", "Ali"]
                    // parts = ["2", "13-10-2021", "Saleh"]
                    int id = Integer.parseInt(parts[0]);
                    InvoiceHeader invHeader = new InvoiceHeader(id, parts[2], parts[1]);
                    invoiceHeadersList.add(invHeader);
                }
                System.out.println("check");
                result = fc.showOpenDialog(frame);
                if (result == JFileChooser.APPROVE_OPTION) {
                    String lineStrPath = fc.getSelectedFile().getAbsolutePath();
                    Path linePath = Paths.get(lineStrPath);
                    List<String> lineLines = Files.lines(linePath).collect(Collectors.toList());
                    // ["1,Mobile,3200,1", "1,Cover,20,2", "1,Headphone,130,1", "2,Laptop,4000,1", "2,Mouse,35,1"]
                    for (String lineLine : lineLines) {
                        String[] parts = lineLine.split(",");
                        // ["1","Mobile","3200","1"]
                        // ["1","Cover","20","2"]
                        // ["1","Headphone","130","1"]
                        // ["2","Laptop","4000","1"]
                        // ["2","Mouse","35","1"]
                        int invId = Integer.parseInt(parts[0]);
                        double price = Double.parseDouble(parts[2]);
                        int count = Integer.parseInt(parts[3]);
                        InvoiceHeader header = getInvoiceHeaderById(invoiceHeadersList, invId);
                        InvoiceLine invLine = new InvoiceLine(parts[1], price, count, header);
                        header.getLines().add(invLine);
                    }
                    frame.setInvoiceHeadersList(invoiceHeadersList);
                    
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private InvoiceHeader getInvoiceHeaderById(ArrayList<InvoiceHeader> invoices, int id) {
        for (InvoiceHeader invoice : invoices) {
            if (invoice.getId() == id) {
                return invoice;
            }
        }
        
        return null;
    }
    
    private void createNewInvoice() {
          int index = list.getSelectedIndex();
         listModel.remove(index);

    int size = listModel.getSize();

    if (size == 0) { //Nobody's left, disable firing.
        fireButton.setEnabled(false);

    } else { //Select an index.
        if (index == listModel.getSize()) {
            //removed item in last position
            index--;
        }

        list.setSelectedIndex(index);
        list.ensureIndexIsVisible(index);
    }
        
    }
    

    private void deleteInvoice() {
        Component nul = null;
        int action = JOptionPane.showConfirmDialog(nul,"do you relay want to delete","Delete",JOptionPane.YES_NO_OPTION);
        JOptionPane.showMessageDialog(nul, "data deleted");
        refreshTable();
    }
    
  
    

    private void saveFiles() {
           try {
            JFileChooser fc = new JFileChooser();
            int result = fc.showSaveDialog(frame);
            if (result == JFileChooser.APPROVE_OPTION) {
                File headerFile = fc.getSelectedFile();
                String headerStrPath = headerFile.getAbsolutePath();
                Path headerPath = Paths.get(headerStrPath);
                List<String> headerLines = Files.lines(headerPath).collect(Collectors.toList());
                // ["1,22-11-2020,Ali", "2,13-10-2021,Saleh"]
                ArrayList<InvoiceHeader> invoiceHeadersList = new ArrayList<>();
                for (String headerLine : headerLines) {
                    String[] parts = headerLine.split(",");
                    // parts = ["1", "22-11-2020", "Ali"]
                    // parts = ["2", "13-10-2021", "Saleh"]
                    int id = Integer.parseInt(parts[0]);
                    InvoiceHeader invHeader = new InvoiceHeader(id, parts[2], parts[1]);
                    invoiceHeadersList.add(invHeader);
                }
                System.out.println("check");
                result = fc.showSaveDialog(frame);
                if (result == JFileChooser.APPROVE_OPTION) {
                    String lineStrPath = fc.getSelectedFile().getAbsolutePath();
                    Path linePath = Paths.get(lineStrPath);
                    List<String> lineLines = Files.lines(linePath).collect(Collectors.toList());
                    // ["1,Mobile,3200,1", "1,Cover,20,2", "1,Headphone,130,1", "2,Laptop,4000,1", "2,Mouse,35,1"]
                    for (String lineLine : lineLines) {
                        String[] parts = lineLine.split(",");
                        // ["1","Mobile","3200","1"]
                        // ["1","Cover","20","2"]
                        // ["1","Headphone","130","1"]
                        // ["2","Laptop","4000","1"]
                        // ["2","Mouse","35","1"]
                        int invId = Integer.parseInt(parts[0]);
                        double price = Double.parseDouble(parts[2]);
                        int count = Integer.parseInt(parts[3]);
                        InvoiceHeader header = getInvoiceHeaderById(invoiceHeadersList, invId);
                        InvoiceLine invLine = new InvoiceLine(parts[1], price, count, header);
                        header.getLines().add(invLine);
                    }
                    frame.setInvoiceHeadersList(invoiceHeadersList);
                    
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
          }
    

    private void createNewLine() {
         int index = list.getSelectedIndex();
         listModel.remove(index);

    int size = listModel.getSize();

    if (size == 0) { //Nobody's left, disable firing.
        fireButton.setEnabled(false);

    } else { //Select an index.
        if (index == listModel.getSize()) {
            //removed item in last position
            index--;
        }

        list.setSelectedIndex(index);
        list.ensureIndexIsVisible(index);
    }
        
    }

    private void deleteLine() {
         Component nul = null;
        JOptionPane.showMessageDialog(nul, "data deleted");
        refreshTable();
    }

    private void refreshTable() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private static class listModel {

        private static int getSize() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        private static void remove(int index) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }


        public listModel() {
        }
    }

    private static class fireButton {

        private static void setEnabled(boolean b) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }


        public fireButton() {
        }
    }

    private static class list {

        private static int getSelectedIndex() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        private static void ensureIndexIsVisible(int index) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        private static void setSelectedIndex(int index) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }



        public list() {
        }
    }

   
    }

   
    

    
    


