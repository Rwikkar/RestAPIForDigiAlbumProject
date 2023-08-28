package com.digialbum.digitalalbumrestapi.repository;

import com.digialbum.digitalalbumrestapi.entity.ImageData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface StorageRepository extends JpaRepository<ImageData, Integer> {
    Optional<ImageData> findById(int id_no);
    List<ImageData> findByfiletypeLike(String s);
    @Query("SELECT d FROM ImageData d WHERE d.filetype LIKE ?1% ORDER BY d.ID asc LIMIT ?2")
    List<ImageData> findByfiletypeSpecific(String fileType, int noOfRecords);
    //Optional<ImageData>[] findByfiletypeLike(String filetype);
}
