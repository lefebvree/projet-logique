import model.Expression;

public class Main {
    public static void main(String[] args) {
        Expression f1 = new Expression("p>((p>q)>q)", false, 0);
        System.out.println(f1.toString());
        Expression f2 = new Expression("p>(!q>!(p>q))", false, 0);
        System.out.println(f2.toString());
        Expression f3 = new Expression("(!p>!q)>(q>p)", false, 0);
        System.out.println(f3.toString());
        Expression f4 = new Expression("(p^(p>q)^((p>q)>r))>(p^q^r)", false, 0);
        System.out.println(f4.toString());
    }
}
