package twitter.comicit;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;


class Windowprocessing extends Thread {
    private Handler mHandler;
    private Message mMsg;
    private ProgressDialog  dialog ;
    private Context con;
    // constructor
    public Windowprocessing(   Context cont) {
        // do something like save the Handler reference
     //   mHandler = handler;
     //   mMsg = msg;
    	con = cont ;
     
    }
    @Override
    public void run() {
        // do some background processing, call the Handler?
      //  mHandler.sendMessage(mMsg);
        dialog = ProgressDialog.show( con , "", "Consultando..", true);
        Toast.makeText(con, ".....", Toast.LENGTH_SHORT);
     
    }
    public void dismiss()
    {
    	dialog.dismiss();
    }
}