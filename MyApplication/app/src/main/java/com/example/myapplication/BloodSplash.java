package com.example.myapplication;

public class BloodSplash extends GameObject
{
    public BloodSplash()
    {
        //imageName = "bloodSpatter.png";
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
    }

    @Override
    public void Draw()
    {
        App.Get().ImageMgr().Draw("bloodSpatterTest2.png", posX, posY);
    }
}
