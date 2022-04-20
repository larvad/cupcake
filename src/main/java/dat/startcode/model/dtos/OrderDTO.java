package dat.startcode.model.dtos;

import java.util.List;

public class OrderDTO {
    private int orderID;
    private int total_price;
    List<CupcakeDTO> cupcakesList;

    public OrderDTO(int orderID, int total_price, List<CupcakeDTO> cupcakesList) {
        this.orderID = orderID;
        this.total_price = total_price;
        this.cupcakesList = cupcakesList;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getTotal_price() {
        return total_price;
    }

    public void setTotal_price(int total_price) {
        this.total_price = total_price;
    }

    public List<CupcakeDTO> getCupcakesList() {
        return cupcakesList;
    }

    public void setCupcakesList(List<CupcakeDTO> cupcakesList) {
        this.cupcakesList = cupcakesList;
    }
}



