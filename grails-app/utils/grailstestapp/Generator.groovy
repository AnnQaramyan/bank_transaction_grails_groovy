package grailstestapp

class Generator {
    static String generateRandomAccountNumber(){
        String number = ""
        for(int i =0; i<16;i++){
            number+=(int)(Math.random() * 10)
        }
        return number
    }
}
