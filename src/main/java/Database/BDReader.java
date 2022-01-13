package Database;

import Objects.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class BDReader{
    public static void main(String[] args)
    {
        Connection connection = null;
        try
        {
            // create a database connection
            connection = DriverManager.getConnection("jdbc:sqlite:assets/InvaderDatabase.sqlite");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.
            ResultSet monsterReader = statement.executeQuery("select * from Objects.Monster");
            List<Monster> monsters = new ArrayList<>();
            ResultSet waveReader = statement.executeQuery("select * from Wave");
            Monster monster;
            while(waveReader.next())
            {
                // read the result set
                monsterReader = statement.executeQuery("SELECT * FROM Objects.Monster WHERE rowid = "
                                                        + waveReader.getInt("MNTID") + ";");

                monster = new Monster(monsterReader.getString("name"),
                                    (monsterReader.getInt("destructible")!= 0),
                                    monsterReader.getInt("life"),
                                    monsterReader.getString("sprite"),
                                    monsterReader.getInt("speed"));

                MonsterWave wave = new MonsterWave(waveReader.getInt("Xpos"),
                                                    waveReader.getInt("ypos"),
                                                    waveReader.getInt("lineize"),
                                                    waveReader.getInt("ColumnSize"),
                                                    waveReader.getInt("xOffset"),
                                                    waveReader.getInt("yOffset"),
                                                    monster);
            }
            while(monsterReader.next())
            {
                // read the result set
                System.out.println("name = " + monsterReader.getString("Name"));
                System.out.println("OBJID = " + monsterReader.getInt("OBJID"));
            }

        }
        catch(SQLException e)
        {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
        }
        finally
        {
            try
            {
                if(connection != null)
                    connection.close();
            }
            catch(SQLException e)
            {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }
    }
}