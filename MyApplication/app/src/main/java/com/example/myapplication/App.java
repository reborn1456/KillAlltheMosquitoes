package com.example.myapplication;


import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.util.Log;
import android.view.SoundEffectConstants;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

/*
* App
* リソース管理などのシステム面全般
* シングルトン
*
*/



/*
*   Global variable is not allowed in java.
*   E.g.  int x = 0;     Error
*/

public class App
{
    /*
    *   All data types in java are pointer.
    *   Therefore whenever you create a new instance you will have to use new. Or else it will be the same as creating a pointer which contains nothing(null) in it. Which means error if did so.
    *   E.g.  Matrix m = new Matrix();      Correct
    *         Matrix m;                     Error
    *
    *   Even array is treated as a pointer.
    *   E.g. int[] arr;  <-same as->  int[] arr = null;     Error
    *        int[] arr = new int[10];                       Correct
    *   But this(int[] arr = new int[10]) only mean you have declared an array with 10 elements.
    *   To instance the array you will have to run it through a loop
    *   E.g. Vector2[] vArr = new Vector2[10];
    *        for (int i = 0; i < 10; i++)
    *        {
    *           vArr[i] = new Vector2();
    *        }
    *       Then vArr array will be able to use like "vArr[0].Add(vm);" and there won't be error.
    *
    *
    *   8 Primitive Data Types (meaning you do not need to have new when instance):
    *    byte
    *    short
    *    int
    *    long
    *    float
    *    double
    *    boolean
    *    char
    */

    /*
    private GameObject play = new Player();
    public GameObject GetPlayer() { return play; }

    private Enemy[] enemies = new Enemy[10];
    public Enemy[] GetEnemies() { return enemies; }

    private ArrayList<GameObject> bullets = new ArrayList<>();
    public ArrayList<GameObject> GetBullets() { return bullets; }
     */


    // 何でも入るリスト
    private ArrayList<GameObject> allObjects = new ArrayList<>();
    public ArrayList<GameObject> GetAllObjects() { return allObjects; }

    // シーンを分ける
    private SceneBase nowScene = null;      // 現在のシーン
    public void ChangeScene (SceneBase scene)
    {
        if (scene == null) { return; }

        scene.Init();
        nowScene = scene;
    }


    // ゲームの実装------------------------------------------------>
    // 特殊な事をしない限りはこの間を編集するだけのはず
    int se1 = 0;

    // アプリケーションが開始された時
    // 諸々の初期化は終わっているので、ここでロードをかけてもOK
    public void Start()
    {
        soundManager.PlayBGM("bgm_yarukinai.mp3");
        se1 = soundManager.LoadSE("se_FlyingMosquito.mp3");

        /*
        // creating(new a pointer) with array
        for (int ec = 0; ec < enemies.length; ec++)
        {
            enemies[ec] = new Enemy();
            enemies[ec].SetPos(250, ec * 100);
        }

        // creating(new a pointer) with arraylist
        for (int ec = 0; ec < 10; ec++)
        {
            Enemy e = new Enemy();
            e.SetPos(250, ec * 100);
            allObjects.add(e);
        }
         */

        nowScene = new Title();     // アップキャスト
    }


    // 毎回呼び出される関数(30fps)
    Vector2 vp = new Vector2();
    Vector2 vm = new Vector2();

    // 配列の宣言
    int[] arr = new int [10];
    Vector2[] vArr = new Vector2[10];

    int count = 0;

    int score = 0;
    public int GetScore() { return score; }
    public void SetScore(int num) { score = num; }

    int highScore = 0;
    public int GetHighScore() { return highScore; }
    public void SetHighScore(int num) { highScore = num; }

