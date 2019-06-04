package com.phone.repository
import com.phone.model.Call
import scala.collection.mutable.ListBuffer

/**
  * This is an in-memory repository. In a real application this would be database access layer.
**/
case class CallRepository(var calls: List[Call]) {

  def getCalls(): List[Call] = {
    return this.calls
  }

  def getCustomers(): List[String] = {
    return this.calls.map(_.customerId).distinct
  }

  def getPhoneNumbers(): List[String] = {
    return this.calls.map(_.phoneNumber).distinct
  }

  def getCallsForCustomer(customerId: String): List[Call] = {
    return this.calls.filter(_.customerId.equals(customerId))
  }

  def getTotalDurationForNumber(phoneNumber: String): Int = {
    return this.calls
      .filter(_.phoneNumber.equals(phoneNumber))
      .map(_.duration)
      .reduce(_+_)    
  }
}

object CallRepository {
  var calls = List[Call]()
  def apply() = new CallRepository(calls)
}