package com.phone.service
import com.phone.repository.CallRepository

case class CallService(callRepository: CallRepository) {

  def getTotalAmountSpentPerCustomer(promotionNumber: String = ""): List[(String, Double)] = {
    val customers = this.callRepository.getCustomers()
    println(customers)
    customers
      .map(customer => (customer, this.callRepository
        .getCallsForCustomer(customer)
        .filter(_.phoneNumber != promotionNumber)
        .map(_.getCost())
        .reduceOption(_+_)
        .getOrElse(0.0)))
  }

  def getCallDurationsByPhoneNumber(): Map[String, Int] = {
    val phoneNumbers = this.callRepository.getPhoneNumbers()
    phoneNumbers
      .map(number => (number, this.callRepository.getTotalDurationForNumber(number))).toMap
  }
}

object CallService {
  def apply(callRepository: CallRepository) = new CallService(callRepository)
}