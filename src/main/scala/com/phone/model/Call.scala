package com.phone.model
case class Call(
  var customerId: String,
  var phoneNumber: String,
  var duration: Int) {

  def getCost() = {
    if (this.duration <= 180) {
      this.duration * 0.05
    } else {
      9 + (this.duration - 180) * 0.03
    }
  }
}

object Call {
  def apply() = new Call("", "", 0)
  def apply(callString: String) = {
    val params = callString.split(" ")
    new Call(params(0), params(1), durationToSeconds(params(2)))
  }

  def durationToSeconds(timeString: String) = {
    val hoursMinsSeconds = timeString.split(":")
    hoursMinsSeconds(0).toInt * 3600 + hoursMinsSeconds(1).toInt * 60 + hoursMinsSeconds(2).toInt
  }
}
