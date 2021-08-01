package dev.malkon.events.domain;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;


@Data
@Builder
public class Event {

    @Id
    private String id;

    private String name;

    private String description;

}
