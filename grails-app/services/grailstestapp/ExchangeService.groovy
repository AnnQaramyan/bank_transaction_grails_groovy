package grailstestapp

import grails.gorm.transactions.Transactional

@Transactional
class ExchangeService {

    def exchange(String fromCur, String toCur, String amount) {
        def fromRate = Rate.withCriteria {
            eq('currency',Currency.valueOf(fromCur))
        }
        def fromAmount = fromRate.amount[0]
        def toRate = Rate.withCriteria {
            eq('currency',Currency.valueOf(toCur))
        }
        def toAmount = toRate.amount[0]
        def countedAmount = Double.valueOf(amount)*fromAmount/toAmount
        return countedAmount
    }
}
