package by.epam.lab;


    public class Byn implements Comparable<Byn> {
        private int kopecks;

        public Byn() {
        }

        public Byn(int value) {
            kopecks = value;
        }

        public Byn(Byn byn) {
            kopecks = byn.kopecks;
        }

        @Override
        public Byn clone() {
            return new Byn(kopecks);
        }

        public Byn add(Byn byn) {
            kopecks += byn.kopecks;
            return this;
        }

        public Byn subtract(Byn byn) {
            kopecks -= byn.kopecks;
            return this;
        }

        public Byn multiply(int value) {
            kopecks *= value;
            return this;
        }

        public Byn multiply(double value) {
            kopecks = (int) Math.round(this.kopecks * value);
            return this;
        }


        @Override
        public String toString() {
            return String.format("%d.%02d", kopecks / 100, kopecks % 100);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Byn byn = (Byn) o;
            return kopecks == byn.kopecks;
        }

        @Override
        public int compareTo(Byn o) {
            return kopecks - o.kopecks;
        }
    }
