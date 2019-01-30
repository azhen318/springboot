import com.az.rabbitmq.entry.RabbitRouteEnum;
import com.az.rabbitmq.utils.RabbitMqSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.az.rabbitmq"})
public class Applcation implements CommandLineRunner{

    @Autowired
    private RabbitMqSender rabbitMqSender;

    public static void main(String[] args){
        SpringApplication.run(Applcation.class,args);

    }

    @Override
    public void run(String... args) throws Exception {
        rabbitMqSender.sendRabbitmqDirect(RabbitRouteEnum.TESTQUEUE.getCode(),"hello");
    }
}
