package org.bnbc;

import io.quarkus.arc.Arc;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.bnbc.entities.Room;
import org.jboss.logging.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

@QuarkusMain
public class CommandRunner implements QuarkusApplication {

    private static final Logger Log = Logger.getLogger(CommandRunner.class.getName());

    @Inject
    DataSource dataSource;


    @Inject
    EntityManager entityManager;

    @Override
    public int run(String... args) throws Exception {
        Log.info("application started");
        String sql ="SELECT NAME,ROOM_NUMBER,BED_INFO FROM ROOM";
        List<Room> rooms = new ArrayList<>();
        Arc.container().requestContext().activate();
        List<Room> roomsList = entityManager.createQuery("select r from Room r",Room.class).getResultList();
        roomsList.forEach(System.out::println);
        Arc.container().requestContext().deactivate();
        try{
            Connection connection= dataSource.getConnection();
            try (Statement statement= connection.createStatement()){
                ResultSet rs = statement.executeQuery(sql);
                while (rs.next())
                {
                    rooms.add(new Room(rs.getString("NAME"), rs.getString("ROOM_NUMBER")
                            ,rs.getString("BED_INFO") ));
                }

            }
        }catch (Exception e)
        {
            Log.error(e.getMessage());
        }
        rooms.forEach(System.out::println);
        Log.info("complete application");
        return 0;
    }
}
