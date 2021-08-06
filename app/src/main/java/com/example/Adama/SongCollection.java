package com.example.Adama;



public class SongCollection {
    public Song songs[] = new Song[12];

    public SongCollection() {
        Song savage = new Song("S1001",
                "Savage Love",
                "Jason Derulo",
                "https://p.scdn.co/mp3-preview/efd5dc3b16c735e2cf39d3e27fce6ce36a26fbdb?cid=2afe87a64b0042dabf51f37318616965",
                3.08,
                R.drawable.savage);

        Song stereo = new Song("S1002",
                "Stereo Heart ",
                "Adam Levine",
                "https://p.scdn.co/mp3-preview/649de18156e9351fcbb8471c93a4ff20e427b25b?cid=2afe87a64b0042dabf51f37318616965",
                3.52,
                R.drawable.steriohearts);

        Song attention = new Song("S1003",
                "Attention",
                "Charlie Puth",
                "https://p.scdn.co/mp3-preview/d7369d506a0647ccd433c7d0ec290d8be52c186f?cid=2afe87a64b0042dabf51f37318616965",
                3.81,
                R.drawable.attention);

        Song love = new Song("S1004",
                "Your Love Could Start a War",
                "The Unlikely Candidates",
                "https://p.scdn.co/mp3-preview/a90d314012f43d497d8a00fb66dd0c7dca547fec?cid=2afe87a64b0042dabf51f37318616965",
                3.38,
                R.drawable.yourlovecould_start);

        Song dua = new Song("S1005",
                "Levitating",
                "Dua Lipa",
                "https://p.scdn.co/mp3-preview/a690735072e85bebf1140d542365751629ae3d74?cid=2afe87a64b0042dabf51f37318616965",
                3.38,
                R.drawable.dualipa);

        Song mess = new Song("S1006",
                "I'm a Mess",
                "Ed Sheeran",
                "https://p.scdn.co/mp3-preview/32028b3d85964481a53e42865121ff2e38ab4acf?cid=2afe87a64b0042dabf51f37318616965",
                4.08,
                R.drawable.mess);

        Song play = new Song("S1007",
                "Play With Fire",
                "Sam Tinnesz",
                "https://p.scdn.co/mp3-preview/671348431593e4495725db99d3eddda857ff990f?cid=2afe87a64b0042dabf51f37318616965",
                3.22,
                R.drawable.play);

        Song thriller  = new Song("S1008",
                "Thriller",
                "Michael Jackson",
                "https://p.scdn.co/mp3-preview/49800591d0107ecc0f3acc67eb01785b35503988?cid=2afe87a64b0042dabf51f37318616965",
                5.96,
                R.drawable.thriller);

        Song shape = new Song("S1009",
                "Shape Of You",
                "Ed Sheeran",
                "https://p.scdn.co/mp3-preview/84462d8e1e4d0f9e5ccd06f0da390f65843774a2?cid=2afe87a64b0042dabf51f37318616965",
                3.9,
                R.drawable.shape_of_you);

        Song alone = new Song("S1010",
                "Alone Pt.2",
                "Alan Walker",
                "https://p.scdn.co/mp3-preview/fea1cee8c2bd09201d6d6a64beff9899d0eec901?cid=2afe87a64b0042dabf51f37318616965",
                3.28,
                R.drawable.alone);

        Song dusk  = new Song("S1011",
                "Dusk Till Dawn",
                "Zayn Malik",
                "https://p.scdn.co/mp3-preview/e2e03acfd38d7cfa2baa924e0e9c7a80f9b49137?cid=2afe87a64b0042dabf51f37318616965",
                3.98,
                R.drawable.dusk_till_dawn);

        Song havana = new Song("S1012",
                "Havana",
                "Camilo Cabello",
                "https://p.scdn.co/mp3-preview/67099e2d84e803937526789d036e2ab68d443bbe?cid=2afe87a64b0042dabf51f37318616965",
                4.14,
                R.drawable.havana);

        songs[0] = savage;
        songs[1] = stereo;
        songs[2] = attention;
        songs[3] = love;
        songs[4] = dua;
        songs[5] = mess;
        songs[6] = play;
        songs[7] = thriller;
        songs[8] = shape;
        songs[9] = alone;
        songs[10] = dusk;
        songs[11] = havana;
    }
    public Song getCurrentSong(int currentSongId) {return songs[currentSongId];}
    public int searchSongById(String id){
        for(int index=0; index < songs.length; index++){
            Song tempSong = songs[index];
            if(tempSong.getId().equals(id)){
                return index;
            }
        }
        return -1;
    }
    public int getNextSong(int currentSongIndex){
        if(currentSongIndex >= songs.length-1){
            return currentSongIndex;
        }
        else{
            return currentSongIndex+1;
        }
    }
    public int getPrevSong(int currentSongIndex) {
        if (currentSongIndex <= 0) {
            return currentSongIndex;
        } else {
            return currentSongIndex - 1;
        }
    }
}

