package hellojpa;

public class ValueMain {

    public static void main(String[] args) {

        Integer a = 220;
        Integer b = 220;

        System.out.println("a = " + a);
        System.out.println("b = " + b);

        Address address1 = new Address("city", "street", "123");
        Address address2 = new Address("city", "street", "123");

        System.out.println(address1 == address2);
        System.out.println(address1.equals(address2));

    }
}
