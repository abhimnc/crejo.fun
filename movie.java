
public class movie {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		movie_helper m = new movie_helper();
		//1.add movies-------
		m.add_movie("\"Tiger\" released in Year 2008 for Genre \"Drama\"");
		m.add_movie( "\"Don\" released in Year 2006 for Genres \"Action\" & \"Comedy\"" );
		m.add_movie("\"Padmaavat\" released in Year 2006 for Genre \"Comedy\"");
		m.add_movie("\"Guru\" released in Year 2006 for Genre \"Drama\"" );
		m.add_movie("\"Metro\" released in Year 2006 for Genre \"Romance\"");
		m.add_movie(("\"Lunchbox\" released in Year 2021 for Genre \"Drama\"") );
		
		//1.add users------
		m.add_users("SRK");
		m.add_users("Salman");
		m.add_users("Deepika");
		m.add_users("salman");
		
		//2.add reviews------
		m.add_review("SRK","Don" ,2);
		m.add_review("SRK", "Padmaavat", 8);
		m.add_review("Salman", "Don", 5);
		m.add_review("Deepika", "Don", 9);
		m.add_review("Deepika", "Guru", 6);
		m.add_review("SRK", "Metro", 7);
		m.add_review("SRK","Don", 10);
		m.add_review("Deepika", "Lunchbox", 5);
		System.out.println(" ");
		
		//top genre
		//m.list_top_genre_n(1,"\"Romance\"");
		
		//average score according to genre
		//m.get_average_score_genre("Comedy");
		
		//3.List top n movies by total review score by ‘critics’ in a particular genre.
		System.out.println("List top n movies by total review score by ‘critics’ in a particular genre :");
		m.first_n_genre_user(1, "Action", "Salman");
		System.out.println(" ");
		
		
		//top according to year
		System.out.println("Top according to year");
		m.list_top_year(4, 2006);
		System.out.println(" ");
		
		//4.avg review for particular year-------------------
		System.out.println("avg review for particular year: " );
		m.avg_score_year(2006);
		System.out.println(" ");
		
		//5.average score for movie-----------
		System.out.println("avg of movie " + m.average_score_movie("Don"));
				
		
		//top n movies by review score in year by user prefered
		
		
	}

}
