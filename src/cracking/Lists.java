package cracking;

public class Lists {
    public static class Node {
        Integer data;
        Node next;

        public Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }

        public String toString() {
            Node p = this;
            StringBuffer sb = new StringBuffer();
            do {
                sb.append(p.data).append(" -> ");
                p = p.next;
            } while (p != null);
            sb.delete(sb.length() - " -> ".length(), sb.length());
            return sb.toString();
        }
    }

    public static class Pair<A, B> {
        private final A key;
        private final B value;

        public Pair(A key, B value) {
            this.key = key;
            this.value = value;
        }

        public static <A, B> Pair<A, B> of(A k, B v) {
            return new Pair<>(k, v);
        }

        public A getKey() {
            return key;
        }

        public B getValue() {
            return value;
        }
    }

    private static Node sumListRev(Node n1, Node n2, Integer rem) {
        Node n3;
        if (n1 != null && n2 != null) {
            n3 = new Node((n1.data + n2.data + rem) % 10, sumListRev(n1.next, n2.next, ((n1.data + n2.data + rem) - (n1.data + n2.data + rem) % 10) / 10));
        } else if (n1 == null && n2 != null) {
            n3 = new Node((n2.data + rem) % 10, sumListRev(null, n2.next, ((n2.data + rem) - (n2.data + rem) % 10) / 10));
        } else if (n1 != null) {
            n3 = new Node((n1.data + rem) % 10, sumListRev(null, n1.next, ((n1.data + rem) - (n1.data + rem) % 10) / 10));
        } else if (rem > 0) {
            n3 = new Node(rem, null);
        } else {
            n3 = null;
        }

        return n3;
    }

    public static Node sumListRev(Node n1, Node n2) {
        return sumListRev(n1, n2, 0);
    }

    private static Pair<Node, Integer> sumListFwdRec(Node n1, Node n2) {
        Node n3;
        Integer rem;
        if (n1 != null && n2 != null) {
            Pair<Node, Integer> prev = sumListFwdRec(n1.next, n2.next);
            Integer v = n1.data + n2.data + prev.getValue();
            n3 = new Node(v % 10, prev.getKey());
            rem = (v - v % 10) / 10;
        } else if (n1 == null && n2 != null) {
            Pair<Node, Integer> prev = sumListFwdRec(null, n2.next);
            Integer v = n2.data + prev.getValue();
            n3 = new Node(v % 10, prev.getKey());
            rem = (v - v % 10) / 10;
        } else if (n1 != null) {
            Pair<Node, Integer> prev = sumListFwdRec(n1.next, null);
            Integer v = n1.data + prev.getValue();
            n3 = new Node(v % 10, prev.getKey());
            rem = (v - v % 10) / 10;
        } else {
            n3 = null;
            rem = 0;
        }

        return Pair.of(n3, rem);
    }

    public static Node sumListFwd(Node n1, Node n2) {
        Pair<Node, Integer> n = sumListFwdRec(n1, n2);
        if (n.getValue() > 0) {
            return new Node(n.getValue(), n.getKey());
        }
        return n.getKey();
    }

    public static Node partition(Node list, int value) {
        Node p = list;
        Node last = null;
        while (p.next != null) {
            if (p.next.data < value) {
                last = p.next;
                p.next = p.next.next;
                last.next = list;
                list = last;
            } else {
                p = p.next;
            }
        }
        last = last == null ? list : last;
        while (last.next != null) last = last.next;
        p = list;
        if (p.data >= value) {
            list = p.next;
            p.next = null;
            last.next = p;
            last = p;
        }
        p = list;
        while (p.next != last) {
            if (p.next.data >= value) {
                Node t = p.next;
                p.next = p.next.next;
                t.next = last.next;
                last.next = t;
            } else {
                p = p.next;
            }
        }
        return list;
    }

    public static void remove(Node c) {
        while (c.next != null) {
            c.data = c.next.data;
            if (c.next.next == null) {
                c.next = null;
            } else {
                c = c.next;
            }
        }
    }

    public static Node findKthToLast(Node list, int k) {
        int len = 0;
        Node p = list;
        while (p != null) {
            len++;
            p = p.next;
        }
        int i = 0;
        p = list;
        while (i < len - k - 1) {
            i++;
            p = p.next;
        }
        return p;
    }

    public static void removeDuplicates(Node list) {
        Node p1 = list;
        Node p2 = p1;
        if (list.next == null) {
            return;
        }
        while (p1.next != null) {
            while (p2.next != null) {
                if (p1.data == p2.next.data) {
                    p2.next = p2.next.next;
                } else {
                    p2 = p2.next;
                }
            }
            p1 = p1.next;
            p2 = p1;
        }
    }
}

