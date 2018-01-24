import java.util.ArrayList;

public class Subsequence {

	String key = "abppblee";
	String[] dict = new String[]{"able","ale","apple","bale","kangaroo"};
	
	ArrayList<String> check = new ArrayList<>();
	
	
	public Subsequence(){
		
		for(int i=0; i<dict.length; i++){
			
			String[] key_char = key.split("");
			String[] dict_char = dict[i].split("");
			String newWord = "";
			int dict_index = 0;
			
			for(int j=0; j<key.length(); j++){
				
				
				if(dict_index < dict_char.length){
	
				//cycle through key_char
				String current_key = key_char[j];
				String current_dict = dict_char[dict_index];
				
					if(current_key.equals(current_dict)){
						newWord+= current_dict;
						dict_index++;
	
					}
				}
			}	
			
			System.out.println(newWord);

		}

	}
	
	
}
