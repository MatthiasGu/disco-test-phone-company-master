package com.phone.utils
import com.phone.model.Call
import scala.io.Source
import scala.collection.mutable.ListBuffer

object FileParser {

  def parseFile(fileName: String): List[String] = {
    Source.fromFile(fileName).getLines.filter(_.length > 0).toList
  }

  def parseCalls(callStrings: List[String]): List[Call] = {
    callStrings.map(callStringToCall(_))
  }

  def callStringToCall(callString: String): Call = {
    Call(callString)
  }
}
