import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Interface {

	public static void main(String[] args) throws DictionaryException, MultimediaException {

		BSTDictionary dict = new BSTDictionary(); // Initialize a new BSTDictionary
		readFile(args, dict); // Adds all the contents of the file into the BSTdictionary
		StringReader keyboard = new StringReader(); // Initialize a new keyboard to read in text

		while (true) {

			String line = keyboard.read("Enter next Command: ");
			String[] parts = line.split(" ");

			// exit
			if (parts[0].toLowerCase().equals("exit")) {
				break;
			}

			switch (parts[0]) {

			case "define": {
				Key key = new Key(parts[1], 1);
				Record record = dict.get(key);
				if (record == null) {
					System.out.println("The word w is not in the ordered dictionary");
				} else {
					System.out.println(record.getDataItem());
				}

				break;

			}

			case "translate": {
				Key key = new Key(parts[1], 2);
				Record record = dict.get(key);
				if (record == null) {
					System.out.println("There is no definition for the word w");
				} else {

					System.out.println(record.getDataItem());
				}

				break;
			}

			case "sound": {
				Key key = new Key(parts[1], 3);
				Record record = dict.get(key);
				if (record == null) {
					System.out.println("There is no sound file for w");
				} else {
					try {

						SoundPlayer player = new SoundPlayer();
						player.play(record.getDataItem());
					} catch (MultimediaException e) {

						System.out.println(e.getMessage());
					}
				}
				break;
			}

			case "play": {
				Key key = new Key(parts[1], 4);
				Record record = dict.get(key);
				if (record == null) {
					System.out.println("There is no music file for w");
				} else {
					try {

						SoundPlayer player = new SoundPlayer();
						player.play(record.getDataItem());
					} catch (MultimediaException e) {

						System.out.println(e.getMessage());
					}
				}
				break;
			}
			case "say": {
				Key key = new Key(parts[1], 5);
				Record record = dict.get(key);
				if (record == null) {
					System.out.println("There is no voice file for w");
				} else {
					try {

						SoundPlayer player = new SoundPlayer();
						player.play(record.getDataItem());
					} catch (MultimediaException e) {

						System.out.println(e.getMessage());
					}
				}
				break;

			}
			case "show": {

				Key key = new Key(parts[1], 6);
				System.out.println(parts[1]);
				Record record = dict.get(key);
				if (record == null) {
					System.out.println("There is no image file for w");
				} else {
					try {
						
						PictureViewer viewer = new PictureViewer();
						viewer.show(record.getDataItem());
					} catch (MultimediaException e) {

						System.out.println(e.getMessage());
					}
				}
				break;
			}
			case "animate": {

				Key key = new Key(parts[1], 7);
				Record record = dict.get(key);
				if (record == null) {
					System.out.println("There is no animated image file for w");
				} else {
					try {

						PictureViewer viewer = new PictureViewer();
						viewer.show(record.getDataItem());
					} catch (MultimediaException e) {

						System.out.println(e.getMessage());
					}
				}
				break;
			}

			case "browse": {
				Key key = new Key(parts[1], 8);
				Record record = dict.get(key);
				if (record == null) {
					System.out.println("There is no animated image file for w");
				} else {
					try {

						ShowHTML browser = new ShowHTML();
						browser.show(record.getDataItem());
					} catch (Exception e) {

						System.out.println(e.getMessage());
					}
				}
				break;
			}

			case "delete": {
				Key key = new Key(parts[1], Integer.parseInt(parts[2]));
				Record record = dict.get(key);
				if (record == null) {
					System.out.println("No record in the ordered dictionary has key (" + parts[1] + "," + parts[2] + ")");
				} else {
					dict.remove(key);
				}

			}

			case "add": {
				Key key = new Key(parts[1], Integer.parseInt(parts[2]));
				Record record = dict.get(key);
				if (record == null) {
					Key keyInsert = new Key(parts[1], Integer.parseInt(parts[2]));
					Record recordInsert = new Record(key, parts[3]);
					dict.insert(dict.getRoot(), recordInsert);
				} else {

					System.out.println("A record with the given key (" + parts[1] + "," + parts[2]
							+ ") is already in the ordered dictionary");
				}

			}

			// list prefix
			// Must be one or more letters
			case "list": {
				StringBuilder resultList = new StringBuilder();
				prefixBSTDictionaryFinder(dict.getRoot(), parts[1], resultList);

				if (resultList.length() > 0) {
					System.out.println(resultList.substring(0, resultList.length() - 2));
				} else {
					System.out.println("No label attributes in the ordered dictionary start with prefix " + parts[1]);
				}
				break;
			}

			case "first": {
				StringBuilder resultFirst = new StringBuilder();
				BSTNode smallest = dict.smallest(dict.getRoot());
				resultFirst.append(smallest.getRecord().getKey().getLabel());
				resultFirst.append(",");
				resultFirst.append(smallest.getRecord().getKey().getType());
				resultFirst.append(",");
				resultFirst.append(smallest.getRecord().getDataItem());
				resultFirst.append(".");
				System.out.println(resultFirst);
				break;
			}

			case "last": {
				StringBuilder resultLast = new StringBuilder();
				BSTNode largest = dict.largest(dict.getRoot());
				resultLast.append(largest.getRecord().getKey().getLabel());
				resultLast.append(",");
				resultLast.append(largest.getRecord().getKey().getType());
				resultLast.append(",");
				resultLast.append(largest.getRecord().getDataItem());
				resultLast.append(".");
				System.out.println(resultLast);
				break;
			}

			default:
				System.out.println("Invalid command.");

			}

		}

	}

	/**
	 * 
	 * @param Root   Root of binary tree passed through
	 * @param Prefix String Prefix to find in the Binary Tree
	 * @param result Result is StringBuilder, that holds all of the found data
	 */

	private static void prefixBSTDictionaryFinder(BSTNode Root, String Prefix, StringBuilder result) {

		if (Root == null)
			return;
		else {

			if (Root.getRecord().getDataItem().endsWith(Prefix)) {
				result.append(Root.getRecord().getDataItem());
				result.append(", ");
			}
			prefixBSTDictionaryFinder(Root.getLeftChild(), Prefix, result);
			prefixBSTDictionaryFinder(Root.getRightChild(), Prefix, result);
		}

	}

	/**
	 * Checks if the given argument is correct or missing, throws IOException if
	 * error with reading file Throws error if nothing was passed
	 * 
	 * @param args Uses first argument provided
	 * @param dict BSTDictionary storing key and data labels
	 * @throws DictionaryException
	 */
	private static void readFile(String[] args, BSTDictionary dict) throws DictionaryException {
		// checks if a file was passed through
		if (args.length < 1) {
			System.err.println("No String provided");
		}

		else {
			// takes the first argument inputFIle
			String inputFile = args[2];

			try {

				BufferedReader br = new BufferedReader(new FileReader(inputFile));
				String line1;
				String line2;

				while ((line1 = br.readLine()) != null && (line2 = br.readLine()) != null) {
					readFileLine(line1, line2, dict);

				}

			} catch (IOException e) {
				System.err.println("Error in reading the inputFile");
			}
		}
	}

	/**
	 * 
	 * @param label Key for BSTDict
	 * @param data  Data for BSTDict
	 * @param dict  BSTDictionary holding label, data, type
	 */
	private static void readFileLine(String label, String data, BSTDictionary dict) throws DictionaryException {

		int findType = findType(data);
		if (findType == 3 || findType == 4 || findType == 5 || findType == 2) {
			data = data.substring(1);
		}
		Key key = new Key(label.toLowerCase(), findType);
		Record record = new Record(key, data);
		try {
			dict.put(record);
		} catch (DictionaryException e) {
			System.out.println(data);
			System.out.println(label);
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param data Seconds sentence, finds the type for key
	 * @return int type for key
	 */
	private static int findType(String data) {
		int type = 0;
		char typeChar = data.charAt(0);

		// Set the type
		switch (typeChar) {

		case '-': // sound file
			type = 3;
			break;
		case '*': // voice file
			type = 5;
			break;
		case '/': // Translation to French
			type = 2;
			break;
		case '+': // Music File
			type = 4;
			break;
		}
		
		// must be one of the weird ones
		if (0 == type) {
			String typeString = data.substring(data.length() - 4, data.length());
			switch (typeString) {

			case "html": // Web Page
				type = 8;
				break;
			case ".jpg": // Image file

				type = 6;
				break;
			case ".gif": // Animated Image File
				type = 7;
				break;
			default: // String containing a definition of a label
				type = 1;
				break;

			}
		}

		return type;

	}

}
