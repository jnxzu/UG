package J.AppUsers.model;

import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class StatsResponse{
    private String nazwa;
    private List<Map<Object, Object>> kraje;

    public StatsResponse(){}
}