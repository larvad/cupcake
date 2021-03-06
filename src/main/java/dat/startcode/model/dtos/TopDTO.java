package dat.startcode.model.dtos;

public class TopDTO {

    private int id;
    private String top_flavor;
    private int top_price;

    public TopDTO(int id, String top_flavor, int top_price) {
        this.id = id;
        this.top_flavor = top_flavor;
        this.top_price = top_price;
    }

    public int getId() {
        return id;
    }

    public String getTop_flavor() {
        return top_flavor;
    }

    public void setTop_flavor(String top_flavor) {
        this.top_flavor = top_flavor;
    }

    public int getTop_price() {
        return top_price;
    }

    public void setTop_price(int top_price) {
        this.top_price = top_price;
    }

    @Override
    public String toString() {
        return "TopDTO{" +
                "top_flavor='" + top_flavor + '\'' +
                ", top_price=" + top_price +
                '}';
    }

}
