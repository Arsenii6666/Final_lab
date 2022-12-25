package Proxy;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JSON_Container {
    JSON_Container(String name, String JSON_data){
        setJSON_data(JSON_data);
        setName(name);
    }
    String name;
    String JSON_data;
}
