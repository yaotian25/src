package com.test;

public class Base {
    public static void main(String[] args) {
        Base b=new Derived();
        b.methodOne();

    }



        public void methodOne()
        {
            System.out.print("A");
            methodTwo();
        }

        public void methodTwo()
        {
            System.out.print("B");
        }


    public static class Derived extends Base
    {
        public void methodOne()
        {
            super.methodOne();
            System.out.println("，C的排序");
            System.out.print("C");
        }

        public void methodTwo()
        {
            super.methodTwo();
            System.out.println("，D的排序");
            System.out.print("D");
        }
    }


}
