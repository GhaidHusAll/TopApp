package com.example.topapplications.model

import org.simpleframework.xml.*
import java.io.Serializable

@Root(name = "entry" , strict = false)
class Entry @JvmOverloads constructor(

    @field:Element(name = "summary")
    @param:Element(name = "summary")
    var summary: String? = null,

    @field:Element(name = "name")
    @param:Element(name = "name")
    var name: String? = null,

    @field:ElementList(name = "image" , inline = true)
    var image: List<Image>? = null

):Serializable{
}