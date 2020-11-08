public class Account {
    private static long nextAccountNumber =10001L;

    private long accountNumber;
    private int pointBalance;

    public Account(){
        this.accountNumber = nextAccountNumber++;
        this.pointBalance = 0;
    }

    public long getAccountNumber(){
        return accountNumber;
    }

    public static long getNextAccountNumber(){return nextAccountNumber;}

    public int getPointBalance(){
        return pointBalance;
    }


    public int addPoints(int points){
        pointBalance += points;
        return PointATM.SUCCESS;
    }

    public int transferPoints(Account account, int pointsTransferred){
        int status = upgrade(pointsTransferred);
        if(status == PointATM.SUCCESS){
            return account.addPoints(pointsTransferred);
        }
        else{
            return status;
        }
    }

    public int upgrade(int pointsTransferred){

        if(pointBalance < pointsTransferred){
            return PointATM.INSUFFICIENT;
        }
        else {
            pointBalance = pointBalance - pointsTransferred;
        }
        return PointATM.SUCCESS;
    }
}
