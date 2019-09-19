package com.test;
public class SuperTest{

    public static void main(String[] args) {
        char ch[] = {'H', 'E', 'L'};
        change(ch);
        System.out.println(ch);
    }

    public static void change(char[] ch) {
        ch[0] = 'C';
    }

}

