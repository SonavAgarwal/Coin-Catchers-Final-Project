public class Timer implements Runnable {
    private int seconds = 184;
    private Screen screen;

    public Timer(Screen s) {
        screen = s;
    }

    public String getTimeString() {
        String s = (seconds / 60) + ":" + (seconds % 60);
        if (s.length() < 4) {
            if (seconds % 60 < 10) {
                s = (seconds / 60) + ":0" + (seconds % 60);
            } else {
                s += "0";
            }
        } 
        return s;
    }

    public void run() {
        while (seconds >= 0) {
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                System.out.println("porque");
            }

            // System.out.println(seconds);
            if (seconds <= 0) {
                screen.endGame();
            }
            seconds--;
        }
        
    }
}