public class Language {
    protected String name;
    protected int numSpeakers;
    protected String regionsSpoken;
    protected String wordOrder;

    Language(String langName, int speakers, String regions, String wdOrder){
        this.name = langName;
        this.numSpeakers = speakers;
        this.regionsSpoken = regions;
        this.wordOrder = wdOrder;
    }

    public void getInfo(){
        System.out.println(this.name + " is spoken by " + this.numSpeakers + " people mainly in " + this.regionsSpoken + ".");
        System.out.println("The language follows the word order: " + this.wordOrder);
    }

    public static void main(String[] args){
        Language english = new Language("English",10,"Worldwide","verb-subject-object");
        english.getInfo();

        Language mayan = new Mayan();
        mayan.getInfo();

        Language burmese = new SinoTibetan("Burmese",10000);
        burmese.getInfo();

        Language chinese = new SinoTibetan("Mandarin Chinese", 10000);
        chinese.getInfo();


    }
}