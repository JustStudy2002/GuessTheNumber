import java.util.Random;

public class GenerateNumber {

    int numberGenerated = randomNumber();

    public int randomNumber(){
        Random random = new Random();

        int numberGenerated = random.nextInt(100);
        
        return numberGenerated;
    }
}