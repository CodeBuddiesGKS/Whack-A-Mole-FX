package whackamole;

import java.util.Random;

public class Game implements Runnable{

    public static final int ROWS = 3,
                            COLUMNS = 5,
                            TOTAL_MOLES = ROWS * COLUMNS,
                            MAX_MOLES_MISSED = 3;

    private static final boolean MOLE = true,
                                 NO_MOLE = false;

    private boolean[] moleHoles = new boolean[TOTAL_MOLES];
    private volatile int molesRemaining,
                         molesMissed,
                         molesWhacked;

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
        molesWhacked++;
    }

    public void moleLeftAt(int index){
        if(molesMissed >= MAX_MOLES_MISSED)
            return;

        moleHoles[index] = false;
        molesRemaining--;
        molesMissed++;
        field.moleMissed();
    }

    public int getMolesWhacked(){
        return molesWhacked;
    }

    public int getMolesMissed(){
        return molesMissed;
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

            while(molesRemaining > 0 && molesMissed < MAX_MOLES_MISSED){}

            if(molesMissed >= MAX_MOLES_MISSED) {
                isRunning = false;
                field.endScreen();
            }
        }

        System.out.println("Game Over");
    }

    private void setupMoles(){
        System.out.println("Setup");
        int nextLocation;

        // Number of moles to set this round
        molesRemaining = 8;//rng.nextInt(8) + 3;

        // Set the logical moles
        for(int i = 0; i < molesRemaining;){
            nextLocation = rng.nextInt(TOTAL_MOLES);
            if(moleHoles[nextLocation] == NO_MOLE) {
                moleHoles[nextLocation] = MOLE;
                field.drawMoleAt(nextLocation);
                i++;
            }
        }
    }
}
