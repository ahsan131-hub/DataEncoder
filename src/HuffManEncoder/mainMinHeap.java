package HuffManEncoder;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Scanner;

//donot edit just seee the code
public class mainMinHeap {
//    public static void main(String[] args) {
//        try {
//            String str = "";
//           encodeFile(new File(str));
//            String decodeFile="/home/ahsan/A/DS/data_structure/encoded_files/sample_E.txt";
//  //        var node=  DecodeNodeFromAFile(new FileReader(new File(decodeFile)));
//           DecodeFile(new File(decodeFile));
//    //        System.out.println(node);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//
//    }

    public static boolean DecodeFile(File file) throws IOException {
        //Decoding starts here
      //  FileReader reader = new FileReader(file);
      //  System.out.println("decoded Value");
        File file1=makeDirectoryForDecoded(file,"_D");
        if(!decode_file(new Scanner(file),file1.getAbsolutePath())){
            Path path=Paths.get(file+"/decoded_files");
            file1.delete();
           return false;
        }
        return true;

    }

    private static File makeDirectoryForDecoded(File file, String d) {
        Path dir= Paths.get(file.getParent()+"/decoded_files/");
        try {
            Files.createDirectories(dir);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String str=file.getName();
        str.indexOf(".");
      //  System.out.println(str.indexOf("."));

        str=str.substring(0,str.indexOf("."))+d+str.substring(str.indexOf("."),str.length());
      //  System.out.println();
        File file1=new File(dir+"/"+str);

        return file1;
    }

    public static boolean encodeFile(File file) throws IOException {
        if(!(file.exists()&&file.length()>0)){
            return false;
        }
        MinHeap que = new MinHeap();
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        HashMap<Integer, String> codesMap = new HashMap<>();
        FileReader reader1 = null;
        Scanner scan = null;
        reader1 = new FileReader(file);
        map = getFreqOfChar(reader1, map);
        System.out.println(map.toString());
        Node root = getHuffmanNode(map, que);
        getHashMapForWrite(root, "", codesMap);//create the hashMap

      //  EncodeNodeInFile(root,file);
        return writeDataCodeToFile(root, codesMap, file);
    }

    private static HashMap<Character, Integer> getFreqOfChar(FileReader reader, HashMap<Character, Integer> map) throws IOException {
        int ch;
        while ((ch = reader.read()) != -1) {
            char c = (char) ch;
            //        System.out.println(ch);
            if (map.get(c) != null) {
                //map.replace(c, map.get(c) + 1);
                //      System.out.println("in replace "+ c);
                map.replace(c, map.get(c), map.get(c) + 1);
            } else {
                //      System.out.println("in put "+ c);
                map.put(c, 1);
            }
        }
        //System.out.println(map.toString());
        return map;
    }

    private static boolean writeDataCodeToFile(Node root, HashMap<Integer, String> codesMap, File file) {
        FileWriter writer = null;
        try {
            File file1=makeDirectory(file,"_E");
            writer = new FileWriter(file1);
            writer.write("!--\n");
            EncodeNodeInFile(writer,root);
            writer.write("\n--!\n");
            writeCodesToFile(root, "", writer, codesMap, file);
            writer.flush();
            writer.close();
        } catch (IOException e) {

            return false;

        }

        return true;
    }

    private static Node getHuffmanNode(HashMap<Character, Integer> map, MinHeap que) {
        var mapIterator = map.entrySet().iterator();
        while (mapIterator.hasNext()) {
            var mapElement = mapIterator.next();
            que.insert(new Node(mapElement.getValue(), mapElement.getKey()));

        }
        //the method will convert queue of the bunch of node into the the single and that node
        // will be the rooot node having rest of the node into its left-right subtree
        makeHuffManTree(que);
        //retrieve root node from tree
        return que.remove();
    }

    private static void writeCodesToFile(Node root, String s, FileWriter writer, HashMap<Integer, String> codesMap, File reader) throws IOException {
        Scanner scan = new Scanner(reader);
    //    System.out.println(codesMap.toString());
        int ch;
        while (scan.hasNext()) {
            String str = scan.nextLine();
            //System.out.println(str);
            //str=codesMap.get(str);
            for (int i = 0; i < str.length(); i++) {
                String code = String.valueOf(codesMap.get((str.charAt(i))));
            //    System.out.println(code);
                writer.write(code);
                writer.write(32);
            }
            String code = String.valueOf(codesMap.get((' ')));
            writer.write(code);
            writer.write("\r");


        }

        //System.out.println("wrieting complete");


        // base case; if the left and right are null
        // then its a leaf node and we print
        // the code s generated by traversing the tree.
//        if (root.left == null && root.right == null) {
//            //&& Character.isLetter(root.value)
//            // c is the character in the node
//            System.out.println(root.value + ":" + s);
//            System.out.println("byte value" +Byte.parseByte(s,2));
//            writer.write(s);
//            writer.write(32);
//            return;
//        }
//
//        // if we go to left then add "0" to the code.
//        // if we go to the right add"1" to the code.
//
//        // recursive calls for left and
//        // right sub-tree of the generated tree.
//        writeCodesToFile(root.left, s + "0", writer, codesMap, reader);
//        writeCodesToFile(root.right, s + "1", writer, codesMap, reader);
    }

    private static Node DecodeNodeFromAFile(FileReader read) throws IOException {

        if (((char) (read.read())) == '1') {
            int b = read.read();
            return new Node((char) b, null, null);
            // return new Node(Integer.valueOf(ch), null, null);
        } else {
            Node leftChild = DecodeNodeFromAFile(read);
            Node rightChild = DecodeNodeFromAFile(read);
            return new Node('0', leftChild, rightChild);
        }

    }

//  //  private static void EncodeNodeInFile(Node root,File file) {
//
//        try {
//
//            File file1=makeDirectory(file,"_key");
//
//            FileWriter writer = new FileWriter(file1);
//            EncodeNodeInFile(writer, root);
//            writer.flush();
//            writer.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    private static File makeDirectory(File file, String key) {
        Path dir= Paths.get(file.getParent()+"/encoded_files/");
        try {
            Files.createDirectories(dir);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String str=file.getName();
        str.indexOf(".");
      //  System.out.println(str.indexOf("."));

        str=str.substring(0,str.indexOf("."))+key+str.substring(str.indexOf("."),str.length());


      //  System.out.println();
        File file1=new File(dir+"/"+str);

        return file1;
    }

    private static void EncodeNodeInFile(FileWriter writer, Node root) {
        try {
            if (root.left == null) {
                writer.write('1');
                writer.write(root.value);
            } else {
                writer.write('0');
                EncodeNodeInFile(writer, root.left);
                EncodeNodeInFile(writer, root.right);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * private static String getValueOfCode(Node root, String s, String str, Node mainRoot, String codeChar, HashMap<String, Integer> map) {
     * if (s.length() <= 0) {
     * return str;
     * } else {
     * if (s.charAt(0) == '0') {
     * if (root.left != null)
     * root = root.left;
     * <p>
     * codeChar+="0";
     * } else if (s.charAt(0) == '1') {
     * //  System.out.println(" root right" + root.value);
     * if (root.right != null)
     * root = root.right;
     * codeChar+="1";
     * }
     * if (root.left == null && root.right == null) {
     * //System.out.println("true");
     * str += root.value;
     * map.put(codeChar,(int)(root.value));
     * System.out.println("Get value of the code"+root.value);
     * root = mainRoot;
     * codeChar="";
     * <p>
     * }
     * <p>
     * }
     * return getValueOfCode(root, s.substring(1), str, mainRoot,codeChar,map);
     * <p>
     * }
     * <p>
     * public static void printCode(Node root, String s) {
     * // base case; if the left and right are null
     * // then its a leaf node and we print
     * // the code s generated by traversing the tree.
     * if (root.left == null && root.right == null) {
     * //&& Character.isLetter(root.value)
     * // c is the character in the node
     * System.out.println(root.value + ":" + s);
     * return;
     * }
     * <p>
     * // if we go to left then add "0" to the code.
     * // if we go to the right add"1" to the code.
     * <p>
     * // recursive calls for left and
     * // right sub-tree of the generated tree.
     * printCode(root.left, s + "0");
     * printCode(root.right, s + "1");
     * }
     */
    private static void makeHuffManTree(MinHeap que) {
        if (que.size() >= 2) {
            var first = que.remove();
            var second = que.remove();
            Node newNode = new Node(first.key + second.key);
            newNode.setLeft(first);
            newNode.setRight(second);
            que.insert(newNode);
            makeHuffManTree(que);
        }
    }

    public static void getHaspMapForRead(Node root, String string, HashMap map) {
        if (root != null) {
            if (root.right == null & root.left == null) {
             //   System.out.println(root.value + "Corresponding Code " + string);
                map.put(string, root.value);
            }
            if (root.left != null) {
                getHaspMapForRead(root.left, string + "0", map);
            }
            if (root.right != null) {
                getHaspMapForRead(root.right, string + "1", map);
            }
        }
    }

    public static void getHashMapForWrite(Node root, String string, HashMap map) {
        if (root != null) {
            if (root.right == null & root.left == null) {
                //System.out.println(root.value + "Corresponding Code " + string);
                map.put(root.value, string);
            }
            if (root.left != null) {
                getHashMapForWrite(root.left, string + "0", map);
            }
            if (root.right != null) {
                getHashMapForWrite(root.right, string + "1", map);
            }
        }
    }

    private static boolean decode_file(Scanner inputStream, String  path) throws IOException {
        File file=new File(path);
        BufferedWriter outputStream = new BufferedWriter(new FileWriter(file));
       // inputStream.next()
        String code="";
        if(inputStream.nextLine().equals("!--")) {
            String line =inputStream.nextLine();
            while (!line.equals("--!")) {
                code+=line+"\r";
                line=inputStream.nextLine();
            }
        }else {
            return false;
        }
    //    System.out.println("the code "+code);
        outputStream.write(code);
        outputStream.flush();

        HashMap<Integer, String> decodeMap = new HashMap<>();

        var decodeNode = DecodeNodeFromAFile(new FileReader(file));
        file.delete();
        outputStream=new BufferedWriter(new FileWriter(new File(path)));

        getHaspMapForRead(decodeNode, "", decodeMap);
     //   System.out.println("decode map" + decodeMap.toString());
        while (inputStream.hasNext()) {
            String[] arrCode = inputStream.nextLine().split(" ");
            //   System.out.println(("here codes"+codes));
            for (String codes : arrCode) {
                if (decodeMap.containsKey(codes)) {
                    String info = String.valueOf(decodeMap.get(codes));
                  //  System.out.print(info);
                    for (int i = 0; i < info.length(); i++) {
                        outputStream.write(info.charAt(i));
                    }
                }
            }
            outputStream.write("\r");
        }
        outputStream.flush();
        outputStream.close();
        return true;
    }

}

