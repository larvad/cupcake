package dat.startcode.model.persistence;

import dat.startcode.model.dtos.BotDTO;
import dat.startcode.model.dtos.CupcakeDTO;
import dat.startcode.model.dtos.TopDTO;
import dat.startcode.model.exceptions.DatabaseException;

import java.util.List;

public interface ICupcakeMapper {


    public List<TopDTO> getCupcakesTop() throws DatabaseException;
    public List<BotDTO> getCupcakesBot() throws DatabaseException;
    public TopDTO findCupcakeTop(int id) throws DatabaseException;
    public BotDTO findCupcakeBot(int id) throws DatabaseException;

    }

