package dat.startcode.model.entities;

public class Bottom {

    private int bottom_id;
    private String flavor;
    private int price;

    public Bottom(int bottom_id, String flavor, int price) {
        this.bottom_id = bottom_id;
        this.flavor = flavor;
        this.price = price;
    }

    public int getBottom_id() {
        return bottom_id;
    }

    public void setBottom_id(int bottom_id) {
        this.bottom_id = bottom_id;
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
        return "Bottom{" +
                "bottom_id=" + bottom_id +
                ", flavor='" + flavor + '\'' +
                ", price=" + price +
                '}';
    }
}
