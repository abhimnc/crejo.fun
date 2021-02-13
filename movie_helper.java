import java.util.*;



public class movie_helper {
	
	int year = 2020;
	List<String> movies = new ArrayList<String>();
	List<triplet<String, String, Integer>> movies_genre_date = new ArrayList<>();
	List<String> users = new ArrayList<String>();
	Map<String,Integer> user_map=new HashMap<>();
	List<triplet<String, String, Integer>> reviews_list = new ArrayList<>();//user, movie, review
	List<triplet<String, String, Integer>> viewer = new ArrayList<>();
	List<triplet<String, String, Integer>> critic = new ArrayList<>();
	List<triplet<String, String, Integer>> expert = new ArrayList<>();
	List<triplet<String, String, Integer>> admin = new ArrayList<>();
	
	
	
	public void add_movie(String moviess) {
		String[] movie_stat = moviess.split(" ");		
		//year
		String movie = movie_stat[0];
		int c_year=0;
		for (String a : movie_stat){
			
            if(a.equals("Year")){
            	break;
            }
            else{
            	c_year+=1;
            }
           
        }	
		String year = movie_stat[c_year+1];
		int year_int = Integer.parseInt(year);
				
		//genre
		
		int c_genres =0;
		String genre = "";

		for (String a : movie_stat){
			
			
            if(a.equals("Genres")){
            	genre = movie_stat[movie_stat.length-3] +" "+ movie_stat[movie_stat.length-1] ;
            	break;
            }
            else{
            	c_genres+=1;
            	genre = movie_stat[movie_stat.length-1];
            	
            }            
           
        }
		if(genre.split(" ").length>1){
			String[] x = genre.split(" ");
			movies_genre_date.add(new triplet(movie,x[0],year_int));
			System.out.println("Added "+movie+ " "+ x[0] + " "+ year);
			movies_genre_date.add(new triplet(movie,x[1],year_int));
			System.out.println("Added "+movie+ " "+ x[1] + " "+ year);
		}else{
			movies_genre_date.add(new triplet(movie,genre,year_int));
			System.out.println("Added "+movie+ " "+ genre + " "+ year);
		}
		
		movies.add(movie);
		
	  }
	
	public void add_users(String user) {
		String t = user.toLowerCase();
	    if(!users.contains(t)){
	    	users.add(t);
		    user_map.put(user, 0);
		    System.out.println("Added "+ user);
	    }else{
	    	System.out.println("Exception user already added");
	    }
	    
	  }
	
	public int date(String movie){
		for(int i=0; i<movies_genre_date.size();i++){
			
			String new_string = "\""+movie+"\"";
			if(movies_genre_date.get(i).getFirst().equals(new_string)){
				
				return movies_genre_date.get(i).getThird();
				//break;
			}
			
		}
		return 0;
	}
	
	
	public void add_review(String user,String movie, int review) {
			
		int flag =0;		
		for(int i=0; i<reviews_list.size(); i++){
			if(reviews_list.get(i).getFirst().equals(user) && reviews_list.get(i).getSecond().equals(movie)){
				flag=1;
				break;
						
			}
			else{
				flag=0;
			}
		}
			
			String movie_with_bracket = "\""+movie+"\"";
			if(movies.contains(movie_with_bracket) && date(movie)<=year){
				
				if(flag==1 ){
					System.out.println("Exception multiple reviews not allowed "+"(by "+ user +" for "+ movie+")");
				}else{
					int review_no = user_map.get(user);
					user_map.put(user, review_no+1);
					//System.out.println(review_no+1);
					if(review_no+1<3){
						reviews_list.add(new triplet(user,movie,review));
						viewer.add(new triplet(user,movie,review));
						System.out.println("Added reveiw for "+movie + " by 'viewer' "+ user + " points " + review);
					}else if(review_no+1>=3 && review_no+1<5){
						reviews_list.add(new triplet(user,movie,review*2));
						critic.add(new triplet(user,movie,review*2));
						System.out.println("Added reveiw for "+movie + " by 'critic' "+ user + " points " + review);
					}else if(review_no+1>=5 && review_no+1<8){
						reviews_list.add(new triplet(user,movie,review*3));
						expert.add(new triplet(user,movie,review*3));
						System.out.println("Added reveiw for "+movie + " by 'expert' "+ user + " points " + review);
					}else if(review_no+1>=8){
						reviews_list.add(new triplet(user,movie,review*4));
						admin.add(new triplet(user,movie,review*4));
						System.out.println("Added reveiw for "+movie + " by 'admin' "+ user + " points " + review);
					}
					
					
								
					
				}
			}else{
				System.out.println("Exception movie yet to be released");
			}	
	  }
	
