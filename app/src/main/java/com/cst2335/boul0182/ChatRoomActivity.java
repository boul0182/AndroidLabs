package com.cst2335.boul0182;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ChatRoomActivity extends AppCompatActivity {

    //Create an OpenHelper to store data:
    MyOpenHelper myOpener;
    SQLiteDatabase conversationDatabase;
    Button send;
    Button receive;
    ArrayList<ChatRoomActivity.Message> messages = new ArrayList<>();
    MyListAdapter myAdapter;
    Cursor results;
    int idIndex;
    int messageIndex;
    int sOrRIndex;
    String message;
    String TAG = "ChatRoomActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);

        //initialize MyOpenHelper in onCreate
        myOpener = new MyOpenHelper( this );
        //open the database:
        conversationDatabase = myOpener.getWritableDatabase();

        //load from the database:
        results = conversationDatabase.rawQuery( "Select * from " + MyOpenHelper.TABLE_NAME + ";", null ); //no arguments to the query

        //Convert column names to indices:
        idIndex = results.getColumnIndex( MyOpenHelper.COL_ID );
        messageIndex = results.getColumnIndex( MyOpenHelper.COL_MESSAGE);
        sOrRIndex = results.getColumnIndex( MyOpenHelper.COL_SEND_RECEIVE);

        //cursor is pointing to row -1
        while( results.moveToNext() ) //returns false if no more data
        { //pointing to row 2
            int sent = results.getInt(sOrRIndex);
            int id = results.getInt(idIndex);
            String message = results.getString( messageIndex );
            Boolean isSent = sent == 1;

            //add to arrayList:
            messages.add( new Message(id, isSent, message ));
        }

        ListView myList = findViewById(R.id.chatText);
        myList.setAdapter(myAdapter = new MyListAdapter());
        EditText messageText = findViewById(R.id.enterMessage);

        send = findViewById(R.id.send);
        send.setOnClickListener(click -> {
            String msgText = messageText.getText().toString();

            //insert into database:
            ContentValues newRow = new ContentValues();// like intent or Bundle

            //Message column:
            newRow.put(MyOpenHelper.COL_MESSAGE, msgText);

            //Send or receive column:
            newRow.put(MyOpenHelper.COL_SEND_RECEIVE, 1);

            long id = conversationDatabase.insert(MyOpenHelper.TABLE_NAME, null, newRow); //returns the id

            Message sendMsg = new Message(id, true, msgText);

            messages.add(sendMsg);
            messageText.setText("");

            //is this redundant
            myAdapter.notifyDataSetChanged();

        });

        receive = findViewById(R.id.receive);
        receive.setOnClickListener(click -> {
            String msgText = messageText.getText().toString();

            //insert into database:
            ContentValues newRow = new ContentValues();// like intent or Bundle

            //Message column:
            newRow.put(MyOpenHelper.COL_MESSAGE, msgText);

            //Send or receive column:
            newRow.put(MyOpenHelper.COL_SEND_RECEIVE, 1);

            long id = conversationDatabase.insert(MyOpenHelper.TABLE_NAME, null, newRow); //returns the id

            Message receiveMsg = new Message(id, false, msgText);

            messages.add(receiveMsg);
            messageText.setText("");

            //is this redundant
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

    public void printCursor(Cursor c, int version) {
        Log.e(TAG, "Database version number: " + conversationDatabase.getVersion());
        Log.e(TAG, "Number of columns: " + c.getColumnCount());

        for (int i = 0; i < 3; i++) {
            Log.e(TAG, "Name of columns: " + c.getColumnName(i));
        }

        Log.e(TAG, "Number of rows: " + c.getCount());

        //cursor points to row -1
        while(c.moveToNext()) {
            long id = c.getLong(idIndex);
            message = results.getString(messageIndex);
            int isSentValue = results.getInt(sOrRIndex);
            String isSent = isSentValue == 1 ? "Sent":"Receive";
            Log.e(TAG, "Message: " + message + "Type: " + isSent );
        }

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
            long id;
            public boolean sent;
            String messageText;

            public Message(long _id, boolean sent, String messageText ) {
                this.id = _id;
                this.messageText = messageText;
                this.sent=sent;
            }

            public boolean getSent() {
                return sent;
            }

            public void setSent(boolean status) {
                this.sent = status;
            }

            public String getMessageText() {
                return messageText;
            }

            public void setMessageText(String text) {
                this.messageText = text;
            }

            public long getId() {
                return id;
            }

            public void setId(long id) {
                this.id = id;
            }

        }





}