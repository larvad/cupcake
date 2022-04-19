package dat.startcode.model.dtos;

public class LineDTO {

    int order_id;
    int total_price;
    int quantity;
    int top_id;
    int bot_id;

    public LineDTO(int order_id, int total_price, int quantity, int top_id, int bot_id) {
        this.order_id = order_id;
        this.total_price = total_price;
        this.quantity = quantity;
        this.top_id = top_id;
        this.bot_id = bot_id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getTotal_price() {
        return total_price;
    }

    public void setTotal_price(int total_price) {
        this.total_price = total_price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setTop_id(int top_id) {
        this.top_id = top_id;
    }

    public void setBot_id(int bot_id) {
        this.bot_id = bot_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getTop_id() {
        return top_id;
    }

    public int getBot_id() {
        return bot_id;
    }
}
