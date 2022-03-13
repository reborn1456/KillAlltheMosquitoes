package com.example.myapplication;

import android.graphics.Color;

public class Title extends SceneBase
{
    @Override
    public void Init()
    {
    }

    @Override
    public void Update()
    {
        // 次のシーンに進める
        Pointer p = App.Get().TouchMgr().GetTouch();
        if (p != null)
        {
            if (p.OnUp())
            {
                App.Get().ChangeScene(new GameMain());
            }
        }
    }

    @Override
    public void Draw()
    {
        //App.Get().GetView().DrawString(300,100,"Kill All the Mosquitos", Color.BLACK);
        App.Get().ImageMgr().Draw("title.png", 300, 510);
    }
}
