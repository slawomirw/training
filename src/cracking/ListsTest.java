package cracking;

import java.util.List;

public class ListsTest {
    public static void main(String[] args) {
        addFwd();
        addRev();
    }

    static void addRev() {
        Lists.Node n1 = new Lists.Node(9, new Lists.Node(2, new Lists.Node(3, null)));
        Lists.Node n2 = new Lists.Node(1, new Lists.Node(2, new Lists.Node(3, null)));

        System.out.println(Lists.sumListRev(n1, n2));
    }

    static void addFwd() {
        Lists.Node n1 = new Lists.Node(9, new Lists.Node(2, new Lists.Node(3, null)));
        Lists.Node n2 = new Lists.Node(1, new Lists.Node(2, new Lists.Node(3, null)));

        System.out.println(Lists.sumListFwd(n1, n2));
    }

    static void partition() {
        Lists.Node list = new Lists.Node(1, new Lists.Node(2, new Lists.Node(3, new Lists.Node(4, new Lists.Node(2, new Lists.Node(1, new Lists.Node(2, new Lists.Node(5, null))))))));
        System.out.println(list);
        //list = Lists.findKthToLast(list, 3);
        //Lists.remove(list.next);
        list = Lists.partition(list, 3);
        System.out.println(list);
    }
}
