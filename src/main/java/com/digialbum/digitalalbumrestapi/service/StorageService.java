package com.digialbum.digitalalbumrestapi.service;

import com.digialbum.digitalalbumrestapi.entity.ImageData;
import com.digialbum.digitalalbumrestapi.entity.RequestDataBase64;
import com.digialbum.digitalalbumrestapi.repository.StorageRepository;
import com.digialbum.digitalalbumrestapi.util.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Array;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class StorageService {
    @Autowired
    private StorageRepository repository;
    private final Timestamp ts = new Timestamp(System.currentTimeMillis());
    public String uploadImage(MultipartFile file) throws IOException {
        ImageData imageData = repository.save(ImageData.builder()
                .CRT_TS(ts)
                .UPD_TS(ts)
                .STATUS('A')
                .file_name(file.getOriginalFilename())
                .filetype(file.getContentType())
                .description("New Image added")
                .raw_data(ImageUtils.compressImage(file.getBytes())).build()
        );
        if(imageData != null) {
            return "{ \"File uploaded successfully\": \"" + file.getOriginalFilename() + "\" }";
        }
        return null;
    }

    public String uploadTextFile(String fileContent) throws IOException {
        ImageData fileData = repository.save(ImageData.builder()
                .CRT_TS(ts)
                .UPD_TS(ts)
                .STATUS('A')
                .file_name("tempFile.txt")
                .filetype("text/plain")
                .description("New File added")
                .raw_data(ImageUtils.compressImage(fileContent.getBytes())).build()
        );
        if(fileData != null) {
            return "{ \"File uploaded successfully\": \"" + "tempFile.txt" + "\" }";
        }
        return null;
    }

    public byte[] downloadImage(int id_no) {
        Optional<ImageData> dbImageData = repository.findById(id_no);
        byte[] image = ImageUtils.decompressImage(dbImageData.get().getRaw_data());
        return image;
    }

    /*public ArrayList<Integer> getAllIds() {
        ArrayList<Integer> listOfIds = new ArrayList<>();
        List<ImageData> allDataList = repository.findByfiletypeLike("image%");
        for (ImageData elements: allDataList) {
            listOfIds.add(elements.getID());
        }
        return listOfIds;
    }*/
    public String getAllIds() {
        String listOfIds = "[ ";
        List<ImageData> allDataList = repository.findByfiletypeLike("image%");

        for (ImageData elements: allDataList) {
            listOfIds = listOfIds.concat("{ \"id\": \"");
            listOfIds = listOfIds.concat(String.valueOf(elements.getID()));
            listOfIds = listOfIds.concat("\" }, ");
        }
        listOfIds = listOfIds.substring(0, listOfIds.length()-2);
        listOfIds = listOfIds.concat(" ]");
        return listOfIds;
    }

    public List<ImageData> getAllData(String requestDataType) {
        requestDataType += "%";
        System.out.println(requestDataType);
        List<ImageData> allDataList = repository.findByfiletypeLike(requestDataType);
        for (int index = 0; index < allDataList.size(); index++) {
            byte[] image = ImageUtils.decompressImage(allDataList.get(index).getRaw_data());
            //byte[] image = allDataList.get(index).getRaw_data();
            allDataList.get(index).setRaw_data(image);
        }
        return allDataList;
    }

    /*public ArrayList<byte[]> downloadImage() {
        ArrayList<byte[]> imageArrayList = new ArrayList<>();
        Optional<ImageData>[] dbImageData = repository.findByfiletypeLike("image%");
        //ArrayList<ImageData> imageArrayList = new ArrayList<>();
        //imageArrayList = dbImageData.get().getRaw_data();
        for(int elements = 0; elements < dbImageData.length; elements++) {
            byte[] image = ImageUtils.decompressImage(dbImageData[elements].get().getRaw_data());
            imageArrayList.add(image);
        }
        //byte[] image = ImageUtils.decompressImage(dbImageData.get().getRaw_data());
        return imageArrayList;
    }*/

}
