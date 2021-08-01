package dev.malkon.events.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.Date;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Event {

    @Id
    private String id;

    private String name;

    private String description;

    @JsonFormat(pattern = "dd-MM-yyyy-hh:mm")
    private Date date;

    private long span;

}

