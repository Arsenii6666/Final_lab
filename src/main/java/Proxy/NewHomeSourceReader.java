package Proxy;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import lombok.Getter;
import org.json.JSONObject;

public class NewHomeSourceReader implements Reader{
    @Getter
    JSONObject data;
    @Override
    public void readStateData(String state) throws IOException {
        state=state.toLowerCase();
        URL url = new URL("https://www.newhomesource.com/state/" + state);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        String text = new Scanner(connection.getInputStream()).useDelimiter("\\Z").next();
        JSONObject jsonObject = new JSONObject(text);
        data=jsonObject;
    }
}
