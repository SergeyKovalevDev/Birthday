import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    private final DateTimeFormatter datePattern = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public static void main(String[] args) {
        String[][] stringArray = {
                {"Анна", "Иванова", "12"},
                {"Иван", "Сидоров", "21"},
                {"Михаил", "Носков", "54"},
                {"Сергей", "Петросян", "18"},
                {"Людмила", "19"},
                {"Светлана", "Мальцева"},
                {"Ольга", "Фролова", "45"},
                {"Федор", "Бондарчук", "10"},
                {"Максим", "Перепелица", "99", "менеджер"},
                {"Валерий"}
        };
        new Main().taskStarter(stringArray);
    }

    private void taskStarter(String[][] stringArray) {
        Map<String, LocalDate> map = getMap(stringArray);

        Stream.of(map)
                .forEach(System.out::println);
    }

    private Map<String, LocalDate> getMap(String[][] stringArray) {
        return Stream.of(stringArray)
                .filter(strings -> strings.length >= 2)
                .map(strings -> {
                    if (strings.length >= 3) return new String[]{strings[0], strings[1], strings[2]};
                    else {
                        if (isNumber(strings[1])) return new String[]{strings[0], "", strings[1]};
                        else return new String[]{strings[0], strings[1], "-1"};
                    }
                })
                .filter(strings -> Integer.parseInt(strings[2]) >= 18)
                .map(strings -> new String[]{strings[1] + (strings[1].equals("") ? "" : " ") + strings[0], strings[2]})
                .collect(Collectors.toMap(strings -> strings[0], strings -> LocalDate.now().minusYears(Integer.parseInt(strings[1]))));
    }

    private boolean isNumber(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
