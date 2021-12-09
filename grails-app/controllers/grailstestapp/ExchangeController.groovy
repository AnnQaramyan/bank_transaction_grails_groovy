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
        def myAm =  exchangeService.exchange(params.fromCur, params.toCur, params.amount)
        render myAm
    }
    def index() {

    }
}
