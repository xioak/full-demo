import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by xioamac on 2017/6/10.
 */
public class Provider {

    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                new String[]{"dubbo-provider.xml"});
        context.start();

        System.out.println("启动成功");

        System.in.read(); // 按任意键退出
    }

}
