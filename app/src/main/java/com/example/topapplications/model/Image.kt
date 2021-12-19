package com.example.topapplications.model

import org.simpleframework.xml.Root
import org.simpleframework.xml.Text
import java.io.Serializable

@Root(name = "image", strict = false)
class Image @JvmOverloads constructor(

  @field:Text(required = false)
  var url: String? = null

):Serializable {
}