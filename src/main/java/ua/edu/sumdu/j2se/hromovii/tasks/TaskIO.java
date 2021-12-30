package ua.edu.sumdu.j2se.hromovii.tasks;

import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Iterator;

public class TaskIO implements Serializable {
    public static void write(AbstractTaskList tasks, OutputStream out) {
        try (DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(out))) {
            dos.writeInt(tasks.size());
            for (Task task : tasks
            ) {
                dos.writeUTF(task.getTitle());
                dos.writeLong(task.getTime().toEpochSecond(ZoneOffset.UTC));
                dos.writeLong(task.getStartTime().toEpochSecond(ZoneOffset.UTC));
                dos.writeLong(task.getEndTime().toEpochSecond(ZoneOffset.UTC));
                dos.writeInt(task.getRepeatInterval());
                dos.writeBoolean(task.isActive());
                dos.writeBoolean(task.isActive());
            }
            dos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void read(AbstractTaskList tasks, InputStream in) {
        try (DataInputStream dis = new DataInputStream(new BufferedInputStream(in))) {
            int size = dis.readInt();
            for (int i = 0; i < size; i++) {
                String title = dis.readUTF();
                LocalDateTime time = LocalDateTime.ofEpochSecond(dis.readLong(), 0, ZoneOffset.UTC);
                LocalDateTime start = LocalDateTime.ofEpochSecond(dis.readLong(), 0, ZoneOffset.UTC);
                LocalDateTime end = LocalDateTime.ofEpochSecond(dis.readLong(), 0, ZoneOffset.UTC);
                int interval = dis.readInt();
                boolean repeated = dis.readBoolean();
                boolean active = dis.readBoolean();
                tasks.add(new Task(title, time, start, end, interval, repeated, active));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeBinary(AbstractTaskList tasks, File file) {
        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file))) {
            write(tasks, bos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readBinary(AbstractTaskList tasks, File file) {
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file))) {
            read(tasks, bis);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void write(AbstractTaskList tasks, Writer out) {
        Gson gson = new Gson();
        try (BufferedWriter writer = new BufferedWriter(out)) {
            writer.write(gson.toJson(tasks));
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void read(AbstractTaskList tasks, Reader in) {
        try (BufferedReader reader = new BufferedReader(in)) {
            AbstractTaskList tempList = null;
            String jsonData;
            while ((jsonData = reader.readLine()) != null){
                Gson gson = new Gson();
                tempList = gson.fromJson(jsonData, tasks.getClass());
            }
            for (Task task : tempList) {
                tasks.add(task);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeText(AbstractTaskList tasks, File file) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            write(tasks, bw);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readText(AbstractTaskList tasks, File file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            read(tasks, br);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
