public class Tools {
    public static void print(String s) {
        System.out.println(s);
    }

    public static void print(double s) {
        System.out.println(s);
    }

    public static void print(int s) {
        System.out.println(s);
    }

    public static void print(char s) {
        System.out.println(s);
    }

    public static void print(Object o) {
        System.out.println(o);
    }

    public static int random(int min, int max) {
        return ((int) (Math.random() * (max + 1))) + min;
    }
}