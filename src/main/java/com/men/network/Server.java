package com.men.network;

import com.men.message.Msg;
import com.men.util.Calculate;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static final int PORT = 8080;


    public static void main(String[] args) throws IOException {

        ServerSocket server = new ServerSocket(PORT);
        System.out.println("Server start...");
        Socket socket = server.accept();

        //ожидаем поток данных
        try (
                OutputStream out = socket.getOutputStream();
                InputStream in = socket.getInputStream();
                ObjectInputStream objectInputStream = new ObjectInputStream(in)) {
            while (true) {

                //принимаем и формируем по классу Msg
                Msg msg = (Msg) objectInputStream.readObject();
                System.out.println("Сообщение получено и обрабатывается...");

                //Вызываем метод обработки номера протокола
                final int result = Calculate.execute(msg);
                System.out.println("Обработка завершена");

                //Формируем ответ
                msg.setResult(result);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(out);
                System.out.println("Отправка ответа...");
                objectOutputStream.writeObject(msg);
            }

        } catch (IOException e) {
            server.close();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
