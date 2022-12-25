package Proxy;

import lombok.Getter;
import lombok.Setter;
import org.json.JSONObject;

import java.io.IOException;
import java.sql.SQLException;

public interface Reader {
    @Getter
    @Setter
    public JSONObject data=null;
    JSONObject getData();
    void readStateData(String state) throws IOException, ClassNotFoundException, SQLException;
}
