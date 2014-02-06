package com.example.MyFirstGame;

import java.util.Random;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
public class Sprite {
    /**Рядков в спрайте = 4*/
    private static final int BMP_ROWS = 4;

    /**Колонок в спрайте = 3*/
    private static final int BMP_COLUMNS = 3;

    /**Объект класса GameView*/
    private GameView gameView;

    /**Картинка*/
    private Bitmap bmp;

    /**Позиция по Х=0*/
    private int x = 5;

    /**Позиция по У=0*/
    private int y = 0;

    /**Скорость по Х=5*/
    private int xSpeed = 5;

    private int ySpeed = 5;

    /**Текущий кадр = 0*/
    private int currentFrame = 0;

    /**Ширина*/
    private int width;

    /**Ввыоста*/
    private int height;
    private int animationRow;

    /**Конструктор*/
    public Sprite(GameView gameView, Bitmap bmp)
    {
        this.gameView = gameView;
        this.bmp = bmp;
        this.width = bmp.getWidth() / BMP_COLUMNS;
        this.height = bmp.getHeight() / BMP_ROWS;

        Random rnd = new Random();
        xSpeed = rnd.nextInt(10)-5;
        ySpeed = rnd.nextInt(10)-5;
    }

    /**Перемещение объекта, его направление*/
    private void update()
    {
        if (x >= gameView.getWidth() - width - xSpeed || x + xSpeed <= 0)
        {
            xSpeed = -xSpeed;
        }

        x = x + xSpeed;

        if (y >= gameView.getHeight() - height - ySpeed || y + ySpeed <= 0)
        {
            ySpeed = -ySpeed;
        }

        y = y + ySpeed;
        currentFrame = ++currentFrame % BMP_COLUMNS;
    }

    /**
     * Рисуем наши спрайты
     */
    public void onDraw(Canvas canvas) {
        update();
        int srcX = currentFrame * width;
        int srcY = getAnimationRow() * height;
        Rect src = new Rect(srcX, srcY, srcX + width, srcY + height);
        Rect dst = new Rect(x, y, x + width, y + height);

        canvas.drawBitmap(bmp, src, dst, null);
    }
    // direction = 0 up, 1 left, 2 down, 3 right,
// animation = 3 up, 1 left, 0 down, 2 right
    int[] DIRECTION_TO_ANIMATION_MAP = { 3, 1, 0, 2 };

    public int getAnimationRow() {
        return animationRow;
    }

    public boolean isCollition(float x2, float y2) {
        return x2 > x && x2 < x + width && y2 > y && y2 < y + height;
}

    public boolean isCollision(float x, float y) {
        return x > x && x < x + width && y > y && y < y + height;}


    //public boolean isCollision(float x, float y) {
    }
