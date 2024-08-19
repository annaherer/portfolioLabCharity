package pl.coderslab.charity.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "donation")
@Getter
@Setter
public class Donation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull (message = "Number of bags (min. 1) must be provided")
    @Min(value = 1, message = "Minimum 1 bag must be entered")
    private Integer quantity;
    @ManyToMany
    private List<Category> categories;
    @ManyToOne
    @NotNull (message = "Institution must be provided")
    private Institution institution;
    @NotBlank (message = "Street must be provided")
    private String street;
    @NotBlank (message = "City must be provided")
    private String city;
    @NotBlank (message = "Zip code must be provided")
    private String zipCode;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @NotNull (message = "Pick up date must be provided")
    private LocalDate pickUpDate;
    @DateTimeFormat(pattern = "hh:mm")
    @Temporal(TemporalType.TIME)
    @NotNull (message = "Pick up time must be provided")
    private LocalTime pickUpTime;
    private String pickUpComment;
}