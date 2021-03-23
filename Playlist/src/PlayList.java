import java.util.ArrayList;

class Playlist {

    public static void main(String[] args) {
        ArrayList<String> desertIslandPlaylist = new ArrayList<String>();
        desertIslandPlaylist.add("Control the Sky");
        desertIslandPlaylist.add("Rackets");
        desertIslandPlaylist.add("before i met you");
        desertIslandPlaylist.add("Brighter Days");
        desertIslandPlaylist.add("Sun Dog");
        desertIslandPlaylist.add("Let Me Get You A Glass");
        desertIslandPlaylist.add("Awake");
        desertIslandPlaylist.add("Inner Peace");
        desertIslandPlaylist.add("Fireflies");
        System.out.println(desertIslandPlaylist);

        System.out.println(desertIslandPlaylist.size());
        desertIslandPlaylist.remove("Inner Peace");
        desertIslandPlaylist.remove("Brighter Days");
        desertIslandPlaylist.remove("Let Me Get You A Glass");
        desertIslandPlaylist.remove("Rackets");
        System.out.println(desertIslandPlaylist.size());

        System.out.println("Before the swap: " + desertIslandPlaylist);

        int songA = desertIslandPlaylist.indexOf("Fireflies");
        int songB = desertIslandPlaylist.indexOf("Sun Dog");
        String tempA = "Fireflies";
        desertIslandPlaylist.set(songA, "Sun Dog");
        System.out.println("During the swap: " + desertIslandPlaylist);
        desertIslandPlaylist.set(songB, tempA);
        System.out.println("After the swap: " + desertIslandPlaylist);


    }

}