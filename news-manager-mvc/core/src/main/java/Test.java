import java.lang.ref.WeakReference;
import java.util.Stack;

/**
 * Created by xioa on 2016/8/6.
 */
public class Test {

    public static void  main(String[] args) {


        Integer h = 1;

        System.out.println(Test.say(2));

        Test test = new Test();

        Test test1 = test;



    }


    public static int say(int r) {

        int result = 0;

        switch (r) {

            case 1:
                result = result + r;
                System.out.println(result +"     C1");

            case 2:
                result = result + r * 2;
                System.out.println(result+"  C2");
                break;

            case 3:
                result = result + r * 3;
                System.out.println(result+"  C3");

            case 4:
                result = result + r * 4;
                System.out.println(result+"  C4");

        }

        Stack stack = new Stack();

        stack.empty();

        return result;

    }


}
