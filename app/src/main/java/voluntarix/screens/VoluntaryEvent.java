package voluntarix.screens;

import java.util.List;

public class VoluntaryEvent {
    String title, description, publisher;
    int day, month, year;
    List<String> tags;

    public VoluntaryEvent(String title, String description, String publisher, List<String> tags, int day, int month, int year) {
        this.title = title;
        this.description = description;
        this.publisher = publisher;
        this.tags = tags;
        this.day = day;
        this.month = month;
        this.year = year;
    }
}
