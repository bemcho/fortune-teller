package com.bemcho.helloworld

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.helloworld.R
import kotlinx.android.synthetic.main.activity_main.*
import java.io.BufferedReader
import java.io.InputStreamReader

class MainActivity : AppCompatActivity() {

    var fortunes: FortuneService? = null
    var fortuneView: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

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

        fortuneView?.text = fortunes?.getFortune() ?: "You're working under a slight handicap.  You happen to be human."
    }

    fun readFortunes(): Array<String> {

        var contents =
            readAsset("fortunes/fortunes") + readAsset("fortunes/literature") + readAsset(
                "fortunes/riddles"
            )

        val result = contents.split("%\n").toMutableList()
        result.shuffle()
        return result.toTypedArray()

    }

    fun readAsset(relativeToAssetsPath: String): String {
        return BufferedReader(InputStreamReader(assets.open(relativeToAssetsPath))).readText()
    }

}
