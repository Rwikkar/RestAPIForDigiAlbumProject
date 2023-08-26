package com.digialbum.digitalalbumrestapi;

import com.digialbum.digitalalbumrestapi.entity.ImageData;
import com.digialbum.digitalalbumrestapi.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@RestController
//@RequestMapping("/image")
@CrossOrigin("*")
public class DigitalalbumrestapiApplication {

	@Autowired
	private StorageService service;

	@PostMapping("/image")
	public ResponseEntity<?> uploadImage(@RequestParam("image")MultipartFile file) throws IOException {
		String uploadImage = service.uploadImage(file);
		return ResponseEntity.status(HttpStatus.OK)
				.body(uploadImage);
	}

	@PostMapping("/text")
	public ResponseEntity<?> uploadTextFile(@RequestParam("file")String fileContent) throws IOException {
		String uploadImage = service.uploadTextFile(fileContent);
		return ResponseEntity.status(HttpStatus.OK)
				.body(uploadImage);
	}

	@PutMapping("/text/{id_no}")
	public ResponseEntity<?> updateTextData(
			@PathVariable int id_no,
			@RequestParam("file")String fileContent) throws IOException {
		String uploadImage = service.updateTextData(id_no, fileContent);
		return ResponseEntity.status(HttpStatus.OK)
				.body(uploadImage);
	}

	@GetMapping("/Files/{id_no}")
	public ResponseEntity<?> downloadImage(@PathVariable int id_no) {
		byte[] imageData = service.downloadImage(id_no);
		return ResponseEntity.status(HttpStatus.OK)
				.contentType(MediaType.valueOf("image/png"))
				.body(imageData);
	}

	/*@GetMapping("/Photos")
	public ArrayList<Integer> getAllIds() {
		return service.getAllIds();
	}*/
	@GetMapping("/Photos")
	public ResponseEntity<?> getAllIds() {
		return ResponseEntity.status(HttpStatus.OK)
				.contentType(MediaType.valueOf("application/json"))
				.body(service.getAllIds());
	}

	/*@GetMapping("/Photos")
	public ResponseEntity<ArrayList<byte[]>> downloadImage() {
		ArrayList<byte[]> imageData = service.downloadImage();
		return ResponseEntity.status(HttpStatus.OK)
				.contentType(MediaType.valueOf("image/png"))
				.body(imageData);
	}*/

	@GetMapping("/Photos/{fileType}")
	public ResponseEntity<?> getAllDataByDataType(@PathVariable String fileType) {
		return ResponseEntity.status(HttpStatus.OK)
				.contentType(MediaType.valueOf("application/json"))
				.body(service.getAllData(fileType));
	}

	public static void main(String[] args) {
		SpringApplication.run(DigitalalbumrestapiApplication.class, args);
	}

}
