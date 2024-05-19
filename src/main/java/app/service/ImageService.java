package app.service;

import app.entities.FitnessProgram;
import app.entities.Image;
import app.repositories.FitnessProgramRepository;
import app.repositories.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.DataFormatException;

import static app.service.ImageUtils.decompressImage;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;
    private final FitnessProgramRepository fitnessProgramRepository;
    public String uploadImage(MultipartFile imageFile, int fitnessProgramId) throws IOException {

        FitnessProgram fitnessProgram = fitnessProgramRepository.findById(fitnessProgramId).orElse(null);

        Image imageToSave = Image.builder()
                .image(ImageUtils.compressImage(imageFile.getBytes()))
                .build();
        imageToSave.setFitnessProgram(fitnessProgram);

        imageRepository.save(imageToSave);
        return "file uploaded successfully : " + imageFile.getOriginalFilename();
    }

    public List<Image> getAllImages(int fitness_program_id) throws IOException, DataFormatException {
        List<Image> images = imageRepository.findAllByFitnessProgramId(fitness_program_id);
        return images.stream()
                .peek(image -> {
                    try {
                         image.setImage(decompressImage(image.getImage()));
                    } catch (DataFormatException | IOException e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.toList());
    }
}