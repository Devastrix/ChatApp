package devas.com.whatchaap;

/**
 * Created by user on 1/29/2016.
 */

import java.util.Random;

public class ChatMessage {

    public String body, sender, receiver, senderName;
    public String Date, Time;
    public String msgid;
    public int ack;
    public boolean isMine;// Did I send the message.

    public ChatMessage(String Sender, String Receiver, String messageString,
                       String ID, boolean isMINE) {
        body = messageString;
        isMine = isMINE;
        sender = Sender;
        msgid = ID;
        receiver = Receiver;
        senderName = sender;
       // ack = 0;
        /*
        0 - not sent
        1 - sent
        2 - received
        3 - read
         */
    }

    public void setMsgID() {

        msgid += "-" + String.format("%02d", new Random().nextInt(100));
        ;
    }
}