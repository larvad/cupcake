package dat.startcode.model.dtos;

public class CupcakeDTO {

    private BotDTO botDTO;
    private TopDTO topDTO;
    private int quantity;
    private int cupcakeID;

    public CupcakeDTO(BotDTO botDTO, TopDTO topDTO, int quantity, int cupcakeID) {
        this.botDTO = botDTO;
        this.topDTO = topDTO;
        this.quantity = quantity;
        this.cupcakeID = cupcakeID;
    }

    public int getCupcakeID() {
        return cupcakeID;
    }

    public int getTotalPrice() {

        return (botDTO.getBot_price() + topDTO.getTop_price())*quantity;


    }


    public BotDTO getBotDTO() {
        return botDTO;
    }

    public void setBotDTO(BotDTO botDTO) {
        this.botDTO = botDTO;
    }

    public TopDTO getTopDTO() {
        return topDTO;
    }

    public void setTopDTO(TopDTO topDTO) {
        this.topDTO = topDTO;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
