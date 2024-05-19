package app.controller;

import app.entities.Image;
import app.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.zip.DataFormatException;

@RestController
@RequestMapping("/image")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    @PostMapping("/{fitness_program_id}")
    public ResponseEntity<?> uploadImage(@RequestParam("image") MultipartFile file,@PathVariable("fitness_program_id") int fitness_program_id) throws IOException {
        String uploadImage = imageService.uploadImage(file, fitness_program_id);
        return ResponseEntity.status(HttpStatus.OK).body(uploadImage);
    }

    @GetMapping("/all/{fitness_program_id}")
    public ResponseEntity<?> downloadImage(@PathVariable("fitness_program_id") int fitness_program_id) throws DataFormatException, IOException {
        List<Image> imageData = imageService.getAllImages(fitness_program_id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(imageData);
    }
}