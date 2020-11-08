public class RandNum {
    private int randomInt;
    public RandNum(){
        this.randomInt = (int) (Math.ceil(Math.random() * 99) +1);
    }

    public int getRandomInt(){
        return randomInt;
    }

    public boolean isValidGuess(int userGuess){
        return userGuess >=0 && userGuess <= 100;
    }

}
