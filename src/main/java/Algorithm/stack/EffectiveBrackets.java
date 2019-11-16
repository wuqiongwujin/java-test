package Algorithm.stack;

import java.util.Stack;

/**
 * @Description 有效的括号字符串
 * @date 2019/11/5
 */
public class EffectiveBrackets {

    private Stack<String> stack = new Stack<String>();

    public boolean isValid(String s) {
        if (s == null) {
            return false;
        }
        if (s.equals("")) return true;
        for (int i=0;i<=s.length()-1;i++) {
            stack.push(s.substring(i,i+1));
        }
        while(!stack.isEmpty()) {
            if (!pop()) return false;
        }
        return true;
    }

    private boolean pop() {
        if (stack.empty()) return false;
        String e = stack.pop();
        if (stack.empty()) return false;
        String next = stack.peek();
        while(!ispair(e, next)) {
            if (!pop()) {
                return false;
            }
            if (stack.isEmpty()) return false;
            next = stack.peek();
        }
        if (stack.isEmpty()) return false;
        stack.pop();
        return true;
    }

    private boolean ispair(String a, String b) {
        if (a.equals("(")) { return b.equals(")"); }
        if (a.equals(")")) { return b.equals("("); }
        if (a.equals("{")) { return b.equals("}"); }
        if (a.equals("}")) {return b.equals("{"); }
        if (a.equals("[")) { return b.equals("]"); }
        if (a.equals("]")) { return b.equals("["); }
        return false;
    }

    public static void main(String[] args) {
        EffectiveBrackets e = new EffectiveBrackets();
        System.out.println(e.isValid("([]"));
    }
}
