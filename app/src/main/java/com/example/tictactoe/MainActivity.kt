package com.example.tictactoe

import android.media.AsyncPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity(),View.OnClickListener {

    var Player=true
    var TurnCount=0
    var boardVal=Array(3){ IntArray(3) }

    lateinit var board:Array<Array<Button>>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        board= arrayOf(
            arrayOf(findViewById(R.id.button1),findViewById(R.id.button2),findViewById(R.id.button3)),
            arrayOf(findViewById(R.id.button4),findViewById(R.id.button5),findViewById(R.id.button6)),
            arrayOf(findViewById(R.id.button7),findViewById(R.id.button8),findViewById(R.id.button9))
        )
        for(i in board){
            for(button in i){
                button.setOnClickListener(this)
            }
        }
        findViewById<Button>(R.id.resetBtn).setOnClickListener {
            initializeBoardStatus()
            Player = true
            TurnCount = 0
        }
        initializeBoardStatus()

    }
    private fun initializeBoardStatus(){
        findViewById<TextView>(R.id.textView).text="Player X Turn"
        for(i in 0..2){
            for(j in 0..2){
                boardVal[i][j]=-1
                board[i][j].isEnabled=true
                board[i][j].text=""
            }
        }
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.button1->{
                updateValue(0,0,Player)
            }
            R.id.button2->{
                updateValue(0,1,Player)
            }
            R.id.button3->{
                updateValue(0,2,Player)
            }
            R.id.button4->{
                updateValue(1,0,Player)
            }
            R.id.button5->{
                updateValue(1,1,Player)
            }
            R.id.button6->{
                updateValue(1,2,Player)
            }
            R.id.button7->{
                updateValue(2,0,Player)
            }
            R.id.button8->{
                updateValue(2,1,Player)
            }
            R.id.button9->{
                updateValue(2,2,Player)
            }
        }
        TurnCount++
        Player=!Player

        if(TurnCount==9){
            findViewById<TextView>(R.id.textView).text="Game Draw"
        }else{
            if(Player){
                findViewById<TextView>(R.id.textView).text="Player X Turn"
            }else{
                findViewById<TextView>(R.id.textView).text="Player O Turn"
            }
        }

        checkWin()


    }

    private fun checkWin() {
        //checkRows
        for(i in 0..2){
            var flag=true
            val initialValue=boardVal[i][0]
            for(j in 1..2){
                if(initialValue==-1){
                    flag=false
                    break
                }
                if(boardVal[i][j]!=initialValue){
                    flag=false
                    break
                }
            }
            if(flag){
                disableBtn()
                if(initialValue>0){
                    findViewById<TextView>(R.id.textView).text="Player X has Won"
                }else{
                    findViewById<TextView>(R.id.textView).text="Player O has Won"
                }
                break
            }
        }
        //check column
        for(j in 0..2){
            var flag=true
            val initialValue=boardVal[0][j]
            for(i in 1..2){
                if(initialValue==-1){
                    flag=false
                    break
                }
                if(boardVal[i][j]!=initialValue){
                    flag=false
                    break
                }
            }
            if(flag){
                disableBtn()
                if(initialValue>0){
                    findViewById<TextView>(R.id.textView).text="Player X has Won"
                }else{
                    findViewById<TextView>(R.id.textView).text="Player O has Won"
                }
                break
            }
        }
        //checkDiag
        if(boardVal[0][0]==boardVal[1][1] && boardVal[0][0]==boardVal[2][2] && boardVal[0][0]!=-1 ){
            disableBtn()
            if(boardVal[0][0]>0){
                findViewById<TextView>(R.id.textView).text="Player X has Won"
            }else{
                findViewById<TextView>(R.id.textView).text="Player O has Won"
            }
        }else if(boardVal[0][2]==boardVal[1][1] && boardVal[0][2]==boardVal[2][0] && boardVal[0][2]!=-1 ){
            disableBtn()
            if(boardVal[0][2]>0){
                findViewById<TextView>(R.id.textView).text="Player X has Won"
            }else{
                findViewById<TextView>(R.id.textView).text="Player O has Won"
            }
        }

    }

    private fun disableBtn() {
        for(i in 0..2){
            for(j in 0..2){
                board[i][j].isEnabled=false
            }
        }
    }

    private fun updateValue(row:Int, col:Int, Player:Boolean){
        val text= if(Player) "X" else "O"
        val value= if(Player) 1 else 0
        board[row][col].apply {
            isEnabled=false
            setText(text)
        }
        boardVal[row][col]=value
    }

}