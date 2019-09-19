package com.ray.atm;

public class BankAccount {
    private String accountId;//账号
    private double balance;//余额

    public BankAccount(String accountId, double initialBalance) {
        this.accountId=accountId;
        this.balance=initialBalance;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;

    }

    /**
     * 获取当前账户的余额
     * @return
     */
    public double getBalance() {
        return balance;
    }

    //存
    public void deposit(double amount) {
        this.balance+=amount;
    }
    //取
    public void withdraw(double amount) {
        this.balance-=amount;
    }
}
