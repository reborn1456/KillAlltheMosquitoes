package com.example.myapplication;

import android.graphics.Color;

public class Result extends SceneBase
{
    int score = 0;
    int highScore = 0;

    @Override
    public void Init()
    {
        App.Get().SoundMgr().PlayBGM("bgm_yarukinai.mp3");
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
                App.Get().ChangeScene(new Title());
            }
        }

        score = App.Get().GetScore();
        if (score > highScore)
        {
            highScore = score;
        }
    }

    @Override
    public void Draw()
    {
        //App.Get().ImageMgr().Draw("whiteScreen.png", 0, 0);
        App.Get().ImageMgr().Draw("result.png", 300, 530);
        App.Get().GetView().DrawString(490,650, "" + score, Color.BLACK);
        App.Get().GetView().DrawString(490,1050, "" + highScore, Color.BLACK);
    }
}
