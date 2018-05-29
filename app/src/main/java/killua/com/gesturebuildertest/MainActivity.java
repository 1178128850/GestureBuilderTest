package killua.com.gesturebuildertest;

import android.app.AlertDialog;
import android.app.Dialog;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.Prediction;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements GestureOverlayView.OnGesturePerformedListener{

    private GestureLibrary gestureLibrary;
    private GestureOverlayView gv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        gv = findViewById(R.id.gv);
        gv.setGestureStrokeType(GestureOverlayView.GESTURE_STROKE_TYPE_MULTIPLE);
        gv.setFadeOffset(2000);
        gv.addOnGesturePerformedListener(this);


        gestureLibrary = GestureLibraries.fromRawResource(this, R.raw.gestures);
        gestureLibrary.load();
    }

    @Override
    public void onGesturePerformed(GestureOverlayView overlay, Gesture gesture) {
        ArrayList<Prediction> recognize = gestureLibrary.recognize(gesture);
        Prediction prediction = recognize.get(0);
        //判断相似度0.0~10.0
        if(prediction.score > 3.0){
            Toast.makeText(MainActivity.this, ""+prediction.name, Toast.LENGTH_SHORT).show();
            /*//通过名字判断手势
            if(prediction.name.equals("gou")){
            }else if(prediction.name.equals("cha")){
                Toast.makeText(MainActivity.this, ""+prediction.name, Toast.LENGTH_SHORT).show();
            }else if(prediction.name.equals("o")){
                Toast.makeText(MainActivity.this, ""+prediction.name, Toast.LENGTH_SHORT).show();
            }else if(prediction.name.equals("you")){
                Toast.makeText(MainActivity.this, ""+prediction.name, Toast.LENGTH_SHORT).show();
            }else if(prediction.name.equals("zuo")){
                Toast.makeText(MainActivity.this, ""+prediction.name, Toast.LENGTH_SHORT).show();
            }*/
        }else{
            Toast.makeText(MainActivity.this, "无法识别！", Toast.LENGTH_SHORT).show();
        }
    }
}
