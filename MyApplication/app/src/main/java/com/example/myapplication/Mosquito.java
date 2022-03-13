package com.example.myapplication;

import android.text.method.Touch;
import android.util.Log;

public class Mosquito extends GameObject
{
    public Mosquito()
    {
        imageName = "mosquito.png";
        imageX = 50;
        imageY = 40;
        alive = true;
    }

    public static float getRandomIntegerBetweenRange(float min, float max)
    {
        float rand = (int)(Math.random()*((max-min)+1))+min;
        return rand;
    }

    int moveWait = 0;


    //==================================================
    // メソッド (= c++関数)
    //==================================================
    @Override
    public void Update()
    {
        // Rectの更新
        hitArea.left = (int)posX - (imageX / 2);
        hitArea.top = (int)posY - (imageY / 2);
        hitArea.right = (int)posX + (imageX / 2);
        hitArea.bottom = (int)posY + (imageY / 2);

        // 当たり判定
        Pointer p = App.Get().TouchMgr().GetTouch();
        if (p != null)
        {
            if(p.OnUp())
            {
                if (HitCheck(p.GetDownPos().x, p.GetDownPos().y))
                {
                    alive = false;
                }
            }
        }

        moveWait += 1;
        if(alive && moveWait % 20 == 0)
        {
            posX = getRandomIntegerBetweenRange(30, 575);
            posY = getRandomIntegerBetweenRange(25, 950);
        }

    }

    @Override
    public void Draw()
    {
        App.Get().ImageMgr().Draw(imageName, posX, posY);
    }

}
