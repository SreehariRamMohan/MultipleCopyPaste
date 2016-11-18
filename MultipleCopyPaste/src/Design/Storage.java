package Design;

public class Storage {
	public String[] storage = new String[1000];
	private int currIndex = 1; 
	private boolean firstTime = true;
	
	public int getCurrentIndex() {
		return currIndex;
	}
	
	public void setCurrentIndex(int index) {
		currIndex = index;
	}

	public boolean getFirstTime() {
		return firstTime;
	}
	
	public void setFirstTime(boolean cond) {
		firstTime = cond;
	}
   
	public String cycleThrough() {
		// this method will be called when shift is pressed while command c is pressed
		//in order to cycle through the values. 
		if(currIndex == storage.length) currIndex = 0;
		int count = 0;
		while(storage[currIndex] == null) {
			currIndex++;
			if(currIndex == storage.length) currIndex = 0;
			count++;
			if(count == storage.length+1) return "Empty!";
		}
			return storage[currIndex++];
		 
	}
	
	public void add(String data) {
		firstTime = false;
		int toPlace = -1;
		for(int i = 0; i < storage.length; i++) {
			if(storage[i] == null) {
				toPlace = i;
				break;
			}
		} 
		if(toPlace == -1) {
			System.out.println("No more room in storage, overwriting first data piece");
			storage[0] = data;
			System.out.println("Added " + data + " succesfully");
		} else {
			storage[toPlace] = data;
			System.out.println("Added " + data + " succesfully");
		}
	}
	
	public void printAll() {
		System.out.println("--------------------");
		for(int i = 0; i < storage.length; i++) {
			if(i < storage.length -1) {
				if(storage[i] == null) {
					System.out.print("FREE" + "  ");
				} else {
					System.out.print(storage[i] + "  ");
				}
			} else {
				if(storage[i] == null) {
					System.out.print("FREE" + "  ");
				} else {
					System.out.print(storage[i]);
				}
			}
		}
		System.out.println();
		System.out.println("--------------------");
	}
	public void percentFull() {
		double max = storage.length;
		double used = 0;
		for(int i = 0; i < storage.length; i++) {
			if(storage[i] != null) {
				used++;
			}
		}
		System.out.println("You used " + (int)((100*used)/max) + "% of total space.");
	}
	
	public void increaseSize(int size) {
		if(size < storage.length) {
			System.out.println("Size too small. You will lose data. Please delete items or ask for larger storage.");
			System.out.println("Command FAILED");
			return;
		}
		String[] temp = new String[size];
		for(int i = 0; i < storage.length; i++) {
			temp[i] = storage[i];
		}
		storage = new String[size];
		for(int i = 0; i < storage.length; i++) {
			storage[i] = temp[i];
		}
		temp = null;
		System.out.println("Succesfully transfered!");
		temp = null;
	}
	
	public void remove(String data) {
		for(int i = 0; i < storage.length; i++) {
			if(storage[i] == data) {
				remove(i);
				return;
			}
		}
	}
	
	public void remove(int index) {
		if(storage[index-1] != null) {
			String removed = storage[index-1];
			storage[index-1] = null;
			System.out.println("removed " + index + "th index" + " which was " + removed );
		} else if(storage[index-1] == null) {
			String removed = storage[storage.length-1];
			storage[storage.length-1] = null;
			System.out.println("removed " + index + "th index" + " which was " + removed );
		}
	}

	public String spitOutArray() {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < storage.length; i++) {
			if(storage[i] != null) {
				sb.append(storage[i]);
				sb.append(" ");
				System.out.println("......");
			}
		}
		return sb.toString();
		
		/*String summary = "";
		System.out.println("In summary block");
		for(int i = 0; i < storage.length; i++) {
			if(storage[i] != null) {
				summary += storage[i] + " ";
				System.out.println("......");
			}
		}
		return summary; */
	}
	
	public void deleteAll() {
		for(int i = 0; i < storage.length; i++) {
			storage[i] = null;
		}
		System.out.println("Deleted entire database");
			
	}
	
	
	

}
