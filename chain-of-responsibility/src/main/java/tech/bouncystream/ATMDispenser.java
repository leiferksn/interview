package tech.bouncystream;

import java.util.Scanner;

public class ATMDispenser {

    private Dispenser firstDispenser;

    public ATMDispenser() {
        this.firstDispenser = new DispenserOf50s();
        final var d2 = new DispenseOf20s();
        final var d3 = new DispenseOf10s();
        this.firstDispenser.nextDispenser(d2);
        d2.nextDispenser(d3);
    }

    public static void main(String[] args) {

        final var atmDispenser = new ATMDispenser();

        while (true) {
            int amount = 0;
            System.out.println("Enter amount to dispense:");
            final var s = new Scanner(System.in);
            amount = s.nextInt();

            if (amount % 10 != 0) {
                System.out.println("Amount should be in multiple of 10s");
                return;
            }

            atmDispenser.firstDispenser.dispense(new Currency(amount));
        }
    }
}