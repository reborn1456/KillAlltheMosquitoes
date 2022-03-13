package com.example.myapplication;

import android.graphics.Rect;

public class GameObject
{
    //==================================================
    // フィールド (= c++のメンバ変数)
    //==================================================
    // protected このクラス(GameObject)と継承先(Player)でアクセスできる指定子


    protected boolean alive;

    // キャラクターの座標
    protected float posX = 0;
    protected float posY = 0;

    // 当たり判定用の四角形
    protected Rect hitArea = new Rect();

    // キャラクターの使用する画像
    protected String imageName;

    protected int imageX;
    protected int imageY;




    //==================================================
    // メソッド (= c++関数)
    //==================================================
    // 継承された先で、中身が作られる関数＝仮想関数

    public void Update() {}

    // 描画
    public void Draw()
    {
        App.Get().ImageMgr().Draw(imageName, posX, posY);
    }

    public boolean IsAlive() { return alive; }

    public void SetPos(double x, double y)
    {
        posX = (float) x;
        posY = (float) y;
    }
    public float GetPosX() { return posX; }
    public float GetPosY() { return posY; }

    public final Rect GetHitArea() { return hitArea; }

    // 当たり判定（タッチ）
    public boolean HitCheck(int x, int y)
    {
        return hitArea.contains(x, y);
    }
    // 当たり判定（四角形）
    public boolean HitCheck(Rect r) {return hitArea.intersect(r); }

    public void SetImageName(String img) { imageName = img; }
}
