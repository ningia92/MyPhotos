package com.myphotos.demo.controller.api;

import com.myphotos.demo.model.Photo;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class AdminPhotoController {
    private List<Photo> photos;
    private int lastId;

    public AdminPhotoController(List<Photo> photos) {
        this.photos = new ArrayList<>();

        this.photos.add(new Photo(1, "./img/01.png"));
        this.photos.add(new Photo(2, "./img/02.png"));
        this.photos.add(new Photo(3, "./img/03.png"));

        this.lastId = 3;
    }

    @RequestMapping("/admin/api/photos")
    public Iterable<Photo> getAll() {
        return photos;
    }

    @RequestMapping("/admin/api/photos/{id}")
    public Photo getById(@PathVariable int id) {
        Optional<Photo> photo = photos.stream().filter(item->item.getId() == id).findFirst();

        if (photo.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "item not found");
        }

        return photo.get();
    }

    @RequestMapping(value = "/admin/api/photos", method = RequestMethod.POST)
    public Photo create(@RequestBody Photo photo) {
        this.lastId++;

        photo.setId(this.lastId);

        this.photos.add(photo);

        return photo;
    }

    @RequestMapping(value = "/admin/api/photos/{id}", method = RequestMethod.PUT)
    public Photo update(@PathVariable int id, @RequestBody Photo photo) {
        Optional<Photo> foundPhoto = photos.stream().filter(item->item.getId() == id).findFirst();

        if (foundPhoto.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "item not found");
        }

        foundPhoto.get().setUrl(photo.getUrl());

        return foundPhoto.get();
    }

    @RequestMapping(value = "/admin/api/photos/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable int id) {
        Optional<Photo> foundPhoto = photos.stream().filter(item->item.getId() == id).findFirst();

        if (foundPhoto.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "item not found");
        }

        photos.remove(foundPhoto.get());
    }
}