    public boolean Update()
    {
        /*
        // ゲームの更新
        vp.Add(vm);

        // タッチの処理
        Pointer p = touchManager.GetTouch(); // ここでnullが帰る場合はタッチされていない
        if(p==null){ return true; }

        // Remember to do a check for nullptr before processing anything, or else null error will occur
        if(p.OnDown()) // 押された瞬間
        {
            vp.x = p.GetDownPos().x;
            vp.y = p.GetDownPos().y;
            vm.Clear();
            //soundManager.PlaySE(se1);
        }

        if(p.OnFlick()) // 離された＆それがフリックと判定された
        {
            vm = p.GetDtoU();
        }

        if(p.OnUp())
        {
            //soundManager.PlaySE(se2);
            Log.d("touch", "OnUP!!!!!");
        }

        //count++;

         */

        /*
        // プレイヤーの更新
        play.Update();

        // エネミーの更新
        for (int ec = 0; ec < enemies.length; ec++)
        {
            enemies[ec].Update();
        }

        // バレットの更新
        for (GameObject b : bullets)
        {
            b.Update();
        }

        // 不要になった要素を削除
        for (int bc = 0; bc < bullets.size();bc++)
        {
            if (bullets.get(bc).IsAlive() == false)
            {
                bullets.remove(bc);
            }
        }
         */

        /*
        // タッチ情報の取得
        Pointer p = touchManager.GetTouch();
        // Remember to do a null check cause there is always a chance that it is not touched
        if(p != null)
        {
            // Player対タッチの判定
            Rect area = play.GetHitArea();
            // Collision detection using contains
            if ( area.contains(p.GetDownPos().x, p.GetDownPos().y) )
            {
                play.SetPos(300, 0);
            }
        }

        // Player対敵の当たり判定
        Rect pArea = play.GetHitArea();
        Rect eArea = enemy.GetHitArea();
        // If true is returned, this means the 2 Rect are colliding/intercepting
        // Collision detection using intersect
        if (pArea.intersect(eArea))
        {
            play.SetPos(play.GetPosX(), enemy.GetPosY() - 64);
        }
         */

        // 今何のシーンかわからないけどUpdate
        if (nowScene != null)
        {
            nowScene.Update();
        }

        // 全員のUpdate
        for (GameObject obj : allObjects)
        {
            obj.Update();
        }



        // 必ずゲーム更新の後に行う事
        touchManager.Update();
        return true;
    }


    // Androidから再描画命令を受けた時
    // ここ以外での描画は無視されます。
    // 早くシステムに返さないと行けないので、描画以外の余計なことはしない。
    // 30fpsで再描画以来はかけているが、呼び出される頻度は端末によります。
    // ここが安定して動いているとは思わないでください。
    public void Draw()
    {
        /*
        play.Draw();

        for (Enemy e : enemies)
        {
            e.Draw();
        }

        for (GameObject b : bullets)
        {
            b.Draw();
        }

         */

        //view.DrawString(0, 100, "count: " + count, Color.BLACK);

        /*
        // imageManage is a function teacher created has the middle of the sprite as default point
        imageManage.Draw("ball.png", vp.x, vp.y);

        // Draw(imageName, x, y, rotation)
        imageManage.Draw("ball.png", 100, 100, 45);
        // Draw(imageName, x, y, ? times the size of x, ? times the size of y, rotation)
        // Be careful not to put 0 for _sx and _sy because it means "* 0" which is 0 which means it will not be drawn
        imageManage.Draw("ball.png", 100, 100, 2,2,0);
        // Can also use Matrix to draw
        // However the default point on the top left corner if use matrix
        Matrix m = new Matrix();
        imageManage.Draw("ball.png", m);
         */


        /*
        // 全員のDraw
        for (GameObject obj : allObjects)
        {
            obj.Draw();
        }
         */

        // 今何のシーンかわからないけどDraw
        if (nowScene != null)
        {
            nowScene.Draw();
        }


    }

    // ホームボタンなどを押して裏側へ回った時
    public void Suspend(Context c)
    {
        soundManager.StopBGM();

        // データの保存
        SharedPreferences dataStore = c.getSharedPreferences("SaveData", MODE_PRIVATE);
                                                                // データ名       データを他のアプリに読ませるか
        SharedPreferences.Editor editor = dataStore.edit();

        editor.putInt("Count", count);      // 書き溜め

        editor.commit();        // データを書き込み
    }

    // 再度アクティブになった時
    public void Resume(Context c)
    {
        soundManager.PlayBGM("bgm_yarukinai.mp3");

        // 復帰
        SharedPreferences dataStore = c.getSharedPreferences("SaveData", MODE_PRIVATE);
                                                                // データ名       データを他のアプリに読ませるか
        count = dataStore.getInt("Count", -1);
    }

    // <--------------------------------------------------ゲームの実装





    // アプリケーション大本
    private OriginalView view = null;
    public OriginalView GetView(){return view;}
    public void SetView(OriginalView _ov )
    {
        view = _ov;

        // viewがないと初期化出来ないもののインスタンス化
        touchManager = new TouchManager();


        Start();
    }

    // 解像度対応
    private float sdPar = 0; // システム座標→ディスプレイ座標変換用
    private float dsPar = 0; // ディスプレイ座標→システム座標変換用
    public float SD(){return sdPar;}
    public float DS(){return dsPar;}

    // システム画面サイズと実機画面サイズが出揃った段階で比率を計算
    public void SetSDPar(float _dp, float _sp)
    {
        sdPar = _dp/_sp;
        dsPar = _sp/_dp;
    }

    // リソース管理
    private ImageManager imageManage = new ImageManager();
    public ImageManager ImageMgr(){return imageManage;}
    private SoundManager soundManager = new SoundManager();
    public SoundManager SoundMgr(){return soundManager;}

    // タッチ管理
    private TouchManager touchManager = null;
    public TouchManager TouchMgr(){return touchManager;}

    //シングルトン実装
    private App() { }
    private static App app = new App();
    public static App Get()
    {
        return app;
    }
}
