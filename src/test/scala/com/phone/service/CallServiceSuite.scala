import org.scalatest.FunSuite
import com.phone.repository.CallRepository
import com.phone.model.Call
import com.phone.service.CallService

class CallServiceSuite extends FunSuite {
    var calls = List(Call("A", "1", 60), Call("A", "1", 120), Call("B", "1", 180),
      Call("A", "2", 200)) 
    var callRepository = CallRepository(calls)
    var callService = CallService(callRepository)

  test("getTotalAmountSpentPerCustomer returns correct result") {
    val totalSpentPerCustomer = callService.getTotalAmountSpentPerCustomer()
    assert(totalSpentPerCustomer(0).equals("A", 18.6))
    assert(totalSpentPerCustomer(1).equals("B", 9.0))
  }

  test("getTotalAmountSpentPerCustomer applies promotion") {
    val promoNumber = "1"
    val totalSpentPerCustomer = callService.getTotalAmountSpentPerCustomer(promoNumber)
    assert(totalSpentPerCustomer(0).equals("A", 9.6))
    assert(totalSpentPerCustomer(1).equals("B", 0.0))
  }

  test("getCallDurationsByPhoneNumber returns correct result") {
    val getCallDurationsByPhoneNumber = callService.getCallDurationsByPhoneNumber()
    assert(getCallDurationsByPhoneNumber.size.equals(2))
    assert(getCallDurationsByPhoneNumber("1").equals(360))
    assert(getCallDurationsByPhoneNumber("2").equals(200))
  }

}