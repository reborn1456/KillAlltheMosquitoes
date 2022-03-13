package com.example.myapplication;

import android.graphics.Color;

import java.util.ArrayList;
import java.util.Iterator;

public class GameMain extends SceneBase
{
    @Override
    public void Init()
    {
        App.Get().SoundMgr().StopBGM();
        App.Get().SoundMgr().PlayBGM("se_FlyingMosquito.mp3");
    }

    public static double getRandomIntegerBetweenRange(double min, double max)
    {
        double rand = (int)(Math.random()*((max-min)+1))+min;
        return rand;
    }


    int mosquitoNum = 0;
    int GameOver = 50;
    int score = 0;

    int bloodWait = 0;

    private ArrayList<Mosquito> mosquitoes = new ArrayList<>();
    public ArrayList<Mosquito> GetMosquitoes() { return mosquitoes; }

    private ArrayList<BloodSplash> bloodSplashes = new ArrayList<>();
    public ArrayList<BloodSplash> GetBloodSplashes() { return bloodSplashes; }

    @Override
    public void Update()
    {

        // Resultシーン
        if (mosquitoNum > GameOver)
        {
            App.Get().ChangeScene(new Result());
        }


        for (Mosquito m : mosquitoes)
        {
            m.Update();
        }

        double randNum = getRandomIntegerBetweenRange(1, 3);
        if (mosquitoNum < 110 && randNum % 2 == 0)
        {
            Mosquito mosquito = new Mosquito();
            mosquito.SetPos(getRandomIntegerBetweenRange(30, 575), getRandomIntegerBetweenRange(25, 950));
            GetMosquitoes().add(mosquito);
            mosquitoNum += 1;
        }


        for (int mc = 0; mc < mosquitoes.size(); mc++)
        {
            if (!mosquitoes.get(mc).IsAlive())
            {
                BloodSplash bloodSplash = new BloodSplash();
                bloodSplash.SetPos(mosquitoes.get(mc).posX, mosquitoes.get(mc).posY);
                GetBloodSplashes().add(bloodSplash);

                mosquitoes.remove(mc);
                mosquitoNum -= 1;
                score += 1;
                App.Get().SetScore(score);
            }
        }

        /*
        for (Iterator<Mosquito> iterator = mosquitoes.iterator(); iterator.hasNext(); )
        {
            Mosquito mosquito = iterator.next();
            if (!mosquito.IsAlive())
            {
                iterator.remove();
                mosquitoNum -= 1;
                score += 1;
            }
        }
         */

        bloodWait += 1;
        if (bloodWait % 50 == 0)
        {
            for (Iterator<BloodSplash> iterator = bloodSplashes.iterator(); iterator.hasNext(); )
            {
                BloodSplash bloodSplash = iterator.next();
                if (bloodSplash.IsAlive())
                {
                    iterator.remove();
                }
            }
            bloodWait = 0;
        }

        /*
        // 全員のUpdate
        for (GameObject obj : App.Get().GetAllObjects())
        {
            obj.Update();
        }
         */

    }


    @Override
    public void Draw()
    {
        for (Mosquito m : mosquitoes)
        {
            if (m.IsAlive())
            {
                m.Draw();
            }
        }

        for (BloodSplash b : bloodSplashes)
        {
            if (b.IsAlive())
            {
                b.Draw();
            }
        }

        App.Get().GetView().DrawString(50,100,"Score: " + score, Color.BLACK);
    }


}