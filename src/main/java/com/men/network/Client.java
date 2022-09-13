package com.men.network;

import com.men.message.Msg;

import java.io.*;
import java.net.Socket;

public class Client {
    private static boolean stop = false;

    public static void main(String[] args) throws IOException {
        try (
                Socket clientSocket = new Socket("localhost", 8080);
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                OutputStream out = clientSocket.getOutputStream();
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(out)) {
            do {

                Msg msg = new Msg();
                do {
                    System.out.println("Введите номер протокола(1-4): ");
                    System.out.println("1 - сложение");
                    System.out.println("2 - вычитание");
                    System.out.println("3 - умножение");
                    System.out.println("4 - деление");
                    try {
                        int ver = Integer.parseInt(reader.readLine());
                        if (ver < 5 && ver > 0) {
                            msg.setVersionProtocol(ver);
                        } else {
                            throw new NumberFormatException();
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Неверный номер порта");
                    }
                } while (msg.getVersionProtocol() == 0);

                boolean interrupt = true;

                do {
                    System.out.println("Введите значение 1: ");
                    try {
                        msg.setA(Integer.parseInt(reader.readLine()));
                        interrupt = false;
                    } catch (NumberFormatException e) {
                        System.out.println("Введены неверные данные!");
                    }

                } while (interrupt);

                interrupt = true;

                do {
                    System.out.println("Введите значение 2: ");
                    try {
                        msg.setB(Integer.parseInt(reader.readLine()));
                        if (msg.getB() == 0) {
                            System.out.println("Деление на ноль!");
                            throw new NumberFormatException();
                        }
                        interrupt = false;
                    } catch (NumberFormatException e) {
                        System.out.println("Введены неверные данные!");
                    }

                } while (interrupt);

                objectOutputStream.writeObject(msg);
                System.out.println("Сообщение отправлено");
                System.out.println("Ответ от сервера");

                //создаём ресурсы на выходящий поток

                InputStream in = clientSocket.getInputStream();
                ObjectInputStream objectInputStream = new ObjectInputStream(in);
                Msg msgOut = (Msg) objectInputStream.readObject();
                System.out.print("Версия используемого протокола: ");
                System.out.println(msgOut.getVersionProtocol());
                System.out.println("Результат: " + msgOut.getResult());

                interrupt = true;

                do {
                    System.out.println("Повторить ввод данных (y/n)?: ");
                    String str = reader.readLine();
                    if (str.equalsIgnoreCase("y")) {
                        stop = false;
                        interrupt = false;
                    } else if (str.equalsIgnoreCase("n")) {
                        stop = true;
                        interrupt = false;
                    } else {
                        System.out.println("Введите корректный ответ! (y - повторить, n - выход из программы)");
                    }

                } while (interrupt);
            } while (!stop);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}