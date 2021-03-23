public class DNA {

    public static void main(String[] args) {

        // A program that determines whether there is a protein
        // in a strand of DNA.
        //  -. .-.   .-. .-.   .
        //    \   \ /   \   \ /
        //   / \   \   / \   \
        //  ~   `-~ `-`   `-~ `-

        String dna1 = "ATGCGATACGCTTGA";
        String dna2 = "ATGCGATACGTGA";
        String dna3 = "ATTAATATGTACTGA";
        String startCodon = "ATG";
        String stopCodon = "TGA";

        // generic var to test each dna sequence
        String dna = dna3;
        System.out.println(dna.length());
        System.out.println("Start of Codon: index "+dna.indexOf(startCodon));
        System.out.println("End of Codon: index "+dna.indexOf(stopCodon));
        int codonStart = dna.indexOf(startCodon);
        int codonStop = dna.indexOf(stopCodon);

        if(codonStart != -1 && codonStop != -1 && (codonStart - codonStop) % 3 == 0){
            System.out.println("Condition 1 and 2 are satisfied.");
            System.out.println(dna+" strand has a protein.");
            String protein = dna.substring(codonStart,codonStop+3);
            System.out.println("Protein: "+protein);
        }else{
            System.out.println(dna+" strand has No protein.");
        }


    }

}