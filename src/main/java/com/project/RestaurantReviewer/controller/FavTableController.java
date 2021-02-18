package com.project.RestaurantReviewer.controller;

import com.project.RestaurantReviewer.entity.FavTable;
import com.project.RestaurantReviewer.service.FavTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FavTableController {

    @Autowired
    private FavTableService favTableService;

    //post
    @PostMapping("/addFav")
    public FavTable addFav(@RequestBody FavTable favTable){
        return favTableService.saveFav(favTable);
    }

    @PostMapping("/addFavs")
    public List<FavTable> addFavs(@RequestBody List<FavTable> favTables){
        return favTableService.saveFavs(favTables);
    }

    //delete
    @DeleteMapping("/deleteFavRowById/{id}")
    public String deleteFavRowById(@PathVariable int id){
        return favTableService.deleteRowById(id);
    }

    /*@DeleteMapping("/deleteFavRowByName")
    public String deleteFavRowByName(@PathVariable String name){
        return favTableService.deleteRowByName(name);
    }*/


    //put
    @PutMapping("/updateFavRow")
    public FavTable updateFavRow(@RequestBody FavTable favTable){
        return favTableService.updateFavRow(favTable);
    }

    //get
    @GetMapping("/getFavRowById/{id}")
    public FavTable getFavRowById(@PathVariable int id){
        return favTableService.getRowById(id);
    }

    @GetMapping("/getAllFavRows")
    public List<FavTable> getAllFavRows(){
        return favTableService.getAllRows();
    }

    @GetMapping("/getFavRowByName/{name}")
    public FavTable getFavRowByName(@PathVariable String name){
        return favTableService.getRowByName(name);
    }

}
