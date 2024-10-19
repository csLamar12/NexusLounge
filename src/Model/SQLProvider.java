package Model;

import java.sql.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class SQLProvider {
    protected Statement stat = null;
    protected Connection conn = null;
    protected PreparedStatement ps = null;
    protected ResultSet rs = null;
    private static final String DRIVER = "com.mysql.jdbc.Driver";

    private static final Logger LOGGER = LogManager.getLogger(SQLProvider.class);
    public SQLProvider() {
        try{
            LOGGER.warn("Attempting to connect to database.");
            Class.forName(DRIVER);
            String url = "jdbc:mysql://localhost:3306/NexusLounge";
            conn = DriverManager.getConnection(url, "root", "root");

        }
        catch (ClassNotFoundException | SQLException | NullPointerException e){
            LOGGER.error(e.getMessage());
        }
        catch (Exception e){
            LOGGER.error(e.getMessage());
        }
    }

    public Statement getStat() {
        return stat;
    }

    public void setStat(Statement stat) {
        this.stat = stat;
    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public PreparedStatement getPs() {
        return ps;
    }

    public void setPs(PreparedStatement ps) {
        this.ps = ps;
    }

    public ResultSet getRs() {
        return rs;
    }

    public void setRs(ResultSet rs) {
        this.rs = rs;
    }
}
