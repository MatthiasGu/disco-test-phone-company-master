package com.phone.repository
import com.phone.model.Call
import com.phone.utils.FileParser
import org.scalatest.FunSuite
import scala.collection.mutable.ListBuffer

class CallRepositorySuite extends FunSuite {

  val testLink = "./src/test/resources/calls_test.log"

  test("CallRepository is constructed correctly with no calls") {
    val calls = List[Call]()
    val callRepository = CallRepository()
    assert(callRepository.getCalls().equals(calls))
  }

  test("CallRepository is constructed correctly with two calls") {
    val parsedFile = FileParser.parseFile(testLink)
    val calls = parsedFile.map(callString => Call(callString))
    val callRepository = CallRepository(calls)
    assert(callRepository.getCalls().length.equals(2))
  }

  test("getCustomers returns correct customer with no duplicates") {
    val parsedFile = FileParser.parseFile(testLink)
    val calls = parsedFile.map(callString => Call(callString))
    val callRepository = CallRepository(calls)
    assert(callRepository.getCustomers().size.equals(1))
    assert(callRepository.getCustomers().head.equals("A"))
  }

  test("getPhoneNumbers returns correct numbers with no duplicates") {
    val calls = List(Call("A", "1", 60), Call("A", "1", 120), Call("B", "2", 180),
      Call("A", "2", 200)) 
    val callRepository = CallRepository(calls)
    assert(callRepository.getPhoneNumbers().size.equals(2))
    assert(callRepository.getPhoneNumbers().lift(0).equals(Some("1")))
    assert(callRepository.getPhoneNumbers().lift(1).equals(Some("2")))
  }

  test("getCallsForCustomer returns correct calls") {
    val calls = List(Call("A", "", 60), Call("A", "", 120), Call("B", "", 180)) 
    val callRepository = CallRepository(calls)
    assert(callRepository.getCallsForCustomer("A").length.equals(2))
    assert(callRepository.getCallsForCustomer("B").length.equals(1))
  }

  test("getTotalDurationForNumber returns correct result") {
    val calls = List(Call("A", "1", 60), Call("A", "1", 120), Call("B", "1", 180),
      Call("A", "2", 200)) 
    val callRepository = CallRepository(calls)
    assert(callRepository.getTotalDurationForNumber("1").equals(360))
    assert(callRepository.getTotalDurationForNumber("2").equals(200))
  }


}