import java.time.Instant;
import java.time.ZonedDateTime;

class Event {
    //поля
    String title;
    ZonedDateTime start;
    int durationMinutes;
    String zone;
    String track;

    public Event(String title, ZonedDateTime start, int durationMinutes, String zone, String track) {
        this.title = title;
        this.start = start;
        this.durationMinutes = durationMinutes;
        this.zone = zone;
        this.track = track;
    }

    public ZonedDateTime end(){
        return start.plusMinutes(durationMinutes);
    }

    public String label(){
        return title + " , "+track;
    }


}
