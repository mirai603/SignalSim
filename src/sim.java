

public class sim {

    public class trial {
        int signals, Apity, Spity, rateS, offS, anyA, currResidual, totalResidual, convertedSignal;
        Boolean guarantee;
        public trial(int signals, Boolean guarantee, int Apity, int Spity) {
            this.signals = signals;
            this.guarantee = guarantee;
            this.Apity = Apity;
            this.Spity = Spity;
            this.rateS = 0;
            this.offS = 0;
            this.anyA = 0;
            this.currResidual = 0;
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
                currResidual+=8;
                resetA();
                return;
            } else {
                if (rng < 0.1) {
                    anyA++;
                    currResidual+=8;
                    resetA();
                    return;
                }
            }

            if (currResidual >= 20) {
                currResidual-=20;
                signals++;
                convertedSignal++;
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
            int curr = Spity;
            resetS();
            currResidual+=20;
            if (guarantee || Math.random() < 0.5) {
                rateS++;
                if (guarantee) {
                    System.out.printf("guarantee at %d\n", curr);
                } else {
                    System.out.printf("won at %d\n", curr);
                }
                guarantee = false;
                return true;
            } else {
                offS++;
                guarantee = true;
                System.out.printf("lost at %d\n", curr);
                return false;
            }
        }
    
        public Boolean hasSignal() {
            return signals > 0;
        }
    
        @Override
        public String toString() {
            return String.format("""
                    \nrate Up S:    %d
                    off banner S: %d
                    any A:        %d
                    pity:         %d
                    guarantee:    %b
                    shop signal:  %d
                    """, rateS, offS, anyA, Spity, guarantee, convertedSignal);
        }
    }

    int signals, Apity, Spity;
    Boolean guarantee;
    public sim(int signals, Boolean guarantee, int Apity, int Spity) {
        this.signals = signals;
        this.guarantee = guarantee;
        this.Apity = Apity;
        this.Spity = Spity;
    }

    public void pull() {
        trial sim = new trial(300, false, 0, 0);
        while (sim.hasSignal()) {
            sim.pull();
        }
        System.out.println(sim.toString());
    }

    public void extractData(trial t) {
        
    }

    

    
    public static void main(String[] args) {
        sim sim = new sim(300, false, 0, 0);
        sim.pull();
    }

}
