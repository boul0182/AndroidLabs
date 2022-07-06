package com.cst2335.boul0182;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.textclassifier.ConversationActions;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ChatRoomActivity extends AppCompatActivity {

    Button send;
    EditText edit;
    Button receive;
    ArrayList<ConversationActions.Message> messages = new ArrayList<>();
    MyListAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);

        //is chatText the correct parameter ???
        ListView myList = findViewById(R.id.chatText);

        myList.setAdapter( myAdapter = new MyListAdapter());

        send = findViewById(R.id.send);
        send.setOnClickListener( click -> {
            messages.add(R.id.textViewSend); //how to bring the message?
            myList.notifyDatasetChanged();
        });

        receive = findViewById(R.id.receive);
        receive.setOnClickListener(click -> {
            messages.add(R.id.textViewReceive); //how to bring the message?
            myList.notifyDatasetChanged();
        });
        }

        myList.setOnItemLongClickListener(); //???

    }

    class MyListAdapter extends BaseAdapter {
        private TextView chatMsg;
        private ImageButton avatar;
        private Context context;
        private ArrayList<String> messages = new ArrayList<>();

        public void setAvatar(ImageButton avatar) {
            this.avatar = avatar;
        }

        @Override
        public int getCount() {
            return messages.size();
        }

        @Override
        public Object getItem(int position) {
            return "This is row " + messages.get(position);
        }

        @Override
        public long getItemId(int position) {
            return (long) position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Message newMessage = (Message) getItem(position);
            LayoutInflater inflater = getLayoutInflater();

            //make a new row:
            View row = convertView;

            //determine the type of message
            if (newMessage.sent) {
                row = inflater.inflate(R.layout.send_message, parent, false);
                TextView chatMsg = row.findViewById(R.id.textViewSend);
                chatMsg.setText(newMessage.messageText.toString());
                ImageButton avatar = row.findViewById((R.id.imageButtonSend));
                //set avatar for message ????
                setAvatar(newMessage.getAvatar());
            } else {
                row = inflater.inflate(R.layout.receive_message, parent, false);
                TextView chatMsg = row.findViewById(R.id.textViewReceive);
                chatMsg.setText(newMessage.messageText.toString());
                ImageButton avatar = row.findViewById((R.id.imageButtonReceive));
                //set avatar for message ????
                setAvatar(newMessage.getAvatar());
            }

            //return it to be put in the table
            return row;
        }

        private class Message {
            public boolean sent;
            TextView messageText;
            ImageButton avatar;

            public Message(boolean sent, TextView messageText, ImageButton avatar) {
                super();
                this.messageText = messageText;
                this.avatar = avatar);
            }

            public void setSent(boolean status) {
                this.sent = status;
            }

            public void setMessageText(TextView text) {
                this.messageText = text;
            }

            public void setAvatar(ImageButton avatar) {
                this.avatar = avatar;
            }

            public boolean getSent() {
                return sent;
            }

            public TextView getMessageText() {
                return messageText;
            }

            public ImageButton getAvatar() {
                return avatar;
            }

        }


    }


}