package dev.malkon.events.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.net.URI;
import java.util.Date;

import static dev.malkon.events.constants.RequestPathConstants.API_EVENT;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Event {

    @Id
    private String id;

    @NotBlank(message = "Event's name cannot be blank.")
    private String name;

    @NotBlank(message = "Event's description cannot be blank.")
    @Size(max = 1000, message = "Event's description cannot be longer than 1000 characters.")
    private String description;

    @JsonFormat(pattern = "dd-MM-yyyy-hh:mm")
    private Date date;

    private long span;

    public URI toURI() {
        return URI.create(API_EVENT + "/" + id);
    }
}

