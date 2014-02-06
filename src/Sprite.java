import android.graphics.Bitmap;
import android.graphics.Canvas;
import com.example.MyFirstGame.GameView;

public class Sprite {
    /**Объект класса GameView*/
    private GameView gameView;

    /**Картинка*/
    private Bitmap bmp;

    /**Позиция по Х=0*/
    private int x = 5;

    /**Скорость по Х=5*/
    private int xSpeed = 5;

    private int ySpeed = 5;

    /**Текущий кадр = 0*/
    private int currentFrame = 0;

    /**Ширина*/
    private int width;

    /**Ввыоста*/
    private int height;

    /**Конструктор*/
    public Sprite(GameView gameView, Bitmap bmp)
    {
        this.gameView=gameView;
        this.bmp=bmp;
    }

    /**Перемещение объекта, его направление*/
    private void update()
    {
        if (x > gameView.getWidth() - bmp.getWidth() - xSpeed) {
            xSpeed = -5;
        }
        if (x + xSpeed< 0) {
            xSpeed = 5;
        }
        x = x + xSpeed;
    }

    /**Рисуем наши спрайты*/
    public void onDraw(Canvas canvas)
    {
        update();
        canvas.drawBitmap(bmp, x , 10, null);
    }
}
