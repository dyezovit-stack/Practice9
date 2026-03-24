import java.util.Comparator;
import java.util.List;

public class LambdaRefactorLab {

    // сортвання через анонімний клас
    public static void sortAnonymous(List<Event> events) {
        events.sort(new Comparator<Event>() {
            @Override
            public int compare(Event e1, Event e2) {
                return e1.start.compareTo(e2.start);
            }
        });
    }

    // сорт бай лямбда
    public static void sortLambda(List<Event> events) {
        events.sort((e1, e2) -> e1.start.compareTo(e2.start));
    }

    // сорт бай метод референс
    // використання гетера старт, що посилається на тип який реалізує комперебл
    public static void sortMethodRef(List<Event> events) {
        events.sort(Comparator.comparing(e -> e.start));
    }
}