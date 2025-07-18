
## Sort
- Collections can be sorted using the `Collections.sort()` method for lists or by using the `Comparator` interface to define custom sorting logic.
- `Collections.sort()` requires that the elements in the list implement the `Comparable` interface or that a `Comparator` is provided `Collections.sort(list, comparator)`.
- Strings internally implement `Comparable`, allowing them to be sorted naturally. Whenever you implement `Comparable`, you must override the `compareTo` method to define the natural ordering of the objects.
- Basic example for sorting a list of strings:
```Java
public class Main {

    private static void printLine() {
        System.out.println("--------------------------------------------------");
    }

    public static List<String> getSongs() {
        List<String> songs = new ArrayList<>();
        songs.add("Havana");
        songs.add("Thankyou, Next");
        songs.add("Shape of You");
        songs.add("Despacito");
        songs.add("Perfect");
        return songs;
    }

    public static void main(String[] args) {
        List<String> songs = getSongs();
        System.out.println("Songs in the list:");

        // Write code in such a way that you just have to tell it what to do with the songs
        // And not how to do it
        // For example, print each song in the list
        songs.forEach(System.out::println);
        /*
            Havana
            Thankyou, Next
            Shape of You
            Despacito
            Perfect
        */

        printLine();

        // Sort the songs
        Collections.sort(songs);
        songs.forEach(System.out::println);
        /*
            Despacito
            Havana
            Perfect
            Shape of You
            Thankyou, Next
        */
    }
}
```
- If we have to compare two objects of a custom class, we can implement the `Comparable` interface in that class. For example:
```Java
public class Song implements Comparable<Song> {
    private String title;
    private String artist;
    
    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    Song(String title, String artist) {
        this.title = title;
        this.artist = artist;
    }

    @Override
    public String toString() {
        return title + " by " + artist;
    }

    // Since we are implementing Comparable, we need to override the compareTo method
    // This method will define how two Song objects are compared
    // compareTo method will be called on Song object and it accepts another Song object as a parameter
    // It should return a negative integer if this object is less than the specified object,
    // zero if they are equal, and a positive integer if this object is greater than the specified object.
    // In this case, we override it to compare songs based on their titles.
    // Since title is a String, we can just use the compareTo method of String class.
    @Override
    public int compareTo(Song o) {
        return this.title.compareTo(o.title);
    }
}


public class Main {

    private static void printLine() {
        System.out.println("--------------------------------------------------");
    }

    public static List<Song> getSongs() {
        List<Song> songs = new ArrayList<>();
        songs.add(new Song("Havana", "Camila Cabello"));
        songs.add(new Song("Thankyou, Next", "Ariana Grande"));
        songs.add(new Song("Shape of You", "Ed Sheeran"));
        songs.add(new Song("Despacito", "Luis Fonsi"));
        songs.add(new Song("Perfect", "Ed Sheeran"));
        return songs;
    }

    public static void main(String[] args) {
        List<Song> songs = getSongs();
        System.out.println("Songs in the list:");

        // Write code in such a way that you just have to tell it what to do with the songs
        // And not how to do it
        // For example, print each song in the list
        songs.forEach(System.out::println);
        /*
            Havana by Camila Cabello
            Thankyou, Next by Ariana Grande
            Shape of You by Ed Sheeran
            Despacito by Luis Fonsi
            Perfect by Ed Sheeran
        */

        printLine();

        // Sort the songs
        Collections.sort(songs);
        songs.forEach(System.out::println);
        /*
            Despacito by Luis Fonsi
            Havana by Camila Cabello
            Perfect by Ed Sheeran
            Shape of You by Ed Sheeran
            Thankyou, Next by Ariana Grande
        */
    }
}
```
- We can also use a custom `Comparator` to sort the songs based on different criteria, such as artist name or title length. For example:
```Java
public class Song {
    private String title;
    private String artist;
    
    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    Song(String title, String artist) {
        this.title = title;
        this.artist = artist;
    }

    @Override
    public String toString() {
        return title + " by " + artist;
    }
}

public class ArtistCompare implements Comparator<Song> {

    // Since we implementing Comparator, we need to override the compare method
    // This method will compare two Song objects based on their artist names
    // It returns a negative integer if the first song's artist is lexicographically less than the second's,
    // zero if they are equal, and a positive integer if the first song's artist is greater than the second's.
    @Override
    public int compare(Song song1, Song song2) {
        return song1.getArtist().compareTo(song2.getArtist());
    }
}


public class Main {

    private static void printLine() {
        System.out.println("--------------------------------------------------");
    }

    public static List<Song> getSongs() {
        List<Song> songs = new ArrayList<>();
        songs.add(new Song("Havana", "Camila Cabello"));
        songs.add(new Song("Thankyou, Next", "Ariana Grande"));
        songs.add(new Song("Shape of You", "Ed Sheeran"));
        songs.add(new Song("Despacito", "Luis Fonsi"));
        songs.add(new Song("Perfect", "Ed Sheeran"));
        return songs;
    }

    public static void main(String[] args) {
        List<Song> songs = getSongs();
        System.out.println("Songs in the list:");

        // Write code in such a way that you just have to tell it what to do with the songs
        // And not how to do it
        // For example, print each song in the list
        songs.forEach(System.out::println);
        /*
            Havana by Camila Cabello
            Thankyou, Next by Ariana Grande
            Shape of You by Ed Sheeran
            Despacito by Luis Fonsi
            Perfect by Ed Sheeran
        */

        printLine();

        // Now we want to sort the songs by artist name
        // We can use the ArtistCompare class to do that
        ArtistCompare artistCompare = new ArtistCompare();
        // We can pass the artistCompare object to the sort method
        Collections.sort(songs, artistCompare);
        
        
        // Sort the songs
        songs.forEach(System.out::println);
        /*            
            Thankyou, Next by Ariana Grande
            Havana by Camila Cabello
            Shape of You by Ed Sheeran
            Perfect by Ed Sheeran
            Despacito by Luis Fonsi
        */

        printLine();

        // But we can also use a lambda expression to do the same thing
        // This is a more concise way to write the same code
        List<Song> newSongs = getSongs();
        Collections.sort(newSongs, (song1, song2) -> song1.getArtist().compareTo(song2.getArtist()));
        newSongs.forEach(System.out::println);
        /*            
            Thankyou, Next by Ariana Grande
            Havana by Camila Cabello
            Shape of You by Ed Sheeran
            Perfect by Ed Sheeran
            Despacito by Luis Fonsi
        */
    }
}

```
