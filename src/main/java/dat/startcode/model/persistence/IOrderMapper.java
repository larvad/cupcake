package dat.startcode.model.persistence;

import dat.startcode.model.dtos.CupcakeDTO;

import java.util.List;

public interface IOrderMapper {

    public void setLines(List<CupcakeDTO> list, int orderId);

}
