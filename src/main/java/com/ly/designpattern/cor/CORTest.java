package com.ly.designpattern.cor;

/**
 * @Description:责任链模式  Chain of Responsibility
 * @Date 2018-07-23 15:46
 * @Author ly
 */
public class CORTest {

    public static void main(String[] args) {

        PosAdministration administration = new PosAdministration();
        PosFinance finance = new PosFinance();
        PosManager manager = new PosManager();
        manager.setNextAbstractPosition(administration);
        administration.setNextAbstractPosition(finance);

        Employer employer = new Employer();
        manager.handleMessage(employer);

        Manager manager1 = new Manager();
        manager.handleMessage(manager1);

        Administrator administrator = new Administrator();
        manager.handleMessage(administrator);

    }

}
