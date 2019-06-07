package com.bemcho.helloworld

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.helloworld.R
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader

class MainActivity : AppCompatActivity() {

    var fortunes: FortuneService? = null
    var fortuneView: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        fortuneView = findViewById(R.id.fortune_text) as TextView
        fortunes = FortuneService(readFortunes())
        tellFortune(fortuneView as View)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun tellFortune(v: View) {

        fortuneView?.setText(
            fortunes?.getFortune() ?: "You're working under a slight handicap.  You happen to be human."
        )
    }

    fun readFortunes(): Array<String> {

        var contents =
            readAsset(assets.open("fortunes/fortunes")) + readAsset(assets.open("fortunes/literature")) + readAsset(
                assets.open("fortunes/riddles")
            )

        return contents.split("%").toTypedArray()
    }

    fun readAsset(inStream: InputStream): String {
        return BufferedReader(InputStreamReader(inStream)).readText()
    }

}
