package zahv.alex.activemq

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class ActivemqApplication

fun main(args: Array<String>) {
    SpringApplication.run(ActivemqApplication::class.java, *args)
}
