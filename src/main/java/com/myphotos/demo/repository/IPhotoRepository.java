package com.myphotos.demo.repository;

import com.myphotos.demo.model.Photo;
import org.springframework.data.repository.CrudRepository;

public interface IPhotoRepository extends CrudRepository<Photo, Integer> {
}
