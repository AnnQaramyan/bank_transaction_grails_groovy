package grailstestapp

import grails.gorm.transactions.Transactional

@Transactional
class ExchangeService {

    def exchange(Currency fromCur, Currency toCur, Double amount) {
        def fromRate = Rate.withCriteria {
            eq('currency',fromCur)
            isNull('end_date')
        }
        Double fromAmount = (Double)fromRate.amount[0]
        def toRate = Rate.withCriteria {
            eq('currency',toCur)
            isNull('end_date')
        }
        Double toAmount = (Double)toRate.amount[0]
        def countedAmount = (amount * fromAmount)/ toAmount
        return countedAmount
    }
}
