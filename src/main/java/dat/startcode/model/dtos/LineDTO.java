package dat.startcode.model.dtos;

public class LineDTO {

    private int orderId;
    private int total_price;
    private int quantity;
    private int top_id;
    private int bot_id;

    public LineDTO(int orderId, int total_price, int quantity, int top_id, int bot_id) {
        this.orderId = orderId;
        this.total_price = total_price;
        this.quantity = quantity;
        this.top_id = top_id;
        this.bot_id = bot_id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getTotal_price() {
        return total_price;
    }

    public void setTotal_price(int total_price) {
        this.total_price = total_price;
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
