package com.reinarc.snowmangame;

import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainGame extends AppCompatActivity {

    Bundle extra;
    TextView nameDisplay;
    TextView difficultyDisplay;
    TextView displayGuesses;
    TextView attempts;
    TextView results;
    String givenWord;
    String[] displaySpaces;
    String valueStr = "";
    int livesLeft;
    boolean mayPressButtons;

    final String[] wordList = {"Adequate", "Anywhere", "Approach", "Activity", "Anything", "Approval", "Advanced", "Although", "Academic", "Advocate", "Accident", "Athletic", "Aircraft", "Activist", "Actually", "Accurate", "Apparent", "Argument", "Analysis", "Audience", "Announce", "Alliance", "Artistic", "Addition", "Absolute", "Attitude", "Birthday", "Behavior", "Bathroom", "Boundary", "Building", "Business", "Capacity", "Champion", "Campaign", "Convince", "Conflict", "Changing", "Ceremony", "Clothing", "Category", "Complain", "Complete", "Computer", "Coverage", "Creative", "Confront", "Conclude", "Civilian", "Concrete", "Critical", "Criminal", "Clinical", "Collapse", "Customer", "Contract", "Consumer", "Consider", "Cultural", "Criteria", "Constant", "Creature", "Creation", "Continue", "Contrast", "Delivery", "Discover", "Directly", "Darkness", "Document", "Dramatic", "Daughter", "Describe", "Division", "Distinct", "Distance", "Decision", "District", "Decrease", "Director", "Dominant", "Dominate", "Designer", "Disorder", "Dialogue", "Detailed", "Disagree", "Disaster", "Exchange", "Everyday", "Exciting", "Exposure", "Exercise", "Existing", "External", "Employer", "Employee", "Emphasis", "Economic", "Everyone", "Evidence", "Electric", "Entirely", "Educator", "Evaluate", "Estimate", "Entrance", "Enormous", "Emission", "Engineer", "Earnings", "Frequent", "Facility", "Fighting", "Friendly", "Favorite", "Familiar", "Football", "Function", "Graduate", "Generate", "Greatest", "Historic", "Hospital", "Headline", "Heritage", "Identify", "Involved", "Industry", "Identity", "Incident", "Investor", "Indicate", "Instance", "Increase", "Innocent", "Interest", "Internal", "Internet", "Judgment", "Lifetime", "Literary", "Language", "Location", "Learning", "Magazine", "Majority", "Movement", "Military", "Minority", "Moreover", "Multiple", "Mortgage", "Musician", "Marriage", "Moderate", "Material", "Mountain", "Maintain", "Neighbor", "Normally", "Negative", "Northern", "Numerous", "National", "Organize", "Official", "Overlook", "Overcome", "Observer", "Opponent", "Ordinary", "Occasion", "Opposite", "Operator", "Original", "Physical", "Probably", "Publicly", "Powerful", "Purchase", "Possibly", "Properly", "Property", "Province", "Platform", "Perceive", "Prospect", "Provider", "Practice", "Priority", "Preserve", "Producer", "Proposed", "Positive", "Previous", "Proposal", "Presence", "Possible", "Politics", "Painting", "Pregnant", "Progress", "Persuade", "Planning", "Personal", "Pleasure", "Portrait", "Position", "Prisoner", "Pressure", "Question", "Recovery", "Remember", "Recently", "Research", "Resemble", "Romantic", "Relative", "Relevant", "Resource", "Response", "Reporter", "Reaction", "Regulate", "Resident", "Register", "Regional", "Religion", "Relation", "Sequence", "Specific", "Somewhat", "Somebody", "Shopping", "Slightly", "Survival", "Survivor", "Software", "Schedule", "Supposed", "Suddenly", "Security", "Shoulder", "Strongly", "Strength", "Strategy", "Straight", "Spending", "Shooting", "Southern", "Standard", "Scenario", "Surprise", "Separate", "Struggle", "Sentence", "Standing", "Sanction", "Stranger", "Surround", "Solution", "Thinking", "Teaching", "Tendency", "Tomorrow", "Together", "Thousand", "Threaten", "Transfer", "Terrible", "Teaspoon", "Teenager", "Training", "Unlikely", "Universe", "Ultimate", "Vacation", "Valuable", "Variable", "Workshop", "Withdraw", "Whatever", "Whenever", "Yourself"};

    Button a;
    Button b;
    Button c;
    Button d;
    Button e;
    Button f;
    Button g;
    Button h;
    Button i;
    Button j;
    Button k;
    Button l;
    Button m;
    Button n;
    Button o;
    Button p;
    Button q;
    Button r;
    Button s;
    Button t;
    Button u;
    Button v;
    Button w;
    Button x;
    Button y;
    Button z;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_game);






        extra = getIntent().getExtras();

        String name = extra.getString("name");
        String difficulty = extra.getString("difficulty");

        nameDisplay = findViewById(R.id.nameDisplay);
        difficultyDisplay = findViewById(R.id.difficultyDisplay);
        displayGuesses = findViewById(R.id.displayWordToGuess);
        attempts = findViewById(R.id.attempts);
        results = findViewById(R.id.results);
        mayPressButtons = true;

        assert difficulty != null;
        livesLeft = difficulty.equals("Hard") ? 3 : 8;

        a = findViewById(R.id.a);
        b = findViewById(R.id.b);
        c = findViewById(R.id.c);
        d = findViewById(R.id.d);
        e = findViewById(R.id.e);
        f = findViewById(R.id.f);
        g = findViewById(R.id.g);
        h = findViewById(R.id.h);
        i = findViewById(R.id.i);
        j = findViewById(R.id.j);
        k = findViewById(R.id.k);
        l = findViewById(R.id.l);
        m = findViewById(R.id.m);
        n = findViewById(R.id.n);
        o = findViewById(R.id.o);
        p = findViewById(R.id.p);
        q = findViewById(R.id.q);
        r = findViewById(R.id.r);
        s = findViewById(R.id.s);
        t = findViewById(R.id.t);
        u = findViewById(R.id.u);
        v = findViewById(R.id.v);
        w = findViewById(R.id.w);
        x = findViewById(R.id.x);
        y = findViewById(R.id.y);
        z = findViewById(R.id.z);


        attempts.setText("Lives: " + livesLeft);
        nameDisplay.setText("Player Name: " + name);
        difficultyDisplay.setText("Difficulty: " + difficulty);

        givenWord = generateWord();
        int givenWordLength = givenWord.length();

        displaySpaces = new String[givenWordLength];

        valueStr = updateSpace(displaySpaces, givenWord, "", true);
        displayGuesses.setText(valueStr);

    }

    public String updateSpace(String[] arr, String str, String guess, boolean init){
        String blankStr = "";


        if (init) {
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] == null) {
                    arr[i] = "_";
                }
            }
        } else {
            Character g = guess.charAt(0);
            for (int i = 0; i < arr.length; i++){
                Character c = str.charAt(i);
                if (c.equals(g)){
                    arr[i] = String.valueOf(g);
                }
            }
        }

        for (int i = 0; i < arr.length; i++){
            if (str.charAt(i) == ' '){
                displaySpaces[i] = " ";
                blankStr = blankStr + "    ";
            } else {
                blankStr = blankStr + arr[i] + "   ";
            }
        }

        return blankStr;
    }

    public void checkGuess(View view){
        if (mayPressButtons){
            String guess = "";

            if (view.getId() == a.getId()){
                guess = "a";
                a.setBackgroundColor(Color.GRAY);
            }
            if (view.getId() == b.getId()){
                guess = "b";
                b.setBackgroundColor(Color.GRAY);
            }
            if (view.getId() == c.getId()){
                guess = "c";
                c.setBackgroundColor(Color.GRAY);
            }
            if (view.getId() == d.getId()){
                guess = "d";
                d.setBackgroundColor(Color.GRAY);
            }
            if (view.getId() == e.getId()){
                guess = "e";
                e.setBackgroundColor(Color.GRAY);
            }
            if (view.getId() == f.getId()){
                guess = "f";
                f.setBackgroundColor(Color.GRAY);
            }
            if (view.getId() == g.getId()){
                guess = "g";
                g.setBackgroundColor(Color.GRAY);
            }
            if (view.getId() == h.getId()){
                guess = "h";
                h.setBackgroundColor(Color.GRAY);
            }
            if (view.getId() == i.getId()){
                guess = "i";
                i.setBackgroundColor(Color.GRAY);
            }
            if (view.getId() == j.getId()){
                guess = "j";
                j.setBackgroundColor(Color.GRAY);
            }
            if (view.getId() == k.getId()){
                guess = "k";
                k.setBackgroundColor(Color.GRAY);
            }
            if (view.getId() == l.getId()){
                guess = "l";
                l.setBackgroundColor(Color.GRAY);
            }
            if (view.getId() == m.getId()){
                guess = "m";
                m.setBackgroundColor(Color.GRAY);
            }
            if (view.getId() == n.getId()){
                guess = "n";
                n.setBackgroundColor(Color.GRAY);
            }
            if (view.getId() == o.getId()){
                guess = "o";
                o.setBackgroundColor(Color.GRAY);
            }
            if (view.getId() == p.getId()){
                guess = "p";
                p.setBackgroundColor(Color.GRAY);
            }
            if (view.getId() == q.getId()){
                guess = "q";
                q.setBackgroundColor(Color.GRAY);
            }
            if (view.getId() == r.getId()){
                guess = "r";
                r.setBackgroundColor(Color.GRAY);
            }
            if (view.getId() == s.getId()){
                guess = "s";
                s.setBackgroundColor(Color.GRAY);
            }
            if (view.getId() == t.getId()){
                guess = "t";
                t.setBackgroundColor(Color.GRAY);
            }
            if (view.getId() == u.getId()){
                guess = "u";
                u.setBackgroundColor(Color.GRAY);
            }
            if (view.getId() == v.getId()){
                guess = "v";
                v.setBackgroundColor(Color.GRAY);
            }
            if (view.getId() == w.getId()){
                guess = "w";
                w.setBackgroundColor(Color.GRAY);
            }
            if (view.getId() == x.getId()){
                guess = "x";
                x.setBackgroundColor(Color.GRAY);
            }
            if (view.getId() == y.getId()){
                guess = "y";
                y.setBackgroundColor(Color.GRAY);
            }
            if (view.getId() == z.getId()){
                guess = "z";
                z.setBackgroundColor(Color.GRAY);
            }

            if (givenWord.contains(guess)){
                displayGuesses.setText(updateSpace(displaySpaces, givenWord, guess, false));
                boolean won = true;
                for (int i = 0; i < displaySpaces.length; i++){
                    if (displaySpaces[i].equals("_")){
                        won = false;
                    }
                }

                if (won){
                    win();
                }
            } else {
                livesLeft--;
                attempts.setText("Lives: " + livesLeft);
                if (livesLeft == 0) {
                    gameOver();
                }
            }
        }



        for (String s: displaySpaces){
            System.out.println(s);
        }
    }

    public void gameOver(){
        displayGuesses.setText(givenWord);
        results.setText("You Lose!");
        mayPressButtons = false;

    }

    public void win(){
        results.setText("You Win!");
        mayPressButtons = false;
    }



    public String generateWord(){

        Random random = new Random();
        int index = random.nextInt(wordList.length);

        return wordList[index].toLowerCase();

    }







}