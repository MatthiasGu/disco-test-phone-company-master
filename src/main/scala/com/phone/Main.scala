package com.phone
import com.phone.utils.FileParser
import com.phone.repository.CallRepository
import com.phone.service.CallService

/**
  * This substitutes the API endpoint.
**/
object Main extends App {
  val testLink = "./src/main/resources/calls.log"
  val calls = FileParser.parseCalls(FileParser.parseFile(testLink))
  val callRepository = CallRepository(calls)
  val callService = CallService(callRepository)
  val callDurationsByNumber = callService.getCallDurationsByPhoneNumber()
  val promotionNumber = callDurationsByNumber.maxBy(_._2)._1  
  val totalSpentPerCustomer = callService.getTotalAmountSpentPerCustomer(promotionNumber)
  totalSpentPerCustomer.foreach(customer =>
    println("Customer " + customer._1 + " total spend -> GBP " + "%1.2f".format(customer._2))
  )
}
