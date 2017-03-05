package main.entities.dao;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.lang.reflect.Type;

import java.io.*;
import java.nio.file.*;
import java.util.*;

import main.entities.Entity;

public abstract class BaseDAO<Model extends Entity> {
    private List<Model> list = new ArrayList<Model>();

    private final String DATABASE_PATH;

    public BaseDAO(String path, Type listType) {
        DATABASE_PATH = path;
        initializeList(listType);
    }

    public List<Model> getAll() {
        return list;
    }

    public Model get(Integer id) throws NoSuchElementException {
        for (Model m : list) {
            if (m.getId().equals(id)) {
                return m;
            }
        }
        throw new NoSuchElementException();
    }

    public void saveNew(Model m) {
        m.setId(list.size() != 0 ? list.get(list.size() - 1).getId() + 1 : 1);
        list.add(m);
        syncJSON();
    }

    public void update(Model modelToSave) {
        Integer requestedId = modelToSave.getId();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId().equals(requestedId)) {
                list.set(i, modelToSave);
                syncJSON();
                return;
            }
        }
    }

    public void delete(Model modelToDelete) {
        Integer requestedId = modelToDelete.getId();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId().equals(requestedId)) {
                list.remove(i);
                syncJSON();
                return;
            }
        }
    }

    private void initializeList (Type listType) {
        try {
            byte[] encoded = Files.readAllBytes(Paths.get(DATABASE_PATH));
            String str = new String(encoded, StandardCharsets.UTF_8);

            Gson gson = new Gson();
            list = gson.fromJson(str, listType);
        } catch (IOException e) {
            createDBFile();
        }
    }

    private void createDBFile () {
        try {
            File f = new File(DATABASE_PATH);
            f.getParentFile().mkdirs();
            f.createNewFile();
            syncJSON();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    private void syncJSON () {
        File f = new File(DATABASE_PATH);
        try {
            Writer writer = new FileWriter(f);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(list, writer);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
