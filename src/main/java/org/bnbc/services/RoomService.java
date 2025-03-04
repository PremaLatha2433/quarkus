package org.bnbc.services;

import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.bnbc.entities.Room;
import org.bnbc.panache.repo.RoomRepository;

import java.util.List;

@ApplicationScoped
public class RoomService {

    @Inject
    RoomRepository roomRepository;

    public List<Room> findRoomByNameAndRoomNumber(String name, String roomNumber) {
        return roomRepository.findRoomByNameAndRoomNumber(name,
                roomNumber);
    }

    public List<Room> findRoomByIdAndName(int id, String name) {
    return roomRepository.findRoomByIdAndName(id,name);
    }

    public List<Room> findRoomsBySizeAndPage(int page,int size) {
        return roomRepository.findRoomsBySizeAndPage(page,size);
    }
    public List<Room> findAll(){
        return  roomRepository.listAll();
    }
    public Room findById(long id){
        return roomRepository.findById(id);
    }
    public void deleteById(long id){
         roomRepository.deleteById(id);
    }

    public Room findByName(String name) {
        return roomRepository.findByName(name);
    }
}