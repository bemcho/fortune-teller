package com.bemcho.helloworld

import kotlin.random.Random

data class FortuneService(val fortunes: Array<String>, val random : Random = Random(System.currentTimeMillis())){

    fun getFortune():String{
        return fortunes[random.nextInt(fortunes.size)]
    }
}