package grailstestapp

import com.google.gson.Gson
import grails.gorm.transactions.Transactional
import org.springframework.web.client.RestTemplate

class RateSaverJob {
    static restTemplate = new RestTemplate()
    static gson = new Gson()
    static curNames = ['USD','EUR','GBP','RUB','AMD']

    static triggers = {
        cron name:   'cronTrigger',   startDelay: 100, cronExpression: '0 42 19 * * ? *'
    }
    @Transactional
    def execute(){
        Date currentDate = new Date()
        Rate.executeUpdate("update Rate r set r.end_date=(:date) where r.end_date is null", [date:currentDate])
        Rate rate
        Currency itToCurrency
        Object resp
        String currentRateJson
        Double amdRate
        curNames.each{
            currentRateJson = restTemplate.getForObject("https://v6.exchangerate-api.com/v6/e336a1af4cebcd34e7ed8114/latest/${it}",
                    String.class)
            resp = gson.fromJson(currentRateJson,Object.class)
            amdRate = resp.conversion_rates.AMD;
            itToCurrency = Currency.valueOf(it);
            rate = new Rate(currency: itToCurrency, amount: amdRate, start_date: currentDate)
            rate.save()
        }

    }
}
