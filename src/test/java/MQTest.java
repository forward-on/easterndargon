import com.ly.rabbitmq.Producer;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author : ly.
 * @Date : 2018/5/8.
 */
public class MQTest {

    private Logger logger = LoggerFactory.getLogger(MQTest.class);

    private ApplicationContext context = null;

    @Before
    public void setUp() throws Exception {
        context = new ClassPathXmlApplicationContext("/context/application-main.xml");
    }

    @Test
    public void should_send_a_amq_message() throws Exception {
        Producer messageProducer = (Producer) context.getBean("producer");
        int a = 10;
        while (a > 0) {
            messageProducer.sendMessage("Hello, I am amq sender num :" + a--);
            try {
                //暂停一下，好让消息消费者去取消息打印出来
//                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

}
