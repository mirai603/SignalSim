

public class sim {
    int signals, Apity, Spity, rateS, offS, anyA;
    Boolean guarantee;

    
    public sim(int signals, Boolean guarantee, int Apity, int Spity) {
        this.signals = signals;
        this.guarantee = guarantee;
        this.Apity = Apity;
        this.Spity = Spity;

        this.rateS = 0;
        this.offS = 0;
        this.anyA = 0;
    }

    public void pull() {
        signals -= 1;
        Apity += 1;
        Spity += 1;

        double rng = Math.random();
        if (Spity < 74) {
            if (rng < 0.006) {
                rateUpS();
                return;
            }
        } else {
            if (rng < (Spity-73) * 0.0585 + 0.06) {
                rateUpS();
                return;
            }
        }

        if (Apity == 10) {
            anyA++;
            resetA();
            return;
        } else {
            if (rng < 0.1) {
                anyA++;
                resetA();
                return;
            }
        }
    }

    public void resetS() {
        Spity = 0;
        Apity = 0;
    }

    public void resetA() {
        Apity = 0;
    }

    public Boolean rateUpS() {
        resetS();
        if (guarantee || Math.random() < 0.5) {
            rateS++;
            guarantee = false;
            return true;
        } else {
            offS++;
            guarantee = true;
            return false;
        }
    }

    public Boolean hasSignal() {
        return signals > 0;
    }

    @Override
    public String toString() {
        return String.format("""
                rate Up S:    %d
                off banner S: %d
                any A:        %d
                pity:         %d
                guarantee:    %b
                """, rateS, offS, anyA, Spity, guarantee);
    }

    public static void main(String[] args) {
        sim sim = new sim(300, false, 0, 0);
        while (sim.hasSignal()) {
            sim.pull();
        }
        System.err.println(sim.toString());
    }

}
