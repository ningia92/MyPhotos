package com.myphotos.demo.service;

import com.myphotos.demo.model.Photo;
import com.myphotos.demo.repository.IPhotoRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("mainPhotoService")
public class DbPhotoService implements IPhotoService {
    private final IPhotoRepository photoRepository;

    public DbPhotoService(IPhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }

    @Override
    public Iterable<Photo> getAll() {
        return photoRepository.findAll();
    }

    @Override
    public Optional<Photo> getById(int id) {
        return photoRepository.findById(id);
    }

    @Override
    public Photo create(Photo photo) {
        return photoRepository.save(photo);
    }

    @Override
    public Optional<Photo> update(int id, Photo photo) {
        Optional<Photo> foundPhoto = photoRepository.findById(id);

        if (foundPhoto.isEmpty()) {
            return Optional.empty();
        }

        foundPhoto.get().setUrl(photo.getUrl());

        photoRepository.save(foundPhoto.get());

        return foundPhoto;
    }

    @Override
    public Boolean delete(int id) {
        Optional<Photo> foundPhoto = photoRepository.findById(id);

        if (foundPhoto.isEmpty()) {
            return false;
        }

        photoRepository.delete(foundPhoto.get());

        return true;

    }
}
