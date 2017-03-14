package whackamole;

import java.util.Random;

public class Game implements Runnable{

    public static final int ROWS = 3,
                            COLUMNS = 5,
                            TOTAL_MOLES = ROWS * COLUMNS;
    private static final boolean MOLE = true,
                                 NO_MOLE = false;

    private boolean[] moleHoles = new boolean[TOTAL_MOLES];
    private volatile int molesRemaining;

    private boolean isRunning;
    private Random rng = new Random();
    private FieldController field;
    private Thread gameThread;

    public Game(FieldController field) {
        this.field = field;
    }

    public void whackMole(int index){
        moleHoles[index] = NO_MOLE;
        molesRemaining--;
    }

    public void start(){
        if(isRunning)
            return;

        isRunning = true;

        gameThread = new Thread(this, "Thread: Whack-A-Mole!");
        gameThread.start();
    }

    @Override
    public void run(){
        System.out.println("Game Started");

        while(isRunning){
            setupMoles();

            while(molesRemaining > 0){}
        }
    }

    private void setupMoles(){
        System.out.println("Setup");
        int nextLocation;

        // Number of moles to set this round
        molesRemaining = rng.nextInt(3) + 1;

        // Set the logical moles
        for(int i = 0; i < molesRemaining;){
            nextLocation = rng.nextInt(9);
            if(moleHoles[nextLocation] == NO_MOLE) {
                moleHoles[nextLocation] = MOLE;
                field.drawMoleAt(nextLocation);
                i++;
            }
        }
    }
}
