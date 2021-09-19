package com.home;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author dillon
 * date: 2021/9/19 18:21
 * description:自定义类加载器
 */
public class CustomClassLoader extends ClassLoader {

    private File file;
    public CustomClassLoader(File file){
        this.file=file;
    }

    @Override
    protected Class<?> findClass(String name){
        try {
            byte[] content = getFileContent();
            return defineClass(name,content,0,content.length);
        } catch (IOException e) {
            throw new RuntimeException("查找类错误"+e.getMessage());
        }
    }
    private byte[] getFileContent() throws IOException {
        FileInputStream in=new FileInputStream(file);
        byte[] buffer=new byte[(int) file.length()];
        int len=0;
        len=in.read(buffer);
        for(int i=0;i<buffer.length;i++){
            buffer[i]= (byte) (255-buffer[i]);
        }
        return buffer;
    }
}
