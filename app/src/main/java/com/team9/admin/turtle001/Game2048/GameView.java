package com.team9.admin.turtle001.Game2048;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridLayout;

import java.util.ArrayList;
import java.util.List;

// 2048的外部框

public class GameView extends GridLayout{

    public GameView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initGameView();
    }

    public GameView(Context context) {
        super(context);

        initGameView();
    }

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);

        initGameView();
    }
//入口方法
    private void initGameView(){

        //4列，颜色
        setColumnCount(4);
//        setBackgroundColor(0xffbbada0);

        addCards(223,223);

        startGame();
        //触摸方向
        setOnTouchListener(new OnTouchListener(){

            //记录初始触摸位置和偏移量
            private float startX,startY,offsetX,offsetY;

            @Override
            public boolean onTouch(View v, MotionEvent event){

                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        startX = event.getX();
                        startY = event.getY();
                        break;
                    case MotionEvent.ACTION_UP:
                        offsetX = event.getX()-startX;
                        offsetY = event.getY()-startY;

                        //水平方向的意图与垂直方向的意图比较,得出移动方向
                       if (Math.abs(offsetX)>Math.abs(offsetY)){
                            if (offsetX<-5) {
                                swipeleft();
                            }else if (offsetX>5){
                                swiperight();
                            }
                       }else {
                           if (offsetY<-5){
                               swipeup();
                           }else if (offsetY>5){
                               swipedown();
                           }
                       }
                        break;
                }
                changeColor();
                return  true;
            }

        });
    }



    //添加卡片
    private void addCards(int cardWidth,int cardHeight){

        Card c;
        for (int y = 0; y < 4; y++){
            for (int x=0; x < 4; x++){
                c=new Card(getContext());
                c.setNum(0);
                addView(c,cardWidth,cardHeight);

                cardsMap[x][y]=c;
            }
        }
    }

    private void changeColor(){
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {

                cardsMap[x][y].setColor();
            }
        }
    }

    //游戏开始
    private void startGame(){

        GameActivity.getMainActivity().score = 0;

        for (int y = 0; y < 4;y++){
            for (int x = 0; x < 4;x++){
               cardsMap[x][y].setNum(0);
            }
        }

        addRandomNum();
        addRandomNum();
        changeColor();
    }

    private void checkComplete(){

        boolean complete = true;

        ALL:
        for (int y = 0;y < 4;y++){
            for (int x = 0;x < 4;x++){
                if(cardsMap[x][y].getNum()==0||
                        (x>0&&cardsMap[x][y].equals(cardsMap[x-1][y]))||
                        (x<3&&cardsMap[x][y].equals(cardsMap[x+1][y]))||
                        (y>0&&cardsMap[x][y].equals(cardsMap[x][y-1]))||
                        (y<3&&cardsMap[x][y].equals(cardsMap[x][y+1]))){

                    complete = false;
                    break ALL;
                }
            }
        }

        if(complete){
            new AlertDialog.Builder(getContext()).setTitle("你好").setMessage("游戏结束").setPositiveButton("重新开始", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    startGame();
                }
            }).show();
        }
    }

    //添加随机数值
    private void addRandomNum(){

        emptyPoints.clear();
        for (int y = 0; y < 4;y++){
            for (int x = 0; x < 4;x++){
                if (cardsMap[x][y].getNum()<=0){
                    emptyPoints.add(new Point(x,y));
                }
            }
        }
        //找到空位置加入随机数
        Point p =emptyPoints.remove((int) (Math.random()*emptyPoints.size()));
        cardsMap[p.x][p.y].setNum(Math.random()>0.1?2:4);
    }

    //移动方向后的操作
    private void swipeleft(){

        boolean merge = false;
        //遍历
        for (int y = 0; y < 4;y++){
            for (int x = 0; x < 4;x++){
                for (int x1 = x+1;x1<4;x1++){
                    //空的
                    if (cardsMap[x1][y].getNum()>0){
                        if(cardsMap[x][y].getNum()<=0){
                            cardsMap[x][y].setNum(cardsMap[x1][y].getNum());
                            cardsMap[x1][y].setNum(0);

                            x--;

                            merge = true;
                        }
                        //合并
                        else if (cardsMap[x][y].equals(cardsMap[x1][y])){
                            cardsMap[x][y].setNum(cardsMap[x][y].getNum()*2);
                            cardsMap[x1][y].setNum(0);

                            GameActivity.getMainActivity().addScore(cardsMap[x][y].getNum());
                            merge = true;
                        }
                        break;
                    }
                }
            }
        }

        if (merge){
            addRandomNum();
            checkComplete();
        }
    }
    private void swiperight(){

        boolean merge = false;
        //遍历
        for (int y = 0; y < 4;y++){
            for (int x = 3; x >= 0;x--){
                for (int x1 = x-1;x1>=0;x1--){
                    //空的
                    if (cardsMap[x1][y].getNum()>0){
                        if(cardsMap[x][y].getNum()<=0){
                            cardsMap[x][y].setNum(cardsMap[x1][y].getNum());
                            cardsMap[x1][y].setNum(0);

                            x++;
                            merge = true;
                        }
                        //合并
                        else if (cardsMap[x][y].equals(cardsMap[x1][y])){
                            cardsMap[x][y].setNum(cardsMap[x][y].getNum()*2);
                            cardsMap[x1][y].setNum(0);

                            GameActivity.getMainActivity().addScore(cardsMap[x][y].getNum());
                            merge = true;
                        }
                        break;
                    }
                }
            }
        }
        if (merge){
            addRandomNum();
            checkComplete();
        }
    }
    private void swipeup(){

        boolean merge = false;
        //遍历
        for (int x = 0; x < 4;x++){
            for (int y = 0; y < 4;y++){
                for (int y1 = y+1;y1 < 4;y1++){
                    //空的
                    if (cardsMap[x][y1].getNum()>0){
                        if(cardsMap[x][y].getNum()<=0){
                            cardsMap[x][y].setNum(cardsMap[x][y1].getNum());
                            cardsMap[x][y1].setNum(0);

                            y--;
                            merge = true;
                        }
                        //合并
                        else if (cardsMap[x][y].equals(cardsMap[x][y1])){
                            cardsMap[x][y].setNum(cardsMap[x][y].getNum()*2);
                            cardsMap[x][y1].setNum(0);

                            GameActivity.getMainActivity().addScore(cardsMap[x][y].getNum());
                            merge = true;
                        }
                        break;
                    }
                }
            }
        }
        if (merge){
            addRandomNum();
            checkComplete();
        }
    }
    private void swipedown(){

        boolean merge = false;
        //遍历
        for (int x = 0; x < 4;x++){
            for (int y = 3; y >= 0;y--){
                for (int y1 = y-1;y1 >= 0;y1--){
                    //空的
                    if (cardsMap[x][y1].getNum()>0){
                        if(cardsMap[x][y].getNum()<=0){
                            cardsMap[x][y].setNum(cardsMap[x][y1].getNum());
                            cardsMap[x][y1].setNum(0);

                            y++;
                            merge = true;
                        }
                        //合并
                        else if (cardsMap[x][y].equals(cardsMap[x][y1])){
                            cardsMap[x][y].setNum(cardsMap[x][y].getNum()*2);
                            cardsMap[x][y1].setNum(0);

                            GameActivity.getMainActivity().addScore(cardsMap[x][y].getNum());
                            merge =true;
                        }
                        break;
                    }
                }
            }
        }
        if (merge){
            addRandomNum();
            checkComplete();
        }
    }


    //存储卡片
    private Card[][]cardsMap = new Card[4][4];
    private List<Point> emptyPoints = new ArrayList<Point>();

}
