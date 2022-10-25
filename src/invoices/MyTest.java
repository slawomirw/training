package invoices;

import java.util.*;

public class MyTest {
    public static void main(String[] args) {
        List<String> list = new ArrayList<String>() {{
            add("2021040200231110,RN20141945,C1");
            add("2021040200231112,RN20141945,C1");
            add("2021040200231110,RN20141952,C2");
            add("2021040200231120,RN20141963,C2");
            add("2021040200231130,RN20141973,C3");
            add("2021040200231130,RN20141972,C4");
            add("2021040200231111,RN20141945,C5");
            add("2021040200231133,RN20141973,C3");
            add(",PR20141973,C3");
            add(",PR20141977,C6");
        }};

        Map<String, List<String[]>> agg = new HashMap<>();

        list.stream().map(s->s.split(",")).filter(s->s[0].length()>0)
                .forEach(s -> {
                    if(!agg.containsKey(s[1])) {
                        agg.put(s[1], new ArrayList<>());
                    }
                    agg.get(s[1]).add(s);
                });
        agg.forEach((key, value) -> {
            System.out.println(key);
            value.forEach(i -> {
                System.out.print("     ");
                System.out.print("INV" + i[0].substring(i[0].length() - 9));
                System.out.print("\t");
                System.out.println(i[2]);
            });
        });
    }
}
