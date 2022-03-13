package com.example.myapplication;

import android.text.method.Touch;

public class FallBox extends GameObject
{
    public FallBox()
    {
        imageName = "square.jpg";
    }



    //==================================================
    // メソッド (= c++関数)
    //==================================================
    @Override
    public void Update()
    {
        int moveY = 0;

        /*
        Pointer p = App.Get().TouchMgr().GetTouch();
        if (p != null) { moveY = 5; }
         */

        posY += 5;

        /*
        if (posY > 1500)
        {
            moveY = 0;
            alive = false;
        }
         */

        // Rectの更新
        hitArea.left = (int)posX - 32;
        hitArea.top = (int)posY - 32;
        hitArea.right = (int)posX + 32;
        hitArea.bottom = (int)posY + 32;

        // 当たり判定
        for (GameObject obj : App.Get().GetAllObjects())
        {
            //if (App.Get().GetEnemy().HitCheck(hitArea))
            {
                //App.Get().GetEnemy().SetPos(0, 0);
                //type = TYPE_ID.STACKBOX;
            }
        }
    }
}
