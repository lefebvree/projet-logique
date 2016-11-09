import model.Expression3;

public class Main {
    public static void main(String[] args) {
        Expression3 f1 = new Expression3("p>((p>q)>q)", false, 0);
        System.out.println(f1.toString());
        Expression3 f2 = new Expression3("p>(!q>!(p>q))", false, 0);
        System.out.println(f2.toString());
        Expression3 f3 = new Expression3("(!p>!q)>(q>p)", false, 0);
        System.out.println(f3.toString());
        Expression3 f4 = new Expression3("(p^(p>q)^((p>q)>r))>(p^q^r)", false, 0);
        System.out.println(f4.toString());
    }
}
