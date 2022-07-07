package com.cst2335.boul0182;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
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
    Button receive;
    ArrayList<ChatRoomActivity.Message> messages = new ArrayList<>();
    MyListAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);

        ListView myList = findViewById(R.id.chatText);
        myList.setAdapter(myAdapter = new MyListAdapter());
        EditText messageText = findViewById(R.id.enterMessage);

        send = findViewById(R.id.send);
        send.setOnClickListener(click -> {
            String msgText = messageText.getText().toString();
            Message sendMsg = new Message(true, msgText);
            messages.add(sendMsg);
            messageText.setText("");
            myAdapter.notifyDataSetChanged();
        });

        receive = findViewById(R.id.receive);
        receive.setOnClickListener(click -> {
            String msgText = messageText.getText().toString();

            Message receiveMsg = new Message(false, msgText);
            messages.add(receiveMsg);
            messageText.setText("");
            myAdapter.notifyDataSetChanged();
        });

        myList.setOnItemLongClickListener((parent, view, position, id) -> {

            AlertDialog.Builder alertMsg = new AlertDialog.Builder(this);
            alertMsg.setTitle("Are you sure you want to delete this message?")

                    .setMessage("This will delete the message: " + position)

                    .setPositiveButton("DELETE", (click, arg) -> {
                        messages.remove(position);
                        myAdapter.notifyDataSetChanged();
                    })
                    .setNegativeButton("CANCEL", (click, arg) -> {})

            .create().show();

            return true;
        });

    }

    class MyListAdapter extends BaseAdapter {
        private TextView chatMsg;
        private ImageButton avatar;
        private Context context;


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
            LayoutInflater inflater = getLayoutInflater();

            //make a new row:
            View row = convertView;

            //determine the type of message
            if (messages.get(position).getSent() == true) {
                row = inflater.inflate(R.layout.send_message, parent, false);
                TextView chatMsg = row.findViewById(R.id.textViewSend);
                chatMsg.setText(messages.get(position).getMessageText());
            } else {
                row = inflater.inflate(R.layout.receive_message, parent, false);
                TextView chatMsg = row.findViewById(R.id.textViewReceive);
                chatMsg.setText(messages.get(position).getMessageText());
            }

            //return it to be put in the table
            return row;
        }

    }

        private class Message {
            public boolean sent;
            String messageText;

            public Message(boolean sent, String messageText) {
                this.messageText = messageText;
                this.sent=sent;
            }

            public void setSent(boolean status) {
                this.sent = status;
            }

            public void setMessageText(String text) {
                this.messageText = text;
            }

            public boolean getSent() {
                return sent;
            }

            public String getMessageText() {
                return messageText;
            }

        }





}