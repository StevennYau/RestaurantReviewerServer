package com.project.RestaurantReviewer.service;

import com.project.RestaurantReviewer.entity.FavTable;
import com.project.RestaurantReviewer.repository.FavTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavTableService {

    @Autowired
    private FavTableRepository favTableRepository;

    //post
    public FavTable saveFav(FavTable favTable){
        return favTableRepository.save(favTable);
    }

    public List<FavTable> saveFavs(List<FavTable> favTables) {
        return favTableRepository.saveAll(favTables);
    }

    //delete
    public String deleteRowById(int id){
        favTableRepository.deleteById(id);
        return "fav row " + id + " has been removed";
    }

    /*public String deleteRowByName(String name){
        favTableRepository.deleteByName(name);
        return "fav row " + name + " has been removed";
    }*/

    public FavTable updateFavRow(FavTable favTable) {
        FavTable existingRow = favTableRepository.findById(favTable.getFav_id()).orElseThrow(() -> new IllegalStateException(
                "row with id " + favTable.getFav_id() + " does not exist"
        ));

        existingRow.setUser_id(favTable.getUser_id());
        existingRow.setBusiness_id(favTable.getBusiness_id());
        existingRow.setUser_id(favTable.getUser_id());

        return favTableRepository.save(existingRow);
    }

    //get

    public FavTable getRowById(int id) {
        return favTableRepository.findById(id).orElse(null);
    }

    public List<FavTable> getAllRows() {
        return favTableRepository.findAll();
    }

    public FavTable getRowByName(String name){
        return favTableRepository.findByName(name);
    }
}
