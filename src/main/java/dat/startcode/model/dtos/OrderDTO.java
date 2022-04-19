package dat.startcode.model.dtos;

import java.util.List;

public class OrderDTO {
    private int orderID;
    private int totalPrice;
    List<LineDTO> cupcakeLines;

    public OrderDTO(int orderID, int totalPrice, List<LineDTO> cupcakeLines) {
        this.orderID = orderID;
        this.totalPrice = totalPrice;
        this.cupcakeLines = cupcakeLines;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<LineDTO> getCupcakeLines() {
        return cupcakeLines;
    }

    public void setCupcakeLines(List<LineDTO> cupcakeLines) {
        this.cupcakeLines = cupcakeLines;
    }
}
