package dat.startcode.model.dtos;

public class OrderDTO {
    private int orderID;
    private int quantity;
    private int top_id;
    private int bot_id;

    public OrderDTO(int orderID, int quantity, int top_id, int bot_id) {
        this.orderID = orderID;
        this.quantity = quantity;
        this.top_id = top_id;
        this.bot_id = bot_id;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getTop_id() {
        return top_id;
    }

    public void setTop_id(int top_id) {
        this.top_id = top_id;
    }

    public int getBot_id() {
        return bot_id;
    }

    public void setBot_id(int bot_id) {
        this.bot_id = bot_id;
    }
}
