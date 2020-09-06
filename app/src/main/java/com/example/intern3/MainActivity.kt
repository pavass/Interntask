package com.example.intern3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.chat_from.*
import kotlinx.android.synthetic.main.chat_from.view.*
import java.sql.Timestamp

class MainActivity : AppCompatActivity() {
    companion object{
        val TAG="ChatLog"
    }
    val adapter=GroupAdapter<ViewHolder>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView_chatlog.adapter=adapter
        //setupDummyData()
        listenForMessages()
        button_send_message.setOnClickListener {
            Log.d(TAG,"attempt to send msg....")
            performSendMessage()
            editText_enter_message.text.clear()
        }
        button_send_message.setOnLongClickListener(object: View.OnLongClickListener {
            override fun onLongClick(v: View?): Boolean {

                textView.setTextSize(100F);
                performSendMessage()
                editText_enter_message.text.clear()
                return false  }
        })
    }
    private fun listenForMessages(){
        val ref=FirebaseDatabase.getInstance().getReference("/messages")
        ref.addChildEventListener(object:ChildEventListener{
            override fun onChildAdded(p0: DataSnapshot, p1: String?) {
                val chatMessage=p0.getValue(ChatMessage::class.java)
                if (chatMessage!=null){
                    Log.d(TAG,chatMessage.text)
                    adapter.add(ChatItems(chatMessage.text))
                    recyclerView_chatlog.smoothScrollToPosition(adapter.getItemCount())
                }

            }

            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onChildChanged(p0: DataSnapshot, p1: String?) {

            }

            override fun onChildMoved(p0: DataSnapshot, p1: String?) {

            }

            override fun onChildRemoved(p0: DataSnapshot) {

            }
        })
    }
    class ChatMessage(val id: String,val text:String,val timestamp: Long){
        constructor():this("","",-1)
    }
    private fun performSendMessage(){
        val text=editText_enter_message.text.toString()
        val reference=FirebaseDatabase.getInstance().getReference("/messages").push()
        val chatMessage=ChatMessage(reference.key!!,text,System.currentTimeMillis()/1000)
        reference.setValue(chatMessage).addOnSuccessListener {
            Log.d(TAG,"Saved message")
        }
    }
    private fun setupDummyData(){
        val adapter = GroupAdapter<ViewHolder>()
        adapter.add(ChatItems("from msggggg"))

        recyclerView_chatlog.adapter=adapter
    }
}

class ChatItems(val text:String): Item<ViewHolder>() {
    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.textView.text = text

    }

    override fun getLayout(): Int {
        return R.layout.chat_from

    }
}




