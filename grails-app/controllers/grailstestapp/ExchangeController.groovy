package grailstestapp

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

class ExchangeController {
    def exchangeService
    static defaultAction = "exchange"

    def home() {
        render "<h1>Real Programmers do not eat Quiche</h1>"
    }
    def exchange(){
        Currency from = Currency.valueOf(params.fromCur)
        Currency to = Currency.valueOf(params.toCur)
        Double amount = Double.valueOf(params.amount)
        def myAm =  exchangeService.exchange(from, to, amount)
        render myAm
    }
    def index() {

    }
}
