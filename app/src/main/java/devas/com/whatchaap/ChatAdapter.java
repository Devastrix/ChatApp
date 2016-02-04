package devas.com.whatchaap;

/**
 * Created by user on 1/29/2016.
 */

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ChatAdapter extends BaseAdapter {

    private static LayoutInflater inflater = null;
    ArrayList<ChatMessage> chatMessageList;

    public ChatAdapter(Activity activity, ArrayList<ChatMessage> list) {
        chatMessageList = list;
        inflater = (LayoutInflater) activity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return chatMessageList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ChatMessage message = (ChatMessage) chatMessageList.get(position);
        View vi = convertView;
        if (convertView == null)
            vi = inflater.inflate(R.layout.chat_bubble, null);

        TextView msg = (TextView) vi.findViewById(R.id.message_text);
        TextView date = (TextView) vi.findViewById(R.id.date);
        ImageView ack = (ImageView) vi.findViewById(R.id.ack);
        switch (message.ack) {
            case 0:
                // not sent
                ack.setImageResource(R.mipmap.ic_query_builder_grey600_18dp);
                break;
            case 1:
                // sent
                ack.setImageResource(R.mipmap.ic_done_grey600_18dp);
                break;
        }
        msg.setText(message.body);
        date.setText(message.Time+","+message.Date);
        LinearLayout layout = (LinearLayout) vi
                .findViewById(R.id.bubble_layout);
        LinearLayout parent_layout = (LinearLayout) vi
                .findViewById(R.id.bubble_layout_parent);

        // if message is mine then align to right
        if (message.isMine) {
            layout.setBackgroundResource(R.drawable.bubble2);
            parent_layout.setGravity(Gravity.RIGHT);
        }
        // If not mine then align to left
        else {
            layout.setBackgroundResource(R.drawable.bubble1);
            parent_layout.setGravity(Gravity.LEFT);
        }
        msg.setTextColor(Color.BLACK);
        return vi;
    }
    public ChatMessage getPMessage(String id) {
        for(int i = 0; i < chatMessageList.size(); i++) {
            ChatMessage msgi = chatMessageList.get(i);
            if(msgi.msgid.equals(id)) {
                return msgi;
            }
        }
        return  null;
    }

    public void add(ChatMessage object) {
        chatMessageList.add(object);
    }
}