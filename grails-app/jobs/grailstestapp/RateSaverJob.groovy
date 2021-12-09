package grailstestapp

import com.google.gson.Gson
import org.springframework.web.client.RestTemplate

class RateSaverJob {
    static restTemplate = new RestTemplate()
    static gson = new Gson()
    static curNames = ['USD','EUR','GBP','RUB','AMD']

    static triggers = {
        cron name:   'cronTrigger',   startDelay: 100, cronExpression: '0 10 16 * * ? *'
    }
    def execute(){
        Rate rate;
        curNames.each{
            def currentRate = restTemplate.getForObject("https://v6.exchangerate-api.com/v6/e336a1af4cebcd34e7ed8114/latest/${it}",
                    String.class)
            def resp = gson.fromJson(currentRate,Object.class)
            rate = new Rate(currency: Currency.valueOf(it), amount: resp.conversion_rates.AMD, start_date: new Date())
            rate.save()
            //println "${resp.conversion_rates.AMD}"
        }

    }
}