	double average_score_movie(String movies){
		int avg =0;
		int c=0;
		for(int i=0; i<reviews_list.size();i++){
			if(reviews_list.get(i).getSecond().equals(movies)){
				//System.out.println(reviews_list.get(i).getSecond()+" "+movies);
				avg+=reviews_list.get(i).getThird();
				c+=1;
						
			}
		}
		 //System.out.println("Average review score for movie : ");
		 return (((double)avg/(double)c));
		 
	 }
	
	
	
	
	public static HashMap<String, Double> sortByValue(Map<String, Double> movie_review) { 
         
        List<Map.Entry<String, Double> > list = 
               new LinkedList<Map.Entry<String, Double> >(movie_review.entrySet()); 
  
       
        Collections.sort(list, new Comparator<Map.Entry<String, Double> >() { 
            public int compare(Map.Entry<String, Double> o1,  
                               Map.Entry<String, Double> o2) 
            { 
                return (o2.getValue()).compareTo(o1.getValue()); 
            } 
        }); 
          
         
        HashMap<String, Double> temp = new LinkedHashMap<String, Double>(); 
        for (Map.Entry<String, Double> aa : list) { 
            temp.put(aa.getKey(), aa.getValue()); 
        } 
        return temp; 
    } 
	
	
	
	public Map<String, Double> list_top_genre_n(int n, String genre) {
		
		String new_genre = "\""+genre+"\"";
		//System.out.println("List of top "+n+" in genre "+genre+" is:");
		Map<String,Double> movie_review =new HashMap<>();
		for(int i=0;i<movies_genre_date.size();i++){
			
			if(movies_genre_date.get(i).getSecond().equals(new_genre)){
				movie_review.put(movies_genre_date.get(i).getFirst(),average_score_movie(movies_genre_date.get(i).getFirst().replaceAll("^\"|\"$", "")  ) );
				//System.out.println(movies_genre_date.get(i).getFirst());
			}
		}
		
		Map<String, Double> hm1 = sortByValue(movie_review);
		int d=0;
		Iterator hmIterator = hm1.entrySet().iterator();
		while (hmIterator.hasNext() && d <n) { 
            Map.Entry mapElement = (Map.Entry)hmIterator.next(); 
            double marks = ((double)mapElement.getValue()); 
            //System.out.println(mapElement.getKey() + " : " + marks);
            d++;
        }
		//System.out.println(movie_review);
		return movie_review;
	}
	
	public void get_average_score_genre(String genre){
		
		String new_genre = "\""+genre+"\"";
		
		Map<String,Double> movie_review =new HashMap<>();
		for(int i=0;i<movies_genre_date.size();i++){
			
			if(movies_genre_date.get(i).getSecond().equals(new_genre)){
				movie_review.put(movies_genre_date.get(i).getFirst(),average_score_movie(movies_genre_date.get(i).getFirst().replaceAll("^\"|\"$", "")  ) );
			}
		}
		
		double sum=0;
		int n = 0;
		Iterator hmIterator = movie_review.entrySet().iterator();
		for (double f : movie_review.values()) {
		    sum += f;
		    n+=1;
		}
		System.out.println("Avegrage score according to genre : ");
		System.out.println(sum/n);
		//return (double)sum/(n);
		
	}
	
	public void list_top_year(int n, int year){
		Map<String,Double> movie_year_rating =new HashMap<>();
		for(int i=0; i<movies_genre_date.size(); i++){
			if(movies_genre_date.get(i).getThird()==year){
				movie_year_rating.put(movies_genre_date.get(i).getFirst(),average_score_movie(movies_genre_date.get(i).getFirst().replaceAll("^\"|\"$", "")));
			}
		}
		
		Map sorted = sortByValue(movie_year_rating);
		
		int d=0;
		Iterator hmIterator = sorted.entrySet().iterator();
		while (hmIterator.hasNext() && d <n) { 
            Map.Entry mapElement = (Map.Entry)hmIterator.next(); 
            double marks = ((double)mapElement.getValue()); 
            System.out.println(mapElement.getKey() + " : " + marks);
            d++;
        } 	
		
	}
	
	public void avg_score_year(int year){
		Map<String,Double> movie_year_rating =new HashMap<>();
		for(int i=0; i<movies_genre_date.size(); i++){
			if(movies_genre_date.get(i).getThird()==year){
				movie_year_rating.put(movies_genre_date.get(i).getFirst(),average_score_movie(movies_genre_date.get(i).getFirst().replaceAll("^\"|\"$", "")));
			}
		}
		
		Map sorted = sortByValue(movie_year_rating);
		double marks =0;
		int d=0;
		Iterator hmIterator = sorted.entrySet().iterator();
		while (hmIterator.hasNext() ) { 
            Map.Entry mapElement = (Map.Entry)hmIterator.next(); 
            marks += ((double)mapElement.getValue()); 
            //System.out.println(mapElement.getKey() + " : " + marks);
            d++;
        }
		System.out.println(marks/d);
		
		
	}
	
	
	public void first_n_genre_user(int first_n, String genre , String user){
		
		List<String> movis = new ArrayList<String>();
		Map<String, Double> hm1 = new HashMap<>();
		hm1 = list_top_genre_n(first_n,genre);
		
		Iterator hmIterator = hm1.entrySet().iterator();
	
		while (hmIterator.hasNext()) { 
            Map.Entry mapElement = (Map.Entry)hmIterator.next(); 
            String movi = (String) mapElement.getKey(); 
            for(int i=0; i<reviews_list.size();i++){
            	//System.out.println(reviews_list.get(i).getSecond());
            	if(reviews_list.get(i).getSecond().equals(((String) mapElement.getKey()).replaceAll("^\"|\"$", ""))&&reviews_list.get(i).getFirst().equals(user)){
            		movis.add((String) mapElement.getKey());
            	}
            }
        } 
		
		System.out.println(movis);
		
	}
	
	

}
