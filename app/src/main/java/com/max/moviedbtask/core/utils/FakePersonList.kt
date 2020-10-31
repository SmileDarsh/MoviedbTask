package com.max.moviedbtask.core.utils

import com.max.moviedbtask.person.domain.model.KnownFor
import com.max.moviedbtask.person.domain.model.Person

/**
 * Created by µðšţãƒâ ™ on 31/10/2020.
 *  ->
 */
object FakePersonList {
    val dataList = arrayOf(
        Person(1, "Dwayne Johnson", 45.649,"Acting","/cgoy7t5Ve075naBPcewZrc08qGw.jpg",
            mutableListOf(KnownFor("Jumanji: Welcome to the Jungle"),KnownFor("Moana"),KnownFor("Fast & Furious 6"))),
        Person(2, "Jason Statham", 37.689,"Acting","/lldeQ91GwIVff43JBrpdbAAeYWj.jpg",
            mutableListOf(KnownFor("Fast & Furious 6"),KnownFor("Furious 7"),KnownFor("The Fate of the Furious"))),
        Person(3, "Arnold Schwarzenegger", 29.517,"Acting","/xKRwhLM8eJwWHppBqSRvVCNt0Cq.jpg",
            mutableListOf(KnownFor("The Terminator"),KnownFor("Terminator 2: Judgment Day"),KnownFor("Terminator Genisys"))),
        Person(4, "Jackie Chan", 28.18,"Acting","/nraZoTzwJQPHspAVsKfgl3RXKKa.jpg",
            mutableListOf(KnownFor("Kung Fu Panda"),KnownFor("Kung Fu Panda 2"),KnownFor("The Karate Kid")))
    )
}