import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {
        // демонстрація об'єктів
        ZonedDateTime now = ZonedDateTime.now();
        Event event1 = new Event("My favourite lecture ASD", now, 80, "EET", "1-310");
        Event event2 = new Event("Architectures", now.plusHours(2), 80, "EET", "1-225");
        Event event3 = new Event("Home", now.plusHours(7), 800, "EET", "home");

        System.out.println("Подія: " + event1.label());
        System.out.println("Завершується о: " + event1.end());
        System.out.println("----------------------------------\n");

        // task2
        List<Event> schedule = new ArrayList<>();
        ZoneId kyiv = ZoneId.of("Europe/Kyiv");
        ZonedDateTime today = ZonedDateTime.now(kyiv);

        // Створення через EventLab.create
        schedule.add(EventLab.create(() ->
                new Event("Лекція", today.withHour(10).withMinute(0), 90, "Kyiv", "Education")));

        schedule.add(EventLab.create(() ->
                new Event("Пончик брейк (данс)", today.withHour(11).withMinute(0), 30, "Kyiv", "General"))); // Конфлікт!

        schedule.add(EventLab.create(() ->
                new Event("Зальчик", today.withHour(18).withMinute(0), 60, "Kyiv", "Health")));

        schedule.add(EventLab.create(() ->
                new Event("малювання з натури", today.withHour(9).withMinute(0), 45, "Kyiv", "Education")));

        // Предикат для ранкових подій
        Predicate<Event> isMorning = e -> e.start.getHour() < 12;

        // ранкові та не дженерал
        Predicate<Event> isGeneral = e -> e.track.equalsIgnoreCase("General");
        Predicate<Event> filteredRules = isMorning.and(isGeneral.negate());

        //май ворк демонстрейшн :3
        System.out.println(" --- Фільтрація (Ранкові не-General події) ---");
        List<Event> morningStudy = EventLab.pick(schedule, filteredRules);
        morningStudy.forEach(e -> System.out.println(e.label()));

        System.out.println("\n--- Список лейблів ---");
        List<String> eventLabels = EventLab.labels(schedule, Event::label);
        eventLabels.forEach(System.out::println);

        System.out.println("\n--- Сповіщення ---");
        EventLab.notifyAll(schedule, e ->
                System.out.println("Reminder: " + e.title + " starts at " + e.start.getHour() + ":" + String.format("%02d", e.start.getMinute())));

        System.out.println("\n--- Перевірка накладок ---");
        List<String> conflicts = EventLab.findConflicts(schedule);
        if (conflicts.isEmpty()) {
            System.out.println("Конфліктів немає");
        } else {
            conflicts.forEach(System.out::println);
        }

        // task 3
        LambdaRefactorLab.sortAnonymous(schedule);
        System.out.println("\nПісля sortAnonymous (за часом):");
        schedule.forEach(e -> System.out.println(e.start.toLocalTime() + " - " + e.title));

        // сорт лямбда
        LambdaRefactorLab.sortLambda(schedule);
        System.out.println("\nПісля sortLambda:"); //порядок не зміниться
        schedule.forEach(e -> System.out.println(e.start.toLocalTime() + " - " + e.title));

        // сорт метод референс
        LambdaRefactorLab.sortMethodRef(schedule);
        System.out.println("\nПісля sortMethodRef (фінальний список):");
        schedule.forEach(e -> System.out.println(e.start.toLocalTime() + " - " + e.title));

        // task 4
        System.out.println("\n--- Task 4: Java Time ---");
        if (!schedule.isEmpty()) {
            Event first = schedule.get(0);
            Event second = schedule.get(1);

            System.out.println("Instant першої події (UTC): " + DateTimeLab.toInstant(first));

            long minutes = DateTimeLab.minutesBetween(first, second);
            System.out.println("Різниця між першими двома подіями: " + minutes + " хв");

            String tokyoZone = "Asia/Tokyo";
            System.out.println("Перша подія за часом у Токіо: " + DateTimeLab.startInZone(first, tokyoZone).toLocalTime());
        }
    }
}