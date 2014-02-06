package com.example.MyFirstGame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.List;

public class GameView extends SurfaceView {

    private List<Sprite> sprites = new ArrayList<Sprite>();
    /**Загружаем спрайт*/
    private Bitmap bmp;

    /**Поле рисования*/
    private SurfaceHolder holder;

    /**объект класса GameView*/
    private GameManager gameLoopThread;

    /**Объект класса Sprite*/
    private Sprite sprite;

    /**Конструктор*/
    public GameView(Context context)
    {
        super(context);
        gameLoopThread = new GameManager(this);
        holder = getHolder();

          /*Рисуем все наши объекты и все все все*/
        holder.addCallback(new SurfaceHolder.Callback()
        {
            /*** Уничтожение области рисования */
            public void surfaceDestroyed(SurfaceHolder holder)
            {
                boolean retry = true;
                gameLoopThread.setRunning(false);
                while (retry)
                {
                    try
                    {
                        gameLoopThread.join();
                        retry = false;
                    } catch (InterruptedException e)
                    {
                    }
                }
            }

            /** Создание области рисования */
            public void surfaceCreated(SurfaceHolder holder)
            {
                gameLoopThread.setRunning(true);
                gameLoopThread.start();
            }

            /** Изменение области рисования */
            public void surfaceChanged(SurfaceHolder holder, int format,
                                       int width, int height)
            {
            }
        });
        bmp = BitmapFactory.decodeResource(getResources(), R.drawable.bad1);
        sprite = new Sprite(this,bmp);
        sprites.add(createSprite(R.drawable.bad1));
        sprites.add(createSprite(R.drawable.bad2));
        sprites.add(createSprite(R.drawable.bad3));
        sprites.add(createSprite(R.drawable.bad4));
        sprites.add(createSprite(R.drawable.bad5));
        sprites.add(createSprite(R.drawable.bad6));
        sprites.add(createSprite(R.drawable.good1));
        sprites.add(createSprite(R.drawable.good2));
        sprites.add(createSprite(R.drawable.good3));
        sprites.add(createSprite(R.drawable.good4));
        sprites.add(createSprite(R.drawable.good5));
        sprites.add(createSprite(R.drawable.good6));
    }

    private Sprite createSprite(int resouce) {
        Bitmap bmp = BitmapFactory.decodeResource(getResources(), resouce);
        return new Sprite(this,bmp);

    //    long lastClick;
    //    if (System.currentTimeMillis() - lastClick > 300) {
    //        //скобочка закрывается перед return true; иф - охватывает весь метод
    //}
    }
    /**Обработка косания по экрану*/
    /**Обработка косания по экрану*/

    public boolean onTouchEvent(MotionEvent event) {
        synchronized (getHolder()) {
            for (int i = sprites.size()-1; i > 0; i--)
            {
                Sprite sprite = sprites.get(i);
                if (sprite.isCollision(event.getX(),event.getY()))
                {
                    sprites.remove(sprite);
                    break;
                }
            }
        }
        return super.onTouchEvent(event);
    }

    /**Функция рисующая все спрайты и фон*/
    protected void onDraw(Canvas canvas)
    {
        canvas.drawColor(Color.BLACK);
        sprite.onDraw(canvas);
        for(Sprite sprite : sprites) {
            sprite.onDraw(canvas);
        }
    }
}