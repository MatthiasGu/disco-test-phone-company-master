package com.phone.utils
import com.phone.model.Call
import org.scalatest.FunSuite

class FileParserSuite extends FunSuite {

  val testLink = "./src/test/resources/calls_test.log"
  
  test("parseFile correctly parses a well-formatted file") {
    val parsedFile = FileParser.parseFile(testLink)
    assert(parsedFile(0).equals("A 555-333-212 00:02:03"))
    assert(parsedFile(1).equals("A 555-433-242 00:06:41"))
  }

  test("callStringToCall correctly converts a string to a call") {
    val callString = "A 555-333-212 00:02:03"
    val call = Call("A", "555-333-212", 123)
    assert(FileParser.callStringToCall(callString).equals(call))
  }

  test("parseCalls correctly converts a list of strings to a list of calls") {
    val parsedFile = FileParser.parseFile(testLink)
    val calls = FileParser.parseCalls(parsedFile)
    assert(calls(0).duration.equals(123))
    assert(calls(1).duration.equals(401))
  }
}