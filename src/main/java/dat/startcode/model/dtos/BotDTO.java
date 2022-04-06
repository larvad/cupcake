package dat.startcode.model.dtos;

public class BotDTO {

    private int id;
    private String bot_flavor;
    private int bot_price;

    public BotDTO(int id, String bot_flavor, int bot_price) {
        this.id = id;
        this.bot_flavor = bot_flavor;
        this.bot_price = bot_price;
    }

    public int getId() {
        return id;
    }

    public String getBot_flavor() {
        return bot_flavor;
    }

    public void setBot_flavor(String bot_flavor) {
        this.bot_flavor = bot_flavor;
    }

    public int getBot_price() {
        return bot_price;
    }

    public void setBot_price(int bot_price) {
        this.bot_price = bot_price;
    }

    @Override
    public String toString() {
        return "BotDTO{" +
                "bot_flavor='" + bot_flavor + '\'' +
                ", bot_price=" + bot_price +
                '}';
    }
}
