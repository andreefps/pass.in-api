package andreefps.com.pass.in.repositories;

import andreefps.com.pass.in.domain.event.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, String> {

}
