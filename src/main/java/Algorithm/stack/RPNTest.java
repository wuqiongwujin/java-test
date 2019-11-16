package Algorithm.stack;

import java.util.Stack;

/**
 * @Description 逆波兰表达式
 * @author Cain
 * @date 2019/11/7
 */
public class RPNTest {

    private static final String ADDITION = "+";
    private static final String SUBTRACTION = "-";
    private static final String MULTIPLICATION = "*";
    private static final String DIVISION = "/";

    private Stack<Integer> stack = new Stack<>();

    public int evalRPN(String[] tokens) {
        if (tokens == null || tokens.length <= 0) {
            return 0;
        }
        for (int i=0; i<tokens.length; i++) {
            String str = tokens[i];
            if (isOperator(str)) {
                int num2 = stack.pop();
                int num1 = stack.pop();
                int r = 0;
                switch (str) {
                    case ADDITION:
                        r = add(num1, num2);
                        break;
                    case SUBTRACTION:
                        r = subtract(num1, num2);
                        break;
                    case MULTIPLICATION:
                        r = multiply(num1, num2);
                        break;
                    case DIVISION:
                        r = divide(num1, num2);
                        break;
                }
                stack.push(r);
            } else {
                stack.push(Integer.valueOf(str));
            }
        }
        return stack.pop();
    }

    private int add(int num1, int num2) {
        return num1 + num2;
    }

    private int subtract(int num1, int num2) {
        return num1 - num2;
    }

    private int multiply(int num1, int num2) {
        return num1 * num2;
    }

    private int divide(int num1, int num2) {
        if (num2 == 0) {
            return 0;
        }
        return num1/num2;
    }

    private boolean isOperator(String str) {
        if (ADDITION.equals(str) || SUBTRACTION.equals(str) || MULTIPLICATION.equals(str) || DIVISION.equals(str)) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        RPNTest test = new RPNTest();
        String[] tokens = new String[]{"4","-2","/","2","-3","-","-"};
        System.out.println(test.evalRPN(tokens));
    }
}
