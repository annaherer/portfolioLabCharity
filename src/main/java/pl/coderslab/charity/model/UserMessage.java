package pl.coderslab.charity.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "user_message")
@Getter
@Setter
public class UserMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank (message = "Name must be provided")
    private String name;
    @NotBlank (message = "Surname must be provided")
    private String surname;
    @NotBlank (message = "Message must be provided")
    private String messageText;
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;
}