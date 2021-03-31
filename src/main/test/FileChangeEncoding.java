import java.io.*;

public class FileChangeEncoding {
    public static void main(String[] args) throws IOException {
        File file = new File("D:\\杂\\CTour-master\\frontend\\src\\code\\more");
        for(String item : file.list()){
//            File file1 = new File(file+"/"+item).
//            BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(file+"/"+item)));
            FileInputStream fileInputStream = new FileInputStream(file+"/"+item);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            char[] bus = new char[1024];
            int len = 0;
            FileOutputStream fileOutputStream = new FileOutputStream("D:\\project\\Hello-C\\code-UTF\\more\\"+item);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream,"UTF-8");
            while((len = inputStreamReader.read(bus))!=-1){
                outputStreamWriter.write(bus,0,len);
            }
            outputStreamWriter.close();
            fileOutputStream.close();
            inputStreamReader.close();
            fileInputStream.close();
        }
    }
}
