import java.util.Random;

public class Main {
    public static Random random = new Random();

    public static void main(String[] args) {

        BST<Integer, String> bst = new BST<>();

        bst.put(50, "A");
        bst.put(30, "AA");
        bst.put(70, "AAA");
        bst.put(20, "AAAA");
        bst.put(40, "AAAAA");
        bst.put(60, "AAAAAA");
        bst.put(80, "AAAAAAA");

        System.out.println(bst.get(40));
        System.out.println(bst.get(60));

        bst.delete(50);

        System.out.println(bst.get(50));
        System.out.println(bst.get(70));


        MyHashTable<MyTestingClass , Person> table =  new MyHashTable<MyTestingClass , Person>();

        table.put(new MyTestingClass("abc") , new Person("abc" , "def"));

        for(int i = 0; i < 10000000; i++) {
            table.put(new MyTestingClass(generateRandomString(5)), new Person(generateRandomString(5), generateRandomString(5)));
        }
    }

    public static String generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder result = new StringBuilder();
        for(int i = 0 ; i < length ; i++) {
            result.append(characters.charAt(random.nextInt(characters.length())));
        }
        return result.toString();
    }

}