public class Magic {
    public static void main(String[] args) {
        // we will always print 3 no matter the vale of myNumber
        int myNumber = 5;
        // myNumber = 1 is the original
        int stepOne = myNumber * myNumber;
        int stepTwo = stepOne + myNumber;
        int stepThree = stepTwo / myNumber;
        int stepFour = stepThree + 17;
        int stepFive = stepFour - myNumber;
        int stepSix = stepFive / 6;
        System.out.println(stepSix);

        // different approach using logic
        int magicNumber = ((((((myNumber * myNumber) + myNumber) / myNumber) + 17) - myNumber) / 6);
        System.out.println(magicNumber);

    }
}