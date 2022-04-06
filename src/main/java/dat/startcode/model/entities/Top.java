package dat.startcode.model.entities;

public class Top {

    private int top_id;
    private String flavor;
    private int price;

    public Top(int top_id, String flavor, int price) {
        this.top_id = top_id;
        this.flavor = flavor;
        this.price = price;
    }

    public int getTop_id() {
        return top_id;
    }

    public void setTop_id(int top_id) {
        this.top_id = top_id;
    }

    public String getFlavor() {
        return flavor;
    }

    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Top{" +
                "top_id=" + top_id +
                ", flavor='" + flavor + '\'' +
                ", price=" + price +
                '}';
    }
}
