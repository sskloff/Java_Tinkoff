package edu.project1;

public enum Gallows {

    StepZero {
        @Override
        public String toString() {
            return "\n\n +----+\n      |\n      |\n      |\n      |\n      |\n=========";

        }
    },
    StepOne {
        @Override
        public String toString() {
            return "\n\n +----+\n |    |\n      |\n      |\n      |\n      |\n=========";
        }
    },
    StepTwo {
        @Override
        public String toString() {
            return "\n\n +----+\n |    |\n O    |\n      |\n      |\n      |\n=========";
        }
    },
    StepThree {
        @Override
        public String toString() {
            return "\n\n +----+\n |    |\n O    |\n |    |\n      |\n      |\n=========";
        }
    },
    StepFour {
        @Override
        public String toString() {
            return "\n\n +----+\n |    |\n O    |\n/|    |\n      |\n      |\n=========";
        }
    },
    StepFive {
        @Override
        public String toString() {
            return "\n\n +----+\n |    |\n O    |\n/|\\   |\n      |\n      |\n=========";
        }
    },
    StepSix {
        @Override
        public String toString() {
            return "\n\n +----+\n |    |\n O    |\n/|\\   |\n/     |\n      |\n=========";
        }
    },
    StepSeven {
        @Override
        public String toString() {
            return "\n\n +----+\n |    |\n O    |\n/|\\   |\n/ \\   |\n      |\n=========";
        }
    }
}


