package dev.malkon.events.helper

import dev.malkon.events.domain.Event

class EventCreationHelper {
    public static Event validEvent = new Event("123",
            'Event',
            "Event description",
            new Date(0x7e5, 8, 2, 19, 30),
            1_800_00L)

    public static List<Event> validEvents = [validEvent, validEvent, validEvent]
}
