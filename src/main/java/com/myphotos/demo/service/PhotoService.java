package com.myphotos.demo.service;

import com.myphotos.demo.model.Photo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PhotoService implements IPhotoService {
    private List<Photo> photos;
    private int lastId;

    public PhotoService() {
        this.photos = new ArrayList<>();

        this.photos.add(new Photo(1, "./img/01.png"));
        this.photos.add(new Photo(2, "./img/02.png"));
        this.photos.add(new Photo(3, "./img/03.png"));

        this.lastId = 3;
    }

    @Override
    public Iterable<Photo> getAll() {
        return photos;
    }

    @Override
    public Optional<Photo> getById(int id) {
        Optional<Photo> photo = photos.stream().filter(item->item.getId() == id).findFirst();

        return photo;
    }

    @Override
    public Photo create(Photo photo) {
        this.lastId++;

        photo.setId(this.lastId);

        this.photos.add(photo);

        return photo;
    }

    @Override
    public Optional<Photo> update(int id, Photo photo) {
        Optional<Photo> foundPhoto = photos.stream().filter(item->item.getId() == id).findFirst();

        if (foundPhoto.isEmpty()) {
            return Optional.empty();
        }

        foundPhoto.get().setUrl(photo.getUrl());

        return foundPhoto;
    }

    @Override
    public Boolean delete(int id) {
        Optional<Photo> foundPhoto = photos.stream().filter(item->item.getId() == id).findFirst();

        if (foundPhoto.isEmpty()) {
            return false;
        }

        photos.remove(foundPhoto.get());

        return true;
    }
}
