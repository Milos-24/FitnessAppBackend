package app.dtos;

import lombok.Data;

@Data
public class FitnessProgramDTO {
    private String name;
    private String price;
    private String duration;
    private String description;
    private int level;
    private String location;
    private String selectedCategory;

    public FitnessProgramDTO() {
    }

    public FitnessProgramDTO(String name, String price, String duration, String description, int level, String location, String selectedCategory) {
        this.name = name;
        this.price = price;
        this.duration = duration;
        this.description = description;
        this.level = level;
        this.location = location;
        this.selectedCategory = selectedCategory;
    }

}
