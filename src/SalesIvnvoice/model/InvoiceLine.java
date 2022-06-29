
package SalesIvnvoice.model;

public class InvoiceLine {
        private int id;
    private String itemName;
    private double unitPrice;
    private int count;
    private InvoiceHeader header;

    public InvoiceLine(int id,String itemName, double unitPrice, int count, InvoiceHeader header) {
        this.itemName = itemName;
        this.unitPrice = unitPrice;
        this.count = count;
        this.header = header;
        this.id=id;
    }

    public InvoiceLine(String part, double price, int count, InvoiceHeader header) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

  

    public InvoiceHeader getHeader() {
        return header;
    }

    public void setId(int id) {
        this.id = id;
    }
    

    public void setHeader(InvoiceHeader header) {
        this.header = header;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getLineTotal() {
        return count * unitPrice;
    }

    @Override
    public String toString() {
        return "InvoiceLine{" + "itemName=" + itemName + ", unitPrice=" + unitPrice + ", count=" + count +", id="+id+ ", lineTotal=" + getLineTotal() + '}';
    }

    public int getId() {
        return id;
    }

    
    
    
    
}
