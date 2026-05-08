package com.tasc.tascbackend.controller;

import com.tasc.tascbackend.entity.Place;
import com.tasc.tascbackend.service.PlaceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")   // Cho phép Flutter gọi từ localhost
public class PlaceController {

    private final PlaceService placeService;

    public PlaceController(PlaceService placeService) {
        this.placeService = placeService;
    }

    // API: Get All Places - Popular Destination
    @GetMapping("/places")
    public ResponseEntity<List<Place>> getAllPlaces() {
        List<Place> places = placeService.getAllPlaces();
        return ResponseEntity.ok(places);
    }
}