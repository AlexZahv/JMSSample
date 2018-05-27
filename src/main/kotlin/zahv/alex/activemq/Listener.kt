package zahv.alex.activemq

import com.google.gson.Gson
import org.springframework.jms.annotation.JmsListener
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.stereotype.Component
import javax.jms.JMSException
import javax.jms.Message
import javax.jms.TextMessage

@Component
class Listener {

    @JmsListener(destination = "inbound.queue")
    @SendTo("outbound.queue")
    @Throws(JMSException::class)
    fun firstStep(jsonMessage: Message): String? {
        val messageData: String
        println("Received message " + jsonMessage)
        var response: String? = null
        if (jsonMessage is TextMessage) {
            messageData = jsonMessage.text
            val map = Gson().fromJson(messageData, Map::class.java)
            response = "Hello " + map["name"]
        }
        return response
    }

    @JmsListener(destination = "outbound.queue")
    @SendTo("result.queue")
    @Throws(JMSException::class)
    fun secondStep(jsonMessage: Message): String? {
        val messageData: String
        println("Received message " + jsonMessage)
        val response: String? = null
        if (jsonMessage is TextMessage) {
            messageData = jsonMessage.text
            println(messageData)
        }
        return response
    }
}