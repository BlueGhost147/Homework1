package at.fh.swengb.raith.homeexercise1

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class WelcomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
    }

    fun startGame(v : View)
    {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
