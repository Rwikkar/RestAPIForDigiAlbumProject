package com.digialbum.digitalalbumrestapi.repository;

import com.digialbum.digitalalbumrestapi.entity.ImageData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StorageRepository extends JpaRepository<ImageData, Integer> {
    Optional<ImageData> findById(int id_no);
    List<ImageData> findByfiletypeLike(String s);
    //Optional<ImageData>[] findByfiletypeLike(String filetype);
}
