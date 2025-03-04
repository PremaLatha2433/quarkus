package org.bnbc.panache.repo;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Page;
import jakarta.enterprise.context.ApplicationScoped;
import org.bnbc.entities.Room;

import java.util.List;
import java.util.stream.Stream;

@ApplicationScoped
public class RoomRepository implements PanacheRepository<Room> {

    public List<Room> findRoomByNameAndRoomNumber(String name, String roomNumber) {
        return Room.find("name = ?1 and roomNumber = ?2", name, roomNumber).list();
    }
    public Stream<PanacheEntityBase> streamAllBooks() {
        return Room.streamAll();
    }

    public List<Room> findRoomByIdAndName(int id,String name){
        return Room.find("id = ?1 and name = ?2",id,name).list();
    }
    public List<Room> findRoomsBySizeAndPage(int page,int size){
        return Room.findAll().page(Page.of(page,size)).list();
    }

    public Room findByName(String name){
       return find(name,name).firstResult();
    }

//    @Transient
//    public Uni<Void> updateRoom(long id,String name){
//        return Room.<Room>findById(id)
//                .onItem().ifNotNull().invoke(book -> book.title = newTitle)
//                .onItem().ifNotNull().transformToUni(Book::persist);
//
//    }
}
