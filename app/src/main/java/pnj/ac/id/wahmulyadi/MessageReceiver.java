package pnj.ac.id.wahmulyadi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
public class MessageReceiver extends BroadcastReceiver {
    private static MessageListener mListener;
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle data = intent.getExtras();
        Object[] pdus = (Object[]) data.get("pdus");
        for(int i=0; i<pdus.length; i++){
            SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) pdus[i]);
            String message = " Sender : " + smsMessage.getDisplayOriginatingAddress()
                    + "\n Email From: " + smsMessage.getEmailFrom()
                    + "\n Emal Body: " + smsMessage.getEmailBody()
                    + "\n Display message body: " + smsMessage.getDisplayMessageBody()
                    + "\n Time in millisecond: " + smsMessage.getTimestampMillis()
                    + "\n Message: " + smsMessage.getMessageBody();
            mListener.messageReceived(message);
        }
    }
    public static void bindListener(MessageListener listener){
        mListener = listener;
    }
}
