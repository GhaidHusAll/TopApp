package com.example.topapplications.model

import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root
import java.io.Serializable

@Root(name = "feed" ,strict = false)
class MyFeed constructor(): Serializable {

    @field:Element(name = "title")
    var title: String? = null

    @field:Element(name = "updated")
    var date: String? = null

    @field:ElementList(name = "entry", inline = true)
    var entrys: List<Entry>? = null

}