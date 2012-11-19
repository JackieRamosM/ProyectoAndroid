package comic_it.project;

import java.util.ArrayList;

public class ActivitiesBringe {

private static ArrayList<Tweetc> tweets;
private static TwitterApp mTwitter;
	
public static void setObject(ArrayList<Tweetc> tw) {
	tweets = tw;
}

public static ArrayList<Tweetc> getObject() {

return tweets;
}

public static void setMtwitter(TwitterApp mTwitterx) {
	mTwitter = mTwitterx;
}
public static TwitterApp getMtwitter() {

return  mTwitter ;
}


}
