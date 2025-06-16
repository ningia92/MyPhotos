package com.myphotos.demo.controller.api;

import com.myphotos.demo.model.Photo;
import com.myphotos.demo.service.IPhotoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
public class AdminPhotoController {
    private final IPhotoService photoService;

    public AdminPhotoController(@Qualifier("mainPhotoService") IPhotoService photoService) {
        this.photoService = photoService;
    }

    @RequestMapping("/admin/api/photos")
    public Iterable<Photo> getAll() {
        return photoService.getAll();
    }

    @RequestMapping("/admin/api/photos/{id}")
    public Photo getById(@PathVariable int id) {
        Optional<Photo> photo = photoService.getById(id);

        if (photo.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "item not found");
        }

        return photo.get();
    }

    @RequestMapping(value = "/admin/api/photos", method = RequestMethod.POST)
    public Photo create(@Valid @RequestBody Photo photo) {
        return photoService.create(photo);
    }

    @RequestMapping(value = "/admin/api/photos/{id}", method = RequestMethod.PUT)
    public Photo update(@PathVariable int id, @Valid @RequestBody Photo photo) {
        Optional<Photo> updatePhoto = photoService.update(id, photo);

        if (updatePhoto.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "item not found");
        }

        return updatePhoto.get();
    }

    @RequestMapping(value = "/admin/api/photos/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable int id) {
        Boolean isDeleted = photoService.delete(id);

        if (!isDeleted) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "item not found");
        }
    }
}
