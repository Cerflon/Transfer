package com.men.util;

import com.men.message.Msg;

public class Calculate {


    public static int execute(Msg msg) {
        if (msg.getVersionProtocol() == 1) {
            return sum(msg.getA(), msg.getB());
        } else if (msg.getVersionProtocol() == 2) {
            return decline(msg.getA(), msg.getB());
        } else if (msg.getVersionProtocol() == 3) {
            return multip(msg.getA(), msg.getB());
        } else {
            return div(msg.getA(), msg.getB());
        }
    }


    private static int sum(int a, int b) {
        int result = (a + b);
        System.out.println("Результат вычислений полученных значений: " + a + b);
        return result;
    }

    private static int decline(int a, int b) {
        int result = (a - b);
        System.out.println("Результат вычислений полученных значений: " + (a - b));
        return result;
    }

    private static int multip (int a, int b) {
        int result = (a*b);
        System.out.println("Результат вычислений полученных значений: " + (a*b));
        return result;
    }

    private static int div (int a, int b) {
        int result = (a/b);
        System.out.println("Результат вычислений полученных значений: " + (a/b));
        return result;
    }


}
