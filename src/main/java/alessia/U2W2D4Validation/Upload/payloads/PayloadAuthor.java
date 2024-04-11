package alessia.U2W2D4Validation.Upload.payloads;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.AccessLevel;
import lombok.Setter;
import org.springframework.format.annotation.NumberFormat;

public record PayloadAuthor(
        @NotEmpty(message = "Il nome proprio è obbligatorio")
        @Size(min = 3, max = 30, message = "Il nome proprio deve essere compreso tra i 3 e i 30 caratteri")
        String name,
        @NotEmpty(message = "Il nome proprio è obbligatorio")
        @Size(min = 3, max = 30, message = "Il nome proprio deve essere compreso tra i 3 e i 30 caratteri")
        String surname,
        @NotEmpty(message = "L'email è obbligatoria")
        @Email(message = "L'email inserita non è valida")
        String eMail,
        @NotNull(message = "L'anno di nacita è obbligatorio")
        int birthdayYear){

}
