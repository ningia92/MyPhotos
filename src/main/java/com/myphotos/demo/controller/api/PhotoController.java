package com.myphotos.demo.controller.api;

import com.myphotos.demo.model.Photo;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class PhotoController {
    private List<Photo> photos;

    public PhotoController(List<Photo> photos) {
        this.photos = new ArrayList<>();

        this.photos.add(new Photo(1, "./img/01.png"));
        this.photos.add(new Photo(2, "./img/02.png"));
        this.photos.add(new Photo(3, "./img/03.png"));
    }

    @RequestMapping("/api/photos")
    public Iterable<Photo> getAll() {
        return photos;
    }

    @RequestMapping("/api/photos/{id}")
    public Photo getById(@PathVariable int id) {
        Optional<Photo> photo = photos.stream().filter(item->item.getId() == id).findFirst();

        if (photo.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "item not found");
        }

        return photo.get();
    }
}
