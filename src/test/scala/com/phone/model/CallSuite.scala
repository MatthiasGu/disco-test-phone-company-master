package com.phone.model
import org.scalatest.FunSuite

class CallSuite extends FunSuite {

  val customerId = "A"
  val phoneNumber = "555-433-242"
  val duration = 60
  var callString = "A 555-333-212 00:02:00"

  test("Call is constructed correctly with all parameters") {
    val call = Call(customerId, phoneNumber, duration)
    assert(call.customerId.equals(customerId))
    assert(call.phoneNumber.equals(phoneNumber))
    assert(call.duration.equals(duration))
  }

  test("Call is constructed correctly with no parameters") {
    val call = Call()
    assert(call.customerId.equals(""))
    assert(call.phoneNumber.equals(""))
    assert(call.duration.equals(0))
  }

  test("Call is constructed correctly with all parameters from a String") {
    val call = Call(callString)
    assert(call.customerId.equals("A"))
    assert(call.phoneNumber.equals("555-333-212"))
    assert(call.duration.equals(120))
  }

  test("Call with duration 00:00:00 costs nothing") {
    val call = Call("","", 0)
    assert(call.getCost().equals(0.0))
  }

  test("Call with duration less than 3 minutes costs 5 pence per second") {
    val call = Call(callString)
    assert(call.getCost().equals(6.0))
  }

    test("Call with duration more than 3 minutes has promotion applied") {
    callString = "A 555-333-212 00:05:00"
    val call = Call(callString)
    assert(call.getCost().equals(12.6))
  }
}