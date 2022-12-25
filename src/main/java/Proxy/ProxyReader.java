package Proxy;

import org.json.JSONObject;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ProxyReader implements Reader{
    Reader reader;
    Connection c;
    Statement stmt;
    ProxyReader(Reader reader){
        this.reader=reader;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:user.db");
            stmt = c.createStatement();
            String sql = "CREATE TABLE USER " +
                    "(NAME           TEXT    " +
                    " JSON_DATA          TEXT    ";
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Opened database successfully");
    }
    @Override
    public JSONObject getData() {
        return data;
    }

    @Override
    public void readStateData(String state) throws IOException, ClassNotFoundException, SQLException {
        if (!caching(state)){
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:user.db");
            stmt = c.createStatement();
            reader.readStateData(state);
            String data0=reader.getData().toString();
            data=reader.getData();
            try {
                System.out.println("Opened database successfully");
                String sql = "INSERT or REPLACE INTO JSON_CONTAINER (NAME, JSON_data) " +
                        "VALUES ('" + state + "', '" + data0 + "');";
                stmt.executeUpdate(sql);
                stmt.close();
                c.close();
            } catch ( Exception e ) {
                System.err.println( e.getClass().getName() + ": " + e.getMessage() );
                System.exit(0);
            }
            System.out.println("Records created successfully");
        }

    }
    boolean caching(String state){
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:user.db");
            stmt = c.createStatement();
            String sql = "SELECT"+ state+ "FROM JSON_DATA";
            stmt.executeUpdate(sql);
            JSONObject jsonObject = new JSONObject(sql);
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            return false;
        }
        return true;
    }
}
