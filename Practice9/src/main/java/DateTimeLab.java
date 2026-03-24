import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class DateTimeLab {

    // 1. Реалізація toInstant з урахуванням ZoneId (використовуємо вбудований метод)
    public static Instant toInstant(Event e) {
        return e.start.toInstant();
    }

    // 2. Реалізація minutesBetween через Duration
    public static long minutesBetween(Event a, Event b) {
        return Math.abs(Duration.between(a.start, b.start).toMinutes());
    }

    // 3. Реалізація startInZone (переведення в іншу зону)
    public static ZonedDateTime startInZone(Event e, String zone) {
        return e.start.withZoneSameInstant(ZoneId.of(zone));
    }
}