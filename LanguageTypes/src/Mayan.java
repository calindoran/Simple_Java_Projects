class Mayan extends Language {
    Mayan(){
        super("Ki'che'",2330000,"Central America","verb-object-subject");
    }

    @Override
    public void getInfo(){
        System.out.println(this.name + " is spoken by " + this.numSpeakers + " people mainly in " + this.regionsSpoken + ".");
        System.out.println("The language follows the word order: " + this.wordOrder);
        System.out.println("Fun fact: Ki'che' is an ergative language.");
    }

}