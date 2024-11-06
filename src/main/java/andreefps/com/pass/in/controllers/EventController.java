package andreefps.com.pass.in.controllers;

import andreefps.com.pass.in.dto.attendee.AttendeesListResponseDTO;
import andreefps.com.pass.in.dto.event.EventIdDTO;
import andreefps.com.pass.in.dto.event.EventRequestDTO;
import andreefps.com.pass.in.dto.event.EventResponseDTO;
import andreefps.com.pass.in.services.AttendeeService;
import andreefps.com.pass.in.services.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;
    private final AttendeeService attendeeService;
    @GetMapping("/{id}")
    public ResponseEntity<EventResponseDTO> getEvent(@PathVariable String id){
        EventResponseDTO event = this.eventService.getEventDetail(id);
        return ResponseEntity.ok(event);
    }

    @PostMapping
    public ResponseEntity<EventIdDTO> createEvent(@RequestBody EventRequestDTO body, UriComponentsBuilder uriComponentsBuilder){
        EventIdDTO eventIdDto = this.eventService.createEvent(body);
        var uri = uriComponentsBuilder.path("events/{id}").buildAndExpand(eventIdDto.eventId()).toUri();
        return ResponseEntity.created(uri).body(eventIdDto);
    }

    @GetMapping("/attendees/{id}")
    public ResponseEntity<AttendeesListResponseDTO> getEventAttendees(@PathVariable String id){
        AttendeesListResponseDTO attendeeList = this.attendeeService.getEventsAttendee(id);
        return ResponseEntity.ok(attendeeList);
    }
}
