package com.example.helloworld;

import android.content.Intent;

import java.util.Stack;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

public class calculator {
    static String dc="";//返回计算值
    static double π=Math.PI;
    static int h=0;
   // Pattern pattern=Pattern.compile(regEx);
    public static String d (String s){
        Stack<String> stack=new Stack<>();//数字栈1
        Stack<String> stack1=new Stack<>();//符号栈
        Stack<String> stack2=new Stack<>();//数字栈2
        String e="";
            StringTokenizer stringTokenizer=new StringTokenizer(s,"+-*/()^sc%",true);
           while(stringTokenizer.hasMoreTokens()){
                String s1=stringTokenizer.nextToken().trim();
                if(isNum(s1)&&stack.empty()){
                    stack.push(s1);
                }
                else if(isNum(s1)&&stack2.empty()){
                    stack2.push(s1);
                }
                else if(!isNum(s1)&&!stack1.empty()) {
                    if(compare(s1)>compare(stack1.peek())) {
                        if (!s1.equals(")") && !stack2.empty()) {//防止优先级高符号后还有优先级更高的，将优先级高的符号一块绑定递归运算
                            String r = stack2.pop() + s1;
                            while (stringTokenizer.hasMoreTokens()) {
                                int i=0;
                                int j=0;
                                String s2=stringTokenizer.nextToken().trim();
                                if(s2.equals("(")){
                                    i=i+1;
                                }
                                if(s2.equals(")")){
                                    j=j+1;
                                }
                                if(!isNum(s2)&&compare(s2)<compare(stack1.peek())&&i==j){
                                    r=stack.pop()+stack1.pop()+r;
                                    r=d(r);
                                    stack.push(r);
                                    stack1.push(s2);
                                    h=1;
                                    break;
                                }
                                r = r + s2;
                            }
                            if(h==0){
                                r = d(r);
                                stack2.push(r);
                            }
                            h=0;
                        }
                        else{
                            double b;
                            double d;
                            switch (s1) {
                                case "(":
                                    String s2 = "";
                                    int i = 1;
                                    int j = 0;
                                    while (stringTokenizer.hasMoreTokens()) {
                                        String s3 = stringTokenizer.nextToken().trim();
                                        s2 = s2 + s3;
                                        if (s3.equals("(")) {
                                            i = i + 1;
                                        }
                                        if (s3.equals(")")) {
                                            j = j + 1;
                                        }
                                        if (i == j) {
                                            break;
                                        }
                                    }
                                    System.out.println(i);
                                    System.out.println(j);
                                    System.out.println(s2);
                                    String rs = d(s2);
                                    stack2.push(rs);
                                    break;
                                case ")":
                                   /* double a = Double.valueOf(stack.pop());
                                    double c = Double.valueOf(stack2.pop());
                                    switch (stack1.peek()) {
                                        case "*":
                                            a = a * c;
                                            dc = String.valueOf(a);
                                            stack.push(dc);
                                            break;
                                        case "+":
                                            a = a + c;
                                            dc = String.valueOf(a);
                                            stack.push(dc);
                                            break;
                                        case "-":
                                            a = a - c;
                                            dc = String.valueOf(a);
                                            stack.push(dc);
                                            break;
                                        case "^":
                                            a = Math.pow(a, c);
                                            dc = String.valueOf(a);
                                            stack.push(dc);
                                            break;
                                        case "/":
                                            a=a/c;
                                            dc=String.valueOf(a);
                                            stack.push(dc);
                                        case"%":
                                            a=a%c;
                                            dc=String.valueOf(a);
                                            stack.push(dc);
                                    }
                                    stack1.pop();//释放右括号
                                    break;*/
                                   continue;
                                case"s":
                                    String s4=s1;
                                    while(stringTokenizer.hasMoreTokens()){
                                        s4=s4+stringTokenizer.nextToken().trim();
                                    }
                                    String dd=d(s4);
                                    stack2.push(dd);
                                    break;
                                /*case "*":
                                    e = stringTokenizer.nextToken();
                                    if (!isNum(e)) {//防止符号后是其他符号，比如括号，sin，cos 1+2*（2*3） 可以舍去，看sin算法是否支持
                                        while (stringTokenizer.hasMoreTokens()) {
                                            e = e + stringTokenizer.nextToken();
                                        }
                                        e = d(e);
                                        double f = Double.valueOf(e);
                                        f = Double.valueOf(stack2.pop()) * f;
                                        System.out.println(f);
                                        stack2.push(String.valueOf(f));
                                    }
                                    else {
                                        b = Double.valueOf(e);
                                        d = Double.valueOf(stack2.pop());
                                        d = d * b;
                                        dc = String.valueOf(d);
                                        stack2.push(dc);
                                        System.out.println(d);
                                    }
                                    break;
                                case "/":
                                    e = stringTokenizer.nextToken();
                                    if (!isNum(e)) {
                                        while (stringTokenizer.hasMoreTokens()) {
                                            e = e + stringTokenizer.nextToken();
                                        }
                                        e = d(e);
                                        double f = Double.valueOf(e);
                                        f = Double.valueOf(stack2.pop()) / f;
                                        System.out.println(f);
                                        stack2.push(String.valueOf(f));
                                    }
                                    else {
                                        b = Double.valueOf(e);
                                        d = Double.valueOf(stack2.pop());
                                            d = d / b;
                                            dc = String.valueOf(d);
                                            stack2.push(dc);
                                            System.out.println(d);
                                    }
                                    break;
                                case "^":
                                    e = stringTokenizer.nextToken();
                                    if (!isNum(e)) {
                                        while (stringTokenizer.hasMoreTokens()) {
                                            e = e + stringTokenizer.nextToken();
                                        }
                                        e = d(e);
                                        double f = Double.valueOf(e);
                                        f = Math.pow(Double.valueOf(stack2.pop()), f);
                                        System.out.println(f);
                                        stack2.push(String.valueOf(f));
                                    }
                                    else {
                                        b = Double.valueOf(e);
                                        d = Double.valueOf(stack2.pop());
                                        d = Math.pow(d, b);
                                        dc = String.valueOf(d);
                                        stack2.push(dc);
                                    }
                                    break;
                                case"%":
                                    e=stringTokenizer.nextToken();
                                    b=Double.valueOf(e);
                                    d=Double.valueOf(stack2.pop());
                                    d=d%b;
                                    dc=String.valueOf(d);
                                    stack2.push(dc);*/
                                 }
                            }
                        }
                    else if(compare(s1)<compare(stack1.peek())||compare(s1)==compare(stack1.peek())){
                       switch (stack1.peek()){
                           case"(":
                               String s2="";
                               s2=stack.pop()+s1;
                               int i=1;
                               int j=0;
                               while(stringTokenizer.hasMoreTokens()){
                                   String s3=stringTokenizer.nextToken();
                                   s2=s2+s3;
                                   if(s3.equals("(")){
                                       i=i+1;
                                   }
                                   if(s3.equals(")")){
                                       j=j+1;
                                   }
                                   if(i==j){
                                       break;
                                   }
                               }
                               String rs=d(s2);
                               stack.push(rs);
                               stack1.pop();//释放第一个左括号
                               break;
                           case "*":
                               dc=String.valueOf(Double.valueOf(stack.pop())*Double.valueOf(stack2.pop()));
                               stack.push(dc);
                               stack1.pop();
                               stack1.push(s1);
                               break;
                           case "+":
                               dc=String.valueOf(Double.valueOf(stack.pop())+Double.valueOf(stack2.pop()));
                               stack.push(dc);
                               stack1.pop();
                               stack1.push(s1);
                               break;
                           case"^":
                               dc=String.valueOf(Math.pow(Double.valueOf(stack.pop()),Double.valueOf(stack2.pop())));
                               stack.push(dc);
                               stack1.pop();
                               stack1.push(s1);
                               break;
                           case "-":
                               dc=String.valueOf(Double.valueOf(stack.pop()) - Double.valueOf(stack2.pop()));
                               stack.push(dc);
                               stack1.pop();
                               stack1.push(s1);
                               break;
                           case "/":
                               dc=String.valueOf(Double.valueOf(stack.pop()) / Double.valueOf(stack2.pop()));
                               stack.push(dc);
                               stack1.pop();
                               stack1.push(s1);
                               break;
                           case"%":
                               dc=String.valueOf(Double.valueOf(stack.pop())%Double.valueOf(stack2.pop()));
                               stack.push(dc);
                               stack1.pop();
                               stack1.push(s1);
                               break;
                           case "s":
                               if(!stack2.empty()) {
                                   dc = String.valueOf(Math.sin(Double.valueOf(stack2.pop()) / 180 * π));
                                   stack.push(dc);
                                   stack1.pop();
                                   stack1.push(s1);
                               }
                               else if(!stack.empty()){
                                   dc = String.valueOf(Math.sin(Double.valueOf(stack.pop()) / 180 * π));
                                   stack.push(dc);
                                   stack1.pop();
                                   stack1.push(s1);
                               }
                               break;

                       }
                    }
                }
               else if(!isNum(s1)){
                    stack1.push(s1);
                }
            }
       if(!stack1.empty()) {
           double b ;
           double d ;
           switch (stack1.peek()) {
               case "+":
                   b = Double.valueOf(stack.pop());
                   d = Double.valueOf(stack2.pop());
                   d = d + b;
                   dc = String.valueOf(d);
                   stack.push(dc);
                   break;
               case "*":
                   b = Double.valueOf(stack.pop());
                   d = Double.valueOf(stack2.pop());
                   d=d*b;
                   dc=String.valueOf(d);
                   stack.push(dc);
                   break;
               case "-":
                   b = Double.valueOf(stack.pop());
                   d = Double.valueOf(stack2.pop());
                   b=b-d;
                   dc=String.valueOf(b);
                   stack.push(dc);
                   break;
               case "/":
                   b = Double.valueOf(stack.pop());
                   d = Double.valueOf(stack2.pop());
                       b = b / d;
                       dc = String.valueOf(b);
                       stack.push(dc);
                   break;
               case "^":
                   b = Double.valueOf(stack.pop());
                   d = Double.valueOf(stack2.pop());
                   b=Math.pow(b,d);
                   dc=String.valueOf(b);
                   stack.push(dc);
                   break;
               case "s":
                   if(!stack.empty()) {
                       b = Double.valueOf(stack.pop());
                       b=b/180;
                       b=Math.sin(π*b);
                       System.out.println(b);
                       dc=String.valueOf(b);
                       stack.push(dc);
                   }
                  else if(!stack2.empty()){
                       d = Double.valueOf(stack2.pop());
                       d=d/180;
                       d=Math.sin(π*d);
                       System.out.println(d);
                       dc=String.valueOf(d);
                       stack.push(dc);
                   }
                   break;
               case"c":
                   b=Double.valueOf(stack.pop());
                   b=b/180;
                   b=Math.cos(π*b);
                   dc=String.valueOf(b);
                   stack.push(dc);
                   break;
               case"%":
                   b=Double.valueOf(stack.pop());
                   d=Double.valueOf(stack2.pop());
                   b=b%d;
                   dc=String.valueOf(b);
                   stack.push(dc);
                   break;
           }
       }
           System.out.println(dc);
        return dc;

    }
    private static boolean isNum(String str) {
        String numRegex = "^\\d+(\\.\\d+)?$";   //数字的正则表达式
        return Pattern.matches(numRegex, str);
    }
    public static int compare(String str){
        switch (str){
            case "(":
            case ")":
                return 5;
            case "^":
                return 4;
            case "s":
            case"c":
                return 3;
            case "*":
            case "/":
            case "%":
                return 2;
            case"+":
            case"-":
                return 1;
            default:
                return 0;

        }
    }
}

