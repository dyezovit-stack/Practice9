import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

class EventLab {
    /*Реалізуйте EventLab.pick(List<Event>, Predicate<Event>).
    Реалізуйте EventLab.labels(List<Event>, Function<Event, String>).
    Реалізуйте EventLab.notifyAll(List<Event>, Consumer<Event>).
    Реалізуйте EventLab.create(Supplier<Event>).*/


    //реалізація
    public static List<Event> pick (List<Event> events, Predicate<Event> predicate){
        return events.stream().filter(predicate).collect(Collectors.toList());
    }

    public static List<String> labels(List<Event> events, Function<Event, String> transformer) {
        return events.stream().map(transformer).collect(Collectors.toList());
    }

    public static void notifyAll(List<Event> events, Consumer<Event> action) {
        events.forEach(action);
    }

    public static Event create(Supplier<Event> supplier) {
        return supplier.get();
    }

    //перетин себто знаходження конфліктів
    public static List<String> findConflicts(List<Event> events) {
        List<String> conflicts = new ArrayList<>();
        for (int i = 0; i < events.size(); i++) {
            for (int j = i + 1; j < events.size(); j++) {
                Event e1 = events.get(i);
                Event e2 = events.get(j);

                if (e1.start.isBefore(e2.end()) && e2.start.isBefore(e1.end())) {
                    conflicts.add("!!! Конфлікт між: " + e1.title + " та " + e2.title);
                }
            }
        }
            return conflicts;
        }
    }
