package org.bnbc.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.panache.common.Parameters;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="ROOM")
@NamedQueries({
        @NamedQuery(name = "Room.getByName", query = "from Room where name = ?1"),
        @NamedQuery(name = "Room.count", query = "select count(*) from Room"),
        @NamedQuery(name = "Room.updateById", query = "update Room r set r.name = :name where r.id = :id"),
        @NamedQuery(name = "Room.deleteById", query = "delete from Room r where r.id = ?1")
})
public class Room extends PanacheEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ROOM_ID")
    private long id;
    @Column(name="NAME")
    private String name;
    @Column(name="ROOM_NUMBER")
    private String roomNumber;
    @Column(name = "BED_INFO")
    private String bedInfo;

    public Room() {
        super();
    }

    public Room(String name, String bedInfo, String roomNumber) {
        this.name = name;
        this.bedInfo = bedInfo;
        this.roomNumber = roomNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getBedInfo() {
        return bedInfo;
    }

    public void setBedInfo(String bedInfo) {
        this.bedInfo = bedInfo;
    }

    @Override
    public String toString() {
        return "Room{" +
                "name='" + name + '\'' +
                ", roomNumber='" + roomNumber + '\'' +
                ", bedInfo='" + bedInfo + '\'' +
                '}';
    }
    public static Room findByName(String name){
        return find("#Room.getByName", name).firstResult();
    }

    public static long count() {
        return count("#Room.count");
    }

    public static long updateById( long id) {
        return update("#Room.updateById", Parameters.with("id", id));
    }

    public static long deleteById(long id) {
        return delete("#Room.deleteById", id);
    }
    public static List<Room> findOrdered() {
        return find("ORDER BY name").list();
    }

}
