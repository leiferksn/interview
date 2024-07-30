package tech.bouncystream;


public class PrototypeExample {
    public static void main(String[] args) throws CloneNotSupportedException {
        final var employees = new Employees();
        employees.loadData();

        //Use the clone method to get the Employee object
        final var newEmps = (Employees) employees.clone();
        final var anotherEmps = (Employees) employees.clone();
        final var list = newEmps.getEmpList();
        list.add("John");
        final var list1 = anotherEmps.getEmpList();
        list1.remove("Pankaj");

        System.out.println("original employees list: " + employees.getEmpList());
        System.out.println("new employees list: " + list);
        System.out.println("another employees list: " + list1);
    }
}