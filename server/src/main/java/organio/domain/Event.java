package organio.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import organio.constants.RequestPathConstants;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.net.URI;
import java.util.Date;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Event extends BaseEntity {

    @Id
    String id;

    @NotBlank(message = "Event's name cannot be blank.")
    private String name;

    @NotBlank(message = "Event's description cannot be blank.")
    @Size(max = 1000, message = "Event's description cannot be longer than 1000 characters.")
    private String description;

    @JsonFormat(pattern = "dd-MM-yyyy-hh:mm")
    private Date date;

    private long span;

    public URI toURI() {
        return URI.create(RequestPathConstants.API_EVENT + "/" + id);
    }
}

