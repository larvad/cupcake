package dat.startcode.model.dtos;

public class CupcakeDTO {

    private int top_id;
    private String top_flavor;
    private int bottom_id;
    private String bottom_flavor;
    private int total_price;
    private int quantity;

    public CupcakeDTO(int top_id, String top_flavor, int bottom_id, String bottom_flavor, int total_price, int quantity) {
        this.top_id = top_id;
        this.top_flavor = top_flavor;
        this.bottom_id = bottom_id;
        this.bottom_flavor = bottom_flavor;
        this.total_price = total_price;
        this.quantity = quantity;
    }

    public int getTop_id() {
        return top_id;
    }

    public void setTop_id(int top_id) {
        this.top_id = top_id;
    }

    public String getTop_flavor() {
        return top_flavor;
    }

    public void setTop_flavor(String top_flavor) {
        this.top_flavor = top_flavor;
    }

    public int getBottom_id() {
        return bottom_id;
    }

    public void setBottom_id(int bottom_id) {
        this.bottom_id = bottom_id;
    }

    public String getBottom_flavor() {
        return bottom_flavor;
    }

    public void setBottom_flavor(String bottom_flavor) {
        this.bottom_flavor = bottom_flavor;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getTotal_price() {
        return total_price;
    }

    public void setTotal_price(int total_price) {
        this.total_price = total_price;
    }

}
