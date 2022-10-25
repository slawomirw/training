package expressioneval;

import java.util.Arrays;

public class MyTest {

    public static void main(String[] args) {
        System.out.println(eval("3 +2*2"));
    }

    private static String getToken(String expression) {
        String trim = expression.trim();
        if (trim.length() == 0) {
            return "";
        }
        return trim.contains(" ") ? trim.substring(0, trim.indexOf(" ")) : trim;
    }

    private static int ev(int n1, String op, String expression) {
        switch (op) {
            case "":
                return n1;
            case "*":
            case "/":
                String a2 = getToken(expression);
                int n2 = Integer.parseInt(a2);
                expression = expression.substring(a2.length());
                return op.equals("*") ?
                        evalSpace((n1 * n2) + expression)
                        : evalSpace((n1 / n2) + expression);
            default:
                return op.equals("+") ?
                        n1 + evalSpace(expression)
                        : n1 - evalSpace(expression);
        }
    }

    public static int eval(String expression) {
        StringBuffer sb = new StringBuffer();
        for(int i=0; i<expression.length(); i++) {
            if (expression.charAt(i) >= '0' && expression.charAt(i) <= '9' || expression.charAt(i) == ' ') {
                sb.append(expression.charAt(i));
            } else {
                sb.append(" ").append(expression.charAt(i)).append(" ");
            }
        }
        expression = sb.toString();
        sb.delete(0, sb.length());
        Arrays.stream(expression.split("\\s+")).forEach(t -> sb.append(t).append(" "));
        return evalSpace(sb.toString().trim());
    }

    public static int evalSpace(String expression) {
        if (expression.trim().length() > 0) {
            String a1 = getToken(expression);
            expression = expression.substring(Math.min(a1.length(), expression.length())).trim();
            String op = getToken(expression);
            expression = expression.substring(Math.min(1, expression.length())).trim();
            return ev(Integer.parseInt(a1), op, expression);
        }
        return 0;
    }
}
