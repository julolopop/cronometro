package com.example.usuario.cronometro;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by usuario on 18/01/18.
 */

@SuppressLint("AppCompatCustomView")
public class ReverseCronometro extends TextView implements Runnable{

    private long overallDuration;
    private long warningDuration;
    private long starTime;

    public ReverseCronometro(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray atributos = getContext().obtainStyledAttributes(attrs,R.styleable.ReverseCronometro);

        overallDuration = atributos.getInteger(R.styleable.ReverseCronometro_overallduration,60);
        warningDuration = atributos.getInteger(R.styleable.ReverseCronometro_warningDuration,10);

        reset();
    }

    @Override
    public void run() {
        long elapsedSeconds = (SystemClock.elapsedRealtime() - starTime)/1000;
        if (elapsedSeconds < overallDuration){
            //actualizar tiempos
            long remaingSecond = overallDuration -elapsedSeconds;
            long minutos = remaingSecond/60;
            long segundos = remaingSecond-(minutos*60);
            setText(String.format("%d:%02d",minutos,segundos));

            if(remaingSecond < warningDuration)
                setTextColor(Color.RED);

            postDelayed(this,1000);
        }else{
            setText("00:00");
            setTextColor(Color.BLACK);
        }
    }

    public void reset(){
        starTime = SystemClock.elapsedRealtime();
        setText("--:--");
        setTextColor(Color.BLACK);
    }

    public long getOverallDuration() {
        return overallDuration;
    }
    public long getWarningDuration() {
        return warningDuration;
    }

    public void setOverallDuration(long overallDuration) {
        this.overallDuration = overallDuration;
    }

    public void setWarningDuration(long warningDuration) {
        this.warningDuration = warningDuration;
    }

    public void stop() {
        removeCallbacks(this);
    }
}
