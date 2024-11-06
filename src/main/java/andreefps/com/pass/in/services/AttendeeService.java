package andreefps.com.pass.in.services;

import andreefps.com.pass.in.domain.attendee.Attendee;
import andreefps.com.pass.in.domain.checkin.CheckIn;
import andreefps.com.pass.in.dto.attendee.AttendeeDetails;
import andreefps.com.pass.in.dto.attendee.AttendeesListResponseDTO;
import andreefps.com.pass.in.repositories.AttendeeRepository;
import andreefps.com.pass.in.repositories.CheckinRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AttendeeService {
    private final AttendeeRepository attendeeRepository;
    private final CheckinRepository checkinRepository;
    public List<Attendee> getAllAttendeesFromEvent(String eventId){
        return this.attendeeRepository.findByEventId(eventId);
    }

    public AttendeesListResponseDTO getEventsAttendee(String eventId){
        List<Attendee> attendeesList = this.getAllAttendeesFromEvent(eventId);

        List<AttendeeDetails> attendeeDetailsList = attendeesList.stream().map(attendee -> {
            Optional<CheckIn> checkIn = this.checkinRepository.findByAttendeeId(attendee.getId());
            LocalDateTime checkedInAt = checkIn.isPresent() ? checkIn.get().getCreatedAt() : null;
            return new AttendeeDetails(attendee.getId(), attendee.getName(), attendee.getEmail(),attendee.getCreatedAt(), checkedInAt);
        }).toList();

        return new AttendeesListResponseDTO(attendeeDetailsList);
    }
}
