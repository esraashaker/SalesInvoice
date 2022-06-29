
package SalesIvnvoice.model;

import java.util.ArrayList;

public class InvoiceHeader {
    private int id;
    private String customerName;
    private String date;
    private double total;

    private ArrayList<InvoiceLine> lines;
    
    public InvoiceHeader(int id, String customerName, String date,double total) {
        this.id = id;
        this.customerName = customerName;
        this.date = date;
        this.total=total;
    }

    public InvoiceHeader(int id, String part, String part0) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }
      public double getTotal() {
        return total;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public double getInvoiceTotal() {
        double total = 0;
        for (InvoiceLine line : getLines()) {
            total += line.getLineTotal();
        }
        return total;
    }

    @Override
    public String toString() {
        return "InvoiceHeader{" + "id=" + id + ", customerName=" + customerName + ", date=" + date + '}';
    }

    public ArrayList<InvoiceLine> getLines() {
        if (lines == null) {
            lines = new ArrayList<>();
        }
        return lines;
    }

  
    
    
    
    
}
