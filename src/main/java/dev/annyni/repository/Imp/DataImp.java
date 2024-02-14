package dev.annyni.repository.Imp;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class DataImp {

    private final Gson gson = new Gson();
    private final File file;

    public DataImp(File file) {
        this.file = file;
    }

    public <T> List<T> readData(Class<T> tClass){
        try(Reader reader = new FileReader(file)) {
            Type type = TypeToken.getParameterized(List.class, tClass).getType();
            List<T> data = gson.fromJson(reader, type);

            if (data == null){
                data = new ArrayList<>();
            }

            return data;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> void writeData(List<T> data){
        try(Writer writer = new FileWriter(file)) {
            gson.toJson(data, writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
