import java.util.HashMap;

public class LSystem {
	
	
	
	String final_word;
	
	public LSystem(){
	
	}
	
	//generate the current sentence 
	public void generate(String word, HashMap<String,String> rules, int depth){
		
		//variables to store current word and word for next iteration 
		String[] current_word;
		String new_word;
		
		//base case: update global variable & return 
		if(depth == 0){
			final_word = word;
			return;
		}
		
		current_word = word.split("");//break current iteration into characters 
		new_word = "";//Instantiate new word 
		
		//loop through current word, retrieve associated grammar and update new word 
		for(int i=0; i<current_word.length; i++){	
			
			//logic to allow for constants 
			if(rules.containsKey(current_word[i])){
				new_word += rules.get(current_word[i]);	
			}else{
				new_word += current_word[i];
			}
		}
		
		generate(new_word,rules,depth-1);//recurse w/ depth-1

	}
	
	
	//return the final word 
	public String getFinalWord(){
		return final_word;
	}

}
